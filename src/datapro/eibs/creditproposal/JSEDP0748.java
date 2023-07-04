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

public class JSEDP0748 extends datapro.eibs.master.SuperServlet {

	// Action 
	// first screen
	protected static final int R_BRANCH_LIST = 100;
	//	selecciona nuevo, o modificar registro seleccionado, o delete
	 protected static final int A_POSITION = 800;
	// second screen
	protected static final int R_BRANCH_LIST_ACC = 200;
	// viene seleccionado el formato a trabajar	y mostrar cuentas del formato
	protected static final int SCREEN400 = 400;
	//	llamar screen para actualizar registro
 	protected static final int A_MAINTENANCE = 600;
	//	llamar screen para actualizar registro	
	protected static final int A_MAINTENANCE2 = 630;
	// llamar a procedimiento para crear resistro 
	protected static final int A_INSERT  = 650;
	
	// llamar screen para nuevo registro
	protected static final int R_NEW = 300;
	
	protected static final int R_ENTER = 1;
	protected static final int R_MAINTENANCE = 500;
	protected static final int R_DELETE = 700;

	protected String LangPath = "s";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDP0748() {
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
	
	
	// first screen list 
	protected void procReqListPaths1(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP074801Message msgList = null;
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


//take out				
		if(req.getParameter("CACTI")!=null){
			   userPO.setHeader11(req.getParameter("CACTI"));
			}
//take out
		if(req.getParameter("DACTI")!=null){
				   userPO.setHeader12(req.getParameter("DACTI"));
		   }
					
		// Send Initial data
		try {
			msgList = (EDP074801Message) mc.getMessageRecord("EDP074801");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ELD010003");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
//call 400
			msgList.send();
			msgList.destroy();
			flexLog("EDP074801 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP074801")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				//String myFlag = "";
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

					msgList = (EDP074801Message) newmessage;

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
				ses.setAttribute("EDP074801Help", beanList);
				ses.setAttribute("userPO", userPO);
			
			
//				BEGIN TABLE CN48
				/*---------------------------------------------------------------*/ 
				// Carga dropdownlist para la consulta de la tabla cnofc48
				/*---------------------------------------------------------------*/
				JBList beanCN48 = null;
				String myFlagCN48 = "";
				String myOptionCN48 = "";
   			    String mensaje_error = "";
				EDP073601Message msgCnofc = null;

				try {
					beanCN48 = new datapro.eibs.beans.JBList();
					msgCnofc = new datapro.eibs.beans.EDP073601Message();
				} catch (Exception ex) {
				flexLog(mensaje_error+ ex);	
				//flexLog("Error: " + ex);
				}
				/*....................................................*/
				// Send Initial data for CNOFC tables. (Path) 
				try {
					flexLog("Send Initial Data");
					msgCnofc = (EDP073601Message) mc.getMessageRecord("EDP073601");
					msgCnofc.setH01USERID(user.getH01USR());
					msgCnofc.setH01PROGRM("EDP0736");
					msgCnofc.setH01TIMSYS(getTimeStamp());
					msgCnofc.setH01OPECOD("0015");
					msgCnofc.setE01CNOCFL("48");
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
					String myFlag = "";
					newmessage = mc.receiveMessage();
				//habilitar cuando la primera seleccion esta en blanco
					 myOptionCN48 = "<option value=\"\"></option>";
					 beanCN48.addRow(myFlag, myOptionCN48);

					if (newmessage.getFormatName().equals("EDP073601")) {

						String markerCN = "";
						int ct1 = 0;
						while (ct1++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
							if (ct1 >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
								System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
							}

							msgCnofc = (EDP073601Message) newmessage;

							markerCN = msgCnofc.getH01FLGMAS();

							if (markerCN.equals("*")) {
			   	
								break;
							} else {
								myOptionCN48 =
									"<option value=\""
										+ msgCnofc.getE01CNORCD()
										+ "\">"
										+ msgCnofc.getE01CNORCD()
										+ " - "
										+ msgCnofc.getE01CNODSC()
										+ "</option>";
								beanCN48.addRow(myFlagCN48, myOptionCN48);
							}
							newmessage = mc.receiveMessage();
						}

					} else
						flexLog("Message " + newmessage.getFormatName() + " received.");

				 flexLog("Putting java beans into the session");
				 ses.setAttribute("error", msgError);
				 ses.setAttribute("optCN48", beanCN48);

				} catch (Exception ex) {
					ex.printStackTrace();
					flexLog("Error: " + ex);
					throw new RuntimeException("Socket Communication Error Cnofc");

				}
//	   END TABLE CN48


//		 BEGIN TABLE CN_PP
		 /*---------------------------------------------------------------*/ 
		 // Carga dropdownlist para la consulta de la tabla cnofc_PP
		 /*---------------------------------------------------------------*/
		 JBList beanCN_PP = null;
		 String myFlagCN_PP = "";
		 String myOptionCN_PP = "";
//		 String mensaje_error = "";
		 mensaje_error = "";
//		 EDP073601Message msgCnofc = null;
		 msgCnofc = null;

		 try {
			 beanCN_PP = new datapro.eibs.beans.JBList();
			 msgCnofc = new datapro.eibs.beans.EDP073601Message();
		 } catch (Exception ex) {
		 flexLog(mensaje_error+ ex);	
		 //flexLog("Error: " + ex);
		 }
		 /*....................................................*/
		 // Send Initial data for CNOFC tables. (Path) 
		 try {
			 flexLog("Send Initial Data");
			 msgCnofc = (EDP073601Message) mc.getMessageRecord("EDP073601");
			 msgCnofc.setH01USERID(user.getH01USR());
			 msgCnofc.setH01PROGRM("EDP0736");
			 msgCnofc.setH01TIMSYS(getTimeStamp());
			 msgCnofc.setH01OPECOD("0015");
			 msgCnofc.setE01CNOCFL("PP");
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
			 String myFlag = "";
			 newmessage = mc.receiveMessage();
		 //habilitar cuando la primera seleccion esta en blanco
			  myOptionCN_PP = "<option value=\"\"></option>";
			  beanCN_PP.addRow(myFlag, myOptionCN_PP);

			 if (newmessage.getFormatName().equals("EDP073601")) {

				 String markerCN = "";
				 int ct1 = 0;
				while (ct1++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct1 >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					 msgCnofc = (EDP073601Message) newmessage;

					 markerCN = msgCnofc.getH01FLGMAS();

					 if (markerCN.equals("*")) {
			   	
						 break;
					 } else {
						 myOptionCN_PP =
							 "<option value=\""
								 + msgCnofc.getE01CNORCD()
								 + "\">"
								 + msgCnofc.getE01CNORCD()
								 + " - "
								 + msgCnofc.getE01CNODSC()
								 + "</option>";
						 beanCN_PP.addRow(myFlagCN_PP, myOptionCN_PP);
					 }
					 newmessage = mc.receiveMessage();
				 }

			 } else
				 flexLog("Message " + newmessage.getFormatName() + " received.");

		  flexLog("Putting java beans into the session");
		  ses.setAttribute("error", msgError);
		  ses.setAttribute("optCN_PP", beanCN_PP);

		 } catch (Exception ex) {
			 ex.printStackTrace();
			 flexLog("Error: " + ex);
			 throw new RuntimeException("Socket Communication Error Cnofc");

		 }
//END TABLE CN_PP


//DDLBegin ===

  				  /*---------------------------------------------------*/
				  // Send Initial data for DDL Producto
				  /*---------------------------------------------------*/				
				  EDP074301Message msgList3 = null; //Productos
				  JBList beanList3 = null;
				  String myOption = "";
//				  String mensaje_error = "";
				  String myFlag = "";
				  EDP074801Message msgFnl = null; //PGM aqui 

					  try {
						  mensaje_error = "Error: ";
						  msgError = new datapro.eibs.beans.ELEERRMessage();
						  //beanList1 = new datapro.eibs.beans.JBList();
						  //beanList2 = new datapro.eibs.beans.JBList();
						  beanList3 = new datapro.eibs.beans.JBList();
						  //msgList1 = new datapro.eibs.beans.EDP074101Message(); //DDL1
						  //msgList2 = new datapro.eibs.beans.EDP074201Message(); //DDL2
						  msgList3 = new datapro.eibs.beans.EDP074301Message(); //DDL3
						  msgFnl = new datapro.eibs.beans.EDP074801Message(); //PGM
					  } catch (Exception ex) {
						  //flexLog("Error: " + ex);
						  flexLog(mensaje_error + ex);
					  }
			
			
			
				  try {
					  flexLog("Send Initial Data Productos");
					  msgList3 = (EDP074301Message) mc.getMessageRecord("EDP074301");

					  msgList3.setH01USERID(user.getH01USR());
					  msgList3.setH01PROGRM("EDP0743");
					  msgList3.setH01TIMSYS(getTimeStamp());
					  msgList3.setH01OPECOD("0015");
					  msgList3.setH01FLGWK1("T");
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
					  //      beanList3.addRow(myFlag, myOption);
					  if (newmessage.getFormatName().equals("EDP074301")) {

						   marker = "";
						  int ct1 = 0;
						while (ct1++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
							if (ct1 >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
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
					  //ses.setAttribute("clientFnl", msgFnl); /*OJO*/
					  ses.setAttribute("optList3", beanList3);
					  //ses.setAttribute("optList4", beanList3);
					  ses.setAttribute("error", msgError);

				  } catch (Exception ex) {
					  ex.printStackTrace();
					  //flexLog("Error: " + e);
					  flexLog(mensaje_error + ex);
					  throw new RuntimeException("Socket Communication Error Producto");
				  }
				
				
				  /*---------------------------------------------------------------*/

//DDLEnd ===

				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0748_product_path_list1.jsp");
							callPage(
								LangPath + "EDP0748_product_path_list1.jsp",
								req,
								res);
						} else {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0748_product_path_list1.jsp");

							callPage(
								LangPath + "EDP0748_product_path_list1.jsp",
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

	 int inptOPT = 1;

	 //inptOPT = Integer.parseInt(req.getParameter("opt"));

	 switch (inptOPT) {
		 case 1 : //List accounts by format
		
		 procReqListPaths1(mc, user, req, res, ses);
		
		 default :
			 res.sendRedirect(
				 super.srctx
					 + "/servlet/datapro.eibs.creditproposer.JSEDP0748?SCREEN=800") ;
			 break;
		 }
	 }


	//LISTA INICIAL DE FORMATOS PARA SELECCIONAR
	protected void procReqEnterBranch(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
	MessageRecord newmessage = null;
	EDP074801Message msgList = null;
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
		msgList = (EDP074801Message) mc.getMessageRecord("EDP070201");
		msgList.setH01USERID(user.getH01USR());
		msgList.setH01PROGRM("ELD010003");
		msgList.setH01TIMSYS(getTimeStamp());
		msgList.setH01SCRCOD("01");
		msgList.setH01OPECOD("0015");
		msgList.send();
		msgList.destroy();
		flexLog("EDP070201 Message Sent");
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

		if (newmessage.getFormatName().equals("EDP070201")) {

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

				msgList = (EDP074801Message) newmessage;

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
			ses.setAttribute("EDP070201Help", beanList);
			ses.setAttribute("userPO", userPO);

			try {

					if (msgError.getERRNUM().equals("0")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0703_financial_list.jsp");
						callPage(
							LangPath + "EDP0703_financial_list.jsp",
							req,
							res);
					} else {
//						flexLog(
//							"About to call Page: "
//								+ LangPath
//								+ "EDP0703_financial_list_enter.jsp");
//
//						callPage(
//							LangPath + "EDP0703_financial_list_enter.jsp",
//							req,
//							res);

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
			//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.creditproposer.JSEDP0748?SCREEN=300");
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
					+ "/servlet/datapro.eibs.creditproposer.JSEDP0748?SCREEN=500") ;
			break;
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
	EDP074801Message msgRT = null;
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

	msgRT = new datapro.eibs.beans.EDP074801Message();
	
	ses.setAttribute("brnDetails", msgRT);
	try {
		flexLog(
			"About to call Page: "
				+ LangPath
				+ "EDP0748_product_path_maintenance.jsp");
		callPage(LangPath + "EDP0748_product_path_maintenance.jsp", req, res);
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
		EDP074801Message msgRT = null;
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
			msgRT = (EDP074801Message) ses.getAttribute("brnDetails");
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
			flexLog("EDP074801 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP074801")) {
				try {
					msgRT = new datapro.eibs.beans.EDP074801Message();
					flexLog("EDP074801 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDP074801Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0748?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0748_product_path_maintenance.jsp");
							callPage(
								LangPath + "EDP0748_product_path_maintenance.jsp",
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
		EDP074801Message msgRT = null;
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
			msgRT = (EDP074801Message) ses.getAttribute("brnDetails");
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
			flexLog("EDP074801 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP074801")) {
				try {
					msgRT = new datapro.eibs.beans.EDP074801Message();
					flexLog("EDP074801 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDP074801Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposer.JSEDP0748?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0748_product_path_maintenance.jsp");
							callPage(
								LangPath + "EDP0748_product_path_maintenance.jsp",
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
		EDP074801Message msgRT = null;
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
			msgRT = (EDP074801Message) ses.getAttribute("brnDetails");
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
			flexLog("EDP074801 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP074801")) {
				try {
					msgRT = new datapro.eibs.beans.EDP074801Message();
					flexLog("EDP074801 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDP074801Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0748?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0748_product_path_maintenance.jsp");
							callPage(
								LangPath + "EDP0748_product_path_maintenance.jsp",
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

	EDP074801Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("MAINTENANCE");

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP074801Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP074801Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0748_product_path_maintenance.jsp");
				callPage(
					LangPath + "EDP0748_product_path_maintenance.jsp",
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

	EDP074801Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("DELETE");

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP074801Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP074801Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0748_product_path_maintenance.jsp");
				callPage(
					LangPath + "EDP0748_product_path_maintenance.jsp",
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

	EDP074801Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("CONSULTA");

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP074801Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP074801Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0748_product_path_maintenance.jsp");
				callPage(
					LangPath + "EDP0748_product_path_maintenance.jsp",
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
						case R_BRANCH_LIST :
							procReqListPaths1(mc, msgUser, req, res, session);
							break;
						// Second screen, only list data
						case R_BRANCH_LIST_ACC :
						procReqListPaths1(mc, msgUser, req, res, session);
							break;
						case SCREEN400 :
							procAction400(mc, msgUser, req, res, session);
							break;
						case R_ENTER :
							procReqEnterBranch(mc, msgUser, req, res, session);
							break;
							//ok
						case R_NEW :
							 procReqNew(mc, msgUser, req, res, session);							
							break;
							//ok								
						//case R_MAINTENANCE :
						//	procReqMaintenance(mc, msgUser, req, res, session);
						//	break;
						case R_DELETE :
							procActionPos(
								mc,
								msgUser,
								req,
								res,
								session);
						//	break;
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
