package datapro.eibs.bolgaran;

/**
 * Insert the type's description here.
 * Creation date: (3/19/02 10:08:55 PM)
 * @author: Ramses Amaro
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELC500001Message;
import datapro.eibs.beans.ELC500002Message;
import datapro.eibs.beans.ELC500003Message;
import datapro.eibs.beans.ELC500004Message;
import datapro.eibs.beans.ELC500005Message;
import datapro.eibs.beans.ELC500006Message;
import datapro.eibs.beans.ELC570001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSELC5000 extends datapro.eibs.master.SuperServlet {

	// Request 

	//Mantenedor de Boletas
	protected static final int R_ENTER_MANT = 200;
	protected static final int R_NEW_BG = 100;
	protected static final int R_ENTER_INQ = 300;
	protected static final int R_APROBAR = 400;

	//Mantenedor de Aval
	protected static final int R_LIST_AVAL = 1;

	//Mantenedor de prorroga	
	protected static final int R_PRORROGA = 3;

	// actions
	protected static final int A_LIST_AVAL = 2;
	protected static final int A_PRORROGA = 4;
	protected static final int A_BOLETA = 5;
	protected static final int A_AVAL = 6;
	protected static final int A_ENTER_INQ = 7;
	protected static final int A_ENTER_MANT = 8;
	protected static final int A_APROBAR = 9;
	protected static final int R_AVI_BEN = 20;
	protected static final int A_AVI_BEN = 21;
	
	protected static final int PLAN_DISMINUCION_LISTAR		= 310;
	protected static final int PLAN_DISMINUCION_GRABAR		= 320;
	protected static final int PLAN_DISMINUCION_CANCELAR	= 330;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSELC5000() {
		super();
	}
	/**
	 * This method was created in VisualAge.
	 */
	public void destroy() {

		flexLog("free resources used by JSELC5000");

	}
	/**
	 * This method was created in VisualAge.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected boolean procRecMaintData(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses, 
		boolean flagMantenimiento)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELC500001Message bolgaran = null;
		ELC500005Message planDis = null;
		ELC500006Message garantizaBean = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

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
		if(IsNotError){
			try {
				int i = 0;
				do{
					newmessage = mc.receiveMessage();
					flexLog("newmessage["+ i +"]:[" + newmessage + "]");
					i++;
					if (newmessage.getFormatName().equals("ELC500001")) {
						bolgaran = (ELC500001Message) newmessage;
						ses.setAttribute("bolgaran", bolgaran);
						ses.setAttribute("error", msgError);
					}else if (newmessage.getFormatName().equals("ELC500005")) {
						planDis = (ELC500005Message) newmessage;
						ses.setAttribute("planDis", planDis);
						break;	
					}else if (newmessage.getFormatName().equals("ELC500006")) {
						garantizaBean = (ELC500006Message) newmessage;
						ses.setAttribute("garantizaBean", garantizaBean);
						if (bolgaran.getE01LCMFDI().equals("N"))break;
					} else	flexLog("Message " + newmessage.getFormatName() + " received.");
				}while(flagMantenimiento && i<5);
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}else{
			try {
				newmessage = mc.receiveMessage();
				flexLog("newmessage[" + newmessage + "]");
				if (newmessage.getFormatName().equals("ELC500001")) {
					bolgaran = (ELC500001Message) newmessage;
					ses.setAttribute("bolgaran", bolgaran);
					ses.setAttribute("error", msgError);
				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}			
		}
		return IsNotError;
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqNewBoleta(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELC500001Message msgBG = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			userPO = new datapro.eibs.beans.UserPos();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		// Send Initial data
		try {
			msgBG = (ELC500001Message) mc.getMessageRecord("ELC500001");
			msgBG.setH01USERID(user.getH01USR());
			msgBG.setH01PROGRM("ELC5000");
			msgBG.setH01TIMSYS(getTimeStamp());
			msgBG.setH01SCRCOD("01");
			msgBG.setH01OPECOD("0001");
			msgBG.setE01LCMACD("43");
			try {
				msgBG.setE01LCMPRO(req.getParameter("E01LCMPRO"));
			} catch (Exception e) {
				msgBG.setE01LCMPRO("0");
			}
			try {
				msgBG.setE01LCMACC(req.getParameter("E01LCMACC"));
			} catch (Exception e) {
			}
			msgBG.send();
			msgBG.destroy();
			flexLog("ELC500001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc, user, req, res, ses, false)) { // There are no errors
				userPO.setPurpose("NEW");
				ses.setAttribute("userPO", userPO);
				try {
					flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
					callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: /servlet/datapro.eibs.products.JSESD0711?TYPE=43");
					//res.sendRedirect(super.srctx +"/servlet/datapro.eibs.products.JSESD0711?TYPE=10");
					String firstLink =
						super.webAppPath
							+ LangPath
							+ "ESD0711_products_detail.jsp?appcode="
							+ req.getParameter("appcode").trim()
							+ "&typecode="
							+ req.getParameter("typecode").trim()
							+ "&generic="
							+ req.getParameter("generic").trim()
							+ "&title="
							+ req.getParameter("title").trim()
							+ "&bank="
							+ req.getParameter("bank").trim();
					res.setContentType("text/html");
					res.setHeader("Pragma", "No-cache");
					res.setDateHeader("Expires", 0);
					res.setHeader("Cache-Control", "no-cache");
					PrintWriter out = res.getWriter();
					printProdFrame(out, firstLink, LangPath);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	protected void procReqListAval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
		ELC500002Message msgAval = null;
		ELC500001Message msgBol = null;
		JBObjList bolaval = null;
		UserPos userPO = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		// Send Initial data
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		msgBol = (datapro.eibs.beans.ELC500001Message) ses.getAttribute("bolgaran");

		userPO.setHeader20("");
		userPO.setHeader21("");

		try {
			msgAval = (ELC500002Message) mc.getMessageRecord("ELC500002");
			msgAval.setH02USERID(user.getH01USR());
			msgAval.setH02PROGRM("ELC5000");
			msgAval.setH02TIMSYS(getTimeStamp());
			msgAval.setH02SCRCOD("01");
			msgAval.setH02OPECOD("0002");
			msgAval.setE02LCMACC(msgBol.getE01LCMACC());
			msgAval.send();
			msgAval.destroy();
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

		// Receive Data

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELC500002")) {
				flexLog("ELC500002 Message Received");
				String marker = "";
				bolaval = new JBObjList();
				int ct = 0;
while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
	if (ct == datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
	System.out.println("MAX_ITERATION_REACHED_ERROR class:" + this.getClass().getName());
				}


					msgAval = (ELC500002Message) newmessage;

					marker = msgAval.getH02FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						bolaval.addRow(msgAval);
					}
					newmessage = mc.receiveMessage();
				}
				ses.setAttribute("bolaval", bolaval);
				ses.setAttribute("error", msgError);
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_list_aval.jsp");
						callPage(LangPath + "ELC5000_bg_list_aval.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
						callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
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

	protected void procReqProrroga(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ELC500003Message msgPrga = null;
		ELC500001Message msgBol = null;
		UserPos userPO = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		// Send Initial data
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		msgBol = (datapro.eibs.beans.ELC500001Message) ses.getAttribute("bolgaran");

		try {
			msgPrga = (ELC500003Message) mc.getMessageRecord("ELC500003");
			msgPrga.setH03USERID(user.getH01USR());
			msgPrga.setH03PROGRM("ELC5000");
			msgPrga.setH03TIMSYS(getTimeStamp());
			msgPrga.setH03SCRCOD("01");
			msgPrga.setH03OPECOD("0002");
			msgPrga.setE03LCMACC(msgBol.getE01LCMACC());
			msgPrga.send();
			msgPrga.destroy();
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

		// Receive Data

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELC500003")) {
				flexLog("ELC500003 Message Received");

				msgPrga = (ELC500003Message) newmessage;

				ses.setAttribute("bolprga", msgPrga);
				ses.setAttribute("error", msgError);
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_prorroga.jsp");
						callPage(LangPath + "ELC5000_bg_prorroga.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
						callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
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
	protected void procActionListAval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELC500002Message msgAval = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList bolaval = null;

		bolaval = (JBObjList) ses.getAttribute("bolaval");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
				row = 0;
			}

			switch (option) {

				case 3 : // Delete
					boolean IsNotError = true;
					bolaval.setCurrentRow(row);
					try {
						msgAval = (ELC500002Message) bolaval.getRecord();
						msgAval.setH02USERID(user.getH01USR());
						msgAval.setH02PROGRM("ERA0010");
						msgAval.setH02TIMSYS(getTimeStamp());
						msgAval.setH02SCRCOD("01");
						msgAval.setH02OPECOD("0009");

						mc.sendMessage(msgAval);
						msgAval.destroy();
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
					// Receive Confirmation
					try {
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);
							if (IsNotError) { // There are no errors
								procReqListAval(mc, user, req, res, ses);
							} else {
								try {
									flexLog("Putting java beans into the session");
									ses.setAttribute("error", msgError);
									res.sendRedirect(super.srctx +LangPath + "ELC5000_bg_list_aval.jsp?ROW=" + row);
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
					break;

				case 1 : // New
					userPO.setHeader20("DO_NEW");
					msgAval = new ELC500002Message();
					userPO.setHeader21(super.webAppPath + LangPath + "ELC5000_bg_aval.jsp?seq=0");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgAval", msgAval);
					res.sendRedirect(super.srctx +LangPath + "ELC5000_bg_list_aval.jsp?ROW=" + row);
					break;

				case 2 : // Maintenance
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(
						super.webAppPath + LangPath + "ELC5000_bg_aval.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx +LangPath + "ELC5000_bg_list_aval.jsp?ROW=" + row);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	/**
	 * Metodos del Menu "Mantenedor de Boletas 
	 */
	protected void procActionBoleta(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELC500001Message bolgaran = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
				
		String _E06SPIM01 = req.getParameter("E06SPIM01")==null?"":req.getParameter("E06SPIM01");

		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			bolgaran = (ELC500001Message) ses.getAttribute("bolgaran");
			//mc.getMessageRecord("ELC500001");
			bolgaran.setH01USERID(user.getH01USR());
			bolgaran.setH01PROGRM("ELC5000");
			bolgaran.setH01TIMSYS(getTimeStamp());
			bolgaran.setH01SCRCOD("01");
			bolgaran.setH01OPECOD("0003"); //3 de validar; 5 de actualizar

			// all the fields here
			Enumeration enu = bolgaran.fieldEnumeration();
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
			mc.sendMessage(bolgaran);
			bolgaran.destroy();
			
			// Receive Error Message
			try {
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				}else flexLog("Message " + newmessage.getFormatName() + " received.");
				
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELC500001")) {
					bolgaran = (ELC500001Message) newmessage;
					//ses.setAttribute("bolgaran", bolgaran);
					//ses.setAttribute("error", msgError);
				}else flexLog("Message " + newmessage.getFormatName() + " received.");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
			
			if( !IsNotError ){
				ELC500005Message planDis = null;
				if( bolgaran.getE01LCMFDI().equals("Y") ){
					planDis = (ELC500005Message) ses.getAttribute("planDis");
				}
				ELC500006Message garantizaBean = new ELC500006Message();
				garantizaBean.setE06SPIM01( _E06SPIM01 );
				procAbrirboletaError( mc, user, req, res, ses, bolgaran, planDis, garantizaBean, msgError);
				return;
			}
			
			ELC500005Message planDis = null;
			if( IsNotError ){ 
				if( bolgaran.getE01LCMFDI().equals("Y") ){
					planDis = (ELC500005Message) ses.getAttribute("planDis");
//					planDis.setE05LCPISA();planDis.setE05LCPISM();planDis.setE05LCPISD();
				}else {
					planDis = new ELC500005Message();
				}
				planDis.setH05USERID(user.getH01USR());
				planDis.setH05PROGRM("ELC5000");
				planDis.setH05SCRCOD("01");
				planDis.setH05TIMSYS(getTimeStamp());
				planDis.setH05OPECOD("0003");
				planDis.setE05LCPBNK( bolgaran.getE01LCMBNK() );
				planDis.setE05LCPBRN( bolgaran.getE01LCMBRN() );
				planDis.setE05LCPCCY( bolgaran.getE01LCMCCY() );// Moneda
				planDis.setE05LCPMAC( bolgaran.getE01LCMACC() );
				planDis.setE05LCPCUN( bolgaran.getE01LCMCUN() );
				planDis.setE05LCPMNT( bolgaran.getE01LCMC10() );
				// Emision					           
				planDis.setE05LCPISM( bolgaran.getE01LCMIDD() ); // al verre 1
				planDis.setE05LCPISD( bolgaran.getE01LCMIDM() ); // al verre 1
				planDis.setE05LCPISA( bolgaran.getE01LCMIDY() );
				// Expiracion
				planDis.setE05LCPEXM( bolgaran.getE01LCMEXD() ); // al verre 2
				planDis.setE05LCPEXD( bolgaran.getE01LCMEXM() ); // al verre 2
				planDis.setE05LCPEXY( bolgaran.getE01LCMEXY() );
				mc.sendMessage(planDis);
				planDis.destroy();
				// Receive Error Message
				try {
					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						showERROR(msgError);
					}  else flexLog("Message " + newmessage.getFormatName() + " received.");
					
					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELC500005")) {
						planDis = (ELC500005Message) newmessage;
						//ses.setAttribute("planDis", planDis);
					} else flexLog("Message " + newmessage.getFormatName() + " received.");

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
			}
			
			if( !IsNotError ){
				ELC500006Message garantizaBean = new ELC500006Message();
				garantizaBean.setE06SPIM01( _E06SPIM01 );
				procAbrirboletaError( mc, user, req, res, ses, bolgaran, planDis, garantizaBean, msgError);
				return;
			}
			
			ELC500006Message garantizaBean = new ELC500006Message();
			//Guardar Datos
			if( IsNotError ){
				//Enviando Bean Boleta
				bolgaran.setH01OPECOD("0005");
				mc.sendMessage(bolgaran);
				bolgaran.destroy();

				// Receive Message
				try {
					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						showERROR(msgError);
					}else flexLog("Message " + newmessage.getFormatName() + " received.");

					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELC500001")) {
						bolgaran = (ELC500001Message) newmessage;
						ses.setAttribute("bolgaran", bolgaran);
					} else flexLog("Message " + newmessage.getFormatName() + " received.");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
				
				//Enviando Bean Plan Disminucion
				planDis.setE05LCPMAC( bolgaran.getE01LCMACC() );
				planDis.setH05OPECOD("0005");
				mc.sendMessage(planDis);
				planDis.destroy();

				// Receive Message
				try {
					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						showERROR(msgError);
					}else flexLog("Message " + newmessage.getFormatName() + " received.");

					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELC500005")) {
						planDis = (ELC500005Message) newmessage;
						ses.setAttribute("planDis", planDis);
					} else flexLog("Message " + newmessage.getFormatName() + " received.");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}

				// Enviando Bean con Campo garantizado
				garantizaBean.setH06USERID(user.getH01USR());
				garantizaBean.setH06PROGRM("ELC5000");
				garantizaBean.setH06TIMSYS(getTimeStamp());
				garantizaBean.setH06SCRCOD("01");
				garantizaBean.setH06OPECOD("0005");
				garantizaBean.setE06SPIACC( bolgaran.getE01LCMACC() );
				garantizaBean.setE06SPIM01( _E06SPIM01 );
				mc.sendMessage(garantizaBean);
				garantizaBean.destroy();

				// Receive Message
				try {
					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						showERROR(msgError);
					}else flexLog("Message " + newmessage.getFormatName() + " received.");

					newmessage = mc.receiveMessage();
					if (newmessage.getFormatName().equals("ELC500006")) {
						garantizaBean = (ELC500006Message) newmessage;
						ses.setAttribute("garantizaBean", garantizaBean);
					} else flexLog("Message " + newmessage.getFormatName() + " received.");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}

			}
			//flexLog("ELC500001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		//procRecMaintData(mc, user, req, res, ses, false)
		if (IsNotError) { // There are no errors
			if (userPO.getPurpose().equals("NEW")) {
				bolgaran = (ELC500001Message) ses.getAttribute("bolgaran");
				
				userPO.setIdentifier(bolgaran.getE01LCMACC());
				userPO.setPurpose("MAINTENANCE");
				ses.setAttribute("userPO", userPO);
				try {
					flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
					callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				try {
					req.setAttribute("E06SPIM01", _E06SPIM01);
					flexLog("About to call Page: " + LangPath + "ELC5000_bg_enter_maint.jsp");
					callPage(LangPath + "ELC5000_bg_enter_maint.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} else { // There are errors		 		 	    
			try {
				flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
				callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}

	}

	protected void procReqEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ELC500001Message bolgaran = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			bolgaran = new ELC500001Message();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		try {
			userPO = new UserPos();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("bolgaran", bolgaran);
		ses.setAttribute("userPO", userPO);

		try {
			flexLog("About to call Page: " + LangPath + "ELC5000_bg_enter_maint.jsp");
			callPage(LangPath + "ELC5000_bg_enter_maint.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELC500001Message msgBol = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String acc = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgBol = (ELC500001Message) mc.getMessageRecord("ELC500001");
			msgBol.setH01USERID(user.getH01USR());
			msgBol.setH01PROGRM("ELC5000");
			msgBol.setH01TIMSYS(getTimeStamp());
			msgBol.setH01SCRCOD("01");
			msgBol.setH01OPECOD("0002");
			msgBol.setE01LCMACD("43");
			try {
				msgBol.setE01LCMACC(req.getParameter("E01LCMACC"));
				userPO.setIdentifier(msgBol.getE01LCMACC());
			} catch (Exception e) {
				msgBol.setE01LCMACC(userPO.getIdentifier());
			}

			msgBol.send();
			msgBol.destroy();
			flexLog("ELC500001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc, user, req, res, ses, true)) { // There are no errors
				try {
					ses.setAttribute("userPO", userPO);
					// Mantenimiento Boleta Garantia
					flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
					callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog("About to call Page: " + LangPath + "ELC5000_bg_enter_maint.jsp");
					callPage(LangPath + "ELC5000_bg_enter_maint.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionAval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELC500002Message msgAval = null;
		ELEERRMessage msgError = null;
		JBObjList bolaval = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
		int row = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			//option = Integer.parseInt(req.getParameter("opt"));

			flexLog("Send Initial Data");
			msgAval = (ELC500002Message) mc.getMessageRecord("ELC500002");
			msgAval.setH02USERID(user.getH01USR());
			msgAval.setH02PROGRM("ELC5000");
			msgAval.setH02TIMSYS(getTimeStamp());
			msgAval.setH02SCRCOD("01");
			msgAval.setH02OPECOD("0005");

			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception ex) {
				row = 0;
			}

			// all the fields here
			java.util.Enumeration enu = msgAval.fieldEnumeration();
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

			msgAval.send();
			//mc.sendMessage(msgAval);
			msgAval.destroy();
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

			if (newmessage.getFormatName().equals("ELC500002")) {
				try {
					msgAval = new datapro.eibs.beans.ELC500002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgAval = (ELC500002Message) newmessage;
				// showESD008004(msgAval);

				if (IsNotError) { // There are no errors
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println(
						"		top.opener.window.location.href='"
							+ super.webAppPath
							+ "/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=1'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					//ses.setAttribute("userPO", userPO);
					if (userPO.getHeader20().equals("DO_MAINT")) {
						bolaval = (datapro.eibs.beans.JBObjList) ses.getAttribute("bolaval");
						bolaval.setRecord(msgAval, row);
						ses.setAttribute("bolaval", bolaval);
					} else
						ses.setAttribute("msgAval", msgAval);

					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_aval.jsp?ROW=" + row);
						res.sendRedirect(super.srctx +LangPath + "ELC5000_bg_aval.jsp?ROW=" + row);
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

	protected void procActionPrga(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELC500003Message msgPrga = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {

			flexLog("Send Initial Data");
			msgPrga = (ELC500003Message) mc.getMessageRecord("ELC500003");
			msgPrga.setH03USERID(user.getH01USR());
			msgPrga.setH03PROGRM("ELC5000");
			msgPrga.setH03TIMSYS(getTimeStamp());
			msgPrga.setH03SCRCOD("01");
			msgPrga.setH03OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgPrga.fieldEnumeration();
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

			msgPrga.send();
			//mc.sendMessage(msgPrga);
			msgPrga.destroy();
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

			if (newmessage.getFormatName().equals("ELC500003")) {
				try {
					msgPrga = new datapro.eibs.beans.ELC500003Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgPrga = (ELC500003Message) newmessage;

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
						callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("bolprga", msgPrga);
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_prorroga.jsp");
						callPage(LangPath + "ELC5000_bg_prorroga.jsp", req, res);
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
	protected void procReqEnterInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ELC500001Message bolgaran = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			bolgaran = new ELC500001Message();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		try {
			userPO = new UserPos();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO.setPurpose("INQUIRY");
		ses.setAttribute("bolgaran", bolgaran);
		ses.setAttribute("userPO", userPO);

		try {
			flexLog("About to call Page: " + LangPath + "ELC5000_bg_enter_inq.jsp");
			callPage(LangPath + "ELC5000_bg_enter_inq.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionEnterInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			//try {
			//	userPO.setCusNum(req.getParameter("CUSNUM").trim());
			//} catch (Exception e) {
			//	userPO.setCusNum("0");
			//}
			try {
				if (req.getParameter("E01LCMACC") != null) {				
					userPO.setIdentifier(req.getParameter("E01LCMACC").trim());
				}
			} catch (Exception e) {
				userPO.setIdentifier("0");
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			//if (userPO.getIdentifier().equals("")) {
			//	procReqCollBolGar(mc, user, req, res, ses);
			//} else {
				procActionConsBolGar(mc, user, req, res, ses);
			//}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procReqCollBolGar(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELC570001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int type = 0;
		String num = "";

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ELC570001Message) mc.getMessageRecord("ELC570001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ELC5000");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01FLGMAS("S");

			try {
				msgList.setELCCUN(userPO.getCusNum());
			} catch (Exception e) {
			}
			msgList.send();
			msgList.destroy();
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

				try {
					flexLog("About to call Page: " + LangPath + "ELC5000_bg_enter_inq.jsp");
					callPage(LangPath + "ELC5000_bg_enter_inq.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else
				if (newmessage.getFormatName().equals("ELC570001")) {
					try {
						beanList = new datapro.eibs.beans.JBList();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String ref = "";
					java.math.BigDecimal totalGross = new java.math.BigDecimal(0);
					java.math.BigDecimal totalUsed = new java.math.BigDecimal(0);
					java.math.BigDecimal totalAvailable = new java.math.BigDecimal(0);

					int ct = 0;
while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
	if (ct == datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
	System.out.println("MAX_ITERATION_REACHED_ERROR class:" + this.getClass().getName());
				}


						msgList = (ELC570001Message) newmessage;

						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {

							if (firstTime) {
								firstTime = false;
								userPO.setCusNum(msgList.getELCCUN().trim());
								userPO.setCusName(msgList.getCUSNA1().trim());
							}

							myFlag = "";
							//totalGross = totalGross.add(msgList.getBigDecimalE04ROCNBL());
							//totalUsed = totalUsed.add(msgList.getBigDecimalE04ROCCOP());
							//totalAvailable = totalAvailable.add(msgList.getBigDecimalE04DISPON());

							myRow = new StringBuffer("<TR>");
							ref = msgList.getLCMACC();
							myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic("
								+ ref
								+ ")\">"
								+ Util.formatCell(msgList.getLCMACC())
								+ "</A></TD>");

							String strestado = "";
							if (msgList.getELCSTS().equals("C"))
								strestado = "Cancelado";
							else
								if (msgList.getELCSTS().equals("X"))
									strestado = "Con Aviso de Pago";
								else
									strestado = "Vigente";

							myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic("
								+ ref
								+ ")\">"
								+ Util.formatCell(strestado)
								+ "</A></TD>");
							myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic("
								+ ref
								+ ")\">"
								+ Util.formatCell(msgList.getLCMCCY())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic("
								+ ref
								+ ")\">"
								+ Util.formatCell(msgList.getLCMOAM())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic("
								+ ref
								+ ")\">"
								+ Util.formatCell(msgList.getELCRUB())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic("
								+ ref
								+ ")\">"
								+ Util.formatCell(msgList.getELCNOB())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic("
								+ ref
								+ ")\">"
								+ Util.formatDate(msgList.getLCMIDD(), msgList.getLCMIDM(), msgList.getLCMIDY())
								+ "</A></TD>");
							myRow.append("<TD NOWRAP><A HREF=\"javascript:goCollBasic("
								+ ref
								+ ")\">"
								+ Util.formatDate(msgList.getLCMEED(), msgList.getLCMEEM(), msgList.getLCMEEY())
								+ "</A></TD>");
							myRow.append("</TR>");
							beanList.addRow(myFlag, myRow.toString());

						}

						newmessage = mc.receiveMessage();
					}

					userPO.setHeader3(Util.fcolorCCY(totalGross.toString()));
					userPO.setHeader4(Util.fcolorCCY(totalUsed.toString()));
					userPO.setHeader5(Util.fcolorCCY(totalAvailable.toString()));

					flexLog("Putting java beans into the session");
					ses.setAttribute("collList", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_Bol_Gar_list.jsp");
						callPage(LangPath + "ELC5000_Bol_Gar_list.jsp", req, res);

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

	protected void procActionConsBolGar(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ELC500003Message msgPrga = null;
		ELC500002Message msgAval = null;
		ELC500001Message msgBG = null;
		ELC500001Message msgBG2 = null;
		ELC500004Message msgAviBen = null;

		UserPos userPO = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		JBObjList bolaval = null;
		ELC500005Message planDis = null;
		ELC500006Message garantizaBean = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		// Send Initial data
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			msgBG = (ELC500001Message) mc.getMessageRecord("ELC500001");
			msgBG.setH01USERID(user.getH01USR());
			msgBG.setH01PROGRM("ELC5000");
			msgBG.setH01TIMSYS(getTimeStamp());
			msgBG.setH01SCRCOD("01");
			msgBG.setH01OPECOD("0004");
			//	 	msgBG.setE01LCMACC(req.getParameter("E01LCMACC"));
			msgBG.setE01LCMACC(userPO.getIdentifier().trim());
			msgBG.send();
			msgBG.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		try {
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				if (userPO.getPurpose().equals("APPROVAL_INQ")) {
					try {
						flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_enter_inq.jsp");
						callPage(LangPath + "ELC5000_bg_enter_inq.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else {

				int ctAcc = 0;
				while (ctAcc++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					if (ctAcc == datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
					System.out.println("MAX_ITERATION_REACHED_ERROR class:" + this.getClass().getName());
								}


					if (newmessage.getFormatName().equals("ELC500001")) {

						msgBG = (ELC500001Message) newmessage;
						ses.setAttribute("bolgaran", msgBG);

					} else if (newmessage.getFormatName().equals("ELC500002")) {

						String marker = "";
						bolaval = new JBObjList();
						int ct = 0;
						while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
							if (ct == datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
								System.out.println("MAX_ITERATION_REACHED_ERROR class:" + this.getClass().getName());
							}
	
							msgAval = (ELC500002Message) newmessage;
							marker = msgAval.getH02FLGMAS();
	
							if (marker.equals("*")) {
								break;
							} else {
								bolaval.addRow(msgAval);
							}
							newmessage = mc.receiveMessage();
						}
						ses.setAttribute("bolaval", bolaval);

						} else if (newmessage.getFormatName().equals("ELC500003")) {
								msgPrga = (ELC500003Message) newmessage;
								ses.setAttribute("bolprga", msgPrga);
								
						} else	if (newmessage.getFormatName().equals("ELC500004")) {
							msgAviBen = (ELC500004Message) newmessage;
							ses.setAttribute("bolaviben", msgAviBen);
							break;
						}else {
							flexLog("Message " + newmessage.getFormatName() + " received.");
							break;
						}
						/*else if (newmessage.getFormatName().equals("ELC500005")) {
							planDis = (ELC500005Message) newmessage;
							ses.setAttribute("planDis", planDis);
								
						}else if (newmessage.getFormatName().equals("ELC500006")) {
							garantizaBean = (ELC500006Message) newmessage;
							ses.setAttribute("garantizaBean", garantizaBean);
							break;
														
						}*/ 
								
					newmessage = mc.receiveMessage();
					System.out.println("newmessage:" + newmessage);
				}
				userPO.setIdentifier(msgBG.getE01LCMACC());
				ses.setAttribute("userPO", userPO);
			}
			// Send Initial data
			//  500005 y 500006
			try {
				msgBG2 = (ELC500001Message) mc.getMessageRecord("ELC500001");
				msgBG2.setH01USERID(user.getH01USR());
				msgBG2.setH01PROGRM("ELC5000");
				msgBG2.setH01TIMSYS(getTimeStamp());
				msgBG2.setH01SCRCOD("01");
				msgBG2.setH01OPECOD("0002");
				msgBG2.setE01LCMACD("43");
				try {
					msgBG2.setE01LCMACC(req.getParameter("E01LCMACC"));
					userPO.setIdentifier(msgBG2.getE01LCMACC());
				} catch (Exception e) {
					msgBG2.setE01LCMACC(userPO.getIdentifier());
				}

				msgBG2.send();
				msgBG2.destroy();
				flexLog("ELC500001 Message Sent");
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
				} else flexLog("Message " + newmessage.getFormatName() + " received.");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
			// Receive Data
			if( IsNotError ){
				
				try {
					int i = 0;
					do{
						newmessage = mc.receiveMessage();
						flexLog("newmessage["+ i +"]:[" + newmessage + "]");
						i++;
						if (newmessage.getFormatName().equals("ELC500005")) {
							planDis = (ELC500005Message) newmessage;
							ses.setAttribute("planDis", planDis);
							break;	
						}else if (newmessage.getFormatName().equals("ELC500006")) {
							garantizaBean = (ELC500006Message) newmessage;
							ses.setAttribute("garantizaBean", garantizaBean);
							if (msgBG2.getE01LCMFDI().equals("N"))break;
						} else	flexLog("Message " + newmessage.getFormatName() + " received.");
					}while(i<5);
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_inq_basic.jsp");
						callPage(LangPath + "ELC5000_bg_inq_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	//JLS

	protected void procReqAvisoBen(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ELC500004Message msgAviBen = null;
		ELC500001Message msgBol = null;
		UserPos userPO = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		// Send Initial data
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		msgBol = (datapro.eibs.beans.ELC500001Message) ses.getAttribute("bolgaran");

		try {
			msgAviBen = (ELC500004Message) mc.getMessageRecord("ELC500004");
			msgAviBen.setH04USERID(user.getH01USR());
			msgAviBen.setH04PROGRM("ELC5000");
			msgAviBen.setH04TIMSYS(getTimeStamp());
			msgAviBen.setH04SCRCOD("01");
			msgAviBen.setH04OPECOD("0002");
			msgAviBen.setE04LCMACC(msgBol.getE01LCMACC());
			msgAviBen.send();
			msgAviBen.destroy();
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

		// Receive Data

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELC500004")) {
				flexLog("ELC500004 Message Received");

				msgAviBen = (ELC500004Message) newmessage;

				ses.setAttribute("bolprga", msgAviBen);
				ses.setAttribute("error", msgError);
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_aviso_bene.jsp");
						callPage(LangPath + "ELC5000_bg_aviso_bene.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
						callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
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

	protected void procActionAvisoBen(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELC500004Message msgAviBen = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {

			flexLog("Send Initial Data");
			msgAviBen = (ELC500004Message) mc.getMessageRecord("ELC500004");
			msgAviBen.setH04USERID(user.getH01USR());
			msgAviBen.setH04PROGRM("ELC5000");
			msgAviBen.setH04TIMSYS(getTimeStamp());
			msgAviBen.setH04SCRCOD("01");
			msgAviBen.setH04OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgAviBen.fieldEnumeration();
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

			msgAviBen.send();
			msgAviBen.destroy();
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

			if (newmessage.getFormatName().equals("ELC500004")) {
				try {
					msgAviBen = new datapro.eibs.beans.ELC500004Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgAviBen = (ELC500004Message) newmessage;

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
						callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("bolprga", msgAviBen);
					try {
						flexLog("About to call Page: " + LangPath + "ELC5000_bg_aviso_bene.jsp");
						callPage(LangPath + "ELC5000_bg_aviso_bene.jsp", req, res);
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
	//JLS

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws javax.servlet.ServletException, java.io.IOException {

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

			int screen = R_ENTER_MANT;

			try {

				flexLog("Screen  Number: " + screen);

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				try {
					flexLog("Opennig Socket Connection");

					s = new Socket(super.hostIP, super.iniSocket + 7);
					//if (screen == A_ENTER_INQ) {
					//	if (req.getParameter("E01LCMACC").trim().equals("")) {
					//		s = new Socket(super.hostIP, super.iniSocket + 999);
					//	}
					//}
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					switch (screen) {
						case R_ENTER_MANT :
							procReqEnterMaint(mc, msgUser, req, res, session);
							break;
						case R_ENTER_INQ :
							procReqEnterInq(mc, msgUser, req, res, session);
							break;
						case R_NEW_BG :
							procReqNewBoleta(mc, msgUser, req, res, session);
							break;
						case R_LIST_AVAL :
							procReqListAval(mc, msgUser, req, res, session);
							break;
						case R_PRORROGA :
							procReqProrroga(mc, msgUser, req, res, session);
							break;
						case R_AVI_BEN :
							procReqAvisoBen(mc, msgUser, req, res, session);
							break;
						case A_BOLETA :
							procActionBoleta(mc, msgUser, req, res, session);
							break;
						case A_AVAL :
							procActionAval(mc, msgUser, req, res, session);
							break;
						case A_LIST_AVAL :
							procActionListAval(mc, msgUser, req, res, session);
							break;
						case A_ENTER_MANT :
							procActionEnterMaint(mc, msgUser, req, res, session);
							break;
						case A_ENTER_INQ :
							procActionEnterInq(mc, msgUser, req, res, session);
							break;
						case A_PRORROGA :
							procActionPrga(mc, msgUser, req, res, session);
							break;
						case A_AVI_BEN :
							procActionAvisoBen(mc, msgUser, req, res, session);
							break;
						case PLAN_DISMINUCION_LISTAR :
							procListarPlanDisminucion(mc, msgUser, req, res, session);
							break;
						case PLAN_DISMINUCION_GRABAR :
							procGrabarPlanDisminucion(mc, msgUser, req, res, session);
							break;							
						case PLAN_DISMINUCION_CANCELAR :
							procCancelarPlanDisminucion(mc, msgUser, req, res, session);
							break;							
						
						default :
							res.sendRedirect(super.srctx +LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 7;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
					//				return;
				} finally {
					s.close();
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
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
	
	protected void procListarPlanDisminucion( MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
				
		ELC500001Message bolgaran = null;
		try {
			try {
				flexLog("Send Initial Data");
				bolgaran = (ELC500001Message) ses.getAttribute("bolgaran");
				bolgaran.setH01USERID(user.getH01USR());
				bolgaran.setH01PROGRM("ELC5000");
				bolgaran.setH01SCRCOD("01");
				bolgaran.setH01OPECOD("0005");
				//String _E05LCPCCY = req.getParameter("E05LCPCCY")==null?"":req.getParameter("E05LCPCCY");
				String _E06SPIM01 = req.getParameter("E06SPIM01")==null?"":req.getParameter("E06SPIM01");
				req.setAttribute("E06SPIM01", _E06SPIM01);
				ELC500006Message garantizaBean = new ELC500006Message();
				garantizaBean.setE06SPIM01( _E06SPIM01 );				// all the fields here
				Enumeration enu = bolgaran.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while (enu.hasMoreElements()) {
					field = (MessageField) enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase();
						if (value != null) {
							field.setString(value);
						}
					}catch(Exception e) {}
				}
				ses.setAttribute("bolgaran", bolgaran);
				ELC500005Message beanPlasDis = new ELC500005Message();
				beanPlasDis = (ELC500005Message)ses.getAttribute("planDis");
				if ( beanPlasDis==null ){
					ses.setAttribute("planDis", new ELC500005Message());
				}else{
					ses.setAttribute("planDis", beanPlasDis);
				}
				
				ses.setAttribute("garantizaBean", garantizaBean);
				//ses.setAttribute("E05LCPCCY", _E05LCPCCY);
				flexLog("About to call Page: " + LangPath + "ELC5000_datos_plan_disminucion.jsp");
				callPage(LangPath + "ELC5000_datos_plan_disminucion.jsp", req, res);
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
			
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}

	protected void procGrabarPlanDisminucion( MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
				
		ELC500005Message planDis = null;
		ELC500006Message garantiza = null;
//		UserPos userPO = null;

		//String _E05LCPCCY = (String)ses.getAttribute("E05LCPCCY");
		//req.setAttribute("E05LCPCCY", _E05LCPCCY);

//		try {
//			userPO = new datapro.eibs.beans.UserPos();
//		} catch (Exception e) {
//			flexLog("Error: " + e);
//		}
		try {
			try {
				planDis = new ELC500005Message();
				planDis.setH05USERID(user.getH01USR());
				planDis.setH05PROGRM("ELC5000");
				planDis.setH05SCRCOD("01");
				planDis.setH05OPECOD("0005");

				// all the fields here
				Enumeration enu = planDis.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while (enu.hasMoreElements()) {
					field = (MessageField) enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase();
						if (value != null) {
							field.setString(value);
						}
					}catch(Exception e) {}
				}
				
				
				ses.setAttribute("planDis", planDis);
				
//				garantiza = new ELC500006Message();
//				garantiza.setE06SPIM01(_E06SPIM01);
//				ses.setAttribute("garantizaBean", garantiza);

////				userPO.setPurpose("NEW");
////				ses.setAttribute("userPO", userPO);
				try {
					flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
					callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);//?E05LCPCCY=" + _E05LCPCCY
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
			
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}

	protected void procCancelarPlanDisminucion( MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {
				
		ELC500005Message planDis = null;
//		UserPos userPO = null;
		//String _E05LCPCCY = (String)ses.getAttribute("E05LCPCCY");
		//req.setAttribute("E05LCPCCY", _E05LCPCCY);
//		try {
//			userPO = new datapro.eibs.beans.UserPos();
//		} catch (Exception e) {
//			flexLog("Error: " + e);
//		}
		try {
			try {

//				userPO.setPurpose("NEW");
//				ses.setAttribute("userPO", userPO);
				try {
					flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
					callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);//?E05LCPCCY=" + _E05LCPCCY
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
			
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}

	protected void procAbrirboletaError( MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses,
										 ELC500001Message bolgaran, ELC500005Message planDis, 
										 ELC500006Message garantizaBean, ELEERRMessage msgError)
			throws ServletException, IOException {

		try {
			ses.setAttribute("error", msgError);
			ses.setAttribute("bolgaran", bolgaran);
			ses.setAttribute("planDis", planDis);
			ses.setAttribute("garantizaBean", garantizaBean);
			flexLog("About to call Page: " + LangPath + "ELC5000_bg_basic.jsp");
			callPage(LangPath + "ELC5000_bg_basic.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
}