package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ECH033501Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSECH0335 extends datapro.eibs.master.SuperServlet {

	protected static final int A_RT_PROTECT = 3;
	protected static final int A_RT_PROTECT_LIST = 4;

	// options
	protected static final int R_RT_PROTECT = 1;
	protected static final int R_RT_PROTECT_LIST = 2;
	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;

	protected String LangPath = "S";

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
				userPO.setIdentifier(req.getParameter("E01CKCACC").trim());
			} catch (Exception e) {
				userPO.setIdentifier("");
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			procReqChkProtectedList(mc, user, req, res, ses);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqChkProtectedList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECH033501Message msgChk = null;
		JBListRec chkList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgChk = (ECH033501Message) mc.getMessageRecord("ECH033501");
			msgChk.setH01USERID(user.getH01USR());
			msgChk.setH01PROGRM("ECH0335");
			msgChk.setH01TIMSYS(getTimeStamp());
			msgChk.setH01SCRCOD("01");
			msgChk.setH01OPECOD("0015");

			// Get Parameters here	

			try {
				msgChk.setE01CKCACC(userPO.getIdentifier());
			} catch (Exception e) {
			}

			msgChk.send();
			msgChk.destroy();

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		int colNum = 6;

		flexLog("Initializing java beans into the session");
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			chkList = new JBListRec();
			chkList.init(colNum);
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
			if (newmessage.getFormatName().equals("ECH033501")) {

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				boolean myFirstRow = true;

				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				while (true) {

					msgChk = (ECH033501Message) newmessage;
					marker = msgChk.getH01FLGMAS();
					if (myFirstRow) {
						userPO.setCusNum(msgChk.getE01CUSCUN());
						userPO.setCusName(msgChk.getE01CUSNA1());
						userPO.setHeader20("");
						userPO.setHeader21("");
						myFirstRow = false;
					}
					if (marker.equals("*")) {
						break;
					} else {
						myRow[0] = msgChk.getE01CKCCKN(); // Check Number
						myRow[1] = msgChk.getE01CKCAMT(); // Check Amount
						myRow[2] = msgChk.getE01CKCBNF(); // Beneficiary
						myRow[3] = msgChk.getE01CKCACC(); // Account
						myRow[4] = msgChk.getE01CUSCUN(); // Customer Code
						myRow[5] = msgChk.getE01CUSNA1(); // Customer Name

						chkList.addRow(myFlag, myRow);

					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.setAttribute("chkList", chkList);

				if (userPO.getPurpose().equals("INQUIRY")) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECH0335_rt_inq_protect_list.jsp");
						callPage(
							LangPath + "ECH0335_rt_inq_protect_list.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					if (IsNotError) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ECH0335_rt_protect_list.jsp");
							callPage(
								LangPath + "ECH0335_rt_protect_list.jsp",
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
									+ "ECH0335_rt_enter_protect.jsp");
							callPage(
								LangPath + "ECH0335_rt_enter_protect.jsp",
								req,
								res);
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
	protected void procReqEnter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ECH033501Message msgProtec = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			msgProtec = new ECH033501Message();

			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("chkProtec", msgProtec);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ECH0335_rt_enter_protect.jsp");
			callPage(LangPath + "ECH0335_rt_enter_protect.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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
					s = new Socket(super.hostIP, getInitSocket(req) + 7);
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
						// Request
						case R_RT_PROTECT_LIST :
							procReqChkProtectedList(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
							//entering options
						case R_ENTER :
							procReqEnter(msgUser, req, res, session);
							break;

						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						case A_RT_PROTECT :
							procActionChkProtected(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_RT_PROTECT_LIST :
							procActionChkProtectedList(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 7;
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

	protected void procActionChkProtected(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECH033501Message msgChk = null;
		ELEERRMessage msgError = null;
		JBListRec chkList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
		int row = 0;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			option = Integer.parseInt(req.getParameter("opt"));

			flexLog("Send Initial Data");
			msgChk = (ECH033501Message) mc.getMessageRecord("ECH033501");
			msgChk.setH01USERID(user.getH01USR());
			msgChk.setH01PROGRM("ECH0335");
			msgChk.setH01TIMSYS(getTimeStamp());
			msgChk.setH01SCRCOD("01");
			if (option == 1)
				msgChk.setH01OPECOD("0001");
			else {
				msgChk.setH01OPECOD("0005");
				chkList =
					(datapro.eibs.beans.JBListRec) ses.getAttribute("chkList");
				try {
					row = Integer.parseInt(req.getParameter("ROW"));
				} catch (Exception ex) {
				}
			}
			// all the fields here
			java.util.Enumeration enu = msgChk.fieldEnumeration();
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

			msgChk.send();
			//mc.sendMessage(msgChk);
			msgChk.destroy();
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

			if (newmessage.getFormatName().equals("ECH033501")) {
				try {
					msgChk = new ECH033501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgChk = (ECH033501Message) newmessage;
				// showESD008004(msgChk);

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
							+ "/servlet/datapro.eibs.products.JSECH0335?SCREEN=2'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("chkDet", msgChk);
					ses.setAttribute("userPO", userPO);
					if (option == 1) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ECH0335_rt_new_protect.jsp");
							callPage(
								LangPath + "ECH0335_rt_new_protect.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {

						// set
						chkList.setCurrentRow(row);

						chkList.setRecord(msgChk.getE01CKCAMT(), row, 1);
						// Check Amount
						chkList.setRecord(msgChk.getE01CKCBNF(), row, 2);
						// Beneficiary

						ses.setAttribute("chkList", chkList);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ECH0335_rt_maint_protect.jsp?ROW="
									+ row);
							res.sendRedirect(super.srctx + 
								LangPath
									+ "ECH0335_rt_maint_protect.jsp?ROW="
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
	protected void procActionChkProtectedList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ECH033501Message msgChk = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBListRec chkList = null;
		chkList = (JBListRec) ses.getAttribute("chkList");
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
					chkList.setCurrentRow(row);
					try {
						msgChk =
							(ECH033501Message) mc.getMessageRecord("ECH033501");
						msgChk.setH01USERID(user.getH01USR());
						msgChk.setH01PROGRM("ECH0335");
						msgChk.setH01TIMSYS(getTimeStamp());
						msgChk.setH01SCRCOD("01");
						msgChk.setH01OPECOD("0009");

						try {
							msgChk.setE01CKCCKN(chkList.getRecord(0));
						} catch (Exception e) {
						}
						try {
							msgChk.setE01CKCACC(chkList.getRecord(3));
						} catch (Exception e) {
						}

						msgChk.send();
						msgChk.destroy();
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
								procReqChkProtectedList(
									mc,
									user,
									req,
									res,
									ses);
							} else {
								try {
									flexLog("Putting java beans into the session");
									ses.setAttribute("error", msgError);
									res.sendRedirect(super.srctx + 
										LangPath
											+ "ECH0335_rt_protect_list.jsp?ROW="
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
					msgChk =
						(ECH033501Message) mc.getMessageRecord("ECH033501");
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(
						super.webAppPath
							+ LangPath
							+ "ECH0335_rt_new_protect.jsp");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("chkDet", msgChk);
					res.sendRedirect(super.srctx + 
						LangPath + "ECH0335_rt_protect_list.jsp?ROW=" + row);
					break;

				case 2 : // Maintenance
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(
						super.webAppPath
							+ LangPath
							+ "ECH0335_rt_maint_protect.jsp?ROW="
							+ row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx + 
						LangPath + "ECH0335_rt_protect_list.jsp?ROW=" + row);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

}