package datapro.eibs.forex;

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
import datapro.eibs.beans.UserPos;

 
import datapro.eibs.sockets.*;

public class JSEFE0160 extends datapro.eibs.master.SuperServlet {

	// Foreign Exchange 


	protected static final int A_SPOT				= 2;
	protected static final int A_FAST_SPOT		= 10;
	protected static final int A_OPTION			= 12;
	protected static final int A_FORWARD			= 14;
	protected static final int A_SWAP				= 16;
	protected static final int A_NDF				= 18;	
	protected static final int A_MAINT_DEAL       = 40;

	protected static final int A_SPOT_MOD			= 20;
	protected static final int A_FORWARD_MOD		= 22;
	protected static final int A_OPTION_MOD		= 24;
	protected static final int A_NDF_MOD			= 26;
	protected static final int A_SWAP_MOD			= 28;
	
	protected static final int R_SPOT				= 1;
	protected static final int R_FORWARD			= 3;
	protected static final int R_NDF				= 5;
	protected static final int R_OPTION			= 7;
	protected static final int R_SWAP				= 9;
	protected static final int R_MAINT_DEAL		= 100;
	
	protected static final int R_S_SPOT			= 31;
	protected static final int R_S_FORWARD		= 33;
	protected static final int R_S_NDF			= 35;	
	protected static final int R_S_OPT			= 37;
	protected static final int R_S_SWAP			= 39;
	
	protected static final int A_S_SPOT			= 32;
	protected static final int A_S_FORWARD		= 34;
	protected static final int A_S_NDF			= 36;	
	protected static final int A_S_OPT			= 38;
	protected static final int A_S_SWAP			= 42;
	
	protected static final int R_SC_SPOT			= 41;
	protected static final int R_SC_FORWARD		= 43;
	protected static final int R_SC_NDF			= 45;	
	protected static final int R_SC_OPT			= 47;
	protected static final int R_SC_SWAP			= 49;
	
	protected static final int R_SPECIAL_INST 	= 55;
	protected static final int A_SPECIAL_INST 	= 56;


	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEFE0160() {
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

protected void procActionMaintDeal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	EFE0160DSMessage msgFex = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = new ELEERRMessage();
	  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	//userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
	  try{
	   userPO = new UserPos();
	  }
	  catch (Exception e)	{
	  }
	String opCode = null;
	
	// Send Initial data
	try
	{
		msgFex = (EFE0160DSMessage)mc.getMessageRecord("EFE0160DS");
	 	msgFex.setH01USERID(user.getH01USR());
	 	msgFex.setH01PROGRM("EFE0160");
	 	msgFex.setH01TIMSYS(getTimeStamp());
	 	msgFex.setH01SCRCOD("01");
	 	msgFex.setH01OPECOD("0003");

		try {
		 	msgFex.setE01FEMACC(req.getParameter("E01FEMACC"));
		}
		catch (Exception e)	{
	 	  msgFex.setE01FEMACC("0");
		}

	 	msgFex.send();
	 	msgFex.destroy();
	 	flexLog("EFE0160DS Message Sent");
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
	  }
	  else
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

		if (newmessage.getFormatName().equals("EFE0160DS")) {
			try {
				msgFex = new EFE0160DSMessage();
		  	} catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}

			msgFex = (EFE0160DSMessage)newmessage;
			userPO.setIdentifier(msgFex.getE01FEMACC());
			userPO.setCusNum(msgFex.getE01FEMCUN());
			userPO.setBank(msgFex.getE01FEMBNK());
			userPO.setHeader1(msgFex.getE01FEMAM1());
			userPO.setCusName(msgFex.getD01FEMCP1());
			userPO.setProdCode(msgFex.getE01FEMCLS());
			userPO.setCurrency(msgFex.getE01FEMCCY());
			userPO.setHeader2("2");	
			
			if(msgFex.getE01FEMTYP().equals("OPTI")){
			     userPO.setHeader3("OPTI");				
				}
				else if(msgFex.getE01FEMTYP().equals("SPOT")){
			     userPO.setHeader3("SPOT"); 				
				}
				else if(msgFex.getE01FEMTYP().equals("FWRD")){
				 userPO.setHeader3("FWRD");				
				}
				else if(msgFex.getE01FEMTYP().equals("SWAP")){
				 userPO.setHeader3("SWAP");				
				}
				else if(msgFex.getE01FEMTYP().equals("NDF")){
				 userPO.setHeader3("NDF");				
				}				


			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("fex", msgFex);
			ses.setAttribute("userPO", userPO);
			
 
			if (IsNotError) {  // There are no errors
				if(msgFex.getE01FEMTYP().equals("OPTI")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_opt.jsp");
						callPage(LangPath + "EFE0160_fe_basic_opt.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01FEMTYP().equals("SPOT")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_sf.jsp");
						callPage(LangPath + "EFE0160_fe_basic_sf.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01FEMTYP().equals("FWRD")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_forward.jsp");
						callPage(LangPath + "EFE0160_fe_basic_forward.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01FEMTYP().equals("SWAP")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_swap.jsp");
						callPage(LangPath + "EFE0160_fe_basic_swap.jsp", req, res);	
					}
					catch (Exception e) {
		 				flexLog("Exception calling page " + e);
					}
				}
				else if(msgFex.getE01FEMTYP().equals("NDF")){				
					try {
						flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_ndf.jsp");
						callPage(LangPath + "EFE0160_fe_basic_ndf.jsp", req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}				
			}
			else {  // There are errors
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.forex.JSEWD0333?ACCOUNT=0");		
			}
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");
		}
	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
}




