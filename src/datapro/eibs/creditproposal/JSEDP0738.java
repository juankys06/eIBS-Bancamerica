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

public class JSEDP0738 extends datapro.eibs.master.SuperServlet {

	// entering options
	protected static final int R_FILTER = 100; //
	protected static final int R_LIST = 200; //
	protected static final int R_ACT = 300; //


	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0738() {
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
		}

		ses.setAttribute("userPO", userPO);
		ses.setAttribute("error", msgError);
		/***********************************************/
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDP0738_asignusers_filter.jsp");
			callPage(LangPath + "EDP0738_asignusers_filter.jsp", req, res);
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
		EDP074601Message msgList746 = null; 
		JBList beanList746 = null;
		beanList746 = new datapro.eibs.beans.JBList();
		String myOption = "";
		String myFlag = "";

		boolean IsNotError = false;
		String DESINCORPORADAS = "";
		String RENOVAR = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");


		/*---------------------------------------------------*/
		// Send Initial data for DDL Producto
		/*---------------------------------------------------*/
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
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDP074601")) {

				String marker = "";
				myOption = "<option value=\"\"></option>";
				beanList746.addRow(myFlag, myOption);

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
			ses.setAttribute("error", msgError);

		} catch (Exception ex) {
			ex.printStackTrace();
			flexLog("Error: " + ex);
			throw new RuntimeException("Socket Communication Error Producto");
		}
		/*---------------------------------------------------------------*/

		if (req.getParameter("E01PLPBNK") != null) {
			userPO.setBank(req.getParameter("E01PLPBNK"));
		}

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
			
			//msgList.setE01PLPBNK(userPO.getBank());
			//msgList.setE01PLPBRN(userPO.getBranch());

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
			if (req.getParameter("E01FILRUT") != null) {
				msgList.setE01PLPCN4(req.getParameter("E01FILRUT"));
			}
			//
			if (req.getParameter("E01FILPRD") != null) {
				msgList.setE01FILPRD(req.getParameter("E01FILPRD"));
			}
			//			
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

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDP072001Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {

					if (msgError.getERRNUM().equals("0")) {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0738_asignusers_list.jsp");
						callPage(
							LangPath + "EDP0738_asignusers_list.jsp",
							req,
							res);
					} else {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDP0738_asignusers_filter.jsp");

						callPage(
							LangPath + "EDP0738_asignusers_filter.jsp",
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



	protected void procAct(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		JBListRec beanList = null;
		MessageRecord newmessage = null;
		EDP072001Message msgSearch = null;
		ELEERRMessage msgError = null;
		EDP072001Message msgList = null;
		JBObjList bl = (JBObjList) ses.getAttribute("EDP072001Help");
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String check = "";
		int numrec = 0;
		if(req.getParameter("RECNUM")!=null){
			   numrec = Integer.parseInt(req.getParameter("RECNUM"));
		   }
	try {
	   for (int i = 1; i <= numrec; i++) {

		bl.setCurrentRow(i);

		if(req.getParameter("ASIGUSR" + i)!=null){

		try {
		msgList = (EDP072001Message) mc.getMessageRecord("EDP072001");
		  msgList.setH01USERID(user.getH01USR());
		  msgList.setH01PROGRM("EDP0720");
		  msgList.setH01TIMSYS(getTimeStamp());
		  msgList.setH01SCRCOD("01");
		  msgList.setH01OPECOD("0030");
		  
		  msgList.setE01PLPNPR(req.getParameter("PLPNPR" + i));
		  msgList.setE01PLPUID(req.getParameter("E01PLPUID"));

		  msgList.send();
		  msgList.destroy();
		  flexLog("EDP072001 Message Sent");
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
//					beanList.setFlag("S", beanList.getCurrentRow());
				} else {
					msgError = (ELEERRMessage) newmessage;
					showERROR(msgError);
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
//					ses.setAttribute("chkList", beanList);


				try {

					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDP0738_asignusers_list.jsp");
					callPage(
						LangPath + "EDP0738_asignusers_list.jsp",
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
			procReqFilter(mc, user, req, res, ses);
			
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
						case R_ACT :
							procAct(mc, msgUser, req, res, session);
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