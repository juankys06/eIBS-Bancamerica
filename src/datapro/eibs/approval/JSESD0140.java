package datapro.eibs.approval;

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
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD014001Message;
import datapro.eibs.beans.ESD014002Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSESD0140 extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL				= 5;
	protected static final int A_APPROVAL				= 2;
	protected static final int R_APPROVAL_INQ			= 3;
	protected static final int R_PASSWORD				= 1;
	
	protected String Lang = "S";	
	protected String LangPath = "S";

/**
 * JSReportManager constructor comment.
 */
public JSESD0140() {
	super();
}
/**
 * JSReportManager constructor comment.
 * @param logType int
 */
public JSESD0140(int logType) {
	super(logType);
}
/**
 * This method was created in VisualAge.
 */
protected void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ESD014002Message msgList = null;
	UserPos	userPO = null;
	boolean IsNotError = false;
	
	ESD014001Message bean = null;
	JBObjList beanAccbyCustList = null;
	JBObjList beanList = null;
	
	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		beanAccbyCustList = (JBObjList) ses.getAttribute("appList");
		int pos = Integer.parseInt(req.getParameter("activeTable"));
		beanAccbyCustList.setCurrentRow(pos);
		beanList = (JBObjList) beanAccbyCustList.getRecord();
		beanList.initRow();
		String row = req.getParameter("activeTable");
		ArrayList posAcc = new ArrayList();
		while (beanList.getNextRow()) {
			String idx = "" + beanList.getCurrentRow();
			String selAcc = req.getParameter("ACCNUM" + pos + idx);
			
			if (selAcc != null) { //send info
				bean = (ESD014001Message) beanList.getRecord();
				flexLog("Send Initial Data");
				msgList = (ESD014002Message)mc.getMessageRecord("ESD014002");
				msgList.setH02USERID(user.getH01USR());
				msgList.setH02PROGRM("ESD0140");
				msgList.setH02TIMSYS(getTimeStamp());
			 	
				if ( bean.getE01LNENUM().equals("0")) {
					msgList.setE02ACCNUM(bean.getE01ACCNUM());
				}else{	
					msgList.setE02ACCNUM(bean.getE01CUSNUM());
					msgList.setE02LNENUM(bean.getE01LNENUM());		 		
				}
				msgList.setE02FILCOD(bean.getE01FILCOD());
				msgList.setE02ACTION(req.getParameter("action"));	 	
				msgList.setE02MSGTXT(req.getParameter("reason"));
				msgList.send();	
				msgList.destroy();
				flexLog("Send ESD014002Message");
				// Receive Message
	
				newmessage = mc.receiveMessage();
	  
				if (newmessage.getFormatName().equals("ELEERR")) {
					 flexLog("Receive ELEERRMessage");
					 msgError = (ELEERRMessage)newmessage;
					 IsNotError = msgError.getERRNUM().equals("0");
					 flexLog("IsNotError = " + IsNotError);

					 if (IsNotError) { // There is no error
						 if (req.getParameter("action").equals("A")) posAcc.add(idx);
					 }
					 else {
						 break;						 
					 }		
				} else {
					flexLog("Receive" + newmessage.getFormatName());
					IsNotError= false;
					msgError = new ELEERRMessage();
					msgError.setERRNUM("1");
					break;
				}
				
			}
		}
		
		if (IsNotError) { // There is no error
			//ses.setAttribute("ROWCUST",row);
			//ses.setAttribute("arrayPos",posAcc);
			
			res.sendRedirect(super.srctx + LangPath + "MISC_search_wait.jsp?URL='" + super.srctx + "/servlet/datapro.eibs.approval.JSESD0140?SCREEN=5'");
			
			//flexLog("About to call Page: " + LangPath + "ESD0140_approval_confirm.jsp");
			//callPage(LangPath + "ESD0140_approval_confirm.jsp", req, res);
		}
		else {
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("ROWCUST",row);
			//remove all approved
			if (!posAcc.isEmpty()) {
				for (int i = posAcc.size() -1 ; i > 0 ; i--){
					int posD = Integer.parseInt((String)posAcc.get(i));
					beanList.removeRow(posD);
				}
			}
			ses.setAttribute("appList",beanAccbyCustList);
			 try {
				  flexLog("About to call Page: " + LangPath + "ESD0140_approval_list.jsp");
				  callPage(LangPath + "ESD0140_approval_list.jsp", req, res);
			 }
			 catch (Exception e) {
				 flexLog("Exception calling page " + e);
			 }
		}		
							 	
	
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
}
/**
 * This method was created in VisualAge.
 */
