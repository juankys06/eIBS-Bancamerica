package datapro.eibs.client;

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
import datapro.eibs.beans.ESD008701Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSESD0087 extends datapro.eibs.master.SuperServlet {

 
	protected static final int R_ENTER         = 100;
	protected static final int R_ENTER_HELP    = 200;
	protected static final int A_ENTER         = 0001;
	protected static final int A_ENTER_HELP    = 0006;
	protected static final int R_ENTER_MAINT   = 0002;
	protected static final int R_ENTER_MAINT_HELP   = 0003;
	protected String LangPath = "S";

	/**
	 * JSESD0087 constructor comment.
	 */
	public JSESD0087() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0087");

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
protected void procActionEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		String LN3 = null;
		
		// Send Initial data
		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			if (req.getParameter("IDN2") != null &&
			 		!req.getParameter("IDN2").equals("") ){
			  LN3 = req.getParameter("IDN2");
			}
			else{
			  LN3 = req.getParameter("IDN");	
			}
			String LGT = req.getParameter("TYPE");
			String GEC = req.getParameter("COUNTRY");	
			
// flexLog("bnk= *"  + bnk +"*");	
// flexLog("opt_bank= *"  + opt_bank +"*");			
			ses.setAttribute("userPO", userPO);
			
		    procReqEnterMaint(mc, user, req, res, ses);
		    
		} catch (Exception e) {
			flexLog( "About to call Page: " + LangPath + "ESD0087_noclientes_enter.jsp");
			callPage(LangPath + "ESD0087_noclientes_enter.jsp",req,res);
		}
}	


