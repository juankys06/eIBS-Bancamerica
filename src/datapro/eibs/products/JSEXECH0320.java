package datapro.eibs.products;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ECH032002Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;

import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

/**
 * @version 	1.0
 * @author		Ramses Amaro
 */
public class JSEXECH0320 extends JSECH0320 {
	
	/**
	 * JSEXEDL0130 constructor comment.
	 */
	public JSEXECH0320() {
		super();
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			super.service(req, res);
		}
	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqPassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		String opt;
		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			opt = req.getParameter("OPTION");
			if (opt.equals("1")) {
				userPO.setRedirect(
					"/servlet/datapro.eibs.products.JSECH0320?SCREEN="
						+ R_ENTER_BRN
						+ (req.getParameter("ACCNUM") == null
							? ""
							: "&ACCNUM=" + req.getParameter("ACCNUM")));
			} else {
				if (opt.equals("2"))
					userPO.setHeader23(" ");
				else
					userPO.setHeader23("O"); //delivery with overdraft
				userPO.setRedirect(
					"/servlet/datapro.eibs.products.JSEXECH0320?SCREEN="
						+ R_ENTER_ACC
						+ (req.getParameter("ACCNUM") == null
							? ""
							: "&ACCNUM=" + req.getParameter("ACCNUM")));
			}
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}	
	
	protected void procReqEnterAccount(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		//EDL013001Message msgCD = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			userPO.setOption("CH");
			userPO.setPurpose("MAINTENANCE");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXECH0320?SCREEN=7");
			userPO.setProdCode("RA");
			//Others Parameters
			userPO.setHeader1("E02SELACC");
			userPO.setHeader4("E02CUMBNI");
			//msgCD = new EDL013001Message();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			//ses.setAttribute("cdMant", msgCD);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
			callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
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
		ECH032002Message msgList = null;
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
			msgList = (ECH032002Message) mc.getMessageRecord("ECH032002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ECH0320");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02OPECOD("0001");
			try {
				msgList.setE02SELACC(req.getParameter("E02SELACC"));
			} catch (Exception e) {}
			try {
				msgList.setE02CUMBNI(req.getParameter("E02CUMBNI"));
			} catch (Exception e) {}
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

			if (newmessage.getFormatName().equals("ECH032002")) {

				try {
					beanList = new JBObjList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";

				while (true) {

					msgList = (ECH032002Message) newmessage;

					marker = msgList.getE02OPECDE();

					if (marker.equals("*")) {
						break;
					} else {
						beanList.addRow(msgList);
					}
					newmessage = mc.receiveMessage();
				}

				//userPO = new UserPos();
				//userPO.setPurpose("RECEP_CHKB");

				//
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("chkbList", beanList);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ECH0320_chb_delivery_list.jsp");
						callPage(LangPath + "ECH0320_chb_delivery_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
						callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
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
}
