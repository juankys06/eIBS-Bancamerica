package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (8/19/04 04:23:45 PM)
 * @author: Antonio Blanco
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSECIF090 extends datapro.eibs.master.SuperServlet {

	protected static final int R_SEARCH = 100;
	protected static final int A_SEARCH = 200;
	protected static final int A_SEARCH2 = 201;
	protected static final int A_SEARCH3 = 202;
	protected static final int A_PROD = 300;
	protected static final int A_LOANS_STS = 400;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECIF090() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECIF090");

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
	protected void procReqSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ECIF09001Message msgSearch = new ECIF09001Message();
		JBObjList beanList = null;

		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("mtListAgent", beanList);
		// gvalle

		UserPos userPOLevel = null;
		userPOLevel = new datapro.eibs.beans.UserPos();
		ses.setAttribute("userPOLevel",userPOLevel);
		userPOLevel.setBank(user.getE01UBK());
		userPOLevel.setBranch(user.getE01UBR());
 		userPOLevel.setHeader22(200+user.getE01RDY());
    	userPOLevel.setHeader23(user.getE01RDM());
		
		userPOLevel.setHeader1("");
		userPOLevel.setHeader2("");
		userPOLevel.setHeader3("");
		userPOLevel.setHeader4("");
		userPOLevel.setHeader5("");
		userPOLevel.setHeader6("");
		userPOLevel.setHeader7("");
		userPOLevel.setHeader8("");
		userPOLevel.setHeader9("");
		userPOLevel.setHeader10("");
		//	userPOLevel.setHeader18("");
		//	userPOLevel.setHeader19("");
		//	userPOLevel.setHeader22("");
		//	userPOLevel.setHeader23("");
		try {

			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ECIF090_client_agent_inq.jsp");
			callPage(LangPath + "ECIF090_client_agent_inq.jsp", req, res);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
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

		boolean ExitFlag = false;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECIF09001Message msgSearch = null;
		ECIF09002Message msgList = null;

		JBObjList beanList = null;
		UserPos userPOLevel = null;
		boolean IsNotError = false;

		userPOLevel = (datapro.eibs.beans.UserPos) ses.getAttribute("userPOLevel");
		beanList = (JBObjList) ses.getAttribute("mtListAgent");
		int screen = 0;
		try {
			screen = Integer.parseInt(req.getParameter("SCREEN"));
		} catch (Exception e) {
			flexLog("Screen set to default value");
		}

		if ((beanList == null)
			|| (screen == A_SEARCH2)
			|| (screen == A_SEARCH3)) {
			int posi = 0;
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgSearch = (ECIF09001Message) mc.getMessageRecord("ECIF09001");
				msgSearch.setH01USERID(user.getH01USR());
				msgSearch.setH01PROGRM("ECIF090");
				msgSearch.setH01TIMSYS(getTimeStamp());
				msgSearch.setH01SCRCOD("01");
				msgSearch.setH01OPECOD("0015");

				//all the fields here
				java.util.Enumeration enu = msgSearch.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while (enu.hasMoreElements()) {
					field = (MessageField) enu.nextElement();
					try {
						value =
							req
								.getParameter(field.getTag())
								.toUpperCase()
								.trim();
						if (value != null) {
							field.setString(value);
						}
					} catch (Exception e) {
					}
				}

				//				retrieve previous officer
				//				all the fields here
				if (screen == A_SEARCH3) {

					if (userPOLevel.getHeader10() != "") {
						msgSearch.setE01INQOFC(userPOLevel.getHeader9());
						userPOLevel.setHeader10("");
					} else {
						if (userPOLevel.getHeader9() != "") {
							msgSearch.setE01INQOFC(userPOLevel.getHeader8());
							userPOLevel.setHeader9("");
						} else {
							if (userPOLevel.getHeader8() != "") {
								msgSearch.setE01INQOFC(userPOLevel.getHeader7());
								userPOLevel.setHeader8("");
							} else {
								if (userPOLevel.getHeader7() != "") {
									msgSearch.setE01INQOFC(userPOLevel.getHeader6());
									userPOLevel.setHeader7("");
								} else {
									if (userPOLevel.getHeader6() != "") {
										msgSearch.setE01INQOFC(
											userPOLevel.getHeader5());
										userPOLevel.setHeader6("");
									} else {
										if (userPOLevel.getHeader5() != "") {
											msgSearch.setE01INQOFC(
												userPOLevel.getHeader4());
											userPOLevel.setHeader5("");
										} else {
											if (userPOLevel.getHeader4() != "") {
												msgSearch.setE01INQOFC(
													userPOLevel.getHeader3());
												userPOLevel.setHeader4("");
											} else {
												if (userPOLevel.getHeader3()
													!= "") {
													msgSearch.setE01INQOFC(
														userPOLevel.getHeader2());
													userPOLevel.setHeader3("");
												} else {
													if (userPOLevel.getHeader2()
														!= "") {
														msgSearch.setE01INQOFC(
															userPOLevel
																.getHeader1());
														userPOLevel.setHeader2("");
													} else {
														if (userPOLevel.getHeader1()
															!= "") {
															userPOLevel.setHeader1(
																"");
															//			userPOLevel.setHeader2("");
															//			userPOLevel.setHeader3("");
															//			userPOLevel.setHeader4("");
															//			userPOLevel.setHeader5("");
															//			userPOLevel.setHeader6("");
															//			userPOLevel.setHeader7("");
															//			userPOLevel.setHeader8("");
															//			userPOLevel.setHeader9("");
															//			userPOLevel.setHeader10("");
															//			userPOLevel.setHeader18("");
															//			userPOLevel.setHeader19("");
															//			userPOLevel.setHeader22("");
															//			userPOLevel.setHeader23("");
															//			ses.setAttribute(
															//				"msgMT",
															//				null);
															//			ses.setAttribute(
															//				"mtListLvl",
															//				null);
															//			ses.setAttribute(
															//				"msgMTLvl",
															//				null);
															//			callPage(
															//				LangPath
															//					+ "ECIF090_client_agent_inq.jsp",
															//				req,
															//				res);
															procReqSearch(
																user,
																req,
																res,
																ses);
															ExitFlag = true;
														} else {
															procReqSearch(
																user,
																req,
																res,
																ses);
															ExitFlag = true;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				// retrieve previous officer

				if (!ExitFlag) {

					msgSearch.send();
					msgSearch.destroy();
					flexLog("ECIF09001 Message Sent");

					// Receive Message

					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("ELEERR")) {

						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						ses.setAttribute("error", msgError);
					} else
						flexLog(
							"Message "
								+ newmessage.getFormatName()
								+ " received.");

					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("ECIF09002")) {

						beanList = new JBObjList();
						String marker = "";
						boolean firstRec = true;

						while (true) {

							msgList = (ECIF09002Message) newmessage;

							marker = msgList.getH02FLGMAS();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {
								if (firstRec) {
									//sets type and subtype as headers
									userPOLevel.setHeader18(msgList.getE02INQBNK());
									userPOLevel.setHeader19(msgList.getE02INQBNN());
									userPOLevel.setHeader22(msgList.getE02INQRDY());
									userPOLevel.setHeader23(msgList.getE02INQRDM());
									firstRec = false;
									// Save officer Information
									if (screen == A_SEARCH2
										|| screen == A_SEARCH) {

										if (userPOLevel.getHeader1().equals("")) {
											userPOLevel.setHeader1(
												msgList.getE02INQOFC());
										} else {
											if (userPOLevel
												.getHeader2()
												.equals("")) {
												userPOLevel.setHeader2(
													msgList.getE02INQOFC());
											} else {
												if (userPOLevel
													.getHeader3()
													.equals("")) {
													userPOLevel.setHeader3(
														msgList.getE02INQOFC());
												} else {
													if (userPOLevel
														.getHeader4()
														.equals("")) {
														userPOLevel.setHeader4(
															msgList
																.getE02INQOFC());
													} else {
														if (userPOLevel
															.getHeader5()
															.equals("")) {
															userPOLevel.setHeader5(
																msgList
																	.getE02INQOFC());
														} else {
															if (userPOLevel
																.getHeader6()
																.equals("")) {
																userPOLevel
																	.setHeader6(
																	msgList
																		.getE02INQOFC());
															} else {
																if (userPOLevel
																	.getHeader7()
																	.equals("")) {
																	userPOLevel
																		.setHeader7(
																		msgList
																			.getE02INQOFC());
																} else {
																	if (userPOLevel
																		.getHeader8()
																		.equals("")) {
																		userPOLevel
																			.setHeader8(
																			msgList
																				.getE02INQOFC());
																	} else {
																		if (userPOLevel
																			.getHeader9()
																			.equals("")) {
																			userPOLevel
																				.setHeader9(
																				msgList
																					.getE02INQOFC());
																		} else {
																			if (userPOLevel
																				.getHeader10()
																				.equals("")) {
																				userPOLevel
																					.setHeader10(
																					msgList
																						.getE02INQOFC());
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}

									}
									//

								}

								beanList.addRow(msgList);
								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						ses.setAttribute("mtListAgent", beanList);
						ses.setAttribute("userPOLevel", userPOLevel);

						try {
							if (IsNotError) { // There are no errors
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "ECIF090_client_agent_list.jsp");
								callPage(
									LangPath + "ECIF090_client_agent_list.jsp",
									req,
									res);
							} else {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "ECIF090_client_agent_inq.jsp");
								callPage(
									LangPath + "ECIF090_client_agent_inq.jsp",
									req,
									res);
							}
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (
						newmessage.getFormatName().equals("ECIF09001")) {

						msgSearch = (ECIF09001Message) newmessage;
						ses.setAttribute("msgMT", msgSearch);
						ses.setAttribute("userPOLevel", userPOLevel);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ECIF090_client_agent_inq.jsp");
							callPage(
								LangPath + "ECIF090_client_agent_inq.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						flexLog(
							"Message "
								+ newmessage.getFormatName()
								+ " received.");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		} else {
			flexLog("Putting java beans into the session");
			ses.setAttribute("mtList", beanList);
			ses.setAttribute("userPOLevel", userPOLevel);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ECIF090_client_agent_list.jsp");
				callPage(LangPath + "ECIF090_client_agent_list.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqListProd(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECIF09002Message msgSearch = null;
		ECIF09003Message msgList = null;

		JBObjList beanList = null;
		UserPos userPOLevel = null;
		JBObjList bl = null;
		boolean IsNotError = true;

		userPOLevel = (datapro.eibs.beans.UserPos) ses.getAttribute("userPOLevel");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			bl = (JBObjList) ses.getAttribute("mtListAgent");

			int idx = Integer.parseInt(req.getParameter("CURRCODE"));
			bl.setCurrentRow(idx);

			msgSearch = (ECIF09002Message) bl.getRecord();

			msgSearch.setH02USERID(user.getH01USR());
			msgSearch.setH02PROGRM("ECIF090");
			msgSearch.setH02TIMSYS(getTimeStamp());
			msgSearch.setH02SCRCOD("01");
			msgSearch.setH02OPECOD("0015");

			mc.sendMessage(msgSearch);
			//msgSearch.destroy();
			flexLog("ECIF09002 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECIF09003")) {

				beanList = new JBObjList();
				String marker = "";
				boolean firstRec = true;

				while (true) {

					msgList = (ECIF09003Message) newmessage;

					marker = msgList.getH03FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstRec) {
							//sets type and subtype as headers
							userPOLevel.setHeader18(msgList.getE03INQBNK());
							userPOLevel.setHeader19(msgList.getE03INQBNN());
							userPOLevel.setHeader20(msgList.getE03INQOFC());
							userPOLevel.setHeader21(msgList.getE03INQOFN());
							userPOLevel.setHeader22(msgList.getE03INQRDY());
							userPOLevel.setHeader23(msgList.getE03INQRDM());
							firstRec = false;
						}

						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("mtListAgent", bl);
				ses.setAttribute("mtListProd", beanList);
				ses.setAttribute("userPOLevel", userPOLevel);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ECIF090_client_product_list.jsp");
					callPage(
						LangPath + "ECIF090_client_product_list.jsp",
						req,
						res);
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procLoansSts(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECIF09004Message msgLNS = null;
		ELEERRMessage msgError = null;
		UserPos userPOLevel = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPOLevel = (datapro.eibs.beans.UserPos) ses.getAttribute("userPOLevel");

		// Send Initial data
		try {
			msgLNS = (ECIF09004Message) mc.getMessageRecord("ECIF09004");
			msgLNS.setH04USERID(user.getH01USR());
			msgLNS.setH04PROGRM("ECIF090");
			flexLog("header 4 timestamp = " + getTimeStamp());
			msgLNS.setH04TIMSYS(getTimeStamp());
			msgLNS.setH04SCRCOD("01");
			msgLNS.setH04OPECOD("0004");
			msgLNS.setE04LNSBNK(userPOLevel.getHeader18());
			msgLNS.setE04LNSBNN(userPOLevel.getHeader19());
			msgLNS.setE04LNSRDY(userPOLevel.getHeader22());
			msgLNS.setE04LNSRDM(userPOLevel.getHeader23());
			msgLNS.setE04LNSOFC(req.getParameter("E01INQOFC"));
			
		flexLog("Send command");
		msgLNS.send();
		flexLog("Destroy command");
		msgLNS.destroy();
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	//Receive Error Message
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {
			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgError = (ELEERRMessage) newmessage;
			showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ECIF090_client_loans_sts.jsp");
				callPage(LangPath + "ECIF090_client_loans_sts.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} else if (newmessage.getFormatName().equals("ECIF09004")) {
			try {
				msgLNS = new datapro.eibs.beans.ECIF09004Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgLNS = (ECIF09004Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("msgMT", msgLNS);
			ses.setAttribute("userPOLevel", userPOLevel);
			ses.setAttribute("error", msgError);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ECIF090_client_loans_sts.jsp");
				callPage(LangPath + "ECIF090_client_loans_sts.jsp", req, res);
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
				s = new Socket(super.hostIP, super.iniSocket + 1);
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
						procReqSearch(msgUser, req, res, session);
						break;
					case A_SEARCH :
						procReqList(mc, msgUser, req, res, session);
						break;
					case A_SEARCH2 :
						procReqList(mc, msgUser, req, res, session);
						break;
					case A_SEARCH3 :
						procReqList(mc, msgUser, req, res, session);
						break;
					case A_PROD :
						procReqListProd(mc, msgUser, req, res, session);
						break;
					case A_LOANS_STS :
						procLoansSts(mc, msgUser, req, res, session);
						break;

					default :
						res.sendRedirect(
							super.srctx + LangPath + super.devPage);
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				int sck = super.iniSocket + 1;
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