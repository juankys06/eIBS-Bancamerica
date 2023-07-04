package datapro.eibs.creditproposal;

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
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.sockets.*;

public class JSEDP0880 extends datapro.eibs.master.SuperServlet {

	// entering options
	protected static final int R_DET = 100;
	protected static final int A_FINANCIAL = 200;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0880() {
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

	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procActionFinancial(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		MessageRecord newmessage = null;

		EDP073001Message mL0730 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String opeCode = "";
		String FormatCode = "";
		String flagAccept = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		opeCode = "0002";

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			mL0730 = (EDP073001Message) mc.getMessageRecord("EDP073001");
			mL0730.setH01USERID(user.getH01USR());
			mL0730.setH01PROGRM("EDP0730");
			mL0730.setH01TIMSYS(getTimeStamp());
			mL0730.setH01OPECOD(opeCode);
//			mL0730.setE01IFMCFO(FormatCode);
			// all the fields here
			java.util.Enumeration enu = mL0730.fieldEnumeration();
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
			mL0730.send();
			mL0730.destroy();
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

			if (newmessage.getFormatName().equals("EDP073001")) {

				if (IsNotError) { // There are no errors
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=400&E01IFMCUN="
							+ userPO.getHeader1()
							+ "&E01IFMCFO="
							+ userPO.getHeader3()
							+ "&E01IFMFEY="
							+ req.getParameter("E01IFMFEY")
							+ "&E01IFMFEM="
							+ req.getParameter("E01IFMFEM")
							+ "&E01IFMFED="
							+ req.getParameter("E01IFMFED")
							+ "&ROW="
							+ req.getParameter("ROW")
							+ "&E01IFMCFA="
							+ userPO.getHeader8()
							+ "&TITULO="
							+ userPO.getHeader20());
				} else { // There are errors
					mL0730 = (EDP073001Message) newmessage;

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					//ses.setAttribute("plan", mL0730);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0730_client_financial.jsp?ROW="
								+ req.getParameter("ROW"));
						res.sendRedirect(super.srctx + 
							LangPath
								+ "EDP0730_client_financial.jsp?ROW="
								+ req.getParameter("ROW"));
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
	protected void procActionEnter(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDP073001Message mL0730 = null;
	ELEERRMessage msgError = null;
	JBListRec optList = null;
	JBListRec grpList = null;
	JBListRec grpAccList = null;
	JBListRec grpMemList = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	boolean IsNotError = false;	
//	Socket s1 = null;
	flexLog("Opennig Socket Connection Branch");
	MessageContext mc730;
	mc730 = mc;

	// Send Initial data
	try {
		flexLog("Send Initial Data");

		mL0730 = (EDP073001Message) mc730.getMessageRecord("EDP073001");
		mL0730.setH01USERID(user.getH01USR());
		mL0730.setH01PROGRM("EDP0730");
		mL0730.setH01TIMSYS(getTimeStamp());
		mL0730.setH01SCRCOD("01");
		mL0730.setH01OPECOD("0015");
		try {
			try {
				mL0730.setE01IFMCUN(req.getParameter("E01IFMCUN"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMFEY(req.getParameter("E01IFMFEY"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMFEM(req.getParameter("E01IFMFEM"));
			} catch (Exception e) {
			}
			if(req.getParameter("E01IFMFED")!=null){
				mL0730.setE01IFMFED(req.getParameter("E01IFMFED"));
				}
			try {
				mL0730.setE01IFMCFO(req.getParameter("E01IFMCFO"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMCFA(req.getParameter("E01IFMCFA"));
			} catch (Exception e) {
			}
			if(req.getParameter("opt")!=null){
				mL0730.setH01FLGWK3(req.getParameter("opt"));
			}
	} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}

		mL0730.send();
		mL0730.destroy();


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message

	  try {
	  newmessage = mc730.receiveMessage();

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
	  newmessage = mc730.receiveMessage();

	  if (newmessage.getFormatName().equals("EDP073001")) {

				String marker = "";
				String myFlag = "";
				int idxOpt = -1;
				int idxGrp = -1;
				int idxGrpAcc = -1;
				int idxAcc = -1;
				//Initial... 
				/*try {
					optList = new datapro.eibs.beans.JBList();
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}*/
				int colNum = 4;
				int colNumAcc = 12;
				int colNumMem = 4;
				try {
					optList = new datapro.eibs.beans.JBListRec();
					optList.init(colNum);
					grpList = new datapro.eibs.beans.JBListRec();
					grpList.init(colNum);
					grpAccList = new datapro.eibs.beans.JBListRec();
					grpAccList.init(colNumAcc);
					grpMemList = new datapro.eibs.beans.JBListRec();
					grpMemList.init(colNumMem);
//					userPO = new datapro.eibs.beans.UserPos();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				String myGrp[] = new String[colNum];
				String myGrpAcc[] = new String[colNumAcc];
				String myGrpMem[] = new String[colNumMem];
				String myOption[] = new String[colNum];
				boolean firstTime = true;
				for (int i = 0; i < colNum; i++) {
					myGrp[i] = "";
//					myGrpAcc[i] = "";
					myOption[i] = "";
				}
				for (int i = 0; i < colNumAcc; i++) {
					myGrpAcc[i] = "";
				}

				while (true) {
					mL0730 = (EDP073001Message) newmessage;
					marker = mL0730.getH01FLGMAS();
					if (marker.equals("*")) {
						break;
					} else {
						// LEE DATOS DE LA CABECERA DE LA PANTALLA
						if (mL0730.getE01IFMTYP().equals("")) {
							idxOpt++;
							myOption[0] = mL0730.getE01IFMDSC();
							//"<option value=\"" + idxOpt + "\">" + mL0730.getE01IFMDSC() + "</option>";
							myFlag = "";
							idxGrp = -1;
							optList.addRow(myFlag, myOption);
							if (firstTime) {
								firstTime = false;
								if (!mL0730.getE01IFMCUN().equals("0")) {
									userPO.setHeader1(mL0730.getE01IFMCUN());
								}
								//Custummer
								userPO.setHeader2(mL0730.getE01IFMNA1());
								//Custumer Name
								userPO.setHeader3(mL0730.getE01IFMCFO());
								//Format
								userPO.setHeader18(mL0730.getE01IFMCIN());
								//Industry Code
								userPO.setHeader5(mL0730.getE01IFMLNE());
								//Bussines Code
								userPO.setHeader6(mL0730.getE01IFMFEY());
								//Year
								userPO.setHeader7(mL0730.getE01IFMFEM());
								//Month
								userPO.setHeader8(mL0730.getE01IFMCFA());
								//Audit Firm
								userPO.setHeader9(mL0730.getE01CNORCD());
								// inf revision
								userPO.setHeader13(mL0730.getE01DPBREV());
								userPO.setHeader14(mL0730.getE01DPBVEN());
								userPO.setHeader15(mL0730.getE01DPBFVD());
								userPO.setHeader16(mL0730.getE01DPBFVM());
								userPO.setHeader17(mL0730.getE01DPBFVA());
							}
						} else if (
							mL0730.getE01IFMTYP().equals("H")) { //Header
							myFlag = "" + idxOpt;
							idxGrp++;
							myGrp[0] = mL0730.getE01IFMDSC();
							myGrp[1] = mL0730.getE01IFMAMT();
							grpList.addRow(myFlag, myGrp);
						} else if (
							mL0730.getE01IFMTYP().equals(
								"D")) { // detail mL0730.getE01IFMTYP().equals("D")
							myFlag = "" + idxOpt + "" + idxGrp;
							idxAcc++;
							myGrpAcc[0] = mL0730.getE01IFMGLN();
							myGrpAcc[1] = mL0730.getE01IFMDSC();
							myGrpAcc[2] = mL0730.getE01IFMAMT();
							myGrpAcc[3] = mL0730.getE01IFMNA1();
							myGrpAcc[4] = mL0730.getE01IFMCFA();
							myGrpAcc[5] = mL0730.getE01CNORCD();
							myGrpAcc[6] = mL0730.getE01DPXSEC();
							myGrpAcc[7] = mL0730.getE01DPXLID();
							myGrpAcc[8] = mL0730.getE01IFMAM1();
							myGrpAcc[9] = mL0730.getE01IFMAM2();
							myGrpAcc[10] = mL0730.getE01DPLSEC();
							myGrpAcc[11] = mL0730.getE01IFMOPR();
							grpAccList.addRow(myFlag, myGrpAcc);
						} else if (
							mL0730.getE01IFMTYP().equals(
							"M")) { // detail mL0730.getE01IFMTYP().equals("D")
							myFlag = "" + idxGrp + "" + idxAcc;
							myGrpMem[0] = mL0730.getE01DPMS01();
							myGrpMem[1] = mL0730.getE01DPMM01();
							myGrpMem[2] = mL0730.getE01DPMP01();
							myGrpMem[3] = mL0730.getE01IFMGLN();
							grpMemList.addRow(myFlag, myGrpMem);

						} else {
						}
					}
					newmessage = mc730.receiveMessage();
				}

				if (firstTime) {
//					try {
//						userPO.setHeader1(req.getParameter("E01IFMCUN"));
//					} catch (Exception e) {
//						userPO.setHeader1("");
//					}
					try {
						userPO.setHeader6(req.getParameter("E01IFMFEY"));
					} catch (Exception e) {
						userPO.setHeader6("");
					}
					try {
						userPO.setHeader7(req.getParameter("E01IFMFEM"));
					} catch (Exception e) {
						userPO.setHeader7("");
					}
					try {
						userPO.setHeader3(req.getParameter("E01IFMCFO"));
					} catch (Exception e) {
						userPO.setHeader3("");
					}
				}

				try {
					mL0730.setE01DPBFED(req.getParameter("E01IFMFED"));
					mL0730.setE01IFMFED(req.getParameter("E01IFMFED"));
					if(req.getParameter("ROW")!=null){
						mL0730.setH01OPECOD(req.getParameter("ROW"));
					} else {
						mL0730.setH01OPECOD("");
					}
				} catch (Exception e) {
				}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

				flexLog("Putting java beans into the session");
				ses.setAttribute("optList", optList);
// el mismo arreglo lo copio para el siguiente formato
				ses.setAttribute("grpList", grpList);
				ses.setAttribute("accList", grpAccList);
				ses.setAttribute("memList", grpMemList);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("mL0730", mL0730);

				if(req.getParameter("TITULO")!=null){
					userPO.setHeader20(req.getParameter("TITULO"));
				}
				if(req.getParameter("RETORNO")!=null){
					userPO.setHeader11(req.getParameter("RETORNO"));
				}
				// opt=4 consulta
				if(req.getParameter("opt")!=null){
					userPO.setOption(req.getParameter("opt"));
				}
				try {
					 if (msgError.getERRNUM().equals("0")) {
		 				flexLog(
			 			"About to call Page: "
				 		+ LangPath
						+ "EDP0730_client_financial.jsp");
						callPage(
						LangPath + "EDP0730_client_financial.jsp",
						 req,
						 res);
	 				} else {
						//By error
		 				flexLog(
			 			"About to call Page: "
				 		+ LangPath
			 			+ "EDP0730_client_financial.jsp");
						 callPage(
			 			LangPath + "EDP0730_client_financial.jsp",
			 			req,
						 res);

					 }


				///**
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}

//			} else
//				flexLog("Message " + newmessage.getFormatName() + " received.");


			} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		finally
		{
//		 s1.close();
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

			int screen = R_DET;
			String parametro;

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
						parametro = req.getParameter("RETORNO");
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case R_DET :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						case A_FINANCIAL :
							procActionFinancial(mc, msgUser, req, res, session);
							break;
						
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
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