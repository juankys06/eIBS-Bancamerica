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

public class JSEDL0110L extends datapro.eibs.master.SuperServlet {


	static final int R_LIST  			= 1;
	static final int A_LIST   			= 2;
	static final int R_I_LIST 			= 3;
	
	static final int A_MAINT  			= 200;
	
	static final int R_ENTER_NEW  		= 1000;
	
	static final int R_NEW 	 			= 100;
	static final int R_MAINT  			= 300;
	static final int R_DELETE 			= 500;
	static final int R_INQUIRY 			= 700;
	static final int R_MAINT_APPROVAL  	= 900;
	
	private String LangPath = "S";

	/**
	 * JSEDL0110L constructor comment.
	 */
	public JSEDL0110L() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0110L");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	
	
        private synchronized void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDL011002Message msgList = null;
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
		
//        flexLog("JSEDL0110L-->procReqList");
 
		// Send Initial data
		try {
			msgList = (EDL011002Message) mc.getMessageRecord("EDL011002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("EDL011002");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02SCRCOD("01");
			msgList.setH02OPECOD("0015");
			
			msgList.send();
			msgList.destroy();
			flexLog("EDL011002 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error
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
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
        
        
        //Receive Data
		try {
			  newmessage = mc.receiveMessage();
			  
//			  flexLog("Receive Data");
//            flexLog("newmessage= " +  newmessage );
								
				if (newmessage.getFormatName().equals("EDL011002")) {

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
					String typeRt= "";
					String typeLD= "";
					int compar = 0;
					int indexRow = 0;
					while (true) {
                      
						msgList = (EDL011002Message) newmessage;

                        
                                            
						marker = msgList.getE02PRPOPE();
						
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
							
							typeRt= msgList.getE02PRRTSL();
//							flexLog("typeRt = *" +  typeRt + "*"); 
							if ( typeRt.equals("1") )
							      typeLD="Usar la mas Alta";
							else if  ( typeRt.equals("2") )
							      typeLD="Usar la mas Baja";
							      
							      else 
							         typeLD="Por definir";				
							
							
							
						//	myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><input type=\"radio\" name=\"ACCNUM\" value=\""
      						myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><input type=\"radio\" name=\"TBL\" value=\""
								+ msgList.getE02PRRTBL()
								+ "\" "
								+ chk
							+ " onclick=\"getParams('"
							+ msgList.getE02PRRTBL()   
							+ "','" 
							+ msgList.getE02PRRNME()
							+ "','" 
							+ msgList.getE02PRRTFR() 
							+ "','" 
							+ msgList.getE02PRRTTO()
							+ "','" 	
							+ msgList.getE02PRRTSL()
							+ "','" 													
							+ msgList.getE02PRRPRT()
							+ "','"
							+ msgList.getE02PRRSRT()
							+ "','"
							+ msgList.getE02PRREFM()
							+ "','"
							+ msgList.getE02PRREFD()
							+ "','"
							+ msgList.getE02PRREFY() 
							+ "')\"></TD>");
							//table
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\">" + msgList.getE02PRRTBL() + "</TD>");
							//description
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"30%\">" + msgList.getE02PRRNME() + "</TD>");
							//initial range
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"10%\">" + msgList.getE02PRRTFR() + "</td>");							
							//final range							
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"10%\">" + msgList.getE02PRRTTO() + "</td>");
							
							//type
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"10%\">" + typeLD + "</td>");
					        typeRt= "";
					        typeLD= "";							
					        
							// primary rate 
							myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"30%\">" + msgList.getE02PRRPRT() + "</td>");							 	
						    // secondary rate 
						    myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"30%\">" + msgList.getE02PRRSRT() + "</td>");
							//  date
                            myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"20%\">" + Util.formatDate(msgList.getE02PRREFM(), msgList.getE02PRREFD(), msgList.getE02PRREFY())  + "</td>");		
							myRow.append("</TR>");
												
