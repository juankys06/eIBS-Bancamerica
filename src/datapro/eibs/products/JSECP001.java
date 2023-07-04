package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

 
import datapro.eibs.sockets.*;
import datapro.eibs.master.Util;

public class JSECP001 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 
	protected static final int R_PROFIT		= 2;
	protected static final int R_SERVICES	= 4;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSECP001() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSEDL0130");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}
/**
 * This method was created in VisualAge.
 */
protected void procReqProfit(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
	MessageRecord newmessage = null;
	ECP001001Message msgProfit = null;
	ECP001002Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	JBList beanList = null;
	boolean IsNotError = false;
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	String opCode = "0002";

	// Send Initial data
	try {
		msgProfit = (ECP001001Message) mc.getMessageRecord("ECP001001");
		msgProfit.setH01USERID(user.getH01USR());
		msgProfit.setH01PROGRM("EDL0130");
		msgProfit.setH01TIMSYS(getTimeStamp());
		msgProfit.setH01SCRCOD("01");
		msgProfit.setH01OPECOD(opCode);
		
		try { 
		 msgProfit.setE01CUSNUM(req.getParameter("CUSTOMER"));
		}
		catch (Exception e) {
		 msgProfit.setE01CUSNUM(userPO.getCusNum());
		}
		
		try {
		 msgProfit.setE01SELMTH(req.getParameter("SELMTH"));
		}
		catch (Exception e) {
		 msgProfit.setE01SELMTH("M");
		} 
		msgProfit.send();
		msgProfit.destroy();
	} 
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
	}

	else if (newmessage.getFormatName().equals("ECP001001")) {
			try {
				msgProfit = new datapro.eibs.beans.ECP001001Message();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			msgProfit = (ECP001001Message) newmessage;
			
			flexLog("Putting java beans into the session"); 
			ses.setAttribute("error", msgError);
			ses.setAttribute("profit", msgProfit);
	   
	    } else
			flexLog("Message " + newmessage.getFormatName() + " received.");
			
			
				try {
					flexLog("About to call Page: " + LangPath + "ECP001_cif_profit.jsp");
					callPage(LangPath + "ECP001_cif_profit.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			
		
	} 
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
}

protected  void procReqServices(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {


	MessageRecord newmessage = null;
	ECP001001Message msgProfit = null;
	ECP001002Message msgInst = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	JBList beanList = null;
	boolean IsNotError = false;
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	String opCode = "0002";

	// Send Initial data
	try {
		msgInst = (ECP001002Message) mc.getMessageRecord("ECP001002");
		msgInst.setH02USERID(user.getH01USR());
		msgInst.setH02PROGRM("EDL0130");
		msgInst.setH02TIMSYS(getTimeStamp());
		msgInst.setH02SCRCOD("01");
		msgInst.setH02OPECOD(opCode);
		
		try { 
		 msgInst.setE02CUSNUM(req.getParameter("CUSTOMER"));
		}
		catch (Exception e) {
		 msgInst.setE02CUSNUM(userPO.getCusNum());
		}
		
		msgInst.send();
		msgInst.destroy();
	} 
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

	// Receive Error Message
	try {
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
	}

	else if (newmessage.getFormatName().equals("ECP001002")) {
				
			msgInst = (ECP001002Message) newmessage;	
				
				try {
			    	beanList = new JBList();
				} catch (Exception ex) {
				  flexLog("Error: " + ex);
				}


					boolean firstTime;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					java.math.BigDecimal income = new java.math.BigDecimal(0);
					String realincome = "";
					
					int indexRow = 0;
					while (true) {
						msgInst = (ECP001002Message) newmessage;
						marker = msgInst.getE02CUSTYP();
				

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {


							myRow = new StringBuffer("<TR>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgInst.getE02NAR() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT>" + msgInst.getE02NTRN() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=RIGHT>" + Util.fcolorCCY(msgInst.getE02FEEINC()) + "</td>");
							myRow.append("</TR>");

							income = income.add(msgInst.getBigDecimalE02FEEINC());
							realincome = income.toString();
							
							userPO.setHeader21(Util.fcolorCCY(realincome));
							userPO.setCusNum(msgInst.getE02CUSNUM());
							userPO.setCusName(msgInst.getE02CUSNME());
							userPO.setHeader3(msgInst.getE02CUSTYP());
							
							beanList.addRow(myFlag, myRow.toString());
							
							
							indexRow++;
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}


					flexLog("Putting java beans into the session");
					ses.setAttribute("services", beanList);
					ses.setAttribute("userPO", userPO);
				
	   
	   
	    } else
			flexLog("Message " + newmessage.getFormatName() + " received.");
			
			
				try {
					flexLog("About to call Page: " + LangPath + "ECP001_services.jsp");
					callPage(LangPath + "ECP001_services.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			
		
	} 
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
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

		int screen = R_PROFIT;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection ");
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
				case R_PROFIT :
					procReqProfit(mc, msgUser, req, res, session);
					break;	
				case R_SERVICES :
					procReqServices(mc, msgUser, req, res, session);
					break;				
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}

			} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}

	}
	
}
      
protected void showERROR(ELEERRMessage m)
{
	if (logType != NONE) {
		
		flexLog("ERROR received.");
		
		flexLog("ERROR number:" + m.getERRNUM());
		flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01());
		flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02());
		flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03());
		flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04());
		flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05());
		flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06());
		flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07());
		flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08());
		flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09());
		flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10());
		
	}
}



}