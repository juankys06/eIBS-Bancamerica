package datapro.eibs.ach;
/**
 * ACH Incoming  
 * Creation date: (06/10/08)
 * @author: Carlos Castillo
 *                   
 * 	// List of Options
 * 	//
 *	// SCREEN =    1	Request ACH File Name          
 *	// SCREEN =    2    Validate ACH Incoming File 
 **	// SCREEN =    5    Process  ACH Incoming File 
 */
import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.*;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;
import javax.servlet.ServletConfig;

public class JSEACH400 extends JSEIBSServlet { 
	
	private ServletConfig config = null;
	
	/**
	 *  Menu 
	 */
	protected void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
			HttpSession session, int screen)
		throws ServletException, IOException {
		
		UserPos userPO = null;
		userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
		
		if (userPO.getPurpose().equals("PROCESS")) {
			screen = 5;
		}
		
		switch (screen) {
			//  
			case 1 : // Request File Name 
				procReqFile(user, req, res, session);
				break;
			case 2 : // Valide ACH File
			case 5 : // Proccess ACH File
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
		forwardOnSuccess("EACH400_incoming_file.jsp", req, res);
		
	}

	// SCREEN = 2,5
	// Validate and/or Process de File  
	//
	protected void procActionFile(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res,
		HttpSession ses, int screen)
		throws ServletException, IOException {

		MessageProcessor mp = null;
		MessageRecord newmessage = null;
		EACH400DSMessage msg = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("ENTER");
		try {
			// Read file
			String st = "";
			StringBuffer buf = new StringBuffer();
			try {
				SmartUpload mySmartUpload = new SmartUpload();
				mySmartUpload.initialize(config, req, res);
				mySmartUpload.upload();
				com.jspsmart.upload.File myFile =  mySmartUpload.getFiles().getFile(0);
				StringReader sr = new StringReader(myFile.getContentString());
				LineNumberReader lnr = new LineNumberReader(sr);
				String line = "";
				String linetosend = "";
				mp = new MessageProcessor("EACH400");
				msg = (EACH400DSMessage) mp.getMessageRecord("EACH400DS");
				
				while (true) {
					line = lnr.readLine();
					// EOF
					if (line == null) {
						break;
					}
					linetosend = line;
					if (line.length() > 0 ) {
						int posIni = 0;
						boolean find = true;
						while (find == true) {
							if (line.substring(posIni, posIni + 94).length() > 94) {
								linetosend = line.substring(posIni, posIni + 94);
								posIni = posIni + 94;					
							} else {
								linetosend = line.substring(posIni, line.length() );
								find = false;
							}		
							//
							msg.setH01USERID(user.getH01USR());
							msg.setH01PROGRM("EACH400");
							msg.setH01TIMSYS(getTimeStamp());  
							msg.setH01SCRCOD("01");
							if (screen == 5 ) {
								msg.setH01OPECOD("0005");
							} else {
								msg.setH01OPECOD("0002");
							}
							msg.setE01FILE(myFile.getFileName());
							msg.setE01DATA(linetosend);		
							msg.setH01FLGWK1("");
							// Send Message
							mp.sendMessage(msg);
						}					
					}
				}
				lnr.close();
				sr.close();
				// Send message indicating end of process	
				msg.setH01USERID(user.getH01USR());
				msg.setH01PROGRM("EACH400");
				msg.setH01TIMSYS(getTimeStamp());  
				msg.setH01SCRCOD("01");
				if (screen == 5 ) {
					msg.setH01OPECOD("0005");
				} else {
					msg.setH01OPECOD("0002");
				}
				msg.setE01FILE(myFile.getFileName());
				msg.setH01FLGWK1("S");  // End of List
				mp.sendMessage(msg);
				// Receive Error Message or Data Message (Optional)
				ses.setAttribute("userPO", userPO);
				newmessage = mp.receiveMessageRecord();
				if (mp.hasError(newmessage)) {
					ses.setAttribute("error", newmessage);
					forward("EACH400_incoming_file.jsp", req, res);
				} else {
					redirectToPage("/servlet/datapro.eibs.ach.JSEACH400?SCREEN=1", res);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("File Upload Error");
			}
		} finally {
			if (mp != null) 
				mp.close();
		}
	}

}