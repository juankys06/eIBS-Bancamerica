package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (4/23/00 4:10:04 PM)
 * @author: Gustavo Adolfo Villarroel
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSECD0015 extends datapro.eibs.master.SuperServlet {

	// settlement inquiry 
	static final int R_INQUIRY					= 3;

	static final int R_SETTLEMENT_ENTER			= 100;
	static final int R_SETTLEMENT_GEN_LIST 		= 101;
	static final int R_SETTLEMENT_DET_LIST 		= 102;
	static final int A_SETTLEMENT_GEN_LIST 		= 103;
	static final int A_SETTLEMENT_DETAIL		= 104;
	
	protected String LangPath = "S";

	/**
	 * JSECD0015 constructor comment.
	 */
	public JSECD0015() {
		super();
	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void destroy() {

		flexLog("free resources used by JSECD0015");

	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0015DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList detList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("INQUIRY");
		detList = (JBObjList) ses.getAttribute("detList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		}
		catch (Exception e) {}
		
		detList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0015DSMessage) detList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0015_settlement_inq.jsp");
				callPage(LangPath + "ECD0015_settlement_inq.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procReqEnterSettlement(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0015DSMessage msgCD = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0015_settlement_inq_enter.jsp");
				callPage(LangPath + "ECD0015_settlement_inq_enter.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procReqSettleListGeneral(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0015DSMessage msgList = null;
		JBObjList genList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0015DSMessage) mc.getMessageRecord("ECD0015DS");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECD0015");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			try {
				msgList.setE01CCRATM(req.getParameter("E01CCRATM"));			
			}
			catch (Exception e) {
				msgList.setE01CCRATM("");
			}
			try {
				msgList.setE01CCRTAR(req.getParameter("E01CCRTAR"));		
			}
			catch (Exception e) {
				msgList.setE01CCRTAR("");
			}
			try {
				msgList.setE01CCRSTS(req.getParameter("E01CCRSTS"));		
			}
			catch (Exception e) {
				msgList.setE01CCRSTS("");
			}
			
			msgList.setE01CCRDDE(req.getParameter("E01CCRDDE"));
			msgList.setE01CCRMDE(req.getParameter("E01CCRMDE"));
			msgList.setE01CCRADE(req.getParameter("E01CCRADE"));
			msgList.setE01CCRDDH(req.getParameter("E01CCRDDH"));
			msgList.setE01CCRMDH(req.getParameter("E01CCRMDH"));
			msgList.setE01CCRADH(req.getParameter("E01CCRADH"));

			msgList.send();
			msgList.destroy();
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "ECD0015_settlement_gen_list.jsp");
					callPage(LangPath + "ECD0015_settlement_gen_list.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			}
			else if (newmessage.getFormatName().equals("ECD0015DS")) {
					genList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0015DSMessage) newmessage;
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
							genList.setShowNext(false);
							break;
						}
						else {
							genList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								genList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("genList", genList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0015_settlement_gen_list.jsp");
						callPage(LangPath + "ECD0015_settlement_gen_list.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				}
				else
					flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
	
	protected void procReqSettleListDetail(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0015DSMessage msgList = null;
		JBObjList detList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0015DSMessage) mc.getMessageRecord("ECD0015DS");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECD0015");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0012");
			
			try {
				msgList.setE01CCRATM(req.getParameter("E01CCRATM"));			
			}
			catch (Exception e) {
				msgList.setE01CCRATM("");
			}
			try {
				msgList.setE01CCRTAR(req.getParameter("E01CCRTAR"));		
			}
			catch (Exception e) {
				msgList.setE01CCRTAR("");
			}
			try {
				msgList.setE01CCRSTS(req.getParameter("E01CCRSTS"));		
			}
			catch (Exception e) {
				msgList.setE01CCRSTS("");
			}		

			msgList.setE01CCRDDE(req.getParameter("E01CCRDDE"));
			msgList.setE01CCRMDE(req.getParameter("E01CCRMDE"));
			msgList.setE01CCRADE(req.getParameter("E01CCRADE"));
			msgList.setE01CCRDDH(req.getParameter("E01CCRDDH"));
			msgList.setE01CCRMDH(req.getParameter("E01CCRMDH"));
			msgList.setE01CCRADH(req.getParameter("E01CCRADH"));

			msgList.send();
			msgList.destroy();
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex) {
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
					flexLog("About to call Page: " + LangPath + "ECD0015_settlement_det_list.jsp");
					callPage(LangPath + "ECD0015_settlement_det_list.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			}
			else if (newmessage.getFormatName().equals("ECD0015DS")) {
					detList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0015DSMessage) newmessage;
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
								detList.setShowNext(false);
								break;
						}
						else {
							detList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								detList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("detList", detList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0015_settlement_det_list.jsp");
						callPage(LangPath + "ECD0015_settlement_det_list.jsp", req, res);
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				}
				else
					flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
	
	protected void procActionSettleListGeneral(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0015DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList genList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		genList = (JBObjList) ses.getAttribute("genList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		}
		catch (Exception e) {
		}
		genList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0015DSMessage) genList.getRecord();

			try {
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECD0015?SCREEN=102"
					+ "&E01CCRDDE=" + msgCD.getE01CCRFET().substring(6,8)
					+ "&E01CCRMDE=" + msgCD.getE01CCRFET().substring(4,6)
					+ "&E01CCRADE=" + msgCD.getE01CCRFET().substring(0,4)
					+ "&E01CCRDDH=" + msgCD.getE01CCRFET().substring(6,8)
					+ "&E01CCRMDH=" + msgCD.getE01CCRFET().substring(4,6)
					+ "&E01CCRADH=" + msgCD.getE01CCRFET().substring(0,4));
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionSettlementDetail(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0015DSMessage msgList = null;
		JBObjList genList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			msgList = (ECD0015DSMessage) mc.getMessageRecord("ECD0015DS");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECD0015");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0005");
			
			// all the fields here
			java.util.Enumeration enu = msgList.fieldEnumeration();
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
	
			msgList.send();
			msgList.destroy();
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
	
				try {
					flexLog("About to call Page: " + LangPath + "ECD0015_settlement_inq.jsp");
					callPage(LangPath + "ECD0015_settlement_inq.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	
			}
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ECD0015DS")) {
				msgList = (ECD0015DSMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgCD", msgList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "ECD0015_settlement_inq.jsp");
					callPage(LangPath + "ECD0015_settlement_inq.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		}
		catch (Exception e) {
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

			int screen = R_SETTLEMENT_ENTER;

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
					case R_INQUIRY:
						procReqInquiry(mc, msgUser, req, res, session);
						break;
						
					case R_SETTLEMENT_ENTER:
						procReqEnterSettlement(mc, msgUser, req, res, session);
						break;
					case R_SETTLEMENT_GEN_LIST:
						procReqSettleListGeneral(mc, msgUser, req, res, session);
						break;
					case R_SETTLEMENT_DET_LIST:
						procReqSettleListDetail(mc, msgUser, req, res, session);
						break;
						
					case A_SETTLEMENT_GEN_LIST:
						procActionSettleListGeneral(mc, msgUser, req, res, session);
						break;
					case A_SETTLEMENT_DETAIL:
						procActionSettlementDetail(mc, msgUser, req, res, session);
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


}