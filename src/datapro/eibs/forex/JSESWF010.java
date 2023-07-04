package datapro.eibs.forex;

/**
 * Insert the type's description here.
 * Creation date: (5/16/00 6:53:55 PM)
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

public class JSESWF010 extends datapro.eibs.master.SuperServlet {

	protected static final int R_PASSWORD = 1;
	protected static final int R_APPROVAL = 5;
	protected static final int A_APPROVAL = 2;
	protected static final int R_T_APPROVAL = 6;
	protected static final int R_COPY = 10;
	
	protected static final int R_INQ = 3;
	protected static final int R_INQ_A = 9;
	protected static final int R_MAINT = 7;
	protected static final int A_MAINT = 8;
	protected static final int A_VALIDATE = 26;
	protected static final int A_LOGS = 18;
	
	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSESWF010() {
		super();
	}
	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSESWF010(int logType) {
		super(logType);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ESWF01003Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ESWF01003Message) mc.getMessageRecord("ESWF01003");
			msgList.setH03PGM("ESWF010");
			msgList.setH03TIM(getTimeStamp());
			msgList.setH03USR(user.getH01USR());
			msgList.setESW3USR(req.getParameter("USERID"));
			msgList.setESW3REF(req.getParameter("REFNUM"));
			msgList.setESW3ACTION(req.getParameter("ACTION"));
			msgList.send();
			msgList.destroy();
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
					msgError = new ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There is no error
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSESWF010?SCREEN=6");
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESWF010A_swift_free_format_approval_list.jsp");
						callPage(LangPath + "ESWF010A_swift_free_format_approval_list.jsp", req, res);
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ESWF01001Message msgList = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ESWF01001Message) mc.getMessageRecord("ESWF01001");
			msgList.setH01USR(user.getH01USR());
			msgList.setH01PGM("ESWF010");
			msgList.setH01TIM(getTimeStamp());
			msgList.setH01OPE("0001");

			try {
				try {
					posi = Integer.parseInt(req.getParameter("Pos"));
				} catch (Exception e) {
					posi = 0;
					flexLog("E01NUMPOS");
				}

				try {
					msgList.setH01SCR(req.getParameter("Pos"));
				} catch (Exception e) {
					msgList.setH01SCR("0");
					flexLog("H01SCR");
				}

				//try{
				//	msgList.setE02RCLBACC(req.getParameter("NUMACC"));
				//}
				//catch (Exception e){
				// 	flexLog("E02RCLBACC");
				//}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgList.send();
			msgList.destroy();
			flexLog("ESWF01001 Message Sent");
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
					msgError = new ELEERRMessage();
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
							+ "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else

				//newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ESWF01001")) {

					try {
						beanList = new JBList();
					} catch (Exception ex) {
						flexLog("ESWF01001 Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";

					while (true) {

						msgList = (ESWF01001Message) newmessage;

						marker = msgList.getH01MAS();

						if (msgList.getH01MAS().equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(0);
								//userPO.setHeader2(msgList.getE02RCLBCUN());
								//userPO.setHeader3(msgList. getE02CUSNA1A());					
								chk = "checked";
							} else
								chk = "";
								
							myRow = new StringBuffer();
							myRow.append("<TR>");
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"REFNUM\" value=\""
								+ msgList.getESW1REF()
								+ "\" "
								+ chk
								+ " onclick=\"setValue('"
								+ msgList.getESW1USR()
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP ALIGN=CENTER>"
								+ Util.formatCell(msgList.getESW1FOR())
								+ "</TD>");
							myRow.append("<TD NOWRAP ALIGN=CENTER>"
								+ Util.formatCell(msgList.getESW1USR())
								+ "</TD>");
							myRow.append("<TD NOWRAP ALIGN=CENTER>"
								+ Util.formatCell(msgList.getESW1REF())
								+ "</TD>");
							myRow.append("<TD NOWRAP ALIGN=CENTER>"
								+ Util.formatDate(msgList.getESW1DT1(),msgList.getESW1DT2(),msgList.getESW1DT3())
								+ "</TD>");	
							myRow.append("<TD NOWRAP ALIGN=CENTER>"
								+ Util.formatCell(msgList.getESW1SWI())
								+ "</TD>");
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
					ses.setAttribute("SwiftFFList", beanList);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESWF010_swift_free_format_approval_list.jsp");
						callPage(LangPath + "ESWF010_swift_free_format_approval_list.jsp", req, res);
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
	 * 
	 */
	protected void procReqInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF01002Message msgSWFF = null;
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
			msgSWFF = (ESWF01002Message) mc.getMessageRecord("ESWF01002");
			msgSWFF.setH02PGM("ESWF010");
			msgSWFF.setH02TIM(getTimeStamp());
			msgSWFF.setH02SCR("01");
			msgSWFF.setH02OPE(opCode);
			msgSWFF.setH02USR(user.getH01USR());
			msgSWFF.setESW2USR(req.getParameter("USERID"));
			msgSWFF.setESW2REF(req.getParameter("REFNUM"));
			msgSWFF.send();
			msgSWFF.destroy();
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
				try {
					flexLog(
						"About to call Page3: "
							+ LangPath
							+ "ESWF010_swift_free_format_approval_list.jsp");
					callPage(LangPath + "ESWF010_swift_free_format_approval_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESWF01002")) {
				try {
					msgSWFF = new ESWF01002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgSWFF = (ESWF01002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);

				try {
					flexLog(
						"About to call Page3: " + LangPath + "ESWF000_swift_free_format_inq.jsp");
					callPage(LangPath + "ESWF000_swift_free_format_inq.jsp", req, res);
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
	 * 
	 */
	protected void procReqInqApp(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF01002Message msgSWFF = null;
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
			msgSWFF = (ESWF01002Message) mc.getMessageRecord("ESWF01002");
			msgSWFF.setH02PGM("ESWF010");
			msgSWFF.setH02TIM(getTimeStamp());
			msgSWFF.setH02SCR("01");
			msgSWFF.setH02OPE(opCode);
			msgSWFF.setH02USR(user.getH01USR());
			msgSWFF.setESW2USR(req.getParameter("USERID"));
			msgSWFF.setESW2REF(req.getParameter("REFNUM"));
			msgSWFF.send();
			msgSWFF.destroy();
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
				try {
					flexLog(
						"About to call Page3: "
							+ LangPath
							+ "ESWF010_swift_free_format_approval_list.jsp");
					callPage(LangPath + "ESWF010_swift_free_format_approval_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESWF01002")) {
				try {
					msgSWFF = new ESWF01002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgSWFF = (ESWF01002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);

				try {
					flexLog(
						"About to call Page3: " + LangPath + "ESWF000_swift_free_format_inq_app.jsp");
					callPage(LangPath + "ESWF000_swift_free_format_inq_app.jsp", req, res);
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
	 * 
	 */
	protected void procReqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF01002Message msgSWFF = null;
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
			msgSWFF = (ESWF01002Message) mc.getMessageRecord("ESWF01002");
			msgSWFF.setH02PGM("ESWF010");
			msgSWFF.setH02TIM(getTimeStamp());
			msgSWFF.setH02SCR("01");
			msgSWFF.setH02OPE(opCode);
			msgSWFF.setH02USR(user.getH01USR());
			msgSWFF.setESW2USR(req.getParameter("USERID"));
			msgSWFF.setESW2REF(req.getParameter("REFNUM"));
			msgSWFF.send();
			msgSWFF.destroy();
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
				try {
					flexLog(
						"About to call Page3: "
							+ LangPath
							+ "ESWF010_swift_free_format_approval_list.jsp");
					callPage(LangPath + "ESWF010_swift_free_format_approval_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("ESWF01002")) {
				try {
					msgSWFF = new ESWF01002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgSWFF = (ESWF01002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);

				try {
					flexLog(
						"About to call Page3: " + LangPath + "ESWF000_swift_free_format_maint.jsp");
					callPage(LangPath + "ESWF000_swift_free_format_maint.jsp", req, res);
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

	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF01002Message msgSWFF = null;
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
			// msgSWFF = (ESWF01002Message) ses.getAttribute("swff");
			msgSWFF = (ESWF01002Message) mc.getMessageRecord("ESWF01002");
			msgSWFF.setH02USR(user.getH01USR());
			msgSWFF.setH02PGM("ESWF010");
			msgSWFF.setH02TIM(getTimeStamp());
			msgSWFF.setH02OPE("0003");

			// all the fields here
			java.util.Enumeration enu = msgSWFF.fieldEnumeration();
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
			mc.sendMessage(msgSWFF);
			msgSWFF.destroy();
			flexLog("ESWF01002 Message Sent");

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

		//Receive Data 
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF01002")) {
				try {
					msgSWFF = new ESWF01002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgSWFF = (ESWF01002Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESWF010_swift_free_format_approval_list.jsp");
						callPage(LangPath + "ESWF010_swift_free_format_approval_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: " + LangPath + "ESWF000_swift_free_format_maint.jsp");
						callPage(LangPath + "ESWF000_swift_free_format_maint.jsp", req, res);
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

	protected void procActionValidate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF01002Message msgSWFF = null;
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
			// msgSWFF = (ESWF01002Message) ses.getAttribute("swff");
			msgSWFF = (ESWF01002Message) mc.getMessageRecord("ESWF01002");
			msgSWFF.setH02USR(user.getH01USR());
			msgSWFF.setH02PGM("ESWF010");
			msgSWFF.setH02TIM(getTimeStamp());
			msgSWFF.setH02OPE("0006");

			// all the fields here
			java.util.Enumeration enu = msgSWFF.fieldEnumeration();
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
			mc.sendMessage(msgSWFF);
			msgSWFF.destroy();
			flexLog("ESWF01002 Message Sent");

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

		//Receive Data 
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF01002")) {
				try {
					msgSWFF = new ESWF01002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgSWFF = (ESWF01002Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESWF000_swift_free_format_maint.jsp");
						callPage(LangPath + "ESWF000_swift_free_format_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: " + LangPath + "ESWF000_swift_free_format_maint.jsp");
						callPage(LangPath + "ESWF000_swift_free_format_maint.jsp", req, res);
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
	 * service method comment.
	 */
	protected void procReqEnterCopy(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");


		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "ESWF200_enter_search.jsp");
			callPage(LangPath + "ESWF200_enter_search.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}	
		protected void procReqApprovalList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ESWF01001Message msgList = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ESWF01001Message) mc.getMessageRecord("ESWF01001");
			msgList.setH01USR(user.getH01USR());
			msgList.setH01PGM("ESWF010");
			msgList.setH01TIM(getTimeStamp());
			msgList.setH01OPE("0001");

			try {
				try {
					posi = Integer.parseInt(req.getParameter("Pos"));
				} catch (Exception e) {
					posi = 0;
					flexLog("E01NUMPOS");
				}

				try {
					msgList.setH01SCR(req.getParameter("Pos"));
				} catch (Exception e) {
					msgList.setH01SCR("0");
					flexLog("H01SCR");
				}

				//try{
				//	msgList.setE02RCLBACC(req.getParameter("NUMACC"));
				//}
				//catch (Exception e){
				// 	flexLog("E02RCLBACC");
				//}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgList.send();
			msgList.destroy();
			flexLog("ESWF01001 Message Sent");
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
					msgError = new ELEERRMessage();
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
							+ "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else

				//newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ESWF01001")) {

					try {
						beanList = new JBList();
					} catch (Exception ex) {
						flexLog("ESWF01001 Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";

					while (true) {

						msgList = (ESWF01001Message) newmessage;

						marker = msgList.getH01MAS();

						if (msgList.getH01MAS().equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(0);
								//userPO.setHeader2(msgList.getE02RCLBCUN());
								//userPO.setHeader3(msgList. getE02CUSNA1A());					
								chk = "";
							} else
								chk = "";

							myRow = new StringBuffer();
							myRow.append("<TR>");
							myRow.append("<TD NOWRAP><input type=\"radio\" name=\"index1\" value=\""
								+ msgList.getESW1REF()
								+ "\" "
								+ chk
								+ " onclick=\"setValue('"
								+ msgList.getESW1USR()
								+ "','" 
								+ msgList.getESW1REF()
								+ "')\"></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalSwiftFree('" + msgList.getESW1USR() + "', '" + msgList.getESW1REF() + "')\">"
								+ Util.formatCell(msgList.getESW1FOR())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalSwiftFree('" + msgList.getESW1USR() + "', '" + msgList.getESW1REF() + "')\">"
								+ Util.formatCell(msgList.getESW1USR())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalSwiftFree('" + msgList.getESW1USR() + "', '" + msgList.getESW1REF() + "')\">"
								+ Util.formatCell(msgList.getESW1REF())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalSwiftFree('" + msgList.getESW1USR() + "', '" + msgList.getESW1REF() + "')\">"
								+ Util.formatDate(msgList.getESW1DT1(),msgList.getESW1DT2(),msgList.getESW1DT3())
								+ "</A></TD>");	
							myRow.append("<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalSwiftFree('" + msgList.getESW1USR() + "', '" + msgList.getESW1REF() + "')\">"
								+ Util.formatCell(msgList.getESW1SWI())
								+ "</A></TD>");
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
					ses.setAttribute("SwiftFFList", beanList);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESWF010A_swift_free_format_approval_list.jsp");
						callPage(LangPath + "ESWF010A_swift_free_format_approval_list.jsp", req, res);
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
protected void procReqPos(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EPR020001Message msgList = null;
	ELEERRMessage msgError = null;
	JBListRec beanList = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
    int colNum = 17;
	try {
		beanList = new JBListRec();
		beanList.init(colNum);
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}
  	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (EPR020001Message)mc.getMessageRecord("EPR020001");
		msgList.setH01USERID(user.getH01USR());
        msgList.setH01FLGWK1("");
        
		try {
		 	  msgList.setE01PRLSFR(req.getParameter("REFNUM"));
		}
		catch (Exception e)	{
	 	  msgList.setE01PRLSFR("0");
		}
        
	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("EPR020001 Message Sent");
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

		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);
			beanList.setNoResult(true);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("docList", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EFE0000_enter_inquiry.jsp");
				callPage(LangPath + "EFE0000_enter_inquiry.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("EPR020001")) {

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			String myRow[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRow[i] = "";
			}
			String VDate = "";
			String VCCY  = "";
			
			while (true) {
				
				

				msgList = (EPR020001Message)newmessage;

				marker = msgList.getE01INDOPE();

				if (marker.equals("*")) {
					break;
				}
				else {


						myFlag = msgList.getE01PRLFMT();
						
						userPO.setIdentifier(msgList.getE01ACCNUM());
						
						myRow[0] = msgList.getE01PRLFMT();
						myRow[1] = Util.fcolorCCY(msgList.getE01AMOUNT());
						myRow[2] = msgList.getE01PRLSFR();
						myRow[3] = msgList.getE01PRLSRR();
						myRow[4] = msgList.getE01PRLUSR();								
						myRow[5] = msgList.getE01PRLRID();
						myRow[6] = msgList.getE01PRLTST();
						myRow[7] = msgList.getE01PRLMOD();
						myRow[8] = msgList.getE01PRLPRT();
						myRow[9] = Util.formatDate(msgList.getE01PRLSY1(),msgList.getE01PRLSY2(),msgList.getE01PRLSY3());
						myRow[10] = msgList.getE01PRLSYT();
						myRow[11] = msgList.getE01PRLCCY();
						myRow[12] = msgList.getE01ACCNUM();
																							
						  beanList.addRow(myFlag, myRow);
						}
						newmessage = mc.receiveMessage();
					}

			flexLog("Putting java beans into the session");
			ses.setAttribute("logs", beanList);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "EPR0200_logs.jsp");
				callPage(LangPath + "EPR0200_logs.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
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




	public void service(HttpServletRequest req, HttpServletResponse res)
		throws javax.servlet.ServletException, java.io.IOException {
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

			int screen = R_PASSWORD;

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
					case R_PASSWORD :
						procReqPassword(req, res, session);
						break;
					case R_COPY :
				     	procReqEnterCopy(msgUser, req, res, session);
						 break;	
					case R_APPROVAL :
						procReqApproval(mc, msgUser, req, res, session);
						break;
					case R_T_APPROVAL :
						procReqApprovalList(mc, msgUser, req, res, session);
						break;	
					case R_INQ :
						procReqInq(mc, msgUser, req, res, session);
						break;
					case R_INQ_A :
						procReqInqApp(mc, msgUser, req, res, session);
						break;	
					case R_MAINT :
						procReqMaint(mc, msgUser, req, res, session);
						break;
					case A_APPROVAL :
						procActionApproval(mc, msgUser, req, res, session);
						break;
					case A_MAINT :
						procActionMaint(mc, msgUser, req, res, session);
						break;
					case A_VALIDATE :
						procActionValidate(mc, msgUser, req, res, session);
						break;	
					case A_LOGS :
						procReqPos(mc, msgUser, req, res, session);
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqPassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setRedirect(
				"/servlet/datapro.eibs.forex.JSESWF010?SCREEN="
					+ R_APPROVAL
					+ (req.getParameter("REFNUM") == null
						? ""
						: "&REFNUM=" + req.getParameter("REFNUM")));
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}