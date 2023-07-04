package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ECH531001Message;
import datapro.eibs.beans.ECH530501Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.*;

public class JSECH5305 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_ACC 		= 1;
	protected static final int A_ENTER_ACC 		= 2;
	protected static final int R_SOLIC 			= 3;
	protected static final int R_SOLIC_2 		= 4;
	protected static final int R_SOLIC_VENTA 	= 5;
	protected static final int A_SEND_ERROR 	= 10;
	
	protected String LangPath = "S";

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	protected void procReqEnterAccount(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ECH530501Message msgCHKBSend = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgCHKBSend = new ECH530501Message();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		try {
			userPO = new UserPos();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("msgCHKBSend", msgCHKBSend);
		ses.setAttribute("userPO", userPO);

		try {
			flexLog("About to call Page: " + LangPath + "ECH5305_chb_enter_acc.jsp");
			callPage(LangPath + "ECH5305_chb_enter_acc.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterAccount(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH530501Message msgCHKBSend = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCHKBSend = (ECH530501Message) mc.getMessageRecord("ECH530501");
			msgCHKBSend.setH01USERID(user.getH01USR());
			msgCHKBSend.setH01PROGRM("ECH5305");
			msgCHKBSend.setH01TIMSYS(getTimeStamp());
			msgCHKBSend.setH01OPECOD("0001");
			
			try {
				msgCHKBSend.setE01CHPACC(req.getParameter("E01CHPACC"));
			} catch (Exception e) {}
			
			msgCHKBSend.send();
			msgCHKBSend.destroy();
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

			if (newmessage.getFormatName().equals("ECH530501")) {
				msgCHKBSend = (ECH530501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgCHKBSend", msgCHKBSend);
				ses.setAttribute("error", msgError);
				
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ECH5305_chb_dispens.jsp");
						callPage(LangPath + "ECH5305_chb_dispens.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ECH5305_chb_enter_acc.jsp");
						callPage(LangPath + "ECH5305_chb_enter_acc.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSolicManual(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH530501Message msgCHKBSend = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String flg = req.getParameter("FLG");
		if (flg == null) flg = "";		
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCHKBSend = (ECH530501Message) mc.getMessageRecord("ECH530501");
			msgCHKBSend.setH01USERID(user.getH01USR());
			msgCHKBSend.setH01PROGRM("ECH5305");
			msgCHKBSend.setH01TIMSYS(getTimeStamp());
			msgCHKBSend.setH01OPECOD("0005");
			
			// all the fields here
			java.util.Enumeration enu = msgCHKBSend.fieldEnumeration();
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
			
			msgCHKBSend.send();
			msgCHKBSend.destroy();
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

			if (newmessage.getFormatName().equals("ECH530501")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgCHKBSend = (ECH530501Message) newmessage;
					flexLog("ECH530501 Message Received");					
					marker = msgCHKBSend.getH01FLGMAS();

					msgCHKBSend.setE01ERRCOD("9999");
					beanList.addRow(msgCHKBSend);
					if (marker.equals("*")) break;
					newmessage = mc.receiveMessage();
				}				
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("chkbList", beanList);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					//userPO.setRedirect("/servlet/datapro.eibs.products.JSECH5305?SCREEN=2" + 
					//			"&E01CHPACC=" + msgCHKBSend.getE01CHPACC());
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.products.JSECH5300?SCREEN=1");
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ECH5305_chb_dispens.jsp");
						callPage(LangPath + "ECH5305_chb_dispens.jsp", req, res);
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
	protected void procReqSolic2(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH530501Message msgCHKBSend = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCHKBSend = (ECH530501Message) mc.getMessageRecord("ECH530501");
			msgCHKBSend.setH01USERID(user.getH01USR());
			msgCHKBSend.setH01PROGRM("ECH5305");
			msgCHKBSend.setH01TIMSYS(getTimeStamp());
			msgCHKBSend.setH01OPECOD("0006");
			
			msgCHKBSend.setE01CHPACC(req.getParameter("E01CHPACC"));
			try {
				msgCHKBSend.setE01CHPBRN(req.getParameter("E01CHPBRN"));
			} catch (Exception e) {}
			try {
				msgCHKBSend.setE01CHPN01(req.getParameter("E01CHPN01"));
			} catch (Exception e) {}
			
			mc.sendMessage(msgCHKBSend);
			//msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
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
			if (newmessage.getFormatName().equals("ECH530501")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgCHKBSend = (ECH530501Message) newmessage;
					flexLog("ECH530501 Message Received");					
					marker = msgCHKBSend.getH01FLGMAS();

					msgCHKBSend.setE01ERRCOD("9999");
					beanList.addRow(msgCHKBSend);
					if (marker.equals("*")) break;
					newmessage = mc.receiveMessage();
				}				
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("chkbList", beanList);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					//userPO.setRedirect("/servlet/datapro.eibs.products.JSECH5310?SCREEN=2" + 
					//			"&E01SELBNK=" + msgCHKBSend.getE01CHPBNK() + 
					//			"&E01SELBRN=" + msgCHKBSend.getE01CHPBRN());
					try {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.products.JSECH5300?SCREEN=1");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.products.JSECH5310?SCREEN=2" + 
								"&E01SELBNK=" + msgCHKBSend.getE01CHPBNK() + 
								"&E01SELBRN=" + msgCHKBSend.getE01CHPBRN());
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
	protected void procReqSolicVenta(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH530501Message msgCHKBSend = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCHKBSend = (ECH530501Message) mc.getMessageRecord("ECH530501");
			msgCHKBSend.setH01USERID(user.getH01USR());
			msgCHKBSend.setH01PROGRM("ECH5305");
			msgCHKBSend.setH01TIMSYS(getTimeStamp());
			msgCHKBSend.setH01OPECOD("0005");
			
			try {
				msgCHKBSend.setE01CHPACC(req.getParameter("E01CHPACC"));
			} catch (Exception e) {}
			try {
				msgCHKBSend.setE01CHPNSC(req.getParameter("E01CHPNSC"));
			} catch (Exception e) {}
			
			msgCHKBSend.send();
			msgCHKBSend.destroy();
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

			if (newmessage.getFormatName().equals("ECH530501")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgCHKBSend = (ECH530501Message) newmessage;
					flexLog("ECH530501 Message Received");					
					marker = msgCHKBSend.getH01FLGMAS();

					msgCHKBSend.setE01ERRCOD("9999");
					beanList.addRow(msgCHKBSend);
					if (marker.equals("*")) break;
					newmessage = mc.receiveMessage();
				}				
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("chkbList", beanList);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					//userPO.setRedirect("/servlet/datapro.eibs.products.JSECH0320?SCREEN=7&E02SELACC="
					//		+ msgCHKBSend.getE01CHPACC());
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.products.JSECH5300?SCREEN=1");
				} else {
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.products.JSECH0320?SCREEN=7&E02SELACC="
							+ msgCHKBSend.getE01CHPACC());
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
	}
	
	protected void procActionSendError(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH530501Message msgCHKBSend = null;
		JBObjList chkbList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			chkbList = (JBObjList) ses.getAttribute("chkbList");
			chkbList.initRow();
			while (chkbList.getNextRow()) {
				msgCHKBSend = (ECH530501Message) chkbList.getRecord();

				if (msgCHKBSend.getE01ERRCOD().equals("9999")) {
					msgCHKBSend.setH01USERID(user.getH01USR());
					msgCHKBSend.setH01PROGRM("ECH5305");
					msgCHKBSend.setH01TIMSYS(getTimeStamp());
					msgCHKBSend.setH01OPECOD("0007");
					
					mc.sendMessage(msgCHKBSend);				
				}

			}

		} catch (Exception e) {
			flexLog("El Error de Comunicación con la Dispensadora no ha sido procesado");
			flexLog("E01CHPACC: " + msgCHKBSend.getE01CHPACC());
			flexLog("E01ERRCOD: 9999");
			e.printStackTrace();
			flexLog("Error: " + e);
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
			flexLog("Message " + newmessage.getFormatName() + " received.");
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
					 callPage(LangPath + "error_viewer.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else if (newmessage.getFormatName().equals("ECH530501")) {
				msgCHKBSend = (ECH530501Message) newmessage;
			}
			
		} catch (Exception e) {
			flexLog("El Error de Comunicación con la Dispensadora quizás no ha sido procesado");
			flexLog("E01CHPACC: " + msgCHKBSend.getE01CHPACC());
			flexLog("E01ERRCOD: 9999");
			e.printStackTrace();
			flexLog("Error: " + e);
		}
		
	}
	
	/**
	 * service method comment.
	 */
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

			int screen = R_ENTER_ACC;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}
	
					switch (screen) {
						case R_ENTER_ACC :
							procReqEnterAccount(msgUser, req, res, session);
							break;
						case A_ENTER_ACC :
							procActionEnterAccount(mc, msgUser, req, res, session);
							break;
						case R_SOLIC_VENTA :
							procReqSolicVenta(mc, msgUser, req, res, session);
							break;
						case R_SOLIC :
							procReqSolicManual(mc, msgUser, req, res, session);
							break;
						case R_SOLIC_2 :
							procReqSolic2(mc, msgUser, req, res, session);
							break;
						case A_SEND_ERROR :
							procActionSendError(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				}
				finally {
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