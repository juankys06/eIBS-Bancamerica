package datapro.eibs.teller;

/**
 * Insert the type's description here.
 * Creation date: (3/3/05 6:08:55 PM)
 * @author: Eloy Rodriguez
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDD0310 extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int A_POSITION = 800;
	
	protected static final int R_TELLER_LIST = 1;

	protected static final int R_NEW = 300;
	protected static final int R_MAINTENANCE = 500;
	protected static final int R_DELETE = 700;
	
	protected static final int A_MAINTENANCE = 600;
	protected static final int A_TYPE_MAINTENANCE = 200;
	protected static final int A_NEW = 400;
	
	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDD0310() 
	  {
	   super();
	  }

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() 
	  {
       flexLog("free resources used by JSEIE00000");
	  }
	/**
	 * This method was created by David Mavilla.
	 * 
	 */
	public void init(ServletConfig config) throws ServletException 
	  {
	   super.init(config);
	  }


	protected void procActionPos (MessageContext mc,
	                              ESS0030DSMessage user,
	                              HttpServletRequest req,
		                          HttpServletResponse res,
		                          HttpSession ses) throws ServletException, IOException 
	{
	 MessageRecord newmessage = null;
	 ELEERRMessage msgError = null;
	 UserPos userPO = null;
	 boolean IsNotError = false;
     userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
     int inptOPT = 0;
     inptOPT = Integer.parseInt(req.getParameter("opt"));
     switch (inptOPT) 
     {
      case 1 : //New
			   callPage(LangPath + "EDD0310_teller_enter_new.jsp",	req, res);
			   break;
	  case 2 : //Maintenance
	           procReqMaintenance(mc, user, req, res, ses);
	           break;
	  case 3 : //Delete
	           procReqBranchListDelete(mc, user, req, res, ses);
	           break;
	  case 4 : //Change Teller Type
			   callPage(LangPath + "EDD0310_teller_enter_type.jsp",	req, res);
			   break;
     default :
               res.sendRedirect(super.srctx + "/servlet/datapro.eibs.teller.JSEDD0310?SCREEN=1");
			   break;
	 }
	}

	protected void procReqTellerList (MessageContext mc,
	                                  ESS0030DSMessage user,
	                                  HttpServletRequest req,
	                                  HttpServletResponse res,
	                                  HttpSession ses) throws ServletException, IOException 
    {
	 MessageRecord newmessage = null;
	 EDD031001Message msgList = null;
	 ELEERRMessage msgError = null;
	 UserPos userPO = null;
	 boolean IsNotError = false;
	 try {
	      msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate 
	                   (getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
	     } 
	 catch (Exception ex) 
	     {
		   flexLog("Error: " + ex);
	     }

     userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	 if (req.getParameter("E01BRNBNK")!= null)
	   userPO.setBank(req.getParameter("E01BRNBNK"));
		
	 // Send Initial data
	 try {
		  msgList = (EDD031001Message) mc.getMessageRecord("EDD031001");
		  msgList.setH01USERID(user.getH01USR());
		  msgList.setH01PROGRM("ELD0100");
		  msgList.setH01TIMSYS(getTimeStamp());
		  msgList.setH01SCRCOD("01");
		  msgList.setH01OPECOD("0015");
		  msgList.send();
		  msgList.destroy();
		  flexLog("EDD031001 Message Sent");
	     } 
	  catch (Exception e) 
		 {
		  e.printStackTrace();
		  flexLog("Error: " + e);
		  throw new RuntimeException("Socket Communication Error");
		 }

      // Receive Data
	  try {
		   newmessage = mc.receiveMessage();
		   if (newmessage.getFormatName().equals("ELEERR")) 
		     {
			  try {
				   msgError = (datapro.eibs.beans.ELEERRMessage) Beans.instantiate
			                    (getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
    		      } 
			  catch (Exception ex) 
			      {
			       flexLog("Error: " + ex);
			      }
			  msgError = (ELEERRMessage) newmessage;
			  flexLog("Putting java beans into the session");
			  ses.setAttribute("error", msgError);
			  ses.setAttribute("userPO", userPO);
		     }
		   } 
	   catch (Exception e) 
		   {
		    e.printStackTrace();
		    flexLog("Error: " + e + newmessage);
		    throw new RuntimeException("Socket Communication Error Receiving");
		   }
	   try {
		    newmessage = mc.receiveMessage();
		    if (newmessage.getFormatName().equals("EDD031001")) 
	          {
			   JBObjList beanList = new JBObjList();
			   boolean firstTime = true;
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
			   while (true) 
			     {
				  msgList = (EDD031001Message) newmessage;
				  marker = msgList.getE01INDOPE();
				  if (firstTime) 
    			    {
					 firstTime = false;
					 chk = "checked";
				    } 
				  else chk = "";
				  if (marker.equals("*")) 
    			    {
					 beanList.setShowNext(false);
					 break;
				    } 
				  else 
				    {
					 beanList.addRow(msgList);
					 if (marker.equals("+")) 
					   {
						beanList.setShowNext(true);
						break;
					   }
				     }
				   newmessage = mc.receiveMessage();
			      } 

				flexLog("Putting java beans into the session");
				ses.setAttribute("EDD031001Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {
				     flexLog("About to call Page: " + LangPath + "EDD0310_teller_list.jsp");
  				     callPage(LangPath + "EDD0310_teller_list.jsp", req, res);
				    } 
			    catch (Exception e) 
				    {
				     flexLog("Exception calling page " + e);
				    }
		      } 
			else flexLog("Message " + newmessage.getFormatName() + " received.");
	       } 
	   catch (Exception e) 
		   {
		    e.printStackTrace();
		    flexLog("Error: " + e);
		    throw new RuntimeException("Socket Communication Data Receiving");
		   }
	}

	protected void procReqBranchListDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD031001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		
			msgError = new ELEERRMessage();
		

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("EDD031001Help");
			int idx = Integer.parseInt(req.getParameter("idxrow"));
			bl.setCurrentRow(idx);
			msgList = (EDD031001Message) bl.getRecord();
			//msgList = (EDD031001Message) mc.getMessageRecord("EDD031001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ELD010003");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0009");

			mc.sendMessage(msgList);
			flexLog("EDD031001 Message Sent");
		
		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				
				if (!msgError.getERRNUM().equals("0")){
					callPage(LangPath + "EDD0310_teller_list.jsp", req, res);
				}
                else{
					msgList.destroy();
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.teller.JSEDD0310?SCREEN=1");
                }

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		
	}

	protected void procActionMaintenance(MessageContext mc,
										 ESS0030DSMessage user,
										 HttpServletRequest req,
										 HttpServletResponse res,
										 HttpSession ses,
										 int scr) throws ServletException, IOException 
	{
	 MessageRecord newmessage = null;
	 EDD031001Message msgRT = null;
	 ELEERRMessage msgError = null;
	 UserPos userPO = null;
	 boolean IsNotError = false;
	 int acctype = 0;
	 
	 msgError = new datapro.eibs.beans.ELEERRMessage();
	

	 userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	 // Send Initial data
	 try {
		  flexLog("Send Initial Data");
		  msgRT = (EDD031001Message) ses.getAttribute("brnDetails");
		  msgRT.setH01USERID(user.getH01USR());
		  msgRT.setH01PROGRM("EGL036001");
		  msgRT.setH01TIMSYS(getTimeStamp());
		  msgRT.setH01SCRCOD("01");
		  if (scr == 600 || scr == 200) 
		    msgRT.setH01OPECOD("0002");
		  else  
		    msgRT.setH01OPECOD("0001");

		  // all the fields here
		  java.util.Enumeration enu = msgRT.fieldEnumeration();
		  MessageField field = null;
		  String value = null;
		  while (enu.hasMoreElements()) 
			{
			 field = (MessageField) enu.nextElement();
			 try {
				  value = req.getParameter(field.getTag()).toUpperCase();
				  if (value != null) field.setString(value);
				 } 
			 catch (Exception e) 
				 {
				 }
			 }

		  mc.sendMessage(msgRT);
		  msgRT.destroy();
		  flexLog("EDD031001 Message Sent");
		  
	

	  // Receive Error Message
	
		   newmessage = mc.receiveMessage();
		   if (newmessage.getFormatName().equals("ELEERR")) 
			 {
			  msgError = (ELEERRMessage) newmessage;
			  IsNotError = msgError.getERRNUM().equals("0");
			  flexLog("IsNotError = " + IsNotError);
			  showERROR(msgError);
			 } 
		   else	flexLog("Message " + newmessage.getFormatName() + " received.");
	

	  // Receive Data
	  
		   newmessage = mc.receiveMessage();
		   if (newmessage.getFormatName().equals("EDD031001")) 
			 {
			  
			  msgRT = (EDD031001Message) newmessage;
			  flexLog("Putting java beans into the session");
			  ses.setAttribute("error", msgError);
			  
			  ses.setAttribute("userPO", userPO);

			  if (IsNotError && scr != 200) // There are no errors 
				{ 
				  ses.removeAttribute("brnDetails");
				  res.sendRedirect(super.srctx + "/servlet/datapro.eibs.teller.JSEDD0310?SCREEN=1");
				} 
			  else // There are errors 			  	
				{ 
				ses.setAttribute("brnDetails", msgRT);
				 try {
					  flexLog("About to call Page: " + LangPath	+ "EDD0310_teller_maintenance.jsp");
					  callPage(LangPath + "EDD0310_teller_maintenance.jsp",	req, res);
					 } 
				 catch (Exception e) 
					 {
					  flexLog("Exception calling page " + e);
					 }
				 }
			 } 
		   else flexLog("Message " + newmessage.getFormatName() + " received.");
		  } 
	  catch (Exception e) 
		  {
		   e.printStackTrace();
		   flexLog("Error: " + e);
		   throw new RuntimeException("Socket Communication Error");
		  }
	}


	protected void procReqNew(MessageContext mc,
		                      ESS0030DSMessage user,
		                      HttpServletRequest req,
		                      HttpServletResponse res,
		                      HttpSession ses) throws ServletException, IOException 
    {
  	 MessageRecord newmessage = null;
	 ELEERRMessage msgError = null;
	 EDD031001Message msgRT = null;
  	 UserPos userPO = null;
	 boolean IsNotError = false;
	 msgError = new ELEERRMessage();
	 userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	 userPO.setPurpose("NEW");
	 ses.setAttribute("userPO", userPO);
		
	 msgRT = new datapro.eibs.beans.EDD031001Message();
	 msgRT.setE01TLMTYP(req.getParameter("E01TLMTYP"));
	 msgRT.setE01TLMTID(req.getParameter("E01TLMTID"));
	 msgRT.setE01TLMCCY(req.getParameter("E01TLMCCY"));
	 
	 ses.setAttribute("brnDetails", msgRT);
	 try {
		  flexLog("About to call Page: " + LangPath	+ "EDD0310_teller_maintenance.jsp");
		  callPage(LangPath + "EDD0310_teller_maintenance.jsp?SCREEN=400", req, res);
		 } 
	 catch (Exception e) 
	     {
		  flexLog("Exception calling page " + e);
		 }

	}




	protected void procReqMaintenance(MessageContext mc,
		                              ESS0030DSMessage user,
		                              HttpServletRequest req,
		                              HttpServletResponse res,
		                              HttpSession ses) throws ServletException, IOException 
	{
	 EDD031001Message msgDoc = null;
	 UserPos userPO = null;
 	 userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	 userPO.setPurpose("MAINTENANCE");
		
	 // Receive Data
	 try {
		  JBObjList bl = (JBObjList) ses.getAttribute("EDD031001Help");
		  int idx = Integer.parseInt(req.getParameter("idxrow"));
		  bl.setCurrentRow(idx);
   		  msgDoc = (EDD031001Message) bl.getRecord();
		  flexLog("Putting java beans into the session");
		  ses.setAttribute("userPO", userPO);
		  ses.setAttribute("brnDetails", msgDoc);
		  try {
			   flexLog("About to call Page: " + LangPath + "EDD0310_teller_maintenance.jsp");
			   callPage(LangPath + "EDD0310_teller_maintenance.jsp?SCREEN=600", req, res);
			  } 
		  catch (Exception e) 
		      {
			   flexLog("Exception calling page " + e);
			  }
		 } 
	 catch (Exception e) 
	     {
		  e.printStackTrace();
		  flexLog("Error: " + e);
		  throw new RuntimeException("Socket Communication Error");
		 }

	}


	public void service(HttpServletRequest req, 
	                    HttpServletResponse res) throws ServletException, IOException 
	{
  	 Socket s = null;
	 MessageContext mc = null;
	 ESS0030DSMessage msgUser = null;
	 HttpSession session = null;
	 session = (HttpSession) req.getSession(false);
	 if (session == null) 
	   {
		try {
			 res.setContentType("text/html");
			 printLogInAgain(res.getWriter());
			} 
		catch (Exception e) 
		    {
			 e.printStackTrace();
			 flexLog("Exception ocurred. Exception = " + e);
			}
		} 
	  else 
	    {
		 int screen = A_POSITION;
		 try {
			  msgUser =	(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
			  // Here we should get the path from the user profile
			  LangPath = super.rootPath + msgUser.getE01LAN() + "/";
			  try {
				   flexLog("Opennig Socket Connection");
				   s = new Socket(super.hostIP, getInitSocket(req) + 1);
				   s.setSoTimeout(super.sckTimeOut);
				   mc =	new MessageContext( new DataInputStream(
								            new BufferedInputStream(s.getInputStream())),
							                new DataOutputStream(
								            new BufferedOutputStream(s.getOutputStream())),
							                "datapro.eibs.beans");
				   try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					   } 
				   catch (Exception e) 
				       {
						flexLog("Screen set to default value");
					   }
				   switch (screen) 
				     {
					  //Request
					  case R_TELLER_LIST : procReqTellerList(mc, msgUser, req, res, session);
							               break;
					  case R_NEW : procReqNew(mc, msgUser, req, res, session);							
							       break;	
					  case R_MAINTENANCE : procReqMaintenance(mc, msgUser, req, res, session);							
							               break;
					  case R_DELETE : procReqBranchListDelete(mc,msgUser, req, res, session);
							          break;
					  // Action
					  case A_POSITION :	procActionPos(mc, msgUser, req, res, session);
							            break;
					  case A_NEW :
					  case A_TYPE_MAINTENANCE:
					  case A_MAINTENANCE : procActionMaintenance(mc, msgUser, req, res, session, screen);							
							               break;
					  // END Entering
					  default :	res.sendRedirect(super.srctx + LangPath + super.devPage);
							    break;
					 }
				  } 
			  catch (Exception e) 
			      {
				   e.printStackTrace();
				   int sck = getInitSocket(req) + 1;
				   flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				   res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				   //return;
				  } 
			  finally 
			      {
				   s.close();
				  }
			 } 
		 catch (Exception e) 
		     {
			  flexLog("Error: " + e);
			  res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			 }
		}
	}
	
	protected void showERROR(ELEERRMessage m) 
	{
	 if (logType != NONE) 
	   {
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
