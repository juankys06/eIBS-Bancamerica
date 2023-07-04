package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (08/Mar/07 3:09:07 PM)
 * @author: William Alfaro ( DATAPRO Inc )
 */
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

import datapro.eibs.beans.ECB004001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSECB0040 extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_SEARCH_FILTER = 100;
	protected static final int A_SEARCH_FILTER = 200;

	protected String LangPath = "S";

	/**
	 * JSEEJ0030 constructor comment.
	 */
	public JSECB0040() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECB0030");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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

			int screen = R_SEARCH_FILTER;

			try {

				msgUser = 	(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, super.iniSocket + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
										    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
											"datapro.eibs.beans");
					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case R_SEARCH_FILTER :
							procReqSearch(msgUser, req, res, session);
							break;
						case A_SEARCH_FILTER :
							procActSearch(msgUser, req, res, session, mc);
						    break;	
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
		
		ECB004001Message msgSearch = new ECB004001Message();
		UserPos userPO = new UserPos();

		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		try { 
			
			flexLog("About to call Page: " + LangPath + "ECB0040_gestion_enter_inq.jsp");
			callPage(LangPath + "ECB0040_gestion_enter_inq.jsp", req, res);
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	
	protected void procActSearch(ESS0030DSMessage user, HttpServletRequest req,
	                             HttpServletResponse res, HttpSession ses,
	                             MessageContext mc)	throws ServletException, IOException {
		
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		JBObjList beanList = null;
		ECB004001Message msgList = null;
		boolean IsNotError = true;
		
		ECB004001Message msgSearch = new ECB004001Message();
		UserPos userPO = new UserPos();

		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);
		
		int TipoSol = Integer.parseInt(req.getParameter("TIPO"));
		String Prestamo = req.getParameter("H01GCMACC");
		String Cobrador = req.getParameter("H01GCMCOD");
		String FechaId  = req.getParameter("H01GCMFGD");
		String FechaIm  = req.getParameter("H01GCMFGM");
		String FechaIy  = req.getParameter("H01GCMFGY");
		String FechaFd  = req.getParameter("H01GCMTGD");
		String FechaFm  = req.getParameter("H01GCMTGM");
		String FechaFy  = req.getParameter("H01GCMTGY");		
		
		try{		
			msgSearch = (ECB004001Message)mc.getMessageRecord("ECB004001");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("ECB0040");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			switch(TipoSol){
				case 1:
				 	msgSearch.setH01OPECOD("0010");	
				 	msgSearch.setH01GCMACC(Prestamo);   
				   	break;
				case 2:
					msgSearch.setH01OPECOD("0011");
					msgSearch.setH01GCMCOD(Cobrador);	   
					break;
				case 3:   
					msgSearch.setH01OPECOD("0012");	 
					msgSearch.setH01GCMFGD(FechaId);
					msgSearch.setH01GCMFGM(FechaIm);
					msgSearch.setH01GCMFGY(FechaIy);
					msgSearch.setH01GCMTGD(FechaFd);
					msgSearch.setH01GCMTGM(FechaFm);
					msgSearch.setH01GCMTGY(FechaFy);  
					break;
			}
			
			
			msgSearch.send();
			msgSearch.destroy();
			flexLog("ECB004001 Message Sent");
			// Receive Message
			newmessage = mc.receiveMessage();
			boolean marker = false;
			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		
			newmessage = mc.receiveMessage();
						
			if (newmessage.getFormatName().equals("ECB004001")) {			    	
				beanList = new JBObjList();
				msgList = (ECB004001Message) newmessage;	
				marker = msgList.getH01FLGMAS().equals("*");			
				while (!marker) {
					beanList.addRow(msgList);
					newmessage = mc.receiveMessage();
					msgList = (ECB004001Message) newmessage;
					marker = msgList.getH01FLGMAS().equals("*");								
				}
				flexLog("Putting java beans into the session");
				ses.setAttribute("mtList", beanList);				
			}else
				flexLog("ERROR Message received : " + newmessage.getFormatName());			
			
		}catch(Exception e){
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
			
		}
		
		if (IsNotError) {
			try {
				flexLog("About to call Page: " + LangPath + "ECB0040_gestion_list.jsp");
				callPage(LangPath + "ECB0040_gestion_list.jsp", req, res);						
				
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		else {
			flexLog("Putting java beans into the session");
			try {
				flexLog("About to call Page: " + LangPath + "ECB0040_gestion_enter_inq.jsp");
				callPage(LangPath + "ECB0040_gestion_enter_inq.jsp", req, res);						
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}			
	}
	
}