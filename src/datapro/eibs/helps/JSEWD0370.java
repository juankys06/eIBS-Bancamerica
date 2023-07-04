package datapro.eibs.helps;

/**
 * This type was created by Catalina Sepulveda.
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EWD0370DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;

public class JSEWD0370 extends JSEIBSServlet {

	protected void processRequest(ESS0030DSMessage msgUser,
			HttpServletRequest req, HttpServletResponse res,
			HttpSession session, int screen) throws ServletException,
			IOException {

		switch (screen) {
		case 1:
			reqList(msgUser, req, res, session);
			break;
		default:
			forward(super.devPage, req, res);
			break;
		}
	}

	protected void reqList(ESS0030DSMessage user, HttpServletRequest req,
			HttpServletResponse res, HttpSession ses) throws ServletException,
			IOException {

		MessageProcessor mp = null;
		UserPos userPO = null;
		try {
			mp = new MessageProcessor("EWD0370");
			EWD0370DSMessage msg = (EWD0370DSMessage) mp.getMessageRecord("EWD0370DS");
			mp.sendMessage(msg);

			JBObjList list = (JBObjList) mp.receiveMessageRecordList("EWDOPE");
			if (mp.hasError(list)) {
				ses.setAttribute("error", mp.getError(list));
			} else {
				ses.setAttribute("msgHelp", list);
			}
			forward("EWD0370_insurance_formulas_helpmessage.jsp", req, res);

		} finally {
			if (mp != null)
				mp.close();
		}
	}
}