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

public class JSEDP0883 extends datapro.eibs.master.SuperServlet {


	// Action 
	// first screen
	protected static final int R_GET_LIST = 100;
	final int A_TRANSACTION = 300;

	protected String LangPath = "s";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0883() {
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
	EDP088301Message Edp088301 = null;
	ELEERRMessage msgError = null;
	JBListRec grpAccList1 = null;
	JBListRec grpAccList2 = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	DataTransaction transData = new DataTransaction();

	boolean IsNotError = false;	
	flexLog("Opennig Socket Connection Branch");

	if (req.getParameter("CUN") != null) {
		userPO.setCusNum(req.getParameter("CUN"));
	}
	if (req.getParameter("CUNAM") != null) {
		userPO.setCusName(req.getParameter("CUNAM"));
	}
	if(req.getParameter("OPTION")!=null){
		if(req.getParameter("OPTION").equals("1")){
			userPO.setPurpose("MAINTENANCE");
		} else {
			userPO.setPurpose("INQUIRY");
			}
		}

	// Send Initial data
	try {
		flexLog("Send Initial Data");

		Edp088301 = (EDP088301Message) mc.getMessageRecord("EDP088301");
		Edp088301.setH01USERID(user.getH01USR());
		Edp088301.setH01PROGRM("EDP0883");
		Edp088301.setH01TIMSYS(getTimeStamp());
		Edp088301.setE01CPTCUN(userPO.getCusNum());
		Edp088301.setH01SCRCOD("01");
		Edp088301.setH01OPECOD("0015");
		if (req.getParameter("validate") != null) {
			Edp088301.setH01OPECOD("0020");
		}

		Edp088301.send();
		Edp088301.destroy();


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
	  newmessage = mc.receiveMessage();
	  int idxAcc = -1;

	  if (newmessage.getFormatName().equals("EDP088301")) {

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
					Edp088301 = (EDP088301Message) newmessage;
					marker = Edp088301.getE01OPECDE();
					if (marker.equals("*")) {
						break;
					} else {
							myFlag = "" + idxOpt + "" + idxGrp;
							idxAcc++;
							myGrpAcc[0] = Edp088301.getE01MODLGT();
							myGrpAcc[1] = Edp088301.getE01MODCOD();
							myGrpAcc[2] = Edp088301.getE01MODDSC();
							myGrpAcc[3] = Edp088301.getE01PTSSEQ();
							myGrpAcc[4] = Edp088301.getE01PTSPTS();
							myGrpAcc[5] = Edp088301.getE01PTSFLG();
							myGrpAcc[6] = Edp088301.getE01MODPER();
							myGrpAcc[7] = Edp088301.getE01MODPTS();
							myGrpAcc[8] = Edp088301.getE01MODCAT();
							myGrpAcc[9] = Edp088301.getE01MODFL1();
							grpAccList1.addRow(myFlag, myGrpAcc);
					newmessage = mc.receiveMessage();
				}
				}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("trans1", grpAccList1);

				try {
					 if (msgError.getERRNUM().equals("0")) {
						flexLog(
						"About to call Page: "
						+ LangPath
						+ "EDP0883_punctuation_customer.jsp");
						callPage(LangPath + "EDP0883_punctuation_customer.jsp?ROW=" + idxAcc, req, res);
					} else {
						//By error
						flexLog(
						"About to call Page: "
						+ LangPath
						+ "EDP0883_punctuation_customer.jsp");
						 callPage(
						LangPath + "EDP0883_punctuation_customer.jsp",
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
	EDP088301Message Edp088301 = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	int numrec = 0;
	int iw = 0;
	if(req.getParameter("RECNUM")!=null){
		   numrec = Integer.parseInt(req.getParameter("RECNUM"));
	   }
	boolean IsNotError = false;	

	// UPDATE RECORDS, READING SELECTED ITEM ONLY
	try {
	for (int i = 1; i < numrec; i++) {
		if(req.getParameter("PTSSEQ_"+i)!=null){
		if(req.getParameter("PTSSEQ_"+i).equals("1")){
		try {
		iw++;
		Edp088301 = (EDP088301Message) mc.getMessageRecord("EDP088301");
		Edp088301.setH01USERID(user.getH01USR());
		Edp088301.setH01PROGRM("EDP0883");
		Edp088301.setH01TIMSYS(getTimeStamp());
		Edp088301.setH01OPECOD("0005");
		Edp088301.setE01CPTCUN(userPO.getCusNum());
		Edp088301.setE01MODCOD(req.getParameter("MODCOD_" + i));
		Edp088301.setE01PTSSEQ(req.getParameter("E01PTSSEQ_" + i));
		Edp088301.setE01PTSPTS(req.getParameter("E01PTSPTS_" + i));

		Edp088301.send();
		Edp088301.destroy();
	  flexLog("Edp088301 Message Sent");
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	// Receive Error
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {


			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			if (IsNotError) {

			} else {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

			try {

				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.creditproposal.JSEDP0883?SCREEN=100&validate=0020");




//				flexLog(
//					"About to call Page: "
//						+ LangPath
//						+ "EDP0870_percent_punctuation.jsp");
//
//				flexLog("About to call Page: " + LangPath + "EDP0883_punctuation_customer.jsp");
//				callPage(LangPath + "EDP0883_punctuation_customer.jsp?ROW=" + iw, req, res);

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
		}
	}
   }

   res.sendRedirect(
	   super.srctx
		   + "/servlet/datapro.eibs.creditproposal.JSEDP0883?SCREEN=100&validate=0020");

	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
   }
	// END UPDATE
		   	
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
