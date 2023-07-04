package datapro.eibs.approval;

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

import java.math.*;

public class JSEDL0828 extends datapro.eibs.master.SuperServlet {

	protected String LangPath = "S";
	// CIF options
	protected static final int R_PASSWORD = 1;
	protected static final int R_SUMMARY = 6;
	protected static final int A_LIST = 3;
	protected static final int R_PAY_DETAIL = 4;
	protected static final int R_CANCEL_DETAIL = 5;
	protected static final int R_LIST = 2;
	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0828() {
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

			int screen = R_LIST;

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
					// Requests
					case R_PASSWORD :
						procReqPassword(msgUser, req, res, session);
						break;
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
					case R_PAY_DETAIL :
						procReqPayDetail(mc, msgUser, req, res, session);
						break;
					case R_CANCEL_DETAIL :
						procReqCancelDetail(mc, msgUser, req, res, session);
						break;
						// Actions
					case A_LIST :
						procActionList(mc, msgUser, req, res, session);
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

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDL082802Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDL082802Message) mc.getMessageRecord("EDL082802");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("EDL0828");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setE02SELDID(req.getParameter("ID"));
			msgList.setE02SELDTP(req.getParameter("Type"));
			msgList.setE02SELNDR(req.getParameter("Doc"));
			msgList.setE02SELNLN(req.getParameter("Loan"));
			msgList.setE02ACTION(req.getParameter("action"));
			msgList.setE02MSGTXT(req.getParameter("reason"));
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
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There is no error
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.approval.JSEDL0828?SCREEN=1");
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "EDL0828_doc_ap_list.jsp");
						callPage(LangPath + "EDL0828_doc_ap_list.jsp", req, res);
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
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL082801Message msgList = null;
		ELEERRMessage msgError = null;
		JBListRec beanList = null;
		JBListRec beanAccList = null;

		boolean IsNotError = false;
		String Pos = "0";

		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			msgList = (EDL082801Message) mc.getMessageRecord("EDL082801");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDL0828");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0000");

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
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else
				if (newmessage.getFormatName().equals("EDL082801")) {

					int colNum = 8;
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

					String myRow[] = new String[colNum];
					String myAccRow[] = new String[colNum];
					BigDecimal totPerACC = new BigDecimal("0");

					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
						myAccRow[i] = "";
					}

					while (true) {

						msgList = (EDL082801Message) newmessage;

						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
							break;
						} else {

							if (!myFlag.equals(msgList.getE01DEAACC())) {

								myFlag = msgList.getE01DLDNLN();
								totPerACC = new BigDecimal("0");

								myAccRow[0] = msgList.getE01DLDNLN(); // Account Number
								myAccRow[1] = msgList.getE01DEACUN(); // Customer Number
								myAccRow[2] = msgList.getE01CUSNA1(); // Customer Name
								myAccRow[3] = msgList.getE01DEAPRO(); // Product
								myAccRow[4] = msgList.getE01TRNTOT(); // Net Balance
								myAccRow[5] = msgList.getE01WKPUSR(); // User	

								beanAccList.addRow(myFlag, myAccRow);
							}
							myRow[0] = msgList.getE01DLDNDR(); // Doc Number
							myRow[1] = msgList.getE01DLDDID(); // Acceptor ID
							myRow[2] = msgList.getE01ACPNME(); // Acceptor Name
							myRow[3] = msgList.getE01TRNTOT(); // Amount to Pay
							myRow[4] = msgList.getE01PAGTOT(); // Amount Paid	
							myRow[5] = msgList.getE01REMARK(); // Status
							myRow[6] = msgList.getE01DLDDTP(); // Type Doc.
							myRow[7] = msgList.getE01DLDNLN(); // Loan Number

							beanList.addRow(myFlag, myRow);
							totPerACC = totPerACC.add(msgList.getBigDecimalE01TRNTOT());
							beanAccList.setRecord(totPerACC.toString(), 4, beanAccList.getLastRec());

						}

						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("docList", beanList);
					ses.setAttribute("accList", beanAccList);

					if (beanAccList.getNoResult()) {
						try {
							flexLog("About to call Page: " + LangPath + "MISC_no_result.jsp");
							res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {

						try {
							flexLog("About to call Page: " + LangPath + "EDL0828_doc_ap_list.jsp");
							callPage(LangPath + "EDL0828_doc_ap_list.jsp", req, res);
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
	protected void procReqPassword(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			userPO.setRedirect("/servlet/datapro.eibs.approval.JSEDL0828?SCREEN=2");

			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqPayDetail(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL082501Message msgPayDoc = null;
		ELEERRMessage msgError = null;
		//DataPayDoc docData = null;	
		boolean IsNotError = false;

		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		//docData = (datapro.eibs.beans.DataPayDoc)ses.getAttribute("docData");

		String opCode = null;
		opCode = "0002";

		// Send Initial data
		try {
			msgPayDoc = (EDL082501Message) mc.getMessageRecord("EDL082501");
			msgPayDoc.setH01USERID(user.getH01USR());
			msgPayDoc.setH01PROGRM("EDL0825");
			msgPayDoc.setH01TIMSYS(getTimeStamp());
			msgPayDoc.setH01SCRCOD("01");
			msgPayDoc.setH01OPECOD(opCode);
			msgPayDoc.setE01SELNLN(req.getParameter("Loan"));
			msgPayDoc.setE01SELDID(req.getParameter("ID"));
			msgPayDoc.setE01SELNDR(req.getParameter("Doc"));
			msgPayDoc.setE01SELDTP(req.getParameter("Type"));
			msgPayDoc.setH01FLGWK1("P");
			msgPayDoc.send();
			msgPayDoc.destroy();

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

			if (newmessage.getFormatName().equals("EDL082501")) {
				try {
					msgPayDoc =new datapro.eibs.beans.EDL082501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPayDoc = (EDL082501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("payDoc", msgPayDoc);
				//ses.setAttribute("docData",docData);
				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0828_pay_ap_det.jsp");
						callPage(LangPath + "EDL0828_pay_ap_det.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors

					try {
						flexLog("About to call Page4: " + LangPath + "error_viewer.jsp");
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
	protected void procReqCancelDetail(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0145DSMessage msgPay = null;
		EDL082501Message msgPayDoc = null;
		JBListRec docList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		Socket s = null;
		
		String Opt = "";
		String LN = "0";
		String ID = "0";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgPay = (EWD0145DSMessage) mc.getMessageRecord("EWD0145DS");			
			msgPay.setE01SELLNS(req.getParameter("Loan"));
			msgPay.setE01SELIDE(req.getParameter("ID"));
			msgPay.setE01SELGIR(req.getParameter("Doc"));
			msgPay.setE01SELSTS("A");
			msgPay.send();
			msgPay.destroy();
			flexLog("EWD0145DS Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		int colNum = 30;

		flexLog("Initializing java beans into the session");
		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			docList =new datapro.eibs.beans.JBListRec();
			docList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Data
		try {

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EWD0145DS")) {

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				boolean myFirstRow = false;
				BigDecimal total = new BigDecimal("0");
				BigDecimal capital = new BigDecimal("0");
				BigDecimal interest = new BigDecimal("0");
				BigDecimal penalty = new BigDecimal("0");
				
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				//while (true) {

					msgPay = (EWD0145DSMessage) newmessage;

					marker = msgPay.getE01ENDFLD();
					total = new BigDecimal("0");
					capital = new BigDecimal("0");
					interest = new BigDecimal("0");
					penalty = new BigDecimal("0");
					
					//if (marker.equals("*")) {
					//	break;
					//} else {
						
							capital = msgPay.getBigDecimalE01DLDNAM().subtract(msgPay.getBigDecimalE01DLDPAM());
							total = capital;
							penalty = msgPay.getBigDecimalE01DLDIPA().subtract(msgPay.getBigDecimalE01DLDIPD());
							total = total.add(penalty);
							interest = msgPay.getBigDecimalE01DLDOIA().subtract(msgPay.getBigDecimalE01DLDPIA());
							total = total.add(interest);
							total = total.add(msgPay.getBigDecimalE01DLDREV());
							
						    myRow[0] = msgPay.getE01DLDNDR(); // Doc. Number
							myRow[1] = msgPay.getE01DLDDID(); // Acceptant ID
							myRow[2] = msgPay.getE01DLDNME(); // Acceptant Name
							myRow[3] = Util.formatCCY(msgPay.getE01DLDOAM()); // Amount 
							myRow[4] = Util.formatDate(msgPay.getE01DLDMA1(),msgPay.getE01DLDMA2(),msgPay.getE01DLDMA3()); // Mat. Date
							myRow[5] = Util.formatCCY(penalty.toString()); // penalty amount
							myRow[6] = msgPay.getE01DLDDTP(); // Type Doc.
							myRow[7] = msgPay.getE01DLDNLN(); // Loans Number
							myRow[8] = msgPay.getE01DLDRST(); // Status
							myRow[9] = msgPay.getE01CUSNA1(); // Client
						    
						    myRow[10] = msgPay.getE01DLDBNK(); // bank
						    myRow[11] = msgPay.getE01DLDBRN(); // branch
						    myRow[12] = msgPay.getE01DLDCCY(); // currency
						    myRow[13] = msgPay.getE01DLDGLN(); // GL
						    myRow[14] = Util.formatCCY(capital.toString()); // capital amount
						    myRow[15] = Util.formatCCY(interest.toString()); // interest amount
						    myRow[16] = Util.formatCCY(msgPay.getE01DLDREV()); // rev. amount
						    myRow[17] = Util.formatCCY(total.toString()); // total amount
						    myRow[18] = Util.formatDate(msgPay.getE01DLDRF1(),msgPay.getE01DLDRF2(),msgPay.getE01DLDRF3()); // Open. Date
							myRow[19] = msgPay.getE01DLDGPD(); // Grace Period
							myRow[20] = msgPay.getE01DLDREW()+" - "+msgPay.getE01DSCREW(); //payment via
							myRow[21] = msgPay.getE01DLDPYW()+" - "+msgPay.getE01DSCPYW(); //plaza de pago
							myRow[22] = msgPay.getE01DLDREM()+" - "+msgPay.getE01DSCREM(); //agente cobrador
							//for checks
							myRow[23] = msgPay.getE01DLDKBK(); // check bank
						    myRow[24] = msgPay.getE01DLDKBR(); // check branch
						    myRow[25] = msgPay.getE01DLDCTA(); // account number
						    myRow[26] = msgPay.getE01DLDCHQ(); // check number
							myRow[27] = Util.formatDate(msgPay.getE01DLDPD1(),msgPay.getE01DLDPD2(),msgPay.getE01DLDPD3()); // Envio a camara
							myRow[28] = msgPay.getE01DEACUN(); // Client Number
							myRow[29] = msgPay.getE01DLDARC(); // Interest Rate
							
						

						docList.addRow(myFlag, myRow);
					//}

					///newmessage = mc.receiveMessage();

				//}

				// reset mc
				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				 } 
				catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					return;
				 }
				
				// Send Initial data
				try {
					msgPayDoc = (EDL082501Message) mc.getMessageRecord("EDL082501");
					msgPayDoc.setH01USERID(user.getH01USR());
					msgPayDoc.setH01PROGRM("EDL0825");
					msgPayDoc.setH01TIMSYS(getTimeStamp());
					msgPayDoc.setH01SCRCOD("01");
					msgPayDoc.setH01OPECOD("0002");
					msgPayDoc.setE01SELNLN(req.getParameter("Loan"));
					msgPayDoc.setE01SELDID(req.getParameter("ID"));
					msgPayDoc.setE01SELNDR(req.getParameter("Doc"));
					msgPayDoc.setE01SELDTP(req.getParameter("Type"));
					msgPayDoc.setH01FLGWK1("Q");
					msgPayDoc.send();
					msgPayDoc.destroy();

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

					if (newmessage.getFormatName().equals("EDL082501")) {
						try {
							msgPayDoc =new datapro.eibs.beans.EDL082501Message();
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

				   		 msgPayDoc = (EDL082501Message) newmessage;
				    
				    	flexLog("Putting java beans into the session");
				    	ses.setAttribute("error", msgError);
				    	ses.setAttribute("docList", docList);
				    	ses.setAttribute("payDoc", msgPayDoc);
				    	
				    	if (IsNotError) { // There are no errors 
							try {
								flexLog("About to call Page3: " + LangPath + "EDL0825_cancel_ap_det.jsp");
								callPage(LangPath + "EDL0828_cancel_ap_det.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else { // There are errors
							try {
								flexLog("About to call Page4: " + LangPath + "error_viewer.jsp");
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
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}