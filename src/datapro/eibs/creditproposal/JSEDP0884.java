package datapro.eibs.creditproposal;

/** 
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla???????????????????????????
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;
import datapro.eibs.beans.DataTransaction;

public class JSEDP0884 extends datapro.eibs.master.SuperServlet {


	// Action 
	// first screen
	protected static final int R_GET_LIST = 100;
	final int A_TRANSACTION = 300;

	protected String LangPath = "s";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0884() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEIE00000");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procGetList(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDP088401Message mL088401 = null;
	EDP088402Message mL088402 = null;
	ELEERRMessage msgError = null;
	JBListRec grpAccList1 = null;
	JBListRec grpAccList2 = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	DataTransaction transData = new DataTransaction();

	boolean IsNotError = false;	
	flexLog("Opennig Socket Connection Branch");
	MessageContext mc884;
	mc884 = mc;
	MessageContext mc88402;
	mc88402 = mc;

	if (req.getParameter("CUN") != null) {
		userPO.setCusNum(req.getParameter("CUN"));
	}
	if (req.getParameter("CUNAM") != null) {
		userPO.setCusName(req.getParameter("CUNAM"));
	}

	// Send Initial data
	try {
		flexLog("Send Initial Data");

		mL088401 = (EDP088401Message) mc884.getMessageRecord("EDP088401");
		mL088402 = (EDP088402Message) mc88402.getMessageRecord("EDP088402");
		mL088401.setH01USERID(user.getH01USR());
		mL088401.setH01PROGRM("EDP0884");
		mL088401.setH01TIMSYS(getTimeStamp());
		mL088401.setE01CPTCUN(userPO.getCusNum());
		mL088401.setH01SCRCOD("01");
		mL088401.setH01OPECOD("0015");

		mL088401.send();
		mL088401.destroy();


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message

	  try {
	  newmessage = mc884.receiveMessage();

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

		  showERROR(msgError);

		  //beanList.setNoResult(true);

		  flexLog("Putting java beans into the session");
		  ses.setAttribute("error", msgError);
		  ses.setAttribute("userPO", userPO);

	  }
	  } catch (Exception e) {
	  e.printStackTrace();
	  flexLog("Error: " + e + newmessage);
	  throw new RuntimeException("Socket Communication Error Receiving");
	  }
	
	  // Receive Data
	try {
	  newmessage = mc884.receiveMessage();
	  int idxAcc = -1;

	  if (newmessage.getFormatName().equals("EDP088401")) {

				String marker = "";
				String myFlag = "";
				int idxOpt = -1;
				int idxGrp = -1;
				int idxGrpAcc = -1;
				int colNum = 5;
				int colNumAcc = 12;
				try {
					grpAccList1 = new datapro.eibs.beans.JBListRec();
					grpAccList1.init(colNumAcc);
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				String myGrp[] = new String[colNum];
				String myGrpAcc[] = new String[colNumAcc];
				String myOption[] = new String[colNum];
				boolean firstTime = true;
				for (int i = 0; i < colNum; i++) {
					myGrp[i] = "";
					myOption[i] = "";
				}
				for (int i = 0; i < colNumAcc; i++) {
					myGrpAcc[i] = "";
				}
				transData.clear();

				while (true) {
					mL088401 = (EDP088401Message) newmessage;
					marker = mL088401.getE01OPECDE();
					if (marker.equals("*")) {
						break;
					} else {
							myFlag = "" + idxOpt + "" + idxGrp;
							idxAcc++;
							myGrpAcc[0] = mL088401.getE01MODLGT();
							myGrpAcc[1] = mL088401.getE01MODCOD();
							myGrpAcc[2] = mL088401.getE01MODDSC();
							myGrpAcc[3] = mL088401.getE01MODPER();
							myGrpAcc[4] = mL088401.getE01PTSPTS();
							myGrpAcc[5] = mL088401.getE01MODPET();
							grpAccList1.addRow(myFlag, myGrpAcc);
					newmessage = mc884.receiveMessage();
				}
				}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

		newmessage = mc88402.receiveMessage();

		if (newmessage.getFormatName().equals("EDP088402")) {

				  String marker = "";
				  String myFlag = "";
				  int idxOpt = -1;
				  int idxGrp = -1;
				  int idxGrpAcc = -1;
				  int colNum = 5;
				  int colNumAcc = 12;
				  try {
					  grpAccList2 = new datapro.eibs.beans.JBListRec();
					  grpAccList2.init(colNumAcc);
				  } catch (Exception ex) {
					  flexLog("Error: " + ex);
				  }
				  String myGrp[] = new String[colNum];
				  String myGrpAcc[] = new String[colNumAcc];
				  String myOption[] = new String[colNum];
				  boolean firstTime = true;
				  for (int i = 0; i < colNum; i++) {
					  myGrp[i] = "";
					  myOption[i] = "";
				  }
				  for (int i = 0; i < colNumAcc; i++) {
					  myGrpAcc[i] = "";
				  }
				  transData.clear();

				  while (true) {
					  mL088402 = (EDP088402Message) newmessage;
					  marker = mL088402.getE02OPECDE();
					  if (marker.equals("*")) {
						  break;
					  } else {
							  myFlag = "" + idxOpt + "" + idxGrp;
							  idxAcc++;
							  myGrpAcc[0] = mL088402.getE02PTSMOD();
							  myGrpAcc[1] = mL088402.getE02PTSSEQ();
							  myGrpAcc[2] = mL088402.getE02PTSDSC();
							  myGrpAcc[3] = mL088402.getE02PTSPTS();
							  myGrpAcc[4] = mL088402.getE02PTSFLG();
							  grpAccList2.addRow(myFlag, myGrpAcc);
					  newmessage = mc88402.receiveMessage();
				  }
				  }

				  } else
					  flexLog("Message " + newmessage.getFormatName() + " received.");

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("trans1", grpAccList1);
				ses.setAttribute("trans2", grpAccList2);

				try {
					 if (msgError.getERRNUM().equals("0")) {
						flexLog(
						"About to call Page: "
						+ LangPath
						+ "EDP0884_punctuation_customer.jsp");
						callPage(LangPath + "EDP0884_punctuation_customer.jsp?ROW=" + idxAcc, req, res);
					} else {
						//By error
						flexLog(
						"About to call Page: "
						+ LangPath
						+ "EDP0884_punctuation_customer.jsp");
						 callPage(
						LangPath + "EDP0884_punctuation_customer.jsp",
						req,
						 res);
					 }

				///**
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}

			} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		finally
		{
		}
	}


protected void procUpdAcc(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses) {
	JBListRec beanList = null;
	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDP088402Message mL088402 = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		   	// ADD RECORDS
			try {
				mL088402 = (EDP088402Message) mc.getMessageRecord("EDP088402");
				mL088402.setH02USERID(user.getH01USR());
				mL088402.setH02PROGRM("EDP0884");
				mL088402.setH02TIMSYS(getTimeStamp());
				mL088402.setH02OPECOD("0005");
				mL088402.setE02CPTCUN(userPO.getCusNum());
				mL088402.setE02PTSMOD(req.getParameter("E02PTSMOD" ));
				mL088402.setE02PTSSEQ(req.getParameter("E02PTSSEQ" ));
				mL088402.setE02PTSPTS(req.getParameter("E02PTSPTS" ));

				mL088402.send();
				mL088402.destroy();
			  flexLog("EDP0888402 Message Sent");
			// Receive Error
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					boolean IsNotError = msgError.getERRNUM().equals("0");
					if (IsNotError) {

					} else {
						msgError = (ELEERRMessage) newmessage;
						showERROR(msgError);
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);

					try {

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0884_punctuation_customer.jsp");

						flexLog("About to call Page: " + LangPath + "EDP0884_punctuation_customer.jsp");
						callPage(LangPath + "EDP0884_punctuation_customer.jsp", req, res);

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
					return;
					}
				}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}

		   res.sendRedirect(
			   super.srctx
				   + "/servlet/datapro.eibs.creditproposal.JSEDP0884?SCREEN=100");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
		   }
			// END ADD RECORDS
		   	
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

			int screen = R_GET_LIST;

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

						//Request
						// First screen, only list data
						case R_GET_LIST :
							procGetList(mc, msgUser, req, res, session);
							break;
						// Action
						case A_TRANSACTION :
							procUpdAcc(mc, msgUser, req, res, session);
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

}
