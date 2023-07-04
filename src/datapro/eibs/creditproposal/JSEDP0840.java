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

public class JSEDP0840 extends datapro.eibs.master.SuperServlet {


	// Action 
	// first screen
	protected static final int R_GET_CUSTTYP = 100;
	final int A_TRANSACTION = 2;
	// second screen. list modules
	protected static final int R_GET_LIST = 200;
	// third screen. list sub-modules
	protected static final int R_GET_LIST_SUB = 300;

	protected String LangPath = "s";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0840() {
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
	
	
	// first screen list of formats
	protected void procGetModules(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);

				try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0840_modules_enter.jsp");
							callPage(
								LangPath + "EDP0840_modules_enter.jsp",
								req,
								res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	}
	

	protected void procGetListMod(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDP082001Message mL0820 = null;
	ELEERRMessage msgError = null;
	JBListRec grpAccList = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	DataTransaction transData = new DataTransaction();

	boolean IsNotError = false;	
	flexLog("Opennig Socket Connection Branch");
	MessageContext mc820;
	mc820 = mc;

	userPO.setHeader2(req.getParameter("E01MODLGT"));

	// Send Initial data
	try {
		flexLog("Send Initial Data");

		mL0820 = (EDP082001Message) mc820.getMessageRecord("EDP082001");
		mL0820.setH01USERID(user.getH01USR());
		mL0820.setH01PROGRM("EDP0820");
		mL0820.setH01TIMSYS(getTimeStamp());
		mL0820.setH01SCRCOD("01");
		mL0820.setH01OPECOD("0015");
		if(req.getParameter("E01MODLGT")!=null){
			   mL0820.setE01MODLGT(req.getParameter("E01MODLGT"));
		   }

		mL0820.send();
		mL0820.destroy();


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message

	  try {
	  newmessage = mc820.receiveMessage();

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
	  newmessage = mc820.receiveMessage();
	  int idxAcc = -1;

	  if (newmessage.getFormatName().equals("EDP082001")) {

				String marker = "";
				String myFlag = "";
				int idxOpt = -1;
				int idxGrp = -1;
				int idxGrpAcc = -1;
				int colNum = 5;
				int colNumAcc = 12;
				try {
					grpAccList = new datapro.eibs.beans.JBListRec();
					grpAccList.init(colNumAcc);
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
					mL0820 = (EDP082001Message) newmessage;
					marker = mL0820.getE01OPECDE();
					if (marker.equals("*")) {
						break;
					} else {
							myFlag = "" + idxOpt + "" + idxGrp;
							idxAcc++;
							myGrpAcc[0] = mL0820.getE01MODLGT();
							myGrpAcc[1] = mL0820.getE01MODCOD();
							myGrpAcc[2] = mL0820.getE01MODDSC();
							myGrpAcc[3] = mL0820.getE01MODPER();
							myGrpAcc[4] = mL0820.getE01MODPET();
							grpAccList.addRow(myFlag, myGrpAcc);
					newmessage = mc820.receiveMessage();
				}
				}
				if (firstTime) {
					userPO.setHeader1(mL0820.getE01MODPET());
				}

				if (grpAccList.getNoResult()) {
					transData.setTrNum("20");
				} else {
					transData.setTrNum("0");
				}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

				flexLog("Putting java beans into the session");
				ses.setAttribute("accList", grpAccList);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("mL0820", mL0820);
				ses.setAttribute("transData", transData);
				ses.setAttribute("trans", grpAccList);

				try {
					 if (msgError.getERRNUM().equals("0")) {
						flexLog(
						"About to call Page: "
						+ LangPath
						+ "EDP0840_modules_list.jsp");
						callPage(LangPath + "EDP0840_modules_list.jsp?ROW=" + idxAcc, req, res);
					} else {
						//By error
						flexLog(
						"About to call Page: "
						+ LangPath
						+ "EDP0840_modules_enter.jsp");
						 callPage(
						LangPath + "EDP0840_modules_enter.jsp",
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

	protected void procGetListSub(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDP084001Message mL0840 = null;
	ELEERRMessage msgError = null;
	JBListRec grpAccList = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	DataTransaction transData = new DataTransaction();

	boolean IsNotError = false;	
	flexLog("Opennig Socket Connection Branch");
	MessageContext mc840;
	mc840 = mc;

	if (req.getParameter("E01MODCOD") != null) {
		userPO.setHeader3(req.getParameter("E01MODCOD"));
	}
	if (req.getParameter("E01MODDSC") != null) {
		userPO.setHeader4(req.getParameter("E01MODDSC"));
	}

	// Send Initial data
	try {
		flexLog("Send Initial Data");

		mL0840 = (EDP084001Message) mc840.getMessageRecord("EDP084001");
		mL0840.setH01USERID(user.getH01USR());
		mL0840.setH01PROGRM("EDP0840");
		mL0840.setH01TIMSYS(getTimeStamp());
		mL0840.setH01SCRCOD("01");
		mL0840.setH01OPECOD("0015");
 	    mL0840.setE01PTSLGT(userPO.getHeader2());
		mL0840.setE01PTSMOD(userPO.getHeader3());

		mL0840.send();
		mL0840.destroy();

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message

	  try {
	  newmessage = mc840.receiveMessage();

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
	  newmessage = mc840.receiveMessage();
	  int idxAcc = -1;

	  if (newmessage.getFormatName().equals("EDP084001")) {

				String marker = "";
				String myFlag = "";
				int idxOpt = -1;
				int idxGrp = -1;
				int idxGrpAcc = -1;
				int colNum = 5;
				int colNumAcc = 12;
				try {
					grpAccList = new datapro.eibs.beans.JBListRec();
					grpAccList.init(colNumAcc);
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
					mL0840 = (EDP084001Message) newmessage;
					marker = mL0840.getE01OPECDE();
					if (marker.equals("*")) {
						break;
					} else {
							myFlag = "" + idxOpt + "" + idxGrp;
							idxAcc++;
							myGrpAcc[1] = mL0840.getE01PTSSEQ();
							myGrpAcc[2] = mL0840.getE01PTSDSC();
							myGrpAcc[3] = mL0840.getE01PTSPTS();
							myGrpAcc[4] = mL0840.getE01PTSVMN();
							myGrpAcc[5] = mL0840.getE01PTSVMX();
							myGrpAcc[6] = mL0840.getE01PTSVAL();
							grpAccList.addRow(myFlag, myGrpAcc);
					newmessage = mc840.receiveMessage();
				}
				}
				if (firstTime) {
					userPO.setHeader1(mL0840.getE01PTSMOD());
				}

				if (grpAccList.getNoResult()) {
					transData.setTrNum("10");
				} else {
					transData.setTrNum("0");
				}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

				flexLog("Putting java beans into the session");
				ses.setAttribute("accList", grpAccList);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("mL0840", mL0840);
				ses.setAttribute("transData", transData);
				ses.setAttribute("trans", grpAccList);

				try {
					 if (msgError.getERRNUM().equals("0")) {
						flexLog(
						"About to call Page: "
						+ LangPath
						+ "EDP0840_submodules_list.jsp");
						callPage(LangPath + "EDP0840_submodules_list.jsp?ROW=" + idxAcc, req, res);
					} else {
						//By error
						flexLog(
						"About to call Page: "
						+ LangPath
						+ "EDP0840_submodules_enter.jsp");
						 callPage(
						LangPath + "EDP0840_submodules_enter.jsp",
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


	private void procActionTr(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession session)
	throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		DataTransaction transData =	(DataTransaction) session.getAttribute("transData");

		int colNum = 10;
		JBListRec trList = new JBListRec();
		trList.init(colNum);
		JBListRec trListOld = (JBListRec) session.getAttribute("trans");
		trListOld.initRow();
		
		int maxRow = 0;
		try {
			maxRow = Integer.parseInt(req.getParameter("RECNUM"));
		} catch (Exception e) {
			maxRow = 0;
		}
		int realMaxRow = maxRow;
		int opt;
		try {
			opt = Integer.parseInt(req.getParameter("opt"));
		} catch (Exception e) {
			opt = 0;
		}

		
		String checked = "";
		String sel2 = "";
		String myFlag = "";
		String roww = "";
		int rowww = 0;
		
		for (int row = 0; row < maxRow; row++) {
			try {
				checked = req.getParameter("TRANSROW_" + row);
				if (!checked.equals("checked")) { checked = "";	}
			} catch (Exception e) {
				checked = "";
			}
			if (opt == 7) {checked = "" ;}

			if ((opt == 1) & (checked.equals("checked"))) {
				realMaxRow--;
			} else {
				try {
					sel2 =
						req.getParameter("E01PTSSEQ_" + row)
							+ req.getParameter("E01PTSDSC_" + row)
							+ req.getParameter("E01PTSPTS_" + row);
					sel2 = sel2.trim();
				} catch (Exception e) {
					sel2 = "1";
				}
				if (sel2.equals("") || sel2.equals("null")) sel2 = "0";
				int sel3 = 0;
				try {
					sel3 = Integer.parseInt(sel2);
				} catch (Exception e) {
					sel3 = 1;
				}
				if (sel3 == 0) { // from wrong, transaction data is not complete
					sel2 = ""; 
				} 

				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) myRow[i] = "";

				if (!req.getParameter("E01PTSSEQ_" + row).equals("")) {
				rowww = rowww+1;
				roww = "" + rowww + "";
				myRow[1] = roww;
				myRow[2] = req.getParameter("E01PTSDSC_" + row).toUpperCase();
				myRow[3] = req.getParameter("E01PTSPTS_" + row).toUpperCase();
				myRow[4] = req.getParameter("E01PTSVMN_" + row).toUpperCase();
				myRow[5] = req.getParameter("E01PTSVMX_" + row).toUpperCase();
				myRow[6] = req.getParameter("E01PTSVAL_" + row).toUpperCase();
				trList.addRow(myFlag, myRow);

				if ((opt == 2) & (checked.equals("checked"))) {
					myRow[24] = "*";
					trList.addRow(myFlag, myRow);
					realMaxRow++;
				}
				}
			}
		}

		session.setAttribute("trans", trList);
		session.setAttribute("transData", transData);
		
		switch (opt) {
			case 0 :
				break;
			case 4 :
//				procActionDeleteBatch(user, req, res, session);
				break;
			case 5 :
			case 6 :
			case 7 :
				procUpdAcc(mc, user, req, res, session);
				break;
			default :

				try {			
					flexLog("About to call Page: " + LangPath + "EDP0840_submodules_list.jsp");
					callPage(LangPath + "EDP0840_submodules_list.jsp?ROW=" + maxRow, req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

				break;
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
	EDP084001Message mL0840 = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String checked = "";
	int numrec = 0;
	int iw = 0;
	if(req.getParameter("RECNUM")!=null){
		   numrec = Integer.parseInt(req.getParameter("RECNUM"));
	   }

// DELETE ALL RECORDS BEFORE CREATE RECORDS FROM LIST

	   try {
	   mL0840 = (EDP084001Message) mc.getMessageRecord("EDP084001");
	   mL0840.setH01USERID(user.getH01USR());
	   mL0840.setH01PROGRM("EDP0840");
	   mL0840.setH01TIMSYS(getTimeStamp());
	   mL0840.setH01OPECOD("0010");
	   mL0840.setE01PTSLGT(userPO.getHeader2());
	   mL0840.setE01PTSMOD(userPO.getHeader3());

	   mL0840.send();
	   mL0840.destroy();
	 flexLog("EDP084001 Message Sent");
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
		   boolean IsNotError = msgError.getERRNUM().equals("0");
		   if (IsNotError) {
		   	
		   	// ADD RECORDS
			try {
			for (int i = 0; i < numrec; i++) {
				try {
					checked = req.getParameter("TRANSROW_" + i);
					if (!checked.equals("checked")) { checked = "";	}
				} catch (Exception e) {
					checked = "";
				}

//  				if((!req.getParameter("E01PTSDSC_"+i).equals(""))&&(!checked.equals("checked"))){
				if(!req.getParameter("E01PTSDSC_"+i).equals("")){
				try {
				iw++;
				mL0840 = (EDP084001Message) mc.getMessageRecord("EDP084001");
				mL0840.setH01USERID(user.getH01USR());
				mL0840.setH01PROGRM("EDP0840");
				mL0840.setH01TIMSYS(getTimeStamp());
				mL0840.setH01OPECOD("0001");
				mL0840.setE01PTSLGT(userPO.getHeader2());
				mL0840.setE01PTSMOD(userPO.getHeader3());
				mL0840.setE01PTSSEQ(req.getParameter("E01PTSSEQ_" + i));
				mL0840.setE01PTSDSC(req.getParameter("E01PTSDSC_" + i));
				mL0840.setE01PTSPTS(req.getParameter("E01PTSPTS_" + i));
				mL0840.setE01PTSVMN(req.getParameter("E01PTSVMN_" + i));
				mL0840.setE01PTSVMX(req.getParameter("E01PTSVMX_" + i));
				mL0840.setE01PTSVAL(req.getParameter("E01PTSVAL_" + i));

				mL0840.send();
				mL0840.destroy();
			  flexLog("EDP084001 Message Sent");
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

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0840_modules_list.jsp");

						flexLog("About to call Page: " + LangPath + "EDP0840_submodules_list.jsp");
						callPage(LangPath + "EDP0840_submodules_list.jsp?ROW=" + iw, req, res);

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

		   res.sendRedirect(
			   super.srctx
				   + "/servlet/datapro.eibs.creditproposal.JSEDP0840?SCREEN=300&E01MODLGT="+userPO.getHeader2());

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
		   }
			// END ADD RECORDS
		   	
		   } else {
			   msgError = (ELEERRMessage) newmessage;
			   showERROR(msgError);
			   flexLog("Putting java beans into the session");
			   ses.setAttribute("error", msgError);

		   try {

			   flexLog(
				   "About to call Page: "
					   + LangPath
					   + "EDP0840_modules_list.jsp");

			   flexLog("About to call Page: " + LangPath + "EDP0840_modules_list.jsp");
     		   callPage(LangPath + "EDP0840_modules_list.jsp?ROW=" + numrec, req, res);

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

			int screen = R_GET_CUSTTYP;

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
						case R_GET_CUSTTYP :
							procGetModules(mc, msgUser, req, res, session);
							break;
						// Second screen, only list data
						case R_GET_LIST :
							procGetListMod(mc, msgUser, req, res, session);
							break;
						// third screen, list submodules
						case R_GET_LIST_SUB :
							procGetListSub(mc, msgUser, req, res, session);
							break;
						// Action - UPDATE
						case A_TRANSACTION :
							procActionTr(mc, msgUser, req, res, session);
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
