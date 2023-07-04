package datapro.eibs.credprop;

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

public class JSEPV5000 extends datapro.eibs.master.SuperServlet {


	static final int R_ENTER_NEW  		 = 100;
	static final int R_ENTER_SEARCH_PROP = 300;
	static final int R_LIST  			 = 500;
	static final int R_MAINT_PROP		 = 700;
	
	static final int A_ENTER_MAINT 		 = 200;
	static final int A_MAINT_CUSTOMER	 = 400;
	static final int A_LIST  			 = 600;
	static final int A_MAINT_PROPOSAL	 = 800;
	
	
	private String LangPath = "S";

	/**
	 * JSEPV5000 constructor comment.
	 */
	public JSEPV5000() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEPV5000");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	
	
	protected void procActionMaintCustomer(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPV500001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;
		String source = null;
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

//        flexLog("JSEPV5000-->procActionMaint");
       
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EPV500001Message) ses.getAttribute("customer");
			msgRT.setH01USR(user.getH01USR());
			msgRT.setH01PGM("EPV500001");
			msgRT.setH01TIM(getTimeStamp());
			msgRT.setH01SCR("01");
			msgRT.setH01OPE("0005");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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
			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EPV500001 Message Sent");
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

			if (newmessage.getFormatName().equals("EPV500001")) {
				try {
					msgRT = new datapro.eibs.beans.EPV500001Message();
					flexLog("EPV500001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EPV500001Message) newmessage;
				
 
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("customer", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EPV5000_client_both_enter.jsp");
						callPage(LangPath + "EPV5000_client_both_enter.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: /pages/" + LangPath + "EPV5000_platform_customer.jsp");
						callPage(LangPath + "EPV5000_platform_customer.jsp", req, res);	
					}
					catch (Exception e) {
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

		protected void procActionMaintProposal(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			MessageRecord newmessage = null;
			EPV500001Message msgRT = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
			int acctype = 0;
			String source = null;
				
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
	//        flexLog("JSEPV5000-->procActionMaint");
	       
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgRT = (EPV500001Message) ses.getAttribute("proposal");
				msgRT.setH01USR(user.getH01USR());
				msgRT.setH01PGM("EPV500001");
				msgRT.setH01TIM(getTimeStamp());
				msgRT.setH01SCR("01");
				msgRT.setH01OPE("0005");
	
				// all the fields here
				java.util.Enumeration enu = msgRT.fieldEnumeration();
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
				mc.sendMessage(msgRT);
				msgRT.destroy();
				flexLog("EPV500001 Message Sent");
			
	
			// Receive Error Message
			
				newmessage = mc.receiveMessage();
	
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
				
			// Receive Data
			
				newmessage = mc.receiveMessage();
	
				if (newmessage.getFormatName().equals("EPV500001")) {
					try {
						msgRT = new datapro.eibs.beans.EPV500001Message();
						flexLog("EPV500001 Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}
	
					msgRT = (EPV500001Message) newmessage;
					
	 
					
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("proposal", msgRT);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) {  // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EPV5000_proposal_selection.jsp");
							callPage(LangPath + "EPV5000_proposal_selection.jsp", req, res);	
						}
						catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
					else {  // There are errors
						try {
							flexLog("About to call Page: /pages/" + LangPath + "EPV5000_proposal_details.jsp");
							callPage(LangPath + "EPV5000_proposal_details.jsp", req, res);	
						}
						catch (Exception e) {
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
          
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

					try {
						flexLog("About to call Page: " + LangPath + "EPV5000_client_both_enter.jsp");
						callPage(LangPath + "EPV5000_client_both_enter.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

	}

	protected void procReqSearchProposal(
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
          
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

					try {
						flexLog("About to call Page: " + LangPath + "EPV5000_proposal_enter.jsp");
						callPage(LangPath + "EPV5000_proposal_enter.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

	}
	protected void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
				throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPV500001Message msgClientPersonal = null;
		ELEERRMessage msgError = null;
		UserPos	userPO = null;	
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}

		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		// Send Initial data
		try
		{
			msgClientPersonal = (EPV500001Message)mc.getMessageRecord("EPV500001");
			msgClientPersonal.setH01USR(user.getH01USR());
			msgClientPersonal.setH01PGM("EPV5000");
			msgClientPersonal.setH01TIM(getTimeStamp());
			msgClientPersonal.setH01SCR("01");
			msgClientPersonal.setH01OPE("0001");
	
			try {
				msgClientPersonal.setE01PVMIDN(req.getParameter("E01PVMIDN"));
			}
			catch (Exception e)
			{
			}
			//msgClientPersonal.setE01IDN("");
			msgClientPersonal.send();	
			msgClientPersonal.destroy();
			flexLog("EPV500001 Message Sent");
		}		
		catch (Exception e)
		{
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
		  }
		  else
			flexLog("Message " + newmessage.getFormatName() + " received.");
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}	
	
		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EPV500001")) {
				try {
					msgClientPersonal = new datapro.eibs.beans.EPV500001Message();
					flexLog("EPV500001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex); 
				}

				msgClientPersonal = (EPV500001Message)newmessage;

			
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("customer", msgClientPersonal);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) {  // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EPV5000_platform_customer.jsp");
						callPage(LangPath + "EPV5000_platform_customer.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else {  // There are errors
					try {
						flexLog("About to call Page: /pages/" + LangPath + "EPV5000_client_both_enter.jsp");
						callPage(LangPath + "EPV5000_client_both_enter.jsp", req, res);	
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


	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPV500002Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
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

		// Send Initial data
		try {
			msgList = (EPV500002Message) mc.getMessageRecord("EPV500002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("EPV500002");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02SCRCOD("01");
			msgList.setH02OPECOD("0015");
			

			try { 
				msgList.setE02SELTYP(req.getParameter("TYPSEL").toUpperCase());
				
			} catch (Exception e) {
				
			}
			
			try { 
				msgList.setE02SELIDE(req.getParameter("E01PVMIDN").toUpperCase());
				userPO.setCusNum(req.getParameter("E01PVMIDN"));
			} catch (Exception e) {
				
			}

			try { 
				msgList.setE02SELUSR(user.getH01USR());
				msgList.setE02PVMSTS(req.getParameter("E02PVMSTS"));				
			} catch (Exception e) {
				
			}
			
			
			msgList.send();
			msgList.destroy();
			flexLog("EPV500002 Message Sent");
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

				// showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
			else if (newmessage.getFormatName().equals("EPV500002")) {

					beanList = new JBList();

					boolean firstTime =true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					//var for ofac status
					//var for Warning status
					String chkOfac = "";
					String chkWarn = "";
					int compar = 0;
					int indexRow = 0;
					while (true) {

						msgList = (EPV500002Message) newmessage;

						marker = msgList.getH02FLGMAS();
						
						if (firstTime) {
							firstTime = false;
							chk = "checked";
							}
						else {
							chk = "";
						}						
					
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							
							
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><input type=\"radio\" name=\"ACCNUM\" value=\""
								+ msgList.getE02PVMNUM()
								+ "\" "
								+ chk
							+ " onclick=\"getParams('"
							+ msgList.getE02PVMNUM() 
							+ "','"  
							+ msgList.getE02PVMCUN() 
							+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\">" + msgList.getE02PVMNUM() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"30%\">" + msgList.getE02PVMNME() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"40%\">" + msgList.getE02PVMSTS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"40%\">" + msgList.getD02PVMSTS() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"40%\">" + Util.formatDate(msgList.getE02PVMOP1(),msgList.getE02PVMOP1(),msgList.getE02PVMOP1()) + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"40%\">" + msgList.getPVMRTE() + "</td>");
							myRow.append("</TR>");
														
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
					ses.setAttribute("EPV5000Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EPV5000_proposal_selection.jsp");
						callPage(LangPath + "EPV5000_proposal_selection.jsp", req, res);
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


	protected void procActionPos(
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

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("opt"));
		//Maintenance/New
		String CUSTIDN = req.getParameter("CUSTIDN");
		String PROPNUM = req.getParameter("PROPNUM");
		String CUSNUM = req.getParameter("CUSNUM");

		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.credprop.JSEPV5000?SCREEN=700"  + "&CUSTIDN=" + CUSTIDN);		    
				break;
			case 2 : //Maintenance
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.params.JSEPV5000?SCREEN=700" + "&PROPNUM=" + PROPNUM + "&CUSNUM=" + CUSNUM);
				break;
				
			
			default :
			res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.params.JSEPV5000?SCREEN=700"  + "&CUSTIDN=" + CUSTIDN);			    
		}
	}

	protected void procReqMaintProp(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EPV500001Message msgProp = null;
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
		
//		flexLog("JSEDL0110L-->procReqMaint");

		// Send Initial data
		try {
			msgProp = (EPV500001Message) mc.getMessageRecord("EPV500001");
			msgProp.setH01USR(user.getH01USR());
			msgProp.setH01PGM("EPV500001");
			msgProp.setH01TIM(getTimeStamp());
			msgProp.setH01SCR("01");
			msgProp.setH01OPE("0002");
			
			
			try{
				msgProp.setE01PVMCUN(req.getParameter("CUSNUM"));
			} catch (Exception e) {
			}	
			
			try{
				msgProp.setE01PVMNUM(req.getParameter("PROPNUM"));
			} catch (Exception e) {
			}
				
			try{
				msgProp.setE01PVMIDN(req.getParameter("CUSTIDN"));
			} catch (Exception e) {
			}
			
			msgProp.send();
			msgProp.destroy();
 		
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

			if (newmessage.getFormatName().equals("EPV500001")) {
				try {
					msgProp = new EPV500001Message();
					flexLog("EPV500001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgProp = (EPV500001Message) newmessage;
				

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("proposal", msgProp);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "EPV5000_proposal_details.jsp");
						callPage(LangPath + "EPV5000_proposal_details.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {
						flexLog("About to call Page: " + LangPath + "EPV5000_proposal_selection.jsp");
						callPage(LangPath + "EPV5000_proposal_selection.jsp", req, res);
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


	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);
		
//	 	flexLog("JSEPV5000-->service");

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = A_MAINT_CUSTOMER;

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
                
                flexLog("service-->screen= " +  screen );
                
				switch (screen) {
					// Requests
					case R_ENTER_NEW :					
						procReqEnterNew(mc, msgUser, req, res, session);
						break;
					case R_ENTER_SEARCH_PROP :					
						procReqSearchProposal(mc, msgUser, req, res, session);
						break;						
					case A_ENTER_MAINT :
						procActionEnterMaint(mc, msgUser, req, res, session);
						break;	
					case A_MAINT_CUSTOMER : 
						procActionMaintCustomer(mc, msgUser, req, res, session);
						break;
					case A_MAINT_PROPOSAL : 
						procActionMaintProposal(mc, msgUser, req, res, session);
						break;	
					case R_LIST :					
						procReqList(mc, msgUser, req, res, session);
						break;	
					case R_MAINT_PROP :					
						procReqMaintProp(mc, msgUser, req, res, session);
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