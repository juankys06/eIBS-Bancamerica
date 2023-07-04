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

public class JSEDP0730 extends datapro.eibs.master.SuperServlet {

	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;
	protected static final int R_IDX = 300;
	protected static final int R_DET = 400;
	protected static final int R_IDX_COMP = 600;
	protected static final int A_FINANCIAL = 2;
	protected static final int R_IDX_FINANCIAL = 3;
	protected static final int R_MEMO = 4;
	protected static final int R_UPD_MEMO = 5;
	protected static final int RUPDVENC = 6;
	protected static final int UpdAcc = 8;
	protected static final int RLISTANALYSIS = 800; //
	protected static final int RACTDANALYSIS = 810; //
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0730() {
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



	protected void procUpdAcc(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		JBListRec beanList = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP073001Message mL0730 = null;
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String CnOrcd1 = "";
		String CnOrcd2 = req.getParameter("E01CNORCD");

		int numrec = 0;
		if(req.getParameter("RECNUM")!=null){
			   numrec = Integer.parseInt(req.getParameter("RECNUM"));
		   }
	try {
	   for (int i = 0; i < numrec; i++) {
		if(req.getParameter("VLRCTA"+i)!=null){

			CnOrcd1 = req.getParameter("CNORCD"+i);

			if(CnOrcd1.equals(CnOrcd2)){

		try {
			mL0730 = (EDP073001Message) mc.getMessageRecord("EDP073001");
			mL0730.setH01USERID(user.getH01USR());
			mL0730.setH01PROGRM("EDP0730");
			mL0730.setH01TIMSYS(getTimeStamp());
			mL0730.setH01OPECOD("0002");
		  
			mL0730.setE01IFMCUN(req.getParameter("E01IFMCUN"));
			mL0730.setE01IFMFEY(req.getParameter("E01IFMFEY"));
			mL0730.setE01IFMFED(req.getParameter("E01IFMFED"));
			mL0730.setE01IFMFEM(req.getParameter("E01IFMFEM"));
			mL0730.setE01IFMCFO(req.getParameter("E01IFMCFO"));
			mL0730.setE01CNORCD(req.getParameter("CNORCD" + i));
			mL0730.setE01IFMGLN(req.getParameter("DPLGLN" + i));
			mL0730.setE01IFMAMT(req.getParameter("VLRCTA" + i));

			mL0730.send();
			mL0730.destroy();
		  flexLog("EDP073001 Message Sent");
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
							+ "EDP0715_ListAccess_maint.jsp");
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

	EDP073101Message mL0731 = null;
	String myOptionL = "";
	String myFlag = "";
	JBList beanList1 = null;
	JBList beanListN = null;
	JBList beanListJ = null;
	try {
		beanList1 = new datapro.eibs.beans.JBList();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		beanListN = new datapro.eibs.beans.JBList();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		beanListJ = new datapro.eibs.beans.JBList();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

// inicio traer lista de formatos
   // Send Initial data for formats
   try {
	   flexLog("Send Initial Data");
	   mL0731 = (EDP073101Message) mc.getMessageRecord("EDP073101");
	   mL0731.setH01USERID(user.getH01USR());
	   mL0731.setH01PROGRM("EDP0731");
	   mL0731.setH01TIMSYS(getTimeStamp());
	   mL0731.setH01OPECOD("0015");
	   mL0731.send();
	   mL0731.destroy();
   } catch (Exception e) {
	   e.printStackTrace();
	   flexLog("Error: " + e);
	   throw new RuntimeException("Socket Communication Error");
   }

   // Receive Error Message
   try {
	   newmessage = mc.receiveMessage();

//	   myOptionL = "<option value=\"\"></option>";
	   beanList1.addRow(myFlag, myOptionL);

	   if (newmessage.getFormatName().equals("EDP073101")) {

		   String select = "";
		   String marker = "";

			myOptionL =
			"<option value=\""
				+ "\">"
				+ " Seleccione Formato: "
				+ "</option>";
			beanList1.addRow(myFlag, myOptionL);
			beanListJ.addRow(myFlag, myOptionL);
			beanListN.addRow(myFlag, myOptionL);

		   while (true) {

			   mL0731 = (EDP073101Message) newmessage;

			   marker = mL0731.getH01FLGMAS();

				if(userPO.getHeader3()!=null){
					if (mL0731.getE01DPFFMT().trim().equals(userPO.getHeader3().trim())){
						select = "selected";
					}else{
					 	select = "";
					}
				}

			   if (marker.equals("*")) {
				   break;
			   } else {
				   myOptionL =
					   "<option value=\""
						   + mL0731.getE01DPFFMT()
						   + "\" "
						   + select
						   + " >"
						   + mL0731.getE01DPFFMT()
						   + " - "
						   + mL0731.getE01DPFDSC()
						   + "</option>";
					   beanList1.addRow(myFlag, myOptionL);
				   if (mL0731.getH01FLGWK1().equals("2")) {
					   beanListJ.addRow(myFlag, myOptionL);
				   } else {
					   beanListN.addRow(myFlag, myOptionL);
				   }
			   }
			   newmessage = mc.receiveMessage();
		   }

	   } else
		   flexLog("Message " + newmessage.getFormatName() + " received.");
   } catch (Exception ex) {
	   flexLog("Error: " + ex);
   }

// fin traer lista de formatos


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
			if(req.getParameter("E01IFMCUN")!=null){
				userPO.setHeader1(req.getParameter("E01IFMCUN"));
			}
			try {
				mL0730.setE01IFMCUN(req.getParameter("E01IFMCUN"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMFEY(req.getParameter("E01IFMFEY"));
			} catch (Exception e) {
				mL0730.setE01IFMFEY("0");
			}
			try {
				mL0730.setE01IFMFEM(req.getParameter("E01IFMFEM"));
			} catch (Exception e) {
				mL0730.setE01IFMFEM("0");
			}
			try {
				mL0730.setE01IFMFED(req.getParameter("E01IFMFED"));
			} catch (Exception e) {
				mL0730.setE01IFMFED("0");
			}
			try {
				mL0730.setE01IFMCFO(req.getParameter("E01IFMCFO"));
			} catch (Exception e) {
				mL0730.setE01IFMCFO("");
			}
			try {
				mL0730.setE01IFMCFA(req.getParameter("E01IFMCFA"));
			} catch (Exception e) {
				mL0730.setE01IFMCFA("");
			}
			if(req.getParameter("action")!=null){
				mL0730.setH01FLGWK3(req.getParameter("action"));
				if(req.getParameter("action").equals("1")){
					mL0730.setH01FLGWK3("5");
				}
			}
			// Purpose. NEW
			if(req.getParameter("opt").equals("1")){
				userPO.setOption("1");
			}
			// Purpose. INQUIRY
			if(req.getParameter("opt").equals("4")){
				userPO.setOption("4");
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
//				String myFlag = "";
		        myFlag = "";
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

				try {
					userPO.setHeader20(req.getParameter("TITULO"));
				} catch (Exception e) {
					userPO.setHeader20("");
				}


				flexLog("Putting java beans into the session");
				ses.setAttribute("optList", optList);
// el mismo arreglo lo copio para el siguiente formato
				ses.setAttribute("grpList", grpList);
				ses.setAttribute("accList", grpAccList);
				ses.setAttribute("memList", grpMemList);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("mL0730", mL0730);
				ses.setAttribute("optListJ", beanListJ);
				ses.setAttribute("optListN", beanListN);
				ses.setAttribute("optListF", beanList1);

				if(req.getParameter("RETORNO")!=null){
					userPO.setHeader11(req.getParameter("RETORNO"));
				}
				// opt=4 consulta
//				if(req.getParameter("opt")!=null){
//					userPO.setOption(req.getParameter("opt"));
//				}
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

	protected void procList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;

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

		EDP073101Message mL0731 = null;
 
		String myOption = "";
		String myFlag = "";
		JBList beanList1 = null;
		JBList beanListN = null;
		JBList beanListJ = null;

		try {
			beanList1 = new datapro.eibs.beans.JBList();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			beanListJ = new datapro.eibs.beans.JBList();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			beanListN = new datapro.eibs.beans.JBList();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
			mL0731 = new datapro.eibs.beans.EDP073101Message();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			userPO.setHeader1(req.getParameter("E01IFMCUN"));
		} catch (Exception e) {
			userPO.setHeader1("");
		}
		try {
			userPO.setHeader2(req.getParameter("E01IFMNA1"));
		} catch (Exception e) {
			userPO.setHeader2("");
		}
		try {
			userPO.setHeader3(req.getParameter("E01IFMCFO"));
		} catch (Exception e) {
			userPO.setHeader3("");
		}
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
			userPO.setHeader8(req.getParameter("E01IFMFED"));
		} catch (Exception e) {
			userPO.setHeader8("");
		}

		// Send Initial data for formats
		try {
			flexLog("Send Initial Data");
			mL0731 = (EDP073101Message) mc.getMessageRecord("EDP073101");
			mL0731.setH01USERID(user.getH01USR());
			mL0731.setH01PROGRM("EDP0731");
			mL0731.setH01TIMSYS(getTimeStamp());
			mL0731.setH01OPECOD("0015");
			mL0731.send();
			mL0731.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP073101")) {

				String marker = "";

				myOption =
				"<option value=\""
					+ "\">"
					+ " Seleccione Formato "
					+ "</option>";
				beanList1.addRow(myFlag, myOption);
				beanListJ.addRow(myFlag, myOption);
				beanListN.addRow(myFlag, myOption);

				while (true) {

					mL0731 = (EDP073101Message) newmessage;

					marker = mL0731.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						myOption =
							"<option value=\""
								+ mL0731.getE01DPFFMT()
								+ "\">"
								+ mL0731.getE01DPFFMT()
								+ " - "
								+ mL0731.getE01DPFDSC()
								+ "</option>";
							beanList1.addRow(myFlag, myOption);
						if (mL0731.getH01FLGWK1().equals("2")) {
							beanListJ.addRow(myFlag, myOption);
						} else {
							beanListN.addRow(myFlag, myOption);
						}
					}
					newmessage = mc.receiveMessage();
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		ses.setAttribute("optList",  beanList1);
		ses.setAttribute("optListF",  beanList1);
		ses.setAttribute("optListJ", beanListJ);
		ses.setAttribute("optListN", beanListN);

		EDP073001Message l07301 = null;
		// Send Initial data
		try {
			l07301 = (EDP073001Message) mc.getMessageRecord("EDP073001");
			l07301.setH01USERID(user.getH01USR());
			l07301.setH01PROGRM("EDP073001");
			l07301.setH01TIMSYS(getTimeStamp());
			l07301.setH01SCRCOD("01");
			l07301.setH01OPECOD("0010");
			try {
				try {
					l07301.setE01IFMCUN(req.getParameter("E01IFMCUN"));
				} catch (Exception e) {
				}
				try {
					l07301.setE01IFMFEY(req.getParameter("E01IFMFEY"));
				} catch (Exception e) {
				}
				try {
					l07301.setE01IFMFEM(req.getParameter("E01IFMFEM"));
				} catch (Exception e) {
				}
				try {
					l07301.setE01IFMFED(req.getParameter("E01IFMFED"));
				} catch (Exception e) {
				}
				try {
					l07301.setE01IFMCFO(req.getParameter("E01IFMCFO"));
				} catch (Exception e) {
				}
//				try {
//					l07301.setE01IFMCFA(req.getParameter("E01IFMCFA"));
//				} catch (Exception e) {
//				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}
			try {
				userPO.setHeader1(req.getParameter("E01IFMCUN"));
			} catch (Exception e) {
				userPO.setHeader1("");
			}
			try {
				userPO.setHeader2(req.getParameter("E01IFMNA1"));
			} catch (Exception e) {
				userPO.setHeader2("");
			}
			try {
				userPO.setHeader3(req.getParameter("E01IFMCFO"));
			} catch (Exception e) {
				userPO.setHeader3("");
			}
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
//			try {
//				userPO.setHeader8(req.getParameter("E01IFMFED"));
//			} catch (Exception e) {
//				userPO.setHeader8("");
//			}
			try {
				userPO.setHeader20(req.getParameter("TITULO"));
			} catch (Exception e) {
				userPO.setHeader20("");
			}


			if(req.getParameter("Pos")!=null){
				l07301.setE01RECPOS(req.getParameter("Pos"));
				}
			if(req.getParameter("opt")!=null){
				l07301.setH01FLGWK3(req.getParameter("opt"));
				}
			if(req.getParameter("OPT")!=null){
			if(req.getParameter("OPT").equals("1")){
				userPO.setOption(" ");
				}else{
				userPO.setOption("5");
				}
			}
			l07301.send();
			l07301.destroy();
			flexLog("EDP073001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP073001")) {

				JBObjList beanList = new JBObjList();
				JBObjList beanListD = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String TableTyp = "";
				//var for ofac status
				//var for Warning status
				String chkOfac = "";
				String chkWarn = "";
				int compar = 0;
				int indexRow = 0;
				int numrec = 0;
				while (true) {

					l07301 = (EDP073001Message) newmessage;

					marker = l07301.getH01FLGMAS();

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

						beanList.addRow(l07301);

						if (marker.equals("+")) {
						   beanList.setShowNext(true);
						   beanList.setLastRec(
						   Integer.parseInt(l07301.getE01RECPOS()));

							break;
						}

						numrec = Integer.parseInt(l07301.getE01RECPOS());
						
						if (numrec >= 20) {
							beanList.setShowPrev(true);
						}

					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP073001Help", beanList);
				ses.setAttribute("userPO", userPO);


				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0730_client_list.jsp");
							callPage(
								LangPath + "EDP0730_client_list.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0730_client_list.jsp");

							callPage(
								LangPath + "EDP0730_client_list.jsp",
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


	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMemo(
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
	JBListRec optListF = null;
	JBListRec grpList = null;
	JBListRec grpAccList = null;
	JBListRec grpMemList = null;
	UserPos userPO = null;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		//para llevar todos los campos de la clase a la pantalla
		mL0730 = (EDP073001Message) ses.getAttribute("mL0730");
		//
		mL0730 = (EDP073001Message) mc.getMessageRecord("EDP073001");
		mL0730.setH01USERID(user.getH01USR());
		mL0730.setH01PROGRM("EDP0730");
		mL0730.setH01TIMSYS(getTimeStamp());
		mL0730.setH01SCRCOD("01");
		mL0730.setH01OPECOD("0004");
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
			try {
				mL0730.setE01IFMFED(req.getParameter("E01IFMFED"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMCFO(req.getParameter("E01IFMCFO"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMCFA(req.getParameter("E01IFMCFA"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01CNORCD(req.getParameter("E01CNORCD"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMGLN(req.getParameter("E01IFMGLN"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMDSC(req.getParameter("E01IFMDSC"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01DPLSEC(req.getParameter("MEMO"));
			} catch (Exception e) {
			}

			try {
				userPO.setIdentifier(req.getParameter("ROW"));
			} catch (Exception e) {
				userPO.setIdentifier("0");
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

		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("mL0730", mL0730);

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				//IsNotError = msgError.getERRNUM().equals("0");
				//flexLog("IsNotError = " + IsNotError);
				//showERROR(msgError);
				userPO.setHeader20(req.getParameter("TITULO"));

				try {
					
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0730_client_financial_enter.jsp");
					callPage(
						LangPath + "EDP0730_client_financial_enter.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDP073001")) {
				String marker = "";

				while (true) {
					mL0730 = (EDP073001Message) newmessage;
					marker = mL0730.getH01FLGMAS();
					userPO = new datapro.eibs.beans.UserPos();
					if (marker.equals("*")) {
						break;
					} else {
						// LEE DATOS DE LA CABECERA DE LA PANTALLA
						userPO.setHeader1(mL0730.getE01IFMCUN());
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
						//Account Group
					}
					newmessage = mc.receiveMessage();
				}


				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0730_client_financial_memo.jsp");
					callPage(
						LangPath + "EDP0730_client_financial_memo.jsp",
						req,
						res);
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
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procActionUpdMemo(
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
		String flagAccept = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		opeCode = "0005";


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
			try {
				mL0730.setE01IFMFED(req.getParameter("E01IFMFED"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMCFO(req.getParameter("E01IFMCFO"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMCFA(req.getParameter("E01IFMCFA"));
			} catch (Exception e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}



//		userPO.setHeader8(req.getParameter("E01IFMCFA"));

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			mL0730 = (EDP073001Message) mc.getMessageRecord("EDP073001");
			mL0730.setH01USERID(user.getH01USR());
			mL0730.setH01PROGRM("EDP0730");
			mL0730.setH01TIMSYS(getTimeStamp());
			mL0730.setH01OPECOD(opeCode);
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
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procRUPREV(
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
		String flagAccept = "";

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		opeCode = "0006";


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
			try {
				mL0730.setE01IFMFED(req.getParameter("E01IFMFED"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMCFO(req.getParameter("E01IFMCFO"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMCFA(req.getParameter("E01IFMCFA"));
			} catch (Exception e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}



//		userPO.setHeader8(req.getParameter("E01IFMCFA"));

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			mL0730 = (EDP073001Message) mc.getMessageRecord("EDP073001");
			mL0730.setH01USERID(user.getH01USR());
			mL0730.setH01PROGRM("EDP0730");
			mL0730.setH01TIMSYS(getTimeStamp());
			mL0730.setH01OPECOD(opeCode);
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
	 * This method was created in VisualAge. INF. FINANCIERA
	 */
	protected void procReqIdxFinancial(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP073201Message msgSearch = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");




		try {
			flexLog("Send Initial Data");
			msgSearch = (EDP073201Message) mc.getMessageRecord("EDP073201");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDP0732");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0010");
			try {
				try {
					msgSearch.setE01IFDCUN(userPO.getHeader1());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE01IFDCFO(userPO.getHeader3());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE01IFDFEY(userPO.getHeader6());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE01IFDFEM(userPO.getHeader7());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE01IFMCFA(userPO.getHeader8());
				} catch (Exception e) {
				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Input data error " + e);
			}

			msgSearch.send();
			msgSearch.destroy();

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
				// showERROR(msgError);
				flexLog("ELEERRMessage: " + msgError.toString());

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

			if (newmessage.getFormatName().equals("EDP073201")) {

				msgSearch = (EDP073201Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("idxFnl", msgSearch);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0730_client_financial_idx.jsp");
						callPage(
							LangPath + "EDP0730_client_financial_idx.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
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
	protected void procReqEnter(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	MessageRecord newmessageP4 = null;
	ELEERRMessage msgError = null;
	EDP073101Message mL0730 = null;
	EDP073001Message msgFnl = null;
	JBList beanList = null;
	JBList beanListN = null;
	JBList beanListJ = null;
	String myOption = "";
	String myFlag = "";
	UserPos userPO = null;


	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	userPO.setOption(" ");
	userPO.setCusNum(" ");
	userPO.setHeader3(" ");

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		beanList = new datapro.eibs.beans.JBList();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		beanListJ = new datapro.eibs.beans.JBList();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		beanListN = new datapro.eibs.beans.JBList();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		msgFnl = new datapro.eibs.beans.EDP073001Message();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		mL0730 = new datapro.eibs.beans.EDP073101Message();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	

	if (req.getParameter("RETORNO") != null) {
		userPO.setHeader11(req.getParameter("RETORNO"));
	}

//	if (req.getParameter("CUSTOMER") != null) {
//		userPO.setCusNum(req.getParameter("CUSTOMER"));
//	}

	if(req.getParameter("OPT")!= null && req.getParameter("OPT").equals("1")){
		userPO.setOption(" ");
		}else{
		userPO.setOption("5");
		}


	// Send Initial data for formats
	try {
		flexLog("Send Initial Data");
		mL0730 = (EDP073101Message) mc.getMessageRecord("EDP073101");
		mL0730.setH01USERID(user.getH01USR());
		mL0730.setH01PROGRM("EDP0731");
		mL0730.setH01TIMSYS(getTimeStamp());
		mL0730.setH01OPECOD("0015");
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

		if (newmessage.getFormatName().equals("EDP073101")) {

			String select = "";
			String marker = "";
			myOption =
			"<option value=\""
				+ "\">"
				+ " Seleccione Formato: "
				+ "</option>";
			beanList.addRow(myFlag, myOption);
			beanListJ.addRow(myFlag, myOption);
			beanListN.addRow(myFlag, myOption);

			while (true) {

				mL0730 = (EDP073101Message) newmessage;

				marker = mL0730.getH01FLGMAS();

				if (marker.equals("*")) {
					break;
				} else {

				   if (mL0730.getE01DPFFMT().trim().equals(userPO.getHeader3().trim())){
						select = "selected";
				   }else{
						select = "";
				   }

					myOption =
						"<option value=\""
							+ mL0730.getE01DPFFMT()
							+ "\">"
							+ mL0730.getE01DPFFMT()
							+ " - "
							+ mL0730.getE01DPFDSC()
							+ "</option>";
						beanList.addRow(myFlag, myOption);
					if (mL0730.getH01FLGWK1().equals("2")) {
						beanListJ.addRow(myFlag, myOption);
					} else {
						beanListN.addRow(myFlag, myOption);
					}
				}
				newmessage = mc.receiveMessage();
			}

		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

		flexLog("Putting java beans into the session");
		ses.setAttribute("ses0730", msgFnl);
		ses.setAttribute("optList",  beanList);
		ses.setAttribute("optListF", beanList);
		ses.setAttribute("optListJ", beanListJ);
		ses.setAttribute("optListN", beanListN);
		ses.setAttribute("error", msgError);


	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}



// Carga dropdownlist para la consulta de la tabla

	JBList beanLP4 = null;
	String myFlagP4 = "";
	String myOptionP4 = "";
	EDP073601Message msgCnofc = null;

	try {
		beanLP4 = new datapro.eibs.beans.JBList();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	try {
		msgCnofc = new datapro.eibs.beans.EDP073601Message();
	} catch (Exception ex) {
	flexLog("Error: " + ex);
	}

	// Send Initial data for CNOFC tables. Audit Firm P4
	try {
		flexLog("Send Initial Data");
		msgCnofc = (EDP073601Message) mc.getMessageRecord("EDP073601");
		msgCnofc.setH01USERID(user.getH01USR());
		msgCnofc.setH01PROGRM("EDP0736");
		msgCnofc.setH01TIMSYS(getTimeStamp());
		msgCnofc.setH01OPECOD("0015");
		msgCnofc.setE01CNOCFL("P4");
		msgCnofc.send();
		msgCnofc.destroy();
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
		newmessage = mc.receiveMessage();
//habilitar cuando la primera seleccion esta en blanco
//		myOptionP4 = "<option value=\"\"></option>";
//		beanLP4.addRow(myFlag, myOptionP4);

		if (newmessage.getFormatName().equals("EDP073601")) {

			String marker = "";
			while (true) {

				msgCnofc = (EDP073601Message) newmessage;

				marker = msgCnofc.getH01FLGMAS();

				if (marker.equals("*")) {
					break;
				} else {
					myOptionP4 =
						"<option value=\""
							+ msgCnofc.getE01CNORCD()
							+ "\">"
							+ msgCnofc.getE01CNORCD()
							+ " - "
							+ msgCnofc.getE01CNODSC()
							+ "</option>";
					beanLP4.addRow(myFlagP4, myOptionP4);
				}
				newmessage = mc.receiveMessage();
			}

		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

		// end read table

		flexLog("Putting java beans into the session");
		ses.setAttribute("ses0730", msgFnl);
		ses.setAttribute("optList", beanList);
		ses.setAttribute("error", msgError);
		ses.setAttribute("cnofcP4", beanLP4);

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0730_client_financial_enter.jsp");
			callPage(
				LangPath + "EDP0730_client_financial_enter.jsp",
				req,
				res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	finally
	{
//	 s.close();
	}



}


// PANTALLA INDICES FINANCIEROS
   protected void procReqInd(
	   MessageContext mc,
	   ESS0030DSMessage user,
	   HttpServletRequest req,
	   HttpServletResponse res,
	   HttpSession ses)
	   throws ServletException, IOException {

	   MessageRecord newmessage = null;
	   EDP073301Message mL0730 = null;
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
		   mL0730 = (EDP073301Message) mc.getMessageRecord("EDP073301");
		   mL0730.setH01USERID(user.getH01USR());
		   mL0730.setH01PROGRM("EDP073301");
		   mL0730.setH01TIMSYS(getTimeStamp());
		   mL0730.setH01SCRCOD("01");
		   mL0730.setH01OPECOD("0015");
		   mL0730.setE01DPIIND(userPO.getHeader9());
		   mL0730.setE01DPIDSC(userPO.getHeader10());
		try {
			try {
				mL0730.setE01IFMCUN(userPO.getHeader1());
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMCFO(userPO.getHeader3());
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMFEY(userPO.getHeader6());
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMFEM(userPO.getHeader7());
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMFED(req.getParameter("E01IFMFED"));
			} catch (Exception e) {
			}
			try {
				mL0730.setE01IFMCFA(userPO.getHeader8());
			} catch (Exception e) {
			}
//			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}

		mL0730.send();
		mL0730.destroy();
		flexLog("EDP073301 Message Sent");



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

		   if (newmessage.getFormatName().equals("EDP073301")) {

			   JBObjList beanList = new JBObjList();

			   boolean firstTime = true;
			   String marker = "";
			   String myFlag = "";
			   StringBuffer myRow = null;
			   String chk = "";
				String val = "";
			   String TableTyp = "";
			   //var for ofac status
			   //var for Warning status
			   String chkOfac = "";
			   String chkWarn = "";
			   int compar = 0;
			   int indexRow = 0;
				int varfin = 0;
			   while (true) {

				   mL0730 = (EDP073301Message) newmessage;

				   marker = mL0730.getE01OPECDE();

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

	
					   beanList.addRow(mL0730);

					   if (marker.equals("+")) {
						   beanList.setShowNext(true);

						   break;
					   }
				   }
				   newmessage = mc.receiveMessage();
			   }

			   flexLog("Putting java beans into the session");
			   ses.setAttribute("EDP073301Help", beanList);
			   ses.setAttribute("userPO", userPO);

			   try {

					   if (msgError.getERRNUM().equals("0")) {
						   flexLog(
							   "About to call Page: "
								   + LangPath
								   + "EDP0730_client_financial_ind.jsp");
						   callPage(
							   LangPath + "EDP0730_client_financial_ind.jsp",
							   req,
							   res);
					   } else {
						   flexLog(
							   "About to call Page: "
								   + LangPath
								   + "EDP0730_client_financial_ind.jsp");

						   callPage(
							   LangPath + "EDP0730_client_financial_ind.jsp",
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

// FIN PANTALLA INDICES FINANCIEROS

// PANTALLA INDICES FINANCIEROS COMPARATIVO
   protected void procReqIndComp(
	   MessageContext mc,
	   ESS0030DSMessage user,
	   HttpServletRequest req,
	   HttpServletResponse res,
	   HttpSession ses)
	   throws ServletException, IOException {

	   MessageRecord newmessage = null;
	   EDP073401Message mL0734 = null;
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
		   mL0734 = (EDP073401Message) mc.getMessageRecord("EDP073401");
		   mL0734.setH01USERID(user.getH01USR());
		   mL0734.setH01PROGRM("EDP073401");
		   mL0734.setH01TIMSYS(getTimeStamp());
		   mL0734.setH01SCRCOD("01");
		   mL0734.setH01OPECOD("0015");
		   mL0734.setE01DPIIND(userPO.getHeader9());
		   mL0734.setE01DPIDSC(userPO.getHeader10());
		try {
			try {
				mL0734.setE01IFMCUN(userPO.getHeader1());
			} catch (Exception e) {
			}
			try {
				mL0734.setE01IFMCFO(userPO.getHeader3());
			} catch (Exception e) {
			}
			try {
				mL0734.setE01IFMFEY(userPO.getHeader6());
			} catch (Exception e) {
			}
			try {
				mL0734.setE01IFMFEM(userPO.getHeader7());
			} catch (Exception e) {
			}
			try {
				mL0734.setE01IFMCFA(userPO.getHeader8());
			} catch (Exception e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Input data error " + e);
		}

		mL0734.send();
		mL0734.destroy();
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
			   //var for ofac status
			   //var for Warning status
			   String chkOfac = "";
			   String chkWarn = "";
			   int compar = 0;
			   int indexRow = 0;
				int varfin = 0;
			   while (true) {

				   mL0734 = (EDP073401Message) newmessage;

				   marker = mL0734.getE01OPECDE();

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

	
					   beanList.addRow(mL0734);

					   if (marker.equals("+")) {
						   beanList.setShowNext(true);

						   break;
					   }
				   }
				   newmessage = mc.receiveMessage();
			   }

			   flexLog("Putting java beans into the session");
			   ses.setAttribute("EDP073401Help", beanList);
			   ses.setAttribute("userPO", userPO);
				ses.setAttribute("mL0734", mL0734);

			   try {

					   if (msgError.getERRNUM().equals("0")) {
						   flexLog(
							   "About to call Page: "
								   + LangPath
								   + "EDP0730_client_financial_ind_comp.jsp");
						   callPage(
							   LangPath + "EDP0730_client_financial_ind_comp.jsp",
							   req,
							   res);
					   } else {
						   flexLog(
							   "About to call Page: "
								   + LangPath
								   + "EDP0730_client_financial_ind_comp.jsp");

						   callPage(
							   LangPath + "EDP0730_client_financial_ind_comp.jsp",
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

// FIN PANTALLA INDICES FINANCIEROS
   protected void procRLISTANALYSIS(
	   MessageContext mc,
	   ESS0030DSMessage user,
	   HttpServletRequest req,
	   HttpServletResponse res,
	   HttpSession ses)
	   throws ServletException, IOException {

	   MessageRecord newmessage = null;
	   EDP072901Message msgL0729 = null;
	   ELEERRMessage msgError = null;
	   UserPos userPO = null;
	   boolean IsNotError = false;

	   userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	   userPO.setHeader8(req.getParameter("E01IFMFED"));
					
	   // Send Initial data
	   try {
		   msgL0729 = (EDP072901Message) mc.getMessageRecord("EDP072901");
		   msgL0729.setH01USERID(user.getH01USR());
		   msgL0729.setH01PROGRM("EDP0729");
		   msgL0729.setH01TIMSYS(getTimeStamp());
		   msgL0729.setH01SCRCOD("01");
		   msgL0729.setH01OPECOD("0015");
		   msgL0729.setE01CAFCUN(userPO.getHeader1());
			msgL0729.setE01CAFFEY(userPO.getHeader6());
			msgL0729.setE01CAFFEM(userPO.getHeader7());
			msgL0729.setE01CAFFED(userPO.getHeader8());
		   msgL0729.send();
		   msgL0729.destroy();
		   flexLog("EDP072901 Message Sent");
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
			   showERROR(msgError);

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

		   if (newmessage.getFormatName().equals("EDP072901")) {

			   JBObjList beanList = new JBObjList();

			   boolean firstTime = true;
			   String marker = "";

			  beanList.setNoResult(true);
			   int ct = 0;
			  while (true) {

				   msgL0729 = (EDP072901Message) newmessage;

				   marker = msgL0729.getE01OPECDE();

				   if (firstTime) {
					   firstTime = false;
				   } else {
					  beanList.setNoResult(false);
				   }

				   if (marker.equals("*")) {
					   beanList.setShowNext(false);
					   break;
				   } else {
					   beanList.addRow(msgL0729);

					   if (marker.equals("+")) {
						   beanList.setShowNext(true);

						   break;
					   }
				   }
				   newmessage = mc.receiveMessage();
			   }

			   flexLog("Putting java beans into the session");
			   ses.setAttribute("msgL0729", msgL0729);
			   ses.setAttribute("userPO", userPO);

			   try {

					   if (msgError.getERRNUM().equals("0")) {
						   flexLog(
							   "About to call Page: "
								   + LangPath
								   + "EDP0730_client_financial_analysis.jsp");
						   callPage(
							   LangPath + "EDP0730_client_financial_analysis.jsp",
							   req,
							   res);
					   } else {
						   flexLog(
							   "About to call Page: "
								   + LangPath
								   + "EDP0730_client_financial.jsp");

						   callPage(
							   LangPath + "EDP0730_client_financial.jsp",
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


   protected void procRACTDANALYSIS(
	   MessageContext mc,
	   ESS0030DSMessage user,
	   HttpServletRequest req,
	   HttpServletResponse res,
	   HttpSession ses) {

	   JBListRec beanList = null;
	   MessageRecord newmessage = null;
	   EDP072901Message msgL0729 = null;
	   ELEERRMessage msgError = null;
	   UserPos userPO = null;
	   userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	   int inptOPT = 0;

	   inptOPT = Integer.parseInt(req.getParameter("opt"));
	   if (inptOPT != 2) {

	   try {
			
		   msgL0729 = (EDP072901Message) mc.getMessageRecord("EDP072901");
		 msgL0729.setH01USERID(user.getH01USR());
		 msgL0729.setH01PROGRM("EDP0729");
		 msgL0729.setH01TIMSYS(getTimeStamp());
		 msgL0729.setH01SCRCOD("01");
		 msgL0729.setH01OPECOD("0005");
		msgL0729.setE01CAFCUN(userPO.getHeader1());
		 msgL0729.setE01CAFFEY(req.getParameter("E01IFMFEY"));
		 msgL0729.setE01CAFFEM(req.getParameter("E01IFMFEM"));
		 msgL0729.setE01CAFFED(req.getParameter("E01IFMFED"));
		 msgL0729.setE01CAFM21(req.getParameter("E01CAFM21"));
		 msgL0729.setE01CAFM22(req.getParameter("E01CAFM22"));
		 msgL0729.setE01CAFM23(req.getParameter("E01CAFM23"));
		 msgL0729.setE01CAFM24(req.getParameter("E01CAFM24"));
		msgL0729.setE01CAFM25(req.getParameter("E01CAFM25"));
		 msgL0729.send();
		 msgL0729.destroy();
		  
		 flexLog("EDP072901 Message Sent");
	   } catch (Exception e) {
		   e.printStackTrace();
		   flexLog("Error: " + e);
		   throw new RuntimeException("Socket Communication Error");
	   }
	   // Receive Error
	   try {
		   newmessage = mc.receiveMessage();

		   if (newmessage.getFormatName().equals("ELEERR")) {
//			   ses.setAttribute("userPO", userPO);


			   msgError = (ELEERRMessage) newmessage;
			   boolean IsNotError = msgError.getERRNUM().equals("0");
			   if (IsNotError) {
//				   procReqProductsList(mc, user, req, res, ses);

//				   JBList beanLP4 = getNextActivityJBList(msgRT);
//				   ses.setAttribute("optLP4", beanLP4);

				   try {
					return;

				   } catch (Exception e) {
					   flexLog("Exception calling page " + e);
					   e.printStackTrace();

				   }

			   } else {
				   msgError = (ELEERRMessage) newmessage;
				   showERROR(msgError);
				   flexLog("Putting java beans into the session");
				   ses.setAttribute("error", msgError);
//				   ses.setAttribute("chkList", beanList);
				   flexLog(
					   "About to call Page: "
						   + LangPath
						   + "EDP0730_client_financial_analysis.jsp");
				   callPage(
					   LangPath + "EDP0730_client_financial_analysis.jsp",
					   req,
					   res);
			   }
		   }
		   } catch (Exception e) {
			   e.printStackTrace();
			   flexLog("Error: " + e);
			   throw new RuntimeException("Socket Communication Error");
		   }
	   } else { 
		try {
			return;

		} catch (Exception e) {
			flexLog("Exception calling page " + e);
			e.printStackTrace();

		}


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

			int screen = R_ENTER;
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
						// Requests
						case R_ENTER :
							procReqEnter(mc, msgUser, req, res, session);
							break;
						case R_IDX_FINANCIAL :
							procReqIdxFinancial(mc, msgUser, req, res, session);
							break;
							// Actions
						case A_ENTER :
//							procActionEnter(mc, msgUser, req, res, session);
							procList(mc, msgUser, req, res, session);
							break;
						case R_DET :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						case A_FINANCIAL :
							procActionFinancial(mc, msgUser, req, res, session);
							break;
						case R_MEMO :
							procActionMemo(mc, msgUser, req, res, session);
							break;
						case R_UPD_MEMO :
							procActionUpdMemo(mc, msgUser, req, res, session);
							break;
						case RUPDVENC :
							procRUPREV(mc, msgUser, req, res, session);
							break;
						case R_IDX :
							procReqInd(mc, msgUser, req, res, session);
							break;
						case R_IDX_COMP :
							procReqIndComp(mc, msgUser, req, res, session);
							break;
						case UpdAcc :
							procUpdAcc(mc, msgUser, req, res, session);
							break;
						case RLISTANALYSIS :
							procRLISTANALYSIS(mc, msgUser, req, res, session);
							break;
						case RACTDANALYSIS :
							procRACTDANALYSIS(mc, msgUser, req, res, session);
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