//							flexLog("myRow= " +  myRow );				
												
														
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
					ses.setAttribute("EDL0110LHelp", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "EDL0110L_rt_sel_tables.jsp");
						callPage(LangPath + "EDL0110L_rt_sel_tables.jsp", req, res);
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
	
	
 

	private synchronized void procActionPos(
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
//		flexLog("procActionPos-->inptOPT= " + inptOPT );
		
		//New
		 
		String DESCRIPTION = req.getParameter("DESCRIPTION");
		String TABLE = req.getParameter("TABLE");		 
 
		//Maintenance
		String TABLEN = req.getParameter("TABLEN");	
    	String DSC = req.getParameter("DSC");
    	
    	String TFR = req.getParameter("TRF");
    	String TTO = req.getParameter("TTO");
   		String TSL = req.getParameter("TSL");

		String RT_PRY    = req.getParameter("RT_PRY");
        String RT_SEC = req.getParameter("RT_SEC"); 
        
        String PRY_MONTH = req.getParameter("PRY_MONTH");
        String PRY_DAY   = req.getParameter("PRY_DAY");
        String PRY_YEAR  = req.getParameter("PRY_YEAR"); 
        

/*		
    document.forms[0].RT_PRY.value = rtPry1;
	document.forms[0].PRY_MONTH.value = pryMonth1;
	document.forms[0].PRY_DAY.value = pryDay1;
	document.forms[0].PRY_YEAR.value = pryYear1;
	
	document.forms[0].RT_SEC.value = rtSec1;
	document.forms[0].SEC_MONTH.value = secMonth1;
	document.forms[0].SEC_DAY.value = secDay1;
	document.forms[0].SEC_YEAR.value = secYear1;
		
		flexLog("TABLE== *" + req.getParameter("TABLE") + "*");
        flexLog("DESCRIPTION== *" + req.getParameter("DESCRIPTION") + "*");
        flexLog("TABLEN== *" + req.getParameter("TABLEN") + "*");
        flexLog("DSC== *" + req.getParameter("DSC") + "*");
        flexLog("TSL== *" + req.getParameter("TSL") + "*");
*/
		switch (inptOPT) {
			case 1 : //New
//			    flexLog("procActionPos-->screen=100");
				res.sendRedirect(super.srctx +                                                    
					"/servlet/datapro.eibs.params.JSEDL0110L?SCREEN=100"  + "&TABLE=" + TABLE + "&DESCRIPTION=" + DESCRIPTION);		    
				break;
			case 2 : //Maintenance
//			    flexLog("procActionPos-->screen=300");
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.params.JSEDL0110L?SCREEN=300" + "&TABLEN=" + TABLEN  + "&DSC=" + DSC + 
					"&TFR=" + TFR +  "&TTO=" + TTO +  "&TSL=" + TSL +
					"&RT_PRY=" + RT_PRY  + "&RT_SEC=" + RT_SEC +
					"&PRY_MONTH=" + PRY_MONTH + "&PRY_DAY=" + PRY_DAY + "&PRY_YEAR=" + PRY_YEAR);
				break;
				
			case 3 : //Delete
//			    flexLog("procActionPos-->screen=500");
			    res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.params.JSEDL0110L?SCREEN=500" + "&TABLEN=" + TABLEN + "&TSL=" + TSL);
				break;
				
			case 4 : //Inquiry
//			    flexLog("procActionPos-->screen=700");
			    res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.params.JSEDL0110L?SCREEN=700" + "&TABLEN=" + TABLEN + "&TSL=" + TSL );
				break;
			
			default :
//			flexLog("procActionPos-->(CASE-->DEFAULT)screen=100");
			res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.params.JSEDL0110L?SCREEN=100"  + "&TABLE=" + TABLE + "&DESCRIPTION=" + DESCRIPTION
				+  "&TSL=" + TSL );		    
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
		EDL011002Message msgRT = null;
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

