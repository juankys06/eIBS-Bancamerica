package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.Beans;
import java.io.*;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletOutputStream;

import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import datapro.eibs.tools.pdf.*;

public class JSECC0080I extends datapro.eibs.master.SuperServlet {

	// credit card 
	static final int A_LIST 			= 2;
		
	static final int R_ENTER_CARDS_ASSIGN = 700;
	static final int A_ENTER_CARDS_ASSIGN = 800;
			
	protected String LangPath = "S";

	/**
	 * JSECC0080I constructor comment.
	 */
	public JSECC0080I() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECC0010");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}


	protected void procReqEnterAssignCard(
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
			userPO.setPurpose("INQUIRY");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		String opt = req.getParameter("OPT");
		if (opt == null) opt = "";

		try {
			flexLog("About to call Page: " + LangPath + "ECC0080_cc_inq_enter_card.jsp");
			callPage(LangPath + "ECC0080_cc_inq_enter_card.jsp?opt=" + opt, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionEnterAssignCard(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC008001Message msgList = null;
		ELEERRMessage msgError = null;
		JBListRec appList = null;
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
			msgList = (ECC008001Message) mc.getMessageRecord("ECC008001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC0080");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			try {
				msgList.setE01CCANUM(req.getParameter("E01CCANUM"));
			} catch (Exception e) {
				msgList.setE01CCANUM(userPO.getIdentifier());
			}
			msgList.send();
			msgList.destroy();
			flexLog("ECC008001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		int colNum = 17;
		try {
			appList = new datapro.eibs.beans.JBListRec();
			appList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "ECC0080_cc_inq_enter_card.jsp");
					callPage(LangPath + "ECC0080_cc_inq_enter_card.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				if (newmessage.getFormatName().equals("ECC008001")) {

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					while (true) {

						msgList = (ECC008001Message) newmessage;

						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								userPO.setIdentifier(msgList.getE01CCANUM());
								userPO.setHeader1(msgList.getE01CCANUM());
								userPO.setCusNum(msgList.getE01CARCUN());
								userPO.setCusName(msgList.getE01CARNA1());		
							
							appList.setShowNext(false);
							break;
						} else {
							
							if (firstTime) {
								firstTime = false;
								//appList.setFirstRec(Integer.parseInt(msgList.getE01CCANUM()));
								userPO.setIdentifier(msgList.getE01CCANUM());
								userPO.setHeader1(msgList.getE01CCANUM());
								userPO.setCusNum(msgList.getE01CARCUN());
								userPO.setCusName(msgList.getE01CARNA1());		
								userPO.setHeader20("");
								userPO.setHeader21("");
							}
							
							// Quote List
							myRow[0] = msgList.getE01CCANUM();   // card number
							myRow[1] = msgList.getE01CARCUN();   // customer number card
							myRow[2] = msgList.getE01CARNA1();   // customer name card
							myRow[3] = msgList.getE01CCAATY();   // account type
							myRow[4] = msgList.getE01CCAACC();   // account number
							myRow[5] = msgList.getE01CCAFAU();   // from authority 
							myRow[6] = msgList.getE01CCAIAU();   // inquiry authority
							myRow[7] = msgList.getE01CCATAU();   // transfer authority
							myRow[8] = msgList.getE01CCADAU();   // default authority 
							myRow[9] = msgList.getE01CCARAU();   // ready reserve authority
							myRow[10] = msgList.getE01CCAIND();  // Account index
							myRow[11] = msgList.getE01CCACWL();  // cash withdrawal limit
							myRow[12] = msgList.getE01CCAFBL();  // Found back limit
							myRow[13] = msgList.getE01CCAODL();  // overdraft limit
							myRow[14] = msgList.getE01CCATWL();  // teller withdrawal limit
							myRow[15] = msgList.getE01CTACUN();   // customer number account
							myRow[16] = msgList.getE01CTANA1();   // customer name account
														
							appList.addRow(myFlag, myRow);
							
							if (marker.equals("+")) {
								appList.setShowNext(true);
								break;
							}
						}

						newmessage = mc.receiveMessage();

					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("appList", appList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECC0080_cc_inq_accounts_list.jsp");
						callPage(LangPath + "ECC0080_cc_inq_accounts_list.jsp", req, res);

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

	protected void procActionAditionalCardsList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ESS0030DSMessage msgUser = null;
		JBListRec appList = null;

		appList = (JBListRec) ses.getAttribute("appList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
				appList.setCurrentRow(row);
			} catch (Exception e) {
			}
			
			userPO.setHeader20("DO_INQUIRY");
			userPO.setHeader21(super.webAppPath + LangPath + "ECC0080_cc_inq_account.jsp?ROW=" + row);
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + LangPath + "ECC0080_cc_inq_accounts_list.jsp?SEL=" + row);

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

			int screen = A_ENTER_CARDS_ASSIGN;

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
					case A_LIST :					    
						procActionAditionalCardsList(mc, msgUser, req, res, session);
						break;	

					case R_ENTER_CARDS_ASSIGN :
						procReqEnterAssignCard(mc, msgUser, req, res, session);
						break;
					case A_ENTER_CARDS_ASSIGN :
						procActionEnterAssignCard(mc, msgUser, req, res, session);
						break;
					
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
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


}