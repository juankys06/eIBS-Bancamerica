package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (11/5/02 6:08:55 PM) x
 * @author: Ramses Amaro
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

import datapro.eibs.beans.EDL081001Message;
import datapro.eibs.beans.EDL081002Message;
import datapro.eibs.beans.EDL081003Message;
import datapro.eibs.beans.EDL081004Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000015Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0810 extends datapro.eibs.master.SuperServlet {

	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int R_ENTER_MAINT = 300;
	protected static final int A_ENTER_NEW = 200;
	protected static final int A_ENTER_MAINT = 400;
	// 
	protected static final int A_LIST_DRAFT = 1;
	protected static final int R_LIST_DRAFT = 2;
	protected static final int A_DRAFT_DET = 3;
	protected static final int A_COLLECTION = 4;
	protected static final int A_ENTER_ACC_MAINT = 600;
	protected static final int A_MAINT_TO_SCK = 502;
	protected static final int A_ENTER_GEN_DOCS = 1000;
	protected static final int A_ENTER_DOCUMENT = 800;
	protected static final int R_ENTER_UPLOAD = 1200;
	protected static final int A_UPLOAD = 1300;
	protected static final int A_VALIDATE = 1400;
	protected static final int R_CREATE = 1500;
	protected static final int A_CREATE = 1600;
	protected static final int R_INQUIRY = 1700;
	protected static final int A_INQUIRY = 1800;
	protected static final int R_ENTER_DELETE = 1900;
	protected static final int A_ENTER_DELETE = 2000;
	protected static final int A_DELETE = 2200;

	protected static final int A_MAINT_ACEP = 3100;
	protected static final int R_MAINT_ACEP = 3200;
	protected static final int R_MAINT_ACTION = 3300;
	private String LangPath = "S";
	private ServletConfig config = null;

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0810() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0810");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081001Message collBasic = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError =
				(ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			collBasic = (EDL081001Message) mc.getMessageRecord("EDL081001");
			collBasic.setH01USERID(user.getH01USR());
			collBasic.setH01PROGRM("EDL0810");
			collBasic.setH01TIMSYS(getTimeStamp());
			collBasic.setH01SCRCOD("01");
			collBasic.setH01OPECOD("0002");
			try {
				collBasic.setE01DLHNRO(req.getParameter("E01DLHNRO"));
			} catch (Exception e) {
			}

			collBasic.send();

			flexLog("EDL081001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL081001")) {

				collBasic = (EDL081001Message) newmessage;
				userPO.setIdentifier(collBasic.getE01DLHNRO());
				userPO.setAccNum("");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				try {
					if (IsNotError) { // There are no errors EDL081001
						if (procReqListDraft(mc,
							user,
							req,
							res,
							ses)) { // There are no errors EDL081002							
							userPO.setPurpose("MAINTENANCE");
							userPO.setIdentifier(collBasic.getE01DLHNRO());
							userPO.setHeader1(collBasic.getE01DLHPRD());
							userPO.setHeader2(collBasic.getE01DLHCUN());
							userPO.setHeader3(collBasic.getE01CUSNA1());
							userPO.setHeader4(collBasic.getE01DSCPRO());
							userPO.setCurrency(collBasic.getE01DLHCCY());
							userPO.setBank(collBasic.getE01DLHOBK());
							userPO.setBranch(collBasic.getE01DLHOBR());
							ses.setAttribute("userPO", userPO);
							ses.setAttribute("collBasic", collBasic);
							try {
								flexLog(
									"About to redirect : "
										+ LangPath
										+ "EDL0810_collection_list.jsp");
								callPage(
									LangPath + "EDL0810_collection_list.jsp",
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
										+ "EDL0810_collection_enter_maint.jsp");
								callPage(
									LangPath
										+ "EDL0810_collection_enter_maint.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_collection_enter_maint.jsp");
							callPage(
								LangPath + "EDL0810_collection_enter_maint.jsp",
								req,
								res);
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
		EDL081001Message collBasic = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		flexLog("Initializing java beans into the session");
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			collBasic = (EDL081001Message) mc.getMessageRecord("EDL081001");
			collBasic.setH01USERID(user.getH01USR());
			collBasic.setH01PROGRM("EDL0810");
			collBasic.setH01TIMSYS(getTimeStamp());
			collBasic.setH01SCRCOD("01");
			collBasic.setH01OPECOD("0001");
			//collBasic.setE01DEAACD("10");
			try {
				collBasic.setE01DLHPRD(req.getParameter("E01DLHPRD"));
			} catch (Exception e) {
				collBasic.setE01DLHPRD("0");
			}
			try {
				collBasic.setE01DLHNRO(req.getParameter("E01DLHNRO"));
			} catch (Exception e) {
			}
			collBasic.send();
			collBasic.destroy();
			flexLog("EDL081001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			// Receive Error Message
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

			if (newmessage.getFormatName().equals("EDL081001")) {
				try {
					collBasic =
						(
							datapro
								.eibs
								.beans
								.EDL081001Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.EDL081001Message");
					flexLog("EDL081001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				collBasic = (EDL081001Message) newmessage;

				flexLog("Putting java beans into the session");
				userPO.setPurpose("NEW");

				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					userPO.setPurpose("NEW");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("collBasic", collBasic);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0810_collection_basic.jsp");
						callPage(
							LangPath + "EDL0810_collection_basic.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: /servlet/datapro.eibs.products.JSESD0711?TYPE=10");
						res.sendRedirect(
							"/servlet/datapro.eibs.products.JSESD0711?TYPE=10&GENERIC=V");
						//					String firstLink =
						//						super.webAppPath
						//							+ LangPath
						//							+ "ESD0711_products_detail.jsp?appcode="
						//							+ req.getParameter("appcode").trim()
						//							+ "&typecode="
						//							+ req.getParameter("typecode").trim()
						//							+ "&generic="
						//							+ req.getParameter("generic").trim()
						//							+ "&title="
						//							+ req.getParameter("title").trim()
						//							+ "&bank="
						//							+ req.getParameter("bank").trim();
						//					res.setContentType("text/html");
						//					PrintWriter out = res.getWriter();
						//					out.println("<!-- frames -->");
						//					out.println("<frameset  rows=\"30%,*\">");
						//					out.println(
						//						"<frame name=\"list\" src=\""
						//							+ super.webAppPath
						//							+ LangPath
						//							+ "ESD0711_products_tree_view.htm\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"no\" frameborder=\"NO\">");
						//					out.println(
						//						"<frame name=\"detail\" src=\""
						//							+ firstLink
						//							+ "\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"auto\" frameborder=\"NO\">");
						//					out.println("</frameset>");
						//					out.close();
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
	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");
			userPO.setOption("LN");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0810_collection_enter_maint.jsp");
			callPage(LangPath + "EDL0810_collection_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqEnterNew(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");
			userPO.setOption("LN");
			userPO.setPurpose("NEW");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0810_collection_enter_maint.jsp");
			callPage(LangPath + "EDL0810_collection_enter_new.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected boolean procReqListDraft(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081002Message dftBasic = null;
		JBObjList dftList = null;
		JBListRec lstAcceptors = new JBListRec();
		JBListRec lstDocuments = new JBListRec();
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		String idAcceptor = "";
		String nameAcceptor = "";
		String AcceptorNDA = "";
		String DDIB = "";
		BigDecimal totalAcceptorDocs;

		try {
			msgError =
				(ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		// Send Initial data
		try {
			dftBasic = (EDL081002Message) mc.getMessageRecord("EDL081002");
			dftBasic.setH02USERID(user.getH01USR());
			dftBasic.setH02PROGRM("EDL0810");
			dftBasic.setH02TIMSYS(getTimeStamp());
			dftBasic.setH02SCRCOD("01");
			dftBasic.setH02OPECOD("0015");
			try {
				dftBasic.setE02DLDNRO(userPO.getIdentifier());
			} catch (Exception e) {
				dftBasic.setE02DLDNRO("0");
			}
			try {
				dftBasic.setE02DLDIDA(userPO.getAccNum());
			} catch (Exception e) {
				dftBasic.setE02DLDIDA("");
			}
			dftBasic.send();
			dftBasic.destroy();
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
			idAcceptor = "";
			nameAcceptor = "";
			totalAcceptorDocs = new BigDecimal("0");
			AcceptorNDA = "";
			DDIB= "";
			newmessage = mc.receiveMessage();
			dftBasic = (EDL081002Message) newmessage;

			boolean endList =(dftBasic.getH02FLGMAS().trim().equals("*") ? true : false);

			if (!endList) {
				idAcceptor = dftBasic.getE02DLDIDA();
				nameAcceptor = dftBasic.getE02ACPNME();
				AcceptorNDA = dftBasic.getE02DLDNDA();
				DDIB= dftBasic.getE02DLDDIB();
				totalAcceptorDocs = dftBasic.getBigDecimalE02DLDAMT();
			}

			lstAcceptors.init(5);
			lstDocuments.init(10);
			String myRow[] = new String[5];
			String myRow2[] = new String[10];
			for (int i = 0; i < 5; i++) {
				myRow[i] = "";
			}
			for (int i = 0; i < 10; i++) {
				myRow2[i] = "";
			}
			boolean firstTime = true;
			BigDecimal totalDocs = new BigDecimal("0");
			if (newmessage.getFormatName().equals("EDL081002")) {
				flexLog("EDL081002 Message Received");
				String marker = "";
				dftList = new JBObjList();
				while (true) {

					dftBasic = (EDL081002Message) newmessage;

					marker = dftBasic.getH02FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						dftList.addRow(dftBasic);
						totalDocs =
							totalDocs.add(dftBasic.getBigDecimalE02DLDAMT());

						myRow2[0] = dftBasic.getE02DLDNRI();
						myRow2[1] = dftBasic.getE02DLDFV1();
						myRow2[2] = dftBasic.getE02DLDFV2();
						myRow2[3] = dftBasic.getE02DLDFV3();
						myRow2[4] = dftBasic.getE02DLDAMT();
						//myRow2[5] = dftBasic.getE02DLDARC();
						myRow2[5] = "";
						//myRow2[6] = dftBasic.getE02DLDACC();
						myRow2[6] = dftBasic.getE01DLDDAC();
						//myRow2[7] = dftBasic.getE02DLDEXT();
						myRow2[7] = "";
						//id of acceptant
						myRow2[8] = dftBasic.getE02DLDIDA();
						//inst cob
						//myRow2[9] = dftBasic.getE02DLDCOI();
						myRow2[9] = dftBasic.getE02DLDDIB();

						lstDocuments.addRow("", myRow2);
					}

					if (idAcceptor.trim().equals(dftBasic.getE02DLDIDA().trim())) {
						if (firstTime) {
							totalAcceptorDocs = dftBasic.getBigDecimalE02DLDAMT();
							firstTime = false;
						} else {
							totalAcceptorDocs =totalAcceptorDocs.add(dftBasic.getBigDecimalE02DLDAMT());
						}
					} else {
						myRow[0] = idAcceptor;
						myRow[1] = nameAcceptor;
						myRow[2] = totalAcceptorDocs.toString();
						myRow[3] = AcceptorNDA;
						myRow[4] = DDIB;

						lstAcceptors.addRow("", myRow);
						idAcceptor = dftBasic.getE02DLDIDA();
						nameAcceptor = dftBasic.getE02ACPNME();
						AcceptorNDA = dftBasic.getE02DLDNDA();
						DDIB=dftBasic.getE02DLDDIB();
						
						
						totalAcceptorDocs = dftBasic.getBigDecimalE02DLDAMT();
					}
					newmessage = mc.receiveMessage();
					dftBasic = (EDL081002Message) newmessage;

					marker = dftBasic.getH02FLGMAS();
					if (marker.equals("*")) {
						myRow[0] = idAcceptor;
						myRow[1] = nameAcceptor;
						myRow[2] = totalAcceptorDocs.toString();
						myRow[3] = AcceptorNDA;
						myRow[4] = DDIB;
						lstAcceptors.addRow("", myRow);
					}
				}

				ses.setAttribute("dftList", dftList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("lstAcceptors", lstAcceptors);
				ses.setAttribute("lstDocuments", lstDocuments);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		return IsNotError;
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionListDraft(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081002Message dftBasic = null;
		JBObjList dftList = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		dftList = (JBObjList) ses.getAttribute("dftList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("OPT"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
				row = 0;
			}

			switch (option) {

				case 3 : // Delete
					dftList.setCurrentRow(row);
					try {
						dftBasic = (EDL081002Message) dftList.getRecord();
						dftBasic.setH02USERID(user.getH01USR());
						dftBasic.setH02PROGRM("EDL0810");
						dftBasic.setH02TIMSYS(getTimeStamp());
						dftBasic.setH02SCRCOD("01");
						dftBasic.setH02OPECOD("0009");

						mc.sendMessage(dftBasic);
						//msgAval.destroy();
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
								if (procReqListDraft(mc,
									user,
									req,
									res,
									ses)) { // There are no errors EDL081002							
									try {
										flexLog(
											"About to redirect : "
												+ LangPath
												+ "EDL0810_collection_list.jsp");
										callPage(
											LangPath
												+ "EDL0810_collection_list.jsp",
											req,
											res);
									} catch (Exception e) {
										flexLog("Exception calling page " + e);
									}
								} else { // There are errors
									try {
										flexLog("Putting java beans into the session");
										res.sendRedirect(
											LangPath
												+ "EDL0810_collection_list.jsp?ROW="
												+ row);
									} catch (Exception e) {
										flexLog("Exception calling page " + e);
									}
								}
							} else {
								try {
									ses.setAttribute("error", msgError);
									flexLog("Putting java beans into the session");
									res.sendRedirect(
										LangPath
											+ "EDL0810_collection_list.jsp?ROW="
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
					try {
						dftBasic =
							(EDL081002Message) Beans.instantiate(
								getClass().getClassLoader(),
								"datapro.eibs.beans.EDL081002Message");
						dftBasic.setE02DLDNRO(userPO.getIdentifier());
					} catch (Exception e) {
						flexLog("Error: " + e);
					}
					ses.setAttribute("dftBasic", dftBasic);
					res.sendRedirect(LangPath + "EDL0810_draft_det.jsp");
					break;

				case 2 : // Maintenance
					dftList.setCurrentRow(row);
					try {
						dftBasic = (EDL081002Message) dftList.getRecord();
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
					ses.setAttribute("dftBasic", dftBasic);
					res.sendRedirect(LangPath + "EDL0810_draft_det.jsp");
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
	protected void procActionDraftDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081002Message dftBasic = null;
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
			flexLog("Send Initial Data");
			dftBasic = (EDL081002Message) mc.getMessageRecord("EDL081002");
			dftBasic.setH02USERID(user.getH01USR());
			dftBasic.setH02PROGRM("EDL0810");
			dftBasic.setH02TIMSYS(getTimeStamp());
			dftBasic.setH02SCRCOD("01");
			dftBasic.setH02OPECOD("0005");
			//dftBasic.setE02PAGFLG("CW");
			// all the fields here
			java.util.Enumeration enu = dftBasic.fieldEnumeration();
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

			dftBasic.send();
			dftBasic.destroy();

			flexLog("EDL081002 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL081002")) {
				try {
					dftBasic =
						(
							datapro
								.eibs
								.beans
								.EDL081002Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.EDL081002Message");
					flexLog("EDL081002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				dftBasic = (EDL081002Message) newmessage;

				flexLog("Putting java beans into the session");

				//ses.setAttribute("userPO", userPO);
				//ses.setAttribute("error", msgError);
				ses.removeAttribute("dftBasic");

				if (IsNotError) { // There are no errors
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					if (procReqListDraft(mc, user, req, res, ses)) {
						out.println("<HTML>");
						out.println("<HEAD>");
						out.println("<TITLE>Close</TITLE>");
						out.println("</HEAD>");
						out.println("<BODY>");
						out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
						out.println(
							"		top.opener.location.href='"
								+ LangPath
								+ "EDL0810_collection_list.jsp';");
						out.println("       top.close();");
						out.println("</SCRIPT>");
						out.println("</BODY>");
						out.println("</HTML>");
					} else {
						out.println("<HTML>");
						out.println("<HEAD>");
						out.println("<TITLE>Close</TITLE>");
						out.println("</HEAD>");
						out.println("<BODY>");
						out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
						out.println("		top.close();");
						out.println("</SCRIPT>");
						out.println("</BODY>");
						out.println("</HTML>");
					}
					out.close();
				} else { // There are errors
					ses.setAttribute("error", msgError);
					ses.setAttribute("dftBasic", dftBasic);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0810_draft_det.jsp");
						callPage(LangPath + "EDL0810_draft_det.jsp", req, res);
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
	protected void procActionCollection(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081001Message collBasic = null;
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
			flexLog("Send Initial Data");
			collBasic = (EDL081001Message) mc.getMessageRecord("EDL081001");
			collBasic.setH01USERID(user.getH01USR());
			collBasic.setH01PROGRM("EDL0810");
			collBasic.setH01TIMSYS(getTimeStamp());
			collBasic.setH01SCRCOD("01");
			collBasic.setH01OPECOD("0005");
			//collBasic.setE02PAGFLG("CW");
			// all the fields here
			java.util.Enumeration enu = collBasic.fieldEnumeration();
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

			collBasic.send();
			collBasic.destroy();

			flexLog("EDL081001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL081001")) {
				try {
					collBasic =
						(
							datapro
								.eibs
								.beans
								.EDL081001Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.EDL081001Message");
					flexLog("EDL081001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				collBasic = (EDL081001Message) newmessage;

				flexLog("Putting java beans into the session");
				userPO.setIdentifier(collBasic.getE01DLHNRO());
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.removeAttribute("collBasic");

				if (IsNotError) { // There are no errors

					if (userPO.getPurpose().equals("NEW")) {
						ses.setAttribute("collBasic", collBasic);
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.products.JSEDL0810?SCREEN=400&E01DLHNRO="
								+ collBasic.getE01DLHNRO());
					} else {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_collection_enter_maint.jsp");
							callPage(
								LangPath + "EDL0810_collection_enter_maint.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					ses.setAttribute("error", msgError);
					ses.setAttribute("collBasic", collBasic);
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_collection_basic.jsp");
							callPage(
								LangPath + "EDL0810_collection_basic.jsp",
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
									+ "EDL0810_collection_list.jsp");
							callPage(
								LangPath + "EDL0810_collection_list.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				}

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

			int screen = A_UPLOAD;

			try {

				flexLog("Screen  Number: " + screen);

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
						// BEGIN
						case R_ENTER_MAINT :
							procReqEnterMaint(msgUser, req, res, session);
							break;
						case R_ENTER_NEW :
							procReqEnterNew(msgUser, req, res, session);
							break;
							// Action 
						case A_ENTER_NEW :
							procActionEnterNew(mc, msgUser, req, res, session);
							break;
						case A_ENTER_MAINT :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_LIST_DRAFT :
							procActionListDraft(mc, msgUser, req, res, session);
							break;
						case A_DRAFT_DET :
							procActionDraftDet(mc, msgUser, req, res, session);
							break;
							// END Entering
						case A_COLLECTION :
							procActionCollection(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_MAINT_ACEP :
							procActionEnterAcep(mc, msgUser, req, res, session);
							break;
						case R_MAINT_ACEP :
							procRequestAcep(mc, msgUser, req, res, session);
							break;
						case R_MAINT_ACTION :
							procRequestAcepAction(
								mc,
								msgUser,
								req,
								res,
								session);
							break;

						case A_ENTER_ACC_MAINT :
							procActionEnterNewAcc(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_MAINT_TO_SCK :
							procActionMaintBeansToSck(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_GEN_DOCS :
							procActionGenDocs(mc, msgUser, req, res, session);
							break;
						case A_ENTER_DOCUMENT :
							procActionEnterDocument(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_ENTER_UPLOAD :
							procRequestUpload(mc, msgUser, req, res, session);
							break;
						case A_UPLOAD :
							procActionUpload(mc, msgUser, req, res, session);
							break;
						case A_VALIDATE :
							procActionValidate(mc, msgUser, req, res, session);
							break;
						case R_CREATE :
							procReqEnterCreate(msgUser, req, res, session);
							break;
						case A_CREATE :
							procActionCreate(mc, msgUser, req, res, session);
							break;
						case R_INQUIRY :
							procReqEnterInquiry(msgUser, req, res, session);
							break;
						case A_INQUIRY :
							procActionInquiry(mc, msgUser, req, res, session);
							break;
						case R_ENTER_DELETE :
							procReqEnterDelete(msgUser, req, res, session);
							break;
						case A_ENTER_DELETE :
							procActionEnterDelete(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_DELETE :
							procActionDelete(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					return;
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
		/** if (super.logType != NONE) {
		
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
		
		} **/
	}

	protected void procActionEnterNewAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081003Message dftAcceptor = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String address = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		boolean newAcceptor = false;
		try {
			dftAcceptor = (EDL081003Message) mc.getMessageRecord("EDL081003");
			dftAcceptor.setH03USERID(user.getH01USR());
			dftAcceptor.setH03PROGRM("EDL0810");
			dftAcceptor.setH03TIMSYS(getTimeStamp());
			dftAcceptor.setH03SCRCOD("01");

			if (req.getParameter("ACTION") != null) {
				//inside drafts maintenance
				newAcceptor = true;
			} else {
				//inside acceptant maintenance
				newAcceptor = false;
			}
			dftAcceptor.setH03OPECOD("0002");
			if (req.getParameter("IDEACC") == null) {
				dftAcceptor.setE03DLDIDA("");

			} else {
				dftAcceptor.setE03DLDIDA(req.getParameter("IDEACC"));
			}

			if (req.getParameter("ADDRESS") == null) {
				address = "0";
			} else {
				address = req.getParameter("ADDRESS");
				if (address.trim().equals(""))
					address = "0";
			}

			dftAcceptor.setE03DLDNDA(address);

			dftAcceptor.send();
			dftAcceptor.destroy();
			flexLog("EDL080003 Message Sent");

			//Receive Error 
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) { //
				msgError = (datapro.eibs.beans.ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
			}

			//Receive Data
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDL081003")) {

				dftAcceptor = (EDL081003Message) newmessage;
				// There are no errors
				try {
					if (IsNotError) {

						ses.setAttribute("dftAcceptor", dftAcceptor);
						ses.setAttribute("error", msgError);

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0810_dft_acceptor_basic.jsp");
						callPage(
							LangPath + "EDL0810_dft_acceptor_basic.jsp",
							req,
							res);

					} else { // There are errors

						if (newAcceptor) {
							ses.setAttribute("error", msgError);
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "error_viewer.jsp");
							callPage(LangPath + "error_viewer.jsp", req, res);
						} else {
							msgError = new datapro.eibs.beans.ELEERRMessage();
							//ses.setAttribute("dftAcceptor", dftAcceptor);
							ses.setAttribute("error", msgError);
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_dft_enter_acc_maint.jsp");
							callPage(
								LangPath + "EDL0810_dft_enter_acc_maint.jsp",
								req,
								res);
						}

					}
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

	protected void procActionMaintBeansToSck(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		Socket s = null;
		MessageRecord newmessage = null;

		EDL081003Message dftAcceptor = null;

		JBListRec lstAcceptors = null;

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
			dftAcceptor = (EDL081003Message) ses.getAttribute("dftAcceptor");
			dftAcceptor.setH03USERID(user.getH01USR());
			dftAcceptor.setH03PROGRM("EDL0800");
			dftAcceptor.setH03TIMSYS(getTimeStamp());
			dftAcceptor.setH03SCRCOD("01");
			dftAcceptor.setH03OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = dftAcceptor.fieldEnumeration();
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
			mc.sendMessage(dftAcceptor);
			dftAcceptor.destroy();
			flexLog("EDL081003 Message Sent to Sockets");

			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) { //
				msgError = (datapro.eibs.beans.ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
			}
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDL081003")) {
				// There are no errors
				dftAcceptor = (EDL081003Message) newmessage;
				ses.setAttribute("dftAcceptor", dftAcceptor);
				ses.setAttribute("error", msgError);

				try {
					if (IsNotError) {

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0810_dft_acceptor_docs.jsp");
						callPage(
							LangPath + "EDL0810_dft_acceptor_docs.jsp",
							req,
							res);
					} else { // There are errors

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0810_dft_acceptor_basic.jsp");
						callPage(
							LangPath + "EDL0810_dft_acceptor_basic.jsp",
							req,
							res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			}
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}

	protected void procActionGenDocs(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081002Message dftDocument = null;
		JBListRec lstDocuments = null;

		UserPos userPO = null;
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			lstDocuments = new datapro.eibs.beans.JBListRec();
			ses.setAttribute("lstDocuments", lstDocuments);
			int docIni = 0;
			int docFin = 0;
			int docFreq = 0;
			int docMatDateMonth = 0;
			int docMatDateDay = 0;
			int docMatDateYear = 0;
			double docAmount = 0;
			double docRate = 0;
			String docAutDebitAcc = "";
			String docChgTyp = "";
			String docFreqTyp = "";
			String docInstCob = "";
			String action = "";
			String autgen = "N";
			try {
				action = req.getParameter("ACTION");
				if (action == null)
					action = "";
				autgen = req.getParameter("AUTGEN");
				if (autgen == null)
					autgen = "N";
			} catch (Exception ex) {
				action = "";
				autgen = "N";
			}
			if (action.equals("")) {
				if (autgen.trim().equals("Y")) {
					try {
						docIni =
							Integer.parseInt(req.getParameter("DFTINIDOC"));
					} catch (Exception e) {
						docIni = 0;
					}
					try {
						docFin =
							Integer.parseInt(req.getParameter("DFTFINDOC"));
					} catch (Exception e) {
						docFin = 0;
					}
					try {
						docFreq =
							Integer.parseInt(req.getParameter("DFTFRECUENCY"));
					} catch (Exception e) {
						docFreq = 0;
					}
					//type of frecuency : D - days , M - Months , Y - Years
					try {
						docFreqTyp = req.getParameter("INDFREC");
					} catch (Exception e) {
						docFreqTyp = " ";
					}

					String stAT1 = req.getParameter("DFTDTMAT1");
					String stAT2 = req.getParameter("DFTDTMAT2");
					String stAT3 = req.getParameter("DFTDTMAT3");
					if (stAT1 == null || stAT1.equals("")) stAT1 = "0";
					if (stAT2 == null || stAT2.equals("")) stAT2 = "0";
					if (stAT3 == null || stAT3.equals("")) stAT3 = "0";
					if (user.getE01DTF().equals("MDY")) {
						docMatDateMonth =
							Integer.parseInt(stAT1);
						docMatDateDay =
							Integer.parseInt(stAT2);
						docMatDateYear =
							Integer.parseInt(stAT3);
					}
					if (user.getE01DTF().equals("DMY")) {
						docMatDateDay =
							Integer.parseInt(stAT1);
						docMatDateMonth =
							Integer.parseInt(stAT2);
						docMatDateYear =
							Integer.parseInt(stAT3);
					}
					if (user.getE01DTF().equals("YMD")) {
						docMatDateYear =
							Integer.parseInt(stAT1);
						docMatDateMonth =
							Integer.parseInt(stAT2);
						docMatDateDay =
							Integer.parseInt(stAT3);
					}
					//check century
					if (docMatDateYear > 50) {
						docMatDateYear += 1900;
					} else {
						docMatDateYear += 2000;
					}
					try {
						docAmount =
							Double.parseDouble(req.getParameter("DFTAMOUNT"));
					} catch (Exception ex) {
						docAmount = 0.0;
					}
					try {
						docRate =
							Double.parseDouble(req.getParameter("DFTRATE"));
					} catch (Exception ex) {
						docRate = 0.0;
					}
					try {
						docAutDebitAcc = req.getParameter("DFTAUTDEBACCT");
					} catch (Exception ex) {
						docAutDebitAcc = "";
					}
					try {
						docChgTyp = req.getParameter("DFTCHGTYP");
					} catch (Exception ex) {
						docChgTyp = "";
					}
					int colnumdocs = 10;
					lstDocuments.init(colnumdocs);
					String myRow2[] = new String[colnumdocs];
					for (int i = 0; i < colnumdocs; i++) {
						myRow2[i] = "";
					}
					String myFlag = "";
					boolean endDocs = false;
					int seq = docIni;
					Calendar dateini = Calendar.getInstance();
					Calendar datetmp = Calendar.getInstance();
					dateini.set(
						docMatDateYear,
						--docMatDateMonth,
						docMatDateDay);
					datetmp = dateini;
					while (!endDocs) {
						myRow2[0] = String.valueOf(seq++);
						if (user.getE01DTF().equals("MDY")) {
							myRow2[1] =
								String.valueOf(datetmp.get(Calendar.MONTH) + 1);
							myRow2[2] =
								String.valueOf(datetmp.get(Calendar.DATE));
							myRow2[3] =
								String
									.valueOf(datetmp.get(Calendar.YEAR))
									.trim()
									.substring(
									2);
						}
						if (user.getE01DTF().equals("DMY")) {
							myRow2[1] =
								String.valueOf(datetmp.get(Calendar.DATE));
							myRow2[2] =
								String.valueOf(datetmp.get(Calendar.MONTH) + 1);
							myRow2[3] =
								String
									.valueOf(datetmp.get(Calendar.YEAR))
									.trim()
									.substring(
									2);
						}
						if (user.getE01DTF().equals("YMD")) {
							myRow2[1] =
								String
									.valueOf(datetmp.get(Calendar.YEAR))
									.trim()
									.substring(
									2);
							myRow2[2] =
								String.valueOf(datetmp.get(Calendar.MONTH) + 1);
							myRow2[3] =
								String.valueOf(datetmp.get(Calendar.DATE));
						}
						myRow2[4] = String.valueOf(docAmount);
						myRow2[5] = String.valueOf(docRate);
						myRow2[6] = docAutDebitAcc;
						myRow2[7] = docChgTyp;
						// add frecuency of documents
						if (docFreqTyp.equals("D"))
							dateini.add(Calendar.DATE, docFreq);
						if (docFreqTyp.equals("M"))
							dateini.add(Calendar.MONTH, docFreq);
						if (docFreqTyp.equals("Y"))
							dateini.add(Calendar.YEAR, docFreq);

						datetmp = dateini;
						// repeat while date is holiday
						// no more than 5 times
						int numTimes = 0;

						while (!procValidDate(mc,
							user,
							req,
							res,
							ses,
							String.valueOf(datetmp.get(Calendar.MONTH) + 1),
							String.valueOf(datetmp.get(Calendar.DATE)),
							String
								.valueOf(datetmp.get(Calendar.YEAR))
								.trim()
								.substring(
								2))) {
							//add 1 day to date
							datetmp.add(Calendar.DATE, 1);

							numTimes++;
							if (numTimes > 5) {
								//generate error
								IsNotError = false;
								break;
							}
						}
						lstDocuments.addRow(myFlag, myRow2);
						if (seq == docFin + 1)
							endDocs = true;
					}
					ses.setAttribute("lstDocuments", lstDocuments);
					if (IsNotError) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0810_dft_acceptor_docs_detail.jsp");
						callPage(
							LangPath + "EDL0810_dft_acceptor_docs_detail.jsp",
							req,
							res);
					} else {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0810_dft_acceptor_docs.jsp");
						callPage(
							LangPath + "EDL0810_dft_acceptor_docs.jsp",
							req,
							res);
					}
				} else {
					int colnumdocs = 10;
					lstDocuments.init(colnumdocs);
					String myRow2[] = new String[colnumdocs];
					String myFlag = "";
					myRow2[0] = "1";
					myRow2[1] = "";
					myRow2[2] = "";
					myRow2[3] = "";
					myRow2[4] = "";
					myRow2[5] = "";
					myRow2[6] = "";
					myRow2[7] = "";
					myRow2[9] = "";
					lstDocuments.addRow(myFlag, myRow2);
					ses.setAttribute("lstDocuments", lstDocuments);
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0810_dft_acceptor_docs_detail.jsp");
					callPage(
						LangPath + "EDL0810_dft_acceptor_docs_detail.jsp",
						req,
						res);
				}
			} else {
				procMaintDocs(mc, user, req, res, ses);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procMaintDocs(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {
		String action = "";
		int numdocini = 0;
		int numdocs = 0;
		int seq = 0;
		JBListRec lstDocuments = null;
		EDL081002Message dftDocument = null;
		ELEERRMessage msgError = null;
		Socket s = null;
		MessageRecord newmessage = null;
		boolean IsNotError = true;
		UserPos userPO = null;

		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			lstDocuments = new datapro.eibs.beans.JBListRec();
			action = req.getParameter("ACTION");
			if (action.equals("D")) {
				//delete documents
				numdocini = Integer.parseInt(req.getParameter("NUMINI"));
				numdocs = Integer.parseInt(req.getParameter("NUMRECORDS"));

				int colnumdocs = 10;
				lstDocuments.init(colnumdocs);
				String myRow2[] = new String[colnumdocs];
				for (int i = 0; i < colnumdocs; i++) {
					myRow2[i] = "";
				}
				String myFlag = "";
				seq = numdocini;
				String checked = "";
				String numActualSeq = "";
				String numSeq = "";
				for (int i = 0; i < numdocs; i++) {
					//numseq saves the last sequencence number
					//e02dldndr could be changed
					numActualSeq = req.getParameter("E02DLDNRI" + i);
					numSeq = req.getParameter("CURSEQ" + i);

					checked = req.getParameter("NUMSEQ" + i);
					if (checked == null
						&& numActualSeq.trim().equals(numSeq.trim())) {

						myRow2[0] = req.getParameter("E02DLDNRI" + i);
						myRow2[1] = req.getParameter("E02DLDFV1" + i);
						myRow2[2] = req.getParameter("E02DLDFV2" + i);
						myRow2[3] = req.getParameter("E02DLDFV3" + i);
						myRow2[4] = req.getParameter("E02DLDAMT" + i);
						//myRow2[5] = req.getParameter("E02DLDARC" + i);
						myRow2[5] = "";
						myRow2[6] = req.getParameter("E02DLDNDA" + i);
						//myRow2[7] = req.getParameter("E02DLDEXT" + i);
						myRow2[7] = "";
						//myRow2[9] = req.getParameter("E02DLDCOI" + i);
						myRow2[9] = "";
						lstDocuments.addRow(myFlag, myRow2);
						seq++;
					} else {
						//sends delete message
						dftDocument =
							(EDL081002Message) mc.getMessageRecord("EDL081002");
						dftDocument.setH02USERID(user.getH01USR());
						dftDocument.setH02PROGRM("EDL0800");
						dftDocument.setH02TIMSYS(getTimeStamp());
						dftDocument.setH02SCRCOD("01");
						dftDocument.setH02OPECOD("0009");
						dftDocument.setE02DLDNRO(userPO.getIdentifier());
						dftDocument.setE02DLDIDA(req.getParameter("E03DLDIDA"));

						if (numActualSeq.trim().equals(numSeq.trim())
							== false) {
							//deletes the previous number sequence
							dftDocument.setE02DLDNRI(
								req.getParameter("CURSEQ" + i));
						} else {
							//deletes the current number sequence
							dftDocument.setE02DLDNRI(
								req.getParameter("E02DLDNRI" + i));
						}
						try {
							dftDocument.setE01DLDDAC(
								req.getParameter("E02DLDNDA" + i).trim());
						} catch (Exception ex) {
							dftDocument.setE01DLDDAC("0");
						}

						flexLog("dftDocument = " + dftDocument);
						dftDocument.send();
						dftDocument.destroy();
						flexLog("EDL081002 Message Sent");
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);
							showERROR(msgError);
							msgError.setERRNUM("0");
							IsNotError = true;
							newmessage = mc.receiveMessage();
						} else
							flexLog(
								"Message "
									+ newmessage.getFormatName()
									+ " received.");
						if (numActualSeq.trim().equals(numSeq.trim())
							== false) {
							//saves the new number sequence..
							dftDocument =
								(EDL081002Message) mc.getMessageRecord(
									"EDL081002");
							dftDocument.setH02USERID(user.getH01USR());
							dftDocument.setH02PROGRM("EDL0810");
							dftDocument.setH02TIMSYS(getTimeStamp());
							dftDocument.setH02SCRCOD("01");
							dftDocument.setH02OPECOD("0005");
							dftDocument.setE02DLDNRO(userPO.getIdentifier());
							dftDocument.setE02DLDNRI(
								req.getParameter("E02DLDNRI" + i).trim());
							dftDocument.setE02DLDIDA(
								req.getParameter("E03DLDIDA"));
							dftDocument.setE02DLDFV1(
								req.getParameter("E02DLDFV1" + i).trim());
							dftDocument.setE02DLDFV2(
								req.getParameter("E02DLDFV2" + i).trim());
							dftDocument.setE02DLDFV3(
								req.getParameter("E02DLDFV3" + i).trim());
							try {
								dftDocument.setE02DLDAMT(
									req.getParameter("E02DLDAMT" + i).trim());
							} catch (Exception ex) {
								dftDocument.setE02DLDAMT("0.0");
							}

							//								try {
							//									dftDocument.setE02DLDARC(req.getParameter("E02DLDARC" + i).trim());
							//								} catch (Exception ex) {
							//									dftDocument.setE02DLDARC("0.0");
							//								}
							try {
								dftDocument.setE02DLDNRO(
									req.getParameter("E02DLDNRO" + i).trim());
							} catch (Exception ex) {

								dftDocument.setE02DLDNRO("0");
							}

							//								try {
							//									dftDocument.setE02DLDEXT(req.getParameter("E02DLDEXT" + i).trim());
							//								} catch (Exception ex) {
							//									dftDocument.setE02DLDEXT("0");
							//								}
							//								try {
							//									dftDocument.setE02DLDCOI(req.getParameter("E02DLDCOI" + i).trim());
							//								} catch (Exception ex) {
							//									dftDocument.setE02DLDCOI("0");
							//								}
							try {
								dftDocument.setE01DLDDAC(
									req.getParameter("E02DLDNDA" + i).trim());
							} catch (Exception ex) {
								dftDocument.setE01DLDDAC("0");
							}

							dftDocument.send();
							dftDocument.destroy();
							flexLog("EDL080002 Message Sent");
							myRow2[0] =
								req.getParameter("E02DLDNRI" + i).trim();
							myRow2[1] =
								req.getParameter("E02DLDFV1" + i).trim();
							myRow2[2] =
								req.getParameter("E02DLDFV2" + i).trim();
							myRow2[3] =
								req.getParameter("E02DLDFV3" + i).trim();
							myRow2[4] =
								req.getParameter("E02DLDAMT" + i).trim();
							//myRow2[5] = req.getParameter("E02DLDARC" + i).trim();
							myRow2[5] = "";
							myRow2[6] =
								req.getParameter("E02DLDNDA" + i).trim();
							//myRow2[7] = req.getParameter("E02DLDEXT" + i).trim();
							myRow2[7] = "";
							//myRow2[9] = req.getParameter("E02DLDCOI" + i).trim();
							myRow2[9] = "";
							lstDocuments.addRow(myFlag, myRow2);
						}

					}
				}
				ses.setAttribute("lstDocuments", lstDocuments);
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0810_dft_acceptor_docs_detail.jsp");
				callPage(
					LangPath + "EDL0810_dft_acceptor_docs_detail.jsp",
					req,
					res);
			}
			if (action.equals("R")) {
				//duplicate documents
			}
			if (action.equals("N")) {
				//new record
				numdocs = Integer.parseInt(req.getParameter("NUMRECORDS"));
				int colnumdocs = 10;
				lstDocuments.init(colnumdocs);
				String myRow2[] = new String[colnumdocs];
				for (int i = 0; i < colnumdocs; i++) {
					myRow2[i] = "";
				}
				String myFlag = "";
				for (int i = 0; i < numdocs; i++) {
					myRow2[0] = req.getParameter("E02DLDNRI" + i);
					myRow2[1] = req.getParameter("E02DLDFV1" + i);
					myRow2[2] = req.getParameter("E02DLDFV2" + i);
					myRow2[3] = req.getParameter("E02DLDFV3" + i);
					myRow2[4] = req.getParameter("E02DLDAMT" + i);
					//myRow2[5] = req.getParameter("E02DLDARC" + i);
					myRow2[5] = "";
					myRow2[6] = req.getParameter("E02DLDNDA" + i);
					//myRow2[7] = req.getParameter("E02DLDEXT" + i);
					myRow2[7] = "";
					//myRow2[9] = req.getParameter("E02DLDCOI" + i);
					myRow2[9] = "";
					lstDocuments.addRow(myFlag, myRow2);
				}
				myRow2[0] = "";
				myRow2[1] = "";
				myRow2[2] = "";
				myRow2[3] = "";
				myRow2[4] = "";
				myRow2[5] = "";
				//myRow2[6] = "";
				myRow2[7] = "";
				myRow2[9] = "";
				lstDocuments.addRow(myFlag, myRow2);
				ses.setAttribute("lstDocuments", lstDocuments);
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0810_dft_acceptor_docs_detail.jsp");
				callPage(
					LangPath + "EDL0810_dft_acceptor_docs_detail.jsp",
					req,
					res);
			}

			if (action.equals("0")) {
				//save records
				numdocini = Integer.parseInt(req.getParameter("NUMINI"));
				numdocs = Integer.parseInt(req.getParameter("NUMRECORDS"));

				String curdoc = "";
				String dtini = "";

				int colnumdocs = 10;
				lstDocuments.init(colnumdocs);
				String myRow2[] = new String[colnumdocs];
				for (int i = 0; i < colnumdocs; i++) {
					myRow2[i] = "";
				}
				String myFlag = "";

				for (int i = 0; i < numdocs; i++) {
					dftDocument =
						(EDL081002Message) mc.getMessageRecord("EDL081002");
					dftDocument.setH02USERID(user.getH01USR());
					dftDocument.setH02PROGRM("EDL0810");
					dftDocument.setH02TIMSYS(getTimeStamp());
					dftDocument.setH02SCRCOD("01");
					dftDocument.setH02OPECOD("0005");
					dftDocument.setE02DLDNRO(userPO.getIdentifier());
					dftDocument.setE02DLDIDA(req.getParameter("E03DLDIDA"));
					//saves only if numdoc != blank
					if (i == numdocs - 1)
						dftDocument.setH02FLGWK2("*");

					curdoc = req.getParameter("E02DLDNRI" + i);
					dtini = req.getParameter("E02DLDFV1" + i);

					if (curdoc == null)
						curdoc = "";
					else
						curdoc = curdoc.trim();
					if (dtini == null)
						dtini = "";
					else
						dtini = dtini.trim();
					if (!curdoc.equals("") && !dtini.equals("")) {
						dftDocument.setE02DLDNRI(
							req.getParameter("E02DLDNRI" + i).trim());

						dftDocument.setE02DLDFV1(
							req.getParameter("E02DLDFV1" + i).trim());
						dftDocument.setE02DLDFV2(
							req.getParameter("E02DLDFV2" + i).trim());
						dftDocument.setE02DLDFV3(
							req.getParameter("E02DLDFV3" + i).trim());
						try {
							if (req.getParameter("E02DLDAMT" + i) != null) {
								dftDocument.setE02DLDAMT(
									req.getParameter("E02DLDAMT" + i).trim());
							} else {
								dftDocument.setE02DLDAMT("0.0");
							}
						} catch (Exception e) {
							dftDocument.setE02DLDAMT("0.0");
						}

						//							try {
						//								if (req.getParameter("E02DLDARC" + i) != null) {
						//									dftDocument.setE02DLDARC(req.getParameter("E02DLDARC" + i).trim());
						//								} else {
						//									dftDocument.setE02DLDARC("0.0");
						//								}
						//							} catch (Exception e) {
						//								dftDocument.setE02DLDARC("0.0");
						//							}
						try {
							if (req.getParameter("E02DLDNDA" + i).equals(""))
								dftDocument.setE02DLDNDA("0");
							else
								dftDocument.setE02DLDNDA(
									req.getParameter("E02DLDNDA" + i).trim());
						} catch (Exception e) {
							flexLog("Exception E02DLDNDA: " + e);
							dftDocument.setE02DLDNDA("0");
						}
						try {
							dftDocument.setE01DLDDAC(
								req.getParameter("E02DLDNDA" + i).trim());
						} catch (Exception ex) {
							dftDocument.setE01DLDDAC("0");
						}

						//							try {
						//								dftDocument.setE02DLDEXT(req.getParameter("E02DLDEXT" + i).trim());
						//							} catch (Exception e) {
						//								flexLog("Exception E02DLDEXT : " + e);
						//								dftDocument.setE02DLDEXT("0");
						//							}
						//							try {
						//								dftDocument.setE02DLDCOI(req.getParameter("E02DLDCOI" + i).trim());
						//							} catch (Exception e) {
						//								flexLog("Exception E02DLDCOI : " + e);
						//								dftDocument.setE02DLDCOI("");
						//							}
						myRow2[0] = req.getParameter("E02DLDNRI" + i);
						myRow2[1] = req.getParameter("E02DLDFV1" + i);
						myRow2[2] = req.getParameter("E02DLDFV2" + i);
						myRow2[3] = req.getParameter("E02DLDFV3" + i);
						myRow2[4] = req.getParameter("E02DLDAMT" + i);
						//myRow2[5] = req.getParameter("E02DLDARC" + i);
						myRow2[5] = "";

						myRow2[6] = req.getParameter("E02DLDNDA" + i);
						//myRow2[7] = req.getParameter("E02DLDEXT" + i);
						myRow2[7] = "";
						//myRow2[9] = req.getParameter("E02DLDCOI" + i);
						myRow2[9] = "";
						lstDocuments.addRow(myFlag, myRow2);

						dftDocument.send();
						dftDocument.destroy();
						flexLog("EDL081002 Message Sent");
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);
							//IsNotError = true;
							//msgError.setERRNUM("0");
							showERROR(msgError);
							newmessage = mc.receiveMessage();
							/*FVO
							if (IsNotError == false) {
								break;
							}*/
						} else
							flexLog(
								"Message "
									+ newmessage.getFormatName()
									+ " received.");
					}

				}
				if (IsNotError) {
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println(
						"		top.opener.document.forms[0].SCREEN.value = 400");
					out.println(
						"		top.opener.document.forms[0].E01DLHNRO.value = "
							+ userPO.getIdentifier());
					out.println(
						"		top.opener.document.forms[0].submit();");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else {
					ses.setAttribute("lstDocuments", lstDocuments);
					ses.setAttribute("error", msgError);
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0810_dft_acceptor_docs_detail.jsp");
					callPage(
						LangPath + "EDL0810_dft_acceptor_docs_detail.jsp",
						req,
						res);
				}

			}
		} catch (Exception ex) {
			flexLog("Error: " + ex);
			ex.printStackTrace();
		}
	}
	protected boolean procValidDate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
		String month,
		String day,
		String year) {
		boolean retVal = true;
		Socket s = null;
		MessageRecord newmessage = null;

		ESD000015Message msgValDate = null;
		ELEERRMessage msgError = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			msgValDate = new datapro.eibs.beans.ESD000015Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			//s = new Socket(super.hostIP, super.iniSocket + 1);
			//s.setSoTimeout(super.sckTimeOut);
			//mc =
			//	new MessageContext(
			//		new DataInputStream(new BufferedInputStream(s.getInputStream())),
			//		new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
			//		"datapro.eibs.beans");

			msgValDate = (ESD000015Message) mc.getMessageRecord("ESD000015");
			msgValDate.setH15USERID(user.getH01USR());
			msgValDate.setH15PROGRM("ESD0000");
			msgValDate.setH15TIMSYS(getTimeStamp());
			msgValDate.setH15SCRCOD("01");
			msgValDate.setH15OPECOD("0001");

			if (user.getE01DTF().equals("MDY")) {
				msgValDate.setE15HOLDT1(month);
				msgValDate.setE15HOLDT2(day);
				msgValDate.setE15HOLDT3(year);
			}
			if (user.getE01DTF().equals("DMY")) {
				msgValDate.setE15HOLDT1(day);
				msgValDate.setE15HOLDT2(month);
				msgValDate.setE15HOLDT3(year);
			}
			if (user.getE01DTF().equals("YMD")) {
				msgValDate.setE15HOLDT1(year);
				msgValDate.setE15HOLDT2(month);
				msgValDate.setE15HOLDT3(day);
			}
			msgValDate.send();
			msgValDate.destroy();
			flexLog("ESD000015 Message Sent");

			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				boolean IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				msgError.setERRNUM("0");
				showERROR(msgError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
				msgValDate = (ESD000015Message) newmessage;
				if (msgValDate.getE15HOLFLG().equals("Y")) {
					retVal = false;
				} else {
					retVal = true;
				}
			}

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		return retVal;
	}

	protected void procActionEnterDocument(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081003Message dftAcceptor = new EDL081003Message();
		EDL081002Message dftDocument = null;

		JBListRec lstDocuments = null;
		JBListRec lstAcceptors = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setAccNum(req.getParameter("IDEACC"));
		userPO.setIdentifier(req.getParameter("E01DLHNRO"));
		dftAcceptor.setE03DLDIDA(req.getParameter("IDEACC"));
		lstAcceptors = (JBListRec) ses.getAttribute("lstAcceptors");
		lstAcceptors.initRow();
		while (lstAcceptors.getNextRow()) {
			if (lstAcceptors.getRecord(0).equals(dftAcceptor.getE03DLDIDA())) {
				if (lstAcceptors.getRecord(1).length() > 35) {
					dftAcceptor.setE03DLDMA1(
						lstAcceptors.getRecord(1).substring(1, 35));
				} else
					dftAcceptor.setE03DLDMA1(lstAcceptors.getRecord(1));
			}
		}

		ses.setAttribute("dftAcceptor", dftAcceptor);
		ses.setAttribute("userPO", userPO);
		try {
			if (procReqListDraft(mc,
				user,
				req,
				res,
				ses)) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0810_dft_acceptor_docs_detail.jsp");
					callPage(
						LangPath + "EDL0810_dft_acceptor_docs_detail.jsp",
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
							+ "EDL0810_dft_acceptor_docs_detail.jsp");
					callPage(
						LangPath + "EDL0810_dft_enter_maint.jsp",
						req,
						res);
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

	protected void procRequestUpload(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		try {
			String E01DLHPRD = req.getParameter("E01DLHPRD");
			if (E01DLHPRD != null) user.setH01OPE(E01DLHPRD);
			ses.setAttribute("currUser", user);
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0810_collection_upload.jsp");
			callPage(LangPath + "EDL0810_collection_upload.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionUpload(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		EDL081004Message msgFile;
		EDL081001Message collBasic;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		SmartUpload mySmartUpload = new SmartUpload();
		try {
			//upload file
			//			Initialization
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			
			mySmartUpload.initialize(config, req, res);
			// Upload
			mySmartUpload.upload();
			// Retreive the current file
			com.jspsmart.upload.File myFile =
				mySmartUpload.getFiles().getFile(0);
			String prdcde = mySmartUpload.getRequest().getParameter("PRDCDE");
			//
			if (myFile.getSize() > 0) {

				byte[] bd = new byte[myFile.getSize()];
				for (int i = 0; i < myFile.getSize(); i++) {
					bd[i] = myFile.getBinaryData(i);
				}
				msgFile = (EDL081004Message) mc.getMessageRecord("EDL081004");
				msgFile.setH04USERID(user.getH01USR());
				msgFile.setH04PROGRM("EDL0810");
				msgFile.setH04TIMSYS(getTimeStamp());
				msgFile.setH04SCRCOD("01");
				msgFile.setH04OPECOD("0001");
				msgFile.setE04DKTPRD(prdcde);
				int bc = 0;
				int line = 0;
				String st = new String(bd);
				while (true) {
					line++;
					msgFile.setE04DKTCOT(st.substring(bc, bc + 1));
					msgFile.setE04DKTBRN(st.substring(bc + 2, bc + 5));
					msgFile.setE04DKTREF(st.substring(bc + 5, bc + 11));
					msgFile.setE04DKTCUO(st.substring(bc + 11, bc + 13));
					msgFile.setE04DKTRUT(st.substring(bc + 13, bc + 22));
					msgFile.setE04DKTRUA(st.substring(bc + 22, bc + 32));
					msgFile.setE04DKTCCC(st.substring(bc + 32, bc + 42));
					msgFile.setE04DKTFRE(st.substring(bc + 42, bc + 50));
					msgFile.setE04DKTFOL(st.substring(bc + 50, bc + 56).trim());
					msgFile.setE04DKTCOB(st.substring(bc + 56, bc + 57));
					msgFile.setE04DKTMON(st.substring(bc + 57, bc + 60));
					msgFile.setE04DKTTDO(st.substring(bc + 60, bc + 61));
					msgFile.setE04DKTABO(st.substring(bc + 61, bc + 71));
					msgFile.setE04DKTCAB(st.substring(bc + 71, bc + 73));
					msgFile.setE04DKTCAR(st.substring(bc + 73, bc + 83));
					msgFile.setE04DKTCCA(st.substring(bc + 83, bc + 85));
					msgFile.setE04DKTPCO(st.substring(bc + 85, bc + 86));
					msgFile.setE04DKTNIN(st.substring(bc + 86, bc + 101));
					msgFile.setE04DKTNMA(st.substring(bc + 101, bc + 131));
					msgFile.setE04DKTDIA(st.substring(bc + 131, bc + 161));
					msgFile.setE04DKTCOM(st.substring(bc + 161, bc + 176));
					msgFile.setE04DKTFVE(st.substring(bc + 176, bc + 184));
					msgFile.setE04DKTVLR(st.substring(bc + 184, bc + 196));
					msgFile.setE04DKTINS(st.substring(bc + 196, bc + 197));
					msgFile.setE04DKTPZA(st.substring(bc + 197, bc + 201));
					msgFile.setE04DKTCTP(st.substring(bc + 201, bc + 202));
					msgFile.setE04DKTFIL(st.substring(bc + 202, bc + 220));
					flexLog("msgFile = " + msgFile);
					msgFile.send();
					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;

						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);

						if (!IsNotError) {
							newmessage = mc.receiveMessage();
							if (newmessage.getFormatName().equals("EDL081004")) {
								msgFile = (EDL081004Message) newmessage;
								ses.setAttribute("msgFile", msgFile);
								ses.setAttribute("linea", String.valueOf(line));
							}
							break;
						}
					}
					bc += 222;
					if (bc >= myFile.getSize())
						break;
				}
				// sending last record
				if (IsNotError) {
					/*					msgFile.initialize();
										msgFile.setH04USERID(user.getH01USR());
										msgFile.setH04PROGRM("EDL0810");
										msgFile.setH04TIMSYS(getTimeStamp());
										msgFile.setH04SCRCOD("01");
										msgFile.setH04OPECOD("0001");
										msgFile.setE04DKTPRD(prdcde);*/
					msgFile.setH04FLGMAS("*");
					flexLog("msgFile = " + msgFile);
					msgFile.send();
					msgFile.destroy();
					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
					}
					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("EDL081004")) {
						
						msgFile = (EDL081004Message) newmessage;
						userPO.setIdentifier(msgFile.getE04DKTFIL().substring(msgFile.getE04DKTFIL().length()-12,msgFile.getE04DKTFIL().length()));
						
						flexLog("About to call Page: "+ LangPath+ "EDL0810_dft_confirm.jsp");
						callPage(LangPath + "EDL0810_dft_confirm.jsp",req,res);

						/*						collBasic = (EDL081001Message) newmessage;
												ses.setAttribute("collBasic", collBasic);
												res.sendRedirect(super.srctx+ "/servlet/datapro.eibs.products.JSEDL0810?SCREEN=400&E01DLHNRO="+ collBasic.getE01DLHNRO());
						*/
					}

				} else {
					ses.setAttribute("error", msgError);
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0810_collection_upload.jsp");
					callPage(
						LangPath + "EDL0810_collection_upload.jsp",
						req,
						res);
				}
			} else {
				msgError = new ELEERRMessage();
				msgError.setERRNUM(new BigDecimal(1));
				msgError.setERDS01("Archivo Vacio...");
				ses.setAttribute("error", msgError);
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL0810_collection_upload.jsp");
				callPage(LangPath + "EDL0810_collection_upload.jsp", req, res);
			}
		} catch (Exception e) {

			flexLog("Exception calling page " + e);
			msgError = new ELEERRMessage();
			msgError.setERRNUM(new BigDecimal(1));
			msgError.setERDS01("Error en Archivo...");
			ses.setAttribute("error", msgError);
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0810_collection_upload.jsp");
			callPage(LangPath + "EDL0810_collection_upload.jsp", req, res);
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
		EDL081001Message collBasic = null;
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
			flexLog("Send Initial Data");
			collBasic = (EDL081001Message) mc.getMessageRecord("EDL081001");
			collBasic.setH01USERID(user.getH01USR());
			collBasic.setH01PROGRM("EDL0810");
			collBasic.setH01TIMSYS(getTimeStamp());
			collBasic.setH01SCRCOD("01");
			collBasic.setH01OPECOD("0004");
			//collBasic.setE02PAGFLG("CW");
			// all the fields here

			userPO.setHeader3(collBasic.getE01DLHNRO());

			java.util.Enumeration enu = collBasic.fieldEnumeration();
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

			collBasic.send();
			collBasic.destroy();

			flexLog("EDL081001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL081001")) {
				try {
					collBasic =
						(
							datapro
								.eibs
								.beans
								.EDL081001Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.EDL081001Message");
					flexLog("EDL081001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				collBasic = (EDL081001Message) newmessage;

				flexLog("Putting java beans into the session");
				userPO.setIdentifier(collBasic.getE01DLHNRO());
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				ses.removeAttribute("collBasic");

				if (IsNotError) { // There are no errors

					if (userPO.getPurpose().equals("NEW")) {
						ses.setAttribute("collBasic", collBasic);
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.products.JSEDL0810?SCREEN=400&E01DLHNRO="
								+ collBasic.getE01DLHNRO());
					} else {
						try {
							//FVO
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_colldraft_confirm.jsp");
							callPage(
								LangPath + "EDL0810_colldraft_confirm.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else { // There are errors
					ses.setAttribute("error", msgError);
					ses.setAttribute("collBasic", collBasic);
					if (userPO.getPurpose().equals("NEW")) {
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_collection_list.jsp");
							callPage(
								LangPath + "EDL0810_collection_list.jsp",
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
									+ "EDL0810_collection_list.jsp");
							callPage(
								LangPath + "EDL0810_collection_list.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionCreate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081001Message collBasic = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			collBasic = (EDL081001Message) mc.getMessageRecord("EDL081001");
			collBasic.setH01USERID(user.getH01USR());
			collBasic.setH01PROGRM("EDL0810");
			collBasic.setH01TIMSYS(getTimeStamp());
			collBasic.setH01SCRCOD("01");
			collBasic.setH01OPECOD("0010");
			try {
				collBasic.setE01DLHNRO(req.getParameter("E01DLHNRO"));
			} catch (Exception e) {
			}

			collBasic.send();

			flexLog("EDL081001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL080030")) {

				//collBasic1 = (EDL080030Message) newmessage;
				userPO.setIdentifier(collBasic.getE01DLHNRO());
				userPO.setAccNum("");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				try {
					if (IsNotError) { // There are no errors EDL081001
						//ses.setAttribute("collBasic", collBasic);

						flexLog("About to call Page: "+ LangPath+ "EDL0810_dft_confirm.jsp");
						callPage(LangPath + "EDL0810_dft_confirm.jsp",req,res);

					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_colldraft_enter_create.jsp");
							callPage(
								LangPath + "EDL0810_colldraft_enter_create.jsp",
								req,
								res);
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
			flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqEnterCreate(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");
			userPO.setOption("LN");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0810_colldraft_enter_create.jsp");
			callPage(LangPath + "EDL0810_colldraft_enter_create.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

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

			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");
			userPO.setOption("LN");
			userPO.setPurpose("INQUIRY");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0810_colldraft_enter_inquiry.jsp");
			callPage(
				LangPath + "EDL0810_colldraft_enter_inquiry.jsp",
				req,
				res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqEnterDelete(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
			userPO =
				(datapro.eibs.beans.UserPos) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.UserPos");
			userPO.setOption("LN");
			userPO.setPurpose("INQUIRY");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0810_colldraft_enter_delete.jsp");
			callPage(LangPath + "EDL0810_colldraft_enter_delete.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081001Message collBasic = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError =
				(ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			collBasic = (EDL081001Message) mc.getMessageRecord("EDL081001");
			collBasic.setH01USERID(user.getH01USR());
			collBasic.setH01PROGRM("EDL0810");
			collBasic.setH01TIMSYS(getTimeStamp());
			collBasic.setH01SCRCOD("01");
			collBasic.setH01OPECOD("0002");
			try {
				collBasic.setE01DLHNRO(req.getParameter("E01DLHNRO"));
			} catch (Exception e) {
			}

			collBasic.send();

			flexLog("EDL081001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL081001")) {

				collBasic = (EDL081001Message) newmessage;
				userPO.setIdentifier(collBasic.getE01DLHNRO());
				userPO.setAccNum("");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				try {
					if (IsNotError) { // There are no errors EDL081001
						if (procReqListDraft(mc,
							user,
							req,
							res,
							ses)) { // There are no errors EDL081002							
							userPO.setPurpose("MAINTENANCE");
							userPO.setIdentifier(collBasic.getE01DLHNRO());
							userPO.setHeader1(collBasic.getE01DLHPRD());
							userPO.setHeader2(collBasic.getE01DLHCUN());
							userPO.setHeader3(collBasic.getE01CUSNA1());
							userPO.setHeader4(collBasic.getE01DSCPRO());
							userPO.setCurrency(collBasic.getE01DLHCCY());
							userPO.setBank(collBasic.getE01DLHOBK());
							userPO.setBranch(collBasic.getE01DLHOBR());
							ses.setAttribute("userPO", userPO);
							ses.setAttribute("collBasic", collBasic);
							try {
								flexLog(
									"About to redirect : "
										+ LangPath
										+ "EDL0810_colldraft_inq_basic.jsp");
								callPage(
									LangPath
										+ "EDL0810_colldraft_inq_basic.jsp",
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
										+ "EDL0810_colldraft_enter_inquiry.jsp");
								callPage(
									LangPath
										+ "EDL0810_colldraft_enter_inquiry.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_colldraft_enter_inquiry.jsp");
							callPage(
								LangPath
									+ "EDL0810_colldraft_enter_inquiry.jsp",
								req,
								res);
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
			flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionEnterDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081001Message collBasic = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError =
				(ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			collBasic = (EDL081001Message) mc.getMessageRecord("EDL081001");
			collBasic.setH01USERID(user.getH01USR());
			collBasic.setH01PROGRM("EDL0810");
			collBasic.setH01TIMSYS(getTimeStamp());
			collBasic.setH01SCRCOD("01");
			collBasic.setH01OPECOD("0002");
			try {
				collBasic.setE01DLHNRO(req.getParameter("E01DLHNRO"));
			} catch (Exception e) {
			}

			collBasic.send();

			flexLog("EDL081001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL081001")) {

				collBasic = (EDL081001Message) newmessage;
				userPO.setIdentifier(collBasic.getE01DLHNRO());
				userPO.setAccNum("");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				try {
					if (IsNotError) { // There are no errors EDL081001
						if (procReqListDraft(mc,
							user,
							req,
							res,
							ses)) { // There are no errors EDL081002							
							userPO.setPurpose("MAINTENANCE");
							userPO.setIdentifier(collBasic.getE01DLHNRO());
							userPO.setHeader1(collBasic.getE01DLHPRD());
							userPO.setHeader2(collBasic.getE01DLHCUN());
							userPO.setHeader3(collBasic.getE01CUSNA1());
							userPO.setHeader4(collBasic.getE01DSCPRO());
							userPO.setCurrency(collBasic.getE01DLHCCY());
							userPO.setBank(collBasic.getE01DLHOBK());
							userPO.setBranch(collBasic.getE01DLHOBR());
							ses.setAttribute("userPO", userPO);
							ses.setAttribute("collBasic", collBasic);
							try {
								flexLog(
									"About to redirect : "
										+ LangPath
										+ "EDL0810_colldraft_del_basic.jsp");
								callPage(
									LangPath
										+ "EDL0810_colldraft_del_basic.jsp",
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
										+ "EDL0810_colldraft_enter_delete.jsp");
								callPage(
									LangPath
										+ "EDL0810_colldraft_enter_delete.jsp",
									req,
									res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_colldraft_enter_delete.jsp");
							callPage(
								LangPath + "EDL0810_colldraft_enter_delete.jsp",
								req,
								res);
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
			flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081001Message collBasic = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError =
				(ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			collBasic = (EDL081001Message) mc.getMessageRecord("EDL081001");
			collBasic.setH01USERID(user.getH01USR());
			collBasic.setH01PROGRM("EDL0810");
			collBasic.setH01TIMSYS(getTimeStamp());
			collBasic.setH01SCRCOD("01");
			collBasic.setH01OPECOD("0009");
			try {
				collBasic.setE01DLHNRO(req.getParameter("E01DLHNRO"));
			} catch (Exception e) {
			}

			collBasic.send();

			flexLog("EDL081001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL081001")) {

				collBasic = (EDL081001Message) newmessage;
				userPO.setIdentifier(collBasic.getE01DLHNRO());
				userPO.setAccNum("");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", msgError);
				try {
					if (IsNotError) { // There are no errors EDL081001
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_colldraft_del_conf.jsp");
							callPage(
								LangPath + "EDL0810_colldraft_del_conf.jsp",
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
									+ "EDL0810_colldraft_del_basic.jsp");
							callPage(
								LangPath + "EDL0810_colldraft_del_basic.jsp",
								req,
								res);
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
			flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procRequestAcepAction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081003Message dftAcceptor = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String address = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		boolean newAcceptor = false;
		try {
			dftAcceptor = (EDL081003Message) mc.getMessageRecord("EDL081003");

			if (req.getParameter("ACTION") != null) {
				//inside drafts maintenance
				newAcceptor = true;
			} else {
				//inside acceptant maintenance
				newAcceptor = false;
			}

			if (req.getParameter("address") == null) {
				address = "0";
			} else {
				address = req.getParameter("address");
				if (address.trim().equals(""))
					address = "0";
			}

			java.util.Enumeration enu = dftAcceptor.fieldEnumeration();
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
			if (req.getParameter("ACCION") == "E") {
				dftAcceptor.setH03OPECOD("0004");
			} else {
				dftAcceptor.setH03OPECOD("0014");
			}
			dftAcceptor.setH03USERID(user.getH01USR());
			dftAcceptor.setH03PROGRM("EDL0810");
			dftAcceptor.setH03TIMSYS(getTimeStamp());
			dftAcceptor.setH03SCRCOD("01");
			dftAcceptor.setE03DLDNDA(address);
			dftAcceptor.setE03DLDIDA(req.getParameter("ideacc"));
			
			dftAcceptor.send();
			dftAcceptor.destroy();
			flexLog("EDL080003 Message Sent");

			//Receive Error 
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) { //
				msgError = (datapro.eibs.beans.ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
			}

			//Receive Data
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDL081003")) {

				dftAcceptor = (EDL081003Message) newmessage;
				// There are no errors
				try {
					if (IsNotError) {

						ses.setAttribute("dftAcceptor", dftAcceptor);
						ses.setAttribute("error", msgError);
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0810_acceptor_enter.jsp");
						callPage(
							LangPath + "EDL0810_acceptor_enter.jsp",
							req,
							res);

					} else { // There are errors
						if (newAcceptor) {
							ses.setAttribute("error", msgError);
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "error_viewer.jsp");
							callPage(LangPath + "error_viewer.jsp", req, res);
						} else {
							ses.setAttribute("error", msgError);
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_acceptor_mant.jsp");
							callPage(
								LangPath + "EDL0810_acceptor_mant.jsp",
								req,
								res);

						}

					}
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

	protected void procRequestAcep(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL081003Message dftAcceptor = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String address = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		boolean newAcceptor = false;
		try {
			dftAcceptor = (EDL081003Message) mc.getMessageRecord("EDL081003");
			dftAcceptor.setH03USERID(user.getH01USR());
			dftAcceptor.setH03PROGRM("EDL0810");
			dftAcceptor.setH03TIMSYS(getTimeStamp());
			dftAcceptor.setH03SCRCOD("01");

			if (req.getParameter("ACTION") != null) {
				//inside drafts maintenance
				newAcceptor = true;
			} else {
				//inside acceptant maintenance
				newAcceptor = false;
			}
			dftAcceptor.setH03OPECOD("0002");
			dftAcceptor.setE03DLDIDA(req.getParameter("ideacc"));

			if (req.getParameter("address") == null) {
				address = "0";
			} else {
				address = req.getParameter("address");
				if (address.trim().equals(""))
					address = "0";
			}

			dftAcceptor.setE03DLDNDA(address);

			dftAcceptor.send();
			dftAcceptor.destroy();
			flexLog("EDL080003 Message Sent");

			//Receive Error 
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) { //
				msgError = (datapro.eibs.beans.ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
			}

			//Receive Data
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDL081003")) {

				dftAcceptor = (EDL081003Message) newmessage;
				// There are no errors
				try {
					if (IsNotError) {

						ses.setAttribute("dftAcceptor", dftAcceptor);
						ses.setAttribute("error", msgError);
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0810_acceptor_mant.jsp");
						callPage(
							LangPath + "EDL0810_acceptor_mant.jsp",
							req,
							res);
					} else { // There are errors
						if (newAcceptor) {
							ses.setAttribute("error", msgError);
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "error_viewer.jsp");
							callPage(LangPath + "error_viewer.jsp", req, res);
						} else {
							msgError = new datapro.eibs.beans.ELEERRMessage();
							ses.setAttribute("error", msgError);
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDL0810_acceptor_mant.jsp");
							callPage(
								LangPath + "EDL0810_acceptor_enter.jsp",
								req,
								res);
						}

					}
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

	protected void procActionEnterAcep(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0810_acceptor_enter.jsp");
			callPage(LangPath + "EDL0810_acceptor_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

}