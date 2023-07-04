package datapro.eibs.client;

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
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.sockets.*;

public class JSEDP0030 extends datapro.eibs.master.SuperServlet {

	protected static final int A_ENTER = 200;
	protected static final int A_FINANCIAL = 2;
	protected static final int R_IDX_FINANCIAL = 3;
	// entering options
	protected static final int R_ENTER = 100;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0030() {
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
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procActionFinancial(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		MessageRecord newmessage = null;

		EDP003001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String opeCode = "";
		String flagAccept = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		opeCode = "0002";

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDP003001Message) mc.getMessageRecord("EDP003001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP0030");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD(opeCode);
			// all the fields here
			java.util.Enumeration enu = msgList.fieldEnumeration();
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
			msgList.send();
			msgList.destroy();
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
				//showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDP003001")) {

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.client.JSEDP0030?SCREEN=200&E01IFMCUN="
							+ userPO.getHeader1()
							+ "&E01IFMCFO="
							+ userPO.getHeader3()
							+ "&E01IFMFEY="
							+ userPO.getHeader6()
							+ "&E01IFMFEM="
							+ userPO.getHeader7()
							+ "&ROW="
							+ req.getParameter("ROW"));
				} else { // There are errors
					msgList = (EDP003001Message) newmessage;

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					//ses.setAttribute("plan", msgList);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0030_client_financial.jsp?ROW="
								+ req.getParameter("ROW"));
						res.sendRedirect(super.srctx + 
							LangPath
								+ "EDP0030_client_financial.jsp?ROW="
								+ req.getParameter("ROW"));
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
	protected void procActionEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP003001Message msgList = null;
		ELEERRMessage msgError = null;
		JBListRec optList = null;
		JBListRec grpList = null;
		JBListRec grpAccList = null;
		UserPos userPO = null;

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			msgList = (EDP003001Message) mc.getMessageRecord("EDP003001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP0030");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			try {
				try {
					msgList.setE01IFMCUN(req.getParameter("E01IFMCUN"));
				} catch (Exception e) {
				}
				try {
					msgList.setE01IFMFEY(req.getParameter("E01IFMFEY"));
				} catch (Exception e) {
				}
				try {
					msgList.setE01IFMFEM(req.getParameter("E01IFMFEM"));
				} catch (Exception e) {
				}
				try {
					msgList.setE01IFMCFO(req.getParameter("E01IFMCFO"));
				} catch (Exception e) {
				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

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
				msgError = (ELEERRMessage) newmessage;
				//IsNotError = msgError.getERRNUM().equals("0");
				//flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0030_client_financial_enter.jsp");
					callPage(
						LangPath + "EDP0030_client_financial_enter.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDP003001")) {
				String marker = "";
				String myFlag = "";
				int idxOpt = -1;
				int idxGrp = -1;
				int idxGrpAcc = -1;
				//Initial... 
				/*try {
					optList = new datapro.eibs.beans.JBList();
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}*/
				int colNum = 4;
				try {
					optList = new datapro.eibs.beans.JBListRec();
					optList.init(colNum);
					grpList = new datapro.eibs.beans.JBListRec();
					grpList.init(colNum);
					grpAccList = new datapro.eibs.beans.JBListRec();
					grpAccList.init(colNum);
					userPO = new datapro.eibs.beans.UserPos();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				String myGrp[] = new String[colNum];
				String myGrpAcc[] = new String[colNum];
				String myOption[] = new String[colNum];
				boolean firstTime = true;
				for (int i = 0; i < colNum; i++) {
					myGrp[i] = "";
					myGrpAcc[i] = "";
					myOption[i] = "";
				}

				while (true) {
					msgList = (EDP003001Message) newmessage;
					marker = msgList.getH01FLGMAS();
					if (marker.equals("*")) {
						break;
					} else {
						if (msgList.getE01IFMTYP().equals("")) {
							idxOpt++;
							myOption[0] = msgList.getE01IFMDSC();
							//"<option value=\"" + idxOpt + "\">" + msgList.getE01IFMDSC() + "</option>";
							myFlag = "";
							idxGrp = -1;
							optList.addRow(myFlag, myOption);
							if (firstTime) {
								firstTime = false;
								userPO.setHeader1(msgList.getE01IFMCUN());
								//Custummer
								userPO.setHeader2(msgList.getE01IFMNA1());
								//Custumer Name
								userPO.setHeader3(msgList.getE01IFMCFO());
								//Format
								userPO.setHeader4(msgList.getE01IFMCIN());
								//Industry Code
								userPO.setHeader5(msgList.getE01IFMLNE());
								//Bussines Code
								userPO.setHeader6(msgList.getE01IFMFEY());
								//Year
								userPO.setHeader7(msgList.getE01IFMFEM());
								//Month
							}
						} else if (
							msgList.getE01IFMTYP().equals("H")) { //Header
							myFlag = "" + idxOpt;
							idxGrp++;
							myGrp[0] = msgList.getE01IFMDSC();
							myGrp[1] = msgList.getE01IFMAMT();
							grpList.addRow(myFlag, myGrp);
						} else if (
							msgList.getE01IFMTYP().equals(
								"D")) { // detail msgList.getE01IFMTYP().equals("D")
							myFlag = "" + idxOpt + "" + idxGrp;
							myGrpAcc[0] = msgList.getE01IFMGLN();
							myGrpAcc[1] = msgList.getE01IFMDSC();
							myGrpAcc[2] = msgList.getE01IFMAMT();
							myGrpAcc[3] = msgList.getE01IFMNA1();
							grpAccList.addRow(myFlag, myGrpAcc);
						} else {
						}
					}
					newmessage = mc.receiveMessage();
				}

				if (firstTime) {
					try {
						userPO.setHeader1(req.getParameter("E01IFMCUN"));
					} catch (Exception e) {
						userPO.setHeader1("");
					}
					try {
						userPO.setHeader6(req.getParameter("E01IFMFEY"));
					} catch (Exception e) {
						userPO.setHeader6("");
					}
					try {
						userPO.setHeader7(req.getParameter("E01IFMFEM"));
					} catch (Exception e) {
						userPO.setHeader7("");
					}
					try {
						userPO.setHeader3(req.getParameter("E01IFMCFO"));
					} catch (Exception e) {
						userPO.setHeader3("");
					}
				}
				flexLog("Putting java beans into the session");
				ses.setAttribute("optList", optList);
				ses.setAttribute("grpList", grpList);
				ses.setAttribute("accList", grpAccList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0030_client_financial.jsp");
					callPage(
						LangPath + "EDP0030_client_financial.jsp",
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
	protected void procReqIdxFinancial(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP009001Message msgSearch = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgSearch = (EDP009001Message) mc.getMessageRecord("EDP009001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDP0090");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0004");
			try {
				try {
					msgSearch.setE01IFDCUN(userPO.getHeader1());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE01IFDCFO(userPO.getHeader3());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE01IFDFEY(userPO.getHeader6());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE01IFDFEM(userPO.getHeader7());
				} catch (Exception e) {
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

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				// showERROR(msgError);
				flexLog("ELEERRMessage: " + msgError.toString());

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

			if (newmessage.getFormatName().equals("EDP009001")) {

				msgSearch = (EDP009001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("idxFnl", msgSearch);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0030_client_financial_idx.jsp");
						callPage(
							LangPath + "EDP0030_client_financial_idx.jsp",
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
								+ "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
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
	protected void procReqEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP003501Message msgList = null;
		EDP003001Message msgFnl = null;
		JBList beanList = null;
		String myOption = "";
		String myFlag = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			beanList = new datapro.eibs.beans.JBList();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			msgFnl = new datapro.eibs.beans.EDP003001Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDP003501Message) mc.getMessageRecord("EDP003501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP0035");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD("0015");
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			myOption = "<option value=\"\"></option>";
			beanList.addRow(myFlag, myOption);

			if (newmessage.getFormatName().equals("EDP003501")) {

				String marker = "";
				while (true) {

					msgList = (EDP003501Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						myOption =
							"<option value=\""
								+ msgList.getE01IFFFMT()
								+ "\">"
								+ msgList.getE01IFFFMT()
								+ " - "
								+ msgList.getE01IFFDSC()
								+ "</option>";
						beanList.addRow(myFlag, myOption);
					}
					newmessage = mc.receiveMessage();
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session");
			ses.setAttribute("clientFnl", msgFnl);
			ses.setAttribute("optList", beanList);
			ses.setAttribute("error", msgError);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0030_client_financial_enter.jsp");
				callPage(
					LangPath + "EDP0030_client_financial_enter.jsp",
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

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						// Requests
						case R_ENTER :
							procReqEnter(mc, msgUser, req, res, session);
							break;
						case R_IDX_FINANCIAL :
							procReqIdxFinancial(mc, msgUser, req, res, session);
							break;
							// Actions
						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						case A_FINANCIAL :
							procActionFinancial(mc, msgUser, req, res, session);
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
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

}