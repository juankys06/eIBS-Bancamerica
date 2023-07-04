package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import java.util.Calendar;

import datapro.eibs.sockets.*;

public class JSEDL0370 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINTENANCE = 4;
	protected static final int R_CANCEL = 5;
	protected static final int A_CANCEL = 6;
	protected static final int R_LIST_BRK = 7;
	protected static final int R_HELP_LIST_BRK = 8;
	protected static final int R_HELP_LIST_DED = 9;
	protected static final int R_INQUIRY = 10;

	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int R_ENTER_MAINT = 300;
	protected static final int R_ENTER_CANCEL = 500;
	protected static final int A_ENTER_NEW = 200;
	protected static final int A_ENTER_MAINT = 400;
	protected static final int A_ENTER_CANCEL = 600;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0370() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0370");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
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
		EDL037001Message msgDed = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgDed = (EDL037001Message) mc.getMessageRecord("EDL037001");
			msgDed.setH01USERID(user.getH01USR());
			msgDed.setH01PROGRM("EDL0370");
			msgDed.setH01TIMSYS(getTimeStamp());
			msgDed.setH01SCRCOD("01");
			msgDed.setH01OPECOD("0002");

			try {
				msgDed.setE01DEAACC(req.getParameter("ACCNUM"));
			} catch (Exception e) {
			}
			try {
				msgDed.setE01DLICDE(req.getParameter("E01DLICDE"));
			} catch (Exception e) {
			}
			msgDed.send();
			msgDed.destroy();
			flexLog("EDL037001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL037001")) {
				try {
					msgDed = new datapro.eibs.beans.EDL037001Message();
					flexLog("EDL037001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDed = (EDL037001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deduct", msgDed);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0370_deduct_basic.jsp");
						callPage(
							LangPath + "EDL0370_deduct_basic.jsp",
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
								+ "EDL0150_ln_basic.jsp");
						callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
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
	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL037001Message msgDed = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		String opeCode = "";
		String actCode = "";
		String row = "";
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			row = req.getParameter("ROW");
			actCode = req.getParameter("OPT");
			opeCode = (actCode.equals("D")) ? "0009" : "0005";
		} catch (Exception ex) {
			row = "0";
			actCode = "M";
			opeCode = "0005";
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgDed = (EDL037001Message) mc.getMessageRecord("EDL037001");
			msgDed.setH01USERID(user.getH01USR());
			msgDed.setH01PROGRM("EDL0370");
			msgDed.setH01TIMSYS(getTimeStamp());
			msgDed.setH01SCRCOD("01");
			msgDed.setH01OPECOD(opeCode);
			msgDed.setH01FLGWK1(req.getParameter("OPT"));
			// all the fields here
			java.util.Enumeration enu = msgDed.fieldEnumeration();
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
			msgDed.send();
			msgDed.destroy();
			flexLog("EDL037001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL037001")) {
				try {
					msgDed = new datapro.eibs.beans.EDL037001Message();
					flexLog("EDL037001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDed = (EDL037001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deduct", msgDed);

				if (IsNotError) { // There are no errors	
					// put on session dedlist
					try {
						JBListRec dedList = (JBListRec)ses.getAttribute("ded");	
						if (dedList != null) {	
							int pos =  Integer.parseInt(row) - 1;
							if (actCode.equals("D")) {
								dedList.removeRow(pos);
							} else {
								String myRow[] = new String[29];
								for (int i=0; i<29; i++) {
									myRow[i] = "";
								}
							    myRow[1] =  msgDed.getE01DEAACC().trim();	// 
							    myRow[4] =  msgDed.getE01DLICDE().trim();	// 
							    myRow[5] =  msgDed.getE01DLIDPM().trim();	// 
							    myRow[6] =  msgDed.getE01DLIFAC().trim();	// 
							    myRow[7] =  msgDed.getE01DLIIVA().trim();	// 
							    myRow[10] =  msgDed.getE01DLINME().trim();//CHARGE NAME 
								myRow[12] =  msgDed.getE01DLIBNK().trim();//
								myRow[13] =  msgDed.getE01DLIBRN().trim();//
								myRow[14] =  msgDed.getE01DLICCY().trim();//
								myRow[15] =  msgDed.getE01DLIGLN().trim();//
							    myRow[16] =  msgDed.getE01DLIREF().trim();// 
								myRow[17] =  "L";//
							    myRow[23] =  msgDed.getE01DLISEL().trim();//
								myRow[24] =  msgDed.getE01DLIPMF().trim();//
								myRow[26] =  msgDed.getE01DLINPM().trim();//
								myRow[27] =  msgDed.getE01DLINPD().trim();//
								myRow[28] =  msgDed.getE01DLINPY().trim();//
																  
								if (actCode.equals("M")) {
									dedList.setRecord(myRow[1],1,pos);	// 
									dedList.setRecord(myRow[4],4,pos);	// 
									dedList.setRecord(myRow[5],5,pos);	// 
									dedList.setRecord(myRow[6],6,pos);	// 
									dedList.setRecord(myRow[7],7,pos);	// 
									dedList.setRecord(myRow[10],10,pos);//CHARGE NAME
									dedList.setRecord(myRow[12],12,pos);// 
									dedList.setRecord(myRow[13],13,pos);// 
									dedList.setRecord(myRow[14],14,pos);// 
									dedList.setRecord(myRow[15],15,pos);//  
									dedList.setRecord(myRow[16],16,pos);// 
									dedList.setRecord(myRow[17],17,pos);//
									dedList.setRecord(myRow[23],23,pos);//
									dedList.setRecord(myRow[24],24,pos);//	
									dedList.setRecord(myRow[26],26,pos);//
									dedList.setRecord(myRow[27],27,pos);//
									dedList.setRecord(myRow[28],28,pos);//					
								} else { //new
									dedList.addRow("", myRow);
								}
							}
							ses.setAttribute("ded",dedList);
						}
					} catch (Exception e) {
						
					}
					  
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0370_ded_update.jsp?ACT="
								+ actCode
								+ "&ROW="
								+ row);
						res.sendRedirect(
							super.srctx
								+ LangPath
								+ "EDL0370_ded_update.jsp?ACT="
								+ actCode
								+ "&ROW="
								+ row);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0370_ded_basic.jsp");
						if (actCode.equals("N")) {
							res.sendRedirect(
								super.srctx
									+ LangPath
									+ "EDL0370_ded_basic.jsp?NEW=Y");
						} else {
							res.sendRedirect(
								super.srctx
									+ LangPath
									+ "EDL0370_ded_basic.jsp?ROW="
									+ row);
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqBrkList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL037002Message msgBrk = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		JBListRec beanList = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setIdentifier(req.getParameter("ACCNUM"));

		try {
			msgBrk = (EDL037002Message) mc.getMessageRecord("EDL037002");
			msgBrk.setH02USERID(user.getH01USR());
			msgBrk.setH02PROGRM("EDL0370");
			msgBrk.setH02TIMSYS(getTimeStamp());
			msgBrk.setH02SCRCOD("01");
			msgBrk.setH02OPECOD("0020");
			try {
				msgBrk.setE02DEAACC(req.getParameter("ACCNUM"));
			} catch (Exception e) {
			}
			msgBrk.send();
			msgBrk.destroy();
			flexLog("EDL037002 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL037002")) {

				int colNum = 11;
				try {
					beanList = new datapro.eibs.beans.JBListRec();
					beanList.init(colNum);
					flexLog("EDL037002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";

				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}
				while (true) {

					msgBrk = (EDL037002Message) newmessage;

					marker = msgBrk.getH02FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						myRow[0] = msgBrk.getE02DEAACC(); //account
						myRow[1] = msgBrk.getE02DLIBNK(); //bank
						myRow[2] = msgBrk.getE02DLIBRN(); //branch
						myRow[3] = msgBrk.getE02DLICCY(); //currency
						myRow[4] = msgBrk.getE02DLICDE(); //code
						myRow[5] = msgBrk.getE02DLIDPM(); //deduct value
						myRow[6] = msgBrk.getE02DLIFAC(); //factor
						myRow[7] = msgBrk.getE02DLIGLN(); //general ledger
						myRow[8] = msgBrk.getE02DLIIVA(); //IVA
						myRow[9] = msgBrk.getE02DLINME(); //cia. name
						myRow[10] = msgBrk.getE02DLITYP(); //type

						beanList.addRow(myFlag, myRow);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("brkList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					req.setAttribute(
						"NameSearch",
						req.getParameter("NameSearch"));
					req.setAttribute(
						"FromRecord",
						req.getParameter("FromRecord"));
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0370_brk_list.jsp");
					callPage(LangPath + "EDL0370_brk_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL037001Message msgDed = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		String row = "0";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			row = req.getParameter("ROW");
			if (row == null)
				row = "0";
		} catch (Exception e) {
			row = "0";
		}
		// Send Initial data
		try {
			msgDed = (EDL037001Message) mc.getMessageRecord("EDL037001");
			msgDed.setH01USERID(user.getH01USR());
			msgDed.setH01PROGRM("EDL0370");
			msgDed.setH01TIMSYS(getTimeStamp());
			msgDed.setH01SCRCOD("01");
			msgDed.setH01OPECOD("0002");

			try {
				msgDed.setE01DEAACC(req.getParameter("ACCNUM"));
			} catch (Exception e) {
			}
			try {
				msgDed.setE01DLICDE(req.getParameter("COD"));
			} catch (Exception e) {
			}
			msgDed.send();
			msgDed.destroy();
			flexLog("EDL037001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL037001")) {
				try {
					msgDed = new datapro.eibs.beans.EDL037001Message();
					flexLog("EDL037001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDed = (EDL037001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deduct", msgDed);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0370_ded_basic.jsp");
						res.sendRedirect(
							super.srctx
								+ LangPath
								+ "EDL0370_ded_basic.jsp?ROW="
								+ row);
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqNewDed(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL037001Message msgDed = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		JBListRec brkList = null;
		int row;
		String typ = "";

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		brkList = (datapro.eibs.beans.JBListRec) ses.getAttribute("brkList");
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception ex) {
			row = 0;
		}
		brkList.setCurrentRow(row);

		try {
			typ = brkList.getRecord(10);
		} catch (Exception e){
			typ = "";
		}

		// Send Initial data
		try {
			msgDed = (EDL037001Message) mc.getMessageRecord("EDL037001");
			msgDed.setH01USERID(user.getH01USR());
			msgDed.setH01PROGRM("EDL0370");
			msgDed.setH01TIMSYS(getTimeStamp());
			msgDed.setH01SCRCOD("01");
			msgDed.setH01OPECOD("0001");
			msgDed.setE01DEAACC(brkList.getRecord(0));
			msgDed.setE01DLICDE(brkList.getRecord(4));
			msgDed.send();
			msgDed.destroy();
			flexLog("EDL037001 Message Sent");
		} catch (Exception e) {
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
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL037001")) {
				try {
					msgDed = new datapro.eibs.beans.EDL037001Message();
					flexLog("EDL037001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDed = (EDL037001Message) newmessage;

				msgDed.setE01DLITYP(typ);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deduct", msgDed);

				if (IsNotError) { // There are no errors

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0370_ded_basic.jsp");
						res.sendRedirect(
							super.srctx
								+ LangPath
								+ "EDL0370_ded_basic.jsp?NEW=Y");
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL037001Message msgDed = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		String row = "0";
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		try {
			row = req.getParameter("ROW");
			if (row == null)
				row = "0";
		} catch (Exception e) {
			row = "0";
		}
		// Send Initial data
		try {
			msgDed = (EDL037001Message) mc.getMessageRecord("EDL037001");
			msgDed.setH01USERID(user.getH01USR());
			msgDed.setH01PROGRM("EDL0370");
			msgDed.setH01TIMSYS(getTimeStamp());
			msgDed.setH01SCRCOD("01");
			msgDed.setH01OPECOD("0002");
	
			try {
				msgDed.setE01DEAACC(req.getParameter("ACCNUM"));
			} catch (Exception e) {
			}
			try {
				msgDed.setE01DLICDE(req.getParameter("COD"));
			} catch (Exception e) {
			}
			msgDed.send();
			msgDed.destroy();
			flexLog("EDL037001 Message Sent");
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
	
			if (newmessage.getFormatName().equals("EDL037001")) {
				try {
					msgDed = new datapro.eibs.beans.EDL037001Message();
					flexLog("EDL037001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgDed = (EDL037001Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("deduct", msgDed);
	
				if (IsNotError) { // There are no errors
	
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0370_inq_ded_basic.jsp");
						res.sendRedirect(
							super.srctx
								+ LangPath
								+ "EDL0370_inq_ded_basic.jsp?ROW="
								+ row);
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

			int screen = R_ENTER_MAINT;

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
						// Request
						case R_MAINTENANCE :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case R_LIST_BRK :
							procReqBrkList(mc, msgUser, req, res, session);
							break;
						case R_HELP_LIST_BRK :
							procReqHelpBrkList(mc, msgUser, req, res, session);
							break;
						case R_HELP_LIST_DED :
							procReqHelpDedList(mc, msgUser, req, res, session);
							break;
						case R_NEW :
							procReqNewDed(mc, msgUser, req, res, session);
							break;
						case R_INQUIRY :
							procReqInquiry(mc, msgUser, req, res, session);
							break;							
							// Action				
						case A_MAINTENANCE :
							procActionMaintenance(mc, msgUser, req, res, session);
							break;
						case A_ENTER_MAINT :
							procActionEnterMaint(mc, msgUser, req, res, session);
							break;

							// END Entering

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
	
	protected void procReqHelpBrkList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL037002Message msgBrk = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		JBListRec beanList = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			msgBrk = (EDL037002Message) mc.getMessageRecord("EDL037002");
			msgBrk.setH02USERID(user.getH01USR());
			msgBrk.setH02PROGRM("EDL0370");
			msgBrk.setH02TIMSYS(getTimeStamp());
			msgBrk.setH02SCRCOD("01");
			msgBrk.setH02OPECOD("0020");
			try {
				msgBrk.setE02DEAACC(req.getParameter("ACCNUM"));
			} catch (Exception e) {
			}
			msgBrk.send();
			msgBrk.destroy();
			flexLog("EDL037002 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL037002")) {

				int colNum = 11;
				try {
					beanList = new datapro.eibs.beans.JBListRec();
					beanList.init(colNum);
					flexLog("EDL037002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";

				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}
				while (true) {

					msgBrk = (EDL037002Message) newmessage;

					marker = msgBrk.getH02FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						myRow[0] = msgBrk.getE02DEAACC(); //account
						myRow[1] = msgBrk.getE02DLIBNK(); //bank
						myRow[2] = msgBrk.getE02DLIBRN(); //branch
						myRow[3] = msgBrk.getE02DLICCY(); //currency
						myRow[4] = msgBrk.getE02DLICDE(); //code
						myRow[5] = msgBrk.getE02DLIDPM(); //deduct value
						myRow[6] = msgBrk.getE02DLIFAC(); //factor
						myRow[7] = msgBrk.getE02DLIGLN(); //general ledger
						myRow[8] = msgBrk.getE02DLIIVA(); //IVA
						myRow[9] = msgBrk.getE02DLINME(); //cia. name
						myRow[10] = msgBrk.getE02DLITYP(); //type

						beanList.addRow(myFlag, myRow);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("brkList", beanList);

				try {
					req.setAttribute(
						"NameSearch",
						req.getParameter("NameSearch"));
					req.setAttribute(
						"FromRecord",
						req.getParameter("FromRecord"));
					flexLog("About to call Page: "	+ LangPath	+ "EDL0370_help_brk_list.jsp");
					callPage(LangPath + "EDL0370_help_brk_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}
	
	protected void procReqHelpDedList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL037002Message msgBrk = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		JBListRec beanList = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			msgBrk = (EDL037002Message) mc.getMessageRecord("EDL037002");
			msgBrk.setH02USERID(user.getH01USR());
			msgBrk.setH02PROGRM("EDL0370");
			msgBrk.setH02TIMSYS(getTimeStamp());
			msgBrk.setH02SCRCOD("01");
			msgBrk.setH02OPECOD("0010");
			try {
				msgBrk.setE02DEAACC(req.getParameter("ACCNUM"));
			} catch (Exception e) {
			}
			msgBrk.send();
			msgBrk.destroy();
			flexLog("EDL037002 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}

		// Receiving
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL037002")) {

				int colNum = 11;
				try {
					beanList = new datapro.eibs.beans.JBListRec();
					beanList.init(colNum);
					flexLog("EDL037002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				String myFlag = "";

				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}
				while (true) {

					msgBrk = (EDL037002Message) newmessage;

					marker = msgBrk.getH02FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
      
						myRow[0] = msgBrk.getE02DEAACC(); //account
						myRow[1] = msgBrk.getE02DLIBNK(); //bank
						myRow[2] = msgBrk.getE02DLIBRN(); //branch
						myRow[3] = msgBrk.getE02DLICCY(); //currency
						myRow[4] = msgBrk.getE02DLICDE(); //code
						myRow[5] = msgBrk.getE02DLIDPM(); //deduct value
						myRow[6] = msgBrk.getE02DLIFAC(); //factor
						myRow[7] = msgBrk.getE02DLIGLN(); //general ledger
						myRow[8] = msgBrk.getE02DLIIVA(); //IVA
						myRow[9] = msgBrk.getE02DLINME(); //cia. name
						myRow[10] = msgBrk.getE02DLITYP(); //type

						beanList.addRow(myFlag, myRow);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("brkList", beanList);

				try {
					req.setAttribute(
						"NameSearch",
						req.getParameter("NameSearch"));
					req.setAttribute(
						"FromRecord",
						req.getParameter("FromRecord"));
					flexLog("About to call Page: "	+ LangPath	+ "EDL0370_help_ded_list.jsp");
					callPage(LangPath + "EDL0370_help_ded_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}

}