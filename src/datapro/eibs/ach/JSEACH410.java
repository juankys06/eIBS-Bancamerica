package datapro.eibs.ach;
/**
 * ACH Outgoing  
 * Creation date: (06/25/08)
 * @author: Carlos Castillo
 *                   
 * 	// List of Options
 * 	//
 *	// SCREEN =    1	Request ACH File Name          
 *	// SCREEN =    5    Process  ACH Outgoing File 
 */
import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletConfig;

public class JSEACH410 extends JSEIBSServlet { 
	
	private ServletConfig config = null;
	
	/**
	 *  Menu 
	 */
	protected void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession session, int screen)
		throws ServletException, IOException {
		
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
		
		if (userPO.getPurpose().equals("GENERATE")) {
			screen = 5;
		}
		
		switch (screen) {
			//  
			case 1 : // Request File Name 
				procReqFile(user, req, res, session);
				break;
			case 5 : // Generate ACH File
				procActionFile(user, req, res, session, screen);
				break;
			default :
				forward(SuperServlet.devPage, req, res);
				break;
		}
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}

	// SCREEN = 1 
	// Send Request for File Name  
	//
	protected void procReqFile(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("ENTER");
		ses.setAttribute("userPO", userPO);
		forwardOnSuccess("EACH410_outgoing_file.jsp", req, res);
		
	}

	// SCREEN = 5
	// Generate ACH File  
	//
	protected void procActionFile(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		MessageProcessor mp = null;
		MessageRecord newmessage = null;
		EACH410DSMessage msg = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("ENTER");
		try {
			mp = new MessageProcessor("EACH410");
			msg = (EACH410DSMessage) mp.getMessageRecord("EACH410DS");
			// Request the file
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EACH410");
			msg.setH01TIMSYS(getTimeStamp());  
			msg.setH01SCRCOD("01"); 
			msg.setH01OPECOD("0005");
			msg.setH01FLGWK1("");
			// Send Message
			mp.sendMessage(msg);
			// Receive Error Message or Data Message (Optional)
			newmessage = mp.receiveMessageRecord();
			if (mp.hasError(newmessage)) {
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("error", newmessage);
				forward("EACH410_outgoing_file.jsp", req, res);
			} else {
				// Write PC File
				String marker = "";
				StringBuffer buf = new StringBuffer();
				String sTmp = "";
				while (true) {

					msg = (EACH410DSMessage) newmessage;
					marker = msg.getE01INDOPE();

					if (marker.equals("*")) {
						break;
					} else {
						sTmp = msg.getE01DATA();
						if (sTmp.length() < 94) {
							for (int i=msg.getE01DATA().length(); i < 94; i++) {
								sTmp = sTmp + " ";
							}
						}
						buf.append(sTmp + "\r\n");
						newmessage = mp.receiveMessageRecord();
					}
				}

				try {
					res.setContentType("utx");
					res.addHeader("content-disposition","attachment; filename=" + msg.getE01FILE());

					ServletOutputStream out = (ServletOutputStream) res.getOutputStream();
					out.print(buf.toString());
					out.close();
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("File Upload Error");
				}
				ses.setAttribute("userPO", userPO);
				forwardOnSuccess("EACH410_outgoing_file.jsp", req, res);
				//redirectToPage("/servlet/datapro.eibs.ach.JSEACH410?SCREEN=1", res);
			}	
		
		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}