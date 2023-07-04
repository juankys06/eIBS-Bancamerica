package datapro.eibs.tools;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import datapro.eibs.beans.EDI001001Message;
import datapro.eibs.beans.EDI001002Message;
import datapro.eibs.beans.EDI001003Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.UserPos;
import datapro.eibs.generic.JBParseTree;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

// import datapro.eibs.generic.JODBConn;
// import datapro.eibs.generic.JOSQLExec;

public class JSFormsHistory extends datapro.eibs.master.SuperServlet {

	// CIF options
	static final int R_DOCUMENTS = 1;
	static final int A_DOCUMENTS = 2;
	static final int R_INQ_DOCS = 3;

	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSFormsHistory() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {
		/*
		 	if (!JSEIBSProp.getImgToIFS()) 
				try {
					sqlExec.close();
					dbConn.close();
				 	//psRead.close();   
					//cnRead.close();
				}
				catch (Exception e) {
					flexLog("Exception in Destroy method = " + e);
				}
		*/
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		/*
		 	if (!JSEIBSProp.getImgToIFS()) {
		 		try {
					flexLog("initialize resource related to JDBC connection");
					flexLog("driver = " + JSEIBSProp.getDriver());
					//Class.forName(JSEIBSProp.getDriver());
					flexLog("Connection = " + JSEIBSProp.getDbURL() + " - " + JSEIBSProp.getUserid() + " - " + JSEIBSProp.getPassword());
		
					dbConn.init(JSEIBSProp.getDataSource(),JSEIBSProp.getUserid(),JSEIBSProp.getPassword());
					sqlExec.initSDF(10,dbConn); 
					
					//cnRead = DriverManager.getConnection(JSEIBSProp.getDbURL(), JSEIBSProp.getUserid(), JSEIBSProp.getPassword());
					//psRead = cnRead.prepareStatement("SELECT EDICOD,EDISEQ,EDIDTE,EDINAM,EDIIMG FROM " + JSEIBSProp.getDbSchema() + ".EDIIMG WHERE EDICOD = ? ORDER BY EDISEQ,EDINAM");
		
				}
				catch (Exception e) {
					e.printStackTrace();
					flexLog("Exception in Creator method = " + e);
				}
				
		//		initDataSource("eIBSDS");
				
		 	}
		*/

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	private synchronized void procActionDocList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		JBListRec beanList = null;
		UserPos userPO = null;

		try {
			userPO = (UserPos) ses.getValue("userPO");

			int option = Integer.parseInt(req.getParameter("opt"));
			int row = Integer.parseInt(req.getParameter("ROW"));

			beanList = (JBListRec) ses.getValue("docList");

			beanList.initRow();
			while (beanList.getNextRow()) {
				beanList.setRecord(
					req.getParameter("E01DCIFRE_" + beanList.getCurrentRow()).toUpperCase(),
					4,
					beanList.getCurrentRow());
				beanList.setRecord(
					req.getParameter("E01DCISTA_" + beanList.getCurrentRow()).toUpperCase(),
					5,
					beanList.getCurrentRow());
			}

			ses.putValue("docList", beanList);

			// Update for all options
			procActionDocListUpdate(mc, user, req, res, ses);

			beanList.setCurrentRow(row);

			switch (option) {
				case 1 : // Update
					res.sendRedirect(super.srctx + LangPath + "EDI0010_doc_list.jsp?ROW=" + row);
					break;
				case 2 : // Scan
					if (super.scanActive) {
						String Number = "";
						if (userPO.getHeader22().equals("C")) {
							Number = userPO.getCusNum();
						} else {
							Number = userPO.getIdentifier();
						}
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.tools.JSScan?Name="
								+ beanList.getRecord(0)
								+ "&Desc="
								+ beanList.getRecord(3)
								+ "&URL="
								+ getServerRoot(req)
								+ super.webAppPath
								+ "/servlet/datapro.eibs.tools.JSScanDocDone?USERID="
								+ user.getH01USR()
								+ "@NUMBER="
								+ Number
								+ "@TYPE="
								+ userPO.getHeader22()
								+ "@ADD="
								+ beanList.getRecord(0)
								+ "@TNU="
								+ beanList.getRecord(1)
								+ "@SEQ="
								+ beanList.getRecord(2)
								+ "@DES="
								+ beanList.getRecord(3).trim().replace(' ', '^')
								+ "@FRE="
								+ beanList.getRecord(4)
								+ "@TYP="
								+ beanList.getRecord(7));
					} else {
						res.sendRedirect(super.srctx + LangPath + "EDI0010_doc_list.jsp?ROW=" + row);
					}
					break;
				case 3 : // View
					if (beanList.getRecord(5).equalsIgnoreCase("CO")) {
						if (JSEIBSProp.getImgToIFS())
							procReqDocInfoXML(beanList.getRecord(0), beanList.getRecord(3), ses);
						else
							procReqDocInfoJDBC(beanList.getRecord(0), beanList.getRecord(3), ses);
					}
					res.sendRedirect(super.srctx + LangPath + "EDI0010_doc_list.jsp?ROW=" + row);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.bgPage);
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
	private synchronized void procActionDocListUpdate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDI001001Message msgList = null;
		JBListRec beanList = null;
		UserPos userPO = null;

		userPO = (UserPos) ses.getValue("userPO");
		beanList = (JBListRec) ses.getValue("docList");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDI001001Message) mc.getMessageRecord("EDI001001");

