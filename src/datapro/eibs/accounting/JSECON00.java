package datapro.eibs.accounting;

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

public class JSECON00 extends datapro.eibs.master.SuperServlet {


	static final int R_LIST  			= 1;
	static final int A_LIST   			= 2;

	static final int A_NEW  			= 200;	
	static final int A_MAINT  			= 400;
	static final int A_DELETE  			= 600;
	
	static final int R_NEW 	 			= 100;
	static final int R_MAINT  			= 300;
	static final int R_DELETE 			= 500;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSECON00() {
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
		ECON00001Message msgList = null;
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
			msgList = (ECON00001Message) mc.getMessageRecord("ECON00001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECON00001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			flexLog("Send Initial data");
//           
            
			msgList.send();
			msgList.destroy();
			flexLog("ECON00001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error
		try {
			newmessage = mc.receiveMessage();
			
			
//			flexLog("Receive Error");
//            flexLog("newmessage= " +  newmessage );
			

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
//              flexLog("newmessage= " +  newmessage );
								
				if (newmessage.getFormatName().equals("ECON00001")) {

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
					String typeFL= "";
					int compar = 0;
					int indexRow = 0;
					while (true) {

						msgList = (ECON00001Message) newmessage;


						marker = msgList.getE01CONOPE();
						
						if (firstTime) {
							firstTime = false;
							
							}
					    else {
						  	chk = "";
						}						
					
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							
							myRow = new StringBuffer("<TR>");
							
      						myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"5%\"><input type=\"radio\" name=\"GLNUM\" value=\""
								+ msgList.getE01CONGLN()
								+ "\" "
								+ chk
							+ " onclick=\"getParams('"
							+ msgList.getE01CONBNK()   
							+ "','" 
							+ msgList.getE01CONCCY()
							+ "','" 
							+ msgList.getE01CONGLN()
							+ "','" 
							+ msgList.getE01CONEBK()
							+ "','" 
							+ msgList.getE01CONECY()
							+ "','" 
							+ msgList.getE01CONEGL()							
							+ "')\"></TD>");
							//bank
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01CONBNK() + "</td>");
							//currency
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01CONCCY() + "</td>");
							//GL Number
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\">" + msgList.getE01CONGLN() + "</td>");
							//bank to merge
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01CONEBK() + "</td>");
							//currency to merge
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01CONECY() + "</td>");
							//gl to merge
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\">" + msgList.getE01CONEGL() + "</td>");
							//sequence
							myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"15%\">" + msgList.getE01CONSEQ() + "</td>");
							
							myRow.append("</TR>");
																								
														
							beanList.addRow(myFlag, myRow.toString());
							indexRow++;
							
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								
								break;
							}
						}
						newmessage = mc.receiveMessage();

//						flexLog("procReqList-->while-->newmessage= " +  newmessage );
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("ECON0001Help", beanList);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECON00_gl_list.jsp");
						callPage(LangPath + "ECON00_gl_list.jsp", req, res);
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
	 
 
		//Maintenance
		String BNK1 = req.getParameter("BNK1");
	    String CCY1 = req.getParameter("CCY1");
		String GLN1 = req.getParameter("GLN1");
		String BNK2 = req.getParameter("BNK2");
		String CCY2 = req.getParameter("CCY2");
		String GLN2 = req.getParameter("GLN2");
 

		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(super.srctx +                                                    
					"/servlet/datapro.eibs.accounting.JSECON00?SCREEN=100");		    
				break;
				
			case 2 : //Maintenance
			    res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.accounting.JSECON00?SCREEN=300" + "&BNK1=" + BNK1 + "&CCY1=" + CCY1
					 + "&GLN1=" + GLN1 + "&BNK2=" + BNK2 + "&CCY2=" + CCY2 + "&GLN2=" + GLN2);
				break;
				
			case 3 : //Delete
			    res.sendRedirect(super.srctx + 
				"/servlet/datapro.eibs.accounting.JSECON00?SCREEN=500" + "&BNK1=" + BNK1 + "&CCY1=" + CCY1
			+ "&GLN1=" + GLN1 + "&BNK2=" + BNK2 + "&CCY2=" + CCY2 + "&GLN2=" + GLN2);
				break;
				
			
			default :
			res.sendRedirect(super.srctx +                                                    
					"/servlet/datapro.eibs.accounting.JSECON00?SCREEN=100");		    
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
		ECON00001Message msgRT = null;
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

