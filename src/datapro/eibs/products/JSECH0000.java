package datapro.eibs.products;

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

public class JSECH0000 extends datapro.eibs.master.SuperServlet {

	// RETAIL ACCOUNTS
	static final int A_RT_BASIC				= 29;
	static final int A_RT_SOLICITUD			= 30;
	static final int A_RT_APROBACION			= 31;
	static final int A_RT_RECEPCION			= 32;

	static final int R_RT_ENTER_MAINT 		= 300;
	
	static final int A_RT_ENTER_MAINT			= 400;
	static final int A_RT_ENTER_SOLICITUD		= 401;
	static final int A_RT_ENTER_APROBACION	= 402;
	static final int C_RT_RECEP             = 403;
	
	static final int C_RT_ENTER_MAINT 		= 801;
	
	

	

	private String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSECH0000() {
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
 * Rescata datos de Chequera
 * 
 */
private void  procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

/////////
// FVO //
/////////
	MessageRecord newmessage = null;
	ECH037501Message msgRT = null;

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		msgRT = (ECH037501Message)mc.getMessageRecord("ECH037501");
		
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("ECH0305");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
//	 	msgRT.setH01FLGWK1("R");
	 	msgRT.setH01OPECOD("0001");


		try {
 	  		msgRT.setE01CHPACC(req.getParameter("E01ACMACC"));
		}
		catch (Exception e)	{
	 	  	msgRT.setE01CHPACC("0");
		}

		msgRT.send();	
	 	msgRT.destroy();
	 	flexLog("ECHO0000 Message Sent");
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
			//showERROR(msgError);
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

		if (newmessage.getFormatName().equals("ECH037501")) 
		{
			try 
			{
				msgRT = (datapro.eibs.beans.ECH037501Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ECH037501Message");
				flexLog("ECH037501 Message Received");
		  	} 
			catch (Exception ex) 
			{
				flexLog("Error: " + ex); 
		  	}

			msgRT = (ECH037501Message)newmessage;
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("rtBasic", msgRT);

			if (IsNotError)
			{  // There are no errors
				try 
				{
					flexLog("About to call Page: " + LangPath + "ECH0000_rt_basic.jsp");
					callPage(LangPath + "ECH0000_rt_basic.jsp", req, res);	
				}
				catch (Exception e) 
				{
					flexLog("Exception calling page " + e);
				}
			}
	  	  else
			{
 			  flexLog("About to call Page: " + LangPath + "ECH0000_rt_basic.jsp");
			  callPage(LangPath + "ECH0000_rt_enter_maint.jsp", req, res);	
			}
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


}
/**
 * This method was created in VisualAge.
 * Actualiza Datos de Chequera
 * 
 */
private void procActionRTBasic(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


///fvo

	MessageRecord newmessage = null;
	ECH037501Message msgRT = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try {
		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try
	{
		flexLog("Send Initial Data");
		
		
		msgRT = (ECH037501Message)mc.getMessageRecord("ECH037501");
		
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("ECH0305");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
	 	msgRT.setH01OPECOD("0005");

		// all the fields here
	 	java.util.Enumeration enu = msgRT.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			}
			catch (Exception e) {
			}	
		}

	 	//msgRT.send();
	 	mc.sendMessage(msgRT);
	 	msgRT.destroy();
	 	flexLog("JSECH0000 Message Sent");
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
			//showERROR(msgError);
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

	    if (newmessage.getFormatName().equals("ECH037501")) 
	    {

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);
			try 
			{
				if (IsNotError)
					{  // There are no errors
					try 
					{
					flexLog("About to call Page: " + LangPath + "ECH0000_rt_confirm.jsp");
					callPage(LangPath + "ECH0000_rt_confirm.jsp", req, res);
					}
					catch (Exception e) 
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{
	 			  flexLog("About to call Page: " + LangPath + "ECH0000_rt_basic.jsp");
				  callPage(LangPath + "ECH0000_rt_basic.jsp", req, res);	
				}
				
			}
			catch (Exception e) 
			{
				flexLog("Exception calling page " + e);
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

private void  procReqRTEnterMaint(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
		userPO = (datapro.eibs.beans.UserPos) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.UserPos");
		userPO.setOption("RT");
		userPO.setPurpose("MAINTENANCE");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ECH0000_rt_enter_solicitud_ch.jsp");
		callPage(LangPath + "ECH0000_rt_enter_solicitud_ch.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


private void  procActionEnterSolicitud(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECH030501Message msgRT = null;

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
		msgRT = (ECH030501Message)mc.getMessageRecord("ECH030501");
		
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("ECH0305");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
	 	msgRT.setH01OPECOD("0001");
		try {
 	  		msgRT.setE01CHPACC(req.getParameter("E01ACMACC"));
		}
		catch (Exception e)	{
	 	  	msgRT.setE01CHPACC("0");
		}
		msgRT.send();	
	 	msgRT.destroy();
	 	flexLog("ECHO0000 Message Sent");
	}catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receive Error Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR"))
	  	{
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			//showERROR(msgError);
	  	}

	  	if (IsNotError)
	  	{
		    newmessage = mc.receiveMessage();
   			flexLog("Message " + newmessage.getFormatName() + " received.");
			if (newmessage.getFormatName().equals("ECH030501")) 
			{
				try 
				{
					msgRT = (datapro.eibs.beans.ECH030501Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ECH030501Message");
					flexLog("ECH030501 Message Received");
			  	} catch (Exception ex){
					flexLog("Error: " + ex);}
	
				msgRT = (ECH030501Message)newmessage;
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("rtBasic", msgRT);
				
				//2° conexion a socket	
	
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
						EDD000001Message msgRT1 = null;
						ELEERRMessage msgError1 = null;
	
	
						boolean IsNotError1 = false;
		
						try{
							msgError =
								(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
									getClass().getClassLoader(),
									"datapro.eibs.beans.ELEERRMessage");
						}catch (Exception ex){
							flexLog("Error: " + ex);
						}
				
				
						// Send Initial data
						try 
						{
							msgRT1 = (EDD000001Message) mc1.getMessageRecord("EDD000001");
							msgRT1.setH01USERID(user.getH01USR());
							msgRT1.setH01PROGRM("EDD0000");
							msgRT1.setH01TIMSYS(getTimeStamp());
							msgRT1.setH01SCRCOD("01");
							msgRT1.setH01FLGWK1("R");
							msgRT1.setH01OPECOD("0002");
							try 
							{
								msgRT1.setE01ACMACC(req.getParameter("E01ACMACC"));
							} 
							catch (Exception e) 
							{
								msgRT1.setE01ACMACC("0");
							}
							msgRT1.send();
							msgRT1.destroy();
							flexLog("EDD000001 Message Sent");
						}catch (Exception e){
							e.printStackTrace();
							flexLog("Error: " + e);
							throw new RuntimeException("Socket Communication Error");
						}
	
	
						// Receive Error Message
						try{
							newmessage1 = mc1.receiveMessage();
				
							if (newmessage1.getFormatName().equals("ELEERR")) 
							{
								msgError1 = (ELEERRMessage) newmessage1;
								IsNotError = msgError1.getERRNUM().equals("0");
								flexLog("IsNotError = " + IsNotError1);
								//showERROR(msgError1);
							} 
							else
								flexLog("Message " + newmessage1.getFormatName() + " received.");
						}catch (Exception e){
							e.printStackTrace();
							flexLog("Error: " + e);
							throw new RuntimeException("Socket Communication Error");
						}
	
						// Receive Data
						try{
							newmessage1 = mc1.receiveMessage();
			
							if (newmessage1.getFormatName().equals("EDD000001")) 
							{
								try 
								{
									msgRT1 =
										(datapro.eibs.beans.EDD000001Message) Beans.instantiate(
											getClass().getClassLoader(),
											"datapro.eibs.beans.EDD000001Message");
									flexLog("EDD000001 Message Received");
								} 
								catch (Exception ex) 
								{
									flexLog("Error: " + ex);
								}
			
								msgRT1 = (EDD000001Message) newmessage1;
			
								flexLog("Putting java beans into the session");
								ses.setAttribute("error", msgError);
			
								if (IsNotError) 
								{ 
									ses.setAttribute("rtBasic_1", msgRT1);
								}
							}
						 }catch (Exception e){
							e.printStackTrace();
							flexLog("Error: " + e);
							throw new RuntimeException("Socket Communication Error");
						}
						//Fin Recepion de datos socket 2
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
			}
	    }	
		if (IsNotError)
		{  // There are no errors
			try 
			{
				flexLog("About to call Page: " + LangPath + "ECH0000_rt_basic.jsp");
				callPage(LangPath + "ECH0000_rt_Solicitud.jsp", req, res);	
			}
			catch (Exception e) 
			{
				flexLog("Exception calling page " + e);
			}
		}
		else
		{
 		  ses.setAttribute("error", msgError);
 		  flexLog("About to call Page: " + LangPath + "ECH0000_rt_enter_solicitud_ch.jsp");
		  callPage(LangPath + "ECH0000_rt_enter_solicitud_ch.jsp", req, res);	
		}
	}
	catch (Exception e)	
	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	


}




private void procActionRTSolicitud(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECH030501Message msgRT = null;
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

	try
	{
		flexLog("Send Initial Data");
		
		
		msgRT = (ECH030501Message)mc.getMessageRecord("ECH030501");
		
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("ECH0305");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
	 	msgRT.setH01OPECOD("0005");

		// all the fields here
	 	java.util.Enumeration enu = msgRT.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField)enu.nextElement();
			try {
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			}
			catch (Exception e) {
			}	
		}

	 	mc.sendMessage(msgRT);
	 	msgRT.destroy();
	 	flexLog("JSECH0000 Message Sent");
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
	  
	  if (newmessage.getFormatName().equals("ELEERR")) 
	  {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			//showERROR(msgError);
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

	    if (newmessage.getFormatName().equals("ECH030501")) 
	    {

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtBasic", msgRT);
			try {
					if (IsNotError)
					{  
					    try 
						{
							flexLog("About to call Page: " + LangPath + "ECH0000_rt_confirm.jsp");
							callPage(LangPath + "ECH0000_rt_confirm.jsp", req, res);
						}
						catch (Exception e) 
						{
							flexLog("Exception calling page " + e);
						}
					}
					else
					{
 					  flexLog("About to call Page: " + LangPath + "ECH0000_rt_Solicitud.jsp");
			 		  callPage(LangPath + "ECH0000_rt_Solicitud.jsp", req, res);	
					}

				}
			catch (Exception e) 
			{
				flexLog("Exception calling page " + e);
			}
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


}



private void  procActionEnterAprobacion(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECH053501Message msgRT = null;
	
	JBList beanList = null;

	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;
	
	
	try 
	{
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) 
	{
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	// Send Initial data
	try
	{
		msgRT = (ECH053501Message)mc.getMessageRecord("ECH053501");
		
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("ECH0535");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
	 	msgRT.setH01OPECOD("0001");
		msgRT.send();	
	 	msgRT.destroy();
	 	flexLog("ECHO0000 Message Sent");
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
			//showERROR(msgError);
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

		if (newmessage.getFormatName().equals("ECH053501")) 
		{
			try 
			{
				beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
				flexLog("ECH053501 Message Received");
		  	} 
			catch (Exception ex) 
			{
				flexLog("Error: " + ex); 
		  	}

			String marker = "";
			String myFlag = "";
			String myRow = "";
			int indexRow = 0;

			while (true) 
			{
					msgRT = (ECH053501Message)newmessage;

					//marker = msgHelp.getEWDOPE();
					marker = msgRT.getE01OPECDE();

					if (marker.equals("*")) 
					{
						beanList.setShowNext(false);
						break;
					}
					else 
					{
						myRow =  "<TR>";
						myRow += "<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + indexRow + "\" " +  " onclick=\"showAddInfo("+indexRow+")\"></TD>";
						myRow +="<td nowrap>" + msgRT.getE01CHAACC()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHANA1()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHATCH()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHANCK()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHACCY()+"</td>";
						myRow +="<td nowrap>" +  Util.formatDate(msgRT.getE01CHADT1(),msgRT.getE01CHADT2(), msgRT.getE01CHADT3()) +"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHATIM()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHADSC()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHADBR()+"</td>";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=BNK" + indexRow +  " value=" + msgRT.getE01CHABNK() +">";
					 	myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=BRN" + indexRow +  " value=" + msgRT.getE01CHABRN() +">";
					 	myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=CCY" + indexRow +  " value=" + msgRT.getE01CHACCY() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=ACC" + indexRow +  " value=" + msgRT.getE01CHAACC() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=TIM" + indexRow +  " value=" + msgRT.getE01CHATIM() +">";
						
						
												
						myRow += "</TR>";
						
						beanList.addRow(myFlag, myRow);
						indexRow ++;	
										
						if (marker.equals("+")) 
						{
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
						
			flexLog("Putting java beans into the session");
			ses.setAttribute("rtBasic", beanList);
			
			if (IsNotError)
			{  // There are no errors
				try 
				{
					flexLog("About to call Page: " + LangPath + "ECH0000_rt_aprobacion.jsp");
					callPage(LangPath + "ECH0000_rt_aprobacion.jsp", req, res);	
				}
				catch (Exception e) 
				{
					flexLog("Exception calling page " + e);
				}
			}
			else
			{
 			  flexLog("About to call Page: " + LangPath + "ECH0000_rt_basic.jsp");
			  callPage(LangPath + "ECH0000_rt_enter_maint.jsp", req, res);	
			}
			
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


}


private void procActionRTAprobacion(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECH053501Message msgRT = null;
	ELEERRMessage msgError = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	try 
	{
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} 
	catch (Exception ex) 
	{
		flexLog("Error: " + ex); 
  	}

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	try
	{
		flexLog("Send Initial Data");
		
		
		msgRT = (ECH053501Message)mc.getMessageRecord("ECH053501");
		
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("ECH0535");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
	 	msgRT.setH01OPECOD("0005");

		msgRT.setE01CHABNK(req.getParameter("E01CHABNK"));
		msgRT.setE01CHABRN(req.getParameter("E01CHABRN"));
		msgRT.setE01CHACCY(req.getParameter("E01CHACCY"));
		msgRT.setE01CHAACC(req.getParameter("E01CHAACC"));
		msgRT.setE01CHATIM(req.getParameter("E01CHATIM"));
		msgRT.setE01CHAOCT(req.getParameter("E01CHAOCT"));

	 	mc.sendMessage(msgRT);
	 	msgRT.destroy();
	 	flexLog("JSECH0000 Message Sent");
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
	  
	  if (newmessage.getFormatName().equals("ELEERR")) 
	  {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			//showERROR(msgError);
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

	    if (newmessage.getFormatName().equals("ECH053501")) 
	    {

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtBasic", msgRT);
			try {
					if (IsNotError)
					{  
					    try 
						{
							flexLog("About to call Page: " + LangPath + "ECH0000_rt_confirm.jsp");
							callPage(LangPath + "ECH0000_rt_confirm.jsp", req, res);
						}
						catch (Exception e) 
						{
							flexLog("Exception calling page " + e);
						}
					}
					else
					{
 					  flexLog("About to call Page: " + LangPath + "ECH0000_rt_Aprobacion.jsp");
			 		  callPage(LangPath + "ECH0000_rt_Aprobacion.jsp", req, res);	
					}

				}
			catch (Exception e) 
			{
				flexLog("Exception calling page " + e);
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


private void  procReqRecepcionChequera(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {


	ELEERRMessage msgError = null;
	UserPos	userPO = null;	

	try {

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = (datapro.eibs.beans.UserPos) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.UserPos");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
  	} catch (Exception ex) { 
		flexLog("Error: " + ex); 
  	}

	try {
		flexLog("About to call Page: " + LangPath + "ECH03101_st_RecepcionChequera.jsp");
		callPage(LangPath + "ECH03101_st_RecepcionChequera.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}


private void  procActionEnterMaintCH(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

/////////
//CMV 11/SEPT/2002
/////////
	MessageRecord newmessage = null;
	ECH031001Message msgRT = null;

	JBList beanList = null;
	
	JBList beanListParam = null;

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
		msgRT = (ECH031001Message)mc.getMessageRecord("ECH031001");
		
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("ECH0310");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
//	 	msgRT.setH01FLGWK1("R");
	 	msgRT.setH01OPECOD("0001");


		try {
 	  		msgRT.setE01SELBNK(req.getParameter("E01SELBNK"));
			msgRT.setE01SELBRN(req.getParameter("E01SELBRN"));
            msgRT.setE01SELFD1(req.getParameter("E01SELFD1"));
            msgRT.setE01SELFD2(req.getParameter("E01SELFD2"));
            msgRT.setE01SELFD3(req.getParameter("E01SELFD3"));
            msgRT.setE01SELID1(req.getParameter("E01SELID1"));
            msgRT.setE01SELID2(req.getParameter("E01SELID2"));
            msgRT.setE01SELID3(req.getParameter("E01SELID3"));
		}
		catch (Exception e)	{
	 	  	
//          msgRT.setE01SELBNK("0");
// 		    msgRT.setE01SELBRN("0");
            msgRT.setE01SELFD1("null");
            msgRT.setE01SELFD2("null");
            msgRT.setE01SELFD3("null");
            msgRT.setE01SELID1("null");
            msgRT.setE01SELID2("null");
            msgRT.setE01SELID3("null");


		}

		msgRT.send();	
	 	msgRT.destroy();
	 	flexLog("ECHO3100 Message Sent");

				
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
			//showERROR(msgError);
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

		if (newmessage.getFormatName().equals("ECH031001")) 
		{
			try 
			{
				//msgRT = (datapro.eibs.beans.ECH031001Message) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ECH031001Message");
				beanList = (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
				beanListParam= (datapro.eibs.beans.JBList) Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.JBList");
				flexLog("ECH031001 Message Received");
		  	} 
			catch (Exception ex) 
			{
				flexLog("Error: " + ex); 
		  	}

			String marker = "";
			String myFlag = "";
			String myRow = "";
			int indexRow = 0;

			while (true) 
			{
					msgRT = (ECH031001Message)newmessage;

					//marker = msgHelp.getEWDOPE();
					marker = msgRT.getE01OPECDE();

					if (marker.equals("*")) 
					{
						beanList.setShowNext(false);
						break;
					}
					else 
					{
						myRow =  "<TR>";
				/*		myRow +="<td nowrap>" + msgRT.getE01CHMCUN()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMBNK()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMBRN()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMCCY()+"</td>";*/
						myRow += "<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + indexRow + "\" " +  " onclick=\"showAddInfo("+indexRow+")\"></TD>";
						myRow +="<td nowrap>" +  Util.formatDate(msgRT.getE01CHMRQ1(),msgRT.getE01CHMRQ2(), msgRT.getE01CHMRQ3()) +"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMACC()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMNA1()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMOFC()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMNCB()+"</td>";						
						myRow +="<td nowrap>" + msgRT.getE01CHMICK()+"</td>";
						myRow +="<td nowrap>" + msgRT.getE01CHMDBR()+"</td>";						
						//myRow +="<td nowrap><input type=checkbox name=SEL></td>";
						
						
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=CUN" + indexRow +  " value=" + msgRT.getE01CHMCUN() +">";
					 	myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=BNK" + indexRow +  " value=" + msgRT.getE01CHMBNK() +">";
					 	myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=BRN" + indexRow +  " value=" + msgRT.getE01CHMBRN() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=CCY" + indexRow +  " value=" + msgRT.getE01CHMCCY() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=RQ1" + indexRow +  " value=" + msgRT.getE01CHMRQ1() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=RQ2" + indexRow +  " value=" + msgRT.getE01CHMRQ2() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=RQ3" + indexRow +  " value=" + msgRT.getE01CHMRQ3() +">";												
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=ACC" + indexRow +  " value=" + msgRT.getE01CHMACC() +">";
					 	myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=NA1" + indexRow +  " value=" + msgRT.getE01CHMNA1() +">";
					 	myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=OFC" + indexRow +  " value=" + msgRT.getE01CHMOFC() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=NCB" + indexRow +  " value=" + msgRT.getE01CHMNCB() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=ICK" + indexRow +  " value=" + msgRT.getE01CHMICK() +">";
						myRow +="<td nowrap><INPUT TYPE=HIDDEN NAME=DBR" + indexRow +  " value=" + msgRT.getE01CHMDBR() +">";						

						
						myRow += "</TR>";
						
						beanList.addRow(myFlag, myRow);
						indexRow ++;	
										
						if (marker.equals("+")) 
						{
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
						
			beanListParam.setShowNext(true);
			beanListParam.addRow(myFlag, "<td nowrap><INPUT TYPE=HIDDEN NAME=E01SELBNK value=" + req.getParameter("E01SELBNK") +">");
			beanListParam.addRow(myFlag, "<td nowrap><INPUT TYPE=HIDDEN NAME=E01SELBRN value=" + req.getParameter("E01SELBRN") +">");		
			beanListParam.addRow(myFlag, "<td nowrap><INPUT TYPE=HIDDEN NAME=E01SELFD1 value=" + req.getParameter("E01SELFD1") +">");		
			beanListParam.addRow(myFlag, "<td nowrap><INPUT TYPE=HIDDEN NAME=E01SELFD2 value=" + req.getParameter("E01SELFD2") +">");
			beanListParam.addRow(myFlag, "<td nowrap><INPUT TYPE=HIDDEN NAME=E01SELFD3 value=" + req.getParameter("E01SELFD3") +">");		
			beanListParam.addRow(myFlag, "<td nowrap><INPUT TYPE=HIDDEN NAME=E01SELID1 value=" + req.getParameter("E01SELID1") +">");
			beanListParam.addRow(myFlag, "<td nowrap><INPUT TYPE=HIDDEN NAME=E01SELID2 value=" + req.getParameter("E01SELID2") +">");		
			beanListParam.addRow(myFlag, "<td nowrap><INPUT TYPE=HIDDEN NAME=E01SELID3 value=" + req.getParameter("E01SELID3") +">");		
			beanListParam.setShowNext(false);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("rtBasic", beanList);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("param", beanListParam);

			if (IsNotError)
			{  // There are no errors
//				ses.setAttribute("userPO", userPO);
//				ses.setAttribute("rtBasic", msgRT);
					
				try 
				{
					flexLog("About to call Page: " + LangPath + "ECH03101_st_Result.jsp");
					callPage(LangPath + "ECH03101_st_Result.jsp", req, res);	
				}
				catch (Exception e) 
				{
					flexLog("Exception calling page " + e);
				}
			}
			else
			{
					flexLog("About to call Page: " + LangPath + "ECH03101_st_RecepcionChequera.jsp");
					callPage(LangPath + "ECH03101_st_RecepcionChequera.jsp", req, res);	
			}
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


}




private void procActionRTRecepcion(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECH031001Message msgRT = null;
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

	try
	{
		flexLog("Send Initial Data");
		
		
		msgRT = (ECH031001Message)mc.getMessageRecord("ECH031001");
		
	 	msgRT.setH01USERID(user.getH01USR());
	 	msgRT.setH01PROGRM("ECH0310");
	 	msgRT.setH01TIMSYS(getTimeStamp());
	 	msgRT.setH01SCRCOD("01");
	 	msgRT.setH01OPECOD("0005");

		msgRT.setE01CHMCUN(req.getParameter("E01CHMCUN"));
		msgRT.setE01CHMBNK(req.getParameter("E01CHMBNK"));
		msgRT.setE01CHMBRN(req.getParameter("E01CHMBRN"));
		msgRT.setE01CHMCCY(req.getParameter("E01CHMCCY"));
		msgRT.setE01CHMRQ1(req.getParameter("E01CHMRQ1"));
		msgRT.setE01CHMRQ2(req.getParameter("E01CHMRQ2"));
		msgRT.setE01CHMRQ3(req.getParameter("E01CHMRQ3"));
		msgRT.setE01CHMACC(req.getParameter("E01CHMACC"));		
		msgRT.setE01CHMNA1(req.getParameter("E01CHMNA1"));		
		msgRT.setE01CHMOFC(req.getParameter("E01CHMOFC"));
		msgRT.setE01CHMNCB(req.getParameter("E01CHMNCB"));
		msgRT.setE01CHMICK(req.getParameter("E01CHMICK"));
		msgRT.setE01CHMDBR(req.getParameter("E01CHMDBR"));		
		msgRT.setE01CHMACT(req.getParameter("E01CHMACT"));		

	 	mc.sendMessage(msgRT);
	 	msgRT.destroy();
	 	flexLog("JSECH0000 Message Sent");
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
	  
	  if (newmessage.getFormatName().equals("ELEERR")) 
	  {
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);
			//showERROR(msgError);
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

	    if (newmessage.getFormatName().equals("ECH031001")) 
	    {

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("error", msgError);
			ses.setAttribute("rtBasic", msgRT);
			try {
					if (IsNotError)
					{  
					    try 
						{
							flexLog("About to call Page: " + LangPath + "ECH03101_st_Result.jsp");
							//callPage(LangPath + "ECH03101_st_Result.jsp", req, res);
							procActionEnterMaintCH(mc,user,req,res,ses);
						}
						catch (Exception e) 
						{
							flexLog("Exception calling page " + e);
						}
					}
					else
					{
 					  flexLog("About to call Page: " + LangPath + "ECH03101_st_Result.jsp");
			 		  callPage(LangPath + "ECH03101_st_Result.jsp", req, res);	
					}

				}
			catch (Exception e) 
			{
				flexLog("Exception calling page " + e);
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

		int screen = R_RT_ENTER_MAINT;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
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

				case R_RT_ENTER_MAINT : 
					procReqRTEnterMaint(msgUser, req, res, session);
					break;
				case A_RT_BASIC	:
					procActionRTBasic(mc, msgUser, req, res, session);
					break;
				case A_RT_ENTER_MAINT : 
					 procActionEnterMaint(mc,msgUser,req,res,session);
					break;
				case A_RT_ENTER_SOLICITUD : 	
					procActionEnterSolicitud(mc,msgUser,req,res,session);
					break;					
				case A_RT_SOLICITUD :
					procActionRTSolicitud(mc,msgUser,req,res,session);
					break;					
				case A_RT_ENTER_APROBACION : 	
					procActionEnterAprobacion(mc,msgUser,req,res,session);
					break;					
				case A_RT_APROBACION : 	
					procActionRTAprobacion(mc,msgUser,req,res,session);
					break;					
				case C_RT_RECEP : 
//                	callPage(LangPath + "ECH03101_st_RecepcionChequera.jsp", req, res);	
			        procReqRecepcionChequera(mc,msgUser,req,res,session);
                	break;
				case C_RT_ENTER_MAINT : 
			        procActionEnterMaintCH(mc,msgUser,req,res,session);
					break;
				case A_RT_RECEPCION : 	
					procActionRTRecepcion(mc,msgUser,req,res,session);
					break;					

				default :
					res.sendRedirect(super.srctx +LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
			//	return;
			}
			finally {
				s.close();
			}
			

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
		
	}
	
}

}