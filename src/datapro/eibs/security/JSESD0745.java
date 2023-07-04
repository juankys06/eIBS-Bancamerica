package datapro.eibs.security;

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

import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;

public class JSESD0745 extends datapro.eibs.master.SuperServlet {

	protected static final int R_LIST = 1;
	protected static final int A_LIST = 2;
	protected static final int A_MAINT= 3;
	
	protected String LangPath = "E";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESD0745() {
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


	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

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

			int screen = R_LIST;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case R_LIST :
							procReqList(mc, msgUser, req, res, session);
							break;
						case A_LIST :
							procActionList(mc, msgUser, req, res, session);
							break;
						case A_MAINT :
							procActionMaintenance(mc, msgUser, req, res, session);
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

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

	protected void showERROR(ELEERRMessage m) {
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

	protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD074501Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
	
		userPO = new datapro.eibs.beans.UserPos();

		// Send Initial data
		try
		{
			msgList = (ESD074501Message)mc.getMessageRecord("ESD074501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0745");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
	 	
			msgList.send();	
			msgList.destroy();
		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
			
		// Receive Error Message
		try
		{

			newmessage = mc.receiveMessage();
	  
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
		
		// Receive Data
		try
		{	
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD074501")) {
			
				String marker = "";
				JBObjList beanList = new JBObjList();
			
				while (true) {

					msgList = (ESD074501Message) newmessage;

					marker = msgList.getE01EPROPE();
					msgList.setHandler(null);

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

				flexLog("Putting java beans into the session");
				ses.setAttribute("ppList", beanList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
					
				if (IsNotError)	 { //there are not error
					try {
							flexLog("About to call Page: " + LangPath + "ESD0745_parameter_password_list.jsp");
							callPage(LangPath + "ESD0745_parameter_password_list.jsp", req, res);	
					}
					catch (Exception e) {
							flexLog("Exception calling page " + e);				
					}					
				} else {
					try {
							flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
							callPage(LangPath + "error_viewer.jsp", req, res);	
					}
					catch (Exception e) {
							flexLog("Exception calling page " + e);				
					}
				}			
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	

	}
	
	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		UserPos	userPO = null;
		ELEERRMessage msgError = null;
		ESD074501Message msgList = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		String opt = req.getParameter("opt");
		try
		{	
			msgList = (ESD074501Message)mc.getMessageRecord("ESD074501");
			
			if (opt.equals("N")) {
			
				userPO.setPurpose("N");
										
			} else if (opt.equals("M")) {
					
				userPO.setPurpose("M");
				try {
					msgList.setE01EPRBNK(req.getParameter("BNK"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRDSC(req.getParameter("DSC"));
				} catch(Exception e){}
				try {
					msgList.setE01EPREXP(req.getParameter("EXP"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRPSL(req.getParameter("PSL"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRRPC(req.getParameter("RPC"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRLET(req.getParameter("LET"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRETM(req.getParameter("ETM"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRDPI(req.getParameter("DPI"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRCHG(req.getParameter("CHG"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRSK1(req.getParameter("SK1"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRSK2(req.getParameter("SK2"));
				} catch(Exception e){}
				try {
					msgList.setE01EPRSK3(req.getParameter("SK3"));
				} catch(Exception e){}
			
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("pp", msgList);
			ses.setAttribute("userPO", userPO);
			
			try {	
					flexLog("About to call Page: " + LangPath + "ESD0745_parameter_password_basic.jsp");
					callPage(LangPath + "ESD0745_parameter_password_basic.jsp", req, res);				
				
			}
			catch (Exception e) {
						flexLog("Exception calling page " + e);
			}

			
		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
		
	}
	
	protected void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD074501Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;
		String bnk = "";
		String opeCode = "0005";

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		String opt = req.getParameter("OPT");
		
		if (opt.equals("N")) {
			opeCode = "0001";
		} else if (opt.equals("M")) {
			opeCode = "0005";
		}
			
		// Send Initial data
		try
		{	
			msgList = (ESD074501Message)mc.getMessageRecord("ESD074501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD0745");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD(opeCode);

			// all the fields here
			java.util.Enumeration enu = msgList.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField)enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				}
				catch (Exception e) {
				}	
			}

			msgList.send();
			msgList.destroy();
			flexLog("ESD074501 Message Sent");
		
		// Receive Error Message
	
		  newmessage = mc.receiveMessage();
	  
		  if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		  }
		  else
			flexLog("Message " + newmessage.getFormatName() + " received.");
		
	
		// Receive Data
	
		  newmessage = mc.receiveMessage();

		  if (newmessage.getFormatName().equals("ESD074501")) {
				
				msgList = (ESD074501Message)newmessage;
				flexLog("Putting java beans into the session");
	
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);	
				try {	
					if (IsNotError) {						
						res.sendRedirect(super.srctx + 
							"/servlet/datapro.eibs.security.JSESD0745?SCREEN=1");		    
						
	
					} else {
						ses.setAttribute("pp", msgList);
						flexLog("About to call Page: " + LangPath + "ESD0745_parameter_password_basic.jsp");
						callPage(LangPath + "ESD0745_parameter_password_basic.jsp", req, res);				
					
					}
					
				}
				catch (Exception e) {
							flexLog("Exception calling page " + e);
				}
	
				}
			else 
				flexLog("Message " + newmessage.getFormatName() + " received.");
		}
		catch (Exception e)	{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	


	}
	
}