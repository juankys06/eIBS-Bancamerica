package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.math.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;

public class JSEDL0825 extends datapro.eibs.master.SuperServlet {

	protected String LangPath = "S";
	protected static final int A_ENTER = 2;
	protected static final int A_PAYMENT = 4;
	protected static final int A_CANCEL = 6;
	protected static final int R_ENTER = 1;
	protected static final int R_PAYMENT = 3;
	protected static final int R_CANCEL = 5;
	
	/**
	 * JSEDL0825 constructor comment.
	 */
	public JSEDL0825() {
		super();
	}
	
	public void destroy() {

		flexLog("free resources used by JSEDL0130");

	}
	
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

			int screen = R_ENTER;

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
					
					// Request
					case R_ENTER :
						procReqEnterPay(mc, msgUser, req, res, session);
						break;
					case R_PAYMENT :
						procReqPayment(mc, msgUser, req, res, session);
						break;
					case R_CANCEL :
						procReqCancel(mc, msgUser, req, res, session);
						break;
						// Action
					case A_ENTER :
						procActionEnterPay(mc, msgUser, req, res, session);
						break;
					case A_PAYMENT :
						procActionPayment(mc, msgUser, req, res, session);
						break;
					case A_CANCEL :
						procActionCancel(mc, msgUser, req, res, session);
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
	protected void procActionEnterPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EWD0145DSMessage msgPay = null;
		JBListRec docList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		DataPayDoc docData = null;

		String Opt = "";
		String LN = "0";
		String ID = "0";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			docData = new datapro.eibs.beans.DataPayDoc();
			msgPay = (EWD0145DSMessage) mc.getMessageRecord("EWD0145DS");
			if (req.getParameter("E01SELLNS") == null
				|| req.getParameter("E01SELLNS").equals("0")
				|| req.getParameter("E01SELLNS").trim().equals("")) {
				LN = "0";
			} else {
				LN = req.getParameter("E01SELLNS");
				Opt = "LN";
			}
			if (req.getParameter("E01SELIDE") == null
				|| req.getParameter("E01SELIDE").equals("0")
				|| req.getParameter("E01SELIDE").trim().equals("")) {
				ID = " ";
			} else {
				ID = req.getParameter("E01SELIDE");
				if (Opt.equals("LN"))
					Opt = "ID-LN";
				else
					Opt = "ID";
			}
			msgPay.setE01SELLNS(LN);
			msgPay.setE01SELIDE(ID);
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
			docList = new datapro.eibs.beans.JBListRec();
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

