package datapro.eibs.products;

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
 
import datapro.eibs.sockets.*;

public class JSEFE0100 extends datapro.eibs.master.SuperServlet {

	// CIF options
	protected static final int R_ENTER 	= 1;
	protected static final int R_LIST 	= 2;
	
	protected static final int R_MAINT 	= 3;
	protected static final int R_NEW 	= 4;

	protected static final int A_NEW_MAINT 	= 5;

	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEFE0100() {
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

		int screen = R_LIST;
		
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
				case R_ENTER :
					procReqEnter(mc, msgUser, req, res, session);
					break;
				case R_LIST :
					procReqList(mc, msgUser, req, res, session);
					break;
				case R_NEW :
					procReqNew(mc, msgUser, req, res, session);
					break;
				case R_MAINT :
					procReqMaintenance(mc, msgUser, req, res, session);
					break;

				case A_NEW_MAINT :
					procActionNewMaint(mc, msgUser, req, res, session);
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

protected void procReqEnter(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {
	
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	
	try {
		msgError = new ELEERRMessage();
		userPO = new UserPos();
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
			
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}
	
	try {
		flexLog("About to call Page: " + LangPath + "EFE0100_fx_enter.jsp");
		callPage(LangPath + "EFE0100_fx_enter.jsp", req, res);
	} catch (Exception e) {
		flexLog("Exception calling page " + e);
	}
	
}

protected void procReqList(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EFE010001Message msgSearch = null;
	EFE010001Message msgList = null;
		
	JBObjList beanList = null;
	UserPos userPO = null;
	boolean IsNotError = true;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgSearch = (EFE010001Message) mc.getMessageRecord("EFE010001");
		msgSearch.setH01USERID(user.getH01USR());
		msgSearch.setH01PROGRM("EFE0100");
		msgSearch.setH01TIMSYS(getTimeStamp());
		msgSearch.setH01SCRCOD("01");
		msgSearch.setH01OPECOD("0015");

		try {
			msgSearch.setE01FEGBNK(req.getParameter("E01FEGBNK"));
		} catch (Exception e) {
			msgSearch.setE01FEGBNK("");
		}
		try {
			msgSearch.setE01FEGCCY(req.getParameter("E01FEGCCY"));
		} catch (Exception e) {
			msgSearch.setE01FEGCCY("");
		}
		try {
			msgSearch.setE01FEGTYP(req.getParameter("E01FEGTYP"));
		} catch (Exception e) {
			msgSearch.setE01FEGTYP("");
		}
		    	
		msgSearch.send();
		msgSearch.destroy();
		flexLog("EFE010001 Message Sent");
		
		// Receive Message
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = new ELEERRMessage();
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		} else {
			flexLog("Message " + newmessage.getFormatName() + " received.");				
		}			
				
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EFE010001")) {
			beanList = new JBObjList();
			boolean firstTime = true;
			String marker = "";

			while (true) {
				msgList = (EFE010001Message) newmessage;
				flexLog("EFE010001 Message Received");					
				marker = msgList.getE01OPECDE();

				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				} else {
					beanList.addRow(msgList);
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}
			userPO.setBank(req.getParameter("E01FEGBNK"));
			userPO.setCurrency(req.getParameter("E01FEGCCY"));
			userPO.setType(req.getParameter("E01FEGTYP"));

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("beanList", beanList);
			ses.setAttribute("userPO", userPO);
				
			if (IsNotError) { // There are no errors
				try {
					flexLog("About to call Page: " + LangPath + "EFE0100_fx_list.jsp");
					callPage(LangPath + "EFE0100_fx_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else {
				try {
					flexLog("About to call Page: " + LangPath + "EFE0100_fx_enter.jsp");
					callPage(LangPath + "EFE0100_fx_enter.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			}
		}	
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

}

protected void procReqNew(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EFE010001Message msgSearch = null;
		
	UserPos userPO = null;
	boolean IsNotError = true;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgSearch = (EFE010001Message) mc.getMessageRecord("EFE010001");
		msgSearch.setH01USERID(user.getH01USR());
		msgSearch.setH01PROGRM("EFE0100");
		msgSearch.setH01TIMSYS(getTimeStamp());
		msgSearch.setH01SCRCOD("01");
		msgSearch.setH01OPECOD("0001");

		try {
			msgSearch.setE01FEGBNK(req.getParameter("NEWBNK"));
		} catch (Exception e) {
			msgSearch.setE01FEGBNK("");
		}
		try {
			msgSearch.setE01FEGCCY(req.getParameter("NEWCCY"));
		} catch (Exception e) {
			msgSearch.setE01FEGCCY("");
		}
		try {
			msgSearch.setE01FEGTYP(req.getParameter("NEWTYP"));
		} catch (Exception e) {
			msgSearch.setE01FEGTYP("");
		}
		try {
			msgSearch.setE01FEGCLS(req.getParameter("NEWCLS"));
		} catch (Exception e) {
			msgSearch.setE01FEGCLS("");
		}
		    	
		msgSearch.send();
		msgSearch.destroy();
		flexLog("EFE010001 Message Sent");
		
		// Receive Message
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = new ELEERRMessage();
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		} else {
			flexLog("Message " + newmessage.getFormatName() + " received.");				
		}			
				
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EFE010001")) {

			try {
				msgSearch = new datapro.eibs.beans.EFE010001Message();
				flexLog("EFE010001 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgSearch = (EFE010001Message) newmessage;
							
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("msgBasic", msgSearch);
			ses.setAttribute("userPO", userPO);
				
			if (IsNotError) { // There are no errors
				if (msgSearch.getE01FEGTYP().equals("SPOT")) {
					try {
						flexLog("About to call Page: " + LangPath + "EFE0100_fx_basic_spot.jsp");
						callPage(LangPath + "EFE0100_fx_basic_spot.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}				
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "EFE0100_fx_basic.jsp");
						callPage(LangPath + "EFE0100_fx_basic.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}				
				
			} else {
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEFE0100?SCREEN=2&E01FEGBNK="
								+ userPO.getBank()
								+ "&E01FEGCCY="
								+ userPO.getCurrency()
								+ "&E01FEGTYP="
								+ userPO.getType());
				
			}
		}	
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

}

protected void procReqMaintenance(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EFE010001Message msgSearch = null;
		
	UserPos userPO = null;
	boolean IsNotError = true;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgSearch = (EFE010001Message) mc.getMessageRecord("EFE010001");
		msgSearch.setH01USERID(user.getH01USR());
		msgSearch.setH01PROGRM("EFE0100");
		msgSearch.setH01TIMSYS(getTimeStamp());
		msgSearch.setH01SCRCOD("01");
		msgSearch.setH01OPECOD("0002");

		try {
			msgSearch.setE01FEGBNK(req.getParameter("BNK"));
		} catch (Exception e) {
			msgSearch.setE01FEGBNK("");
		}
		try {
			msgSearch.setE01FEGCCY(req.getParameter("CCY"));
		} catch (Exception e) {
			msgSearch.setE01FEGCCY("");
		}
		try {
			msgSearch.setE01FEGTYP(req.getParameter("TYP"));
		} catch (Exception e) {
			msgSearch.setE01FEGTYP("");
		}
		try {
			msgSearch.setE01FEGCLS(req.getParameter("CLS"));
		} catch (Exception e) {
			msgSearch.setE01FEGCLS("");
		}
		    	
		msgSearch.send();
		msgSearch.destroy();
		flexLog("EFE010001 Message Sent");
		
		// Receive Message
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = new ELEERRMessage();
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		} else {
			flexLog("Message " + newmessage.getFormatName() + " received.");				
		}			
				
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EFE010001")) {

			try {
				msgSearch = new datapro.eibs.beans.EFE010001Message();
				flexLog("EFE010001 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgSearch = (EFE010001Message) newmessage;
							
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("msgBasic", msgSearch);
			ses.setAttribute("userPO", userPO);
				
			if (IsNotError) { // There are no errors
				if (msgSearch.getE01FEGTYP().equals("SPOT")) {
					try {
						flexLog("About to call Page: " + LangPath + "EFE0100_fx_basic_spot.jsp");
						callPage(LangPath + "EFE0100_fx_basic_spot.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}				
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "EFE0100_fx_basic.jsp");
						callPage(LangPath + "EFE0100_fx_basic.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}				
			} else {
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEFE0100?SCREEN=2&E01FEGBNK="
								+ userPO.getBank()
								+ "&E01FEGCCY="
								+ userPO.getCurrency()
								+ "&E01FEGTYP="
								+ userPO.getType());
				
			}
		}	
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

}

protected void procActionNewMaint(
	MessageContext mc,
	ESS0030DSMessage user,
	HttpServletRequest req,
	HttpServletResponse res,
	HttpSession ses)
	throws ServletException, IOException {

	MessageRecord newmessage = null;
	ELEERRMessage msgError = null;
	EFE010001Message msgSearch = null;
		
	UserPos userPO = null;
	boolean IsNotError = true;

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	// Send Initial data
	try {
		flexLog("Send Initial Data");
		msgSearch = (EFE010001Message) ses.getAttribute("msgBasic");
		msgSearch.setH01USERID(user.getH01USR());
		msgSearch.setH01PROGRM("EFE0100");
		msgSearch.setH01TIMSYS(getTimeStamp());
		msgSearch.setH01SCRCOD("01");
		msgSearch.setH01OPECOD("0005");

		// all the fields here
		java.util.Enumeration enu = msgSearch.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField) enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			} catch (Exception e) {
			}
		}
		    	
		//msgSearch.send();
		mc.sendMessage(msgSearch);
		msgSearch.destroy();
		flexLog("EFE010001 Message Sent");
		
		// Receive Message
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = new ELEERRMessage();
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		} else {
			flexLog("Message " + newmessage.getFormatName() + " received.");				
		}			
				
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EFE010001")) {

			try {
				msgSearch = new datapro.eibs.beans.EFE010001Message();
				flexLog("EFE010001 Message Received");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			msgSearch = (EFE010001Message) newmessage;
							
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("msgBasic", msgSearch);
			ses.setAttribute("userPO", userPO);
				
			if (IsNotError) { // There are no errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEFE0100?SCREEN=2&E01FEGBNK="
								+ userPO.getBank()
								+ "&E01FEGCCY="
								+ userPO.getCurrency()
								+ "&E01FEGTYP="
								+ userPO.getType());
			} else {
				if (msgSearch.getE01FEGTYP().equals("SPOT")) {
					try {
						flexLog("About to call Page: " + LangPath + "EFE0100_fx_basic_spot.jsp");
						callPage(LangPath + "EFE0100_fx_basic_spot.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}				
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "EFE0100_fx_basic.jsp");
						callPage(LangPath + "EFE0100_fx_basic.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}				
				
			}
		}	
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
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