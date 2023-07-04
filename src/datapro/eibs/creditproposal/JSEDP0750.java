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

public class JSEDP0750 extends datapro.eibs.master.SuperServlet {

	// Action 
	// first screen
	protected static final int R_FILTER = 100;
	// second screen
	protected static final int A_ENTER = 200;
	// third screen
	protected static final int R_LIST_2 = 300;
	//	selecciona nuevo, o modificar registro seleccionado, o delete
	 protected static final int A_POSITION = 800;
	// viene seleccionado el formato a trabajar	y mostrar cuentas del formato
	protected static final int SCREEN400 = 400;
	//	llamar screen para actualizar registro
 	protected static final int A_MAINTENANCE = 600;
	//	llamar screen para actualizar registro	
	protected static final int A_MAINTENANCE2 = 630;
	// llamar a procedimiento para crear resistro 
	protected static final int A_INSERT  = 650;
	
	// llamar screen para nuevo registro
	protected static final int R_NEW = 500;
	
	protected static final int R_ENTER = 1;
	protected static final int R_MAINTENANCE = 500;
	protected static final int R_DELETE = 700;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0750() {
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
	

	
	/** Filtro */
	/************************************************************************************************/
	/************************************************************************************************/
	/************************************************************************************************/
	protected void procReqFilter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		//Start Parameter
		req.getParameter("SCREEN");
		req.getParameter("OPTION");

		String mensaje_error = "";
		MessageRecord newmessage = null;
		MessageRecord newmessageP4 = null;
		ELEERRMessage msgError = null;
		EDP074101Message msgList1 = null; //Sucursales
		EDP074201Message msgList2 = null; //Banks	
		EDP074301Message msgList3 = null; //Productos
		JBList beanList1 = null;
		JBList beanList2 = null;
		JBList beanList3 = null;
		String myOption = "";
		String myFlag = "";

		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String a = req.getParameter("OPTION");
		
		//RETRIEVE PARAMETERS - OPTION FOR PREPARE=1 OR PROCESS=2
		if (req.getParameter("OPTION") != null) {
			userPO.setOption(req.getParameter("OPTION"));
		}


		try {
			mensaje_error = "Error: ";
			msgError = new datapro.eibs.beans.ELEERRMessage();
			beanList1 = new datapro.eibs.beans.JBList();
			beanList2 = new datapro.eibs.beans.JBList();
			beanList3 = new datapro.eibs.beans.JBList();
			msgList1 = new datapro.eibs.beans.EDP074101Message(); //DDL1
			msgList2 = new datapro.eibs.beans.EDP074201Message(); //DDL2
			msgList3 = new datapro.eibs.beans.EDP074301Message(); //DDL3
		} catch (Exception ex) {
			//flexLog("Error: " + ex);
			flexLog(mensaje_error + ex);
		}

		/*---------------------------------------------------*/
		// Send Initial data for DDL Bank
		/*---------------------------------------------------*/
		try {
			flexLog("Send Initial Data Bank");
			msgList2 = (EDP074201Message) mc.getMessageRecord("EDP074201");
			msgList2.setH01USERID(user.getH01USR());
			msgList2.setH01PROGRM("EDP0742");
			msgList2.setH01TIMSYS(getTimeStamp());
			msgList2.setH01OPECOD("0015");
			msgList2.send();
			msgList2.destroy();
		} catch (Exception ex) {
			ex.printStackTrace();
			//flexLog("Error: " + ex);
			flexLog(mensaje_error + ex);
			throw new RuntimeException("Socket Communication Error Bank");
		}
		/*.................................................*/
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
			//		myOption = "<option value=\"\"></option>";
			//		beanList.addRow(myFlag, myOption);
			if (newmessage.getFormatName().equals("EDP074201")) {

				String marker = "";
				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					msgList2 = (EDP074201Message) newmessage;
					marker = msgList2.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						myOption =
							"<option value=\""
								+ msgList2.getE01CNTKEY()
								+ "\">"
								+ msgList2.getE01CNTKEY()
								+ " - "
								+ msgList2.getE01CNTNME()
								+ "</option>";
						beanList2.addRow(myFlag, myOption);
					}
					newmessage = mc.receiveMessage();
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session Bank");
			ses.setAttribute("optList2", beanList2);
			ses.setAttribute("optList1", beanList1);
			ses.setAttribute("error", msgError);

		} catch (Exception ex) {
			ex.printStackTrace();
			//flexLog("Error: " + e);
			flexLog(mensaje_error + ex);
			throw new RuntimeException("Socket Communication Error Bank");
		}
		/*---------------------------------------------------------------*/

