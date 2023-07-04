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

public class JSEDP0739 extends datapro.eibs.master.SuperServlet {

	// entering options
	protected static final int R_DET = 400;
	protected static final int R_DET1 = 500;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0739() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDP0739");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
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
	EDP073901Message Edp073901 = null;
	ELEERRMessage msgError = null;
	JBListRec optList = null;
	JBListRec grpList = null;
	JBListRec grpAccList = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	boolean IsNotError = false;	
//	Socket s1 = null;
	flexLog("Opennig Socket Connection Branch");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		Edp073901 = (EDP073901Message) mc.getMessageRecord("EDP073901");
		Edp073901.setH01USERID(user.getH01USR());
		Edp073901.setH01PROGRM("EDP0739");
		Edp073901.setH01TIMSYS(getTimeStamp());
		Edp073901.setH01SCRCOD("01");
		Edp073901.setH01OPECOD("0015");
		try {
			try {
				Edp073901.setE01IFMCUN(userPO.getHeader1());
			} catch (Exception e) {
			}
			try {
				Edp073901.setE01IFMFEY(req.getParameter("E01DPBFEY"));
			} catch (Exception e) {
			}
			try {
				Edp073901.setE01IFMFEM(req.getParameter("E01DPBFEM"));
			} catch (Exception e) {
			}
			if(req.getParameter("E01DPBFED")!=null){
				Edp073901.setE01IFMFED(req.getParameter("E01DPBFED"));
				}
			try {
				Edp073901.setE01IFMCFO(req.getParameter("E01IFMCFO"));
			} catch (Exception e) {
			}
			if(req.getParameter("opt")!=null){
				Edp073901.setH01FLGWK3(req.getParameter("opt"));
			}
	} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}

		Edp073901.send();
		Edp073901.destroy();

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

	  if (newmessage.getFormatName().equals("EDP073901")) {

				String marker = "";
				String myFlag = "";
				int idxOpt = -1;
				int idxGrp = -1;
				int idxGrpAcc = -1;
				int idxAcc = -1;
				int colNum = 4;
				int colNumAcc = 19;
				int colNumMem = 4;
				try {
					optList = new datapro.eibs.beans.JBListRec();
					optList.init(colNum);
					grpList = new datapro.eibs.beans.JBListRec();
					grpList.init(colNum);
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

				while (true) {
					Edp073901 = (EDP073901Message) newmessage;
					marker = Edp073901.getH01FLGMAS();
					if (marker.equals("*")) {
						break;
					} else {
						// LEE DATOS DE LA CABECERA DE LA PANTALLA
						if (Edp073901.getE01IFMTYP().equals("")) {
							idxOpt++;
							myOption[0] = Edp073901.getE01IFMDSC();
							myFlag = "";
							idxGrp = -1;
							optList.addRow(myFlag, myOption);
							if (firstTime) {
								firstTime = false;
								if (!Edp073901.getE01IFMCUN().equals("0")) {
									userPO.setHeader1(Edp073901.getE01IFMCUN());
								}
								//Custummer
//								userPO.setHeader2(Edp073901.getE01IFMNA1());
								//Custumer Name
//								userPO.setHeader3(Edp073901.getE01IFMCFO());
								//Format
//								userPO.setHeader18(Edp073901.getE01IFMCIN());
								//Industry Code
//								userPO.setHeader5(Edp073901.getE01IFMLNE());
								//Bussines Code
//								userPO.setHeader6(Edp073901.getE01IFMFEY());
								//Year
//								userPO.setHeader7(Edp073901.getE01IFMFEM());
								//Month
							}
						} else if (
							Edp073901.getE01IFMTYP().equals("H")) { //Header
							myFlag = "" + idxOpt;
							idxGrp++;
							myGrp[0] = Edp073901.getE01IFMDSC();
							myGrp[1] = Edp073901.getE01IFMAMT();
							grpList.addRow(myFlag, myGrp);
						} else if (
							Edp073901.getE01IFMTYP().equals(
								"D")) { // detail Edp073901.getE01IFMTYP().equals("D")
							myFlag = "" + idxOpt + "" + idxGrp;
							idxAcc++;
							myGrpAcc[0] = Edp073901.getE01IFMGLN();
							myGrpAcc[1] = Edp073901.getE01IFMDSC();
							myGrpAcc[2] = Edp073901.getE01IFMAMT();
							myGrpAcc[3] = Edp073901.getE01IFMNA1();
							myGrpAcc[6] = Edp073901.getE01DPXSEC();
							myGrpAcc[7] = Edp073901.getE01DPXLID();
							myGrpAcc[8] = Edp073901.getE01IFMAM1();
							myGrpAcc[9] = Edp073901.getE01IFMAM2();
							myGrpAcc[10] = Edp073901.getE01IFVABS();
							myGrpAcc[11] = Edp073901.getE01IFVREL();
							myGrpAcc[12] = Edp073901.getE01IFANVE();
							myGrpAcc[13] = Edp073901.getE01IFVAB2();
							myGrpAcc[14] = Edp073901.getE01IFVRE2();
							myGrpAcc[15] = Edp073901.getE01IFANV2();
							myGrpAcc[16] = Edp073901.getE01IFVAB1();
							myGrpAcc[17] = Edp073901.getE01IFVRE1();
							myGrpAcc[18] = Edp073901.getE01IFANV1();
							grpAccList.addRow(myFlag, myGrpAcc);
						} 
					}
					newmessage = mc.receiveMessage();
				}

//				if (firstTime) {
//					try {
//						userPO.setHeader6(req.getParameter("E01IFMFEY"));
//					} catch (Exception e) {
//						userPO.setHeader6("");
//					}
//					try {
//						userPO.setHeader7(req.getParameter("E01IFMFEM"));
//					} catch (Exception e) {
//						userPO.setHeader7("");
//					}
//					try {
//						userPO.setHeader3(req.getParameter("E01IFMCFO"));
//					} catch (Exception e) {
//						userPO.setHeader3("");
//					}
//				}

				try {
					Edp073901.setE01IFMFED(req.getParameter("E01IFMFED"));
					if(req.getParameter("ROW")!=null){
						Edp073901.setH01OPECOD(req.getParameter("ROW"));
					} else {
						Edp073901.setH01OPECOD("");
					}
				} catch (Exception e) {
				}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

				flexLog("Putting java beans into the session");
				ses.setAttribute("optList", optList);
				ses.setAttribute("grpList", grpList);
				ses.setAttribute("accList", grpAccList);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("Edp073901", Edp073901);

				if(req.getParameter("TITULO")!=null){
//					userPO.setHeader20(req.getParameter("TITULO"));
				}
				if(req.getParameter("RETORNO")!=null){
//					userPO.setHeader11(req.getParameter("RETORNO"));
				}
				// opt=4 consulta
				if(req.getParameter("opt")!=null){
//					userPO.setOption(req.getParameter("opt"));
				}
				try {
					 if (msgError.getERRNUM().equals("0")) {
		 				flexLog(
			 			"About to call Page: "
				 		+ LangPath
						+ "EDP0739_client_financial_var_comp2.jsp");
						callPage(
						LangPath + "EDP0739_client_financial_var_comp2.jsp",
						 req,
						 res);
	 				} else {
						//By error
		 				flexLog(
			 			"About to call Page: "
				 		+ LangPath
			 			+ "EDP0739_client_financial_var_comp2.jsp");
						 callPage(
			 			LangPath + "EDP0739_client_financial_var_comp2.jsp",
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

// PANTALLA INDICES FINANCIEROS COMPARATIVO
   protected void procReqIndComp(
	   MessageContext mc,
	   ESS0030DSMessage user,
	   HttpServletRequest req,
	   HttpServletResponse res,
	   HttpSession ses)
	   throws ServletException, IOException {

	   MessageRecord newmessage = null;
	   EDP073901Message Edp073901 = null;
	   ELEERRMessage msgError = null;
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

	   userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	   if (req.getParameter("E01DPFFMT") != null) {
		   userPO.setHeader9(req.getParameter("E01DPFFMT"));
	   }
	   if (req.getParameter("E01DPIDSC") != null) {
		   userPO.setHeader10(req.getParameter("E01DPIDSC"));
	   }
	   // Send Initial data
	   try {
		   Edp073901 = (EDP073901Message) mc.getMessageRecord("EDP073401");
		   Edp073901.setH01USERID(user.getH01USR());
		   Edp073901.setH01PROGRM("EDP073401");
		   Edp073901.setH01TIMSYS(getTimeStamp());
		   Edp073901.setH01SCRCOD("01");
		   Edp073901.setH01OPECOD("0015");
		try {
			try {
				Edp073901.setE01IFMCUN(userPO.getHeader1());
			} catch (Exception e) {
			}
			try {
				Edp073901.setE01IFMCFO(userPO.getHeader3());
			} catch (Exception e) {
			}
			try {
				Edp073901.setE01IFMFEY(userPO.getHeader6());
			} catch (Exception e) {
			}
			try {
				Edp073901.setE01IFMFEM(userPO.getHeader7());
			} catch (Exception e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}

		Edp073901.send();
		Edp073901.destroy();
		flexLog("EDP073401 Message Sent");

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

	   try {
		   newmessage = mc.receiveMessage();

		   if (newmessage.getFormatName().equals("EDP073401")) {

			   JBObjList beanList = new JBObjList();

			   boolean firstTime = true;
			   String marker = "";
			   String myFlag = "";
			   StringBuffer myRow = null;
			   String chk = "";
				String val = "";
			   String TableTyp = "";
			   String chkOfac = "";
			   String chkWarn = "";
			   int compar = 0;
			   int indexRow = 0;
				int varfin = 0;
			   while (true) {

				   Edp073901 = (EDP073901Message) newmessage;

				   marker = Edp073901.getH01FLGMAS();

				   if (firstTime) {
					   firstTime = false;
					   chk = "checked";

				   } else {
					   chk = "";
				   }

				   if (marker.equals("*")) {
					   beanList.setShowNext(false);
					   break;
				   } else {

	
					   beanList.addRow(Edp073901);

					   if (marker.equals("+")) {
						   beanList.setShowNext(true);

						   break;
					   }
				   }
				   newmessage = mc.receiveMessage();
			   }

			   flexLog("Putting java beans into the session");
			   ses.setAttribute("Edp073901List", beanList);
			   ses.setAttribute("userPO", userPO);
				ses.setAttribute("Edp073901", Edp073901);

			   try {

					   if (msgError.getERRNUM().equals("0")) {
						   flexLog(
							   "About to call Page: "
								   + LangPath
								   + "EDP0739_client_financial_var_comp.jsp");
						   callPage(
							   LangPath + "EDP0739_client_financial_var_comp.jsp",
							   req,
							   res);
					   } else {
						   flexLog(
							   "About to call Page: "
								   + LangPath
								   + "EDP0739_client_financial_var_comp.jsp");

						   callPage(
							   LangPath + "EDP0739_client_financial_var_comp.jsp",
							   req,
							   res);

					   }
			   } catch (Exception e) {
				   flexLog("Exception calling page " + e);
			   }

		   } else
			   flexLog("Message " + newmessage.getFormatName() + " received.");

	   } catch (Exception e) {
		   e.printStackTrace();
		   flexLog("Error: " + e);
		   throw new RuntimeException("Socket Communication Data Receiving");
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
						case R_DET1 :
							procReqIndComp(mc, msgUser, req, res, session);
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