protected void procReqApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	ESD014001Message msgList = null;
	JBObjList beanList = null;
	JBObjList beanCustList = null;
	UserPos	userPO = null;

	
	msgError = new datapro.eibs.beans.ELEERRMessage();
	

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ESD014001Message)mc.getMessageRecord("ESD014001");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ESD0140");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	//msgList.setE01APLCDE(req.getParameter("appCode"));
	 	msgList.send();	
	 	msgList.destroy();
	
		
	// Receive Message
	
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {

			msgError = (ELEERRMessage)newmessage;
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				 flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 callPage(LangPath + "error_viewer.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}


	  	}
	  	else if (newmessage.getFormatName().equals("ESD014001")) {

			
			beanList = new JBObjList();
			beanCustList = new JBObjList();
			JBObjList beanAccByCustList = new JBObjList();

			boolean firstTime;
			String marker = "";
			String myFlag = "";
			String row = "0";
			
			
			while (true) {

				msgList = (ESD014001Message)newmessage;

				marker = msgList.getH01FLGMAS();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					if (!beanList.getNoResult()) beanAccByCustList.addRow(beanList);
					break;
				}
				else {
															
					if (!myFlag.equals(msgList.getE01CUSNUM())) {
						
						myFlag = msgList.getE01CUSNUM();						
						beanCustList.addRow(msgList);
						if (!beanList.getNoResult()) {
							beanAccByCustList.addRow(beanList); 					
							beanList = new JBObjList();
						}
					}

					
					beanList.addRow(msgList);
					
				}
				newmessage = mc.receiveMessage();
			}
					
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setPurpose("APPROVAL");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("appList", beanAccByCustList);
			ses.setAttribute("custList", beanCustList);
			ses.setAttribute("error", msgError);
			ses.setAttribute("ROWCUST",row);
			
			if (beanCustList.getNoResult()){
				try {
					flexLog("About to call Page: " + LangPath + "MISC_no_result.jsp");
				 	res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
			   	}
			    catch (Exception e) {
				    	flexLog("Exception calling page " + e);
			    }
			}    
			else {

			   	try {
				   	flexLog("About to call Page: " + LangPath + "ESD0140_approval_list.jsp");
				   	callPage(LangPath + "ESD0140_approval_list.jsp", req, res);
			    }
			    catch (Exception e) {
				   flexLog("Exception calling page " + e);
			    }
			}    
		
	  	}
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
}
/**
 * This method was created in VisualAge.
 */
protected void procReqApprovalInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;	

	try {
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		int appCode = Integer.parseInt(req.getParameter("appCode"));
		String accNum = req.getParameter("ACCNUM");
		String typeCode = req.getParameter("typeCode");

		datapro.eibs.products.JOActionRedirect red = new datapro.eibs.products.JOActionRedirect(typeCode, ACC_APPROVAL_INQ, appCode, accNum, LangPath, ses);
		res.sendRedirect(super.srctx + red.action());
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
	
}
	

/**
 * This method was created in VisualAge.
 */
protected void procReqPassword(HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;

	try {
		
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		userPO.setRedirect("/servlet/datapro.eibs.approval.JSESD0140?SCREEN=" + R_APPROVAL + (req.getParameter("ACCNUM") == null ? "" : "&ACCNUM=" + req.getParameter("ACCNUM")));
		ses.setAttribute("userPO", userPO);
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");
		
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	
}

/**
 * service method comment.
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	Socket s = null;
	MessageContext mc = null;

	ESS0030DSMessage msgUser = null;
  	HttpSession session = null;

	session = (HttpSession)req.getSession(false); 
	
	if (session == null) {
		try {
			res.setContentType("text/html");
			printLogInAgain(res.getWriter());
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception ocurred. Exception = " + e);
		}
	}
	else {

		int screen = R_APPROVAL;
		
		try {
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";
			Lang = msgUser.getE01LAN();
			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
			  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				case R_PASSWORD :
					procReqPassword(req, res, session);
					break;
				case R_APPROVAL :
					procReqApproval(mc, msgUser, req, res, session);
					break;
				case A_APPROVAL :
					procActionApproval(mc, msgUser, req, res, session);
					break;
				case R_APPROVAL_INQ :
					procReqApprovalInq(mc, msgUser, req, res, session);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
				s.close();
			}
			

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}

}
}