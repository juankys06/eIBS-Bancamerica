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

public class JSESD0205 extends datapro.eibs.master.SuperServlet {

	
	static final int R_LIST  	= 1;
	static final int A_LIST   	= 2;
	static final int R_I_LIST 	= 3;
	
	static final int A_MAINT  	= 200;
	
	static final int R_ENTER_NEW  	= 1000;
	
	static final int R_NEW 	 	= 100;
	static final int R_MAINT  	= 300;
	static final int R_DELETE 	= 500;
	static final int R_INQUIRY 	= 700;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESD0205() {
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
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD020501Message msgList = null;
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
			msgList = (ESD020501Message) mc.getMessageRecord("ESD020501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESD020501");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			try { 
				msgList.setE01RTEBNK(req.getParameter("E01RTEBNK").toUpperCase());
				
			} catch (Exception e) {
				
			}
			try { 
				msgList.setE01RTEACD(req.getParameter("E01RTEACD").toUpperCase());
				
			} catch (Exception e) {
				
			}
			try { 
				msgList.setE01RTEATY(req.getParameter("E01RTEATY").toUpperCase());
				
			} catch (Exception e) {
				
			}
			try { 
				msgList.setE01RTECUN(req.getParameter("E01RTECUN").toUpperCase());
				
			} catch (Exception e) {
				
			}

			msgList.send();
			msgList.destroy();
			flexLog("ESD020501 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = new ELEERRMessage();
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

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
								
				if (newmessage.getFormatName().equals("ESD020501")) {

					beanList = new JBList();

					boolean firstTime =true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					String chkOfac = "";
					String chkWarn = "";
					int compar = 0;
					int indexRow = 0;
					while (true) {

						msgList = (ESD020501Message) newmessage;

						marker = msgList.getE01CDROPE();
						
						if (firstTime) {
							firstTime = false;
							chk = "checked";
							//Set initial values for bank, application and table 
							userPO.setHeader1(msgList.getE01RTEBNK());
							userPO.setHeader2(msgList.getE01RTEATY());
							userPO.setHeader3(msgList.getE01RTETAR());
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
								+ msgList.getE01RTETAR()
								+ "\" "
								+ chk
							+ " onclick=\"getParams('"
							+ msgList.getE01RTEBNK() 
							+ "','" 
							+ msgList.getE01RTEATY()
							+ "','" 
							+ msgList.getE01RTETAR() 
							+ "','" 
							+ msgList.getE01RTECUN()
							+ "')\"></TD>");
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01RTETAR() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"40%\">" + msgList.getE01RTEDSC() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"15%\">" + msgList.getE01RTEATY() + "</td>");
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"10%\">" + msgList.getE01RTECCY() + "</td>");
							//myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"15%\">" + msgList.getE01RTESTS() + "</td>");
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
					ses.setAttribute("userPO", userPO);

					if (IsNotError) { // There are no errors
						ses.setAttribute("ESD0205Help", beanList);
						try {
							flexLog("About to call Page: " + LangPath + "ESD0205_rt_sel_tables.jsp");
							callPage(LangPath + "ESD0205_rt_sel_tables.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}				
					} else {
						ses.setAttribute("ESD0205msg", msgList);
						try {
							flexLog("About to call Page: " + LangPath + "ESD0205_rt_enter_sel_tables.jsp");
							callPage(LangPath + "ESD0205_rt_enter_sel_tables.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
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
		//New
		String ACCTYPE = req.getParameter("ACCTYPE");
		String DESCRIPTION = req.getParameter("DESCRIPTION");
		String TABLE = req.getParameter("TABLE");		
		String CUSTOMER = req.getParameter("CUSTOMER");
		String PRODUCT = req.getParameter("PRODUCT");
		//Maintenance
		String TABLEN = req.getParameter("TABLEN");
		String BANK = req.getParameter("BANK");
		String ACD = req.getParameter("ACD");
		String CUSTOMERNUMBER = req.getParameter("CUSTOMERNUMBER");

		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.params.JSESD0205?SCREEN=100"  + "&TABLE=" + TABLE + "&DESCRIPTION=" + DESCRIPTION
					+ "&ACCTYPE=" + ACCTYPE + "&CUSTOMER=" + CUSTOMER +  "&PRODUCT=" + PRODUCT);		    
				break;
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.params.JSESD0205?SCREEN=300" + "&TABLEN=" + TABLEN + "&BANK=" + BANK + "&ACD=" + ACD+ "&CUSTOMERNUMBER=" + CUSTOMERNUMBER);
				break;
				
			case 3 : //Delete
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.params.JSESD0205?SCREEN=500" + "&TABLEN=" + TABLEN + "&BANK=" + BANK + "&ACD=" + ACD);
				break;
				
			case 4 : //Inquiry
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.params.JSESD0205?SCREEN=700" + "&TABLEN=" + TABLEN + "&BANK=" + BANK + "&ACD=" + ACD);
				break;
			
			default :
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.params.JSESD0205?SCREEN=100"  + "&TABLE=" + TABLE + "&DESCRIPTION=" + DESCRIPTION
				+ "&ACCTYPE=" + ACCTYPE + "&CUSTOMER=" + CUSTOMER +  "&PRODUCT=" + PRODUCT);		    
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
		ESD020502Message msgRT = null;
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

		String opCode = "";
		if (userPO.getPurpose().equals("NEW")) {
			opCode = "0001";
		} else {
			opCode = "0005";
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (ESD020502Message) ses.getAttribute("charges");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("ESD020502");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			msgRT.setH02OPECOD(opCode);

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

			try{
				String actype = msgRT.getE02RTEACD();
				if (actype.length()>1) {
					if (actype.equals("01")) {
						acctype = 1;
					}
					if (actype.equals("02") || actype.equals("03")) {
						acctype = 2;
					}
					if (actype.equals("04")) {
						acctype = 4;
					}
					if (actype.equals("05")) {
						acctype = 5;
					}
				} else {
					acctype = Integer.parseInt(req.getParameter("ACCTYPE"));
				}
			} catch (Exception e) {
				acctype = 1;
			}

			msgRT.destroy();
			flexLog("ESD020502 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD020502")) {
				try {
					msgRT = new datapro.eibs.beans.ESD020502Message();
					flexLog("ESD020502 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ESD020502Message) newmessage;
				
				try{
					acctype = Integer.parseInt(msgRT.getE02RTEACD());
				} catch (Exception ex) {
				 flexLog("Error: " + ex);
				}
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("charges", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
						try {

							res.sendRedirect(super.srctx + 
												"/servlet/datapro.eibs.params.JSESD0205?SCREEN=1000" );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
							}
				

				} else { // There are errors
					switch (acctype) {
						 case 1 :
								try {
									flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_01.jsp");
									callPage(LangPath + "ESD0205_rt_tables_dda_01.jsp", req, res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
								break;
						case 2 :
						case 3 :
								try {
									flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_02.jsp");
									callPage(LangPath + "ESD0205_rt_tables_dda_02.jsp", req, res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
								break;
						case 4 :
							   try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_04.jsp");
								   callPage(LangPath + "ESD0205_rt_tables_dda_04.jsp", req, res);
							   } catch (Exception e) {
								   flexLog("Exception calling page " + e);
							   }
							   break;
						case 5 :
							   try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_05.jsp");
								   callPage(LangPath + "ESD0205_rt_tables_dda_05.jsp", req, res);
							   } catch (Exception e) {
								   flexLog("Exception calling page " + e);
							   }
							   break;
						default :
							   try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_01.jsp");
								   callPage(LangPath + "ESD0205_rt_tables_dda_01.jsp", req, res);
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
						flexLog("About to call Page: " + LangPath + "ESD0205_rt_enter_sel_tables.jsp");
						callPage(LangPath + "ESD0205_rt_enter_sel_tables.jsp", req, res);
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
		userPO.setPurpose("NEW");
		ses.setAttribute("userPO", userPO);

		// Send Initial data
		try {
			flexLog("document sent");
			msgDoc = (ESD020502Message) mc.getMessageRecord("ESD020502");
			
			try{
				String actype = req.getParameter("ACCTYPE");
				if (actype.length()>1) {
					if (actype.equals("01")) {
						acctype = 1;
					}
					if (actype.equals("02") || actype.equals("03")) {
						acctype = 2;
					}
					if (actype.equals("04")) {
						acctype = 4;
					}
					if (actype.equals("05")) {
						acctype = 5;
					}
				} else {
					acctype = Integer.parseInt(req.getParameter("ACCTYPE"));
				}
			} catch (Exception e) {
				acctype = 1;
			}
			
			try{
				msgDoc.setE02RTETAR(req.getParameter("TABLE"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE02RTEBNK(user.getE01UBK());
			} catch (Exception e) {
			}	


			try{
				msgDoc.setE02RTECUN(req.getParameter("CUSTOMER"));
			} catch (Exception e) {
			}	

			try{
				if (req.getParameter("PRODUCT").equals("null")) {
					msgDoc.setE02RTEATY(req.getParameter("ACCTYPE"));
				} else {
					msgDoc.setE02RTEATY(req.getParameter("PRODUCT"));
				}
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE02RTEDSC(req.getParameter("DESCRIPTION"));
			} catch (Exception e) {
			}	


			flexLog("document assigned");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
				ses.setAttribute("charges", msgDoc);
			
			switch (acctype) {
				case 1 :
						try {
							flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_01.jsp");
							callPage(LangPath + "ESD0205_rt_tables_dda_01.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
						break;
				case 2 :
						try {
							flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_02.jsp");
							callPage(LangPath + "ESD0205_rt_tables_dda_02.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
						break;
				case 4 :
					   try {
						   flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_04.jsp");
						   callPage(LangPath + "ESD0205_rt_tables_dda_04.jsp", req, res);
					   } catch (Exception e) {
						   flexLog("Exception calling page " + e);
					   }
					   break;
				case 5 :
					   try {
						   flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_05.jsp");
						   callPage(LangPath + "ESD0205_rt_tables_dda_05.jsp", req, res);
					   } catch (Exception e) {
						   flexLog("Exception calling page " + e);
					   }
					   break;
				default :
					   try {
						   flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_01.jsp");
						   callPage(LangPath + "ESD0205_rt_tables_dda_01.jsp", req, res);
					   } catch (Exception e) {
						   flexLog("Exception calling page " + e);
					   }  
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
		userPO.setPurpose("MAINTENANCE");

		// Send Initial data
		try {
			msgDoc = (ESD020502Message) mc.getMessageRecord("ESD020502");
			msgDoc.setH02USERID(user.getH01USR());
			msgDoc.setH02PROGRM("EDI010102");
			msgDoc.setH02TIMSYS(getTimeStamp());
			msgDoc.setH02SCRCOD("01");
			msgDoc.setH02OPECOD("0002");
			
			try{
				msgDoc.setE02RTETAR(req.getParameter("TABLEN"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE02RTEBNK(req.getParameter("BANK"));
			} catch (Exception e) {
			}	

			try{
				msgDoc.setE02RTEATY(req.getParameter("ACD"));
			} catch (Exception e) {
			}	
			
			try{
				msgDoc.setE02RTECUN(req.getParameter("CUSTOMERNUMBER"));
			} catch (Exception e) {
			}	

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

		// Receive Data
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
									flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_01.jsp");
									callPage(LangPath + "ESD0205_rt_tables_dda_01.jsp", req, res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
								break;
						case 2 :
								try {
									flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_02.jsp");
									callPage(LangPath + "ESD0205_rt_tables_dda_02.jsp", req, res);
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
						case 4 :
							   try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_04.jsp");
								   callPage(LangPath + "ESD0205_rt_tables_dda_04.jsp", req, res);
							   } catch (Exception e) {
								   flexLog("Exception calling page " + e);
							   }
							   break;
						case 5 :
							   try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_05.jsp");
								   callPage(LangPath + "ESD0205_rt_tables_dda_05.jsp", req, res);
							   } catch (Exception e) {
								   flexLog("Exception calling page " + e);
							   }
							   break;
						default :
							   try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_tables_dda_01.jsp");
								   callPage(LangPath + "ESD0205_rt_tables_dda_01.jsp", req, res);
							   } catch (Exception e) {
								   flexLog("Exception calling page " + e);
							   }	  
					}
				} else { // There are errors
					try {
								   flexLog("About to call Page: " + LangPath + "ESD0205_rt_sel_tables.jsp");
								   callPage(LangPath + "ESD0205_rt_sel_tables.jsp", req, res);
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
					case A_LIST :
						procActionPos(mc, msgUser, req, res, session);
						break;
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