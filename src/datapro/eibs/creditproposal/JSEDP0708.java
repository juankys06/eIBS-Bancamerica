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

public class JSEDP0708 extends datapro.eibs.master.SuperServlet {

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
	public JSEDP0708() {
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
	protected void procReqListPaths1(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP073501Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		//if (req.getParameter("E01DPFDSC") != null) {
		//	userPO.setHeader10(req.getParameter("E01DPFDSC"));
		//}
		
		// Send Initial data
		try {
			msgList = (EDP073501Message) mc.getMessageRecord("EDP073501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ELD010003");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
//			msgList.setH01FLGWK3("OJO");
//			msgList.setE01DPFFMT(userPO.getHeader9());
			msgList.setE01CNOCFL("48");
			//setE01DPFDSC(userPO.getHeader10());
			msgList.send();
			msgList.destroy();
			flexLog("EDP073501 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP073501")) {

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

					msgList = (EDP073501Message) newmessage;

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
				ses.setAttribute("EDP073501Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0708_path_list1.jsp");
							callPage(
								LangPath + "EDP0708_path_list1.jsp",
								req,
								res);
						} else {
//                 By error
//							flexLog(
//								"About to call Page: "
//									+ LangPath
//									+ "EDP0702_changeprod_enter.jsp");
//
//							callPage(
//								LangPath + "EDP0702_changeprod_enter.jsp",
//								req,
//								res);

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
			 break;
		
		 default :
			 res.sendRedirect(
				 super.srctx
					 + "/servlet/datapro.eibs.creditproposal.JSEDP0708?SCREEN=800") ;
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
		 EDP070801Message msgList = null;
		 ELEERRMessage msgError = null;
		 UserPos userPO = null;
		 boolean IsNotError = false;

		 userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		 // asigna variables para trabajar con cuentas del formato seleccionado

		 if(req.getParameter("RUT")!=null){
				userPO.setHeader9(req.getParameter("RUT"));
			}
		if(req.getParameter("DSC")!=null){
				   userPO.setHeader10(req.getParameter("DSC"));
				}
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
			 msgList = (EDP070801Message) mc.getMessageRecord("EDP070801");
			 msgList.setH01USERID(user.getH01USR());
			 msgList.setH01PROGRM("EDP0708");
			 msgList.setH01TIMSYS(getTimeStamp());
			 msgList.setH01SCRCOD("01");
			 msgList.setH01OPECOD("0015");
			 msgList.setE01DPPRUT(userPO.getHeader9());
//cal 400
			 msgList.send();
			 msgList.destroy();
			 flexLog("EDP070801 Message Sent");
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

			 if (newmessage.getFormatName().equals("EDP070801")) {

				 JBObjList beanList = new JBObjList();

				 boolean firstTime = true;
				 String marker = "";

 				beanList.setNoResult(true);
				 int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					 msgList = (EDP070801Message) newmessage;

					 marker = msgList.getE01OPECDE();

					 if (firstTime) {
						 firstTime = false;
					 } else {
						beanList.setNoResult(false);
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
				 ses.setAttribute("EDP070801Help", beanList);
				 ses.setAttribute("userPO", userPO);

				 try {

						 if (msgError.getERRNUM().equals("0")) {
							 flexLog(
								 "About to call Page: "
									 + LangPath
									 + "EDP0708_path_list2.jsp");
							 callPage(
								 LangPath + "EDP0708_path_list2.jsp",
								 req,
								 res);
						 } else {
							 flexLog(
								 "About to call Page: "
									 + LangPath
									 + "EDP0708_path_list_enter.jsp");

							 callPage(
								 LangPath + "EDP0708_path_list_enter.jsp",
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



	//LISTA INICIAL DE FORMATOS PARA SELECCIONAR
	protected void procReqEnterBranch(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
	MessageRecord newmessage = null;
	EDP070201Message msgList = null;
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
		msgList = (EDP070201Message) mc.getMessageRecord("EDP070201");
		msgList.setH01USERID(user.getH01USR());
		msgList.setH01PROGRM("ELD010003");
		msgList.setH01TIMSYS(getTimeStamp());
		msgList.setH01SCRCOD("01");
		msgList.setH01OPECOD("0015");
		msgList.setE01DPFFMT(userPO.getHeader9());
		msgList.setE01DPFDSC(userPO.getHeader10());
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

				msgList = (EDP070201Message) newmessage;

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
								+ "EDP0703_branch_list.jsp");
						callPage(
							LangPath + "EDP0703_branch_list.jsp",
							req,
							res);
					} else {
//						flexLog(
//							"About to call Page: "
//								+ LangPath
//								+ "EDP0703_branch_list_enter.jsp");
//
//						callPage(
//							LangPath + "EDP0703_branch_list_enter.jsp",
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

	MessageRecord newmessage = null;

	ELEERRMessage msgError = null;
	JBList beanLP4 = null;
    String myFlagP4 = "";
    String myOptionP4 = "";
    EDP070801Message msgRuta = null;

   beanLP4 = new datapro.eibs.beans.JBList();
   if(req.getParameter("RUT")!=null){
		  userPO.setHeader9(req.getParameter("RUT"));
	  }
	  
   // Send Initial data for CNOFC tables. Audit Firm P4
   try {
	   flexLog("Send Initial Data");
	   msgRuta = (EDP070801Message) mc.getMessageRecord("EDP070801");
	   msgRuta.setH01USERID(user.getH01USR());
	   msgRuta.setH01PROGRM("EDP0708");
	   msgRuta.setH01TIMSYS(getTimeStamp());
	   msgRuta.setH01OPECOD("0015");
	   msgRuta.setH01SCRCOD("01");
	   msgRuta.setE01DPPRUT(userPO.getHeader9());
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
//	   
//habilitar cuando la primera seleccion esta en blanco
	   if (newmessage.getFormatName().equals("EDP070801")) {
		   String marker = "";
		   boolean firstTime = true;
		   
		   int ct = 0;
			while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
				if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
				}

			   msgRuta = (EDP070801Message) newmessage;

			   marker = msgRuta.getE01OPECDE();
			   
			   if (marker.equals("*")) {
				   break;
			   } else {
				   myOptionP4 =
					   "<option value=\""
						   + msgRuta.getE01DPPSEC()
						   + "\">"
							+ msgRuta.getE01DPPSEC()
							+ " - "
							+ msgRuta.getE01DPPACD()
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
			//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.creditproposal.JSEDP0708?SCREEN=300");
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
					+ "/servlet/datapro.eibs.creditproposal.JSEDP0708?SCREEN=500") ;
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
			EDP070801Message msgList = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			
			// Send Initial data			
			try {
				msgList = (EDP070801Message) mc.getMessageRecord("EDP070801");
				msgList.setH01USERID(user.getH01USR());
				msgList.setH01PROGRM("JSEDP0708");
				msgList.setH01TIMSYS(getTimeStamp());
				msgList.setH01SCRCOD("01");
				msgList.setH01OPECOD("0003");
//				msgList.setE01DPIDSC(userPO.getHeader10());
//				msgList.setE01DPIIND(userPO.getHeader9());
				msgList.send();
				msgList.destroy();
				flexLog("EDP070801 Message Sent");
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

				if (newmessage.getFormatName().equals("EDP070801")) {

					JBObjList beanList = new JBObjList();

					boolean firstTime = true;
					String marker = "";

					int ct = 0;
					while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
							System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
						}

						msgList = (EDP070801Message) newmessage;

						marker = msgList.getE01OPECDE();

						if (firstTime) {
							firstTime = false;
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
					ses.setAttribute("EDP070801Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0708_path_list2.jsp");
						callPage(
							LangPath + "EDP0710_path_list2.jsp",
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
	EDP070801Message msgRT = null;
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
		msgRT = (EDP070801Message) ses.getAttribute("brnDetails");
		msgRT.setH01USERID(user.getH01USR());
		msgRT.setH01PROGRM("DP0708");
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
		flexLog("EDP070801 Message Sent");
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

		if (newmessage.getFormatName().equals("EDP070801")) {
			try {
				msgRT = new datapro.eibs.beans.EDP070801Message();
				flexLog("EDP070801 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgRT = (EDP070801Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("brnDetails", msgRT);
			ses.setAttribute("userPO", userPO);

			//
				if (IsNotError) { // There are no errors
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.creditproposal.JSEDP0708?SCREEN=200");
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0708_path_list1.jsp");
						callPage(
							LangPath + "EDP0708_path_list1.jsp",
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
	EDP070801Message msgRT = null;
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

	msgRT = new datapro.eibs.beans.EDP070801Message();
	
//	msgRT.setE01DPXCFO(userPO.getHeader9());
//	msgRT.setE01DPXCLI(userPO.getHeader11());
//	msgRT.setE01DPXGLN(userPO.getHeader12());
	
	ses.setAttribute("brnDetails", msgRT);
	try {
		flexLog(
			"About to call Page: "
				+ LangPath
				+ "EDP0708_path_maintenance.jsp");
		callPage(LangPath + "EDP0708_path_maintenance.jsp", req, res);
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
		EDP070801Message msgRT = null;
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
			msgRT = (EDP070801Message) ses.getAttribute("brnDetails");
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
			flexLog("EDP070801 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP070801")) {
				try {
					msgRT = new datapro.eibs.beans.EDP070801Message();
					flexLog("EDP070801 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDP070801Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0708?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0708_path_maintenance.jsp");
							callPage(
								LangPath + "EDP0708_path_maintenance.jsp",
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
		EDP070801Message msgRT = null;
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
			msgRT = (EDP070801Message) ses.getAttribute("brnDetails");
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
			flexLog("EDP070801 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP070801")) {
				try {
					msgRT = new datapro.eibs.beans.EDP070801Message();
					flexLog("EDP070801 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDP070801Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0708?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0708_path_maintenance.jsp");
							callPage(
								LangPath + "EDP0708_path_maintenance.jsp",
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
		EDP070801Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		//
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		int inptOPT = 0;
		inptOPT = Integer.parseInt(req.getParameter("opt"));
		//
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDP070801Message) ses.getAttribute("brnDetails");
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
			flexLog("EDP070801 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP070801")) {
				msgRT = (EDP070801Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
					if (IsNotError) { // There are no errors
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.creditproposal.JSEDP0708?SCREEN=200");
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0708_path_maintenance.jsp");
							callPage(
								LangPath + "EDP0708_path_maintenance.jsp",
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

	EDP070801Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("MAINTENANCE");

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP070801Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP070801Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0708_path_maintenance.jsp");
				callPage(
					LangPath + "EDP0708_path_maintenance.jsp",
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

	EDP070801Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("DELETE");

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP070801Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP070801Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0708_path_maintenance.jsp");
				callPage(
					LangPath + "EDP0708_path_maintenance.jsp",
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

	EDP070801Message msgDoc = null;
	UserPos userPO = null;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	userPO.setPurpose("CONSULTA");

	// Receive Data
	try {
		JBObjList bl = (JBObjList) ses.getAttribute("EDP070801Help");
		int idx = Integer.parseInt(req.getParameter("CURRCODE2"));
		bl.setCurrentRow(idx);

		msgDoc = (EDP070801Message) bl.getRecord();

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("brnDetails", msgDoc);

			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0708_path_maintenance.jsp");
				callPage(
					LangPath + "EDP0708_path_maintenance.jsp",
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
							procReqListPaths2(mc, msgUser, req, res, session);
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