		/*---------------------------------------------------*/
		// Send Initial data for DDL Branch
		/*---------------------------------------------------*/
		try {
			flexLog("Send Initial Data Branch");
			msgList1 = (EDP074101Message) mc.getMessageRecord("EDP074101");
			msgList1.setH01USERID(user.getH01USR());
			msgList1.setH01PROGRM("EDP0741");
			msgList1.setH01TIMSYS(getTimeStamp());
			msgList1.setH01OPECOD("0015");
			msgList1.send();
			msgList1.destroy();
		} catch (Exception ex) {
			ex.printStackTrace();
			//flexLog("Error: " + ex);
			flexLog(mensaje_error + ex);
			throw new RuntimeException("Socket Communication Error Branch");
		}
		/*.................................................*/
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
			//			myOption = "<option value=\"\">TODOS</option>";
			//			beanList1.addRow(myFlag, myOption);
			if (newmessage.getFormatName().equals("EDP074101")) {

				String marker = "";
				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					msgList1 = (EDP074101Message) newmessage;
					marker = msgList1.getH01FLGMAS();

					//msgList2 = (EDP074201Message) newmessage;
					//marker = msgList2.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						myOption =
							"<option value=\""
								+ msgList1.getE01BRNNUM()
								+ "\">"
								+ msgList1.getE01BRNNUM()
								+ " - "
								+ msgList1.getE01BRNNME()
								+ "</option>";
						beanList1.addRow(myFlag, myOption);
					}
					newmessage = mc.receiveMessage();
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session Branch");
			ses.setAttribute("optList1", beanList1);
			ses.setAttribute("optList11", beanList1);
			//ses.setAttribute("optList2", beanList2);
			ses.setAttribute("error", msgError);

		} catch (Exception ex) {
			ex.printStackTrace();
			//flexLog("Error: " + e);
			flexLog(mensaje_error + ex);
			throw new RuntimeException("Socket Communication Error Branch");
		}
		/*---------------------------------------------------------------*/

		/*---------------------------------------------------*/
		// Send Initial data for DDL Producto
		/*---------------------------------------------------*/
		try {
			flexLog("Send Initial Data Productos");
			msgList3 = (EDP074301Message) mc.getMessageRecord("EDP074301");

			msgList3.setH01USERID(user.getH01USR());
			msgList3.setH01PROGRM("EDP0743");
			msgList3.setH01TIMSYS(getTimeStamp());
			msgList3.setH01OPECOD("0015");
			msgList3.send();
			msgList3.destroy();
		} catch (Exception ex) {
			ex.printStackTrace();
			//flexLog("Error: " + ex);
			flexLog(mensaje_error + ex);
			throw new RuntimeException("Socket Communication Error Productos");
		}
		/*.................................................*/
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
			myOption = "<option value=\"\">    </option>";
			beanList3.addRow(myFlag, myOption);
			if (newmessage.getFormatName().equals("EDP074301")) {

				String marker = "";
				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					msgList3 = (EDP074301Message) newmessage;
					marker = msgList3.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						myOption =
							"<option value=\""
								+ msgList3.getE01APCCDE()
								+ "\">"
								+ msgList3.getE01APCCDE()
								+ " "
								+ msgList3.getE01APCTYP()
								+ " - "
								+ msgList3.getE01APCFL3() // AGRICOLA O AUTO //
								+ " - "
								+ msgList3.getE01APCDSC()
								+ "</option>";
						beanList3.addRow(myFlag, myOption);
					}
					newmessage = mc.receiveMessage();
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session Producto");
			ses.setAttribute("optList3", beanList3);
			ses.setAttribute("optList4", beanList3);
			ses.setAttribute("error", msgError);

		} catch (Exception ex) {
			ex.printStackTrace();
			//flexLog("Error: " + e);
			flexLog(mensaje_error + ex);
			throw new RuntimeException("Socket Communication Error Producto");
		}
		/*---------------------------------------------------------------*/


		/***********************************************/
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0750_proposals_filter.jsp");
			callPage(LangPath + "EDP0750_proposals_filter.jsp", req, res);
		} catch (Exception ex) {
			flexLog("Exception calling page " + ex);
		}
		/***********************************************/
	}
	


	/**************************************************************************/
	/**************************************************************************/
	/**************************************************************************/
	protected void procActionEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String DESINCORPORADAS ="";
		String RENOVAR ="";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		//RETRIEVE PARAMETERS---------------------------------------------------------
		if (req.getParameter("BNK") != null) {
			userPO.setBank(req.getParameter("BNK"));
		}

		if (req.getParameter("BRN") != null) {
			userPO.setBranch(req.getParameter("BRN"));
		}

		//--------------------------------------------------------------------------------

		// Send Initial data--------------------------------------------------------------
		try {
			msgList = (EDP072001Message) mc.getMessageRecord("EDP072001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP072001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setE01PLPBNK(userPO.getBank());
			msgList.setE01PLPBRN(userPO.getBranch());

			// These are filters
			if(req.getParameter("E01FILNPR")!=null){
				msgList.setE01FILNPR(req.getParameter("E01FILNPR"));
			}
			//
			if(req.getParameter("E01FILCUN")!=null){
				msgList.setE01FILCUN(req.getParameter("E01FILCUN"));
			}
			//
			if(req.getParameter("E01FILEJE")!=null){
				msgList.setE01FILEJE(req.getParameter("E01FILEJE"));
			}
			if(req.getParameter("E01FILEST")!=null){
				msgList.setE01FILEST(req.getParameter("E01FILEST"));
			}
			if(req.getParameter("E01FILTYP")!=null){
				//msgList.setE01FILTYP(req.getParameter("E01FILTYP"));
			}
			//
			if(req.getParameter("E01FILPRD")!=null){
				msgList.setE01FILPRD(req.getParameter("E01FILPRD"));
			}
			//			
			if(req.getParameter("Pos")!=null){
				msgList.setE01RECPOS(req.getParameter("Pos"));
			}
			//
			if(req.getParameter("E01YYYFIL")!=null){
				msgList.setE01YYYFIL(req.getParameter("E01YYYFIL"));
				DESINCORPORADAS = req.getParameter("E01YYYFIL");
			}
			if(req.getParameter("E01XXXFIL")!=null){
				RENOVAR = req.getParameter("E01XXXFIL");
			}
			
			String SEL_OPTION = userPO.getOption();
			//============================================================
			//=================1===========================================
			if (SEL_OPTION.equals("1")) {
				if (DESINCORPORADAS.equals("0") ){
					msgList.setH01FLGWK2("1");
				}
				if (DESINCORPORADAS.equals("1") ){
					if (RENOVAR.equals("1") ){
						msgList.setH01FLGWK2("5");
						userPO.setOption("5");
					} else {
						msgList.setH01FLGWK2("3");
						userPO.setOption("3");
					}
				}
			};
			//
			if (SEL_OPTION.equals("2")) {
				if (DESINCORPORADAS.equals("0") ){
								msgList.setH01FLGWK2("2");
				}
				if (DESINCORPORADAS.equals("1") ){

					if (RENOVAR.equals("1") ){
						msgList.setH01FLGWK2("5");
						userPO.setOption("5");
					} else {
						msgList.setH01FLGWK2("4");
						userPO.setOption("4");
					}
				}
			};
			
			msgList.send();
			msgList.destroy();
			flexLog("EDP072001 Message Sent a.");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {

			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);
				//beanList.setNoResult(true);
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
//
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP072001")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";

				int numrec = 0;
				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					msgList = (EDP072001Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgList.getE01RECPOS()));
					}
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgList);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							beanList.setLastRec(Integer.parseInt(msgList.getE01RECPOS()));
							break;
						}
						numrec = Integer.parseInt(msgList.getE01RECPOS());
						if (numrec == 6) {
							beanList.setShowPrev(true);
						}

					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP072001Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {

					if (msgError.getERRNUM().equals("0")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0750_proposals_list.jsp");
						callPage(
							LangPath + "EDP0750_proposals_list.jsp",
							req,
							res);
					} else {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0750_proposals_filter.jsp");

						callPage(
							LangPath + "EDP0750_proposals_filter.jsp",
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

	
	
	// first screen list of formats
	protected void procReqListPaths1(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP075001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (EDP075001Message) mc.getMessageRecord("EDP075001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ELD010003");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.send();
			msgList.destroy();
			flexLog("EDP075001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
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

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP075001")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String TableTyp = "";

				int compar = 0;
				int indexRow = 0;
				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					msgList = (EDP075001Message) newmessage;

					marker = msgList.getE01OPECDE();

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

						beanList.addRow(msgList);

						if (marker.equals("+")) {
							beanList.setShowNext(true);

							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP075001Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0750_trace_list1.jsp");
							callPage(
								LangPath + "EDP0750_trace_list1.jsp",
								req,
								res);
						} else {

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

	
	//	manejo formato a trabajar first screen
 	protected void procAction400(
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

	 userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	 int inptOPT = 0;

	 inptOPT = Integer.parseInt(req.getParameter("opt"));

	 switch (inptOPT) {
		 case 1 : //List accounts by format
		
		 procReqListPaths2(mc, user, req, res, ses);
		
		 default :
			 res.sendRedirect(
				 super.srctx
					 + "/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=800") ;
			 break;
		 }
	 }



//	MANEJO DE LISTA DE RUTAS CON DETALLES
	 protected void procReqListPaths2(
		 MessageContext mc,
		 ESS0030DSMessage user,
		 HttpServletRequest req,
		 HttpServletResponse res,
		 HttpSession ses)
		 throws ServletException, IOException {

		 MessageRecord newmessage = null;
		 EDP075001Message msgList = null;
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

		 // asigna variables para trabajar con cuentas del formato seleccionado

					
		 // Send Initial data
		 try {
			 msgList = (EDP075001Message) mc.getMessageRecord("EDP075001");
			 msgList.setH01USERID(user.getH01USR());
			 msgList.setH01PROGRM("ELD010003");
			 msgList.setH01TIMSYS(getTimeStamp());
			 msgList.setH01SCRCOD("01");
			 msgList.setH01OPECOD("0015");
			if(req.getParameter("PROP")!=null){
			 msgList.setE01DPSNPR(req.getParameter("PROP"));
			}
			 msgList.send();
			 msgList.destroy();
			 flexLog("EDP075001 Message Sent");
		 } catch (Exception e) {
			 e.printStackTrace();
			 flexLog("Error: " + e);
			 throw new RuntimeException("Socket Communication Error");
		 }

		 // Receive Error
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

			 if (newmessage.getFormatName().equals("EDP075001")) {

				 JBObjList beanList = new JBObjList();

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
 				beanList.setNoResult(true);
				 int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					 msgList = (EDP075001Message) newmessage;

					 marker = msgList.getE01OPECDE();

					 if (firstTime) {
						 firstTime = false;
						 chk = "checked";

					 } else {
						beanList.setNoResult(false);

						 chk = "";
					 }

					 if (marker.equals("*")) {
						 beanList.setShowNext(false);
						 break;
					 } else {

						 beanList.addRow(msgList);

						 if (marker.equals("+")) {
							 beanList.setShowNext(true);

							 break;
						 }
					 }
					 newmessage = mc.receiveMessage();
				 }

				 flexLog("Putting java beans into the session");
				 ses.setAttribute("EDP075001Help", beanList);
				 ses.setAttribute("userPO", userPO);

				 try {

						 if (msgError.getERRNUM().equals("0")) {
							 flexLog(
								 "About to call Page: "
									 + LangPath
									 + "EDP0750_trace_list2.jsp");
							 callPage(
								 LangPath + "EDP0750_trace_list2.jsp",
								 req,
								 res);
						 } else {
							 flexLog(
								 "About to call Page: "
									 + LangPath
									 + "EDP0750_proposals_list.jsp");

							 callPage(
								 LangPath + "EDP0750_proposals_list.jsp",
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



	/************************************************************************/
	/************************************************************************/
	/************************************************************************/
	//manejo mantenimiento de cuentas por formato
	protected void procActionPos(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

//
  	UserPos userPO = null;
	boolean IsNotError = false;
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	//Carga dropdownlist para la consulta de la tabla...........................
	MessageRecord newmessage = null;

	ELEERRMessage msgError = null;
	JBList beanLP4 = null;
    String myFlagP4 = "";
    String myOptionP4 = "";
    EDP075001Message msgRuta = null;

   try {
	   beanLP4 = new datapro.eibs.beans.JBList();
   } catch (Exception ex) {
	   flexLog("Error: " + ex);
   }
   try {
	   msgRuta = new datapro.eibs.beans.EDP075001Message();
   } catch (Exception ex) {
   flexLog("Error: " + ex);
   }
   if(req.getParameter("RUT")!=null){
		  userPO.setHeader9(req.getParameter("RUT"));
	  }

   // Send Initial data for CNOFC tables. Audit Firm P4
   try {
	   flexLog("Send Initial Data");
	   msgRuta = (EDP075001Message) mc.getMessageRecord("EDP075001");
	   msgRuta.setH01USERID(user.getH01USR());
	   msgRuta.setH01PROGRM("EDP0750");
	   msgRuta.setH01TIMSYS(getTimeStamp());
	   msgRuta.setH01OPECOD("0015");
	   msgRuta.setH01SCRCOD("01");
	   msgRuta.setE01DPSNPR(userPO.getHeader9());
	   msgRuta.send();
	   msgRuta.destroy();
   } catch (Exception e) {
	   e.printStackTrace();
	   flexLog("Error: " + e);
	   throw new RuntimeException("Socket Communication Error");
   }

// Receive Error
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
//	   
//habilitar cuando la primera seleccion esta en blanco

	   if (newmessage.getFormatName().equals("EDP075001")) {

		   String marker = "";
		   int ct = 0;
			while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
				if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
				}

			   msgRuta = (EDP075001Message) newmessage;

			   marker = msgRuta.getE01OPECDE();

			   if (marker.equals("*")) {
				   break;
			   } else {
				   myOptionP4 =
					   "<option value=\""
						   + msgRuta.getE01DPSNPR()
						   + "\">"
							+ msgRuta.getE01DPSNPR()
							+ " - "
							+ msgRuta.getE01DPSNPR()
						   + "</option>";
				   beanLP4.addRow(myFlagP4, myOptionP4);
			   }
			   newmessage = mc.receiveMessage();
		   }
	   } else
		   flexLog("Message " + newmessage.getFormatName() + " received.");

	   // end read table

	   flexLog("Putting java beans into the session");
	   ses.setAttribute("error", msgError);
	   ses.setAttribute("optLP4", beanLP4);

   } catch (Exception e) {
	   e.printStackTrace();
	   flexLog("Error: " + e);
	   throw new RuntimeException("Socket Communication Error");
   }

	//FIN CARGUE DROPDL.................................................

//	MessageRecord newmessage = null;
//	ELEERRMessage msgError = null;
	
	int inptOPT = 0;

	inptOPT = Integer.parseInt(req.getParameter("opt"));

	//crear variable para que se pueda leer en las pantallas			
	userPO.setHeader16(req.getParameter("opt"));

	switch (inptOPT) {
		case 1 : //new
			userPO.setHeader17("0");
			userPO.setHeader18("0");
			//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=300");
			procReqNew(mc, user, req, res, ses);
			break;
		case 2 : //Maintenance
			userPO.setHeader17("1");
			userPO.setHeader18("0");
			procReqMaintenance(mc, user, req, res, ses);
			break;
		case 3 : //Delete
			userPO.setHeader17("1");
			userPO.setHeader18("1");
			procReqDelete(mc, user, req, res, ses);
			break;
		case 5 : //Consulta
			userPO.setHeader17("1");
			userPO.setHeader18("1");
			procReqConsulta(mc, user, req, res, ses);
			break;
		default :
		         //Consulta
			res.sendRedirect(
				super.srctx
					+ "/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=500") ;
			break;
	}
}

	/*******************************************************************************************/
	/*******************************************************************************************/
	/*******************************************************************************************/
	protected void procReqListAccDelete99 (
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

			MessageRecord newmessage = null;
			EDP075001Message msgList = null;
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
			
			//if(req.getParameter("FMT")!=null){
			//			userPO.setHeader9(req.getParameter("FMT"));
			//		}
			//if(req.getParameter("DSC")!=null){
			//			userPO.setHeader10(req.getParameter("DSC"));
			//		}


			// Send Initial data
			
			try {
				msgList = (EDP075001Message) mc.getMessageRecord("EDP075001");
				msgList.setH01USERID(user.getH01USR());
				msgList.setH01PROGRM("JSEDP0750");
				msgList.setH01TIMSYS(getTimeStamp());
				msgList.setH01SCRCOD("01");
				msgList.setH01OPECOD("0003");
//				msgList.setE01DPIDSC(userPO.getHeader10());
//				msgList.setE01DPIIND(userPO.getHeader9());
				msgList.send();
				msgList.destroy();
				flexLog("EDP075001 Message Sent");
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

					// showERROR(msgError);
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

				if (newmessage.getFormatName().equals("EDP075001")) {

					JBObjList beanList = new JBObjList();

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
					int ct = 0;
					while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
							System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
						}

						msgList = (EDP075001Message) newmessage;

						marker = msgList.getE01OPECDE();

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

							beanList.addRow(msgList);

							if (marker.equals("+")) {
								beanList.setShowNext(true);

								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("EDP075001Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0750_trace_list2.jsp");
						callPage(
							LangPath + "EDP0710_trace_list2.jsp",
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
				throw new RuntimeException("Socket Communication Data Receiving");
			}

		}		
	/********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/
	
	protected void procReqListAccDelete88(

	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDP075001Message msgRT = null;
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

	// Send Initial data
	try {
		
		flexLog("Send Initial Data");
		msgRT = (EDP075001Message) ses.getAttribute("brnDetails");
		msgRT.setH01USERID(user.getH01USR());
		msgRT.setH01PROGRM("DP0750");
		msgRT.setH01TIMSYS(getTimeStamp());
		msgRT.setH01SCRCOD("01");
		msgRT.setH01OPECOD("0003");
		msgRT.setH01FLGWK1("N");

		// all the fields here
		java.util.Enumeration enu = msgRT.fieldEnumeration();
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

		mc.sendMessage(msgRT);
		msgRT.destroy();
		flexLog("EDP075001 Message Sent");
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

		if (newmessage.getFormatName().equals("EDP075001")) {
			try {
				msgRT = new datapro.eibs.beans.EDP075001Message();
				flexLog("EDP075001 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgRT = (EDP075001Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("brnDetails", msgRT);
			ses.setAttribute("userPO", userPO);

			//
				if (IsNotError) { // There are no errors
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=200");
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0750_trace_list1.jsp");
						callPage(
							LangPath + "EDP0750_trace_list1.jsp",
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

/********************************************************************************************/
/********************************************************************************************/
/********************************************************************************************/


   protected void procReqNew(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EDP075001Message msgRT = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("NEW");
	ses.setAttribute("userPO", userPO);

	msgRT = new datapro.eibs.beans.EDP075001Message();
	
	ses.setAttribute("brnDetails", msgRT);
	try {
		flexLog(
			"About to call Page: "
				+ LangPath
				+ "EDP0750_trace_maintenance.jsp");
		callPage(LangPath + "EDP0750_trace_maintenance.jsp", req, res);
	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

protected void procActionDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP075001Message msgRT = null;
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

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDP075001Message) ses.getAttribute("brnDetails");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EGL036001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0003");
			msgRT.setH01FLGWK1("N");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDP075001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP075001")) {
				msgRT = (EDP075001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0750_trace_maintenance.jsp");
							callPage(
								LangPath + "EDP0750_trace_maintenance.jsp",
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


protected void procActionInsert(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP075001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDP075001Message) ses.getAttribute("brnDetails");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EGL036001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0001");
			msgRT.setH01FLGWK1("N");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDP075001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP075001")) {
				try {
					msgRT = new datapro.eibs.beans.EDP075001Message();
					flexLog("EDP075001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDP075001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0750_trace_maintenance.jsp");
							callPage(
								LangPath + "EDP0750_trace_maintenance.jsp",
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

	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP075001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		//
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		int inptOPT = 0;
		inptOPT = Integer.parseInt(req.getParameter("opt"));
		//
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDP075001Message) ses.getAttribute("brnDetails");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EGL036001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");
			msgRT.setH01FLGWK1("N");
			msgRT.setH01FLGWK3(req.getParameter("opt"));



			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDP075001 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP075001")) {
				try {
					msgRT = new datapro.eibs.beans.EDP075001Message();
					flexLog("EDP075001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDP075001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=300");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0750_trace_maintenance.jsp");
							callPage(
								LangPath + "EDP0750_trace_maintenance.jsp",
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


	protected void procReqMaintenance(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	EDP075001Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("MAINTENANCE");

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP075001Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP075001Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0750_trace_maintenance.jsp");
				callPage(
					LangPath + "EDP0750_trace_maintenance.jsp",
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

	protected void procReqDelete(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	EDP075001Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("DELETE");

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP075001Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP075001Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0750_trace_maintenance.jsp");
				callPage(
					LangPath + "EDP0750_trace_maintenance.jsp",
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

protected void procReqConsulta(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	EDP075001Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("CONSULTA");

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP075001Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP075001Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0750_trace_maintenance.jsp");
				callPage(
					LangPath + "EDP0750_trace_maintenance.jsp",
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
						// First screen, only list data
						case R_FILTER :
							procReqFilter(mc, msgUser, req, res, session);
							break;
						// Second screen, only list data
						case A_ENTER :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						// third screen, list data customers
						case R_LIST_2 :
							procReqListPaths2(mc, msgUser, req, res, session);
							break;
						case SCREEN400 :
							procAction400(mc, msgUser, req, res, session);
							break;
						case R_NEW :
							 procReqNew(mc, msgUser, req, res, session);							
							break;
						case R_DELETE :
							procActionPos(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_POSITION :
							procActionPos(mc, msgUser, req, res, session);
							break;
						case A_MAINTENANCE :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_MAINTENANCE2 :
							procActionDelete(
								mc,
								msgUser,
								req,
								res,
								session);
							break;						
							case A_INSERT :
							  	procActionInsert(
						  		mc,
						  		msgUser,
						  		req,
						  		res,
						  		session);
							  	break;

							// END Entering

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
