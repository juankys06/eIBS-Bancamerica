package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000506Message;
import datapro.eibs.beans.ESD000504Message;
import datapro.eibs.beans.ESD000503Message;

import datapro.eibs.beans.ESD000501Message;
import datapro.eibs.beans.ESD000502Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSESD0005M extends datapro.eibs.master.SuperServlet {

 
	protected static final int R_ENTER_BANK         = 100;
	protected static final int A_ENTER_BANK         = 200;
	protected static final int R_ENTER_MAINT 		= 400;
	protected static final int R_ENTER_MAINT_FIRST 		= 450;
	protected static final int A_ENTER_MAINT_SECOND		= 500;
	protected static final int A_ENTER_NEW_BANK = 300;
	
 
	protected String LangPath = "S";

	/**
	 * JSESD0005M constructor comment.
	 */
	public JSESD0005M() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0005");

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
protected void procActionEnterBank(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
// flexLog("JSESD005M-->procActionEnterBank");
		// Send Initial data
		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			String bnk = req.getParameter("CODBANK"); 			 
			String opt_bank = req.getParameter("TYPBANK");
// flexLog("bnk= *"  + bnk +"*");	
// flexLog("opt_bank= *"  + opt_bank +"*");			
			userPO.setBank(bnk); 
			ses.setAttribute("userPO", userPO);
			
			if (opt_bank.equals("N"))
			   {
			    procReqBankNew(mc, user, req, res, ses);
					 
			   }	
			else 	 
		       {		       
			    if (opt_bank.equals("E")) 
		           {	
		           	  //en bnk va el numero del banco    
		           	  procReqEnterMaint(mc, user, req, res, ses);
	    			 
		           }  
		       }    	 
			

		} catch (Exception e) {
			flexLog( "About to call Page: " + LangPath + "ESD0005_bank_setup_basic_enter.jsp");
			callPage(LangPath + "ESD0005_bank_setup_basic_enter.jsp",	req, res);
		}

	}


	
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterBank(
	    MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ESD000506Message msgPayInst = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
// flexLog("JSESD005M-->procReqEnterBank");

		try {
			flexLog("About to call Page: "	+ LangPath	+ "ESD0005_bank_setup_basic_enter");
			callPage(LangPath + "ESD0005_bank_setup_basic_enter.jsp",req,res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}	
 

	/**
	 * This method was created in VisualAge.
	 */


 
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);
		
// flexLog("JSESD0005M-->service");

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_ENTER_BANK ;

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
                
// flexLog("service-->screen= " +  screen );
                
				switch (screen) {
				  	 
					case R_ENTER_BANK:
					    procReqEnterBank(mc, msgUser, req, res, session);	
						break;
			
			    	case R_ENTER_MAINT :
						procReqEnterMaint(mc, msgUser, req, res, session);
						break;
					case R_ENTER_MAINT_FIRST :
					    procReqBankMaint(mc, msgUser, req, res, session);
						break;						
					case A_ENTER_BANK:
						procActionEnterBank(mc, msgUser, req, res, session);							
						break;	
				    case A_ENTER_NEW_BANK  :
						procActionBankNew(mc, msgUser, req, res, session);
						break;
					case A_ENTER_MAINT_SECOND  :
					    procActionBankMaint(mc, msgUser, req, res, session);
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
	
	
	protected void procReqBankNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
	    ESD000501Message msgBankNew = null;	 
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		//flexLog("JSESD0005M-->procReqBankNew");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		try {
 			userPO.setPurpose("NEW"); 
			msgBankNew = (ESD000501Message) mc.getMessageRecord("ESD000501");			

			msgBankNew.setH01USR(user.getH01USR());
			msgBankNew.setH01PGM("ESD0005");
			msgBankNew.setH01TIM(getTimeStamp());
			msgBankNew.setH01SCR("01");
			msgBankNew.setH01OPE("0010");
			
			msgBankNew.setE01CNTBNK(userPO.getBank());
 
			flexLog("Send Initial data");
			msgBankNew.send();
			msgBankNew.destroy();
			flexLog("ESD000501 Message send");

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		} 		


  		try {
	  		newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
		  		msgError = (ELEERRMessage) newmessage;
		  		IsNotError = msgError.getERRNUM().equals("0");
		  		flexLog("IsNotError = " + IsNotError);

	  		} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
				
			// Receive Data
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ESD000501")) {
					msgBankNew = (ESD000501Message) newmessage;

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);					

					if (IsNotError) { // There are no errors
						ses.setAttribute("bankNew", msgBankNew);
						flexLog("Message " + newmessage.getFormatName() + " received.");
						try {
							flexLog("About to call Page: " 	+ LangPath + "ESD0005_bank_setup_basic_new.jsp");
							callPage(LangPath + "ESD0005_bank_setup_basic_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESD0005_bank_setup_basic_enter.jsp");
							callPage(
								LangPath + "ESD0005_bank_setup_basic_enter.jsp",
								req,
								res);

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
	
	
	protected void procActionBankMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
		MessageRecord newmessage = null;	 
		ESD000502Message msgBankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		ESD000502Message Bank = (ESD000502Message) ses.getAttribute("bankOld");

		String opCode = "0001";

		try {			
			msgBankOld = (ESD000502Message) mc.getMessageRecord("ESD000502");
			    
			// all the fields here
			java.util.Enumeration enu = msgBankOld.fieldEnumeration();
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
					field.setString(Bank.getFieldString(field.getTag()));
				}
			}

			msgBankOld.setH02USR(user.getH01USR());
			msgBankOld.setH02PGM("ESD000501");
			msgBankOld.setH02TIM(getTimeStamp());
			msgBankOld.setH02SCR("01");
			msgBankOld.setH02OPE(opCode);   			
			mc.sendMessage(msgBankOld); 


			msgBankOld.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

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

		// Receive Data
		try {		
			newmessage = mc.receiveMessage();			
		 
			if (newmessage.getFormatName().equals("ESD000502")) 
			{
				//msgBankOld = (ESD000502Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankOld", msgBankOld);

				if (IsNotError) { // There are no errors
					try {

						flexLog("About to call Page: " 	+ LangPath + "ESD0005_bank_setup_basic_enter.jsp");
						callPage(LangPath + "ESD0005_bank_setup_basic_enter.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {

						flexLog("About to call Page: " + LangPath + "ESD0005_bank_setup_basic_maint_add.jsp");
						callPage(LangPath + "ESD0005_bank_setup_basic_maint_add.jsp", req, res);

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} 
			else
			{
				 flexLog("Message " + newmessage.getFormatName() + " received.");
			}	

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}	
	
	
 	
	protected void procActionBankNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
		MessageRecord newmessage = null;
		ESD000501Message msgBankNew = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "0001";

		try {			
			msgBankNew = (ESD000501Message) mc.getMessageRecord("ESD000501");     
			msgBankNew.setH01USR(user.getH01USR());
			msgBankNew.setH01PGM("ESD0005");
			msgBankNew.setH01TIM(getTimeStamp());
			msgBankNew.setH01SCR("01");
			msgBankNew.setH01OPE(opCode);
			// all the fields here
			java.util.Enumeration enu = msgBankNew.fieldEnumeration();
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
			
			mc.sendMessage(msgBankNew); 

			msgBankNew.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

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

		// Receive Data
		try {	
			newmessage = mc.receiveMessage();		
		 
			if (newmessage.getFormatName().equals("ESD000501")) 
			{
				msgBankNew = (ESD000501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bankNew", msgBankNew);

				if (IsNotError) { // There are no errors
					
					ESD000502Message msgBankOld = null;
					try {
						msgBankOld = (ESD000502Message) mc.getMessageRecord("ESD000502");
			
						msgBankOld.setH02USR(user.getH01USR());
						msgBankOld.setH02PGM("ESD000502");
						msgBankOld.setH02TIM(getTimeStamp());
						msgBankOld.setH02SCR("01");
						msgBankOld.setH02OPE("0002");
						try {
							msgBankOld.setE02CNTBNK(userPO.getBank()); 
						} catch (Exception e) {
						}
			
						msgBankOld.send();

						msgBankOld.destroy();
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
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

					// Receive Data
					try {
						newmessage = mc.receiveMessage();
					
						if (newmessage.getFormatName().equals("ESD000502")) {
							
							msgBankOld = (ESD000502Message) newmessage;
							//Is missing I don't know why
							msgBankOld.setE02CNTANG(msgBankNew.getE01CNTANG());
							ses.setAttribute("bankOld", msgBankOld);
							
							procReqBankMaint(mc, user, req, res, ses);
							
						} else
							flexLog("Message " + newmessage.getFormatName() + " received.");
							
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
					
				} else { // There are errors
					try {

						flexLog("About to call Page: " + LangPath + "ESD0005_bank_setup_basic_new.jsp");
						callPage(LangPath + "ESD0005_bank_setup_basic_new.jsp", req, res);

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} 
			else
			{
				 flexLog("Message " + newmessage.getFormatName() + " received.");
			}	

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}	


	protected void procReqBankMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000502Message msgBankOld = null;
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
		ESD000502Message Bank = (ESD000502Message) ses.getAttribute("bankOld");

		// Send Initial data
		try {
			msgBankOld = (ESD000502Message) mc.getMessageRecord("ESD000502");
			
			// all the fields here
			java.util.Enumeration enu = msgBankOld.fieldEnumeration();
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
					field.setString(Bank.getFieldString(field.getTag()));
				}
			}

			msgBankOld.setH02USR(user.getH01USR());
			msgBankOld.setH02PGM("ESD0005");
			msgBankOld.setH02SCR("01");			
			msgBankOld.setH02TIM(getTimeStamp());
			///ojod con el opecod
			msgBankOld.setH02OPE("0002");

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
			ses.setAttribute("bankOld", msgBankOld);
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ESD0005_bank_setup_basic_maint_add.jsp");
				callPage(
					LangPath + "ESD0005_bank_setup_basic_maint_add.jsp",
					req,
					res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}



	protected void procReqEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
 
		ESD000502Message msgBankOld = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
		    msgBankOld = (ESD000502Message) mc.getMessageRecord("ESD000502");
			
			msgBankOld.setH02USR(user.getH01USR());
			msgBankOld.setH02PGM("ESD000502");
			msgBankOld.setH02TIM(getTimeStamp());
			msgBankOld.setH02SCR("01");
			msgBankOld.setH02OPE("0002");
			try {
				msgBankOld.setE02CNTBNK(userPO.getBank()); 
			} catch (Exception e) {
			}
			
			msgBankOld.send();

			msgBankOld.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	

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

		// Receive Data
		try {
			newmessage = mc.receiveMessage();
					
		if (newmessage.getFormatName().equals("ESD000502")) {

			msgBankOld = (ESD000502Message) newmessage;

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("bankOld", msgBankOld);

			if (IsNotError) { // There are no errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ESD0005_bank_setup_basic_maint.jsp");
					callPage(
						LangPath + "ESD0005_bank_setup_basic_maint.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ESD0005_bank_setup_basic_enter.jsp");
					callPage(
						LangPath + "ESD0005_bank_setup_basic_enter.jsp",
						req,
						res);
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
	

 
}