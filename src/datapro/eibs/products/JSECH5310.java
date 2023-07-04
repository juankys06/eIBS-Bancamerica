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

public class JSECH5310 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER_BRN = 1;
	protected static final int A_ENTER_BRN = 2;
	protected static final int A_SOLIC_LIST = 3;

	protected String LangPath = "S";

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	protected void procReqEnterBranch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ECH531001Message msgCHKB = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgCHKB = new ECH531001Message();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		try {
			userPO = new UserPos();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO.setPurpose("MAINTENANCE");
		userPO.setOption(req.getParameter("CYCLE"));
		//ses.setAttribute("msgCHKB", msgCHKB);
		ses.setAttribute("userPO", userPO);

		try {
			flexLog("About to call Page: " + LangPath + "ECH5310_chb_enter_inq.jsp");
			callPage(LangPath + "ECH5310_chb_enter_inq.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterBranch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH531001Message msgList = null;
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
			msgList = (ECH531001Message) mc.getMessageRecord("ECH531001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECH5310");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD("0001");
			//msgList.setH01FLGWK3(userPO.getOption());
			
			try {
				msgList.setE01SELBNK(req.getParameter("E01SELBNK"));
			} catch (Exception e) {
			}
			try {
				msgList.setE01SELBRN(req.getParameter("E01SELBRN"));
			} catch (Exception e) {
			}

			msgList.send();
			msgList.destroy();
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

			if (newmessage.getFormatName().equals("ECH531001")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgList = (ECH531001Message) newmessage;
					marker = msgList.getE01OPECDE();

					if (marker.equals("*")) {
						userPO.setHeader1(msgList.getE01CHDBNK());
						userPO.setHeader2(msgList.getE01CHDBRN());
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
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("chkbList", beanList);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ECH5310_chb_solicitudes_list.jsp");
						callPage(LangPath + "ECH5310_chb_solicitudes_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ECH5310_chb_enter_inq.jsp");
						callPage(LangPath + "ECH5310_chb_enter_inq.jsp", req, res);
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
	protected void procActionSolicList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH531001Message msgCHKB = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		JBObjList chkbList = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		chkbList = (JBObjList) ses.getAttribute("chkbList");
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = 0;
		}
		chkbList.setCurrentRow(row);
		msgCHKB = (ECH531001Message) chkbList.getRecord();
		String Account = msgCHKB.getE01CHDACC();
		String Branch = msgCHKB.getE01CHDBRN();
		String Cheque = msgCHKB.getE01CHDICK();
		
		if (msgCHKB.getE01CHDSTS().equals("")) {
			msgError = new ELEERRMessage();
			msgError.setERRNUM("1");
			msgError.setERNU01("1111");
			msgError.setERDF01("E01CHDSTS");
			msgError.setERDR01("");
			msgError.setERDS01("Esta solicitud no puede ser reenviada");
			
			ses.setAttribute("error", msgError);
			
			try {
				flexLog("About to call Page: " + LangPath + "ECH5310_chb_solicitudes_list.jsp");
				callPage(LangPath + "ECH5310_chb_solicitudes_list.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		} else {
			try {
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.products.JSECH5305?SCREEN=4" + 
						"&E01CHPACC=" + Account +
						"&E01CHPBRN=" + Branch +
						"&E01CHPN01=" + Cheque);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
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

			int screen = R_ENTER_BRN;

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
					case R_ENTER_BRN :
						procReqEnterBranch(msgUser, req, res, session);
						break;
					case A_ENTER_BRN :
						procActionEnterBranch(mc, msgUser, req, res, session);
						break;
					case A_SOLIC_LIST :
						procActionSolicList(mc, msgUser, req, res, session);
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

}