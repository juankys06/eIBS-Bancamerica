package datapro.eibs.params;

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

public class JSESD0205I extends datapro.eibs.master.SuperServlet {

	
	static final int R_LIST  	= 1;
	static final int A_LIST   	= 2;
	static final int R_I_LIST 	= 3;
	
	static final int R_INQUIRY 	= 700;
	
	private String LangPath = "S";

	/**
	 * JSESD0205I constructor comment.
	 */
	public JSESD0205I() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0205I");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD000001Message msgRT1 = null;
		ESD020502Message msgDoc = null;
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
		userPO.setPurpose("INQUIRY");

		Socket s1 = null;
		MessageContext mc1 = null;

		try {
				flexLog("Opennig Socket Connection");
				s1 = new Socket(super.hostIP, getInitSocket(req) + 4);
				s1.setSoTimeout(super.sckTimeOut);
				mc1 =
					new MessageContext(
						new DataInputStream(new BufferedInputStream(s1.getInputStream())),
						new DataOutputStream(new BufferedOutputStream(s1.getOutputStream())),
						"datapro.eibs.beans");
		
				MessageRecord newmessage1 = null;
				ELEERRMessage msgError1 = null;
	
	
				boolean IsNotError1 = false;
		
				try{
					msgError1 =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				}catch (Exception ex){
					flexLog("Error: " + ex);
				}
				
				// Send Initial data for EDD0000
				try {
					msgRT1 = (EDD000001Message) mc1.getMessageRecord("EDD000001");
					msgRT1.setH01USERID(user.getH01USR());
					msgRT1.setH01PROGRM("EDD0000");
					msgRT1.setH01TIMSYS(getTimeStamp());
					msgRT1.setH01SCRCOD("01");
					if (userPO.getOption().equals("RT"))
						msgRT1.setH01FLGWK1("R");
					else if (userPO.getOption().equals("SV"))
						msgRT1.setH01FLGWK1("S");
					msgRT1.setH01OPECOD("0002");
					try {
						msgRT1.setE01ACMACC(userPO.getIdentifier());
					} catch (Exception e) {
						msgRT1.setE01ACMACC("0");
					}
		
					msgRT1.send();
					msgRT1.destroy();
					flexLog("EDD000001 Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
		
				// Receive Error Message
				try {
					newmessage1 = mc1.receiveMessage();
		
					if (newmessage1.getFormatName().equals("ELEERR")) {
						msgError1 = (ELEERRMessage) newmessage1;
						IsNotError1 = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError1);
						showERROR(msgError1);
					} else
						flexLog("Message " + newmessage.getFormatName() + " received.");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}

				// Receive Data for EDD0000
				try {
					newmessage1 = mc1.receiveMessage();
	
					if (newmessage1.getFormatName().equals("EDD000001")) {
						try {
							msgRT1 = new datapro.eibs.beans.EDD000001Message();
							flexLog("EDD000001 Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}
	
						msgRT1 = (EDD000001Message) newmessage1;
	
					} else
						flexLog("Message " + newmessage1.getFormatName() + " received.");
	
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
		} 
		catch (Exception e) 
		{
				e.printStackTrace();
				int sck = getInitSocket(req) + 4;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
		}
	
		finally
		{
			s1.close();
		}


		// Send Initial data for EDI010102
		try {
			msgDoc = (ESD020502Message) mc.getMessageRecord("ESD020502");
			msgDoc.setH02USERID(user.getH01USR());
			msgDoc.setH02PROGRM("EDI010102");
			msgDoc.setH02TIMSYS(getTimeStamp());
			msgDoc.setH02SCRCOD("01");
			msgDoc.setH02OPECOD("0002");
			
			try{
				msgDoc.setE02RTETAR(msgRT1.getE01ACMACL());
			} catch (Exception e) {}	

			try{
				msgDoc.setE02RTEBNK(msgRT1.getE01ACMBNK());
			} catch (Exception e) {}	

			try{
				msgDoc.setE02RTEATY(msgRT1.getE01ACMATY());
			} catch (Exception e) {}	

			msgDoc.send();
			msgDoc.destroy();
			flexLog("EDI010102 Message Sent");
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

		// Receive Data for ESD020502
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD020502")) {
				try {
					msgDoc = new ESD020502Message();
					flexLog("ESD020502 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgDoc = (ESD020502Message) newmessage;
				String atype = msgDoc.getE02RTEACD();
				
				if(atype.equals("02") || atype.equals("03")){
					acctype = 2;
				}
				else if(atype.equals("04")){
					acctype = 4;
				}
				else if(atype.equals("01")){
					acctype = 1;
				}
				else if(atype.equals("05")){
					acctype = 5;
				}
				else{
					try{
						acctype = Integer.parseInt(msgDoc.getE02RTEACD());
				} catch (Exception ex) {
				flexLog("Error: " + ex);
					}
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("charges", msgDoc);

				if (IsNotError) { // There are no errors
					switch (acctype) {
						case 1 :
								try {
									flexLog("About to call Page: " + LangPath + "ESD0205_rt_inq_tables_dda_01.jsp");
									callPage(LangPath + "ESD0205_rt_inq_tables_dda_01.jsp", req, res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
								break;
						case 2 :
								try {
									flexLog("About to call Page: " + LangPath + "ESD0205_rt_inq_tables_dda_02.jsp");
									callPage(LangPath + "ESD0205_rt_inq_tables_dda_02.jsp", req, res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
						case 4 :
							   try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_inq_tables_dda_04.jsp");
								   callPage(LangPath + "ESD0205_rt_inq_tables_dda_04.jsp", req, res);
							   } catch (Exception e) {
								   flexLog("Exception calling page " + e);
							   }
							   break;
						case 5 :
							   try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_inq_tables_dda_05.jsp");
								   callPage(LangPath + "ESD0205_rt_inq_tables_dda_05.jsp", req, res);
							   } catch (Exception e) {
								   flexLog("Exception calling page " + e);
							   }
							   break;
						default :
							   try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_inq_tables_dda_01.jsp");
								   callPage(LangPath + "ESD0205_rt_inq_tables_dda_01.jsp", req, res);
							   } catch (Exception e) {
								   flexLog("Exception calling page " + e);
							   }	  
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
					case R_INQUIRY :
						procReqInquiry(mc, msgUser, req, res, session);
						break;	
						// Actions
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
	private synchronized void showERROR(ELEERRMessage m) {
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