			beanList.initRow();
			while (beanList.getNextRow()) {
				msgList.setH01USERID(user.getH01USR());
				msgList.setH01PROGRM("EDI0010");
				msgList.setH01TIMSYS(getTimeStamp());
				msgList.setH01SCRCOD("01");
				msgList.setH01FLGWK1("S");
				msgList.setH01FLGWK2(userPO.getHeader22());
				if (userPO.getHeader22().equals("C")) {
					msgList.setE01DCIACC(userPO.getCusNum());
				} else {
					msgList.setE01DCIACC(userPO.getIdentifier());
				}
				msgList.setE01DCIADD(beanList.getRecord(0));
				msgList.setE01DCITNU(beanList.getRecord(1));
				msgList.setE01DCISEQ(beanList.getRecord(2));
				msgList.setE01DCIDES(beanList.getRecord(3));
				msgList.setE01DCIFRE(beanList.getRecord(4));
				msgList.setE01DCISTA(beanList.getRecord(5));
				msgList.setE01DCIPAG(beanList.getRecord(6));
				msgList.setE01DCITYP(beanList.getRecord(7));

				msgList.send();
			}
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	private synchronized void procReqDocList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDI001001Message msgList = null;
		EDI001003Message msgTables = null;
		ELEERRMessage msgError = null;
		JBListRec beanList = null;
		JBList tableList = null;
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
		int colNum = 8;
		try {
			beanList =
				(datapro.eibs.beans.JBListRec) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.JBListRec");
			beanList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			tableList =
				(datapro.eibs.beans.JBList) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.JBList");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getValue("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDI001001Message) mc.getMessageRecord("EDI001001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD0395");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01FLGWK1("R"); // Operation (either Send or Receive)
			msgList.setH01FLGWK2(req.getParameter("Type"));
			// Type (either Account or Customer)
			msgList.setH01OPECOD("0002"); // Maintenance

			if (req.getParameter("Type").equals("C") && !userPO.getCusNum().trim().equals("")) {
				msgList.setE01DCIACC(userPO.getCusNum());
			} else {
				msgList.setE01DCIACC(userPO.getIdentifier());
			}
			
