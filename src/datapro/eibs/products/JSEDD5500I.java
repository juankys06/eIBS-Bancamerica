package datapro.eibs.products;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDD550001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
/**
 * @version 	1.0
 * @author Ramses Amaro
 */
public class JSEDD5500I extends JSEDD5500 {
	
	/**
	 * JSEDD5500I constructor comment.
	 */
	public JSEDD5500I() {
		super();
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			super.service(req, res);
		}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSignerList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		
			MessageRecord newmessage = null;
			EDD550001Message msgRT = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;


			msgError = new datapro.eibs.beans.ELEERRMessage();

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			String opCode = null;
			opCode = "0004";

			// Send Initial data
			try {
				msgRT = (EDD550001Message) mc.getMessageRecord("EDD550001");
				msgRT.setH01USR(user.getH01USR());
				msgRT.setH01PGM("EDD5500");
				msgRT.setH01TIM(getTimeStamp());
				msgRT.setH01SCR("01");
				msgRT.setH01OPE(opCode);
				msgRT.setE01CUN(userPO.getIdentifier());
				msgRT.setE01RTP("S");
				msgRT.send();
				msgRT.destroy();


				// Receive Error or Data Message

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {

					msgError = (ELEERRMessage) newmessage;
					ses.setAttribute("error", msgError);

					flexLog("Putting java beans into the session"); 
					ses.setAttribute("error", msgError);

					try {
						flexLog("About to call Page4: " + LangPath + "EDD0000_rt_inq_balances.jsp");
						callPage(LangPath + "EDD0000_rt_inq_balances.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} 
				else if (newmessage.getFormatName().equals("EDD550001")) {


					JBObjList beanList = new datapro.eibs.beans.JBObjList();

					//boolean firstTime;
					String marker = "";

					while (true) {
	
						msgRT = (EDD550001Message) newmessage;
					
						marker = msgRT.getH01MAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							beanList.addRow(msgRT);
						}
						newmessage = mc.receiveMessage();
					}



					flexLog("Putting java beans into the session");
					//ses.setAttribute("userPO", userPO);
					ses.setAttribute("signersList", beanList);
					ses.setAttribute("error", msgError);

					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_inq_signers_list.jsp");
						callPage(LangPath + "EDD0000_rt_inq_signers_list.jsp", req, res);
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
