package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.Enumeration;
import java.util.HashMap;
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
import datapro.eibs.beans.EWD000203Message;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.*;

public class JSECH5300 extends datapro.eibs.master.SuperServlet {

	public static HashMap confParam;

	protected static final int R_GET_IP 	= 1;
	protected static final int R_SEND_SOLIC = 2;

	protected String LangPath = "S";

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqGetIP(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBObjList chkbList = null;
		ECH530501Message msgCHKBSend = null;
		String branch = "";
		
		// Gets Initial data
		try {
			chkbList = (JBObjList) ses.getAttribute("chkbList");
			chkbList.initRow();
			chkbList.getNextRow();
			msgCHKBSend = (ECH530501Message) chkbList.getRecord();
			branch = msgCHKBSend.getE01CHPBRN();
			
			switch ( branch.length()  ) {
				case 1 :
					branch = "00" + branch ;
					break;

				case 2 :
					branch = "0" + branch ;
					break;

			}
			
			flexLog("BRANCH:" + branch ) ;
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			flexLog("calling /servlet/datapro.eibs.helps.JSEWD0002IP?branch=" + branch);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.helps.JSEWD0002IP?branch=" + branch);
		} catch (Exception ex) {
			flexLog("Exception calling /servlet/datapro.eibs.helps.JSEWD0002IP");
			flexLog("No se pueden obtener parametros de conexion con la Dispensadora");
			res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
		}
		
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSendSolic(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECH530501Message msgCHKBSend = null;
		JBObjList chkbList = null;
		
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			chkbList = (JBObjList) ses.getAttribute("chkbList");
			chkbList.initRow();
			while (chkbList.getNextRow()) {
				msgCHKBSend = (ECH530501Message) chkbList.getRecord();

				msgCHKBSend.setH01USERID(user.getH01USR());
				msgCHKBSend.setH01PROGRM("ECH5305");
				msgCHKBSend.setH01TIMSYS(getTimeStamp());
				msgCHKBSend.setH01OPECOD("0007");
				
				mc.sendMessage(msgCHKBSend);
				
				newmessage = mc.receiveMessage();
				msgCHKBSend.setE01ERRCOD("0");
	
				if (newmessage.getFormatName().equals("ECH530501")) {
					msgCHKBSend = (ECH530501Message) newmessage;
					
					flexLog("Confirmation Message Received - No. " + chkbList.getCurrentRow() + 1);
					
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
			}

		} catch (Exception e) {
			flexLog("Account = " + msgCHKBSend.getE01CHPACC());
			throw new RuntimeException("Socket Communication Error");
		}
		
		ses.setAttribute("chkbList", chkbList);
		try {
			flexLog("About to call Page: " + LangPath + "ECH5300_chb_confirm.jsp");
			callPage(LangPath + "ECH5300_chb_confirm.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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
			UserPos userPO = (UserPos) session.getAttribute("userPO");
			int screen = R_GET_IP;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";
				
				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}
					
				switch (screen) {
					case R_GET_IP :
						procReqGetIP(mc, msgUser, req, res, session);
						break;
					case R_SEND_SOLIC :
						String host = "";
						int sck = 32000;
						int timeout = 0;
						try {
							EWD000203Message msgHelp = null;
							JBObjList ewd0002ipHelp = null;
			
							ewd0002ipHelp = (JBObjList) session.getAttribute("ewd0002ipHelp");
							ewd0002ipHelp.initRow();
							while (ewd0002ipHelp.getNextRow()) {
								msgHelp = (EWD000203Message) ewd0002ipHelp.getRecord();
							}
							int seppos = msgHelp.getE03WDDSC().indexOf(':');
							if (seppos != -1) {
								host = msgHelp.getE03WDDSC().substring(0, seppos);
								sck = Integer.parseInt(msgHelp.getE03WDDSC().substring(seppos + 1));
							} else {
								host = msgHelp.getE03WDDSC();
							}
							timeout = Integer.parseInt(msgHelp.getE03WDSCG());
								
							flexLog("Opennig Socket Connection (Host " + host + " - Port " + sck + " - timeout " + timeout + ")");
							s = new Socket(host, sck);
							s.setSoTimeout(timeout);
							mc =
								new MessageContext(
									new DataInputStream(new BufferedInputStream(s.getInputStream())),
									new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									"datapro.eibs.beans");
					
							procReqSendSolic(mc, msgUser, req, res, session);
	
						} catch (Exception e) {
							e.printStackTrace();
							flexLog("Socket not Open(Host " + host + " - Port " + sck + "). Error: " + e);
							throw new RuntimeException("Socket Communication Error");
						} finally {
							s.close();
						} 
						
						procReqSendSolic(mc, msgUser, req, res, session);
						break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				try {
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECH5305?SCREEN=10");
				} catch (Exception ex) {
					flexLog("Exception calling /servlet/datapro.eibs.products.JSECH5305?SCREEN=10");
					flexLog("Error de Comunicacion con la Dispensadora no procesado");
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				}	
			}

		}

	}

}