			try {
				msgList.setE01DCITNU(req.getParameter("TABLE_NUM"));
			}
			catch (Exception e) {
			}

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

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.putValue("error", msgError);
				ses.putValue("docList", beanList);
				ses.putValue("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "EDI0010_doc_list.jsp");
					callPage(LangPath + "EDI0010_doc_list.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDI001001")) {

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				while (true) {

					msgList = (EDI001001Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {

						if (firstTime) {
							firstTime = false;
							userPO.setHeader18(Util.formatCell(msgList.getE01DCITNU()));
							userPO.setHeader19(Util.formatCell(msgList.getE01DCIDES()));
							userPO.setHeader20("");
							userPO.setHeader21("");
							userPO.setHeader22(req.getParameter("Type"));
						} else {
							myRow[0] =
								req.getParameter("Type")
									+ "_"
									+ msgList.getE01DCIACC().trim()
									+ "_"
									+ msgList.getE01DCITNU().trim()
									+ "_"
									+ msgList.getE01DCISEQ().trim();
							myRow[1] = msgList.getE01DCITNU();
							myRow[2] = msgList.getE01DCISEQ();
							myRow[3] = msgList.getE01DCIDES();
							myRow[4] = msgList.getE01DCIFRE();
							myRow[5] = msgList.getE01DCISTA();
							myRow[6] = msgList.getE01DCIPAG();
							myRow[7] = msgList.getE01DCITYP();
							beanList.addRow(myFlag, myRow);
						}

					}

					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.putValue("docList", beanList);
				ses.putValue("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "EDI0010_doc_list.jsp");
					callPage(LangPath + "EDI0010_doc_list.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDI001003")) {

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				String myRow = "";
				String chk = "";

				while (true) {

					msgTables = (EDI001003Message) newmessage;

					marker = msgTables.getH03FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {

						if (firstTime) {
							firstTime = false;
							chk = "checked";
							userPO.setHeader22(req.getParameter("Type"));
						} else {
							chk = "";
						}

						myRow = "<TR>";
						myRow += "<TD NOWRAP><input type=\"radio\" name=\"TABLE_NUM\" value=\""
							+ msgTables.getE03DCITNU()
							+ "\" "
							+ chk
							+ "></TD>";
						myRow += "<TD NOWRAP>" + Util.formatCell(msgTables.getE03DCITNU()) + "</TD>";
						myRow += "<TD NOWRAP>" + Util.formatCell(msgTables.getE03DCIDSC()) + "</TD>";
						myRow += "</TR>";

						tableList.addRow(myFlag, myRow);

					}

					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.putValue("tblList", tableList);
				ses.putValue("userPO", userPO);

				try {
					flexLog(
						"About to call Page: " + LangPath + "EDI0010_doc_table_selection_list.jsp");
					callPage(LangPath + "EDI0010_doc_table_selection_list.jsp", req, res);

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
	private synchronized void procReqInqDocList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDI001001Message msgList = null;
		ELEERRMessage msgError = null;
		JBListRec beanList = null;
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
		int colNum = 9;
		try {
			beanList =
				(datapro.eibs.beans.JBListRec) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.JBListRec");
			beanList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getValue("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDI001001Message) mc.getMessageRecord("EDI001001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDI0010");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01FLGWK1("R"); // Operation (either Send or Receive)
			msgList.setH01FLGWK2(req.getParameter("Type"));
			// Type (either Account or Customer)
			msgList.setH01OPECOD("0004"); // Inquiry

			if (req.getParameter("Number") != null) {
				msgList.setE01DCIACC(req.getParameter("Number"));
			} else {
				if (req.getParameter("Type").equals("C")) {
					msgList.setE01DCIACC(userPO.getCusNum());
				} else {
					msgList.setE01DCIACC(userPO.getIdentifier());
				}
			}

			if (req.getParameter("DocType") != null) {
				msgList.setE01DCITYP(req.getParameter("DocType"));
			}

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

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.putValue("error", msgError);
				ses.putValue("docList", beanList);
				ses.putValue("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "EDI0010_doc_inq_list.jsp");
					callPage(LangPath + "EDI0010_doc_inq_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDI001001")) {

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				while (true) {

					msgList = (EDI001001Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {

						if (firstTime) {
							firstTime = false;
							userPO.setHeader18(Util.formatCell(msgList.getE01DCITNU()));
							userPO.setHeader19(Util.formatCell(msgList.getE01DCIDES()));
							userPO.setHeader20("");
							userPO.setHeader21("");
							userPO.setHeader22(req.getParameter("Type"));
						} else {
							myRow[0] =
								req.getParameter("Type")
									+ "_"
									+ msgList.getE01DCIACC().trim()
									+ "_"
									+ msgList.getE01DCITNU().trim()
									+ "_"
									+ msgList.getE01DCISEQ().trim();
							myRow[1] = msgList.getE01DCITNU();
							myRow[2] = msgList.getE01DCISEQ();
							myRow[3] = msgList.getE01DCIDES();
							myRow[4] = msgList.getE01DCIFRE();
							myRow[5] = msgList.getE01DCISTA();
							myRow[6] = msgList.getE01DCIPAG();
							myRow[7] = msgList.getE01DCITYP();
							myRow[8] = msgList.getE01DCIDSQ();
							beanList.addRow(myFlag, myRow);
						}

					}

					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.putValue("docList", beanList);
				ses.putValue("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "EDI0010_doc_inq_list.jsp");
					callPage(LangPath + "EDI0010_doc_inq_list.jsp", req, res);
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

			int screen = R_DOCUMENTS;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getValue("currUser");

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
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					return;
				}

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					case R_DOCUMENTS :
						procReqDocList(mc, msgUser, req, res, session);
						break;
					case A_DOCUMENTS :
						procActionDocList(mc, msgUser, req, res, session);
						break;
					case R_INQ_DOCS :
						procReqInqDocList(mc, msgUser, req, res, session);
						break;
					case A_INQ_DOCS :
						procActionInqDocList(mc, msgUser, req, res, session);
						break;
					case R_ENTER :
						procReqEnter(msgUser, req, res, session);
						break;
					case A_ENTER :
						procActionEnter(mc, msgUser, req, res, session);
						break;
					case R_DOC_DELETE :
						procReqDocDeleteJDBC(req.getParameter("CODE"));
						break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				try {
					s.close();
				} catch (Exception e) {
					flexLog("Error: " + e);
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}
		}

	}
	private synchronized void showERROR(ELEERRMessage m) {
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

	static final int A_ENTER = 200;
	static final int A_INQ_DOCS = 4;

	// JODBConn dbConn = new JODBConn();
	// JOSQLExec sqlExec = new JOSQLExec();

	static final int R_DOC_DELETE = 5;
	static final int R_ENTER = 100;

	/**
	 * Insert the method's description here.
	 * Creation date: (6/13/2001 9:48:36 AM)
	 * @param dbJndi java.lang.String
	 */
	private void initDataSource(String dbJndi) {
		/*
				try {
					dbConn.init(
						JSEIBSProp.getDataSource(),
						JSEIBSProp.getUserid(),
						JSEIBSProp.getPassword());
					sqlExec.initSDF(10, dbConn);
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Exception in init method = " + e);
				}
		*/

	}

	/**
	 * This method was created in VisualAge.
	 */
	private synchronized void procActionEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		try {
			String type = req.getParameter("Type"); // Type (either Account or Customer)

			if (type.equalsIgnoreCase("C")) {
				procReqDocsTree(mc, user, req, res, ses);
			} else if (type.equalsIgnoreCase("A")) {
				procReqInqDocList(mc, user, req, res, ses);
			} else {
				procReqEnter(user, req, res, ses);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	private synchronized void procActionInqDocList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		JBListRec beanList = null;
		UserPos userPO = null;

		try {
			userPO = (UserPos) ses.getValue("userPO");

			int row = Integer.parseInt(req.getParameter("ROW"));

			beanList = (JBListRec) ses.getValue("docList");

			beanList.setCurrentRow(row);

			if (JSEIBSProp.getImgToIFS())
				procReqDocInfoXML(beanList.getRecord(0), beanList.getRecord(3), ses);
			else
				procReqDocInfoJDBC(beanList.getRecord(0), beanList.getRecord(3), ses);

			try {
				flexLog("About to call Page: " + LangPath + "EDI0010_doc_inq_list.jsp");
				callPage(LangPath + "EDI0010_doc_inq_list.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
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
	private synchronized void procReqDocDeleteJDBC(String infoFileName)
		throws ServletException, IOException {

		/*
			if (!JSEIBSProp.getImgToIFS())
				try {	
		
					//psRead.setString(1, infoFileName);
					sqlExec.addParameter(1,JSEIBSProp.getDbSchema());
					sqlExec.addParameter(2,"\'" + infoFileName + "\'");
					sqlExec.execute();
					ResultSet rs = sqlExec.getResultSet();
					//ResultSet rs = psRead.executeQuery();
		
					while (rs.next()) {
		
						File fImage = new File(JSEIBSProp.getImgTempPath() + rs.getString("EDINAM"));
						fImage.delete();
						
					}
					
					rs.close();
					
				}
				catch (Exception e) {
					flexLog("Exception ocurred. Exception = " + e);
				}
		*/
	}

	/**
	 * This method was created in VisualAge.
	 */
	private synchronized void procReqDocInfoJDBC(
		String infoFileName,
		String docName,
		HttpSession ses)
		throws ServletException, IOException {

		/*
			UserPos userPO = null;
		
			try {	
		
				//psRead.setString(1, infoFileName);
				sqlExec.addParameter(1,JSEIBSProp.getDbSchema());
				sqlExec.addParameter(2,"\'" + infoFileName + "\'");
				sqlExec.execute();
				ResultSet rs = sqlExec.getResultSet();
				//ResultSet rs = psRead.executeQuery();
		
				JBListRec beanImg = new JBListRec();
				int colNum = 5;
				beanImg.init(colNum);
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i=0; i<colNum; i++) {
					myRow[i] = "";
				}
		
				while (rs.next()) {
		
					myRow[0] = JSEIBSProp.getScanDataURL() + rs.getString("EDINAM").trim();
					if (myRow[0].toUpperCase().indexOf(JSEIBSProp.getImgExt().toUpperCase()) > -1) {
						myFlag = "I";
					}
					else {
						myFlag = "";
					}
					myRow[1] = docName;
					myRow[2] = myRow[0].substring(myRow[0].lastIndexOf(".") + 1);
					myRow[3] = rs.getString("EDIDTE");
					myRow[4] = rs.getInt("EDISEQ") + "";
					beanImg.addRow(myFlag, myRow);
		
					byte buf[] = rs.getBlob("EDIIMG").getBytes(1, (int)rs.getBlob("EDIIMG").length());
					OutputStream osImage = new FileOutputStream(JSEIBSProp.getImgTempPath() + rs.getString("EDINAM").trim());
					osImage.write(buf);
					osImage.close();
					
				}
				
				rs.close();
				
				userPO = (UserPos) ses.getValue("userPO");
		
				userPO.setHeader20("DO_INQ");
				userPO.setHeader21(super.webAppPath + LangPath + "EDI0010_doc_viewer_container.jsp");
				
				ses.putValue("userPO", userPO);
				ses.putValue("listImg", beanImg);
				
			}
			catch (Exception e) {
				flexLog("Exception ocurred. Exception = " + e);
			}
		*/
	}

	/**
	 * This method was created in VisualAge.
	 */
	private synchronized void procReqDocInfoXML(
		String infoFileName,
		String docName,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		try {

			org.apache.xerces.parsers.DOMParser parser =
				new org.apache.xerces.parsers.DOMParser();

			String xmlFile = JSEIBSProp.getScanDataURL() + infoFileName + ".xml";

			parser.parse(xmlFile);
			org.w3c.dom.Document doc = parser.getDocument();

			JBListRec beanImg = new JBListRec();
			int colNum = 5;
			beanImg.init(colNum);
			String myFlag = "";
			String myRow[] = new String[colNum];
			for (int i = 0; i < colNum; i++) {
				myRow[i] = "";
			}

			org.w3c.dom.Node docPages = doc.getFirstChild();
			org.w3c.dom.NodeList list = docPages.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				org.w3c.dom.Node n = list.item(i);
				if (n.getNodeName().equals("Page")) {
					org.w3c.dom.NamedNodeMap m = n.getAttributes();
					org.w3c.dom.Node c = m.getNamedItem("Name");
					myRow[0] = JSEIBSProp.getScanDataURL() + c.getNodeValue();
					if (myRow[0].indexOf(JSEIBSProp.getImgExt()) > -1) {
						myFlag = "I";
					} else {
						myFlag = "";
					}
					myRow[1] = docName;
					myRow[2] = myRow[0].substring(myRow[0].lastIndexOf(".") + 1);
					c = m.getNamedItem("Date");
					if (c == null)
						myRow[3] = "";
					else
						myRow[3] = c.getNodeValue();
					c = m.getNamedItem("Seq");
					if (c == null)
						myRow[4] = "1";
					else
						myRow[4] = c.getNodeValue();
					beanImg.addRow(myFlag, myRow);
				}
			}

			userPO = (UserPos) ses.getValue("userPO");

			userPO.setHeader20("DO_INQ");
			userPO.setHeader21(
				super.webAppPath + LangPath + "EDI0010_doc_viewer_container.jsp");

			ses.putValue("userPO", userPO);
			ses.putValue("listImg", beanImg);

		} catch (Exception e) {
			flexLog("Exception ocurred. Exception = " + e);
		}

	}

	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	private synchronized void procReqDocsTree(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		MessageRecord newmessage = null;
		EDI001002Message msgTree = null;
		ELEERRMessage msgError = null;
		JBParseTree dataTree = new JBParseTree();

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		String outParams = "";
		String firstLink = "";
		boolean firstTime = true;

		// Send Request
		try {
			msgTree = (EDI001002Message) mc.getMessageRecord("EDI001002");
			msgTree.setH02USERID(user.getH01USR());
			msgTree.setH02FLGWK2("C");
			msgTree.setE02DCICUN(req.getParameter("Number"));
			if (req.getParameter("DocType") != null) {
				msgTree.setE02DCITYP(req.getParameter("DocType"));
			}

			msgTree.send();
			msgTree.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receiving
		try {
			flexLog("About to receive data");
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDI001002")) {
				msgTree = (EDI001002Message) newmessage;

				if (msgTree.getE02DCINIV().equals("0")) {

					String imageURL = getServerRoot(req) + super.webAppPath + "/images/";

					dataTree.init();
					dataTree.setRootFont("Dialog", "bold", "12", "0D23B5");
					dataTree.setFolderFont("Dialog", "plain", "10", "000033");
					dataTree.setItemFont("Small", "plain", "10", "000033");
					dataTree.setImageUrl(imageURL);

					dataTree.setRootImage("cube.gif", "cubeover.gif");
					dataTree.setFolderImage("cone.gif", "coneover.gif");
					dataTree.setItemImage("ball.gif", "ballover.gif");

					String titleFolder =
						msgTree.getE02DCICUN() + " - " + msgTree.getE02DCIDSC().trim();
					String titleDescription =
						msgTree.getE02DCICUN() + " - " + msgTree.getE02DCIDSC().trim();

					dataTree.setRootTitle(titleFolder, titleDescription);
					dataTree.setTargetLink("detail");

					String item = "";
					String itemLink = "";

					String folder = "";
					String folderLink = "";
					//outParams += "< treecontrol imageurl=\"" + getServerRoot(req) + super.webAppPath + "/images/" + "\">\n";

					//outParams += "< font face=\"Dialog\" style=\"bold\" size=12 >\n";
					//outParams += "< font color=\"0D23B5\">\n";
					//outParams += "		< rootfolder title=\"" + msgTree.getE02DCICUN() + " - " + msgTree.getE02DCIDSC().trim() + "\" image=\"cube.gif\" imageTwo=\"cubeover.gif\" info=\"" + msgTree.getE02DCICUN() + " - " + msgTree.getE02DCIDSC().trim() + "\" target=\"detail\">\n";

					boolean shutIt = false;
					String thisLink = "";

					while (true) {
						newmessage = mc.receiveMessage();
						msgTree = (EDI001002Message) newmessage;

						if (msgTree.getE02DCINIV().equals("1")) {
							if (shutIt) {
								//outParams += "				</ font>\n";
								//outParams += "				</folder>\n";
								folder = "";
							} else {
								shutIt = true;
							}
							//String dsc = msgTree.getE02DCIATY().equalsIgnoreCase("*cus") ? msgTree.getE02DCIDSC().trim() : msgTree.getE02DCIATY() + " - "  + msgTree.getE02DCIDSC().trim();
							folder =
								msgTree.getE02DCIATY().equalsIgnoreCase("*cus")
									? msgTree.getE02DCIDSC().trim()
									: msgTree.getE02DCIATY() + " - " + msgTree.getE02DCIDSC().trim();

							dataTree.addRow(folder, folder, folderLink, folderLink);

							//outParams += "				< font face=\"Dialog\" style=\"plain\" size=10 color=\"000033\">\n";
							//outParams += "				< folder title=\"" + dsc + "\" expand image=\"cone.gif\" imageTwo=\"coneover.gif\" info=\"" + dsc + "\">\n";
							//outParams += "				</ font>\n";
							//outParams += "				< font face=\"Small\" style=\"plain\" size=10 color=\"000033\">\n";

						} else if (msgTree.getE02DCINIV().equals("2")) {
							if (firstTime) {
								firstTime = false;
								firstLink =
									super.webAppPath
										+ "/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type="
										+ msgTree.getH02FLGWK2().trim()
										+ "&Number="
										+ msgTree.getE02DCIACC().trim()
										+ "&DocType="
										+ msgTree.getE02DCITYP().trim();
							}
							//thisLink = getServerRoot(req) + super.webAppPath + "/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=" + msgTree.getH02FLGWK2().trim() + "&Number=" + msgTree.getE02DCIACC().trim() + "&DocType=" + msgTree.getE02DCITYP().trim();
							itemLink =
								getServerRoot(req)
									+ super.webAppPath
									+ "/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type="
									+ msgTree.getH02FLGWK2().trim()
									+ "&Number="
									+ msgTree.getE02DCIACC().trim()
									+ "&DocType="
									+ msgTree.getE02DCITYP().trim();
							//String dsc = msgTree.getE02DCIPRO().equalsIgnoreCase("*cus") ? msgTree.getE02DCIDSC().trim() : msgTree.getE02DCIACC().trim() + " - " + msgTree.getE02DCIPRO() + " - "  + msgTree.getE02DCIDSC().trim();
							item =
								msgTree.getE02DCIPRO().equalsIgnoreCase("*cus")
									? msgTree.getE02DCIDSC().trim()
									: msgTree.getE02DCIACC().trim()
										+ " - "
										+ msgTree.getE02DCIPRO()
										+ " - "
										+ msgTree.getE02DCIDSC().trim();
							//outParams += "						< item title=\"" + dsc + "\" image=\"ball.gif\" imageTwo=\"ballover.gif\" info=\"" + dsc + "\" link=\"" + thisLink + "\" target=\"detail\">\n";
							dataTree.addRow(item, folder, itemLink, folderLink);

						} else if (msgTree.getE02DCINIV().equals("*")) {
							//outParams += "				</folder>\n";
							//outParams += "				</folder>\n";
							break;
						}
					}

					//outParams += "		</folder>\n";
					//outParams += "</treecontrol>";

				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			if (firstTime) {
				try {
					flexLog("About to call Page: " + LangPath + "MISC_no_result.jsp");
					res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				ses.putValue("error", msgError);
				outParams = dataTree.getTree();
				ses.putValue("docsParams", outParams);

				//	  flexLog("About to call Page: " + LangPath + "ESD0711_products_offer_frame.jsp");
				//	  res.sendRedirect(super.srctx + LangPath + "ESD0711_products_offer_frame.jsp");	

				res.setContentType("text/html");
				res.setHeader("Pragma", "No-cache");
				res.setDateHeader("Expires", 0);
				res.setHeader("Cache-Control", "no-cache");
				PrintWriter out = res.getWriter();
				out.println("<!-- frames -->");
				out.println("<frameset  cols=\"28%,*\">");
				out.println(
					"<frame name=\"list\" src=\""
						+ super.webAppPath
						+ LangPath
						+ "EDI0010_doc_inq_tree_view.jsp\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"no\" frameborder=\"NO\">");
				out.println(
					"<frame name=\"detail\" src=\""
						+ firstLink
						+ "\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"auto\" frameborder=\"NO\">");
				out.println("</frameset>");
				out.close();
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
	private synchronized void procReqEnter(
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
			userPO.setOption("DOCUMENTATION");
			userPO.setPurpose("INQUIRY");
			ses.putValue("error", msgError);
			ses.putValue("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: " + LangPath + "EDI0010_doc_general_inq_enter.jsp");
			callPage(LangPath + "EDI0010_doc_general_inq_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
}