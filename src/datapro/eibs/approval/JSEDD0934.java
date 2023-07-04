package datapro.eibs.approval;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.master.JSEIBSProp; 
import datapro.eibs.sockets.*;

import java.util.*;
import java.util.Calendar;

public class JSEDD0934 extends datapro.eibs.master.SuperServlet {

	protected static final int R_SEARCH 				= 2;
	protected static final int A_SEARCH 				= 3;

	protected static final int A_ENTER					= 400;
	protected static final int A_RETURN_ITEMS			= 5;
	// entering options
	protected static final int R_ENTER					= 100;
	protected static final int R_LARGE_ITEMS			= 8;
	protected static final int R_NSF					= 7;
	// CIF options
	protected static final int R_PASSWORD				= 1;
	protected static final int R_SUMMARY 				= 6;
	protected static final int R_UNPOSTED				= 4;

	protected static final int R_POSTED					= 9;
	
	private String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEDD0934() {
	super();
}

/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSESD0080");
	
}

/**
 * This method was created by Orestes Garcia.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}

/**
 * This method was created in VisualAge.
 */
protected void procActionSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	DataCheckReject	dataCR = null;	

	try{
		dataCR = (datapro.eibs.beans.DataCheckReject)ses.getAttribute("dataCR");
		dataCR.setOfficer(req.getParameter("E01SELOFC").toUpperCase());  // Official Code
		dataCR.setBranch(req.getParameter("E01SELBRN").toUpperCase());
		dataCR.setAmount(req.getParameter("E01SELAMT").toUpperCase());
		dataCR.setRelation(req.getParameter("E01SELREL"));
		ses.setAttribute("dataCR", dataCR);
		procReqList(mc, user, req, res, ses);
			
	} catch (Exception e) {
		e.printStackTrace();
	  	throw new RuntimeException("Socket Communication Error");
	}
}

/**
 * This method was created in VisualAge.
 */
protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EDD093401Message msgSearch = null;
	ELEERRMessage msgError = null;
	JBListRec beanList = null;
	DataCheckReject	dataCR = null;	
	boolean IsNotError = false;
	String Pos ="0";
	
	msgError = new datapro.eibs.beans.ELEERRMessage();

	dataCR = (datapro.eibs.beans.DataCheckReject)ses.getAttribute("dataCR");
	
	try {
		if (req.getParameter("FlagMov") == null || req.getParameter("FlagMov").equals("0"))
			Pos = "0";
		else if(req.getParameter("FlagMov").equals("+"))
			Pos = dataCR.getNext();
		else if(req.getParameter("FlagMov").equals("-")) {
			dataCR.setIndex(dataCR.getIndex() - 1);
			Pos = "" + (Integer.parseInt(dataCR.getPrevious()) - 1);
		} else if (dataCR.getIndex() == 0)
			Pos = "0";
		else
			Pos = "" + Integer.parseInt(dataCR.getNext(dataCR.getIndex() - 1));
	} catch (Exception e) {
	}

	try {
		flexLog("Send Initial Data");
		msgSearch = (EDD093401Message)mc.getMessageRecord("EDD093401");
		msgSearch.setH01USERID(user.getH01USR());
	 	msgSearch.setH01PROGRM("EDD0934");
	 	msgSearch.setH01TIMSYS(getTimeStamp());
	 	msgSearch.setH01SCRCOD("01");
	 	msgSearch.setH01OPECOD("0000");
	 	try {
			try {
		 		msgSearch.setACMBNK(dataCR.getBank());
			} catch (Exception e) {
			}
			try {
		 		msgSearch.setACMCCY(dataCR.getCurrency());
			} catch (Exception e) {
			}
			try {
		 		msgSearch.setACMBRN(dataCR.getBranch());
			} catch (Exception e) {
			}			
			try {
		 		msgSearch.setACMOFC(dataCR.getOfficer());
			} catch (Exception e) {
			}
			try {
		 		msgSearch.setSELAMT(dataCR.getAmount());
			} catch (Exception e) {
			}
			try {
		 		msgSearch.setSELREL(dataCR.getRelation());
			} catch (Exception e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		  	flexLog("Input data error " + e);
		}
 		msgSearch.send();	
 		msgSearch.destroy();
		flexLog("EDD093401 Message Sent");

		// Receive Data
	    newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			// showERROR(msgError);
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
		}

		newmessage = mc.receiveMessage();
		if ((newmessage.getFormatName().equals("EDD093401")) ) {
			
			if (IsNotError) {
			int colNum = 45;
			beanList = new datapro.eibs.beans.JBListRec();
			beanList.init(colNum);

			boolean firstTime = true;
			String marker = "";
			String myFlag = "";

			String myRow[] = new String[colNum];
			for (int i=0; i<colNum; i++) {
				myRow[i] = "";
			}
			while (true) {
				
				msgSearch = (EDD093401Message)newmessage;
				marker = msgSearch.getH01FLGMAS();
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				} else {
					if (firstTime) {
						firstTime = false;
	//					beanList.setFirstRec(Integer.parseInt(msgSearch.getE01NUMREC()));
					}
					myRow[0] = msgSearch.getACMACC();  // Account Number
					myRow[17] = msgSearch.getACMGLN();  // GLN
					myRow[1] = msgSearch.getCUSNA1();  // Nombre Cliente
					myRow[2] = msgSearch.getNRORUT();  // Numero Rut
					myRow[3] = msgSearch.getDIGRUT();  // D.V.
					myRow[4] = msgSearch.getACMMNB();  // Saldo Disponible
					myRow[5] = msgSearch.getACMMGB();  // Saldo Contable
					myRow[6] = msgSearch.getACMLOD();  // Fecha Ultimo Protesto					}
					myRow[7] = msgSearch.getACMLOM();  // Fecha Ultimo Protesto
					myRow[8] = msgSearch.getACMLOY();  // Fecha Ultimo Protesto
					myRow[9] = msgSearch.getACMBRN();  // Sucursal
					myRow[10] = msgSearch.getACMOFC();  // Oficial
					myRow[11] = msgSearch.getACMDV3();  // N Protesto por Forma, Ciclo
					myRow[12] = msgSearch.getACMDV4();  // N Protesto por Forma, Anual
					myRow[13] = msgSearch.getACMNRY();  // N Protesto por Forma, Total
					myRow[14] = msgSearch.getACMDV1();  // N Protesto por Fondo, Ciclo
					myRow[15] = msgSearch.getACMDV2();  // N Protesto por Fondo, Anual
					myRow[16] = msgSearch.getACMNRL();  // N Protesto por Fondo, Total
					myRow[17] = msgSearch.getACMCDO();  // Dias Sobregiro
						
					beanList.addRow(myFlag, myRow);
		//			beanList.setLastRec(Integer.parseInt(msgSearch.getE01NUMREC()));				
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}
			
			try {
		 	 	if (dataCR.getMaxRow() == 0) {
			    	dataCR.addRow(beanList.getLastRec() + "", beanList.getFirstRec() + "");
			    } else if (req.getParameter("FlagMov").equals("+")) {
			 	 	dataCR.setIndex(dataCR.getIndex() + 1); 
			    	if (dataCR.getIndex() >= dataCR.getMaxRow()) {
			    	  dataCR.addRow(beanList.getLastRec() + "", beanList.getFirstRec() + "");
			    	}
		     	} 
			} catch (Exception e){
			}
			
			if (dataCR.getIndex() < 1) { 
				beanList.setShowPrev(false);
			} else {
				beanList.setShowPrev(true);
			}
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("chkList", beanList);
			ses.setAttribute("dataCR", dataCR);
		
			try {
				flexLog("About to call Page: " + LangPath + "EDD0934_rejection_chk_sobregiros_list.jsp");
				callPage(LangPath + "EDD0934_rejection_chk_sobregiros_list.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EDD0934_rejection_chk_search.jsp");
					callPage(LangPath + "EDD0934_rejection_chk_search.jsp", req, res);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	} catch (Exception e) {
		e.printStackTrace();
	  	throw new RuntimeException("Socket Communication Error");
	}
}


/**
 * This method was created in VisualAge.
 */
protected void procReqSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	DataCheckReject	dataCR = null;
	
	msgError = new datapro.eibs.beans.ELEERRMessage();
	dataCR = (datapro.eibs.beans.DataCheckReject) ses.getAttribute("dataCR");

	ses.setAttribute("error", msgError);
	ses.setAttribute("dataCR", dataCR);

	try {
		flexLog("About to call Page: " + LangPath + "EDD0934_rejection_chk_search.jsp");
		callPage(LangPath + "EDD0934_rejection_chk_search.jsp", req, res);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
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
		int screen = R_SEARCH;
		
		try {
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

//		 Here we should get the path from the user profile
	 		LangPath = super.rootPath + msgUser.getE01LAN() + "/";
// 		    LangPath = "/pages/s/";
			try {
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 29);
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
					// Requests
					case R_PASSWORD :
						procReqPassword(msgUser, req, res, session);
						break;
					case R_SEARCH :
						procReqSearch(msgUser, req, res, session);
						break;
					case R_UNPOSTED :
					case R_POSTED :
					case R_LARGE_ITEMS :
						procReqList(mc, msgUser, req, res, session);
						break;
					// Actions
					case A_SEARCH :
						procActionSearch(mc, msgUser, req, res, session);
						break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 29;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
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

protected void procReqPassword(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	UserPos	userPO = null;
	DataCheckReject dataCR = null;
	JBListRec beanList = null;
	
	try {
		
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		dataCR = new datapro.eibs.beans.DataCheckReject();
		beanList = new datapro.eibs.beans.JBListRec();
		//beanList.init(colNum);
		
		dataCR.setOption(req.getParameter("opt"));
		userPO.setRedirect("/servlet/datapro.eibs.approval.JSEDD0934?SCREEN=" + R_SEARCH );
		
		dataCR.setBank(user.getE01UBK());
		dataCR.setCurrency(user.getE01BCU());
		
//		dataCR.setBank("01");
//		dataCR.setCurrency("CLP");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("dataCR", dataCR);
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");
		
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
}

}