//        flexLog("JSEDL0110F-->procActionMaint");
       
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (ECON00001Message) ses.getAttribute("gl");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECON00001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			msgRT.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
                    flexLog("field.getTag= *" + field.getTag() +"* == *" + value +"*");
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("ECON00001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();
			
//			flexLog("Receive Error Message");
//			flexLog("newmessage= " + "*" + newmessage +"*");

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
			
			if (newmessage.getFormatName().equals("ECON00001")) {
				try {
					msgRT = new datapro.eibs.beans.ECON00001Message();
					flexLog("ECON00001 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (ECON00001Message) newmessage;
				
 
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("gl", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors  
						try {

							res.sendRedirect(super.srctx + 
												"/servlet/datapro.eibs.accounting.JSECON00?SCREEN=1" );
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
							}
				

				} 
				else 
				{ // There are errors
					 					
	 
					try {
						flexLog("About to call Page: " + LangPath + "ECON00_gl_details_maint.jsp");
						callPage(LangPath + "ECON00_gl_details_maint.jsp", req, res);
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

		protected void procActionNew(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			MessageRecord newmessage = null;
			ECON00001Message msgRT = null;
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
	
	//        flexLog("JSEDL0110F-->procActionMaint");
	       
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgRT = (ECON00001Message) ses.getAttribute("gl");
				msgRT.setH01USERID(user.getH01USR());
				msgRT.setH01PROGRM("ECON00001");
				msgRT.setH01TIMSYS(getTimeStamp());
				msgRT.setH01SCRCOD("01");
				msgRT.setH01OPECOD("0005");
	
				// all the fields here
				java.util.Enumeration enu = msgRT.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while (enu.hasMoreElements()) {
					field = (MessageField) enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase();
	                    flexLog("field.getTag= *" + field.getTag() +"* == *" + value +"*");
						if (value != null) {
							field.setString(value);
						}
					} catch (Exception e) {
					}
				}
	
				mc.sendMessage(msgRT);
				msgRT.destroy();
				flexLog("ECON00001 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
	
			// Receive Error Message
			try {
				newmessage = mc.receiveMessage();
				
	//			flexLog("Receive Error Message");
	//			flexLog("newmessage= " + "*" + newmessage +"*");
	
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
				
				if (newmessage.getFormatName().equals("ECON00001")) {
					try {
						msgRT = new datapro.eibs.beans.ECON00001Message();
						flexLog("ECON00001 Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}
	
					msgRT = (ECON00001Message) newmessage;
					
	 
					
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("gl", msgRT);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors  
							try {
	
								res.sendRedirect(super.srctx + 
													"/servlet/datapro.eibs.accounting.JSECON00?SCREEN=1" );
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
								}
					
	
					} 
					else 
					{ // There are errors
						 					
		 
						try {
							flexLog("About to call Page: " + LangPath + "ECON00_gl_details_new.jsp");
							callPage(LangPath + "ECON00_gl_details_new.jsp", req, res);
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

			protected void procActionDelete(
				MessageContext mc,
				ESS0030DSMessage user,
				HttpServletRequest req,
				HttpServletResponse res,
				HttpSession ses)
				throws ServletException, IOException {
		
				MessageRecord newmessage = null;
				ECON00001Message msgRT = null;
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
		
		//        flexLog("JSEDL0110F-->procActionMaint");
		       
				// Send Initial data
				try {
					flexLog("Send Initial Data");
					msgRT = (ECON00001Message) ses.getAttribute("gl");
					msgRT.setH01USERID(user.getH01USR());
					msgRT.setH01PROGRM("ECON00001");
					msgRT.setH01TIMSYS(getTimeStamp());
					msgRT.setH01SCRCOD("01");
					msgRT.setH01OPECOD("0004");
		
					// all the fields here
					java.util.Enumeration enu = msgRT.fieldEnumeration();
					MessageField field = null;
					String value = null;
					while (enu.hasMoreElements()) {
						field = (MessageField) enu.nextElement();
						try {
							value = req.getParameter(field.getTag()).toUpperCase();
		                    flexLog("field.getTag= *" + field.getTag() +"* == *" + value +"*");
							if (value != null) {
								field.setString(value);
							}
						} catch (Exception e) {
						}
					}
		
					mc.sendMessage(msgRT);
					msgRT.destroy();
					flexLog("ECON00001 Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
		
				// Receive Error Message
				try {
					newmessage = mc.receiveMessage();
					
		//			flexLog("Receive Error Message");
		//			flexLog("newmessage= " + "*" + newmessage +"*");
		
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
					
					if (newmessage.getFormatName().equals("ECON00001")) {
						try {
							msgRT = new datapro.eibs.beans.ECON00001Message();
							flexLog("ECON00001 Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}
		
						msgRT = (ECON00001Message) newmessage;
						
		
						
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("gl", msgRT);
						ses.setAttribute("userPO", userPO);
		
						if (IsNotError) { // There are no errors  
								try {
		
									res.sendRedirect(super.srctx + 
														"/servlet/datapro.eibs.accounting.JSECON00?SCREEN=1" );
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
									}
						
		
						} 
						else 
						{ // There are errors
							 					
			 
							try {
								flexLog("About to call Page: " + LangPath + "ECON00_gl_details_delete.jsp");
								callPage(LangPath + "ECON00_gl_details_delete.jsp", req, res);
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

	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECON00001Message msgDoc = null;
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
		

		// Send Initial data
		try {
			
			msgDoc = (ECON00001Message) mc.getMessageRecord("ECON00001");
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
				ses.setAttribute("gl", msgDoc);
			
	 
			    try {
				    	flexLog("About to call Page: " + LangPath + "ECON00_gl_details_new.jsp");
						callPage(LangPath + "ECON00_gl_details_new.jsp", req, res);
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
		ECON00001Message msgDoc = null;
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
		
	
		// Send Initial data
		try {
			
			msgDoc = (ECON00001Message) mc.getMessageRecord("ECON00001");
			
			try{
				msgDoc.setE01CONBNK(req.getParameter("BNK1"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			try{
				msgDoc.setE01CONCCY(req.getParameter("CCY1"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
						}
			try{				
				msgDoc.setE01CONGLN(req.getParameter("GLN1"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
	
			try{
				msgDoc.setE01CONEBK(req.getParameter("BNK2"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			try{
				msgDoc.setE01CONECY(req.getParameter("CCY2"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
						}
			try{				
				msgDoc.setE01CONEGL(req.getParameter("GLN2"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
				ses.setAttribute("gl", msgDoc);
			
	
			    try {
				    	flexLog("About to call Page: " + LangPath + "ECON00_gl_details_maint.jsp");
						callPage(LangPath + "ECON00_gl_details_maint.jsp", req, res);
					} 
					catch (Exception e) 
					  {
			          		flexLog("Exception calling page " + e);
					  }
			 
	
	}

	protected void procReqDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECON00001Message msgDoc = null;
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
		
	
		// Send Initial data
		try {
			
			msgDoc = (ECON00001Message) mc.getMessageRecord("ECON00001");
			
			try{
				msgDoc.setE01CONBNK(req.getParameter("BNK1"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			try{
				msgDoc.setE01CONCCY(req.getParameter("CCY1"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
						}
			try{				
				msgDoc.setE01CONGLN(req.getParameter("GLN1"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			try{
				msgDoc.setE01CONEBK(req.getParameter("BNK2"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			try{
				msgDoc.setE01CONECY(req.getParameter("CCY2"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
						}
			try{				
				msgDoc.setE01CONEGL(req.getParameter("GLN2"));
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
				ses.setAttribute("gl", msgDoc);
			
	
			    try {
				    	flexLog("About to call Page: " + LangPath + "ECON00_gl_details_delete.jsp");
						callPage(LangPath + "ECON00_gl_details_delete.jsp", req, res);
					} 
					catch (Exception e) 
					  {
			          		flexLog("Exception calling page " + e);
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
                
//             flexLog("service-->screen= " +  screen );
                
				switch (screen) {
					// Requests
				 	case R_LIST :				 	    
						procReqList(mc, msgUser, req, res, session);
						break;   
					case R_NEW :					    
						procReqNew(mc, msgUser, req, res, session);
						break;						
					case R_MAINT :					    
						procReqMaint(mc, msgUser, req, res, session);
						break;	
					case R_DELETE :					    
						procReqDelete(mc, msgUser, req, res, session);
						break;	
						
						// Actions
					case A_LIST :					   
						procActionPos(mc, msgUser, req, res, session);
						break;
					case A_MAINT :					    
						procActionMaint(mc, msgUser, req, res, session);
						break;
					case A_NEW :					    
						procActionNew(mc, msgUser, req, res, session);
						break;
					case A_DELETE :					    
						procActionDelete(mc, msgUser, req, res, session);
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