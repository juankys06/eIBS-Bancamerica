package datapro.eibs.forex;

/**
 * Insert the type's description here.
 * Creation date: (5/16/00 6:53:55 PM)
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

public class JSESWF000 extends datapro.eibs.master.SuperServlet {

	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int A_VALIDATE = 26;


	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESWF000() {
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
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procActionNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF00001Message msgSWFF = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		//String action = "F";
		//try{
		//	action = req.getParameter("ACTION");
		//}
		//catch (Exception e){
		//	flexLog("ACTION");
		//}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			//msgSWFF = (ESWF00001Message) ses.getAttribute("swff");
			msgSWFF = (ESWF00001Message)mc.getMessageRecord("ESWF00001");
			msgSWFF.setH01USR(user.getH01USR());
			msgSWFF.setH01PGM("ESWF000");
			msgSWFF.setH01TIM(getTimeStamp());
			msgSWFF.setH01SCR("01");
			msgSWFF.setH01OPE("0005");

			// all the fields here
			java.util.Enumeration enu = msgSWFF.fieldEnumeration();
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

			//msgRT.send();
			mc.sendMessage(msgSWFF);
			msgSWFF.destroy();
			flexLog("Este es un test");
			flexLog("ESWF00001 Message Sent");
		} catch (Exception e) {
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
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
        
        //Receive Data 
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF00001")) {
				try {
					msgSWFF = new ESWF00001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgSWFF = (ESWF00001Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page: " + LangPath + "ESWF000_swift_free_format_new_confirm.jsp");
						callPage(LangPath + "ESWF000_swift_free_format_new_confirm.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
							flexLog("About to call Page: " + LangPath + "ESWF000_swift_free_format_new.jsp");
							callPage(LangPath + "ESWF000_swift_free_format_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					

				  }
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionValidate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF01002Message msgSWFF = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			// msgSWFF = (ESWF01002Message) ses.getAttribute("swff");
			msgSWFF = (ESWF01002Message) mc.getMessageRecord("ESWF01002");
			msgSWFF.setH02USR(user.getH01USR());
			msgSWFF.setH02PGM("ESWF010");
			msgSWFF.setH02TIM(getTimeStamp());
			msgSWFF.setH02OPE("0006");

			// all the fields here
			java.util.Enumeration enu = msgSWFF.fieldEnumeration();
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

			//msgRT.send();
			mc.sendMessage(msgSWFF);
			msgSWFF.destroy();
			flexLog("ESWF01002 Message Sent");

		} catch (Exception e) {
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
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive Data 
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF01002")) {
				try {
					msgSWFF = new ESWF01002Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgSWFF = (ESWF01002Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);

				if (IsNotError) { // There are no errors 
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESWF000_swift_free_format_new.jsp");
						callPage(LangPath + "ESWF000_swift_free_format_new.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog(
							"About to call Page: " + LangPath + "ESWF000_swift_free_format_new.jsp");
						callPage(LangPath + "ESWF000_swift_free_format_new.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}


	
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESWF00001Message msgSWFF = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0001";

		// Send Initial data
		try {
			msgSWFF = (ESWF00001Message) mc.getMessageRecord("ESWF00001");
			msgSWFF.setH01USR(user.getH01USR());
			msgSWFF.setH01PGM("ESWF00001");
			msgSWFF.setH01TIM(getTimeStamp());
			msgSWFF.setH01SCR("01");
			msgSWFF.setH01OPE(opCode);
			//msgSWFF.setE01ACMPRO(userPO.getIdentifier());
			msgSWFF.send();
			msgSWFF.destroy();
		} catch (Exception e) {
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
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF00001")) {
				try {
					msgSWFF = new ESWF00001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgSWFF = (ESWF00001Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swff", msgSWFF);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page3: " + LangPath + "ESWF000_swift_free_format_new.jsp");
						callPage(LangPath + "ESWF000_swift_free_format_new.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page3: " + LangPath + "ESWF010_swift_free_format_approval_list.jsp");
						callPage(LangPath + "ESWF010_swift_free_format_approval_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
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


		int screen = R_NEW;
		
		try {


			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");


			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";


			try
			{
				flexLog("Opening Socket Connection");
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
				case R_NEW :
					procReqNew(mc,msgUser, req, res, session);
					break;	
				case A_NEW :
					procActionNew(mc, msgUser, req, res, session);
					break;
				case A_VALIDATE :
					procActionValidate(mc, msgUser, req, res, session);
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
	
protected void showEDL013001(EDL013001Message m)
  {
	if (logType != NONE) {
	  
		flexLog("Client Information received (EDL013001).");


		flexLog("User ID: " + m.getH01USERID());
		flexLog("Program: " + m.getH01PROGRM());
		flexLog("Time date: " + m.getH01TIMSYS());
		flexLog("Screen code: " + m.getH01SCRCOD());
		flexLog("Operate code: " + m.getH01OPECOD());
		flexLog("More records" + m.getH01FLGMAS());
		flexLog("Flag 1: " + m.getH01FLGWK1());
		flexLog("Flag 2: " + m.getH01FLGWK2());
		flexLog("Flag 3: " + m.getH01FLGWK3());


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
	
	
	
	
	
	
	
	
	

 