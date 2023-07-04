package datapro.eibs.creditproposal;

/**
 * Insert the type's description here.
 * Creation date: (04/25/05 6:08:55 PM)
 * @author: John Andrade
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

public class JSEDP0720 extends datapro.eibs.master.SuperServlet {

	// entering options
	protected static final int R_FILTER = 100; //
	protected static final int R_LIST = 200; //

	protected static final int R_ENTER_NEW_PROPOSAL = 301;
	protected static final int R_NEW_PROPOSAL = 302;

	protected static final int R_HEADER = 300;
	protected static final int R_SAVE_HEADER = 350;

	protected static final int R_DETAIL_PRODUCT = 400;
	protected static final int RNEWPROD = 410; //
	protected static final int RDETPROD = 420; //

	protected static final int RSAVEPROD = 460;
	protected static final int RFMTHEADER = 470;

	protected static final int R_NEXT_ACTIVITY = 500;
	protected static final int RPRIORACTIV = 540; //
	protected static final int R_SAVE_NEXT_ACTIVITY = 600; //

	protected static final int RUPDGUAR = 710; //
	protected static final int RETCUSINF = 720; //
	protected static final int PRINTPROP = 730; //
	protected static final int RETPRDINF = 740; //

	protected static final int RLISTDOFA = 800; //
	protected static final int RACTDOFA = 810; //

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0720() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDP0720");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
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
		String DESINCORPORADAS = "";
		String RENOVAR = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if (req.getParameter("E01PLPBNK") != null) {
			userPO.setBank(req.getParameter("E01PLPBNK"));
		}
		//RETRIEVE PARAMETERS---------------------------------------------------------
		if (req.getParameter("E01FILPRD") != null) {
			userPO.setProdCode(req.getParameter("E01FILPRD"));
		}

		if (req.getParameter("BNK") != null) {
			userPO.setBank(req.getParameter("BNK"));
		}

		if (req.getParameter("E01FILBRN") != null) {
			userPO.setBranch(req.getParameter("E01FILBRN"));
		}

		if (req.getParameter("E01FILCUN") != null) {
			userPO.setCusNum(req.getParameter("E01FILCUN"));
		}
		if (req.getParameter("E01FILUSR") != null) {
			userPO.setID(req.getParameter("E01FILUSR"));
		}

		if (req.getParameter("E01FILRUT") != null) {
			userPO.setAccOpt(req.getParameter("E01FILRUT"));
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
			msgList.setE01PLPRUT(userPO.getHeader15());
			msgList.setE01FILCUN(userPO.getCusNum());
			msgList.setE01FILPRD(userPO.getProdCode());
			msgList.setE01FILTYP(userPO.getType());
			msgList.setE01FILBRN(userPO.getBranch());
			msgList.setE01PLPCN4(userPO.getAccOpt());
			msgList.setE01PLPUAP(userPO.getID());

			// These are filters
			if (req.getParameter("E01FILBRN") != null) {
				userPO.setBranch(req.getParameter("E01FILBRN"));
			}
			
			if (req.getParameter("E01FILNPR") != null) {
				msgList.setE01FILNPR(req.getParameter("E01FILNPR"));
			}
			//
			if (req.getParameter("E01FILCUN") != null) {
				msgList.setE01FILCUN(req.getParameter("E01FILCUN"));
			}
			//
			if (req.getParameter("E01FILEJE") != null) {
				msgList.setE01FILEJE(req.getParameter("E01FILEJE"));
			}
			if (req.getParameter("E01FILEST") != null) {
				msgList.setE01FILEST(req.getParameter("E01FILEST"));
			}
			if (req.getParameter("E01FILTYP") != null) {
				//msgList.setE01FILTYP(req.getParameter("E01FILTYP"));
			}
			//
			if (req.getParameter("E01FILPRD") != null) {
				msgList.setE01FILPRD(req.getParameter("E01FILPRD"));
			}
			//			
			if (req.getParameter("E01FILRUT") != null) {
				msgList.setE01PLPCN4(req.getParameter("E01FILRUT"));
			}
			if (req.getParameter("E01FILUSR") != null) {
				msgList.setE01PLPUAP(req.getParameter("E01FILUSR"));
			}
			if (req.getParameter("Pos") != null) {
				msgList.setE01RECPOS(req.getParameter("Pos"));
			}
			//
			if (req.getParameter("E01YYYFIL") != null) {
				msgList.setE01YYYFIL(req.getParameter("E01YYYFIL"));
				DESINCORPORADAS = req.getParameter("E01YYYFIL");
			}
			if (req.getParameter("E01XXXFIL") != null) {
				RENOVAR = req.getParameter("E01XXXFIL");
			}

			String SEL_OPTION = userPO.getOption();
			//============================================================
			//=================1===========================================
			if (SEL_OPTION.equals("1")) {
				msgList.setH01FLGWK2("1");
				if (DESINCORPORADAS.equals("0")) {
					msgList.setH01FLGWK2("1");
				}
				if (DESINCORPORADAS.equals("1")) {
					if (RENOVAR.equals("1")) {
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
				if (DESINCORPORADAS.equals("0")) {
					msgList.setH01FLGWK2("2");
				}
				if (DESINCORPORADAS.equals("1")) {

					if (RENOVAR.equals("1")) {
						msgList.setH01FLGWK2("5");
						userPO.setOption("5");
					} else {
						msgList.setH01FLGWK2("4");
						userPO.setOption("4");
					}
				}
			};
			if (SEL_OPTION.equals("5")) {
					msgList.setH01FLGWK2("5");
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
					}
					newmessage = mc.receiveMessage();
				}
				userPO.setPurpose("INQUIRY");

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP072001Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {

					if (msgError.getERRNUM().equals("0")) {

						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0720_proposals_list.jsp");
						callPage(
							LangPath + "EDP0720_proposals_list.jsp",
							req,
							res);
					} else {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0720_proposals_filter.jsp");

						callPage(
							LangPath + "EDP0720_proposals_filter.jsp",
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

		ELEERRMessage msgError = null;

		UserPos userPO = new UserPos();
		msgError = new ELEERRMessage();

		//RETRIEVE PARAMETERS - OPTION FOR PREPARE=1 OR PROCESS=2
		if (req.getParameter("OPTION") != null) {
			userPO.setOption(req.getParameter("OPTION"));
			int option = 0;
			option = Integer.parseInt(req.getParameter("OPTION"));
			if (option != 1) {
				userPO.setPurpose("INQUIRY");
			} else {
				userPO.setPurpose("MAINTENANCE");
			}

		}


		/*---------------------------------------------------*/
		// Send Initial data for DDL Producto
		/*---------------------------------------------------*/
			EDP074601Message msgList746 = null; //Productos
			JBList beanList746 = null;
			MessageRecord newmessage = null;

			msgError = new datapro.eibs.beans.ELEERRMessage();
			beanList746 = new datapro.eibs.beans.JBList();
			msgList746 = new datapro.eibs.beans.EDP074601Message(); //DDL3

		try {
			flexLog("Send Initial Data Productos");
			msgList746 = (EDP074601Message) mc.getMessageRecord("EDP074601");

			msgList746.setH01USERID(user.getH01USR());
			msgList746.setH01PROGRM("EDP0746");
			msgList746.setH01TIMSYS(getTimeStamp());
			msgList746.setH01OPECOD("0015");
			msgList746.send();
			msgList746.destroy();

		} catch (Exception ex) {
			ex.printStackTrace();
			flexLog("Error: " + ex);
			throw new RuntimeException("Socket Communication Error Productos");
		}
		/*.................................................*/
		// Receive Error Message
		try {
			String myOption = "";
			String myFlag = "";
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDP074601")) {
				myOption = "<option value=\"\"></option>";
				beanList746.addRow(myFlag, myOption);

				String marker = "";
				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					msgList746 = (EDP074601Message) newmessage;
					marker = msgList746.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						myOption =
							"<option value=\"" 	
							+ msgList746.getE01EUPUSR() + "\">"
								+ msgList746.getE01EUPUSR()
								+ " - " + msgList746.getE01EUPNME()
								+ "</option>";
						beanList746.addRow(myFlag, myOption);
					}
					newmessage = mc.receiveMessage();
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session Producto");
			ses.setAttribute("optList746", beanList746);

		} catch (Exception ex) {
			ex.printStackTrace();
			flexLog("Error: " + ex);
			throw new RuntimeException("Socket Communication Error Producto");
		}
		/*---------------------------------------------------------------*/


		ses.setAttribute("userPO", userPO);
		ses.setAttribute("error", msgError);
		/***********************************************/
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0720_proposals_filter.jsp");
			callPage(LangPath + "EDP0720_proposals_filter.jsp", req, res);
		} catch (Exception ex) {
			flexLog("Exception calling page " + ex);
		}
		/***********************************************/
	}

	/************************************************************************************************/
	/************************************************************************************************/
	/************************************************************************************************/
	protected void procReqPGM(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP072001Message msgRT = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("opt"));
		if (req.getParameter("opt") != null) {
			userPO.setHeader16(req.getParameter("opt"));
		}

		switch (inptOPT) {
			case 1 : // Req. Header

				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.creditproposal.JSEDP0720?SCREEN=310");
				break;

			default :
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.creditproposal.JSEDP0720?SCREEN=800");
				break;
		}
	}

	protected void procReqEnterNewProposal(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP072001Message msgRT = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		msgError = new ELEERRMessage();

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("msgRT", msgRT);

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0720_proposals_enter_maintenance.jsp");
			callPage(
				LangPath + "EDP0720_proposals_enter_maintenance.jsp",
				req,
				res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqNewProposal(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP072001Message msgRT = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		JBListRec gcodeList = new JBListRec();
		gcodeList.setNoResult(true);

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");

		userPO.setType(req.getParameter("E01PLPPTY"));
		userPO.setCusNum(req.getParameter("E01CUSCUN"));
		userPO.setHeader4(req.getParameter("E01PLPCN3"));

		// Send Initial data--------------------------------------------------------------
		try {
			msgRT = (EDP072001Message) mc.getMessageRecord("EDP072001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDP072001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0003");
			msgRT.setH01FLGWK3("");

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

			msgRT.send();
			msgRT.destroy();
			flexLog("EDP072001 Message Sent b");
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

			if (newmessage.getFormatName().equals("EDP072001")) {

				msgRT = (EDP072001Message) newmessage;
				userPO.setIdentifier(msgRT.getE01PLPNPR());

				if (req.getParameter("E01PLPCN2") != null) {
					msgRT.setE01PLPCN4(req.getParameter("E01PLPCN2"));
				}
				if (req.getParameter("E01PLPCN3") != null) {
					msgRT.setE01PLPCN3(req.getParameter("E01PLPCN3"));
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgRT", msgRT);
				ses.setAttribute("gaCodeList", gcodeList);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}
		
		if (IsNotError) {
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0720_proposals_new.jsp");
				callPage(LangPath + "EDP0720_proposals_new.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}				
		} else {
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0720_proposals_enter_maintenance.jsp");
				callPage(LangPath + "EDP0720_proposals_enter_maintenance.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}	
		
		}
	}

	protected void procReqNewHeader(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDP072001Message msgRT = null;
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
		ses.setAttribute("msgRT", msgRT);

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0720_proposals_header_maintenance.jsp");
			callPage(
				LangPath + "EDP0720_proposals_header_maintenance.jsp",
				req,
				res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procRNewProd(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		EDP072201Message msgRTC = null;
		EDP072101Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		String strNextScreen = "";
		boolean IsNotError = false;
		int acctype = 0;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		//----------------------------------------------------------------

		// Send Initial data
		try {

			flexLog("Send Initial Data");
			msgRT = (EDP072001Message) ses.getAttribute("msgRT");
			msgRTC = (EDP072201Message) ses.getAttribute("msgRTC");

			msgRTC.destroy();

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
			msgRT.setH01FLGWK3(req.getParameter("optH"));
			if (req.getParameter("E01PLPCN2") != null) {
				msgRT.setE01PLPCN4(req.getParameter("E01PLPCN2"));
			}
			if (req.getParameter("E01PLPCN3") != null) {
				msgRT.setE01PLPCN3(req.getParameter("E01PLPCN3"));
			}
			//importante! mueve clase a campos de la pantalla
			ses.setAttribute("msgRT", msgRT);
			ses.setAttribute("msgRTC", msgRTC);
			ses.setAttribute("msgList", msgList);
			ses.setAttribute("userPO", userPO);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0720_proposals_product_enter_maint.jsp");
			callPage(
				LangPath + "EDP0720_proposals_product_enter_maint.jsp",
				req,
				res);

		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procSave400(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if (req.getParameter("opt") != null) {
			userPO.setHeader16(req.getParameter("opt"));
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDP072001Message) ses.getAttribute("brnDetails");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDP072001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0099");
			msgRT.setH01FLGWK1(req.getParameter("opt"));
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
			flexLog("EDP072001 Message Sent c");
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

			if (newmessage.getFormatName().equals("EDP072001")) {
				msgRT = (EDP072001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("brnDetails", msgRT);
				ses.setAttribute("userPO", userPO);

				//
				if (IsNotError) { // There are no errors
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.creditproposal.JSEDP0720?SCREEN=400&opt="
							+ userPO.getHeader16());

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0704_recaudos_maintenance.jsp");
						callPage(
							LangPath + "EDP0704_recaudos_maintenance.jsp",
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

	protected void procProductPos(
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
		//crear variable para que se pueda leer en las pantallas			
		userPO.setHeader16(req.getParameter("opt"));

		inptOPT = Integer.parseInt(req.getParameter("opt"));

		// inptOPT=1;

		switch (inptOPT) {
			case 1 : //new
				userPO.setHeader17("0");
				userPO.setHeader18("0");
				break;
			case 2 : //Maintenance
				userPO.setHeader17("1");
				userPO.setHeader18("0");
				//procReqMaintenance(mc, user, req, res, ses);
				break;
			case 3 : //Delete
				userPO.setHeader17("1");
				userPO.setHeader18("1");
				//procReqDelete(mc, user, req, res, ses);
				break;
			case 5 : //Consulta
				userPO.setHeader17("1");
				userPO.setHeader18("1");
				//procReqConsulta(mc, user, req, res, ses);
				break;
			default :
				//Consulta
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.creditproposer.JSEDP0704?SCREEN=500");
				break;
		}
	}

	//	Next Activity
	protected void procReqNext_Activity(
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
		String OPECOD = "";
		String PARAM = "";

		if (req.getParameter("opt") != null) {
			inptOPT = Integer.parseInt(req.getParameter("opt"));
		} else {
			inptOPT = Integer.parseInt(userPO.getHeader16());
		}

		userPO.setHeader16(req.getParameter("opt"));

		OPECOD = req.getParameter("OPECOD");
		PARAM = req.getParameter("PARAM");

		switch (inptOPT) {
			case 1 : // Apertura
				//userPO.setPurpose("NEW");
				//ses.setAttribute("userPO", userPO);
				procNext_Activity(mc, user, req, res, ses);
				break;
			case 2 : // Mext Activity		
				//userPO.setPurpose("NEXT ACTIVITY");
				//ses.setAttribute("userPO", userPO);
				procNext_Activity(mc, user, req, res, ses);
				break;
			case 5 : // Mext Activity Inquiry
				//userPO.setPurpose("INQUIRY");
				//ses.setAttribute("userPO", userPO);
				procNext_Activity(mc, user, req, res, ses);
				break;
			case 7 : // Forzar		
				//userPO.setPurpose("FORCE");
				//ses.setAttribute("userPO", userPO);
				procNext_Activity(mc, user, req, res, ses);
				break;
			case 9 : // Mext Activity	
				//userPO.setPurpose("NEXT ACTIVITY");
				//ses.setAttribute("userPO", userPO);
				procNext_Activity(mc, user, req, res, ses);
				break;
			default :
				res.sendRedirect(super.srctx + LangPath + super.devPage);
				break;
		}
	}

	protected void procRPriorActiv(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");

			msgRT = (EDP072001Message) ses.getAttribute("msgRT");

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

			ses.setAttribute("msgRT", msgRT);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		String strNextScreen = "";
//		if (req.getParameter("TASK") != null) {
//			strNextScreen = LangPath + req.getParameter("TASK");
//		}
		strNextScreen = LangPath + "EDP0720_proposals_header_maintenance.jsp";

		try {
			flexLog("About to call Page: " + strNextScreen);
			callPage(strNextScreen, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

	protected void procNext_Activity(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		ELEERRMessage msgError = null;
		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEXT ACTIVITY");

		String opt = req.getParameter("opt");
		if (opt == null)
			opt = "";
		if (!opt.equals(""))
			userPO.setHeader16(opt);
		String opecode = "0012";
		if (opt.equals("7")) {
			//FORZAR
			opecode = "0016";
		}
		if (req.getParameter("OPECOD") != null) {
			userPO.setHeader22(req.getParameter("OPECOD"));
		}

		try {
			if (req.getParameter("CURRCODE") != null) {
				JBObjList bl = (JBObjList) ses.getAttribute("EDP072001Help");
				int idx = Integer.parseInt(req.getParameter("CURRCODE"));
				bl.setCurrentRow(idx);
				msgRT = (EDP072001Message) bl.getRecord();
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		userPO.setIdentifier(msgRT.getE01PLPNPR());
		userPO.setType(msgRT.getE01PLPPTY());
		userPO.setBank(msgRT.getE01PLPBNK());
		userPO.setCusNum(msgRT.getE01PLPCUN());

		// userPO.setHeader23(msgRT.getE01DPWACC());
		String strNextScreen = LangPath + "EDP0720_proposals_header_maintenance.jsp";

		userPO.setHeader1(msgRT.getE01PLTPR0());
		userPO.setHeader2(msgRT.getE01CUSCUN());
		userPO.setHeader3(msgRT.getE01CUSNA1());
		userPO.setOfficer(msgRT.getE01XXXEJE());
		userPO.setCurrency(msgRT.getE01PLTCCY());
				
		/*String strParam = msgRT.getE01DPWPAR();

		String strParam1 = strParam.substring(0, 1);
		String strParam2 = strParam.substring(2, 3);
		String strParam3 = strParam.substring(4, 5);

		userPO.setHeader17(strParam2);
		userPO.setHeader18(strParam3);*/

		try {
			msgRT = (EDP072001Message) mc.getMessageRecord("EDP072001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDP072001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opecode);

//			msgRT.setE01PLPBNK(req.getParameter("BNK"));
//			msgRT.setE01PLPBRN(req.getParameter("BRN"));
//			msgRT.setE01PLPRUT(req.getParameter("RUT"));
//			msgRT.setE01FILCUN(req.getParameter("CUS"));
			msgRT.setE01PLPNPR(req.getParameter("PROP"));
			msgRT.setE01FILPRD(userPO.getProdCode());
			msgRT.setE01FILTYP(userPO.getType());
			
			msgRT.setH01FLGWK3(req.getParameter("opt"));

			mc.sendMessage(msgRT);
			//msgRT.send();
			//msgRT.destroy();
			flexLog("EDP072001 Message Sent b");
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

				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP072001")) {

				msgRT = (EDP072001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgRT", msgRT);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("msgRT", msgRT);
		
		if (IsNotError) {
			procReqProductsList(mc, user, req, res, ses);
			procReqGuaranteeList(mc, user, req, res, ses);
			procReqCommentsList(mc, user, req, res, ses);
			procReqRecaudosN(mc, user, req, res, ses);

			JBList beanLP4 = getNextActivityJBList(msgRT);
			ses.setAttribute("optLP4", beanLP4);

			try {

				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0720_proposals_header_maintenance.jsp");
				callPage(
					LangPath + "EDP0720_proposals_header_maintenance.jsp",
					req,
					res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
				e.printStackTrace();

			}
		} else {
			try {
				userPO.setPurpose("INQUIRY");

				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0720_proposals_list.jsp");
				callPage(
					LangPath + "EDP0720_proposals_list.jsp",
					req,
					res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
				e.printStackTrace();

			}
		}
	}

	protected void procProposalProductsList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;
		JBListRec gcodeList = null;
		EDP072101Message msgL21 = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			msgL21 = (EDP072101Message) mc.getMessageRecord("EDP072101");
			msgL21.setH01USERID(user.getH01USR());
			msgL21.setH01PROGRM("EDP072101");
			msgL21.setH01TIMSYS(getTimeStamp());
			msgL21.setH01SCRCOD("01");
			msgL21.setH01OPECOD("0015");

			msgL21.setE01PLTNPR(userPO.getIdentifier());
			msgL21.setE01PLPBNK(userPO.getBank());

			msgL21.send();
			msgL21.destroy();
			flexLog("EDP072101 Message Sent");
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

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP072101")) {

				JBObjList beanList = new JBObjList();

				int colNum = 10;
				int gaColNum = 6;

				gcodeList = new JBListRec();
				gcodeList.init(gaColNum);

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				String myFlag1 = "";

				String myGaCode[] = new String[gaColNum];
				for (int i = 0; i < gaColNum; i++) {
					myGaCode[i] = "";
				}
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}
					msgL21 = (EDP072101Message) newmessage;
					marker = msgL21.getE01OPECDE();

					if (firstTime) {
						firstTime = false;
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						myGaCode[0] = msgL21.getE01PLTPRO();
						myGaCode[1] = msgL21.getE01PLTPRD();
						myGaCode[2] = msgL21.getE01PLTTYP();
						myGaCode[3] = msgL21.getE01PLTAMN();
						myGaCode[4] = msgL21.getE01PLTMAP();
						myGaCode[5] =
							msgL21.getE01PLTRU0() + " " + msgL21.getE01PLTRU1();
						//	otras descripcion				 
						//myGaCode[6] = msgL21.getE01COMDE9();

						gcodeList.addRow(myFlag1, myGaCode);

						beanList.addRow(msgL21);
						//este es el campo de rompimiento por grupo
						myFlag = msgL21.getE01PLTPRO();

						if (marker.equals("+")) {
							beanList.setShowNext(true);

							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP072101Help", beanList);
				ses.setAttribute("gaCodeList", gcodeList);
				ses.setAttribute("userPO", userPO);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected JBList getNextActivityJBList(EDP072001Message msgDoc)
		throws ServletException, IOException {

		JBList beanLP4 = null;
		String myFlagP4 = "";
		String myOptionP4 = "";
		beanLP4 = new datapro.eibs.beans.JBList();

		if (msgDoc.getE01DPPD01().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP01()
					+ "\">"
					+ msgDoc.getE01DPPP01()
					+ " - "
					+ msgDoc.getE01DPPD01()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD02().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP02()
					+ "\">"
					+ msgDoc.getE01DPPP02()
					+ " - "
					+ msgDoc.getE01DPPD02()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD03().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP03()
					+ "\">"
					+ msgDoc.getE01DPPP03()
					+ " - "
					+ msgDoc.getE01DPPD03()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD04().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP04()
					+ "\">"
					+ msgDoc.getE01DPPP04()
					+ " - "
					+ msgDoc.getE01DPPD04()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD05().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP05()
					+ "\">"
					+ msgDoc.getE01DPPP05()
					+ " - "
					+ msgDoc.getE01DPPD05()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD06().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP06()
					+ "\">"
					+ msgDoc.getE01DPPP06()
					+ " - "
					+ msgDoc.getE01DPPD06()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD07().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP07()
					+ "\">"
					+ msgDoc.getE01DPPP07()
					+ " - "
					+ msgDoc.getE01DPPD07()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD08().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP08()
					+ "\">"
					+ msgDoc.getE01DPPP08()
					+ " - "
					+ msgDoc.getE01DPPD08()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD09().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP09()
					+ "\">"
					+ msgDoc.getE01DPPP09()
					+ " - "
					+ msgDoc.getE01DPPD09()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD10().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP10()
					+ "\">"
					+ msgDoc.getE01DPPP10()
					+ " - "
					+ msgDoc.getE01DPPD10()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD11().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP11()
					+ "\">"
					+ msgDoc.getE01DPPP11()
					+ " - "
					+ msgDoc.getE01DPPD11()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD12().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP12()
					+ "\">"
					+ msgDoc.getE01DPPP12()
					+ " - "
					+ msgDoc.getE01DPPD12()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD13().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP14()
					+ "\">"
					+ msgDoc.getE01DPPP14()
					+ " - "
					+ msgDoc.getE01DPPD14()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD14().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP15()
					+ "\">"
					+ msgDoc.getE01DPPP15()
					+ " - "
					+ msgDoc.getE01DPPD15()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD15().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP16()
					+ "\">"
					+ msgDoc.getE01DPPP16()
					+ " - "
					+ msgDoc.getE01DPPD16()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD16().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP17()
					+ "\">"
					+ msgDoc.getE01DPPP17()
					+ " - "
					+ msgDoc.getE01DPPD17()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD17().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP18()
					+ "\">"
					+ msgDoc.getE01DPPP18()
					+ " - "
					+ msgDoc.getE01DPPD18()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD18().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP19()
					+ "\">"
					+ msgDoc.getE01DPPP19()
					+ " - "
					+ msgDoc.getE01DPPD19()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD19().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP20()
					+ "\">"
					+ msgDoc.getE01DPPP20()
					+ " - "
					+ msgDoc.getE01DPPD20()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD20().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP21()
					+ "\">"
					+ msgDoc.getE01DPPP21()
					+ " - "
					+ msgDoc.getE01DPPD21()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD21().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP22()
					+ "\">"
					+ msgDoc.getE01DPPP22()
					+ " - "
					+ msgDoc.getE01DPPD22()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD22().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP23()
					+ "\">"
					+ msgDoc.getE01DPPP23()
					+ " - "
					+ msgDoc.getE01DPPD23()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		if (msgDoc.getE01DPPD23().length() != 0) {
			myOptionP4 =
				"<option value=\""
					+ msgDoc.getE01DPPP24()
					+ "\">"
					+ msgDoc.getE01DPPP24()
					+ " - "
					+ msgDoc.getE01DPPD24()
					+ "</option>";
			beanLP4.addRow(myFlagP4, myOptionP4);
		}
		return beanLP4;
	}

	protected void procRDetProd(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		EDP072201Message msgRTC = null;
		EDP072101Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		String strNextScreen = "";
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		JBObjList beanListC = new JBObjList();

		//----------------------------------------------------------------

		// Send Initial data
		try {
			msgRT = (EDP072001Message) ses.getAttribute("msgRT");
			msgRT.setH01FLGWK3(req.getParameter("optH"));

			//actualiza los campos de la pantalla en la clase all the fields here
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

//			msgRT.setE01PLPFG4(req.getParameter("OPPRODUCT"));
			msgRT.setE01PLPPTY(userPO.getType());
			
			ses.setAttribute("msgRT", msgRT);


			JBObjList bl = (JBObjList) ses.getAttribute("EDP072101Help");
			int idx = Integer.parseInt(req.getParameter("COLLCOD"));
			bl.setCurrentRow(idx);
			msgList = (EDP072101Message) bl.getRecord();


			//		actualiza los campos de la pantalla en la clase all the fields here
			java.util.Enumeration enuL = msgList.fieldEnumeration();
			MessageField fieldL = null;
			String valueL = null;
			while (enuL.hasMoreElements()) {
				fieldL = (MessageField) enuL.nextElement();
				try {
					valueL = req.getParameter(field.getTag()).toUpperCase();
					if (valueL != null) {
						field.setString(valueL);
					}
				} catch (Exception e) {
				}
			}

			msgList.setE01PLTNPR(userPO.getIdentifier());
			msgList.setE01PLTPTY(userPO.getType());

			if (req.getParameter("E01PLPCN2") != null) {
				msgList.setE01PLTCN4(req.getParameter("E01PLPCN2"));
			}
			if (req.getParameter("E01PLPCN3") != null) {
				msgList.setE01PLTCN3(req.getParameter("E01PLPCN3"));
			}

			//importante! mueve clase a campos de la pantalla

			ses.setAttribute("msgList", msgList);

			msgRTC = (EDP072201Message) ses.getAttribute("msgRTC");
			java.util.Enumeration enuC = msgRTC.fieldEnumeration();
			MessageField fieldC = null;
			String valueC = null;
			while (enuC.hasMoreElements()) {
				fieldC = (MessageField) enuC.nextElement();
				try {
					valueC = req.getParameter(field.getTag()).toUpperCase();
					if (valueC != null) {
						field.setString(valueC);
					}
				} catch (Exception e) {
				}
			}

			// Send Initial data--------------------------------------------------------------
			try {
				msgRTC = (EDP072201Message) mc.getMessageRecord("EDP072201");
				msgRTC.setH02USERID(user.getH01USR());
				msgRTC.setH02PROGRM("EDP072201");
				msgRTC.setH02TIMSYS(getTimeStamp());
				msgRTC.setH02SCRCOD("01");
				msgRTC.setH02OPECOD("0007");
				
				msgRTC.setE01PLTPTY(userPO.getType());
				msgRTC.setE02DPJBNK(userPO.getBank());
				msgRTC.setE02DPANPR(userPO.getIdentifier());
				
				msgRTC.setE02DPAPRO(msgList.getE01PLTPRO());
				msgRTC.setE02DPATYP(msgList.getE01PLTTYP());

				msgRTC.send();
				msgRTC.destroy();
				flexLog("EDP072201 Message Sent b");
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

					showERROR(msgError);

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);

				}

				// Receive Data
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDP072201")) {
					msgRTC = (EDP072201Message) newmessage;

					msgRTC.setE01PLTMNO(msgList.getE01PLTMNO());
					msgRTC.setE01PLTRA1(msgList.getE01PLTRA1());
					msgRTC.setE01PLTRA2(msgList.getE01PLTRA2());
					msgRTC.setE01PLTRA3(msgList.getE01PLTRA3());
					msgRTC.setE01PLTRV1(msgList.getE01PLTRV1());
					msgRTC.setE01PLTRV2(msgList.getE01PLTRV2());
					msgRTC.setE01PLTRV3(msgList.getE01PLTRV3());
					msgRTC.setE01PLTBL1(msgList.getE01PLTBL1());
					msgRTC.setE01PLTRRE(msgList.getE01PLTRRE());
	
					flexLog("Putting java beans into the session");
	
					ses.setAttribute("msgRTC", msgRTC);
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e + newmessage);
				throw new RuntimeException("Socket Communication Error Receiving");
			}


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		String appCode = msgList.getE01PLTACD();
		if (IsNotError) {
			try {			
				String pageToCall = "";
				if (msgRT.getH01FLGWK3().equals("3")) {
					if (userPO.getType().equals("7")) {
						pageToCall = "EDP0720_proposals_product_inq_others.jsp";
					} else if (userPO.getType().equals("9")) {
						pageToCall = "EDP0720_proposals_product_inq_renews.jsp";
					} else {
						if (appCode.equals("10")) {
							pageToCall = "EDP0720_proposals_product_inq_loans.jsp";
						} else if (appCode.equals("40")) {
							pageToCall =
								"EDP0720_proposals_product_inq_letters.jsp";
						} else if (appCode.equals("43")) {
							pageToCall =
								"EDP0720_proposals_product_inq_bailgranted.jsp";
						} else if (appCode.equals("90")) {
							pageToCall = "EDP0720_proposals_product_inq_lines.jsp";
						}
					}
				} else {
					if (userPO.getType().equals("7")) {
						pageToCall = "EDP0720_proposals_product_maint_others.jsp";
					} else if (userPO.getType().equals("9")) {
						pageToCall = "EDP0720_proposals_product_maint_renews.jsp";
					} else {
						if (appCode.equals("10")) {
							pageToCall =
								"EDP0720_proposals_product_maint_loans.jsp";
						} else if (appCode.equals("40")) {
							pageToCall =
								"EDP0720_proposals_product_maint_letters.jsp";
						} else if (appCode.equals("43")) {
							pageToCall =
								"EDP0720_proposals_product_maint_bailgranted.jsp";
						} else if (appCode.equals("90")) {
							pageToCall =
								"EDP0720_proposals_product_maint_lines.jsp";
						}
					}
				}

				flexLog("About to call Page: " + LangPath + pageToCall);
				callPage(LangPath + pageToCall, req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} else {
			try {			
				flexLog("About to call Page: " + LangPath + "EDP0720_proposals_header_maintenance.jsp");
				callPage(LangPath + "EDP0720_proposals_header_maintenance.jsp", req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}

	}

	protected void procPRINTPROP(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data--------------------------------------------------------------
		try {
			msgRT = (EDP072001Message) mc.getMessageRecord("EDP072001");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDP072001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0020");
			msgRT.setE01PLPNPR(req.getParameter("PROP"));
			msgRT.send();
			msgRT.destroy();

			flexLog("EDP072001 Message Sent");
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		if (req.getParameter("FMT").equals("L")) {
			try {
				userPO.setPurpose("INQUIRY");
				
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0720_proposals_list.jsp");
				callPage(LangPath + "EDP0720_proposals_list.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		if (req.getParameter("FMT").equals("H")) {
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDP0720_proposals_header_maintenance.jsp");
				callPage(
					LangPath + "EDP0720_proposals_header_maintenance.jsp",
					req,
					res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
	}

	protected void procRFmtHeader(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0720_proposals_header_maintenance.jsp");
			callPage(
				LangPath + "EDP0720_proposals_header_maintenance.jsp",
				req,
				res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}

	protected void procRUPDGUAR(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072601Message msgL0726 = null;
		ELEERRMessage msgError = null;

		EDP072001Message msgRT = null;

		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		JBListRec gcode0726 = null;
		JBListRec gcode0726T = null;
		EDP072601Message msgL26 = null;

		try {
			msgRT = (EDP072001Message) mc.getMessageRecord("EDP072001");

			// recuperar los campos de la pantalla al bean
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

			if (req.getParameter("E01PLPPTY") != null) {
				msgRT.setE01PLPPTY(req.getParameter("E01PLPPTY"));
			}

			msgL26 = (EDP072601Message) mc.getMessageRecord("EDP072601");
			msgL26.setH01USERID(user.getH01USR());
			msgL26.setH01PROGRM("EDP072601");
			msgL26.setH01TIMSYS(getTimeStamp());
			msgL26.setH01SCRCOD("01");
			msgL26.setH01OPECOD("0005");
			msgL26.setE01DPEBNK(userPO.getBank());
			//
			if (req.getParameter("TGAR").equals("G")) {

				msgL26.setE01DPENPR(req.getParameter("E01PLPNPR"));
				msgL26.setE01DPEPRD(req.getParameter("PRDG"));
				msgL26.setE01DPETYP(req.getParameter("TYPG"));
				msgL26.setE01DPEREG(req.getParameter("REGG"));
			}
			if (req.getParameter("TGAR").equals("T")) {
				msgL26.setE01DPZNPR(req.getParameter("E01PLPNPR"));
				msgL26.setE01DPZGRT(req.getParameter("TYPG"));
				msgL26.setE01DPZDGA(req.getParameter("REGG"));
			}

			msgL26.send();
			msgL26.destroy();
			flexLog("EDP072601 Message Sent");
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

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP072601")) {

				JBObjList beanList = new JBObjList();

				int colNum = 10;
				int gaColNum = 6;
				gcode0726 = new JBListRec();
				gcode0726.init(gaColNum);

				gcode0726T = new JBListRec();
				gcode0726T.init(gaColNum);

				boolean firstTime = true;
				String marker = "";
				String tipReg = "";
				String myFlag = "";
				String myFlag1 = "";

				String myGaCode[] = new String[gaColNum];
				for (int i = 0; i < gaColNum; i++) {
					myGaCode[i] = "";
				}
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}

					msgL26 = (EDP072601Message) newmessage;
					marker = msgL26.getE01OPECDE();
					tipReg = msgL26.getH01FLGWK2();

					if (firstTime) {
						firstTime = false;
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (tipReg.equals("G")) {
							myGaCode[0] = msgL26.getE01DPEREG();
							myGaCode[1] = msgL26.getE01DPEDRE();
							myGaCode[2] =
								msgL26.getE01DPEFA1()
									+ "/"
									+ msgL26.getE01DPEFA2()
									+ "/"
									+ msgL26.getE01DPEFA3();
							myGaCode[3] = "";
							myGaCode[4] = "";
							myGaCode[5] = "";

							gcode0726.addRow(myFlag1, myGaCode);
						}
						if (tipReg.equals("T")) {
							myGaCode[0] = msgL26.getE01DPZGRT();
							myGaCode[1] = msgL26.getE01DPZDRE();
							myGaCode[2] = msgL26.getE01DPZDGA();
							myGaCode[3] =
								msgL26.getE01DPZFA1()
									+ "/"
									+ msgL26.getE01DPZFA2()
									+ "/"
									+ msgL26.getE01DPZFA3();
							myGaCode[4] = "";
							myGaCode[5] = "";

							gcode0726T.addRow(myFlag1, myGaCode);
						}
						beanList.addRow(msgL26);
						//este es el campo de rompimiento por grupo
						myFlag = msgL26.getE01DPENPR();

						if (marker.equals("+")) {
							beanList.setShowNext(true);

							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP072601Help", beanList);
				ses.setAttribute("ga0726", gcode0726);
				ses.setAttribute("ga0726T", gcode0726T);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgRT", msgRT);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

		//			END REQUIRED GUARANTEES LIST

		try {

			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0720_proposals_header_maintenance.jsp");
			callPage(
				LangPath + "EDP0720_proposals_header_maintenance.jsp",
				req,
				res);

		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


	protected void procRETPRDINF(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		EDP072201Message msgRTC = null;
		EDP072101Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String appCode = req.getParameter("E01PLTACD");
		String type = req.getParameter("E01PLTTYP");
		String prod = req.getParameter("E01PLTPRO");		

		msgRT = (EDP072001Message) ses.getAttribute("msgRT");

		try {
			msgList = (EDP072101Message) mc.getMessageRecord("EDP072101");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP0721");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0007");

			// all the fields here
			java.util.Enumeration enu = msgList.fieldEnumeration();
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
			
			msgList.setE01PLTNPR(req.getParameter("E01PLPNPR"));

			msgList.send();
			msgList.destroy();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
		}
		
		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				showERROR(msgError);

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

			if (newmessage.getFormatName().equals("EDP072101")) {

				msgList = (EDP072101Message) newmessage;

				if (req.getParameter("E01PLPCN2") != null) {
					msgList.setE01PLTCN4(req.getParameter("E01PLPCN2"));
				}
				if (req.getParameter("E01PLPCN3") != null) {
					msgList.setE01PLTCN3(req.getParameter("E01PLPCN3"));
				}

				ses.setAttribute("msgList", msgList);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}
		
		if (IsNotError) {
			// Send Initial data--------------------------------------------------------------
			try {
				msgRTC = (EDP072201Message) mc.getMessageRecord("EDP072201");
				msgRTC.setH02USERID(user.getH01USR());
				msgRTC.setH02PROGRM("EDP072201");
				msgRTC.setH02TIMSYS(getTimeStamp());
				msgRTC.setH02SCRCOD("01");
				msgRTC.setH02OPECOD("0007");

				// all the fields here
				java.util.Enumeration enu = msgRTC.fieldEnumeration();
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
						//					e.printStackTrace();
						//								flexLog("Error: " + e + newmessage);
						//								throw new RuntimeException("Socket Communication Error Receiving");
					}
				}

				msgRTC.setE02DPJBNK(userPO.getBank());
				msgRTC.setE02DPANPR(userPO.getIdentifier());
				msgRTC.setE01XXXCUN(userPO.getCusNum());

				msgRTC.setE02DPATYP(type);
				msgRTC.setE02DPAPRO(prod);

				msgRTC.send();
				msgRTC.destroy();

				flexLog("EDP072201 Message Sent");
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
					IsNotError = msgError.getERRNUM().equals("0");
					showERROR(msgError);

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

				if (newmessage.getFormatName().equals("EDP072201")) {

					msgRTC = (EDP072201Message) newmessage;

					//Setting all the fields on EDP072101 with EDP072201
					MessageField field21 = null;
					java.util.Enumeration enu22 = msgRT.fieldEnumeration();
					String value22 = null;
					while (enu22.hasMoreElements()) {
						field21 = (MessageField) enu22.nextElement();
						try {
							value22 = msgRTC.getField(field21.getTag()).getString();
							if (value22 != null) {
								field21.setString(value22);
							}
						} catch (Exception e) {
						}
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("msgRTC", msgRTC);

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Data Receiving");
			}
		} 

		msgList.setE01PLTDST(userPO.getHeader4());

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		if (IsNotError) {
			try {			
				String pageToCall = "";
				if (msgRT.getH01FLGWK3().equals("3")) {
					if (userPO.getType().equals("7")) {
						pageToCall = "EDP0720_proposals_product_inq_others.jsp";
					} else if (userPO.getType().equals("9")) {
						pageToCall = "EDP0720_proposals_product_inq_renews.jsp";
					} else {
						if (appCode.equals("10")) {
							pageToCall = "EDP0720_proposals_product_inq_loans.jsp";
						} else if (appCode.equals("40")) {
							pageToCall =
								"EDP0720_proposals_product_inq_letters.jsp";
						} else if (appCode.equals("43")) {
							pageToCall =
								"EDP0720_proposals_product_inq_bailgranted.jsp";
						} else if (appCode.equals("90")) {
							pageToCall = "EDP0720_proposals_product_inq_lines.jsp";
						}
					}
				} else {
					if (userPO.getType().equals("7")) {
						pageToCall = "EDP0720_proposals_product_maint_others.jsp";
					} else if (userPO.getType().equals("9")) {
						pageToCall = "EDP0720_proposals_product_maint_renews.jsp";
					} else {
						if (appCode.equals("10")) {
							pageToCall =
								"EDP0720_proposals_product_maint_loans.jsp";
						} else if (appCode.equals("40")) {
							pageToCall =
								"EDP0720_proposals_product_maint_letters.jsp";
						} else if (appCode.equals("43")) {
							pageToCall =
								"EDP0720_proposals_product_maint_bailgranted.jsp";
						} else if (appCode.equals("90")) {
							pageToCall =
								"EDP0720_proposals_product_maint_lines.jsp";
						}
					}
				}

				flexLog("About to call Page: " + LangPath + pageToCall);
				callPage(LangPath + pageToCall, req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		} else {
			try {			
				flexLog("About to call Page: " + LangPath + "EDP0720_proposals_product_enter_maint.jsp");
				callPage(LangPath + "EDP0720_proposals_product_enter_maint.jsp", req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}


	}

	protected void procSave400Ruta(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		EDP072201Message msgRTC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		String strNextScreen = "";
		boolean IsNotError = true;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

// INICIO EDP0737
	JBListRec beanList = null;
	EDP073701Message msgList = null;
	JBObjList bl = (JBObjList) ses.getAttribute("EDP073701Help");

	int numrec = 0;

	if(req.getParameter("RECNUM")!=null){
		   numrec = Integer.parseInt(req.getParameter("RECNUM"));
	   }
   if(req.getParameter("E01PLPBNK")!=null){
		userPO.setBank(req.getParameter("E01PLPBNK"));
	  }
   if(req.getParameter("E01PLPNPR")!=null){
	   userPO.setIdentifier(req.getParameter("E01PLPNPR"));
	 }
try {
   for (int i = 1; i < numrec; i++) {
	if(req.getParameter("E01DPUB" + i)!=null){

	bl.setCurrentRow(i);
	try {
	msgList = (EDP073701Message) mc.getMessageRecord("EDP073701");
	  msgList.setH01USERID(user.getH01USR());
	  msgList.setH01PROGRM("EDP0737");
	  msgList.setH01TIMSYS(getTimeStamp());
//	  msgList.setH01SCRCOD("01");
	  msgList.setH01OPECOD("0005");
	  msgList.setE01PLPBNK(userPO.getBank());
	  msgList.setE01PLTNPR(userPO.getIdentifier());
	  msgList.setE01PLTPRO(req.getParameter("E01PLTPRO" + i));
	  msgList.setE01DPDD01(req.getParameter("E01DPDD" + i));
	  msgList.setE01DPDT01(req.getParameter("E01DPDT" + i));
	  msgList.setE01DPFR01(req.getParameter("E01DPFR" + i));
	  msgList.setE01DPUB01(req.getParameter("E01DPUB" + i));
	  msgList.setE01DPOBSR(req.getParameter("E01DPOB" + i));
	  if (req.getParameter("E01PLPCN4") != null) {
		  msgList.setE01PLPCN2(req.getParameter("E01PLPCN4"));
	  }
	  if (req.getParameter("E01PLPCN3") != null) {
		  msgList.setE01PLPCN3(req.getParameter("E01PLPCN3"));
	  }
	  msgList.send();
	  msgList.destroy();
	  flexLog("EDP073701 Message Sent");
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	// Receive Error
	try {
		newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {
//			ses.setAttribute("userPO", userPO);


			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			if (IsNotError) {
//				beanList.setFlag("S", beanList.getCurrentRow());
			} else {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
//				ses.setAttribute("chkList", beanList);


			try {


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
		procReqRecaudosN(mc, user, req, res, ses);
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}


// END  EDP0737



		String strParameters = "";
//		if (req.getParameter("TASK") != null) {
//			strNextScreen = LangPath + req.getParameter("TASK");
//		}
		strNextScreen = LangPath + "EDP0720_proposals_header_maintenance.jsp";
		String opecode = "";
		if (userPO.getPurpose().equals("NEW")) {
			opecode = "0001";
		} else {
			opecode = req.getParameter("OPECOD");
			if (opecode == null || opecode.equals("")) opecode = "0002";
		}
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDP072001Message) ses.getAttribute("msgRT");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDP072001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD(opecode);
			//msgRT.setH01FLGWK3(strParam1);

			// actualiza los campos de la pantalla en la clase all the fields here
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
			msgRT.setH01USERID(user.getH01USR());
			if (req.getParameter("pos") != null) {
				msgRT.setE01RECPOS(req.getParameter("pos"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			flexLog("Send Initial Data");
			msgRTC = (EDP072201Message) ses.getAttribute("msgRTC");
			msgRTC.setH02USERID(user.getH01USR());
			msgRTC.setH02PROGRM("EDP072201");
			msgRTC.setH02TIMSYS(getTimeStamp());
			msgRTC.setH02SCRCOD("01");
			msgRTC.setH02OPECOD("0002");

			msgRTC.setE02DPJBNK(userPO.getBank());
			msgRTC.setE02DPANPR(userPO.getIdentifier());

			//	actualiza los campos de la pantalla en la clase all the fields here
			java.util.Enumeration enuC = msgRTC.fieldEnumeration();
			MessageField fieldC = null;
			String valueC = null;
			while (enuC.hasMoreElements()) {
				fieldC = (MessageField) enuC.nextElement();
				try {
					valueC = req.getParameter(fieldC.getTag()).toUpperCase();
					if (valueC != null) {
						fieldC.setString(valueC);
					}
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		if (IsNotError) { // There are no errors
			// Send Initial data
			try {
				//	envia
				mc.sendMessage(msgRTC);
				///	msgRTC.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

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

			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDP072201")) {
					msgRTC = (EDP072201Message) newmessage;

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

		} 






		
		if (IsNotError) { // There are no errors
			// Send Initial data
			try {
				//	envia
				mc.sendMessage(msgRT);
				flexLog("EDP072001 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

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

//			try {
//				newmessage = mc.receiveMessage();
//
//				if (newmessage.getFormatName().equals("EDP072001")) {
//					msgRT = (EDP072001Message) newmessage;
//
//				} else
//					flexLog("Message " + newmessage.getFormatName() + " received.");
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				flexLog("Error: " + e);
//				throw new RuntimeException("Socket Communication Error");
//			}

		} 

		if (IsNotError) { // There are no errors
			res.sendRedirect(
				super.srctx
					+ "/servlet/datapro.eibs.creditproposal.JSEDP0720?SCREEN=100&OPTION="
					+ userPO.getOption());

		} else { // There are errors
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgRT", msgRT);
			ses.setAttribute("msgRTC", msgRTC);
//			ses.setAttribute("msgRTR", msgRTR);
			ses.setAttribute("error", msgError);
			try {
				flexLog("About to call Page: " + strNextScreen);
				callPage(strNextScreen, req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
	
	}

	protected void procReqProductsList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		EDP072101Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		msgRT = (EDP072001Message) ses.getAttribute("msgRT");

		JBListRec gcodeList = null;
		EDP072101Message msgL21 = null;

		try {
			msgL21 = (EDP072101Message) mc.getMessageRecord("EDP072101");
			msgL21.setH01USERID(user.getH01USR());
			msgL21.setH01PROGRM("EDP072101");
			msgL21.setH01TIMSYS(getTimeStamp());
			msgL21.setH01SCRCOD("01");
			msgL21.setH01OPECOD("0015");

			msgL21.setE01PLTNPR(userPO.getIdentifier());
			msgL21.setE01PLPBNK(userPO.getBank());

			msgL21.send();
			msgL21.destroy();
			flexLog("EDP072101 Message Sent");
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

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP072101")) {

				JBObjList beanList = new JBObjList();

				int colNum = 10;
				int gaColNum = 6;

				gcodeList = new JBListRec();
				gcodeList.init(gaColNum);

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				String myFlag1 = "";

				String myGaCode[] = new String[gaColNum];
				for (int i = 0; i < gaColNum; i++) {
					myGaCode[i] = "";
				}
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}
					msgL21 = (EDP072101Message) newmessage;
					marker = msgL21.getE01OPECDE();

					if (firstTime) {
						firstTime = false;
					}
					if (marker.equals("*")) {
						/*java.util.Enumeration enu = msgRT.fieldEnumeration();
						MessageField field = null;
						String value = null;
						while (enu.hasMoreElements()) {
							field = (MessageField) enu.nextElement();
							try {
								value = msgL21.getField(field.getTag()).getString();
								if (value != null) {
									field.setString(value);
								}
							} catch (Exception e) {
							}
						}*/

						beanList.setShowNext(false);
						break;
					} else {
						myGaCode[0] = msgL21.getE01PLTPRO();
						myGaCode[1] = msgL21.getE01PLTPRD();
						myGaCode[2] = msgL21.getE01PLTTYP();
						myGaCode[3] = msgL21.getE01PLTAMN();
						myGaCode[4] = msgL21.getE01PLTMAP();
						myGaCode[5] =
							msgL21.getE01PLTRU0() + " " + msgL21.getE01PLTRU1();

						gcodeList.addRow(myFlag1, myGaCode);

						beanList.addRow(msgL21);
						//este es el campo de rompimiento por grupo
						myFlag = msgL21.getE01PLTPRO();

						if (marker.equals("+")) {
							beanList.setShowNext(true);

							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP072101Help", beanList);
				ses.setAttribute("gaCodeList", gcodeList);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}
	}

	protected void procReqGuaranteeList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		//		BEGIN REQUESTED GUARANTEES LIST
		JBListRec gcode0726 = null;
		JBListRec gcode0726T = null;
		EDP072601Message msgL26 = null;

		try {
			msgL26 = (EDP072601Message) mc.getMessageRecord("EDP072601");
			msgL26.setH01USERID(user.getH01USR());
			msgL26.setH01PROGRM("EDP072601");
			msgL26.setH01TIMSYS(getTimeStamp());
			msgL26.setH01SCRCOD("01");
			msgL26.setH01OPECOD("0015");

			msgL26.setE01DPENPR(userPO.getIdentifier());
			msgL26.setE01DPEBNK(userPO.getBank());

			msgL26.send();
			msgL26.destroy();
			flexLog("EDP072601 Message Sent");
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

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP072601")) {

				JBObjList beanList = new JBObjList();

				int colNum = 10;
				int gaColNum = 6;

				gcode0726 = new JBListRec();
				gcode0726.init(gaColNum);

				gcode0726T = new JBListRec();
				gcode0726T.init(gaColNum);

				boolean firstTime = true;
				String marker = "";
				String tipReg = "";
				String myFlag = "";
				String myFlag1 = "";

				String myGaCode[] = new String[gaColNum];
				for (int i = 0; i < gaColNum; i++) {
					myGaCode[i] = "";
				}
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) {
					myRow[i] = "";
				}

				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}
					msgL26 = (EDP072601Message) newmessage;
					marker = msgL26.getE01OPECDE();
					tipReg = msgL26.getH01FLGWK2();

					if (firstTime) {
						firstTime = false;
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (tipReg.equals("G")) {
							myGaCode[0] = msgL26.getE01DPEREG();
							myGaCode[1] = msgL26.getE01DPEDRE();
							myGaCode[2] =
								msgL26.getE01DPEFA1()
									+ "/"
									+ msgL26.getE01DPEFA2()
									+ "/"
									+ msgL26.getE01DPEFA3();
							myGaCode[3] = "";
							myGaCode[4] = "";
							myGaCode[5] = "";

							gcode0726.addRow(myFlag1, myGaCode);
						}
						if (tipReg.equals("T")) {
							myGaCode[0] = msgL26.getE01DPZGRT();
							myGaCode[1] = msgL26.getE01DPZDRE();
							myGaCode[2] = msgL26.getE01DPZDGA();
							myGaCode[3] =
								msgL26.getE01DPZFA1()
									+ "/"
									+ msgL26.getE01DPZFA2()
									+ "/"
									+ msgL26.getE01DPZFA3();
							myGaCode[4] = "";
							myGaCode[5] = "";

							gcode0726T.addRow(myFlag1, myGaCode);
						}
						beanList.addRow(msgL26);
						//este es el campo de rompimiento por grupo
						myFlag = msgL26.getE01DPENPR();

						if (marker.equals("+")) {
							beanList.setShowNext(true);

							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP072601Help", beanList);
				ses.setAttribute("ga0726", gcode0726);
				ses.setAttribute("ga0726T", gcode0726T);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}
	}

	protected void procReqCommentsList(
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

		//TRAER COMMENTARIOS
		EDP072201Message msgRTC = null;
		JBObjList beanListC = new JBObjList();

		// Send Initial data--------------------------------------------------------------
		try {
			msgRTC = (EDP072201Message) mc.getMessageRecord("EDP072201");
			msgRTC.setH02USERID(user.getH01USR());
			msgRTC.setH02PROGRM("EDP072201");
			msgRTC.setH02TIMSYS(getTimeStamp());
			msgRTC.setH02SCRCOD("01");
			msgRTC.setH02OPECOD("0015");

			msgRTC.setE02DPJBNK(userPO.getBank());
			msgRTC.setE02DPANPR(userPO.getIdentifier());

			msgRTC.setE02DPAPRO("");
			msgRTC.setE02DPATYP("");

			msgRTC.send();
			msgRTC.destroy();
			flexLog("EDP072201 Message Sent b");
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
			}

			// Receive Data
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDP072201")) {
				boolean firstTime = true;
				String marker = "";

				int ct = 0;
				while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
						System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
					}
					msgRTC = (EDP072201Message) newmessage;
					marker = msgRTC.getH02FLGMAS();

					if (firstTime) {
						firstTime = false;
					}

					if (marker.equals("*")) {
						beanListC.setShowNext(false);
						break;
					} else {
						beanListC.addRow(msgRTC);

						if (marker.equals("+")) {
							beanListC.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				} //END WHILE

			} // ENDIF

			flexLog("Putting java beans into the session");

			ses.setAttribute("EDP072201Help", beanListC);
			ses.setAttribute("msgRTC", msgRTC);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
	}


	protected void procReqRecaudosN(
	  MessageContext mc,
	  ESS0030DSMessage user,
	  HttpServletRequest req,
	  HttpServletResponse res,
	  HttpSession ses)
	  throws ServletException, IOException {

	  MessageRecord newmessage = null;
	  EDP073701Message msgList = null;
	  ELEERRMessage msgError = null;
	  UserPos userPO = null;
	  boolean IsNotError = false;

	  userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	  // Send Initial data
	  try {
		  msgList = (EDP073701Message) mc.getMessageRecord("EDP073701");
		  msgList.setH01USERID(user.getH01USR());
		  msgList.setH01PROGRM("EDP0737");
		  msgList.setH01TIMSYS(getTimeStamp());
//		  msgList.setH01SCRCOD("01");
		  msgList.setH01OPECOD("0015");
     	  msgList.setE01PLPBNK(userPO.getBank());
		  msgList.setE01PLTNPR(userPO.getIdentifier());
		if (req.getParameter("E01PLPCN2") != null) {
			msgList.setE01PLPCN2(req.getParameter("E01PLPCN2"));
		}else{
		if (req.getParameter("E01PLPCN4") != null) {
			msgList.setE01PLPCN2(req.getParameter("E01PLPCN4"));
		}
		}
		if (req.getParameter("E01PLPCN3") != null) {
			msgList.setE01PLPCN3(req.getParameter("E01PLPCN3"));
		}

//call 400
		  msgList.send();
		  msgList.destroy();
		  flexLog("EDP073701 Message Sent");
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

		  }
	  } catch (Exception e) {
		  e.printStackTrace();
		  flexLog("Error: " + e + newmessage);
		  throw new RuntimeException("Socket Communication Error Receiving");
	  }

	  // Receive Data
	  try {
		  newmessage = mc.receiveMessage();

		  if (newmessage.getFormatName().equals("EDP073701")) {

			  JBObjList beanList = new JBObjList();
//			  JBListRec beanList = new JBListRec();

			  boolean firstTime = true;
			  String marker = "";

			 beanList.setNoResult(true);
			  int ct = 0;
			 while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
				 if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					 System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
				 }

				  msgList = (EDP073701Message) newmessage;

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
			  ses.setAttribute("EDP073701Help", beanList);

		  } else
			  flexLog("Message " + newmessage.getFormatName() + " received.");

	  } catch (Exception e) {
		  e.printStackTrace();
		  flexLog("Error: " + e);
		  throw new RuntimeException("Socket Communication Data Receiving");
	  }

}



	protected void procReqProposalRouteDetails(
		MessageContext mc,
		ESS0030DSMessage user,
		EDP072001Message msgRT,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		//----------------------------------------------------------------
		try {
			flexLog("Send Initial Data");
			//msgRT = (EDP072001Message) ses.getAttribute("msgRT");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDP0720");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0006"); //099

			//msgRT.setE01PLPNPR(userPO.getIdentifier());
			//msgRT.setE01PLPPTY(userPO.getType());

			mc.sendMessage(msgRT);
			flexLog("Message send : " + msgRT.getFormatName());

			///		   msgRT.destroy();
			flexLog("EDP072001 Message Sent");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		//=================
		//=================
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

			if (newmessage.getFormatName().equals("EDP072001")) {
				msgRT = (EDP072001Message) newmessage;
				JBList beanLP4 = getNextActivityJBList(msgRT);
				ses.setAttribute("optLP4", beanLP4);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			flexLog("Putting java beans into the session");
			ses.setAttribute("msgRT", msgRT);
			ses.setAttribute("error", msgError);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procRSaveProd(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072001Message msgRT = null;
		EDP072101Message msgList = null;
		EDP072201Message msgRTC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		String strNextScreen = "";
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String type = req.getParameter("E01PLTTYP");
		String prod = req.getParameter("E01PLTPRO");

		//----------------------------------------------------------------
		msgRT = (EDP072001Message) ses.getAttribute("msgRT");

		try {
			flexLog("Send Initial Data");
			msgList = (EDP072101Message) ses.getAttribute("msgList");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDP072101");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0005");

			java.util.Enumeration enu = msgList.fieldEnumeration();
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

			msgList.setH01FLGWK1(userPO.getType());
			msgList.setE01PLPBNK(userPO.getBank());
			msgList.setE01PLTNPR(userPO.getIdentifier());
			msgList.setE01PLTCUN(userPO.getCusNum());

			msgList.setE01PLTPRO(prod);
			try {
				msgList.setE01PLTRRE(req.getParameter("E01PLTRREA1"));
			} catch (Exception e) {
			}

			mc.sendMessage(msgList);
			flexLog("Message send : " + msgList.getFormatName());

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		//=================
		//=================
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

			if (newmessage.getFormatName().equals("EDP072101")) {
				msgList = (EDP072101Message) newmessage;
				if (req.getParameter("E01PLPCN2") != null) {
					msgList.setE01PLTCN4(req.getParameter("E01PLPCN2"));
				}
				if (req.getParameter("E01PLPCN3") != null) {
					msgList.setE01PLTCN3(req.getParameter("E01PLPCN3"));
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgList", msgList);
				//				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		try {
			msgRTC = (EDP072201Message) ses.getAttribute("msgRTC");
			msgRTC.setH02USERID(user.getH01USR());
			msgRTC.setH02PROGRM("EDP072201");
			msgRTC.setH02TIMSYS(getTimeStamp());
			msgRTC.setH02SCRCOD("01");
			msgRTC.setH02OPECOD("0002");

			//			actualiza los campos de la pantalla en la clase all the fields here
			java.util.Enumeration enuC = msgRTC.fieldEnumeration();
			MessageField fieldC = null;
			String valueC = null;
			while (enuC.hasMoreElements()) {
				fieldC = (MessageField) enuC.nextElement();
				try {
					valueC =
						req.getParameter(fieldC.getTag()).toUpperCase();
					if (valueC != null) {
						fieldC.setString(valueC);
					}
				} catch (Exception e) {
				}
			}

			msgRTC.setE02DPANPR(userPO.getIdentifier());
			msgRTC.setE01PLTPTY(userPO.getType());
			msgRTC.setE02DPJBNK(userPO.getBank());

			msgRTC.setE02DPAPRO(prod);
			msgRTC.setE02DPATYP(type);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
			
		if (IsNotError) { // There are no errors
			procReqProductsList(mc, user, req, res, ses);
			procReqGuaranteeList(mc, user, req, res, ses);
			procReqRecaudosN(mc, user, req, res, ses);

//			if (req.getParameter("TASK") != null) {
//				strNextScreen = LangPath + req.getParameter("TASK");
//			}
			strNextScreen = LangPath + "EDP0720_proposals_header_maintenance.jsp";
				
			try {
				// Send Initial data
				flexLog("Send Initial Data");
				mc.sendMessage(msgRTC);
				flexLog("Message send : " + msgRTC.getFormatName());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
				
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
				
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDP072201")) {
					msgRTC = (EDP072201Message) newmessage;

				} else {
					flexLog("Message " + newmessage.getFormatName() + " received.");
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}

		if (userPO.getPurpose().equals("NEW")) {
			msgRT.setE01PLTTY0(type);
			msgRT.setE01PLTPR0(prod);
			procReqProposalRouteDetails(mc, user, msgRT, ses);
			//msgError = (ELEERRMessage) ses.getAttribute("error");
		}
		if (req.getParameter("E01PLPCN2") != null) {
			msgList.setE01PLTCN4(req.getParameter("E01PLPCN2"));
		}
		if (req.getParameter("E01PLPCN3") != null) {
			msgList.setE01PLTCN3(req.getParameter("E01PLPCN3"));
		}

		ses.setAttribute("userPO", userPO);
		ses.setAttribute("error", msgError);
		ses.setAttribute("msgRTC", msgRTC);
		if (IsNotError) { // There are no errors

			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0720_proposals_header_maintenance.jsp");
			callPage(
				LangPath + "EDP0720_proposals_header_maintenance.jsp",
				req,
				res);
		} else { // There are errors
			String pageToCall = "";
			String appCode = msgList.getE01PLTACD();
			if (msgRT.getH01FLGWK3().equals("3")) {
				if (userPO.getType().equals("7")) {
					pageToCall = "EDP0720_proposals_product_inq_others.jsp";
				} else if (userPO.getType().equals("9")) {
					pageToCall = "EDP0720_proposals_product_inq_renews.jsp";
				} else {
					if (appCode.equals("10")) {
						pageToCall =
							"EDP0720_proposals_product_inq_loans.jsp";
					} else if (appCode.equals("40")) {
						pageToCall =
							"EDP0720_proposals_product_inq_letters.jsp";
					} else if (appCode.equals("43")) {
						pageToCall =
							"EDP0720_proposals_product_inq_bailgranted.jsp";
					} else if (appCode.equals("90")) {
						pageToCall =
							"EDP0720_proposals_product_inq_lines.jsp";
					}
				}
			} else {
				if (userPO.getType().equals("7")) {
					pageToCall =
						"EDP0720_proposals_product_maint_others.jsp";
				} else if (userPO.getType().equals("9")) {
					pageToCall =
						"EDP0720_proposals_product_maint_renews.jsp";
				} else {
					if (appCode.equals("10")) {
						pageToCall =
							"EDP0720_proposals_product_maint_loans.jsp";
					} else if (appCode.equals("40")) {
						pageToCall =
							"EDP0720_proposals_product_maint_letters.jsp";
					} else if (appCode.equals("43")) {
						pageToCall =
							"EDP0720_proposals_product_maint_bailgranted.jsp";
					} else if (appCode.equals("90")) {
						pageToCall =
							"EDP0720_proposals_product_maint_lines.jsp";
					}
				}
			}
			try {
				flexLog("About to call Page: " + LangPath + pageToCall);
				callPage(LangPath + pageToCall, req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
	}


	protected void procRLISTDOFA(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDP072401Message msgL0724 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


					
		// Send Initial data
		try {
			msgL0724 = (EDP072401Message) mc.getMessageRecord("EDP072401");
			msgL0724.setH01USERID(user.getH01USR());
			msgL0724.setH01PROGRM("EDP0724");
			msgL0724.setH01TIMSYS(getTimeStamp());
			msgL0724.setH01SCRCOD("01");
			msgL0724.setH01OPECOD("0015");
			msgL0724.setE01DPANPR(userPO.getIdentifier());
			msgL0724.setE01DPATIP(req.getParameter("opt"));
			msgL0724.send();
			msgL0724.destroy();
			flexLog("EDP072401 Message Sent");
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

			if (newmessage.getFormatName().equals("EDP072401")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";

			   beanList.setNoResult(true);
				int ct = 0;
			   while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
				   if (ct >= datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					   System.out.println("MAX_ITERATION_REACHED_ERROR class:"	+ this.getClass().getName());
				   }

					msgL0724 = (EDP072401Message) newmessage;

					marker = msgL0724.getE01OPECDE();

					if (firstTime) {
						firstTime = false;
					} else {
					   beanList.setNoResult(false);
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgL0724);

						if (marker.equals("+")) {
							beanList.setShowNext(true);

							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("msgL0724", msgL0724);
				ses.setAttribute("userPO", userPO);

				try {

						if (msgError.getERRNUM().equals("0")) {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDP0720_proposals_dofa.jsp");
							callPage(
								LangPath + "EDP0720_proposals_dofa.jsp",
								req,
								res);
						} else {
							return;
//							flexLog(
//								"About to call Page: "
//									+ LangPath
//									+ "EDP0720_proposals_header_maintenance.jsp");
//
//							callPage(
//								LangPath + "EDP0720_proposals_header_maintenance.jsp",
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


	protected void procACTDOFA(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		JBListRec beanList = null;
		MessageRecord newmessage = null;
		EDP072401Message msgL0724 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			
			msgL0724 = (EDP072401Message) mc.getMessageRecord("EDP072401");
		  msgL0724.setH01USERID(user.getH01USR());
		  msgL0724.setH01PROGRM("EDP0724");
		  msgL0724.setH01TIMSYS(getTimeStamp());
		  msgL0724.setH01SCRCOD("01");
		  msgL0724.setH01OPECOD("0005");
		  msgL0724.setE01DPANPR(userPO.getIdentifier());
		  msgL0724.setE01DPATIP(req.getParameter("opt"));
		  msgL0724.setE01DPAM21(req.getParameter("E01DPAM21"));
		  msgL0724.setE01DPAM22(req.getParameter("E01DPAM22"));
		  msgL0724.setE01DPAM23(req.getParameter("E01DPAM23"));
		  msgL0724.setE01DPAM24(req.getParameter("E01DPAM24"));
		  msgL0724.setE01DPAM25(req.getParameter("E01DPAM25"));
		  msgL0724.setE01DPAM26(req.getParameter("E01DPAM26"));
		  msgL0724.setE01DPAM27(req.getParameter("E01DPAM27"));
		  msgL0724.send();
		  msgL0724.destroy();
		  
		  flexLog("EDP072401 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		// Receive Error
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
//				ses.setAttribute("userPO", userPO);


				msgError = (ELEERRMessage) newmessage;
				boolean IsNotError = msgError.getERRNUM().equals("0");
				if (IsNotError) {
					return;

//					procReqProductsList(mc, user, req, res, ses);
//					procReqGuaranteeList(mc, user, req, res, ses);
//					procReqCommentsList(mc, user, req, res, ses);
//					procReqRecaudosN(mc, user, req, res, ses);

//					try {
//
//						flexLog(
//							"About to call Page: "
//								+ LangPath
//								+ "EDP0720_proposals_header_maintenance.jsp");
//						callPage(
//							LangPath + "EDP0720_proposals_header_maintenance.jsp",
//							req,
//							res);
//					} catch (Exception e) {
//						flexLog("Exception calling page " + e);
//						e.printStackTrace();
//
//					}

				} else {
					msgError = (ELEERRMessage) newmessage;
					showERROR(msgError);
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
//					ses.setAttribute("chkList", beanList);
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0720_proposals_dofa.jsp");
					callPage(
						LangPath + "EDP0720_proposals_dofa.jsp",
						req,
						res);
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

			int screen = R_FILTER;

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
						case R_FILTER :
							procReqFilter(mc, msgUser, req, res, session);
							break;
						case R_LIST :
							procActionEnter(mc, msgUser, req, res, session);
							break;
						case R_ENTER_NEW_PROPOSAL :
							procReqEnterNewProposal(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_NEW_PROPOSAL :
							procReqNewProposal(mc, msgUser, req, res, session);
							break;

						case R_DETAIL_PRODUCT :
							procProductPos(mc, msgUser, req, res, session);
							break;
						case RNEWPROD :
							procRNewProd(mc, msgUser, req, res, session);
							break;
						case RDETPROD :
							procRDetProd(mc, msgUser, req, res, session);
							break;

						case RFMTHEADER :
							procRFmtHeader(mc, msgUser, req, res, session);
							break;
						case RSAVEPROD :
							procRSaveProd(mc, msgUser, req, res, session);
							break;
						case R_SAVE_HEADER :
							procSave400(mc, msgUser, req, res, session);
							break;

						case R_NEXT_ACTIVITY :
							procReqNext_Activity(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case RPRIORACTIV :
							procRPriorActiv(mc, msgUser, req, res, session);
							break;
						case R_SAVE_NEXT_ACTIVITY :
							procSave400Ruta(mc, msgUser, req, res, session);
							break;

						case RUPDGUAR :
							procRUPDGUAR(mc, msgUser, req, res, session);
							break;
						case RETCUSINF :
							//procRETCUSINF(mc, msgUser, req, res, session);
							break;
						case PRINTPROP :
							procPRINTPROP(mc, msgUser, req, res, session);
							break;
						case RETPRDINF :
							procRETPRDINF(mc, msgUser, req, res, session);
							break;
						case RLISTDOFA :
							procRLISTDOFA(mc, msgUser, req, res, session);
							break;
						case RACTDOFA :
							procACTDOFA(mc, msgUser, req, res, session);
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
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
			} finally {
				s.close();
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