	protected void procReqSpotMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_sf.jsp");
			callPage(LangPath + "EFE0160_fe_basic_sf.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procReqForwardMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");



		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_forward.jsp");
			callPage(LangPath + "EFE0160_fe_basic_forward.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	protected void procReqNDMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");



		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_ndf.jsp");
			callPage(LangPath + "EFE0160_fe_basic_ndf.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	

	protected void procReqSwapMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");



		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_swap.jsp");
			callPage(LangPath + "EFE0160_fe_basic_swap.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	
	protected void procReqOptionMod(ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");



		try {

			msgError = new ELEERRMessage();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_opt.jsp");
			callPage(LangPath + "EFE0160_fe_basic_opt.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	

	protected void procReqEspInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000005Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0002";

		// Send Initial data
		try {
			msgCD = (ESD000005Message) mc.getMessageRecord("ESD000005");
			msgCD.setH05USR(user.getH01USR());
			msgCD.setH05PGM("EDL0130");
			msgCD.setH05TIM(getTimeStamp());
			msgCD.setH05SCR("01");
			msgCD.setH05OPE(opCode);
			msgCD.setE05ACC(userPO.getIdentifier());
			msgCD.setE05TYP("2");
			msgCD.setH05WK1("M");
			msgCD.send();
			msgCD.destroy();
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

			if (newmessage.getFormatName().equals("ESD000005")) {
				try {
					msgCD = new ESD000005Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000005Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdInst", msgCD);

				if (IsNotError) { // There are no errors 
					try {
						flexLog("About to call Page: " + LangPath + "EFE0160_fe_special_inst.jsp");
						callPage(LangPath + "EFE0160_fe_special_inst.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					if(userPO.getHeader3().equals("SPOT") ){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_sf.jsp");
							callPage(LangPath + "EFE0160_fe_basic_sf.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("FWRD") ){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_forward.jsp");
							callPage(LangPath + "EFE0160_fe_basic_forward.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("NDF")){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_ndf.jsp");
							callPage(LangPath + "EFE0160_fe_basic_ndf.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("SWAP") ){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_swap.jsp");
							callPage(LangPath + "EFE0160_fe_basic_swap.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
					    }
					}
					else if(userPO.getHeader3().equals("OPTI")){
						try {
							flexLog("About to call Page: " + LangPath + "EFE0160_fe_basic_opt.jsp");
							callPage(LangPath + "EFE0160_fe_basic_opt.jsp", req, res);
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

		int screen = A_SPOT;
		
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
				// BEGIN Fex
				// Action
				case A_MAINT_DEAL :
					procActionMaintDeal(mc, msgUser, req, res, session);
					break;
					
					
				case R_SPOT :
				     procReqSpotMod(msgUser, req, res, session);
					 break;
				case R_FORWARD :
				     procReqForwardMod(msgUser, req, res, session);
					 break;
				case R_NDF :
				     procReqNDMod(msgUser, req, res, session);
					 break;
				case R_OPTION :
				     procReqOptionMod(msgUser, req, res, session);
					 break;
				case R_SWAP :
				     procReqSwapMod(msgUser, req, res, session);
					 break;										
				case R_SPECIAL_INST :
					procReqEspInst(mc, msgUser, req, res, session);
					break;									
									
				// END Entering

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
				return;
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