package datapro.eibs.products;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EDL015001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;

import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;
/**
 * @version 	1.0
 * @author Ramses Amaro
 */
public class JSEXEDL0150 extends JSEDL0150 {
	/**
	* @see datapro.eibs.products.JSEDL0150#JSEDL0150 ()
	*/
	public JSEXEDL0150() {
		super();
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			super.service(req, res);
		}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;
		
		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("LN");
			userPO.setPurpose("MAINTENANCE");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=400");
			userPO.setProdCode("10");
			//Others Parameters
			userPO.setHeader1("E01DEAACC");
			userPO.setHeader2("H01FLGWK2");
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

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
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015001Message msgLN = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgLN = (EDL015001Message) mc.getMessageRecord("EDL015001");
			msgLN.setH01USERID(user.getH01USR());
			msgLN.setH01PROGRM("EDL0150");
			msgLN.setH01TIMSYS(getTimeStamp());
			msgLN.setH01SCRCOD("01");
			msgLN.setH01OPECOD("0002");
			msgLN.setE01DEAACD("10");
			try {
				msgLN.setE01DEAACC(req.getParameter("E01DEAACC"));
			} catch (Exception e) {
			}
			try {
				msgLN.setH01FLGWK2(req.getParameter("H01FLGWK2"));
			} catch (Exception e) {
			}
			msgLN.send();
			msgLN.destroy();
			flexLog("EDL015001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			if (procRecMaintData(mc, user, req, res, ses)) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EDL0150_ln_basic.jsp");
					callPage(LangPath + "EDL0150_ln_basic.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				userPO.setOption("LN");
				userPO.setPurpose("MAINTENANCE");
				userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=400");
				userPO.setProdCode("10");
			//Others Parameters
				userPO.setHeader1("E01DEAACC");
				userPO.setHeader2("H01FLGWK2");			
				ses.setAttribute("userPO", userPO);
				try {
						flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
						callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
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

}