				while (true) {

					msgPay = (EWD0145DSMessage) newmessage;

					marker = msgPay.getE01ENDFLD();
					total = new BigDecimal("0");
					capital = new BigDecimal("0");
					interest = new BigDecimal("0");
					penalty = new BigDecimal("0");
					
					if (marker.equals("*")) {
						break;
					} else {
						
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
							
						if (!myFirstRow) {
							myFirstRow = true;
						}

						docList.addRow(myFlag, myRow);
					}

					newmessage = mc.receiveMessage();

				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("docList", docList);
				ses.setAttribute("docData", docData);
				
				try {
					if (userPO.getOption().equals("P")){
						flexLog("About to call Page: " + LangPath + "EDL0825_pay_list.jsp");
						res.sendRedirect(super.srctx + LangPath + "EDL0825_pay_list.jsp?OPT=" + Opt);
					}
					else {
						flexLog("About to call Page: " + LangPath + "EDL0825_cancel_list.jsp");
						res.sendRedirect(super.srctx + LangPath + "EDL0825_cancel_list.jsp?OPT=" + Opt);
					}
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
	protected void procActionPayment(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL082501Message msgPayDoc = null;
		ELEERRMessage msgError = null;
		DataPayDoc docData = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		docData = (datapro.eibs.beans.DataPayDoc) ses.getAttribute("docData");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPayDoc = (EDL082501Message) ses.getAttribute("payDoc");
			msgPayDoc.setH01USERID(user.getH01USR());
			msgPayDoc.setH01PROGRM("EDL0825");
			msgPayDoc.setH01TIMSYS(getTimeStamp());
			msgPayDoc.setH01SCRCOD("01");
			msgPayDoc.setH01OPECOD("0005");
			msgPayDoc.setH01FLGWK1("P");
			// all the fields here
			java.util.Enumeration enu = msgPayDoc.fieldEnumeration();
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

			//msgPayDoc.send();
			mc.sendMessage(msgPayDoc);
			msgPayDoc.destroy();
			flexLog("EDL082501 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL082501")) {
				try {
					msgPayDoc = new datapro.eibs.beans.EDL082501Message();
					flexLog("EDL082501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPayDoc = (EDL082501Message) newmessage;

				flexLog("Putting java beans into the session");
				try {
					docData.setPAGOGL(req.getParameter("E01PAGOGL"));
					docData.setPAGCON(req.getParameter("E01PAGCON"));
					docData.setPAGOPC(req.getParameter("E01PAGOPC"));
					docData.setPAGOBK(req.getParameter("E01PAGOBK"));
					docData.setPAGOBR(req.getParameter("E01PAGOBR"));
					docData.setPAGOAC(req.getParameter("E01PAGOAC"));
					docData.setPAGOCY(req.getParameter("E01PAGOCY"));
					docData.setPAGVD1(req.getParameter("E01PAGVD1"));
					docData.setPAGVD2(req.getParameter("E01PAGVD2"));
					docData.setPAGVD3(req.getParameter("E01PAGVD3"));
					docData.setDEANR1(req.getParameter("E01DEANR1"));
					docData.setDEANR2(req.getParameter("E01DEANR2"));
				} catch (Exception e) {
				}
				ses.setAttribute("error", msgError);
				ses.setAttribute("payDoc", msgPayDoc);
				ses.setAttribute("docData", docData);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page1: " + LangPath + "EDL0825_pay_list.jsp");
						res.setContentType("text/html");
						printClose(res.getWriter());
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "EDL0825_pay_doc.jsp");
						callPage(LangPath + "EDL0825_pay_doc.jsp", req, res);
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
	protected void procActionCancel(
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
		String ROW="0";
		
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		//docData = (datapro.eibs.beans.DataPayDoc) ses.getAttribute("docData");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgPayDoc = (EDL082501Message) ses.getAttribute("payDoc");
			msgPayDoc.setH01USERID(user.getH01USR());
			msgPayDoc.setH01PROGRM("EDL0825");
			msgPayDoc.setH01TIMSYS(getTimeStamp());
			msgPayDoc.setH01SCRCOD("01");
			msgPayDoc.setH01OPECOD("0005");
			msgPayDoc.setH01FLGWK1("Q");
			// all the fields here
			ROW=req.getParameter("ROW");
			
			java.util.Enumeration enu = msgPayDoc.fieldEnumeration();
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

			//msgPayDoc.send();
			mc.sendMessage(msgPayDoc);
			msgPayDoc.destroy();
			flexLog("EDL082501 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL082501")) {
				try {
					msgPayDoc = new datapro.eibs.beans.EDL082501Message();
					flexLog("EDL082501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPayDoc = (EDL082501Message) newmessage;

				flexLog("Putting java beans into the session");
				
				ses.setAttribute("error", msgError);
				ses.setAttribute("payDoc", msgPayDoc);
				//ses.setAttribute("docData", docData);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page1: " + LangPath + "EDL0825_cancel_list.jsp");
						res.setContentType("text/html");
						printClose(res.getWriter());
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page2: " + LangPath + "EDL0825_cancel_doc.jsp?CurrRow="+ROW);
						res.sendRedirect(super.srctx + LangPath + "EDL0825_cancel_doc.jsp?CurrRow="+ROW);
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
	protected void procReqEnterPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		String Opt="";
		userPO = new UserPos();
		userPO.setPurpose("NEW");
		try {
			Opt=req.getParameter("Opt");
			} 
		catch (Exception e) {
			flexLog("Invalid Parametres Opt " + e);
		}
		userPO.setOption(Opt);
		
		msgError = new ELEERRMessage();

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
		try {
			flexLog("About to call Page3: " + LangPath + "EDL0825_pay_enter.jsp");
			callPage(LangPath + "EDL0825_pay_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqPayment(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL082501Message msgPayDoc = null;
		ELEERRMessage msgError = null;
		DataPayDoc docData = null;
		boolean IsNotError = false;

		try {
			msgError =new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		docData = (datapro.eibs.beans.DataPayDoc) ses.getAttribute("docData");

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

			if (newmessage.getFormatName().equals("EDL082501")) {
				try {
					msgPayDoc = new datapro.eibs.beans.EDL082501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPayDoc = (EDL082501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("payDoc", msgPayDoc);
				ses.setAttribute("docData", docData);
				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0825_pay_doc.jsp");
						callPage(LangPath + "EDL0825_pay_doc.jsp", req, res);
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
	protected void procReqCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL082501Message msgPayDoc = null;
		ELEERRMessage msgError = null;
		DataPayDoc docData = null;
		JBListRec docList = null;
		boolean IsNotError = false;
		String ROW="";
		
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		docList = (datapro.eibs.beans.JBListRec) ses.getAttribute("docList");
		try {
			ROW=req.getParameter("Row");
		} catch (Exception e) {
			ROW="0";
		}
		
		
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

			if (newmessage.getFormatName().equals("EDL082501")) {
				try {
					msgPayDoc = new datapro.eibs.beans.EDL082501Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPayDoc = (EDL082501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("payDoc", msgPayDoc);
				ses.setAttribute("docList", docList);
				
				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "EDL0825_cancel_doc.jsp?CurrRow="+ROW);
						res.sendRedirect(super.srctx + LangPath + "EDL0825_cancel_doc.jsp?CurrRow="+ROW);
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
}