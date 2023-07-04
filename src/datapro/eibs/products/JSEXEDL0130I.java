package datapro.eibs.products;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EDL016001Message;
import datapro.eibs.beans.ESS0030DSMessage;

import datapro.eibs.beans.UserPos;

import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;
/**
 * @version 	1.0
 * @author
 */
public class JSEXEDL0130I extends JSEDL0130I {
	/**
	* @see datapro.eibs.products.JSEDL0130I#JSEDL0130I ()
	*/
	public JSEXEDL0130I() {
		super();
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			super.service(req, res);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterInquiry(
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
			
			userPO.setOption("CD");
			userPO.setPurpose("INQUIRY");
			userPO.setRedirect("/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=600");
			userPO.setProdCode("CD");
			//Others Parameters
			userPO.setHeader1("E01DEAACC");
			userPO.setHeader2("H01FLGWK2");
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
	protected void procActionEnterInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL016001Message msgCD = null;
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
			msgCD = (EDL016001Message) mc.getMessageRecord("EDL016001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0160");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0002");
			try {
				if (req.getParameter("E01DEAACC") != null)
					msgCD.setE01DEAACC(req.getParameter("E01DEAACC"));
				flexLog("Certificate Sent");
			} catch (Exception e) {
				msgCD.setE01DEAACC("0");
				flexLog(" error " + e);
				flexLog("Certificate Error Sent");
			}
			flexLog("Send command");
			msgCD.send();
			flexLog("Destroy command");
			msgCD.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				//showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
						flexLog("About to call Page: " + LangPath + "GENERIC_account_enter.jsp");
						callPage(LangPath + "GENERIC_account_enter.jsp", req, res);
				} catch (Exception e) {
						flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EDL016001")) {
				try {
					msgCD =new datapro.eibs.beans.EDL016001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (EDL016001Message) newmessage;

				userPO.setIdentifier(msgCD.getE01DEAACC());
				userPO.setBank(msgCD.getE01DEABNK());
				userPO.setBranch(msgCD.getE01DEABRN());
				userPO.setCusNum(msgCD.getE01DEACUN());
				userPO.setHeader1(msgCD.getE01DEAPRO());
				userPO.setHeader2(userPO.getCusNum());
				userPO.setHeader3(msgCD.getE01CUSNA1());
				userPO.setHeader23(msgCD.getE01DEARTB());
				userPO.setCurrency(msgCD.getE01DEACCY());
				userPO.setOfficer(
					msgCD.getE01DEAOFC() + " - " + msgCD.getE01DSCOFC());

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdInquiry", msgCD);
				ses.setAttribute("error", msgError);

				procReqMaintPay(user, req, res, ses);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0160_cd_inquiry.jsp");
					callPage(LangPath + "EDL0160_cd_inquiry.jsp", req, res);
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
}