//        flexLog("JSEDL0110L-->procActionMaint");
       
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDL011002Message) ses.getAttribute("rates");
			msgRT.setH02USERID(user.getH01USR());
			msgRT.setH02PROGRM("EDL011002");
			msgRT.setH02TIMSYS(getTimeStamp());
			msgRT.setH02SCRCOD("01");
			msgRT.setH02OPECOD("0005");

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
			flexLog("EDL011002 Message Sent");
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

			if (newmessage.getFormatName().equals("EDL011002")) {
				try {
					msgRT = new datapro.eibs.beans.EDL011002Message();
					flexLog("EDL011002 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDL011002Message) newmessage;
				
 
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rates", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors  
						try {

							res.sendRedirect(super.srctx + 
												"/servlet/datapro.eibs.params.JSEDL0110L?SCREEN=1000" );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
							}
				

				} 
				else 
				{ // There are errors
					 source= req.getParameter("PAG_SCR");
					 flexLog("source= *" + req.getParameter("PAG_SCR") + "*");
					 
					 
					 if ( source.equals("NEW") )
					 {
					
	 
									try {
										flexLog("About to call Page: " + LangPath + "EDL0110L_rt_tables.jsp");
										callPage(LangPath + "EDL0110L_rt_tables.jsp", req, res);
									} catch (Exception e) {
										flexLog("Exception calling page " + e);
									}
									
					 }
					 else				
                     {
					
	 
									try {
										flexLog("About to call Page: " + LangPath + "EDL0110L_rt_tables_maint.jsp");
										callPage(LangPath + "EDL0110L_rt_tables_maint.jsp", req, res);
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
     
//        flexLog("JSEDL0110L-->procReqEnterNew");
     
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

					try {
						flexLog("About to call Page: " + LangPath + "EDL0110L_rt_sel_tables.jsp");
						callPage(LangPath + "EDL0110L_rt_sel_tables.jsp", req, res);
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
		EDL011002Message msgDoc = null;
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
		
//		flexLog("JSEDL0110L-->procReqNew");

		// Send Initial data
		try {
			flexLog("document sent");
			msgDoc = (EDL011002Message) mc.getMessageRecord("EDL011002");
			
 		
			
			try{
				msgDoc.setE02PRRTBL(req.getParameter("TABLE"));
			} catch (Exception e) {
			}	
 
			try{
				msgDoc.setE02PRRNME(req.getParameter("DESCRIPTION"));
			} catch (Exception e) {
			}	
			
//			flexLog("TABLE=msgDoc.setE02PRRTBL= *" + req.getParameter("TABLE") + "*");
//          flexLog("DESCRIPTION=msgDoc.setE02PRRNME= *" + req.getParameter("DESCRIPTION") + "*");

			flexLog("document assigned");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
				ses.setAttribute("rates", msgDoc);
			
	 
			    try {
				    	flexLog("About to call Page: " + LangPath + "EDL0110L_rt_tables.jsp");
						callPage(LangPath + "EDL0110L_rt_tables.jsp", req, res);
					} 
					catch (Exception e) 
					  {
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
		EDL011002Message msgDoc = null;
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
			msgDoc = (EDL011002Message) mc.getMessageRecord("EDL011002");
			msgDoc.setH02USERID(user.getH01USR());
			msgDoc.setH02PROGRM("EDL011002");
			msgDoc.setH02TIMSYS(getTimeStamp());
			msgDoc.setH02SCRCOD("01");
			msgDoc.setH02OPECOD("0002");
			
			
			
			try{
				msgDoc.setE02PRRTBL(req.getParameter("TABLEN"));
			} catch (Exception e) {
			}	
			
			try{
				msgDoc.setE02PRRNME(req.getParameter("DSC"));
			} catch (Exception e) {
			}
			
			
			
			
			try{
				msgDoc.setE02PRRTFR(req.getParameter("TFR"));
			} catch (Exception e) {
			}
			
						try{
				msgDoc.setE02PRRTTO(req.getParameter("TTO"));
			} catch (Exception e) {
			}
				
			
			
			try{
				msgDoc.setE02PRRTSL(req.getParameter("TSL"));
			} catch (Exception e) {
			}

			try{
				msgDoc.setE02PRRPRT(req.getParameter("RT_PRY"));
			} catch (Exception e) {
			}
			
			try{
				msgDoc.setE02PRRSRT(req.getParameter("RT_SEC"));
			} catch (Exception e) {
			}			
			
			try{
				msgDoc.setE02PRREFM(req.getParameter("PRY_MONTH"));
			} catch (Exception e) {
			}

			try{
				msgDoc.setE02PRREFD(req.getParameter("PRY_DAY"));
			} catch (Exception e) {
			}											
			
			try{
				msgDoc.setE02PRREFY(req.getParameter("PRY_YEAR"));
			} catch (Exception e) {
			}
			
/*			
 			flexLog("req.getParameter<TABLEN>= *" + req.getParameter("TABLEN") + "*");
 			flexLog("req.getParameter<DSC>= *" + req.getParameter("DSC") + "*");
 			flexLog("req.getParameter<TSL>= *" + req.getParameter("TSL") + "*");
 			flexLog("req.getParameter<TFR>= *" + req.getParameter("TFR") + "*");
            flexLog("req.getParameter<PRY_YEAR>= *" + req.getParameter("PRY_DAY") + "*"); 
 			flexLog("req.getParameter<PRY_YEAR>= *" + req.getParameter("PRY_MONTH") + "*");
 			flexLog("req.getParameter<PRY_YEAR>= *" + req.getParameter("PRY_YEAR") + "*");
 			
 			
            flexLog("pprocReqMaint-->msgDoc= " +  msgDoc );
*/
 		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

 

		// Receive Data
		try {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("rates", msgDoc);
						try {
										flexLog("About to call Page: " + LangPath + "EDL0110L_rt_tables_maint.jsp");
										callPage(LangPath + "EDL0110L_rt_tables_maint.jsp", req, res);
					 		 }
					 	catch (Exception e) 
					 		 {
										flexLog("Exception calling page " + e);
						     }

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqMaintApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL011002Message msgDoc = null;
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
			msgDoc = (EDL011002Message) mc.getMessageRecord("EDL011002");
			msgDoc.setH02USERID(user.getH01USR());
			msgDoc.setH02PROGRM("EDL011002");
			msgDoc.setH02TIMSYS(getTimeStamp());
			msgDoc.setH02SCRCOD("01");
			msgDoc.setH02OPECOD("0002");
			
			
			
			try{
				msgDoc.setE02PRRTBL(req.getParameter("TABLEN"));
			} catch (Exception e) {
			}	
			
			try{
				msgDoc.setE02PRRNME(req.getParameter("DSC"));
			} catch (Exception e) {
			}
			
			
			
			
			try{
				msgDoc.setE02PRRTFR(req.getParameter("TFR"));
			} catch (Exception e) {
			}
			
						try{
				msgDoc.setE02PRRTTO(req.getParameter("TTO"));
			} catch (Exception e) {
			}
				
			
			
			try{
				msgDoc.setE02PRRTSL(req.getParameter("TSL"));
			} catch (Exception e) {
			}
	
			try{
				msgDoc.setE02PRRPRT(req.getParameter("RT_PRY"));
			} catch (Exception e) {
			}
			
			try{
				msgDoc.setE02PRRSRT(req.getParameter("RT_SEC"));
			} catch (Exception e) {
			}			
			
			try{
				msgDoc.setE02PRREFM(req.getParameter("PRY_MONTH"));
			} catch (Exception e) {
			}
	
			try{
				msgDoc.setE02PRREFD(req.getParameter("PRY_DAY"));
			} catch (Exception e) {
			}											
			
			try{
				msgDoc.setE02PRREFY(req.getParameter("PRY_YEAR"));
			} catch (Exception e) {
			}
			
			
/*			flexLog("req.getParameter<TABLEN>= *" + req.getParameter("TABLEN") + "*");
			flexLog("req.getParameter<DSC>= *" + req.getParameter("DSC") + "*");
			flexLog("req.getParameter<TSL>= *" + req.getParameter("TSL") + "*");
			flexLog("req.getParameter<TFR>= *" + req.getParameter("TFR") + "*");
	        flexLog("req.getParameter<PRY_YEAR>= *" + req.getParameter("PRY_DAY") + "*"); 
			flexLog("req.getParameter<PRY_YEAR>= *" + req.getParameter("PRY_MONTH") + "*");
			flexLog("req.getParameter<PRY_YEAR>= *" + req.getParameter("PRY_YEAR") + "*");
			
	        flexLog("pprocReqMaint-->msgDoc= " +  msgDoc );
*/	
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	
	
		// Receive Data
		try {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("rates", msgDoc);
						try {
										flexLog("About to call Page: " + LangPath + "EDL0110L_ap_rt_tables_inquiry.jsp");
										callPage(LangPath + "EDL0110L_ap_rt_tables_inquiry.jsp", req, res);
					 		 }
					 	catch (Exception e) 
					 		 {
										flexLog("Exception calling page " + e);
						     }
	
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
		
//	 	flexLog("JSEDL0110L-->service");

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
                
                flexLog("service-->screen= " +  screen );
                
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
					case R_MAINT_APPROVAL :
						procReqMaintApproval(mc, msgUser, req, res, session);
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