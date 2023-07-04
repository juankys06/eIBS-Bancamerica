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

public class JSEDP0725 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final int R_BRANCH_LIST = 100;
	protected static final int R_BRANCH_LIST_GAR = 200;
	
	protected static final int R_GAR_NEW    = 210;
	protected static final int R_GAR_MAINT  = 220;
	protected static final int RGARDEL      = 230;
	protected static final int A_GAR_MAINT  = 280;

	protected static final int RGARTENEW   = 310;
	protected static final int RGARTEMAINT = 320;
	protected static final int RGARTEDEL   = 330;
	protected static final int AGARTEMAINT = 380;

	protected static final int RTITNEW     = 410;
	protected static final int RTITMAINT   = 420;
	protected static final int RTITDEL     = 430;
	protected static final int RFMTGUAR    = 440;
	protected static final int SAVTIT      = 450;

	protected static final int RGARIBSCOPY = 510;

	protected static final int A_POSITION  = 800;

	protected static final int R_ENTER = 1;
	protected static final int R_NEW = 300;


	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0725() {
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

	protected void procReqEnterBranch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0725_changeprod_enter.jsp");
				callPage(LangPath + "EDP0725_changeprod_enter.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		}


	protected void procReqBranchList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072501Message l07251 = null;
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

		// Send Initial data
		try {
			l07251 = (EDP072501Message) mc.getMessageRecord("EDP072501");
			l07251.setH01USERID(user.getH01USR());
			l07251.setH01PROGRM("EDP072501");
			l07251.setH01TIMSYS(getTimeStamp());
			l07251.setH01SCRCOD("01");
			l07251.setH01OPECOD("0015");

			if(req.getParameter("Pos")!=null){
				l07251.setE01DPGLNU(req.getParameter("Pos"));
				}

			l07251.send();
			l07251.destroy();
			flexLog("EDP072501 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP072501")) {

				JBObjList beanList = new JBObjList();
				JBObjList beanListD = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
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

					l07251 = (EDP072501Message) newmessage;

					marker = l07251.getE01OPECDE();

					if (firstTime) {
						firstTime = false;
						chk = "checked";
						beanList.setFirstRec(
						Integer.parseInt(l07251.getE01DPGLNU()));

					} else {
						chk = "";
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						beanList.addRow(l07251);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							beanList.setLastRec(
							Integer.parseInt(l07251.getE01DPGLNU()));
							break;
						}
						numrec = Integer.parseInt(l07251.getE01DPGLNU());
						if (numrec == 21) {
							beanList.setShowPrev(true);
						}

					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP072501Help", beanList);
				ses.setAttribute("userPO", userPO);


				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_branch_list.jsp");
							callPage(
								LangPath + "EDP0725_branch_list.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_branch_list.jsp");

							callPage(
								LangPath + "EDP0725_branch_list.jsp",
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


	protected void procReqBranchGar(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072501Message l07251 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBListRec beanList = null;
		JBListRec gcodeList = null;	
		JBListRec gcodeListR = null;	
		
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
		if (req.getParameter("NPR") != null) {
			userPO.setHeader4(req.getParameter("NPR"));
		}
		if (req.getParameter("CUN") != null) {
			userPO.setCusNum(req.getParameter("CUN"));
		}
		if (req.getParameter("NAM") != null) {
			userPO.setCusName(req.getParameter("NAM"));
		}
//		if (req.getParameter("EST") != null) {
//			userPO.setType(req.getParameter("EST"));
//		}

		if (req.getParameter("opt") != null) {
			userPO.setHeader16(req.getParameter("opt"));
		}

		// Send Initial data. LISTA DE GARANTIAS
		try {
			l07251 = (EDP072501Message) mc.getMessageRecord("EDP072501");
			l07251.setH01USERID(user.getH01USR());
			l07251.setH01PROGRM("EDP072501");
			l07251.setH01TIMSYS(getTimeStamp());
			l07251.setH01SCRCOD("01");
			l07251.setH01OPECOD("0016");
			l07251.setE01DPCNPR(userPO.getHeader4());
			l07251.setE01DPCCUN(userPO.getCusNum());
			l07251.send();
			l07251.destroy();
			flexLog("EDP072501 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP072501")) {

				JBObjList beanListM = new JBObjList();
				JBObjList beanListD = new JBObjList();
				JBObjList beanListR = new JBObjList();


				int colNum = 10;
				int gaColNum = 6;
						try {
							beanList = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
							beanList.init(colNum);
							gcodeList = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
							gcodeList.init(gaColNum);
							gcodeListR = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
							gcodeListR.init(gaColNum);

						} 
						catch (Exception ex) {
							flexLog("Error: " + ex); 
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						String myFlag1 = "";
						String myGaCode[] = new String[gaColNum];
						for (int i=0; i<gaColNum; i++) {
							myGaCode[i] = "";
						}
						String myRow[] = new String[colNum];
						for (int i=0; i<colNum; i++) {
							myRow[i] = "";
						}
			
						while (true) {

							l07251 = (EDP072501Message)newmessage;

							marker = l07251.getE01OPECDE();

							if (marker.equals("*")) {
							beanListM.setShowNext(false);
							beanListD.setShowNext(false);
						
								break;
							}
							else {

								// REGISTROS DE GARANTIA PROPUESTA //
								 if (l07251.getH01FLGWK2().equals("")) { 
								 myGaCode[0] = l07251.getE01DPCTYP(); 
								 myGaCode[1] = l07251.getE01DPCIDN(); 
								 myGaCode[2] = l07251.getE01DPCSEQ(); 
								 myGaCode[3] = l07251.getE01DPCDES(); 
								 myGaCode[4] = l07251.getE01DPCAPA(); 
								 myGaCode[5] = l07251.getE01DPGDES(); 
 
								 gcodeList.addRow(myFlag1, myGaCode);

								 beanListM.addRow(l07251);

								}
								// REGISTROS DE GARANTIA IBS//
								 if (l07251.getH01FLGWK2().equals("R")) { 
								 myGaCode[0] = l07251.getE01ROCREF(); 
								 myGaCode[1] = l07251.getE01ROCTYP(); 
								 myGaCode[2] = l07251.getE01ROCFAA(); 
								 myGaCode[3] = l07251.getE01ROCOWN(); 
								 myGaCode[4] = l07251.getE01ROCNAM(); 
 
								 gcodeListR.addRow(myFlag1, myGaCode);

								 beanListR.addRow(l07251);

								}
								// REGISTROS DE GARANTES PROPUESTA //
								if (l07251.getH01FLGWK2().equals("D")){ 
								  try{
									myRow[0] = l07251.getE01DPGSGR(); // 0
//									myRow[1] = l07251.getE01DPGTIP(); // 1
									myRow[2] = l07251.getE01DPGDES(); // 2
									myRow[3] = l07251.getE01DPCSEQ(); // 3
									myRow[4] = l07251.getE01DPGIDN(); // 4
									myRow[5] = l07251.getE01DPGIPY(); // 5
									myRow[6] = l07251.getE01DPGIPM(); // 6
									myRow[7] = l07251.getE01DPGIPD(); // 7
									myRow[8] = l07251.getE01DPGRTE(); // 8

									if (l07251.getE01DPGCRG().equals("1")){ 
										myRow[1] = "AVALISTA";
									}
									if (l07251.getE01DPGCRG().equals("2")){ 
										myRow[1] = "CO-SOLICITANTE";
									}
									if (l07251.getE01DPGCRG().equals("3")){ 
										myRow[1] = "FIADOR";
									}
									beanListD.addRow(l07251);

								  }
								  catch (Exception e){
								  }
								  //este es el campo de rompimiento por grupo
								  myFlag= l07251.getE01DPCSEQ();
								  beanList.addRow(myFlag, myRow);
								}
							}
							newmessage = mc.receiveMessage();

						}

				flexLog("Putting java beans into the session");
				ses.setAttribute("gaList", beanList); 			
				ses.setAttribute("gaCodeList", gcodeList);		
				ses.setAttribute("gaCodeListR", gcodeListR);
				ses.setAttribute("error", msgError);

// carga tablas de ayudas
   /*---------------------------------------------------------------*/ 
   // Carga dropdownlist para la consulta de la tabla cnofc
   /*---------------------------------------------------------------*/
   flexLog("Opennig Socket Connection");
   JBList beanLP4 = null;
   String myFlagP4 = "";
   String myOptionP4 = "";
   String mensaje_error = "";

   //carga productos
   EDP073601Message msgCnofc = null;

   try {
	   beanLP4 = new datapro.eibs.beans.JBList();
	   msgCnofc = new datapro.eibs.beans.EDP073601Message();
   } catch (Exception ex) {
   flexLog(mensaje_error+ ex);	
   //flexLog("Error: " + ex);
   }
   /*....................................................*/
   // Send Initial data for CNOFC tables. (productos) 
   try {
	   flexLog("Send Initial Data");
	   msgCnofc = (EDP073601Message) mc.getMessageRecord("EDP073601");
	   msgCnofc.setH01USERID(user.getH01USR());
	   msgCnofc.setH01PROGRM("EDP0736");
	   msgCnofc.setH01TIMSYS(getTimeStamp());
	   msgCnofc.setH01OPECOD("0015");
	   msgCnofc.setE01CNOCFL("05");
	   msgCnofc.send();
	   msgCnofc.destroy();
   } catch (Exception e) {
	   e.printStackTrace();
	   flexLog("Error: " + e);
	   throw new RuntimeException("Socket Communication Error");
   }
   /*...............................................................*/
   // Receive Error Message
   try {
	   newmessage = mc.receiveMessage();
   //habilitar cuando la primera seleccion esta en blanco
   		myOptionP4 = "<option value=\"\"></option>";
   		beanLP4.addRow(myFlag, myOptionP4);

	   if (newmessage.getFormatName().equals("EDP073601")) {

		   String markerCN = "";
		   while (true) {

			   msgCnofc = (EDP073601Message) newmessage;

			   markerCN = msgCnofc.getH01FLGMAS();

			   if (markerCN.equals("*")) {
			   	
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

	flexLog("Putting java beans into the session");
//	ses.setAttribute("optList", beanList);
	ses.setAttribute("error", msgError);
	ses.setAttribute("optCN05", beanLP4);


	   } catch (Exception ex) {	
	   ex.printStackTrace();
	   flexLog("Error: " + ex);
	   throw new RuntimeException("Socket Communication Error");
   }
   /*---------------------------------------------------------------*/ 
   // Carga dropdownlist para la consulta de la tabla cnofc19
   /*---------------------------------------------------------------*/
   flexLog("Opennig Socket Connection");


   JBList beanCN19 = null;
   String myFlagCN19 = "";
   String myOptionCN19 = "";
//   String mensaje_error = "";

   //carga productos
//   EDP073601Message msgCnofc19 = null;

   try {
	   beanCN19 = new datapro.eibs.beans.JBList();
	   msgCnofc = new datapro.eibs.beans.EDP073601Message();
   } catch (Exception ex) {
   flexLog(mensaje_error+ ex);	
   //flexLog("Error: " + ex);
   }
   /*....................................................*/
   // Send Initial data for CNOFC tables. (productos) 
   try {
	   flexLog("Send Initial Data");
	   msgCnofc = (EDP073601Message) mc.getMessageRecord("EDP073601");
	   msgCnofc.setH01USERID(user.getH01USR());
	   msgCnofc.setH01PROGRM("EDP0736");
	   msgCnofc.setH01TIMSYS(getTimeStamp());
	   msgCnofc.setH01OPECOD("0015");
	   msgCnofc.setE01CNOCFL("19");
	   msgCnofc.send();
	   msgCnofc.destroy();
   } catch (Exception e) {
	   e.printStackTrace();
	   flexLog("Error: " + e);
	   throw new RuntimeException("Socket Communication Error");
   }
   /*...............................................................*/
   // Receive Error Message
   try {
	   newmessage = mc.receiveMessage();
   //habilitar cuando la primera seleccion esta en blanco
   		myOptionCN19 = "<option value=\"\"></option>";
   		beanCN19.addRow(myFlag, myOptionCN19);

	   if (newmessage.getFormatName().equals("EDP073601")) {

		   String markerCN = "";
		   while (true) {

			   msgCnofc = (EDP073601Message) newmessage;

			   markerCN = msgCnofc.getH01FLGMAS();

			   if (markerCN.equals("*")) {
			   	
				   break;
			   } else {
				   myOptionCN19 =
					   "<option value=\""
						   + msgCnofc.getE01CNORCD()
						   + "\">"
						   + msgCnofc.getE01CNORCD()
						   + " - "
						   + msgCnofc.getE01CNODSC()
						   + "</option>";
				   beanCN19.addRow(myFlagCN19, myOptionCN19);
			   }
			   newmessage = mc.receiveMessage();
		   }

	   } else
		   flexLog("Message " + newmessage.getFormatName() + " received.");

	flexLog("Putting java beans into the session");
//	ses.setAttribute("optList", beanList);
	ses.setAttribute("error", msgError);
	ses.setAttribute("optCN19", beanCN19);


	   } catch (Exception ex) {	
	   ex.printStackTrace();
	   flexLog("Error: " + ex);
	   throw new RuntimeException("Socket Communication Error");
   }

// end read table
// fin carga tablas de ayudas

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP072501Help", beanListM);
				ses.setAttribute("EDP072501HelpD", beanListD);
				ses.setAttribute("EDP072501HelpR", beanListR);
				ses.setAttribute("userPO", userPO);
				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_branch_list1.jsp");
							callPage(
								LangPath + "EDP0725_branch_list1.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_branch_list.jsp");

							callPage(
								LangPath + "EDP0725_branch_list.jsp",
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


	protected void procReqNewGar(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072501Message l0725 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;


		JBObjList beanList = new JBObjList();
		JBListRec gcodeList = null;
		EDP072301Message msgL23 = null;



		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if (req.getParameter("opt") != null) {
			userPO.setHeader16(req.getParameter("opt"));
		}
		if (req.getParameter("SEQ") != null) {
			userPO.setHeader7(req.getParameter("SEQ"));
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			 l0725 = (EDP072501Message) mc.getMessageRecord("EDP072501");
			l0725.setH01USERID(user.getH01USR());
			l0725.setH01PROGRM("EDP072501");
			l0725.setH01TIMSYS(getTimeStamp());
			l0725.setH01SCRCOD("01");
			l0725.setH01OPECOD("0001");
			// OPCION 1= ADICIONAR 2=MODIFICAR
			l0725.setH01FLGWK1(userPO.getHeader16());
			l0725.setE01DPCNPR(userPO.getHeader4());
			l0725.setE01DPCSEQ(userPO.getHeader7());

			// all the fields here
			java.util.Enumeration enu = l0725.fieldEnumeration();
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

			mc.sendMessage(l0725);
			l0725.destroy();
			flexLog("EDP072501 Message Sent");
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
				showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDP072501")) {
				try {
					l0725 = new datapro.eibs.beans.EDP072501Message();
					flexLog("EDP072501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				l0725 = (EDP072501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("l0725", l0725);
				ses.setAttribute("userPO", userPO);

				ses.setAttribute("EDP072301Help", beanList);
				ses.setAttribute("gaCodeList", gcodeList);		

				//
					if (IsNotError) { // There are no errors
					 try {
						 flexLog(
							 "About to call Page: "
								 + LangPath
								 + "EDP0725_guarantee_maintenance.jsp");
						 callPage(
							 LangPath + "EDP0725_guarantee_maintenance.jsp",
							 req,
							 res);
					 } catch (Exception e) {
						 flexLog("Exception calling page " + e);
					 }
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_branch_list1.jsp");
							callPage(
								LangPath + "EDP0725_branch_list1.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

					}

				//				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}


	protected void procActionGarMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072501Message l0725 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if (req.getParameter("E01DPCNPR") != null) {
			userPO.setHeader4(req.getParameter("E01DPCNPR"));
		}

//		E01DPCNPR
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			l0725 = (EDP072501Message) ses.getAttribute("l0725");
			l0725.setH01USERID(user.getH01USR());
			l0725.setH01PROGRM("EDP072501");
			l0725.setH01TIMSYS(getTimeStamp());
			l0725.setH01SCRCOD("01");
			l0725.setH01OPECOD("0005");
			// OPCION 1= ADICIONAR 2=MODIFICAR
			l0725.setH01FLGWK1(userPO.getHeader16());

			// all the fields here
			java.util.Enumeration enu = l0725.fieldEnumeration();
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

			mc.sendMessage(l0725);
			l0725.destroy();
			flexLog("EDP072501 Message Sent");
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
				showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDP072501")) {
				try {
					l0725 = new datapro.eibs.beans.EDP072501Message();
					flexLog("EDP072501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				l0725 = (EDP072501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("l0725", l0725);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_guarantee_maintenance.jsp");
							callPage(
								LangPath + "EDP0725_guarantee_maintenance.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

					}

				//				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}



	protected void procRGARDEL(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072501Message l0725 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		EDP072501Message l07252 = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		JBObjList bl = (JBObjList) ses.getAttribute("EDP072501Help");
		int idx = Integer.parseInt(req.getParameter("COLLCOD"));
		bl.setCurrentRow(idx);

		l07252 = (EDP072501Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("l0725", l07252);



		// Send Initial data
		try {
			flexLog("Send Initial Data");
			l0725 = (EDP072501Message) ses.getAttribute("l0725");
			l0725.setH01USERID(user.getH01USR());
			l0725.setH01PROGRM("EDP072501");
			l0725.setH01TIMSYS(getTimeStamp());
			l0725.setH01SCRCOD("01");
			l0725.setH01OPECOD("0003");

			// all the fields here
			java.util.Enumeration enu = l0725.fieldEnumeration();
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

			mc.sendMessage(l0725);
			l0725.destroy();
			flexLog("EDP072501 Message Sent");
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
				showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDP072501")) {
				try {
					l0725 = new datapro.eibs.beans.EDP072501Message();
					flexLog("EDP072501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				l0725 = (EDP072501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("l0725", l0725);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_branch_list1.jsp");
							callPage(
								LangPath + "EDP0725_branch_list1.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

					}

				//				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}



	protected void procReqGarMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EDP072501Message l07252 = null;
		UserPos userPO = null;
		String opt = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
		if (req.getParameter("opt") != null) {
			userPO.setHeader16(req.getParameter("opt"));
			opt = req.getParameter("opt");
		}
		if (req.getParameter("SEQ") != null) {
			userPO.setHeader7(req.getParameter("SEQ"));
		}
		if (req.getParameter("E01DPCCUN") != null) {
			userPO.setCusNum(req.getParameter("E01DPCCUN"));
		}
		if (req.getParameter("E01CUSNA1") != null) {
			userPO.setCusName(req.getParameter("E01CUSNA1"));
		}

		// Receive Data
		try {
			if (req.getParameter("opt") != null) {
				// trae registro seleccionado de las garantias de ROCOL
				if (opt.equals("10")) {
					JBObjList bl = (JBObjList) ses.getAttribute("EDP072501HelpR");
					int idx = Integer.parseInt(req.getParameter("COLLITEMR"));
					bl.setCurrentRow(idx);
					l07252 = (EDP072501Message) bl.getRecord();
				} else {
					// trae registro seleccionado de las garantias de DPCOL
					JBObjList bl = (JBObjList) ses.getAttribute("EDP072501Help");
					int idx = Integer.parseInt(req.getParameter("COLLCOD"));
					bl.setCurrentRow(idx);
					l07252 = (EDP072501Message) bl.getRecord();
			}
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("l0725", l07252);

// BEGIN TRAER LISTA DE TITULARES POR GARANTIA
// BEGIN PRODUCTS LIST
   MessageRecord newmessage = null;
   ELEERRMessage msgError = null;

   JBListRec gcodeList = null;
   EDP072301Message msgL23 = null;

   try {
	   msgL23 = (EDP072301Message) mc.getMessageRecord("EDP072301");
	   msgL23.setH01USERID(user.getH01USR());
	   msgL23.setH01PROGRM("EDP072301");
	   msgL23.setH01TIMSYS(getTimeStamp());
	   msgL23.setH01SCRCOD("01");
	   msgL23.setH01OPECOD("0015");
    	msgL23.setE02DPTNPR(l07252.getE01DPCNPR());
//		msgL23.setE02DPTSEQ(l07252.getE01DPCSEQ());
		msgL23.setE02DPTSEQ(req.getParameter("SEQ"));
	
	   msgL23.send();
	   msgL23.destroy();
	   flexLog("EDP072301 Message Sent");
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

	   }
   } catch (Exception e) {
	   e.printStackTrace();
	   flexLog("Error: " + e + newmessage);
	   throw new RuntimeException("Socket Communication Error Receiving");
   }

   try {
	   newmessage = mc.receiveMessage();

	   if (newmessage.getFormatName().equals("EDP072301")) {

		JBObjList beanList = new JBObjList();

		JBObjList beanListD = new JBObjList();
		JBObjList beanListM = new JBObjList();

		int colNum = 10;
		//NUMERO DE COLUMNAS EN LA LISTA
		int gaColNum = 4;
		try {
			gcodeList = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
			gcodeList.init(gaColNum);

		} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		boolean firstTime = true;
		String chk = "";
		String marker = "";
		String myFlag = "";
		String TableTyp = "";
		//var for ofac status
		//var for Warning status
		String chkOfac = "";
		String chkWarn = "";
		int compar = 0;
		int indexRow = 0;
		
		String myFlag1 = "";
		String myGaCode[] = new String[gaColNum];
		for (int i=0; i<gaColNum; i++) {
			myGaCode[i] = "";
		}
		String myRow[] = new String[colNum];
		for (int i=0; i<colNum; i++) {
			myRow[i] = "";
		}

		
		while (true) {

			msgL23 = (EDP072301Message) newmessage;
			marker = msgL23.getE02OPECDE();

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
				myGaCode[0] = msgL23.getE02DPTIDN(); 
				myGaCode[1] = msgL23.getE02DPTNOM(); 
				myGaCode[2] = msgL23.getE02DPTCND() + "/" + msgL23.getE02DPTCNM()+ "/" + msgL23.getE02DPTCNY() ; 
				myGaCode[3] = msgL23.getE02DPTRTE(); 

				gcodeList.addRow(myFlag1, myGaCode);

				beanList.addRow(msgL23);
				beanListM.addRow(msgL23);
				beanListD.addRow(msgL23);
				//este es el campo de rompimiento por grupo
				myFlag= msgL23.getE02DPTNPR();

				if (marker.equals("+")) {
					beanList.setShowNext(true);

					break;
				}
			}
			newmessage = mc.receiveMessage();
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("EDP072301Help", beanList);
		ses.setAttribute("gaCodeList", gcodeList);		
		ses.setAttribute("userPO", userPO);


	   } else
	   flexLog("Message " + newmessage.getFormatName() + " received.");

	   } catch (Exception e) {
		   e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Data Receiving");
	   }

// END LISTA TITULARES

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0725_guarantee_maintenance.jsp");
					callPage(
						LangPath + "EDP0725_guarantee_maintenance.jsp",
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

	}



	protected void procRTITNEW(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP072301Message l0723 = null;
		EDP072501Message l0725 = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		ses.setAttribute("userPO", userPO);
		
		l0723 = new datapro.eibs.beans.EDP072301Message();
		l0725 = new datapro.eibs.beans.EDP072501Message();
		java.util.Enumeration enu = l0725.fieldEnumeration();
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


		if (req.getParameter("optG") != null) {
			userPO.setHeader17(req.getParameter("optG"));
		}


		try {
			flexLog("Send Initial Data");
			 l0723 = (EDP072301Message) mc.getMessageRecord("EDP072301");
			if (req.getParameter("E01DPCNPR") != null) {
				l0723.setE02DPTNPR(req.getParameter("E01DPCNPR"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		
		ses.setAttribute("l0723", l0723);
		ses.setAttribute("l0725", l0725);
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
		 + "EDP0725_guarantee_titulares.jsp");
			 callPage(LangPath + "EDP0725_guarantee_titulares.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


	protected void procRTITMAINT(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EDP072301Message l0723 = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
		if (req.getParameter("optG") != null) {
			userPO.setHeader17(req.getParameter("optG"));
		}

		// Receive Data
		try {
			// toma campos del registro seleccionado
			JBObjList bl = (JBObjList) ses.getAttribute("EDP072301Help");
			int idx = Integer.parseInt(req.getParameter("COLLCOD"));
			bl.setCurrentRow(idx);

			l0723 = (EDP072301Message) bl.getRecord();
			try {
				flexLog("Send Initial Data");
//				
				if (req.getParameter("E01DPCNPR") != null) {
					l0723.setE02DPTNPR(req.getParameter("E01DPCNPR"));
				}
				if (req.getParameter("SEQ") != null) {
					userPO.setHeader7(req.getParameter("SEQ"));
				}
				if (userPO.getHeader7() != null) {
					l0723.setE02DPTSEQ(userPO.getHeader7());
				}


			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}



			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("l0723", l0723);
			try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0725_guarantee_titulares.jsp");
					callPage(
						LangPath + "EDP0725_guarantee_titulares.jsp",
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

	}



	protected void procRFMTGUAR(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

//		BEGIN TRAER LISTA DE TITULARES POR GARANTIA
//		BEGIN PRODUCTS LIST
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;

		JBListRec gcodeList = null;
		EDP072301Message msgL23 = null;

		try {
			msgL23 = (EDP072301Message) mc.getMessageRecord("EDP072301");
			msgL23.setH01USERID(user.getH01USR());
			msgL23.setH01PROGRM("EDP072301");
			msgL23.setH01TIMSYS(getTimeStamp());
			msgL23.setH01SCRCOD("01");
			msgL23.setH01OPECOD("0015");
			 msgL23.setE02DPTNPR(userPO.getHeader4());
			 msgL23.setE02DPTSEQ(userPO.getHeader7());

			msgL23.setH01FLGWK3(userPO.getHeader17());


			msgL23.send();
			msgL23.destroy();
			flexLog("EDP072301 Message Sent");
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

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP072301")) {

			 JBObjList beanList = new JBObjList();

			 JBObjList beanListD = new JBObjList();
			 JBObjList beanListM = new JBObjList();

			 int colNum = 10;
			 //NUMERO DE COLUMNAS EN LA LISTA
			 int gaColNum = 4;
			 try {
				 gcodeList = (datapro.eibs.beans.JBListRec) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBListRec");
				 gcodeList.init(gaColNum);

			 } 
			 catch (Exception ex) {
				 flexLog("Error: " + ex); 
			 }

			 boolean firstTime = true;
			 String chk = "";
			 String marker = "";
			 String myFlag = "";
			 String TableTyp = "";
			 //var for ofac status
			 //var for Warning status
			 String chkOfac = "";
			 String chkWarn = "";
			 int compar = 0;
			 int indexRow = 0;
		
			 String myFlag1 = "";
			 String myGaCode[] = new String[gaColNum];
			 for (int i=0; i<gaColNum; i++) {
				 myGaCode[i] = "";
			 }
			 String myRow[] = new String[colNum];
			 for (int i=0; i<colNum; i++) {
				 myRow[i] = "";
			 }

		
			 while (true) {

				 msgL23 = (EDP072301Message) newmessage;
				 marker = msgL23.getE02OPECDE();

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
					 myGaCode[0] = msgL23.getE02DPTIDN(); 
					 myGaCode[1] = msgL23.getE02DPTNOM(); 
					 myGaCode[2] = msgL23.getE02DPTCND() + "/" + msgL23.getE02DPTCNM()+ "/" + msgL23.getE02DPTCNY() ; 
					 myGaCode[3] = msgL23.getE02DPTRTE(); 

					 gcodeList.addRow(myFlag1, myGaCode);

					 beanList.addRow(msgL23);
					 beanListM.addRow(msgL23);
					 beanListD.addRow(msgL23);
					 //este es el campo de rompimiento por grupo
					 myFlag= msgL23.getE02DPTNPR();

					 if (marker.equals("+")) {
						 beanList.setShowNext(true);

						 break;
					 }
				 }
				 newmessage = mc.receiveMessage();
			 }

			 flexLog("Putting java beans into the session");
			 ses.setAttribute("EDP072301Help", beanList);
			 ses.setAttribute("gaCodeList", gcodeList);		
			 ses.setAttribute("userPO", userPO);


			} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

			} catch (Exception e) {
				e.printStackTrace();
			 flexLog("Error: " + e);
			 throw new RuntimeException("Socket Communication Data Receiving");
			}

//		END LISTA TITULARES

	try {

		flexLog(
			"About to call Page: "
				+ LangPath
		+ "EDP0725_guarantee_maintenance.jsp");
	callPage(
		LangPath + "EDP0725_guarantee_maintenance.jsp",
				req,
				res);

	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


protected void procActionSAVTIT(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDP072301Message l0723 = null;
	EDP072501Message l0725 = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;
	int acctype = 0;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	if (req.getParameter("E02DPCCUN") != null) {
		userPO.setCusNum(req.getParameter("E02DPCCUN"));
	}
	if (req.getParameter("E02CUSNA1") != null) {
		userPO.setCusName(req.getParameter("E02CUSNA1"));
	}


	// Send Initial data
	try {
		flexLog("Send Initial Data");
		l0725 = (EDP072501Message) ses.getAttribute("l0725");

		l0723 = (EDP072301Message) ses.getAttribute("l0723");
		l0723.setH01USERID(user.getH01USR());
		l0723.setH01PROGRM("EDP072301");
		l0723.setH01TIMSYS(getTimeStamp());
		l0723.setH01SCRCOD("01");
		l0723.setH01OPECOD("0005");
		// OPCION 1= ADICIONAR 2=MODIFICAR 3 = DELETE
		l0723.setH01FLGWK3(userPO.getHeader17());

		// all the fields here
		java.util.Enumeration enu = l0723.fieldEnumeration();
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

		mc.sendMessage(l0723);
		l0723.destroy();
		flexLog("EDP072301 Message Sent");
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
			showERROR(msgError);
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

		if (newmessage.getFormatName().equals("EDP072301")) {
			try {
				l0723 = new datapro.eibs.beans.EDP072301Message();
				flexLog("EDP072301 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			l0723 = (EDP072301Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("l0723", l0723);
			ses.setAttribute("l0725", l0725);
			ses.setAttribute("userPO", userPO);

			//
				if (IsNotError) { // There are no errors
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=440");
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0725_guarantee_titulares.jsp");
						callPage(
							LangPath + "EDP0725_guarantee_titulares.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				}

			//				
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

}


	protected void procRGARTENEW(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072501Message l0725 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if (req.getParameter("opt") != null) {
			userPO.setHeader16(req.getParameter("opt"));
		}


		// Send Initial data
		try {
			flexLog("Send Initial Data");
			 l0725 = (EDP072501Message) mc.getMessageRecord("EDP072501");
//			l0725 = (EDP072501Message) ses.getAttribute("l0725");
			l0725.setH01USERID(user.getH01USR());
			l0725.setH01PROGRM("EDP072501");
			l0725.setH01TIMSYS(getTimeStamp());
			l0725.setH01SCRCOD("01");
			l0725.setH01OPECOD("0002");
			// OPCION 6= ADICIONAR 7=MODIFICAR
			l0725.setH01FLGWK1(userPO.getHeader16());
			l0725.setE01DPCNPR(userPO.getHeader4());

			// all the fields here
			java.util.Enumeration enu = l0725.fieldEnumeration();
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

			mc.sendMessage(l0725);
			l0725.destroy();
			flexLog("EDP072501 Message Sent");
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
				showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDP072501")) {
				try {
					l0725 = new datapro.eibs.beans.EDP072501Message();
					flexLog("EDP072501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				l0725 = (EDP072501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("l0725", l0725);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
					 try {
						 flexLog(

							 "About to call Page: "
								 + LangPath
						+ "EDP0725_garante_maintenance.jsp");
							callPage(LangPath + "EDP0725_garante_maintenance.jsp", req, res);
					 } catch (Exception e) {
						 flexLog("Exception calling page " + e);
					 }
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_branch_list1.jsp");
							callPage(
								LangPath + "EDP0725_branch_list1.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

					}

				//				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}


	protected void procRGARIBSCOPY(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072501Message l0725 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if (req.getParameter("opt") != null) {
			userPO.setHeader16(req.getParameter("opt"));
		}


		// Send Initial data
		try {
			flexLog("Send Initial Data");
			l0725 = (EDP072501Message) mc.getMessageRecord("EDP072501");
			l0725.setH01USERID(user.getH01USR());
			l0725.setH01PROGRM("EDP072501");
			l0725.setH01TIMSYS(getTimeStamp());
			l0725.setH01SCRCOD("01");
			l0725.setH01OPECOD("0022");
			l0725.setH01FLGWK1(userPO.getHeader16());
			l0725.setE01DPCCUN(userPO.getCusNum());
			l0725.setE01DPCNPR(userPO.getHeader4());
			l0725.setE01ROCREF(req.getParameter("IDAVAL"));
 
			// all the fields here
			java.util.Enumeration enu = l0725.fieldEnumeration();
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

			mc.sendMessage(l0725);
			l0725.destroy();
			flexLog("EDP072501 Message Sent");
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
				showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDP072501")) {
				try {
					l0725 = new datapro.eibs.beans.EDP072501Message();
					flexLog("EDP072501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				l0725 = (EDP072501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("l0725", l0725);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors

						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=200");

					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_branch_list1.jsp");
							callPage(
								LangPath + "EDP0725_branch_list1.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

					}

				//				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}




	protected void procRGARTEMAINT(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EDP072501Message l07252 = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
		if (req.getParameter("opt") != null) {
			userPO.setHeader16(req.getParameter("opt"));
		}
		if (req.getParameter("SEQD") != null) {
			userPO.setHeader7(req.getParameter("SEQD"));
		}
		if (req.getParameter("TYP") != null) {
			userPO.setHeader8(req.getParameter("TYP"));
		}
		if (req.getParameter("DES") != null) {
			userPO.setHeader9(req.getParameter("DES"));
		}
		if (req.getParameter("E01DPCCUN") != null) {
			userPO.setCusNum(req.getParameter("E01DPCCUN"));
		}
		if (req.getParameter("E01CUSNA1") != null) {
			userPO.setCusName(req.getParameter("E01CUSNA1"));
		}
		if (req.getParameter("E01DPCNPR") != null) {
			userPO.setHeader4(req.getParameter("E01DPCNPR"));
		}
		
		// Receive Data
		try {
			// toma campos del registro seleccionado
			JBObjList bl = (JBObjList) ses.getAttribute("EDP072501HelpD");
			int idx = Integer.parseInt(req.getParameter("COLLITEM"));
			bl.setCurrentRow(idx);

			l07252 = (EDP072501Message) bl.getRecord();

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("l0725", l07252);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0725_garante_maintenance.jsp");
					callPage(
						LangPath + "EDP0725_garante_maintenance.jsp",
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

	}



	protected void procAGARTEMAINT(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072501Message l0725 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if (req.getParameter("ACTION") != null) {
			userPO.setHeader16(req.getParameter("ACTION"));
		}


		// Send Initial data
		try {
			flexLog("Send Initial Data");
			l0725 = (EDP072501Message) ses.getAttribute("l0725");
			l0725.setH01USERID(user.getH01USR());
			l0725.setH01PROGRM("EDP072501");
			l0725.setH01TIMSYS(getTimeStamp());
			l0725.setH01SCRCOD("01");
			l0725.setH01OPECOD("0006");
			// OPCION 6= ADICIONAR 7=MODIFICAR
			l0725.setH01FLGWK1(userPO.getHeader16());

			// all the fields here
			java.util.Enumeration enu = l0725.fieldEnumeration();
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

			mc.sendMessage(l0725);
			l0725.destroy();
			flexLog("EDP072501 Message Sent");
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
				showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDP072501")) {
				try {
					l0725 = new datapro.eibs.beans.EDP072501Message();
					flexLog("EDP072501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				l0725 = (EDP072501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("l0725", l0725);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_garante_maintenance.jsp");
							callPage(
								LangPath + "EDP0725_garante_maintenance.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

					}

				//				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}



	protected void procRGARTEDEL(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072501Message l0725 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		EDP072501Message l07252 = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		JBObjList bl = (JBObjList) ses.getAttribute("EDP072501HelpD");
		int idx = Integer.parseInt(req.getParameter("COLLITEM"));
		bl.setCurrentRow(idx);

		l07252 = (EDP072501Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("l0725", l07252);


		// Send Initial data
		try {
			flexLog("Send Initial Data");
			l0725 = (EDP072501Message) ses.getAttribute("l0725");
			l0725.setH01USERID(user.getH01USR());
			l0725.setH01PROGRM("EDP072501");
			l0725.setH01TIMSYS(getTimeStamp());
			l0725.setH01SCRCOD("01");
			l0725.setH01OPECOD("0007");

			// all the fields here
			java.util.Enumeration enu = l0725.fieldEnumeration();
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

			mc.sendMessage(l0725);
			l0725.destroy();
			flexLog("EDP072501 Message Sent");
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
				showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDP072501")) {
				try {
					l0725 = new datapro.eibs.beans.EDP072501Message();
					flexLog("EDP072501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				l0725 = (EDP072501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("l0725", l0725);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0725_branch_list1.jsp");
							callPage(
								LangPath + "EDP0725_branch_list1.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

					}

				//				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

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

			int screen = A_POSITION;

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
						case R_ENTER :
							procReqEnterBranch(mc, msgUser, req, res, session);
							break;
						case R_BRANCH_LIST :
							procReqBranchList(mc, msgUser, req, res, session);
							break;
						case R_BRANCH_LIST_GAR :
							procReqBranchGar(mc, msgUser, req, res, session);
							break;
						case R_GAR_NEW :
							 procReqNewGar(mc, msgUser, req, res, session);							
							break;								
						case R_GAR_MAINT :
							procReqGarMaint(mc, msgUser, req, res, session);
							break;

						case RGARTENEW :
							 procRGARTENEW(mc, msgUser, req, res, session);							
							break;								
						case RGARTEMAINT :
							procRGARTEMAINT(mc, msgUser, req, res, session);
							break;

						case RTITNEW :
							 procRTITNEW(mc, msgUser, req, res, session);							
							break;								

						case RTITMAINT :
							procRTITMAINT(mc, msgUser, req, res, session);
							break;

						case RFMTGUAR :
							procRFMTGUAR(mc, msgUser, req, res, session);
							break;

						case RTITDEL :
						procActionSAVTIT(mc, msgUser, req, res, session);
							break;

						case SAVTIT :
							procActionSAVTIT(mc, msgUser, req, res, session);
							break;

							// Action
						case RGARDEL :
							procRGARDEL(mc, msgUser, req, res, session);
							break;
						case A_GAR_MAINT :
							procActionGarMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case AGARTEMAINT :
							procAGARTEMAINT(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case RGARTEDEL :
							procRGARTEDEL(mc, msgUser, req, res, session);
							break;

							// END Entering
						case RGARIBSCOPY :
							procRGARIBSCOPY(mc, msgUser, req, res, session);
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