protected void procActionEnterHelp(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		String LN3 = null;
		
		// Send Initial data
		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			if (req.getParameter("IDN2") != null &&
			 		!req.getParameter("IDN2").equals("") ){
			  LN3 = req.getParameter("IDN2");
			}
			else{
			  LN3 = req.getParameter("IDN");	
			}
			String LGT = req.getParameter("TYPE");
			String GEC = req.getParameter("COUNTRY");	
			
// flexLog("bnk= *"  + bnk +"*");	
// flexLog("opt_bank= *"  + opt_bank +"*");			
			ses.setAttribute("userPO", userPO);
			
			procReqEnterMaintHelp(mc, user, req, res, ses);
			
		} catch (Exception e) {
			flexLog( "About to call Page: " + LangPath + "ESD0087_noclientes_enter_help.jsp");
			callPage(LangPath + "ESD0087_noclientes_enter_help.jsp",req,res);
		}
}	

	 
	protected void procReqEnter(
	    MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ESD008701Message msgPayInst = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
// flexLog("JSESD005M-->procReqEnterBank");

		try {
			flexLog("About to call Page: "	+ LangPath	+ "ESD0087_noclientes_enter");
			callPage(LangPath + "ESD0087_noclientes_enter.jsp",req,res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}	
 
	protected void procReqEnterHelp(
		    MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			ESD008701Message msgPayInst = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
//	 flexLog("JSESD005M-->procReqEnterBank");

			try {
				flexLog("About to call Page: "	+ LangPath	+ "ESD0087_noclientes_enter_help");
				callPage(LangPath + "ESD0087_noclientes_enter_help.jsp",req,res);
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
		
// flexLog("JSESD0087-->service");

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_ENTER;

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
				  	 
					case R_ENTER:
					    procReqEnter(mc, msgUser, req, res, session);	
						break;
					case R_ENTER_HELP:
					    procReqEnterHelp(mc, msgUser, req, res, session);	
						break;	
					case R_ENTER_MAINT:
					    procActionMaint(mc, msgUser, req, res, session);
						break;		
					case R_ENTER_MAINT_HELP:
					    procActionMaintHelp(mc, msgUser, req, res, session);
						break;		
					case A_ENTER:
						procActionEnter(mc, msgUser, req, res, session);							
						break;		
					case A_ENTER_HELP:
						procActionEnterHelp(mc, msgUser, req, res, session);							
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
	
	
	protected void procActionMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
		MessageRecord newmessage = null;	 
		ESD008701Message msgcliente = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
// flexLog("JSESD0087-->procActionBankMaint");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0002";
//flexLog("opCode =" + "*" + opCode +"*");		

		// Send Initial data
//flexLog("Send Initial data");
		try {
			
			msgcliente = (ESD008701Message) mc.getMessageRecord("ESD008701");
			    
			msgcliente.setH01USR(user.getH01USR());
			msgcliente.setH01PGM("ESD008701");
			msgcliente.setH01TIM(getTimeStamp());
			msgcliente.setH01SCR("01");
			msgcliente.setH01OPE(opCode);
			// all the fields here
			java.util.Enumeration enu = msgcliente.fieldEnumeration();
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

   			
			mc.sendMessage(msgcliente); 
			msgcliente.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
//flexLog("Receive Error Message");		

		try {
			newmessage = mc.receiveMessage();
//flexLog("ERROR =" + newmessage);
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
//flexLog("Receive NEW Data");			
			newmessage = mc.receiveMessage();
			
		 
			if (newmessage.getFormatName().equals("ESD008701")) 
			{
				msgcliente = (ESD008701Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("client", msgcliente);

				if (IsNotError) { // There are no errors
					try {

						flexLog("About to call Page: " 	+ LangPath + "ESD0087_noclientes_enter.jsp");
						callPage(LangPath + "ESD0087_noclientes_enter.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					try {

						flexLog("About to call Page: " + LangPath + "ESD0087_noclientes_maint.jsp");
						callPage(LangPath + "ESD0087_noclientes_maint.jsp", req, res);

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
	
	protected void procActionMaintHelp(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
				
			MessageRecord newmessage = null;	 
			ESD008701Message msgcliente = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
//	 flexLog("JSESD0087-->procActionBankMaint");
			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
            
			String LN3 = null;
			String opCode = "0002";
//	flexLog("opCode =" + "*" + opCode +"*");		

			// Send Initial data
//	flexLog("Send Initial data");
			try {
				
				msgcliente = (ESD008701Message) mc.getMessageRecord("ESD008701");
				    
				msgcliente.setH01USR(user.getH01USR());
				msgcliente.setH01PGM("ESD008701");
				msgcliente.setH01TIM(getTimeStamp());
				msgcliente.setH01SCR("01");
				msgcliente.setH01OPE(opCode);
				// all the fields here
				java.util.Enumeration enu = msgcliente.fieldEnumeration();
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

	   			
				mc.sendMessage(msgcliente); 
				msgcliente.destroy();
				
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			// Receive Error Message
//	flexLog("Receive Error Message");		

			try {
				newmessage = mc.receiveMessage();
//	flexLog("ERROR =" + newmessage);
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
//	flexLog("Receive NEW Data");			
				newmessage = mc.receiveMessage();
				
			 
				if (newmessage.getFormatName().equals("ESD008701")) 
				{
					msgcliente = (ESD008701Message) newmessage;

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("client", msgcliente);

					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " 	+ LangPath + "ESD0087_noclientes_maint_help.jsp");
							callPage(LangPath + "ESD0087_noclientes_maint_help.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {
							flexLog("About to call Page: " + LangPath + "ESD0087_noclientes_maint_help.jsp");
							callPage(LangPath + "ESD0087_noclientes_maint_help.jsp", req, res);

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

protected void procReqEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
 
		ESD008701Message msgcliente = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
//flexLog("JSESD0087-->procReqEnterMaint");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0001";
//flexLog("opCode =" + "*" + opCode +"*");		

		// Send Initial data
//flexLog("Send Initial data");
		try {
			 
		
		    msgcliente = (ESD008701Message) mc.getMessageRecord("ESD008701");
			
			msgcliente.setH01USR(user.getH01USR());
			msgcliente.setH01PGM("ESD008701");
			msgcliente.setH01TIM(getTimeStamp());
			msgcliente.setH01SCR("01");
			msgcliente.setH01OPE(opCode);
			try {
				if (req.getParameter("IDN2") != null &&
				 		!req.getParameter("IDN2").equals("") ){
					msgcliente.setE01LN3(req.getParameter("IDN2"));
				}
				  
				else{
					msgcliente.setE01LN3(req.getParameter("IDN"));	
				}
				
				msgcliente.setE01PID(req.getParameter("COUNTRY"));
				if(req.getParameter("TYPE").equals("OTHER")){
				 	  msgcliente.setE01LGT("3");
				 	}
				 	else {
				 		if(req.getParameter("TYPE").equals("CORPORATIVE")){
						 	  msgcliente.setE01LGT("1");
						 	}
						 	else {
						 	  msgcliente.setE01LGT("2");
						 	}	
				 	}	
				 		
				
			} catch (Exception e) {
			}
//flexLog("msgParBankNew = *" + msgBankOld.getE02CNTBNK() + "*");			
//flexLog("msgParBankNew =" + msgBankOld);				
			msgcliente.send();
			msgcliente.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
//flexLog("Receive Error Message");		

		try {      
			newmessage = mc.receiveMessage();
//flexLog("ERROR =" + newmessage);
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
//flexLog("Receive NEW Data");			
			newmessage = mc.receiveMessage();
				
		if (newmessage.getFormatName().equals("ESD008701")) 
		{

			   msgcliente = (ESD008701Message) newmessage;
			   flexLog("Putting java beans into the session");
			   ses.setAttribute("error", msgError);
			   ses.setAttribute("client", msgcliente);

			  if (IsNotError) 
			  { // There are no errors
			   		try {
			  			flexLog("About to call Page: " + LangPath + "ESD0087_noclientes_maint.jsp");
			    			callPage(LangPath + "ESD0087_noclientes_maint.jsp",req,res);
						} 
			   	    catch (Exception e) 
			    	        {
			     				flexLog("Exception calling page " + e);
			     			}
			  } 
			  else 
			  { // There are errors
						try {
			     			flexLog( "About to call Page: " + LangPath + "ESD0087_noclientes_enter.jsp");
				  			callPage( LangPath + "ESD0087_noclientes_enter.jsp", req, res);
							} 
						catch (Exception e)
						  {
						   flexLog("Exception calling page " + e);
						  }
			  }
		} 
		else			
					  flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}	
	
protected void procReqEnterMaintHelp(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
 
		ESD008701Message msgcliente = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
//flexLog("JSESD0087-->procReqEnterMaint");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0001";
//flexLog("opCode =" + "*" + opCode +"*");		

		// Send Initial data
//flexLog("Send Initial data");
		try {
			
			 msgcliente = (ESD008701Message) mc.getMessageRecord("ESD008701");
				
				msgcliente.setH01USR(user.getH01USR());
				msgcliente.setH01PGM("ESD008701");
				msgcliente.setH01TIM(getTimeStamp());
				msgcliente.setH01SCR("01");
				msgcliente.setH01OPE(opCode);
				try {
					if (req.getParameter("IDN2") != null &&
					 		!req.getParameter("IDN2").equals("") ){
						msgcliente.setE01LN3(req.getParameter("IDN2"));
					}
					  
					else{
						msgcliente.setE01LN3(req.getParameter("IDN"));	
					}
					
					msgcliente.setE01PID(req.getParameter("COUNTRY"));
					if(req.getParameter("TYPE").equals("OTHER")){
					 	  msgcliente.setE01LGT("3");
					 	}
					 	else {
					 		if(req.getParameter("TYPE").equals("CORPORATIVE")){
							 	  msgcliente.setE01LGT("1");
							 	}
							 	else {
							 	  msgcliente.setE01LGT("2");
							 	}	
					 	}	
					 		
					
				} catch (Exception e) {
				}
					
			msgcliente.send();

			msgcliente.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
//flexLog("Receive Error Message");		

		try {      
			newmessage = mc.receiveMessage();
//flexLog("ERROR =" + newmessage);
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
//flexLog("Receive NEW Data");			
			newmessage = mc.receiveMessage();
				
		if (newmessage.getFormatName().equals("ESD008701")) 
		{

			   msgcliente = (ESD008701Message) newmessage;
			   flexLog("Putting java beans into the session");
			   ses.setAttribute("error", msgError);
			   ses.setAttribute("client", msgcliente);

			  if (IsNotError) 
			  { // There are no errors
			   		try {
			  			flexLog("About to call Page: " + LangPath + "ESD0087_noclientes_maint_help.jsp");
			    			callPage(LangPath + "ESD0087_noclientes_maint_help.jsp",req,res);
						} 
			   	    catch (Exception e) 
			    	        {
			     				flexLog("Exception calling page " + e);
			     			}
			  } 
			  else 
			  { // There are errors
				        
						try {
			     			flexLog( "About to call Page: " + LangPath + "ESD0087_noclientes_maint_help.jsp");
				  			callPage( LangPath + "ESD0087_noclientes_maint_help.jsp", req, res);
							} 
						catch (Exception e)
						  {
						   flexLog("Exception calling page " + e);
						  }
			  }
		} 
		else			
					  flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}	

 
}