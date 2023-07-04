package datapro.eibs.approval; 

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
import java.math.BigDecimal;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.DataCheckReject;
import datapro.eibs.beans.EDD009001Message;
import datapro.eibs.beans.EDD092401Message;
import datapro.eibs.beans.EDD092402Message;
import datapro.eibs.beans.EDD092403Message;
import datapro.eibs.beans.EDD094001Message;
import datapro.eibs.beans.EDD0948DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EREPORTSTDMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEDD0924 extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_PASSWORD = 1;
	protected static final int R_SEARCH = 2;
	protected static final int A_SEARCH = 3;

	protected static final int R_UNPOSTED = 4;
	protected static final int A_RETURN_ITEMS = 5;
	protected static final int R_SUMMARY = 6;
	protected static final int R_NSF = 7;
	protected static final int R_LARGE_ITEMS = 8;
	protected static final int R_POSTED = 9;

	//protected static final int R_PRINTER 			= 10;
	//protected static final int A_NSF				= 11;

	protected static final int R_ENTER_CLEARING = 10;
	protected static final int R_SUMMARYREPORT = 11;
	protected static final int R_CLEARING = 12;
	protected static final int R_SUMMARY_OFFICER = 13;
	protected static final int R_FILTER_OFFICER = 14;
	protected static final int A_SEND_MESSAGE = 15;
	protected static final int A_CHGSTS_OFFICER = 16;
	protected static final int A_OFFICER_NSF = 17;
	protected static final int R_MISSING_TAX_ID = 18;
	protected static final int R_CUSTOMER_TAX_ID_LISTING = 19;

	// entering options	
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 400;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD0924() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDD0924");

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

	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		JBListRec beanList = null;
		JBListRec beanAccList = null;
		DataCheckReject dataCR = null;
		MessageRecord newmessage = null;
		EDD092403Message msgSearch = null;
		ELEERRMessage msgError = null;

		try {
			dataCR = (DataCheckReject) ses.getAttribute("dataCR");
			beanList = (JBListRec) ses.getAttribute("chkList");

			if (dataCR.getOption().equals("NSF") ||
				dataCR.getOption().equals("NS1")) {
				beanAccList = (JBListRec) ses.getAttribute("accList");
				beanAccList.initRow();
				beanList.initRow();
				beanList.getNextRow();
				int countAC = 0;
				while (beanAccList.getNextRow()) {
					if (!beanAccList.getFlag().equals("")) {
						int countCK = 0;
						while (beanList
							.getRecord(0)
							.equals(beanAccList.getRecord(0))) {
							String name = "OPTION_" + countAC + "" + countCK;
							String name2 = "REMDATA" + countAC + "" + countCK;
							String name3 = "NEWOF1_" + countAC + "" + countCK;
							beanList.setRecord(
								req.getParameter(name).toUpperCase(),
								17,
								beanList.getCurrentRow());
							beanList.setRecord(
								req.getParameter(name2).toUpperCase(),
								22,
								beanList.getCurrentRow());
							try {
								beanList.setRecord(
									req.getParameter(name3).toUpperCase(),
									8,
									beanList.getCurrentRow());
							} catch (Exception e) {
							}
							countCK++;
							if (!beanList.getNextRow()) {
								break;
							}
						}
						countAC++;
					}
				}
			} else {
				beanList.initRow();
				while (beanList.getNextRow()) {
					if (beanList.getFlag().equals("")) {
						beanList.setRecord(
							req
								.getParameter(
									"OPTION_" + beanList.getCurrentRow())
								.toUpperCase(),
							17,
							beanList.getCurrentRow());
						// if (!dataCR.getOption().equals("LI")) 
						beanList.setRecord(
							req
								.getParameter(
									"REMDATA" + beanList.getCurrentRow())
								.toUpperCase(),
							22,
							beanList.getCurrentRow());
						if (dataCR.getOption().equals("UI")
							|| dataCR.getOption().equals("PI")
							|| dataCR.getOption().equals("RP")) {
							beanList.setRecord(
								req
									.getParameter(
										"NEWACC_" + beanList.getCurrentRow())
									.toUpperCase(),
								10,
								beanList.getCurrentRow());
							beanList.setRecord(
								req
									.getParameter(
										"NEWCHK_" + beanList.getCurrentRow())
									.toUpperCase(),
								11,
								beanList.getCurrentRow());
							beanList.setRecord(
								req
									.getParameter(
										"NEWRS1_" + beanList.getCurrentRow())
									.toUpperCase(),
								12,
								beanList.getCurrentRow());
							beanList.setRecord(
								req
									.getParameter(
										"NEWRS2_" + beanList.getCurrentRow())
									.toUpperCase(),
								13,
								beanList.getCurrentRow());
							beanList.setRecord(
								req
									.getParameter(
										"NEWRS3_" + beanList.getCurrentRow())
									.toUpperCase(),
								14,
								beanList.getCurrentRow());
							beanList.setRecord(
								req
									.getParameter(
										"NEWRS4_" + beanList.getCurrentRow())
									.toUpperCase(),
								15,
								beanList.getCurrentRow());
							beanList.setRecord(
								req
									.getParameter(
										"NEWGL_" + beanList.getCurrentRow())
									.toUpperCase(),
								23,
								beanList.getCurrentRow());
						}
					}
				}
			}

			beanList.initRow();
			while (beanList.getNextRow()) {
				if (beanList.getRecord(17).equals(""))
					continue;

				// Send Data & Receive Confirmation
				try {
					flexLog("Send Initial Data");
					msgSearch =
						(EDD092403Message) mc.getMessageRecord("EDD092403");
					msgSearch.setH03USERID(user.getH01USR());
					msgSearch.setH03PROGRM("EDD0924");
					msgSearch.setH03TIMSYS(getTimeStamp());
					msgSearch.setH03SCRCOD("01");

					/**if (dataCR.getOption().equals("NSF")) {
						msgSearch.setH03FLGWK1("1");
					} else if (dataCR.getOption().equals("LI")) {
						msgSearch.setH03FLGWK1("2");
					} else if (dataCR.getOption().equals("UI")) {
						msgSearch.setH03FLGWK1("3");
					} else if (dataCR.getOption().equals("PI")) {
						msgSearch.setH03FLGWK1("4");
					} else {
						msgSearch.setH03FLGWK1("5");
					}	**/

					try {
						msgSearch.setH03OPECOD(beanList.getRecord(17));
						// Operation Code "0001" "0002" "0003" "0004"
					} catch (Exception e) {
					}
					try {
						msgSearch.setE03DEVACC(beanList.getRecord(0));
						// Account Number
					} catch (Exception e) {
					}
					try {
						msgSearch.setE03DEVCHK(beanList.getRecord(2));
						// Check Number
					} catch (Exception e) {
					}
					try {
						msgSearch.setE03DEVAMT(beanList.getRecord(3));
						// Amount
					} catch (Exception e) {
					}
					try {
						msgSearch.setE03DEVFLG(beanList.getRecord(4)); // Flag
					} catch (Exception e) {
					}
					try {
						msgSearch.setE03DEVREM(beanList.getRecord(22));
						// Comments
					} catch (Exception e) {
					}

					if (dataCR.getOption().equals("NSF") ||
						dataCR.getOption().equals("NS1")) {
						try {
							msgSearch.setE03DEVRES(beanList.getRecord(13));
							// Cause 1
							msgSearch.setE03DEVRE2(beanList.getRecord(14));
							// Cause 2
							msgSearch.setE03DEVRE3(beanList.getRecord(15));
							// Cause 3
							msgSearch.setE03DEVRE4(beanList.getRecord(16));
							// Cause 4						
						} catch (Exception e) {
						}
					} else if (dataCR.getOption().equals("RP")) {
						try {
							msgSearch.setE03DEVRES(beanList.getRecord(6));
							// Cause 1
						} catch (Exception e) {
						}
					}

					if (beanList.getRecord(17).equals("0004")) {
						try {
							msgSearch.setE03NEWACC(beanList.getRecord(10));
							// New Account
						} catch (Exception e) {
						}
						try {
							msgSearch.setE03NEWCHK(beanList.getRecord(11));
							// New Check Number
						} catch (Exception e) {
						}
						try {
							msgSearch.setE03NEWRES(beanList.getRecord(12));
							// New Reason 1
						} catch (Exception e) {
						}
						try {
							msgSearch.setE03NEWRE2(beanList.getRecord(13));
							// New Reason 2
						} catch (Exception e) {
						}
						try {
							msgSearch.setE03NEWRE3(beanList.getRecord(14));
							// New Reason 3
						} catch (Exception e) {
						}
						try {
							msgSearch.setE03NEWRE4(beanList.getRecord(15));
							// New Reason 4
						} catch (Exception e) {
						}
						try {
							msgSearch.setE03NEWGLN(beanList.getRecord(23));
							// New GL
						} catch (Exception e) {
						}
						try {
							msgSearch.setE03DEVOF1(beanList.getRecord(8));
							// New Officer
						} catch (Exception e) {
						}
					}
					msgSearch.send();
					msgSearch.destroy();
					flexLog("EDD092403 Message Sent");

					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;
						boolean IsNotError = msgError.getERRNUM().equals("0");
						if (IsNotError) {
							beanList.setFlag("S", beanList.getCurrentRow());
						} else {
							msgError.setERDF01(
								"OPTION" + beanList.getCurrentRow());
							flexLog("Putting java beans into the session");
							ses.setAttribute("error", msgError);
							ses.setAttribute("chkList", beanList);
							ses.setAttribute("dataCR", dataCR);

							try {
								if (dataCR.getOption().equals("NSF") ||
									dataCR.getOption().equals("NS1")) {
									ses.setAttribute("accList", beanAccList);
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDD0924_rejection_chk_nsf_list.jsp?ROW="
											+ beanList.getCurrentRow()
											+ "&ACC="
											+ beanList.getRecord(0));
									res.sendRedirect(
										super.srctx
											+ LangPath
											+ "EDD0924_rejection_chk_nsf_list.jsp?ROW="
											+ beanList.getCurrentRow()
											+ "&ACC="
											+ beanList.getRecord(0));
								} else if (dataCR.getOption().equals("UI")) {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDD0924_rejection_chk_unposted_list.jsp?ROW="
											+ beanList.getCurrentRow());
									res.sendRedirect(
										super.srctx
											+ LangPath
											+ "EDD0924_rejection_chk_unposted_list.jsp?ROW="
											+ beanList.getCurrentRow());
								} else if (dataCR.getOption().equals("PI")) {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDD0924_rejection_chk_posted_list.jsp?ROW="
											+ beanList.getCurrentRow());
									res.sendRedirect(
										super.srctx
											+ LangPath
											+ "EDD0924_rejection_chk_posted_list.jsp?ROW="
											+ beanList.getCurrentRow());
								} else if (dataCR.getOption().equals("RP")) {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDD0924_rejection_chk_protesto_list.jsp");
									callPage(
										LangPath
											+ "EDD0924_rejection_chk_protesto_list.jsp",
										req,
										res);
								} else {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDD0924_rejection_chk_large_list.jsp?ROW="
											+ beanList.getCurrentRow());
									res.sendRedirect(
										super.srctx
											+ LangPath
											+ "EDD0924_rejection_chk_large_list.jsp?ROW="
											+ beanList.getCurrentRow());
								}
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
							return;
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

			}

			if (dataCR.getOption().equals("NSF") ||
				dataCR.getOption().equals("NS1")) {
				procReqNSF(mc, user, req, res, ses);
			} else {
				procReqList(mc, user, req, res, ses);
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
	protected void procActionSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		DataCheckReject dataCR = null;

		try {
			dataCR =
				(datapro.eibs.beans.DataCheckReject) ses.getAttribute("dataCR");
				
			String officer = req.getParameter("E01SELOFC");
			officer = (officer == null) ? "" : officer.toUpperCase(); 
			String branch = req.getParameter("E01SELBRN");
			branch = (branch == null) ? "" : branch.toUpperCase();
			String account = req.getParameter("E01SELACC");
			account = (account == null) ? "" : account.toUpperCase();
			String amount = req.getParameter("E01SELAMT");
			amount = (amount == null) ? "" : amount.toUpperCase();
			String cause = req.getParameter("E01SELCAU");
			cause = (cause == null) ? "" : cause.toUpperCase();
			String doc = req.getParameter("E01SELDOC");
			doc = (doc == null) ? "" : doc.toUpperCase();

			if ((dataCR.getOption().equals("NSF"))
				|| (dataCR.getOption().equals("RR"))
				|| (dataCR.getOption().equals("NS1"))) {
				dataCR.setOfficer(officer);
				// Official Code
				dataCR.setBranch(branch);
				dataCR.setCause(""); // Cause
				dataCR.setAccNum(""); // Acount
				if ((dataCR.getOption().equals("NS1"))||(dataCR.getOption().equals("RR"))) {
					dataCR.setAmount(amount);
				} else {
					dataCR.setAmount("");
				}

			} else if (
				(dataCR.getOption().equals("UI"))
					|| (dataCR.getOption().equals("RP"))) {
				dataCR.setOfficer(officer);
				// Official Code
				dataCR.setBranch(branch);
				dataCR.setCause(cause);
				// Cause
				dataCR.setAccNum(""); // Acount
				if (dataCR.getOption().equals("UI")) {
					  dataCR.setAmount(amount);
				} else {
					dataCR.setAmount("");
				}				
				
			} else if (dataCR.getOption().equals("LI")) {
				dataCR.setOfficer(""); // Official Code
				dataCR.setBranch(branch);
				//dataCR.setCause("0013");	// Cause
				dataCR.setCause(""); // Cause
				dataCR.setAccNum(""); // Acount		
			} else if (dataCR.getOption().equals("PI")) {
				dataCR.setOfficer(""); // Official Code
				dataCR.setBranch("");
				dataCR.setCause("0000"); // Cause
				dataCR.setAccNum(account);
				// Acount
			}

			if (dataCR.getOption().equals("UI")){
			   dataCR.setRelation(doc);
			} else {
				dataCR.setRelation("");	
			}

			ses.setAttribute("dataCR", dataCR);
			if (dataCR.getOption().equals("NSF")
				|| dataCR.getOption().equals("NS1")) {
				procReqNSF(mc, user, req, res, ses);
			} else {
				procReqList(mc, user, req, res, ses);
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
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD092401Message msgSearch = null;
		ELEERRMessage msgError = null;
		JBListRec beanList = null;
		DataCheckReject dataCR = null;
		boolean IsNotError = false;
		String Pos = "0";

		msgError = new datapro.eibs.beans.ELEERRMessage();
		dataCR =
			(datapro.eibs.beans.DataCheckReject) ses.getAttribute("dataCR");

		try {
			if (req.getParameter("FlagMov") == null
				|| req.getParameter("FlagMov").equals("0")) {
				Pos = "0";
			} else if (req.getParameter("FlagMov").equals("+")) {
				Pos = dataCR.getNext();
			} else if (req.getParameter("FlagMov").equals("-")) {
				dataCR.setIndex(dataCR.getIndex() - 1);
				Pos = "" + (Integer.parseInt(dataCR.getPrevious()) - 1);
			} else {
				if (dataCR.getIndex() == 0)
					Pos = "0";
				else
					Pos =
						""
							+ Integer.parseInt(
								dataCR.getNext(dataCR.getIndex() - 1));
			}
		} catch (Exception e) {
		}

		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD092401Message) mc.getMessageRecord("EDD092401");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD0924");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("   ");

			try {
				msgSearch.setE01SELDOC(dataCR.getRelation());
			} catch (Exception e) {
			}

			try {
				msgSearch.setE01SELBNK(dataCR.getBank());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE01SELCCY(dataCR.getCurrency());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE01SELOFC(dataCR.getOfficer());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE01SELBRN(dataCR.getBranch());
			} catch (Exception e) {
			}
			try {
				if ((dataCR.getOption().equals("UI") && Pos.equals("0"))
					|| (dataCR.getOption().equals("RR") && Pos.equals("0"))) {
					if (req.getParameter("CAUSE") != null
						&& !req.getParameter("CAUSE").equals("0")) {
						dataCR.setCause(req.getParameter("CAUSE"));
					}
				}
				msgSearch.setE01SELCAU(dataCR.getCause());
			} catch (Exception e) {
			}
			try {
				if (dataCR.getOption().equals("PI")) {
					msgSearch.setE01SELACC(dataCR.getAccNum());
				} else {
					if (req.getParameter("ACCNUM") == null
						&& req.getParameter("ACCNUM").equals("0")) {
						msgSearch.setE01SELACC(dataCR.getAccNum());
					} else {
						msgSearch.setE01SELACC(req.getParameter("ACCNUM"));
					}
				}
			} catch (Exception e) {
			}
			try {
				msgSearch.setE01SELCHK(dataCR.getChkNum());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE01SELAMT(dataCR.getAmount());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE01NUMREC(Pos);
			} catch (Exception ex) {
			}

			/** opcion del menu va en h01opecod **/
			msgSearch.setH01OPECOD(dataCR.getOption());

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD092401 Message Sent");

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
				// showERROR(msgError);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				beanList =
					(datapro.eibs.beans.JBListRec) ses.getAttribute("chkList");
				if (beanList == null
					|| !msgError.getERNU01().equals("0014")
					|| !msgError.getERNU01().equals("8132")) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0924_rejection_chk_search.jsp");
						callPage(
							LangPath + "EDD0924_rejection_chk_search.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
						e.printStackTrace();
					}
				} else { // This is not needed, always must call the above page if an error 
					// because the status of one or more checks could have change
					try {
						if (dataCR.getOption().equals("UI")) {
							if (msgError.getERNU01().equals("8132")) {
								dataCR.setCause("");
								ses.setAttribute("dataCR", dataCR);
							}
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0924_rejection_chk_unposted_list.jsp");
							callPage(
								LangPath
									+ "EDD0924_rejection_chk_unposted_list.jsp",
								req,
								res);
						} else if (dataCR.getOption().equals("PI")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0924_rejection_chk_posted_list.jsp");
							callPage(
								LangPath
									+ "EDD0924_rejection_chk_posted_list.jsp",
								req,
								res);
						} else if (dataCR.getOption().equals("RR")) {
							if (msgError.getERNU01().equals("8132")) {
								dataCR.setCause("");
								ses.setAttribute("dataCR", dataCR);
							}
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0924_rejection_chk_revision_list.jsp");
							callPage(
								LangPath
									+ "EDD0924_rejection_chk_revision_list.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD0924_rejection_chk_large_list.jsp");
							callPage(
								LangPath
									+ "EDD0924_rejection_chk_large_list.jsp",
								req,
								res);
						}

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
						e.printStackTrace();
					}
				}

			} else if (newmessage.getFormatName().equals("EDD092401")) {
				int colNum = 32;
				try {
					beanList = new datapro.eibs.beans.JBListRec();
					beanList.init(colNum);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";

				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				while (true) {
					msgSearch = (EDD092401Message) newmessage;
					marker = msgSearch.getE01INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(
								Integer.parseInt(msgSearch.getE01NUMREC()));
						}
						myRow[0] = msgSearch.getE01DEVACC(); // Account Number
						myRow[16] = msgSearch.getE01DEVCUN();
						// Customer Number
						myRow[1] = msgSearch.getE01DEVNA1(); // Customer Name
						myRow[2] = Util.addLeftChar('0', 6, msgSearch.getE01DEVCHK()); // Check Number
						myRow[3] = msgSearch.getE01DEVAMT(); // Amount
						myRow[4] = msgSearch.getE01DEVFLG(); // Flag
						myRow[5] =
							msgSearch.getE01DEVRES()
								+ " "
								+ msgSearch.getE01DEVDS1();
						// Cause 1
						myRow[6] = msgSearch.getE01DEVDS2(); // Cause 2
						myRow[7] = msgSearch.getE01DEVDS3(); // Cause 3
						myRow[8] = msgSearch.getE01DEVDS4(); // Cause 4
						myRow[9] = msgSearch.getE01NUMREC(); // Record Number
						myRow[10] = ""; // New Account
						myRow[11] = ""; // New Check Number
						myRow[12] = ""; // New Reason 1
						myRow[13] = ""; // New Reason 2
						myRow[14] = ""; // New Reason 3
						myRow[15] = ""; // New Reason 4
						myRow[23] = ""; // New GL
						myRow[17] = ""; // No Action Operation Code
						myRow[18] = msgSearch.getE01DEVBNK(); // Bank
						myRow[19] = msgSearch.getE01DEVDMM(); // Trans Date
						myRow[20] = msgSearch.getE01DEVDDD(); // Trans date
						myRow[21] = msgSearch.getE01DEVDYY(); // Trans Date
						myRow[22] = msgSearch.getE01DEVREM(); // Comments
						myRow[24] = msgSearch.getE01DEVDOC(); // Type 
						myRow[25] = msgSearch.getE01DEVCCY(); // Currency 
						myRow[29] = msgSearch.getE01DEVGLN(); // GLN
						myRow[26] = msgSearch.getE01DEVOFC(); // Officer
						myRow[27] = msgSearch.getE01DEVIMG(); // IMG
						myRow[28] = msgSearch.getE01DEVOBR(); // Branch
						myRow[30] = msgSearch.getE01DEVEMI(); // Receiver Bank
						//myRow[31] = msgSearch.getE01FULACC(); // full account
                        myRow[31] = msgSearch.getE01SELACC();
						beanList.addRow(myFlag, myRow);
						beanList.setLastRec(
							Integer.parseInt(msgSearch.getE01NUMREC()));
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
						newmessage = mc.receiveMessage();
					}
				}

				try {
					if (dataCR.getMaxRow() == 0) {
						dataCR.addRow(
							beanList.getLastRec() + "",
							beanList.getFirstRec() + "");
					} else if (req.getParameter("FlagMov").equals("+")) {
						dataCR.setIndex(dataCR.getIndex() + 1);
						if (dataCR.getIndex() >= dataCR.getMaxRow()) {
							dataCR.addRow(
								beanList.getLastRec() + "",
								beanList.getFirstRec() + "");
						}
					}
				} catch (Exception e) {
				}

				if (dataCR.getIndex() < 1) {
					beanList.setShowPrev(false);
				} else {
					beanList.setShowPrev(true);
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("chkList", beanList);
				ses.setAttribute("dataCR", dataCR);

				try {
					if (dataCR.getOption().equals("UI")) {
						flexLog(
							"About to call Page:++++++++"
								+ LangPath
								+ "EDD0924_rejection_chk_unposted_list.jsp");
						callPage(
							LangPath
								+ "EDD0924_rejection_chk_unposted_list.jsp",
							req,
							res);
					} else if (dataCR.getOption().equals("PI")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0924_rejection_chk_posted_list.jsp");
						callPage(
							LangPath + "EDD0924_rejection_chk_posted_list.jsp",
							req,
							res);
						//			callPage(LangPath + "EDD0924_rejection_chk_unposted_list.jsp", req, res);
					} else if (dataCR.getOption().equals("RP")) {
						flexLog(
							"About to call Page:******** "
								+ LangPath
								+ "EDD0924_rejection_chk_protesto_list.jsp");
						callPage(
							LangPath
								+ "EDD0924_rejection_chk_protesto_list.jsp",
							req,
							res);
					} else if (dataCR.getOption().equals("RR")) {
						flexLog(	
							"About to call Page:========== "
								+ LangPath
								+ "EDD0924_rejection_chk_revision_list.jsp");
						callPage(
							LangPath
								+ "EDD0924_rejection_chk_revision_list.jsp",
							req,
							res);
					} else {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0924_rejection_chk_large_list.jsp");
						callPage(
							LangPath + "EDD0924_rejection_chk_large_list.jsp",
							req,
							res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
					e.printStackTrace();
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
	protected void procReqSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		DataCheckReject dataCR = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			dataCR =
				(datapro.eibs.beans.DataCheckReject) ses.getAttribute("dataCR");

			ses.setAttribute("error", msgError);
			ses.setAttribute("dataCR", dataCR);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD0924_rejection_chk_search.jsp");
			callPage(LangPath + "EDD0924_rejection_chk_search.jsp", req, res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqNSF(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD092402Message msgSearch = null;
		ELEERRMessage msgError = null;
		JBListRec beanList = null;
		JBListRec beanAccList = null;
		DataCheckReject dataCR = null;
		boolean IsNotError = false;
		String Pos = "0";
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		dataCR =
			(datapro.eibs.beans.DataCheckReject) ses.getAttribute("dataCR");

		try {
			if (req.getParameter("FlagMov") == null
				|| req.getParameter("FlagMov").equals("0")) {
				Pos = "0";
			} else if (req.getParameter("FlagMov").equals("+")) {
				Pos = dataCR.getNext();
			} else if (req.getParameter("FlagMov").equals("-")) {
				dataCR.setIndex(dataCR.getIndex() - 1);
				Pos = "" + (Integer.parseInt(dataCR.getPrevious()) - 1);
			} else {
				if (dataCR.getIndex() == 0)
					Pos = "0";
				else
					Pos =
						""
							+ Integer.parseInt(
								dataCR.getNext(dataCR.getIndex() - 1));
			}
		} catch (Exception e) {
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			msgSearch = (EDD092402Message) mc.getMessageRecord("EDD092402");
			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("EDD0924");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("01");
			msgSearch.setH02OPECOD("0001");

			try {
				msgSearch.setE02SELBNK(dataCR.getBank());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE02SELCCY(dataCR.getCurrency());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE02SELOFC(dataCR.getOfficer());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE02SELBRN(dataCR.getBranch());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE02SELAMT(dataCR.getAmount());
			} catch (Exception e) {
			}
			try {
				msgSearch.setE02NUMREC(Pos);
			} catch (Exception ex) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD092402 Message Sent");

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
				// showERROR(msgError);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (msgError.getERNU01().equals("0014")) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0924_rejection_chk_nsf_list.jsp");
						callPage(
							LangPath + "EDD0924_rejection_chk_nsf_list.jsp",
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
								+ "EDD0924_rejection_chk_search.jsp");
						callPage(
							LangPath + "EDD0924_rejection_chk_search.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else if (newmessage.getFormatName().equals("EDD092402")) {
				int colNum = 32;
				try {
					beanList = new datapro.eibs.beans.JBListRec();
					beanList.init(colNum);
					beanAccList = new datapro.eibs.beans.JBListRec();
					beanAccList.init(colNum);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				userPO.setHeader17("");
				userPO.setHeader18("");

				String myRow[] = new String[colNum];
				String myAccRow[] = new String[colNum];

				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
					myAccRow[i] = "";
				}

				while (true) {
					msgSearch = (EDD092402Message) newmessage;
					marker = msgSearch.getE02INDOPE();

					if (marker.equals("*")) {
						beanAccList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							userPO.setHeader17(msgSearch.getH02FLGWK1());
							userPO.setHeader18(msgSearch.getH02FLGWK2());
							firstTime = false;
							beanList.setFirstRec(
								Integer.parseInt(msgSearch.getE02NUMREC()));
						}
						if (!myFlag.equals(msgSearch.getE02DEVACC())) {
							myFlag = msgSearch.getE02DEVACC();

							myAccRow[0] = msgSearch.getE02DEVACC();
							// Account Number
							myAccRow[1] = msgSearch.getE02DEVCUN();
							// Customer Number
							myAccRow[2] = msgSearch.getE02DEVNA1();
							// Customer Name
							myAccRow[3] = msgSearch.getE02ACMMGB();
							// Gross Balance
							myAccRow[4] = msgSearch.getE02ACMMNB();
							// Net Balance
							myAccRow[5] = msgSearch.getE02LNEAMN();
							// Original Amt	
							BigDecimal bd =
								new BigDecimal(
									msgSearch.getBigDecimalE02LNEAMN() + "");
							myAccRow[6] =
								Util.formatCCY(
									bd.subtract(
										msgSearch.getBigDecimalE02LNEAMU())
										+ "");
							// Available Amt
							myAccRow[7] =
								Util.formatDate(
									msgSearch.getE02LNEMTD(),
									msgSearch.getE02LNEMTM(),
									msgSearch.getE02LNEMTY());
							// Maturity Date
							myAccRow[8] = msgSearch.getE02DEVRES();
							// Type Cause 1
							myAccRow[9] = msgSearch.getE02NUMREC();
							// Record Number
							myAccRow[10] = msgSearch.getE02ACMUL1();
							// Uncollected 1 day
							myAccRow[11] = msgSearch.getE02ACMUL2();
							// "  2 day
							myAccRow[12] = msgSearch.getE02ACMUL3();
							// " over 2 day
							myAccRow[13] = msgSearch.getE02ACMFL1();
							// Fed Float 1 day
							myAccRow[14] = msgSearch.getE02ACMFL2();
							// Fed Float 2 day
							myAccRow[15] = msgSearch.getE02DEVOFC();
							//msgSearch.getE01NEWRE4();  				// New Reason 4
							myAccRow[17] = ""; // No Action Operation Code
							myAccRow[18] = msgSearch.getE02DEVBNK(); // Bank
							//myAccRow[31] = msgSearch.getE02FULACC(); // full account
							myAccRow[31]= msgSearch.getE02SELACC();
							beanAccList.addRow(myFlag, myAccRow);
						}
						myRow[0] = msgSearch.getE02DEVACC(); // Account Number
						myRow[1] = msgSearch.getE02DEVNA1(); // Customer Name
						myRow[2] = Util.addLeftChar('0', 6, msgSearch.getE02DEVCHK()); // Check Number
						myRow[3] = msgSearch.getE02DEVAMT(); // Amount
						myRow[4] = msgSearch.getE02DEVFLG();
						// Flag or Status	
						myRow[5] = msgSearch.getE02DEVTCD(); // Trans Code
					//	myRow[6] = msgSearch.getE02DEVTDS();
					myRow[6] =
												msgSearch.getE02DEVRES()
													+ " "
													+ msgSearch.getE02DEVDS1();
						// T. Code Description
						myRow[7] = msgSearch.getE02DEVORG(); // Origin
						myRow[8] = msgSearch.getE02DEVOFC(); // Officer ****
						myRow[9] = msgSearch.getE02NUMREC(); // Record Number
						myRow[10] = "";
						myRow[11] = "";
						myRow[12] = "";
						myRow[13] = msgSearch.getE02DEVRES(); // Cause 1
						myRow[14] = msgSearch.getE02DEVRE2(); // Cause 2
						myRow[15] = msgSearch.getE02DEVRE3(); // Cause 3
						myRow[16] = msgSearch.getE02DEVRE4(); // Cause 4
						myRow[17] = ""; // No Action Operation Code
						myRow[18] = msgSearch.getE02DEVBNK(); // Bank
						myRow[19] = msgSearch.getE02DEVDMM(); // Trans Date
						myRow[20] = msgSearch.getE02DEVDDD(); // Trans date
						myRow[21] = msgSearch.getE02DEVDYY(); // Trans Date
						myRow[22] = msgSearch.getE02DEVREM(); // Comments
						myRow[27] = msgSearch.getE02DEVIMG();
						// IMG (debe ser 27!!!)
						myRow[28] = msgSearch.getE02DEVBRN();
						// branch
						//myRow[31] = msgSearch.getE02FULACC(); // full account 					
                        myRow[31]=msgSearch.getE02SELACC();
						beanList.addRow(myFlag, myRow);
						beanList.setLastRec(
							Integer.parseInt(msgSearch.getE02NUMREC()));

						if (marker.equals("+")) {
							beanAccList.setShowNext(true);
							break;
						}
					}

					newmessage = mc.receiveMessage();
				}

				try {
					if (dataCR.getMaxRow() == 0) {
						dataCR.addRow(
							beanList.getLastRec() + "",
							beanList.getFirstRec() + "");
					} else if (req.getParameter("FlagMov").equals("+")) {
						dataCR.setIndex(dataCR.getIndex() + 1);
						if (dataCR.getIndex() >= dataCR.getMaxRow()) {
							dataCR.addRow(
								beanList.getLastRec() + "",
								beanList.getFirstRec() + "");
						}
					}
				} catch (Exception e) {
				}

				if (dataCR.getIndex() < 1) {
					beanAccList.setShowPrev(false);
				} else {
					beanAccList.setShowPrev(true);
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("chkList", beanList);
				ses.setAttribute("accList", beanAccList);
				ses.setAttribute("dataCR", dataCR);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0924_rejection_chk_nsf_list.jsp");
					callPage(
						LangPath + "EDD0924_rejection_chk_nsf_list.jsp",
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

	protected void procReqPassword(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		DataCheckReject dataCR = null;

		try {
			userPO = new datapro.eibs.beans.UserPos();
			dataCR = new datapro.eibs.beans.DataCheckReject();

			dataCR.setOption(req.getParameter("opt"));
			if (dataCR.getOption() == null) dataCR.setOption(""); 
			dataCR.setBank(user.getE01UBK());
			dataCR.setCurrency(user.getE01BCU());

			if (dataCR.getOption().equals("CH")) {
				userPO.setRedirect(
					"/servlet/datapro.eibs.approval.JSEDD0924?SCREEN="
						+ R_ENTER_CLEARING);
			} else if (dataCR.getOption().equals("SR")) {
				userPO.setRedirect(
					"/servlet/datapro.eibs.approval.JSEDD0924?SCREEN="
						+ R_SUMMARYREPORT);
			} else if (dataCR.getOption().equals("MT")) {
				userPO.setRedirect(
					"/servlet/datapro.eibs.approval.JSEDD0924?SCREEN="
						+ R_MISSING_TAX_ID);
			} else if (dataCR.getOption().equals("CT")) {
				userPO.setRedirect(
					"/servlet/datapro.eibs.approval.JSEDD0924?SCREEN="
						+ R_CUSTOMER_TAX_ID_LISTING);
			} else if (dataCR.getOption().equals("SO")) {
				userPO.setRedirect(
					"/servlet/datapro.eibs.approval.JSEDD0924?SCREEN="
						+ R_SUMMARY_OFFICER);
			} else {
				userPO.setRedirect(
					"/servlet/datapro.eibs.approval.JSEDD0924?SCREEN="
						+ R_SEARCH);
			}

			ses.setAttribute("userPO", userPO);
			ses.setAttribute("dataCR", dataCR);
			res.sendRedirect(
				super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSummary(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD009001Message msgRT = null;
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
			msgRT = (EDD009001Message) mc.getMessageRecord("EDD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0002");
			try {
				if (req.getParameter("ACCNUM") != null)
					msgRT.setE01ACMACC(req.getParameter("ACCNUM"));
			} catch (Exception e) {
				msgRT.setE01ACMACC("0");
				flexLog(" error " + e);
			}
			msgRT.send();
			msgRT.destroy();
			flexLog("EDD009001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD009001")) {
				try {
					msgRT = new datapro.eibs.beans.EDD009001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD009001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					if (msgRT.getE01ACMACD().equals("04")) {
						userPO.setOption("SV");
					} else {
						userPO.setOption("RT");
					}
					userPO.setIdentifier(msgRT.getE01ACMACC());
					userPO.setBank(msgRT.getE01ACMBNK());
					userPO.setCusNum(msgRT.getE01ACMCUN());
					userPO.setHeader1(msgRT.getE01ACMPRO());
					userPO.setHeader2(msgRT.getE01ACMCCY());
					userPO.setHeader3(msgRT.getE01CUSNA1());

					ses.setAttribute("userPO", userPO);
					ses.setAttribute("rtRejAccInq", msgRT);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD0924_rejection_chk_inq_account.jsp");
						callPage(
							LangPath + "EDD0924_rejection_chk_inq_account.jsp",
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

	protected void procReqEnterClearing(
		MessageContext mc,
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
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "EDD0924_enter_user.jsp");
			callPage(LangPath + "EDD0924_enter_user.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	protected void procReqClearing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD094001Message msgCC = null;
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
			msgCC = (EDD094001Message) mc.getMessageRecord("EDD094001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("EDD0940");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0000");

			msgCC.send();
			msgCC.destroy();
			flexLog("EDD094001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDD094001")) {
				try {
					msgCC = new EDD094001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCC = (EDD094001Message) newmessage;

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0940_message_confirm.jsp");
					callPage(
						LangPath + "EDD0940_message_confirm.jsp",
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

	protected void procReqSummaryReport(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EREPORTSTDMessage msgCC = null;
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
			msgCC = (EREPORTSTDMessage) mc.getMessageRecord("EREPORTSTD");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("DD0943P");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0000");
			//msgCC.setE01REPORT("DD0943P");
			msgCC.setE01REPNME("DD0943P");

			msgCC.send();
			msgCC.destroy();
			flexLog("EREPORTSTD Message Sent");
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

			if (newmessage.getFormatName().equals("EREPORTSTD")) {
				try {
					msgCC = new EREPORTSTDMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCC = (EREPORTSTDMessage) newmessage;

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EREPORTSTD_message_confirm.jsp");
					callPage(
						LangPath + "EREPORTSTD_message_confirm.jsp",
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

	protected void procReqMissingTaxIDReport(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EREPORTSTDMessage msgCC = null;
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
			msgCC = (EREPORTSTDMessage) mc.getMessageRecord("EREPORTSTD");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("FR0200P");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0000");
			//msgCC.setE01REPORT("FR0200P");
			msgCC.setE01REPNME("FR0200P");

			msgCC.send();
			msgCC.destroy();
			flexLog("EREPORTSTD Message Sent");
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

			if (newmessage.getFormatName().equals("EREPORTSTD")) {
				try {
					msgCC = new EREPORTSTDMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCC = (EREPORTSTDMessage) newmessage;

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EREPORTSTD_message_confirm.jsp");
					callPage(
						LangPath + "EREPORTSTD_message_confirm.jsp",
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

	protected void procReqCustomerTaxIDListing(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EREPORTSTDMessage msgCC = null;
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
			msgCC = (EREPORTSTDMessage) mc.getMessageRecord("EREPORTSTD");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("FR0201P");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0000");
			//msgCC.setE01REPORT("FR0201P");
			msgCC.setE01REPNME("FR0201P");

			msgCC.send();
			msgCC.destroy();
			flexLog("EREPORTSTD Message Sent");
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

			if (newmessage.getFormatName().equals("EREPORTSTD")) {
				try {
					msgCC = new EREPORTSTDMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCC = (EREPORTSTDMessage) newmessage;

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EREPORTSTD_message_confirm.jsp");
					callPage(
						LangPath + "EREPORTSTD_message_confirm.jsp",
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

	protected void procReqSummaryOfficer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD0948DSMessage msgOfc = null;
		EDD0948DSMessage msgList = null;

		JBObjList beanList = null;
		UserPos userPO = null;
		//boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgOfc = (EDD0948DSMessage) mc.getMessageRecord("EDD0948DS");
			msgOfc.setH02USERID(user.getH01USR());
			msgOfc.setH02PROGRM("EDD0948");
			msgOfc.setH02TIMSYS(getTimeStamp());
			msgOfc.setH02SCRCOD("01");
			msgOfc.setH02OPECOD("0015");

			msgOfc.send();
			msgOfc.destroy();
			flexLog("EDD0948DS Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgOfc", msgOfc);

				try {
					flexLog(
						"About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDD0948DS")) {
				beanList = new JBObjList();
				String marker = "";
				msgOfc = null;

				while (true) {
					msgOfc = (EDD0948DSMessage) newmessage;
					marker = msgOfc.getH02FLGMAS();

					if (marker.equals("*")) {
						userPO.setHeader11(msgOfc.getE01DEVPAI());
						userPO.setHeader12(msgOfc.getE01DEVRET());
						userPO.setHeader13(msgOfc.getE01DEVTOI());
						userPO.setHeader14(msgOfc.getE01DEVTOR());
						userPO.setHeader15(msgOfc.getE01DEVOVD());
						userPO.setHeader16(msgOfc.getE01DEVUNC());
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgOfc);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				userPO.setHeader10("A");

				flexLog("Putting java beans into the session");
				ses.setAttribute("dvList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0948_officer_list.jsp");
					callPage(LangPath + "EDD0948_officer_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionSendMessage(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD0948DSMessage msgOfc = null;
		EDD0948DSMessage msgList = null;

		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgOfc = (EDD0948DSMessage) mc.getMessageRecord("EDD0948DS");
			msgOfc.setH02USERID(user.getH01USR());
			msgOfc.setH02PROGRM("EDD0948");
			msgOfc.setH02TIMSYS(getTimeStamp());
			msgOfc.setH02SCRCOD("01");
			msgOfc.setH02OPECOD("0010");

			try {
				msgOfc.setE01DEVOFC(req.getParameter("OFCCODE"));
			} catch (Exception e) {
			}
			try {
				msgOfc.setE01MSGTXT(req.getParameter("MESSAGE"));
			} catch (Exception e) {
			}

			msgOfc.send();
			msgOfc.destroy();
			flexLog("EDD0948DS Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0948_officer_list.jsp");
					callPage(LangPath + "EDD0948_officer_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	protected void procReqFilterOfficer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			userPO.setHeader10(req.getParameter("OPT"));
		} catch (Exception e) {
			userPO.setHeader10("A");
		}

		ses.setAttribute("userPO", userPO);
		try {
			flexLog(
				"About to call Page: " + LangPath + "EDD0948_officer_list.jsp");
			callPage(LangPath + "EDD0948_officer_list.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionChangeStatusOfficer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD092402Message msgSearch = null;
		ELEERRMessage msgError = null;
		JBListRec beanList = null;
		JBListRec beanAccList = null;
		DataCheckReject dataCR = null;
		boolean IsNotError = false;
		String Pos = "0";
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		dataCR =
			(datapro.eibs.beans.DataCheckReject) ses.getAttribute("dataCR");

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			msgSearch = (EDD092402Message) mc.getMessageRecord("EDD092402");
			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("EDD0924");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("01");
			if (userPO.getHeader17().equals("C")) {
				msgSearch.setH02OPECOD("0002");
			} else if (userPO.getHeader17().equals("O")) {
				msgSearch.setH02OPECOD("0003");
			}

			try {
				try {
					msgSearch.setE02SELBNK(dataCR.getBank());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE02SELCCY(dataCR.getCurrency());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE02SELBRN(dataCR.getBranch());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE02SELOFC(dataCR.getOfficer());
				} catch (Exception e) {
				}

				/*try {
					msgSearch.setE02SELACC(req.getParameter("ACCNUM"));
				} catch (Exception e) {
				}
				try {
					msgSearch.setE02NUMREC(Pos);
				} catch (Exception ex) {
				}*/

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD092402 Message Sent");

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
				IsNotError = msgError.getERRNUM().equals("0");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ super.srctx
							+ "/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=7");
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=7");

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

	protected void procReqNSFFromOfficer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		DataCheckReject dataCR = null;
		JBListRec beanList = null;

		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			dataCR = new datapro.eibs.beans.DataCheckReject();
			beanList = new datapro.eibs.beans.JBListRec();

			dataCR.setOption("NSF");
			dataCR.setBank(user.getE01UBK());
			dataCR.setCurrency(user.getE01BCU());
			try {
				dataCR.setOfficer(req.getParameter("E01SELOFC").toUpperCase());
			} catch (Exception e) {
			}
			dataCR.setCause("");
			try {
				dataCR.setBranch(req.getParameter("E01SELBRN").toUpperCase());
			} catch (Exception e) {
			}

			ses.setAttribute("userPO", userPO);
			ses.setAttribute("dataCR", dataCR);
			procReqNSF(mc, user, req, res, ses);

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

			int screen = R_SEARCH;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 29);
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
						case R_PASSWORD :
							procReqPassword(msgUser, req, res, session);
							break;
						case R_SEARCH :
							procReqSearch(msgUser, req, res, session);
							break;
						case R_UNPOSTED :
						case R_POSTED :
						case R_LARGE_ITEMS :
							procReqList(mc, msgUser, req, res, session);
							break;
						case R_SUMMARY :
							procReqSummary(mc, msgUser, req, res, session);
							break;
						case R_NSF :
							procReqNSF(mc, msgUser, req, res, session);
							break;
						case R_CLEARING :
							procReqClearing(mc, msgUser, req, res, session);
							break;
						case R_SUMMARY_OFFICER :
							procReqSummaryOfficer(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_ENTER_CLEARING :
							procReqEnterClearing(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_SUMMARYREPORT :
							procReqSummaryReport(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_FILTER_OFFICER :
							procReqFilterOfficer(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_OFFICER_NSF :
							procReqNSFFromOfficer(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_MISSING_TAX_ID :
							procReqMissingTaxIDReport(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_CUSTOMER_TAX_ID_LISTING :
							procReqCustomerTaxIDListing(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

							// Actions
						case A_SEARCH :
							procActionSearch(mc, msgUser, req, res, session);
							break;
						case A_RETURN_ITEMS :
							procActionList(mc, msgUser, req, res, session);
							break;
						case A_CHGSTS_OFFICER :
							procActionChangeStatusOfficer(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_SEND_MESSAGE :
							procActionSendMessage(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
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
}