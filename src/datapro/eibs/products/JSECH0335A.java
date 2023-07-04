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
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;
import datapro.eibs.beans.ECH033501Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSECH0335A extends datapro.eibs.master.SuperServlet {

	protected static final int R_RT_PROTECT_LIST = 1;
	protected static final int A_RT_PROTECT_LIST = 2;

	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;

	protected String LangPath = "S";

	private ServletConfig config = null;

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
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

		ECH033501Message msgChk = null;
		ELEERRMessage msgError = null;

		try {

			SmartUpload mySmartUpload = new SmartUpload();
			boolean badFormat = false;
			try {
				// Initialization
				mySmartUpload.initialize(config, req, res);
				// Upload
				mySmartUpload.upload();
				// Retreive the current file
				com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(0);

				//
				byte[] bd = new byte[myFile.getSize()];
				for (int i = 0; i < myFile.getSize(); i++) {
					bd[i] = myFile.getBinaryData(i);
				}

				msgChk = (ECH033501Message) mc.getMessageRecord("ECH033501");
				msgChk.setH01USERID(user.getH01USR());
				msgChk.setH01PROGRM("ECH0335");
				msgChk.setH01TIMSYS(getTimeStamp());
				msgChk.setH01SCRCOD("01");
				msgChk.setH01OPECOD("0010");

				int bc = 0;
				String st = new String(bd);
				while (true) {

					msgChk.setH01FLGWK1(st.substring(bc, bc + 1));
					msgChk.setE01CKCACC(st.substring(bc + 1, bc + 10));
					msgChk.setE01CKCCKN(st.substring(bc + 10, bc + 19));
					msgChk.setE01CKCAMT(
						st.substring(bc + 19, bc + 30) + "." + st.substring(bc + 30, bc + 32));

					msgChk.send();
					// msgChk.destroy();

					bc += 34;
					if (bc >= myFile.getSize())
						break;
				}
				// sending last record
				msgChk.setH01FLGMAS("*");
				msgChk.send();
				msgChk.destroy();

				//
			} catch (Exception e) {
				badFormat = true;
				flexLog("Exception: " + e);
			}

			if (badFormat) {
				UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
				userPO.setHeader1("1");
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "ECH0335A_rt_enter_rpotect.jsp");
					callPage(LangPath + "ECH0335A_rt_enter_protect.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else {
				procReqChkProtectedList(mc, user, req, res, ses);
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
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		flexLog("Initializing java beans into the session");
		int colNum = 6;
		try {
			chkList = new JBListRec();
			chkList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
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
						myRow[2] = msgChk.getE01CKCBNF(); // Description
						myRow[3] = msgChk.getE01CKCACC(); // Account
						myRow[4] = msgChk.getH01FLGWK1(); // Control

						chkList.addRow(myFlag, myRow);

					}
					newmessage = mc.receiveMessage();

				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("chkList", chkList);

				try {
					flexLog("About to call Page: " + LangPath + "ECH0335A_rt_protect_list.jsp");
					callPage(LangPath + "ECH0335A_rt_protect_list.jsp", req, res);
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
			flexLog("About to call Page: " + LangPath + "ECH0335A_rt_enter_rpotect.jsp");
			callPage(LangPath + "ECH0335A_rt_enter_protect.jsp", req, res);
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

			int screen = A_ENTER;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 7);
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
					case R_RT_PROTECT_LIST :
						procReqChkProtectedList(mc, msgUser, req, res, session);
						break;
						//entering options
					case R_ENTER :
						procReqEnter(msgUser, req, res, session);
						break;

					case A_ENTER :
						procActionEnter(mc, msgUser, req, res, session);
						break;
					case A_RT_PROTECT_LIST :
						procActionChkProtectedList(mc, msgUser, req, res, session);
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

			msgChk = (ECH033501Message) mc.getMessageRecord("ECH033501");
			msgChk.setH01USERID(user.getH01USR());
			msgChk.setH01PROGRM("ECH0335");
			msgChk.setH01TIMSYS(getTimeStamp());
			msgChk.setH01SCRCOD("01");
			msgChk.setH01OPECOD("0010");
			msgChk.setH01FLGWK2("A");

			chkList.initRow();
			while (chkList.getNextRow()) {

				msgChk.setH01FLGWK1(chkList.getRecord(4));
				msgChk.setE01CKCACC(chkList.getRecord(3));
				msgChk.setE01CKCCKN(chkList.getRecord(0));
				msgChk.setE01CKCAMT(chkList.getRecord(1));
				msgChk.setE01CKCBNF(chkList.getRecord(2));

				msgChk.send();
				// msgChk.destroy();

			}
			// sending last record
			msgChk.setH01FLGMAS("*");
			msgChk.send();
			msgChk.destroy();

			// Receive rejection list
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ECH033501")) {

				int colNum = 6;
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
						myRow[2] = msgChk.getE01CKCBNF(); // Description
						myRow[3] = msgChk.getE01CKCACC(); // Account
						myRow[4] = msgChk.getH01FLGWK1(); // Control

						chkList.addRow(myFlag, myRow);

					}
					newmessage = mc.receiveMessage();

				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("chkList", chkList);

				try {
					flexLog("About to call Page: " + LangPath + "ECH0335A_rt_protect_confirm.jsp");
					callPage(LangPath + "ECH0335A_rt_protect_confirm.jsp", req, res);
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

}