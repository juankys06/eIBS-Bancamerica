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

public class JSEDL0156 extends datapro.eibs.master.SuperServlet {

	static final int R_LIST  	= 1;
	
	static final int R_NEW 	 	= 100;
	static final int A_MAINT  	= 200;
	static final int R_MAINT  	= 300;
	
	static final int R_ENTER_NEW  	= 1000;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0156() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0156");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015601Message msgList = null;
		ELEERRMessage msgError = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		if (userPO.getBank() == null || userPO.getBank().equals("")) {
			userPO.setBank(req.getParameter("E01DLEBNK").toUpperCase());
		}

		// Send Initial data
		try {
			msgList = (EDL015601Message) mc.getMessageRecord("EDL015601");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDL015601");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			try { 
				msgList.setE01DLEBNK(userPO.getBank());
			} catch (Exception e) {
				
			}
			try { 
				msgList.setE01DLEPRO(req.getParameter("E01DLEPRO").toUpperCase());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
			}
				
			msgList.send();
			msgList.destroy();
			flexLog("EDL015601 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			} 
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			  newmessage = mc.receiveMessage();
								
				if (newmessage.getFormatName().equals("EDL015601")) {

					beanList = new JBObjList();
					String marker = "";

					while (true) {

						msgList = (EDL015601Message) newmessage;

						marker = msgList.getE01DLEOPE();

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
					ses.setAttribute("lnList", beanList);
					ses.setAttribute("userPO", userPO);				

					try {
						flexLog("About to call Page: " + LangPath + "EDL0156_ln_sel_cost.jsp");
						callPage(LangPath + "EDL0156_ln_sel_cost.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
					
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015601Message msgLnCost = new EDL015601Message();
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgLnCost = (EDL015601Message)mc.getMessageRecord("EDL015601");
			msgLnCost.setH01USERID(user.getH01USR());
			msgLnCost.setH01PROGRM("EDL0156");
			msgLnCost.setH01TIMSYS(getTimeStamp());
			msgLnCost.setH01SCRCOD("01");
			msgLnCost.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgLnCost.fieldEnumeration();
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

			//msgLnCost.send();
			mc.sendMessage(msgLnCost);
			msgLnCost.destroy();
			flexLog("EDL015601 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL015601")) {
				try {
					msgLnCost = new datapro.eibs.beans.EDL015601Message();
					flexLog("EDL015601 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLnCost = (EDL015601Message) newmessage;
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("lnCost", msgLnCost);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href = req.getContextPath() + "/pages/e/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.products.JSEDL0156?SCREEN=1'";
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println("		top.opener.location.href = \""+ href + "\";");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "EDL0156_ln_cost_maint.jsp");
							callPage(LangPath + "EDL0156_ln_cost_maint.jsp", req, res);
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

	protected void procReqEnterNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = new UserPos();

		try {
			flexLog("About to call Page: " + LangPath + "EDL0156_ln_enter_sel_cost.jsp");
			callPage(LangPath + "EDL0156_ln_enter_sel_cost.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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
		EDL015601Message msgCost = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("NEW");
		
		// Send Initial data
		try {
			flexLog("document sent");
			msgCost = (EDL015601Message) mc.getMessageRecord("EDL015601");
			
			try{
				msgCost.setE01DLEBNK(userPO.getBank());
			} catch (Exception e) {
			}	
			//msgCost.setE01DLEPRO("LNS");
			
			flexLog("document assigned");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
				ses.setAttribute("lnCost", msgCost);
			
			try {
				flexLog("About to call Page: " + LangPath + "EDL0156_ln_cost_maint.jsp");
				callPage(LangPath + "EDL0156_ln_cost_maint.jsp", req, res);
				} 
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	}

	protected void procReqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL015601Message msgLnCost = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		
		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
		
		// Send Initial data
		try {
			msgLnCost = (EDL015601Message) mc.getMessageRecord("EDL015601");
			msgLnCost.setH01USERID(user.getH01USR());
			msgLnCost.setH01PROGRM("EDL015601");
			msgLnCost.setH01TIMSYS(getTimeStamp());
			msgLnCost.setH01SCRCOD("01");
			msgLnCost.setH01OPECOD("0002");
			
			try{
				msgLnCost.setE01DLEBNK(req.getParameter("Bank"));
			} catch (Exception e) {
			}	

			try{
				msgLnCost.setE01DLEPRO(req.getParameter("Product"));
			} catch (Exception e) {
			}	

			msgLnCost.send();
			msgLnCost.destroy();
			flexLog("EDL015601 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL015601")) {
				try {
					msgLnCost = new EDL015601Message();
					flexLog("EDL015601 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgLnCost = (EDL015601Message) newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("lnCost", msgLnCost);

				try {
					flexLog("About to call Page: " + LangPath + "EDL0156_ln_cost_maint.jsp");
					callPage(LangPath + "EDL0156_ln_cost_maint.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

				/*
				if (IsNotError) { // There are no errors
					
					try {
						flexLog("About to call Page: " + LangPath + "EDL0156_ln_cost_maint.jsp");
						callPage(LangPath + "EDL0156_ln_cost_maint.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					try {
								   flexLog("About to call Page: " + LangPath + "EDL0510_ln_sel_tables.jsp");
								   callPage(LangPath + "EDL0510_ln_sel_tables.jsp", req, res);
					  } catch (Exception e) {
							   flexLog("Exception calling page " + e);
					  }
				}
				*/	  
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

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

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// Requests
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;
					case R_NEW :
						procReqNew(mc, msgUser, req, res, session);
						break;
					case R_ENTER_NEW :
						procReqEnterNew(mc, msgUser, req, res, session);
						break;
					case R_MAINT :
						procReqMaint(mc, msgUser, req, res, session);
						break;	
					// Actions
					case A_MAINT :
						procActionMaint(mc, msgUser, req, res, session);
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
				}
				finally {
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
}