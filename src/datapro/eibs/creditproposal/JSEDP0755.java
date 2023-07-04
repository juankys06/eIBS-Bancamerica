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

public class JSEDP0755 extends datapro.eibs.master.SuperServlet {

	// entering options
	protected static final int R_DET = 400;
	protected static final int R_UPD = 500;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0755() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDP0755");

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
	EDP075501Message Edp075501 = null;
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
		Edp075501 = (EDP075501Message) mc.getMessageRecord("EDP075501");
		Edp075501.setH01USERID(user.getH01USR());
		Edp075501.setH01PROGRM("EDP0755");
		Edp075501.setH01TIMSYS(getTimeStamp());
		Edp075501.setH01SCRCOD("01");
		Edp075501.setH01OPECOD("0015");
		try {
			try {
				Edp075501.setE01IFMCUN(userPO.getHeader1());
			} catch (Exception e) {
			}
			try {
				Edp075501.setE01IFMFEY(req.getParameter("E01DPBFEY"));
			} catch (Exception e) {
			}
			try {
				Edp075501.setE01IFMFEM(req.getParameter("E01DPBFEM"));
			} catch (Exception e) {
			}
			if(req.getParameter("E01DPBFED")!=null){
				Edp075501.setE01IFMFED(req.getParameter("E01DPBFED"));
				}
			try {
				Edp075501.setE01IFMCFO(req.getParameter("E01IFMCFO"));
			} catch (Exception e) {
			}
			if(req.getParameter("opt")!=null){
				Edp075501.setH01FLGWK3(req.getParameter("opt"));
			}
	} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}

		Edp075501.send();
		Edp075501.destroy();

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

	  if (newmessage.getFormatName().equals("EDP075501")) {

				String marker = "";
				String myFlag = "";
				int idxOpt = -1;
				int idxGrp = -1;
				int idxGrpAcc = -1;
				int idxAcc = -1;
				int colNum = 4;
				int colNumAcc = 16;
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
					Edp075501 = (EDP075501Message) newmessage;
					marker = Edp075501.getH01FLGMAS();
					if (marker.equals("*")) {
						break;
					} else {
						// LEE DATOS DE LA CABECERA DE LA PANTALLA
						if (Edp075501.getE01IFMTYP().equals("")) {
							idxOpt++;
							myOption[0] = Edp075501.getE01IFMDSC();
							myFlag = "";
							idxGrp = -1;
							optList.addRow(myFlag, myOption);
							if (firstTime) {
								firstTime = false;
								if (!Edp075501.getE01IFMCUN().equals("0")) {
									userPO.setHeader1(Edp075501.getE01IFMCUN());
								}
								//Custummer
								userPO.setHeader2(Edp075501.getE01IFMNA1());
								//Custumer Name
								userPO.setHeader3(Edp075501.getE01IFMCFO());
								//Format
								userPO.setHeader18(Edp075501.getE01IFMCIN());
								//Industry Code
								userPO.setHeader5(Edp075501.getE01IFMLNE());
								//Bussines Code
								userPO.setHeader6(Edp075501.getE01IFMFEY());
								//Year
								userPO.setHeader7(Edp075501.getE01IFMFEM());
								//Month
							}
						} else if (
							Edp075501.getE01IFMTYP().equals("H")) { //Header
							myFlag = "" + idxOpt;
							idxGrp++;
							myGrp[0] = Edp075501.getE01IFMDSC();
							myGrp[1] = Edp075501.getE01IFMAM3();
							grpList.addRow(myFlag, myGrp);
						} else if (
							Edp075501.getE01IFMTYP().equals(
								"D")) { 
							myFlag = "" + idxOpt + "" + idxGrp;
							idxAcc++;
							myGrpAcc[0] = Edp075501.getE01IFMGLN();
							myGrpAcc[1] = Edp075501.getE01IFMDSC();
							myGrpAcc[2] = Edp075501.getE01IFMAM3();
							myGrpAcc[3] = Edp075501.getE01IFMNA1();
							myGrpAcc[6] = Edp075501.getE01DPXSEC();
							myGrpAcc[7] = Edp075501.getE01DPXLID();
							myGrpAcc[8] = Edp075501.getE01IFMAM1();
							myGrpAcc[9] = Edp075501.getE01IFMAM2();
							myGrpAcc[10] = Edp075501.getE01DPPRJ1();
							myGrpAcc[11] = Edp075501.getE01DPPAM1();
							myGrpAcc[12] = Edp075501.getE01DPPRJ2();
							myGrpAcc[13] = Edp075501.getE01DPPAM2();
							myGrpAcc[14] = Edp075501.getE01DPPRJ3();
							myGrpAcc[15] = Edp075501.getE01DPPAM3();
							grpAccList.addRow(myFlag, myGrpAcc);
						} 
					}
					newmessage = mc.receiveMessage();
				}

				if (firstTime) {
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
					Edp075501.setE01IFMFED(req.getParameter("E01IFMFED"));
					if(req.getParameter("ROW")!=null){
						Edp075501.setH01OPECOD(req.getParameter("ROW"));
					} else {
						Edp075501.setH01OPECOD("");
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
				ses.setAttribute("Edp075501", Edp075501);

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
						+ "EDP0755_client_financial_proj_main.jsp");
						callPage(
						LangPath + "EDP0755_client_financial_proj_main.jsp",
						 req,
						 res);
	 				} else {
						//By error
		 				flexLog(
			 			"About to call Page: "
				 		+ LangPath
			 			+ "EDP0755_client_financial_proj_main.jsp");
						 callPage(
			 			LangPath + "EDP0755_client_financial_proj_main.jsp",
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


	protected void procUpdProj(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		JBListRec beanList = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP075501Message Edp0755 = null;
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int numrec = 0;
		if(req.getParameter("RECNUM")!=null){
			   numrec = Integer.parseInt(req.getParameter("RECNUM"));
		   }
	try {
	   for (int i = 0; i < numrec; i++) {
		if(req.getParameter("E01DPPRJ1"+i)!=null ) {
		if(req.getParameter("E01DPXLID"+i).equals("Detalle")) {

		try {
			Edp0755 = (EDP075501Message) mc.getMessageRecord("EDP075501");
			Edp0755.setH01USERID(user.getH01USR());
			Edp0755.setH01PROGRM("EDP0730");
			Edp0755.setH01TIMSYS(getTimeStamp());
			Edp0755.setH01OPECOD("0002");
		  
			Edp0755.setE01IFMCUN(req.getParameter("E01IFMCUN"));
			Edp0755.setE01DPPFY1(req.getParameter("E01DPPFY1"));
			Edp0755.setE01DPPFY2(req.getParameter("E01DPPFY2"));
			Edp0755.setE01DPPFY3(req.getParameter("E01DPPFY3"));
			Edp0755.setE01DPPFM1(req.getParameter("E01DPPFM1"));
			Edp0755.setE01DPPFM2(req.getParameter("E01DPPFM2"));
			Edp0755.setE01DPPFM3(req.getParameter("E01DPPFM3"));
			Edp0755.setE01IFMCFO(req.getParameter("E01IFMCFO"));
			Edp0755.setE01IFMGLN(req.getParameter("DPLGLN" + i));
			Edp0755.setE01DPPRJ1(req.getParameter("E01DPPRJ1" + i));
			Edp0755.setE01DPPRJ2(req.getParameter("E01DPPRJ2" + i));
			Edp0755.setE01DPPRJ3(req.getParameter("E01DPPRJ3" + i));

			Edp0755.send();
			Edp0755.destroy();
		  flexLog("EDP075501 Message Sent");
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
//					beanList.setFlag("S", beanList.getCurrentRow());
				} else {
					msgError = (ELEERRMessage) newmessage;
					showERROR(msgError);
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);

				try {

					flexLog(
					"About to call Page: "
					+ LangPath
					+ "EDP0755_client_financial_proj_main.jsp");
					 callPage(
					LangPath + "EDP0755_client_financial_proj_main.jsp",
					req,
					 res);

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
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
   }

	  try {

		res.sendRedirect(super.srctx + 
		"/servlet/datapro.eibs.creditproposal.JSEDP0755?SCREEN=400&E01IFMCUN="
			+ userPO.getHeader1()
			+ "&E01IFMCFO="
			+ userPO.getHeader3()
			+ "&E01DPBFEY="
			+ req.getParameter("E01IFMFEY")
			+ "&E01DPBFEM="
			+ req.getParameter("E01IFMFEM")
			+ "&E01DPBFED="
			+ req.getParameter("E01IFMFED")
			+ "&ROW="
			+ req.getParameter("ROW")
			+ "&E01IFMCFA="
			+ userPO.getHeader8()
			+ "&TITULO="
			+ userPO.getHeader20()
			+ "&PLAN=Y"
			);

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
						case R_UPD :
							procUpdProj(mc, msgUser, req, res, session);
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