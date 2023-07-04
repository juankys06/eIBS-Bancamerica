package datapro.eibs.transfer;

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

public class JSEPR2030 extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_LIST 	= 1;
	protected static final int R_SEARCH 	= 2;
	protected static final int A_SEARCH 	= 3;
	protected static final int R_PRINT 	= 4;
	protected static final int R_DESC 	= 5;

	// entering options
	protected static final int R_ENTER 	= 100;
	protected static final int A_ENTER 	= 200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEPR2030() {
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
	protected void procActionSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		userPO.setHeader7(req.getParameter("E01HISCYC"));
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
			userPO.setHeader15(req.getParameter("E01FRCHKN"));
		} catch (Exception e) {
			flexLog("E01FRCHKN");
		}
		try {
			userPO.setHeader16(req.getParameter("E01TOCHKN"));
		} catch (Exception e) {
			flexLog("E01TOCHKN");
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
		ELEERRMessage msgError = null;
		EPR203001Message msgSearch = null;
		EPR203001Message msgList = null;
		EPR203002Message msgHeader = null;
		JBList beanList = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EPR203001Message) mc.getMessageRecord("EPR203001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EPR2030");
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
				msgSearch.setE01HISCYC(userPO.getHeader7());
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
					msgSearch.setE01FRCHKN(userPO.getHeader15());
				} catch (Exception e) {
					flexLog("E01FRCHKN");
				}
				try {
					msgSearch.setE01TOCHKN(userPO.getHeader16());
				} catch (Exception e) {
					flexLog("E01TOCHKN");
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
			flexLog("EPR203001 Message Sent");
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
							+ "EPR2030_st_selection.jsp");
					callPage(LangPath + "EPR2030_st_selection.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EPR203002")) {
				try {
					msgHeader = new datapro.eibs.beans.EPR203002Message();
				} catch (Exception ex) {
					flexLog("EPR20302 Error: " + ex);
				}

				msgHeader = (EPR203002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("stBalances", msgHeader);

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EPR203001")) {

					try {
						beanList = new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("EPR203001 Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String grpData = "";
					String grpDate = "";
					String acnu = "";
					String checkgraph = "";

					String strDebit = "";
					String strCredit = "";
					String chknum = "";
					int scr = 0;
					String DT1 = "";
					String DT2 = "";
					String DT3 = "";
					java.math.BigDecimal debit = new java.math.BigDecimal(0);
					java.math.BigDecimal credit = new java.math.BigDecimal(0);

					while (true) {
	
						msgList = (EPR203001Message) newmessage;

						marker = msgList.getE01INDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								beanList.setFirstRec(
									Integer.parseInt(msgList.getE01NUMREC()));
								grpData = msgList.getE01ENDBAL();
								grpDate =
									Util.formatDate(
										msgList.getE01DATE21(),
										msgList.getE01DATE22(),
										msgList.getE01DATE23());
								if (posi == 0) {
									userPO.setHeader5(msgList.getE01BEGBAL());
								}
								chk = "checked";
							} else {
								chk = "";
								grpData =
									grpData + "|" + msgList.getE01ENDBAL();
								grpDate =
									grpDate
										+ "|"
										+ Util.formatDate(
											msgList.getE01DATE21(),
											msgList.getE01DATE22(),
											msgList.getE01DATE23());

							}

							if (msgList.getE01TRADCC().equals("0")) {
								debit =
									debit.add(msgList.getBigDecimalE01TRAAMT());
								strDebit =
									Util.fcolorCCY(msgList.getE01TRAAMT());
								strCredit = "&nbsp;";
							} else if (msgList.getE01TRADCC().equals("5")) {
								credit =
									credit.add(
										msgList.getBigDecimalE01TRAAMT());
								strDebit = "&nbsp;";
								strCredit =
									Util.fcolorCCY(msgList.getE01TRAAMT());
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

							try {
								chknum =
									Util.addLeftChar(
										'0',
										6,
										msgList.getE01TRACKN());
								acnu =
									Util.addLeftChar(
										'0',
										9,
										msgList.getE01NUMACC());
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}

							if (chknum.equals("0")) {
								checkgraph = " ";
							} else {
								scr++;
								String imgsrv =
									"http://172.26.16.10/vcheck/vcheck.aspx";
								checkgraph =
									"<A HREF=\"javascript:var newWindow=window.open('"
										+ imgsrv
										+ "?"
										+ acnu
										+ "?"
										+ chknum
										+ "','"
										+ scr
										+ "','height=550,width=700,top=5,left=5,Scrollbars=yes')\">"
										+ chknum
										+ "</A>";
							}

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
							} else {
								myRow.append(
									"<TD NOWRAP ALIGN=\"CENTER\">"
										+ Util.formatDate(
											msgList.getE01DATE11(),
											msgList.getE01DATE12(),
											msgList.getE01DATE13())
										+ "</TD>");
							}
							myRow.append(
								"<TD NOWRAP ALIGN=\"CENTER\">"
									+ Util.formatDate(
										msgList.getE01TRAPD1(),
										msgList.getE01TRAPD2(),
										msgList.getE01TRAPD3())
									+ "</TD>");

							if (msgList.getE01TRACDE().equals("CK")
								|| msgList.getE01TRACDE().equals("K5")) {
								myRow.append(
									"<TD NOWRAP ALIGN=\"RIGHT\">"
										+ checkgraph
										+ "</TD>");
							} else {
								myRow.append(
									"<TD NOWRAP ALIGN=\"RIGHT\"><a href=\"javascript:showTransfer('"
										+ msgList.getH01FLGWK3()
										+ Util.justifyRight(
											msgList.getE01TRACKN(),
											6)
										+ "')\">"
										+ msgList.getH01FLGWK3()
										+ " - "
										+ msgList.getE01TRACKN()
										+ "</a></TD>");
							}

							if (msgList.getE01TRACDE().equalsIgnoreCase("CK")
								|| msgList.getE01TRACDE().equalsIgnoreCase("OF")
								|| msgList.getE01TRACDE().equalsIgnoreCase(
									"DP")) {
								myRow.append(
									"<TD NOWRAP ALIGN=\"CENTER\">"
										+ checkgraph
										+ "</TD>");
							} else {
								myRow.append(
									"<TD NOWRAP ALIGN=\"CENTER\">"
										+ Util.formatCell(msgList.getE01TRACDE())
										+ "</TD>");
							}
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
									+ strCredit
									+ "</TD>");
							
							myRow.append(
								"<TD NOWRAP ALIGN=CENTER><A HREF=\"javascript:GetBatchDet('"
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
							if (userPO.getHeader8().equals("V")) {
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
							} else {
								myRow.append(
									"<TD NOWRAP ALIGN=CENTER>"
										+ Util.formatDate(
											msgList.getE01DATE21(),
											msgList.getE01DATE22(),
											msgList.getE01DATE23())
										+ "</TD>");
							}
							
							myRow.append("</TR>");
							beanList.addRow(myFlag, myRow.toString());

							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}

						newmessage = mc.receiveMessage();
					}

					userPO.setHeader19(Util.fcolorCCY(debit.toString()));
					userPO.setHeader20(Util.fcolorCCY(credit.toString()));
					userPO.setHeader21(grpData);
					userPO.setHeader22(grpDate);

					flexLog("Putting java beans into the session");
					ses.setAttribute("cifList", beanList);

					try {
						if (msgList.getE01VALBTH().equals("V")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EPR2030_st_list_fv.jsp");
							callPage(
								LangPath + "EPR2030_st_list_fv.jsp",
								req,
								res);
						} else if (msgList.getE01VALBTH().equals("B")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EPR2030_st_list_fp.jsp");
							callPage(
								LangPath + "EPR2030_st_list_fp.jsp",
								req,
								res);
						}
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
	protected void procReqSearch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
		UserPos userPO = (UserPos) ses.getAttribute("userPO");
		ELEERRMessage msgError = new ELEERRMessage();
		EDD009001Message msgRT = null;
		MessageRecord newmessage = null;
		boolean IsNotError = false;
		int row = Integer.parseInt(req.getParameter("ROW"));
 		JBObjList prList = (JBObjList) ses.getAttribute("prList");
    	prList.setCurrentRow(row);
		EPR200001Message prBasic = (EPR200001Message) prList.getRecord();
		userPO.setIdentifier(prBasic.getE01PRPCAC());
		
		// Send Initial data
		try {
			msgRT = (EDD009001Message) mc.getMessageRecord("EDD009001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD0000");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0002");			
			msgRT.setE01ACMACC(userPO.getIdentifier());
			msgRT.send();
			msgRT.destroy();
			flexLog("EDD09001 Message Sent");
		
		// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		
		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD009001")) {
				
				msgRT = (EDD009001Message) newmessage;

				if (IsNotError) { // There are no errors
					try {
						userPO.setIdentifier(msgRT.getE01ACMACC());
						userPO.setHeader1(msgRT.getE01ACMPRO());
						userPO.setCusNum(msgRT.getE01ACMCUN());
						userPO.setHeader2(msgRT.getE01ACMCUN());
						userPO.setHeader3(msgRT.getE01CUSNA1());
						userPO.setCurrency(msgRT.getE01ACMCCY());
						userPO.setOfficer(msgRT.getE01ACMOFC()								+ " - "
								+ msgRT.getE01DSCOFC());

						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("userPO", userPO);

						flexLog("Calling Request");
						try {
								flexLog("About to call Page: " + LangPath + "EPR2030_st_selection.jsp");
								callPage(LangPath + "EPR2030_st_selection.jsp", req, res);
						} catch (Exception e) {
								e.printStackTrace();
								flexLog("Exception calling page " + e);
						}


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
						case R_SEARCH :
							procReqSearch(mc,msgUser, req, res, session);
							break;
						case A_SEARCH :
							procActionSearch(mc, msgUser, req, res, session);
							break;
						case R_LIST :
							procReqList(mc, msgUser, req, res, session);
							break;
							//entering options
						default :
							res.sendRedirect(super.srctx +LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
			}

		}

	}
	
}