package datapro.eibs.client;
/**
* Insert the type's description here.
* Creation date: (1/19/00 6:08:55 PM)
* @author: Orestes Garcia
*/
import java.io.*;
import java.net.*;
import java.util.List;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;

public class JSERA0011 extends datapro.eibs.master.SuperServlet {
	protected static final int A_DELETE = 600;
	protected static final int A_ENTER_NEW = 400;
	protected static final int A_NEW = 500;
	protected static final int A_GA_BASIC = 2;
	protected static final int A_GA_DETAIL_LIST = 4;
	protected static final int A_GA_DETAIL = 5;
	protected static final int A_GA_NONE_ACC = 8;
	protected static final int A_INST_SPECIAL = 12;
	protected static final int A_SPECIAL_COD = 10;
	protected static final int A_TR_COLL = 14;
	protected static final int R_ENTER_NEW = 300;
	protected static final int A_GA_POLIZA_LIST = 6;
	protected static final int A_GA_POLIZA_DETAIL = 13;
	// options
	protected static final int R_GA_BASIC = 1;
	protected static final int R_GA_DETAIL_LIST = 3;
	protected static final int R_GA_NONE_ACC = 7;
	protected static final int R_INST_SPECIAL = 11;
	protected static final int R_SPECIAL_COD = 9;
	protected static final int R_GA_POLIZA_LIST = 15;
	protected static final int R_ELIMINAR = 16;

	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;
	protected String LangPath = "S";
	/**
	 * JSECLI001 constructor comment.
	 */
	public JSERA0011() {
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
	 */
	protected void procActionEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		UserPos userPO = null;
		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			try {
				userPO.setCusNum(req.getParameter("CUSNUM").trim());
			} catch (Exception e) {
				userPO.setCusNum("0");
			}
			try {
				userPO.setIdentifier(req.getParameter("REF").trim());
			} catch (Exception e) {
				userPO.setIdentifier("");
			}
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			if (userPO.getIdentifier().equals("")) {
				procReqListNoneAccColl(mc, user, req, res, ses);
			} else {
				procReqCollBasic(mc, user, req, res, ses);
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
	protected void procReqCollBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERA001101Message msgGa = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "0002";

		// Send Initial data
		try {
			msgGa = (ERA001101Message) mc.getMessageRecord("ERA001101");
			msgGa.setH01USERID(user.getH01USR());
			msgGa.setH01PROGRM("ERA0011");
			msgGa.setH01TIMSYS(getTimeStamp());
			msgGa.setH01SCRCOD("01");
			msgGa.setH01OPECOD(opCode);

			try {
				msgGa.setE01ROCREF(userPO.getIdentifier());
			} catch (Exception e) {
			}
			msgGa.send();
			msgGa.destroy();
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

			if (newmessage.getFormatName().equals("ERA001101")) {
				msgGa = (ERA001101Message) newmessage;

				userPO.setPurpose("MAINTENANCE");
				userPO.setCusName(msgGa.getE01CUSNA1());
				userPO.setCusNum(msgGa.getE01ROCCUN());
				userPO.setType(msgGa.getE01ROCTYP());
				userPO.setBank(msgGa.getE01ROCBNK());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("gaMant", msgGa);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_basic.jsp");
						callPage(LangPath + "ERA0011_ga_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_enter_maint.jsp");
						callPage(
							LangPath + "ERA0011_ga_enter_maint.jsp",
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
	protected void procReqDeleteCollBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERA001101Message msgGa = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "0009";

		// Send Initial data
		try {
			msgGa = (ERA001101Message) mc.getMessageRecord("ERA001101");
			msgGa.setH01USERID(user.getH01USR());
			msgGa.setH01PROGRM("ERA0011");
			msgGa.setH01TIMSYS(getTimeStamp());
			msgGa.setH01SCRCOD("01");
			msgGa.setH01OPECOD(opCode);

			try {
				msgGa.setE01ROCREF(userPO.getIdentifier());
			} catch (Exception e) {
			}
			msgGa.send();
			msgGa.destroy();
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

			if (newmessage.getFormatName().equals("ERA001101")) {
				msgGa = (ERA001101Message) newmessage;

				userPO.setPurpose("MAINTENANCE");
				userPO.setCusName(msgGa.getE01CUSNA1());
				userPO.setCusNum(msgGa.getE01ROCCUN());
				userPO.setType(msgGa.getE01ROCTYP());
				userPO.setBank(msgGa.getE01ROCBNK());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("gaMant", msgGa);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_del_confirm.jsp");
						callPage(LangPath + "ERA0011_ga_del_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						showERROR(msgError);
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_enter_maint.jsp");
						callPage(
							LangPath + "ERA0011_ga_enter_maint.jsp",
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
	protected void procReqCollListDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERA001102Message msgColl = null;
		JBObjList trList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgColl = (ERA001102Message) mc.getMessageRecord("ERA001102");
			msgColl.setH02USERID(user.getH01USR());
			msgColl.setH02PROGRM("ERA0011");
			msgColl.setH02TIMSYS(getTimeStamp());
			msgColl.setH02SCRCOD("01");
			msgColl.setH02OPECOD("0006");
			// Get Parameters here	

			try {
				msgColl.setE02RODREF(userPO.getIdentifier());
			} catch (Exception e) {
			}
			try {
				msgColl.setE02RODBNK(userPO.getBank());
			} catch (Exception e) {
			}
			try {
				msgColl.setE02RODCUN(userPO.getCusNum());
			} catch (Exception e) {
			}

			msgColl.send();
			msgColl.destroy();

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		flexLog("Initializing java beans into the session");
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			trList = new JBObjList();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ERA001102")) {

				char sel = ' ';
				String marker = "";

				while (true) {

					msgColl = (ERA001102Message) newmessage;
					marker = msgColl.getH02FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						trList.addRow(msgColl);
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				msgColl = null;
				ses.setAttribute("error", msgError);
				ses.setAttribute("collList", trList);
				ses.setAttribute("collDet", msgColl);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ERA0011_ga_list_detail.jsp");
					callPage(LangPath + "ERA0011_ga_list_detail.jsp", req, res);
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

	protected void procReqPolizalListDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERA001103Message msgColl = null;
		JBObjList trList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgColl = (ERA001103Message) mc.getMessageRecord("ERA001103");
			msgColl.setH03USERID(user.getH01USR());
			msgColl.setH03PROGRM("ERA0011");
			msgColl.setH03TIMSYS(getTimeStamp());
			msgColl.setH03SCRCOD("01");
			msgColl.setH03OPECOD("0006");
			// Get Parameters here	

			try {
				msgColl.setE03ROCREF(userPO.getIdentifier());
			} catch (Exception e) {
			}
			try {
				msgColl.setE03ROCBNK(userPO.getBank());
			} catch (Exception e) {
			}
			try {
				msgColl.setE03ROCCUN(userPO.getCusNum());
			} catch (Exception e) {
			}

			msgColl.send();
			msgColl.destroy();

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		flexLog("Initializing java beans into the session");
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			trList = new JBObjList();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ERA001103")) {

				char sel = ' ';
				String marker = "";

				while (true) {

					msgColl = (ERA001103Message) newmessage;
					marker = msgColl.getH03FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						trList.addRow(msgColl);
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				msgColl = null;
				ses.setAttribute("error", msgError);
				ses.setAttribute("collList", trList);
				ses.setAttribute("collDet", msgColl);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ERA0011_ga_poliza_list_detail.jsp");
					callPage(
						LangPath + "ERA0011_ga_poliza_list_detail.jsp",
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
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	* This method was created in VisualAge.
	*/
	protected void procReqEnter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ERA001101Message msgGa = null;
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");
			msgGa =
				(datapro.eibs.beans.ERA001101Message) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ERA001101Message");

			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("gaMant", msgGa);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ERA0011_ga_enter_maint.jsp");
			callPage(LangPath + "ERA0011_ga_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListNoneAccColl(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERA010004Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
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
		userPO.setPurpose("MAINTENANCE");
		int type = 0;
		String num = "";
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ERA010004Message) mc.getMessageRecord("ERA010004");
			msgList.setH04USERID(user.getH01USR());
			msgList.setH04PROGRM("ERA0100");
			msgList.setH04TIMSYS(getTimeStamp());
			msgList.setH04SCRCOD("01");
			msgList.setH04OPECOD("0004");
			try {
				msgList.setE04ROCCUN(userPO.getCusNum());
			} catch (Exception e) {
			}
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
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
							+ "ERA0011_ga_enter_maint.jsp");
					callPage(LangPath + "ERA0011_ga_enter_maint.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ERA010004")) {
				try {
					beanList =
						(datapro.eibs.beans.JBList) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.JBList");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				String myRow = "";
				String ref = "";
				String client = "";
				String chk = "";
				String refNum = msgList.getE04ROCREF();
				java.math.BigDecimal totalGross = new java.math.BigDecimal(0);
				java.math.BigDecimal totalUsed = new java.math.BigDecimal(0);
				java.math.BigDecimal totalAvailable =
					new java.math.BigDecimal(0);
				if (refNum == "0")
					firstTime = true;
				else
					firstTime = false;

				while (true) {
					msgList = (ERA010004Message) newmessage;
					marker = msgList.getE04ENDFLD();
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						userPO.setCusNum(msgList.getE04ROCCUN().trim());
						userPO.setCusName(msgList.getE04CUSNA1().trim());
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							chk = "checked";
							userPO.setCusNum(msgList.getE04ROCCUN().trim());
							userPO.setCusName(msgList.getE04CUSNA1().trim());
							userPO.setBank(msgList.getE04ROCBNK());
						} else {
							if (msgList.getE04ROCREF().trim().equals(refNum)) {
								userPO.setCusNum(msgList.getE04ROCCUN().trim());
								userPO.setCusName(
									msgList.getE04CUSNA1().trim());
								userPO.setBank(msgList.getE04ROCBNK());
								chk = "checked";
							} else
								chk = "";
						}

						myFlag = "";
						totalGross =
							totalGross.add(msgList.getBigDecimalE04ROCNBL());
						totalUsed =
							totalUsed.add(msgList.getBigDecimalE04ROCCOP());
						totalAvailable =
							totalAvailable.add(
								msgList.getBigDecimalE04DISPON());

						ref = msgList.getE04ROCREF();
						client = msgList.getE04ROCCUN();
						myRow = "<TR>";
						myRow
							+= "<TD NOWRAP><input type=\"radio\" name=\"REFNUM\" value=\""
							+ ref
							+ "\" "
							+ chk
							+ "></TD>";
						myRow += "<TD NOWRAP ALIGN=LEFT>"
							+ Util.formatCell(ref)
							+ "</TD>";
						myRow += "<TD NOWRAP>"
							+ Util.formatCell(msgList.getE04ROCTYP())
							+ "</TD>";
						myRow += "<TD NOWRAP>"
							+ Util.formatDate(
								msgList.getE04ROCMT1(),
								msgList.getE04ROCMT2(),
								msgList.getE04ROCMT3())
							+ "</A></TD>";
						myRow += "<TD NOWRAP>"
							+ Util.formatCell(msgList.getE04ROCCCY())
							+ "</TD>";
						myRow += "<TD NOWRAP ALIGN=RIGHT>"
							+ Util.fcolorCCY(msgList.getE04ROCNBL())
							+ "</TD>";
						myRow += "<TD NOWRAP ALIGN=RIGHT>"
							+ Util.fcolorCCY(msgList.getE04ROCCOP())
							+ "</TD>";
						myRow += "<TD NOWRAP ALIGN=RIGHT>"
							+ Util.fcolorCCY(msgList.getE04DISPON())
							+ "</TD>";
						myRow += "</TR>";
						beanList.addRow(myFlag, myRow);

					}
					newmessage = mc.receiveMessage();
				}

				userPO.setHeader3(Util.fcolorCCY(totalGross.toString()));
				userPO.setHeader4(Util.fcolorCCY(totalUsed.toString()));
				userPO.setHeader5(Util.fcolorCCY(totalAvailable.toString()));

				flexLog("Putting java beans into the session");
				ses.setAttribute("noneCollList", beanList);
				ses.setAttribute("userPO", userPO);
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ERA0011_ga_acc_list.jsp");
					callPage(LangPath + "ERA0011_ga_acc_list.jsp", req, res);
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
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					return;
				}

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// Request
					case R_GA_BASIC :
						procReqCollBasic(mc, msgUser, req, res, session);
						break;
					case R_ELIMINAR :
						procReqDeleteCollBasic(mc, msgUser, req, res, session);
						break;
					case R_GA_DETAIL_LIST :
						procReqCollListDet(mc, msgUser, req, res, session);
						break;
					case R_GA_POLIZA_LIST :
						procReqPolizalListDet(mc, msgUser, req, res, session);
						break;
					case R_GA_NONE_ACC :
						procReqListNoneAccColl(mc, msgUser, req, res, session);
						break;
					case R_SPECIAL_COD :
						procReqCodes(mc, msgUser, req, res, session);
						break;
						//entering options
					case R_ENTER :
						procReqEnter(msgUser, req, res, session);
						break;
					case R_ENTER_NEW :
						procReqEnterNew(mc, msgUser, req, res, session);
						break;
					case R_INST_SPECIAL :
						procReqEspInst(mc, msgUser, req, res, session);
						break;
						// action options
					case A_GA_BASIC :
						procActionCollBasic(mc, msgUser, req, res, session);
						break;
					case A_GA_DETAIL_LIST :
						procActionCollListDet(mc, msgUser, req, res, session);
						break;
					case A_GA_POLIZA_LIST :
						procActionPolizaListDet(mc, msgUser, req, res, session);
						break;
					case A_GA_DETAIL :
						procActionCollDet(mc, msgUser, req, res, session);
						break;
					case A_GA_POLIZA_DETAIL :
						procActionPolizaDet(mc, msgUser, req, res, session);
						break;
					case A_ENTER_NEW :
						procActionEnterNew(mc, msgUser, req, res, session);
						break;
					case A_NEW :
						procActionNew(mc, msgUser, req, res, session);
						break;
					case A_ENTER :
					case A_GA_NONE_ACC :
						procActionEnter(mc, msgUser, req, res, session);
						break;
					case A_DELETE :
						procActionDelete(mc, msgUser, req, res, session);
						break;
					case A_TR_COLL :
						//procActionAddDetail(mc,msgUser, req, res, session);
						break;
					case A_SPECIAL_COD :
						procActionCodes(mc, msgUser, req, res, session);
						break;
					case A_INST_SPECIAL :
						procActionEspInst(mc, msgUser, req, res, session);
						break;

					default :
						res.sendRedirect(
							super.srctx + LangPath + super.devPage);
						break;
				}
				try {
					s.close();
				} catch (Exception e) {
					flexLog("Error: " + e);
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
	protected void procActionCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ESD000002Message msgGA = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgGA = (ESD000002Message) ses.getAttribute("gaCodes");
			//msgGA = (ESD000002Message)mc.getMessageRecord("ESD000002");
			msgGA.setH02USR(user.getH01USR());
			msgGA.setH02PGM("ERA0011");
			msgGA.setH02TIM(getTimeStamp());
			msgGA.setH02SCR("01");
			msgGA.setH02OPE("0003");
			msgGA.setE02ACC(userPO.getIdentifier());
			// all the fields here
			java.util.Enumeration enu = msgGA.fieldEnumeration();
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
			//msgGA.send();
			mc.sendMessage(msgGA);
			msgGA.destroy();
			flexLog("ESD00002 Message Sent");
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
				msgGA = (ESD000002Message) newmessage;
				// showESD008004(msgGA);
				userPO.setIdentifier(msgGA.getE02ACC());
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("gaCodes", msgGA);
				ses.setAttribute("userPO", userPO);
				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_basic.jsp");
						callPage(LangPath + "ERA0011_ga_basic.jsp", req, res);

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						showERROR(msgError);
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_codes.jsp");
						callPage(LangPath + "ERA0011_ga_codes.jsp", req, res);
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
	protected void procActionCollBasic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERA001101Message msgGa = null;
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
		String opCode = "0003";

		// Send Initial data
		try {
			msgGa = (ERA001101Message) mc.getMessageRecord("ERA001101");
			msgGa.setH01USERID(user.getH01USR());
			msgGa.setH01PROGRM("ERA0011");
			msgGa.setH01TIMSYS(getTimeStamp());
			msgGa.setH01SCRCOD("01");
			msgGa.setH01OPECOD(opCode);

			try {
				msgGa.setE01ROCREF(userPO.getIdentifier());
			} catch (Exception e) {
			}

			// all the fields here
			java.util.Enumeration enu = msgGa.fieldEnumeration();
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
			msgGa.send();
			msgGa.destroy();
			flexLog("ERA001101 Message Sent");
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

			if (newmessage.getFormatName().equals("ERA001101")) {
				try {
					msgGa =
						(
							datapro
								.eibs
								.beans
								.ERA001101Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ERA001101Message");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgGa = (ERA001101Message) newmessage;
				userPO.setCusName(msgGa.getE01CUSNA1());
				userPO.setCusNum(msgGa.getE01ROCCUN());
				userPO.setCurrency(msgGa.getE01ROCCCY());
				userPO.setType(msgGa.getE01ROCTYP());
				userPO.setBank(msgGa.getE01ROCBNK());
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.setAttribute("gaMant", msgGa);
				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_enter_maint.jsp");
						callPage(
							LangPath + "ERA0011_ga_enter_maint.jsp",
							req,
							res);
						//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.client.JSERA0011?SCREEN=7");
						//res.setContentType("text/html");
						//PrintWriter  out = res.getWriter();
						//out.println("<HTML>");
						//out.println("<HEAD>");
						//out.println("<TITLE>Close</TITLE>");
						//out.println("</HEAD>");
						//out.println("<BODY>");
						//out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
						//out.println(" var myOpener= top.opener;");
						//out.println("		myOpener.parent.location.href='" + super.webAppPath + LangPath + "ERA0011_ga_acc_list.jsp';");
						//out.println("		top.close();");
						//out.println("</SCRIPT>");
						//out.println("<P>Close it!!!</P>");
						//out.println("</BODY>");
						//out.println("</HTML>");
						//out.close();
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { //There are errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_basic.jsp");
						callPage(LangPath + "ERA0011_ga_basic.jsp", req, res);
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

	protected void procActionCollDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERA001102Message msgColl = null;
		ELEERRMessage msgError = null;
		JBObjList collList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		//int option;
		int row = 0;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		// Send Initial data
		try {
			collList =
				(datapro.eibs.beans.JBObjList) ses.getAttribute("collList");
			//option = Integer.parseInt(req.getParameter("opt"));
			flexLog("Send Initial Data");
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception ex) {
			}
			if (row >= 0 && !((List)collList.getList()).isEmpty()) {
				collList.setCurrentRow(row);
				msgColl = (ERA001102Message) collList.getRecord();
			} else {
				msgColl = (ERA001102Message) mc.getMessageRecord("ERA001102");
			}
			msgColl.setH02USERID(user.getH01USR());
			msgColl.setH02PROGRM("ERA0011");
			msgColl.setH02TIMSYS(getTimeStamp());
			msgColl.setH02SCRCOD("01");
			msgColl.setH02OPECOD("0002");
			
			collList =
				(datapro.eibs.beans.JBObjList) ses.getAttribute("collList");
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception ex) {
			}
			
			//} 		// all the fields here
			java.util.Enumeration enu = msgColl.fieldEnumeration();
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
			if (row >= 0 ) {
				mc.sendMessage(msgColl);
			} else {
				msgColl.send();
			}
			msgColl.destroy();
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
			if (newmessage.getFormatName().equals("ERA001102")) {
				msgColl = (ERA001102Message) newmessage;
				// showESD008004(msgColl);
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
							+ "/servlet/datapro.eibs.client.JSERA0011?SCREEN=3'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("collDet", msgColl);
					ses.setAttribute("userPO", userPO);
					if (collList.getNoResult()) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ERA0011_ga_detail.jsp");
							callPage(
								LangPath + "ERA0011_ga_detail.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						collList.setCurrentRow(row);
						collList.setRecord(msgColl, row);
						ses.setAttribute("collList", collList);
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ERA0011_ga_detail.jsp?ROW="
									+ row);
							res.sendRedirect(
								super.srctx
									+ LangPath
									+ "ERA0011_ga_detail.jsp?ROW="
									+ row);
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

	protected void procActionPolizaDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERA001103Message msgColl = null;
		ELEERRMessage msgError = null;
		JBObjList collList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		//int option;
		int row = 0;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		// Send Initial data
		try {
			collList =
				(datapro.eibs.beans.JBObjList) ses.getAttribute("collList");
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception ex) {
			}
			if (row >= 0) {
				collList.setCurrentRow(row);
				msgColl = (ERA001103Message) collList.getRecord();
			} else {
				msgColl = (ERA001103Message) mc.getMessageRecord("ERA001103");
			}
			//option = Integer.parseInt(req.getParameter("opt"));
			flexLog("Send Initial Data");
			msgColl.setH03USERID(user.getH01USR());
			msgColl.setH03PROGRM("ERA0011");
			msgColl.setH03TIMSYS(getTimeStamp());
			msgColl.setH03SCRCOD("01");
			msgColl.setH03OPECOD("0002");
			msgColl.setH03FLGWK3(userPO.getHeader22());
			
			// all the fields here
			java.util.Enumeration enu = msgColl.fieldEnumeration();
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
			if (row >= 0 ) {
				mc.sendMessage(msgColl);
			} else {
				msgColl.send();
			}
			msgColl.destroy();
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
			if (newmessage.getFormatName().equals("ERA001103")) {
				msgColl = (ERA001103Message) newmessage;
				// showESD008004(msgColl);
				if (IsNotError) { // There are no errors Get Poliza List
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("		top.opener.window.location.href='"
							+ super.webAppPath
							+ "/servlet/datapro.eibs.client.JSERA0011?SCREEN=15'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors Get Data
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("gaPoliza", msgColl);
					ses.setAttribute("userPO", userPO);
					if (collList.getNoResult()) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ERA0011_ga_poliza_detail.jsp");
							callPage(
								LangPath + "ERA0011_ga_poliza_detail.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						if (row >=0) {
							collList.setCurrentRow(row);
							collList.setRecord(msgColl, row);
						}
						ses.setAttribute("collList", collList);
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ERA0011_ga_poliza_detail.jsp?ROW="
									+ row);
							res.sendRedirect(
								super.srctx
									+ LangPath
									+ "ERA0011_ga_poliza_detail.jsp?ROW="
									+ row);
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
	protected void procActionCollListDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ERA001102Message msgColl = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList collList = null;
		collList = (JBObjList) ses.getAttribute("collList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
				row = 0;
			}

			switch (option) {

				case 3 : // Delete
					boolean IsNotError = true;
					collList.setCurrentRow(row);
					try {
						msgColl = (ERA001102Message) collList.getRecord();
						msgColl.setH02USERID(user.getH01USR());
						msgColl.setH02PROGRM("ERA0011");
						msgColl.setH02TIMSYS(getTimeStamp());
						msgColl.setH02SCRCOD("01");
						msgColl.setH02OPECOD("0009");

						try {
							msgColl.setE02RODREF(userPO.getIdentifier());
						} catch (Exception e) {
						}
						try {
							msgColl.setE02RODBNK(userPO.getBank());
						} catch (Exception e) {
						}

						mc.sendMessage(msgColl);
						//msgColl.destroy();
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
								procReqCollListDet(mc, user, req, res, ses);
							} else {
								try {
									flexLog("Putting java beans into the session");
									ses.setAttribute("error", msgError);
									res.sendRedirect(
										super.srctx
											+ LangPath
											+ "ERA0011_ga_list_detail.jsp?ROW="
											+ row);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}
						} else
							flexLog(
								"Message "
									+ newmessage.getFormatName()
									+ " received.");
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
					break;

				case 1 : // New
					int seq = 0;
					if (collList.getNoResult()) {
						seq = 1;
					} else {
						collList.getLastRec();
						//seq = Integer.parseInt(collList.getRecord(0)) + 1;
					}
					msgColl =
						(ERA001102Message) mc.getMessageRecord("ERA001102");
					userPO.setHeader19(seq + "");
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(
						super.webAppPath + LangPath + "ERA0011_ga_detail.jsp");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("collDet", msgColl);
					res.sendRedirect(
						super.srctx
							+ LangPath
							+ "ERA0011_ga_list_detail.jsp?ROW="
							+ row);
					break;

				case 2 : // Maintenance
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(
						super.webAppPath
							+ LangPath
							+ "ERA0011_ga_detail.jsp?ROW="
							+ row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(
						super.srctx
							+ LangPath
							+ "ERA0011_ga_list_detail.jsp?ROW="
							+ row);
					break;
					
				case 4 : // Inquiry
					collList.setCurrentRow(row);
					msgColl = (ERA001102Message) collList.getRecord();
					userPO.setHeader20("DO_INQURY");
					userPO.setHeader21(
						super.webAppPath + LangPath + "ERA0011_ga_detail_inquiry.jsp");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("collDet", msgColl);
					res.sendRedirect(
						super.srctx
							+ LangPath
							+ "ERA0011_ga_detail_inquiry.jsp?ROW="
							+ row);
					break;
					
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procActionPolizaListDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ERA001103Message msgColl = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList collList = null;
		collList = (JBObjList) ses.getAttribute("collList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("actRow"));
			} catch (Exception e) {
				row = 0;
			}

			switch (option) {

				case 3 : // Delete
					boolean IsNotError = true;
					collList.setCurrentRow(row);
					try {
						msgColl = (ERA001103Message) collList.getRecord();
						msgColl.setH03USERID(user.getH01USR());
						msgColl.setH03PROGRM("ERA0011");
						msgColl.setH03TIMSYS(getTimeStamp());
						msgColl.setH03SCRCOD("01");
						msgColl.setH03OPECOD("0009");

						try {
							msgColl.setE03ROCREF(userPO.getIdentifier());
						} catch (Exception e) {
						}
						try {
							msgColl.setE03ROCBNK(userPO.getBank());
						} catch (Exception e) {
						}

						mc.sendMessage(msgColl);
						//msgColl.destroy();
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
								procReqPolizalListDet(mc, user, req, res, ses);
							} else {
								try {
									flexLog("Putting java beans into the session");
									ses.setAttribute("error", msgError);
									res.sendRedirect(
										super.srctx
											+ LangPath
											+ "ERA0011_ga_poliza_list_detail.jsp?ROW="
											+ row);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}
						} else
							flexLog(
								"Message "
									+ newmessage.getFormatName()
									+ " received.");
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
					break;

				case 1 : // New
					int seq = 0;
					if (collList.getNoResult()) {
						seq = 1;
					} else {
						collList.getLastRec();
						//seq = Integer.parseInt(collList.getRecord(0)) + 1;
					}
					msgColl =
						(ERA001103Message) mc.getMessageRecord("ERA001103");
					userPO.setHeader19(seq + "");
				    userPO.setHeader22("N");
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(
						super.webAppPath
							+ LangPath
							+ "ERA0011_ga_poliza_detail.jsp");
					ses.setAttribute("userPO", userPO);
					//ses.setAttribute("collDet", msgColl);
					ses.setAttribute("gaPoliza", msgColl);
					res.sendRedirect(
						super.srctx
							+ LangPath
							+ "ERA0011_ga_poliza_list_detail.jsp?ROW="
							+ row);
					break;

				case 2 : // Maintenance
					userPO.setHeader20("DO_MAINT");
				    userPO.setHeader22("");
					userPO.setHeader21(
						super.webAppPath
							+ LangPath
							+ "ERA0011_ga_poliza_detail.jsp?ROW="
							+ row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(
						super.srctx
							+ LangPath
							+ "ERA0011_ga_poliza_list_detail.jsp?ROW="
							+ row);
					break;
					
				case 4 : // Inquiry
					collList.setCurrentRow(row);
					msgColl = (ERA001103Message) collList.getRecord();
					userPO.setHeader20("DO_INQURY");
					userPO.setHeader21(
						super.webAppPath + LangPath + "ERA0011_ga_poliza_detail_inquiry.jsp");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("gaPoliza", msgColl);
					res.sendRedirect(
						super.srctx
							+ LangPath
							+ "ERA0011_ga_poliza_detail_inquiry.jsp?ROW="
							+ row);
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
	 */
	protected void procActionDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERA001101Message msgGa = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		String opCode = "0009";

		// Send Initial data
		try {
			msgGa = (ERA001101Message) mc.getMessageRecord("ERA001101");
			msgGa.setH01USERID(user.getH01USR());
			msgGa.setH01PROGRM("ERA0011");
			msgGa.setH01TIMSYS(getTimeStamp());
			msgGa.setH01SCRCOD("01");
			msgGa.setH01OPECOD(opCode);

			try {
				msgGa.setE01ROCREF(req.getParameter("REFNUM"));
			} catch (Exception e) {
			}

			msgGa.send();
			msgGa.destroy();
			flexLog("ERA001101 Message Sent");
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
		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);

		if (IsNotError) { // There are no errors
			res.sendRedirect(
				super.srctx
					+ "/servlet/datapro.eibs.client.JSERA0011?SCREEN=7");

		} else { //There are errors

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ERA0011_ga_acc_list.jsp");
				callPage(LangPath + "ERA0011_ga_acc_list.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
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
		ERA001101Message msgGa = null;
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
		String opCode = "0005";

		// Send Initial data
		try {
			msgGa = (ERA001101Message) mc.getMessageRecord("ERA001101");
			msgGa.setH01USERID(user.getH01USR());
			msgGa.setH01PROGRM("ERA0011");
			msgGa.setH01TIMSYS(getTimeStamp());
			msgGa.setH01SCRCOD("01");
			msgGa.setH01OPECOD(opCode);

			//try {
			//msgGa.setE01ROCREF(userPO.getIdentifier());
			//}
			//catch (Exception e) {
			//}

			// all the fields here
			java.util.Enumeration enu = msgGa.fieldEnumeration();
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
			msgGa.send();
			msgGa.destroy();
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

			if (newmessage.getFormatName().equals("ERA001101")) {
				try {
					msgGa =
						(
							datapro
								.eibs
								.beans
								.ERA001101Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ERA001101Message");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgGa = (ERA001101Message) newmessage;
				userPO.setCusName(msgGa.getE01CUSNA1());
				userPO.setCusNum(msgGa.getE01ROCCUN());
				userPO.setCurrency(msgGa.getE01ROCCCY());
				userPO.setType(msgGa.getE01ROCTYP());
				userPO.setIdentifier(msgGa.getE01ROCREF());
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.setAttribute("gaMant", msgGa);
				if (IsNotError) { // There are no errors
					userPO.setPurpose("MAINTENANCE");
					ses.setAttribute("userPO", userPO);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_confirm.jsp");
						callPage(LangPath + "ERA0011_ga_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_basic.jsp");
						callPage(LangPath + "ERA0011_ga_basic.jsp", req, res);
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
	protected void procActionEnterNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ERA001101Message msgGa = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBListRec beanList = null;
		boolean IsNotError = false;
		int pos = 0;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");
			msgGa =
				(datapro.eibs.beans.ERA001101Message) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ERA001101Message");
			beanList =
				(datapro.eibs.beans.JBListRec) ses.getAttribute("gaList");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {

			String opCode = "0001";
			String ref = "0";

			msgGa = (ERA001101Message) mc.getMessageRecord("ERA001101");
			msgGa.setH01USERID(user.getH01USR());
			msgGa.setH01PROGRM("ERA0011");
			msgGa.setH01TIMSYS(getTimeStamp());
			msgGa.setH01SCRCOD("01");
			msgGa.setH01OPECOD(opCode);
			try {
				ref = req.getParameter("accnum");
				if (ref == null || ref.trim().equals(""))
					ref = "0";
				msgGa.setE01ROCREF(ref);
			} catch (Exception e) {
			}
			try {
				pos = Integer.parseInt(req.getParameter("ROW"));
				beanList.setCurrentRow(pos);
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCGLN(beanList.getRecord(3));
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCCCY(beanList.getRecord(6));
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCBRN(beanList.getRecord(8));
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCBNK(beanList.getRecord(7));
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCPRD(beanList.getRecord(9));
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCTYP(beanList.getRecord(0));
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCDPR(beanList.getRecord(5));
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCCGT(beanList.getRecord(1));
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCDGT(beanList.getRecord(2));
			} catch (Exception e) {
			}
			try {
				msgGa.setE01ROCUC9(req.getParameter("ICOD"));
			} catch (Exception e) {
			}

			try {
				msgGa.setE01ROCDCC(beanList.getRecord(1));
			} catch (Exception e) {
			}

			msgGa.send();
			msgGa.destroy();
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

			if (newmessage.getFormatName().equals("ERA001101")) {
				try {
					msgGa =
						(
							datapro
								.eibs
								.beans
								.ERA001101Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ERA001101Message");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgGa = (ERA001101Message) newmessage;

				flexLog("Putting java beans into the session");
				userPO.setPurpose("NEW");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.setAttribute("gaMant", msgGa);

				if (IsNotError) { // There are no errors				
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_basic.jsp");
						callPage(LangPath + "ERA0011_ga_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_enter_new.jsp?ROW="
								+ pos);
						res.sendRedirect(
							super.srctx
								+ LangPath
								+ "ERA0011_ga_enter_new.jsp?ROW="
								+ pos);
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
	protected void procActionEspInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ESD000005Message msgGA = null;
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
			msgGA = (ESD000005Message) ses.getAttribute("gaInst");
			//msgGA = (ESD000005Message)mc.getMessageRecord("ESD000005");
			msgGA.setH05USR(user.getH01USR());
			msgGA.setH05PGM("EDL0130");
			msgGA.setH05TIM(getTimeStamp());
			msgGA.setH05SCR("01");
			msgGA.setH05OPE("0003");
			msgGA.setE05ACC(userPO.getIdentifier());
			msgGA.setE05TYP("C");
			msgGA.setH05WK1("M");

			// all the fields here
			java.util.Enumeration enu = msgGA.fieldEnumeration();
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
			//msgGA.send();
			mc.sendMessage(msgGA);
			msgGA.destroy();
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
					msgGA =
						(
							datapro
								.eibs
								.beans
								.ESD000005Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ESD000005Message");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgGA = (ESD000005Message) newmessage;
				// showESD008004(msgGA);
				userPO.setIdentifier(msgGA.getE05ACC());
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("gaInst", msgGA);
				ses.setAttribute("userPO", userPO);
				if (IsNotError) { // There are no errors
					try {
						{
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ERA0011_ga_basic.jsp");
							callPage(
								LangPath + "ERA0011_ga_basic.jsp",
								req,
								res);
						}
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_special_inst.jsp");
						callPage(
							LangPath + "ERA0011_ga_special_inst.jsp",
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
	protected void procReqCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ESD000002Message msgGa = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "";
		if (userPO.getPurpose().equals("MAINTENANCE"))
			opCode = "0002";
		else
			opCode = "0004";

		// Send Initial data
		try {
			msgGa = (ESD000002Message) mc.getMessageRecord("ESD000002");
			msgGa.setH02USR(user.getH01USR());
			msgGa.setH02PGM("ERA0011");
			msgGa.setH02TIM(getTimeStamp());
			msgGa.setH02SCR("01");
			msgGa.setH02OPE(opCode);
			msgGa.setE02ACC(userPO.getIdentifier());
			msgGa.setH02WK1("M");
			msgGa.send();
			msgGa.destroy();
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
				msgGa = (ESD000002Message) newmessage;
				// showESD008004(msgGa);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("gaCodes", msgGa);
				if (IsNotError) { // There are no errors
					try {

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_codes.jsp");
						callPage(LangPath + "ERA0011_ga_codes.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						showERROR(msgError);
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_codes.jsp");
						callPage(LangPath + "ERA0011_ga_codes.jsp", req, res);
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
	protected void procReqEnterNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ERA001199Message msgGa = null;
		JBListRec beanList = null;
		JBListRec gcodeList = null;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			msgGa = (ERA001199Message) mc.getMessageRecord("ERA001199");
			msgGa.setH99USERID(user.getH01USR());
			msgGa.setH99PROGRM("ERA0011P");
			msgGa.setH99TIMSYS(getTimeStamp());
			msgGa.setH99SCRCOD("01");
			msgGa.setH99OPECOD("0004");
			flexLog("msgGa =" + "*" + msgGa);
			msgGa.send();
			msgGa.destroy();
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

				try {
					flexLog(
						"About to call Page4: "
							+ LangPath
							+ "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ERA001199")) {

				int colNum = 10;
				int gaColNum = 4;
				try {
					beanList =
						(datapro.eibs.beans.JBListRec) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.JBListRec");
					beanList.init(colNum);
					gcodeList =
						(datapro.eibs.beans.JBListRec) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.JBListRec");
					gcodeList.init(gaColNum);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				String myFlag1 = "";
				String myGaCode[] = new String[gaColNum];
				for (int i = 0; i < gaColNum; i++) {
					myGaCode[i] = "";
				}
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				while (true) {

					msgGa = (ERA001199Message) newmessage;

					marker = msgGa.getH99FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						if (!myFlag.equals(msgGa.getE01CNOSCG())) {
							if (msgGa.getE01CNORCD().equals("")
								|| msgGa.getE01CNOF02().equals("")) {
								newmessage = mc.receiveMessage();
								continue;
							}
							myGaCode[0] = msgGa.getE01CNOATY(); // Collateral type
							myGaCode[1] = msgGa.getE01CNODTY(); // Description
							myGaCode[2] = msgGa.getE01CNOSCG(); // Account
							myGaCode[3] = msgGa.getE01CNOF02(); // Currency

							//myFlag1 = msgGa.getE01CNOSCG();
							gcodeList.addRow(myFlag1, myGaCode);
						}
						if (!msgGa.getE01CNORCD().equals("")) {
							try {
								myRow[0] = msgGa.getE01CNORCD(); // Collateral Code					
								myRow[1] = msgGa.getE01CNODCC(); // X Code					
								myRow[2] = msgGa.getE01CNODSC(); // X Description
								myRow[3] = msgGa.getE01CNOSCG(); // Account					
								myRow[4] = msgGa.getE01CNOCST(); // Percent
								myRow[5] = msgGa.getE01CNODTY(); //  
								myRow[6] = msgGa.getE01CNOF02(); // Currency
								myRow[7] = msgGa.getE01CNOBNK(); // Bank
								myRow[8] = msgGa.getE01CNOBRN(); // Branch
								myRow[9] = msgGa.getE01CNOATY(); // Product

							} catch (Exception e) {
							}
							myFlag = msgGa.getE01CNOSCG();
							beanList.addRow(myFlag, myRow);
						}
					}
					newmessage = mc.receiveMessage();

				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("gaList", beanList);
				ses.setAttribute("gaCodeList", gcodeList);
				ses.setAttribute("error", msgError);
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ERA0011_ga_enter_new.jsp");
					callPage(LangPath + "ERA0011_ga_enter_new.jsp", req, res);
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
	protected void procReqEspInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ESD000005Message msgGA = null;
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
		String opCode = "0002";
		// Send Initial data
		try {
			msgGA = (ESD000005Message) mc.getMessageRecord("ESD000005");
			msgGA.setH05USR(user.getH01USR());
			msgGA.setH05PGM("ERA0011");
			msgGA.setH05TIM(getTimeStamp());
			msgGA.setH05SCR("01");
			msgGA.setH05OPE(opCode);
			msgGA.setE05ACC(userPO.getIdentifier());
			msgGA.setE05TYP("C");
			msgGA.setH05WK1("M");
			msgGA.send();
			msgGA.destroy();
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
					msgGA =
						(
							datapro
								.eibs
								.beans
								.ESD000005Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ESD000005Message");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgGA = (ESD000005Message) newmessage;
				// showESD008004(msgGA);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("gaInst", msgGA);
				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERA0011_ga_special_inst.jsp");
						callPage(
							LangPath + "ERA0011_ga_special_inst.jsp",
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
								+ "ERA0011_ga_basic.jsp");
						callPage(LangPath + "ERA0011_ga_basic.jsp", req, res);
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