package datapro.eibs.products;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;


import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.tools.*;
import datapro.eibs.sockets.*;

public class JSECD0009 extends datapro.eibs.master.SuperServlet {
	
	
	public static final int LIST = 15;
	public static final int UPDATE = 5;
	public static final int QUERY = 2;
	public static final int EDIT=  51;
	public static final int DELETE= 4;
	public static final int SAVE = 1;
	public static final int ADD =11;
	
	protected String LangPath = "S";
	

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSECD0009() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEWD0005");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

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

			int screen = LIST;
			
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
					  case LIST :
						procActionList(mc, msgUser, req, res, session);
						break;
					  case UPDATE :
						procActionUpdate(mc, msgUser, req, res, session);
						break;
					  case SAVE :
						procActionSave(mc, msgUser, req, res, session);
						break;	
					  case QUERY :
						procActionQuery(mc, msgUser, req, res, session);
						break;		
					  case EDIT :
						procReqEdit(mc, msgUser, req, res, session);
						break;
					  case DELETE :
						procActionDelete(mc, msgUser, req, res, session);
						break;
					  case ADD :
						procReqNew(mc, msgUser, req, res, session);
						break;		
					  default :
						res.sendRedirect(super.srctx +LangPath + super.devPage);
						break;
				    }
				}
				catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 29;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
					//return;
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
	
	public void procActionList (MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
		
	
		ECD0009DSMessage msg = null;
		JBObjList beanList = null;
		UserPos	userPO = null;	
		
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		// Send Initial data
		try
		{
			
			flexLog("Send Initial Data");
			msg = (ECD0009DSMessage)mc.getMessageRecord("ECD0009DS");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("ECD0009");
			msg.setH01TIMSYS(getTimeStamp());
		 	msg.setH01SCRCOD("01");
		 	msg.setH01OPECOD("0015");
		 	
            Messanger messanger = new Messanger(mc,msg);
            messanger.setResponseMode(Messanger.NO_ERROR_MODE);
            messanger.sendMessage(req,res);
			beanList = messanger.getList();
		    flexLog("Putting java beans into the session");
			ses.setAttribute("ecd0009List", beanList);
			ses.setAttribute("error", messanger.getMsgError());
			ses.setAttribute("userPO",userPO);
			try {
			  flexLog("About to call Page: " + LangPath + "ECD0009_Card_List.jsp");
			  callPage(	LangPath + "ECD0009_Card_List.jsp",	req, res);
			} catch (Exception e) {	flexLog("Exception calling page " + e);	}
			
		}
		catch(MessangerException ex){
			ex.printStackTrace();
			flexLog("Error: " + ex);
			res.sendRedirect(super.srctx +LangPath + "MessagePage.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
	   finally {
		
	   }


}
	
	public void procActionQuery (MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
		ECD0009DSMessage msg = null;
		UserPos	userPO = null;	
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		try
		{
			
			flexLog("Send Initial Data");
			msg = (ECD0009DSMessage)mc.getMessageRecord("ECD0009DS");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("ECD0009");
			msg.setH01TIMSYS(getTimeStamp());
		 	msg.setH01SCRCOD("01");
		 	msg.setH01OPECOD("0002");
		 	
            Messanger messanger = new Messanger(mc,msg);
            messanger.sendMessage(req,res);
			msg = (ECD0009DSMessage) messanger.getRecord();
		    flexLog("Putting java beans into the session");
			ses.setAttribute("ecd0009", msg);
			ses.setAttribute("error", messanger.getMsgError());
			ses.setAttribute("userPO",userPO);
			req.setAttribute("ScreenMode","Q");
			try {
			  flexLog("About to call Page: " + LangPath + "ECD0009_Card_Info.jsp");
			  callPage(	LangPath + "ECD0009_Card_Info.jsp",req, res);
			} catch (Exception e) {	flexLog("Exception calling page " + e);	}
			
		}
		catch(MessangerException ex){
			ex.printStackTrace();
			flexLog("Error: " + ex);
			res.sendRedirect(super.srctx +LangPath + "MessagePage.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
	   finally {
		
	   }

		
	}
	
	public void procReqNew (MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
		req.setAttribute("ScreenMode","N");
		try {
		  MessageRecord	msg = mc.getMessageRecord("ECD0009DS");
		  ses.setAttribute("ecd0009", msg);	
		  flexLog("About to call Page: " + LangPath + "ECD0009_Card_Info.jsp");
		  callPage(	LangPath + "ECD0009_Card_Info.jsp",req, res);
		}
		 catch (Exception e) {	flexLog("Exception calling page " + e);	}
	}
	
	public void procReqEdit (MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
		ECD0009DSMessage msg = null;
		UserPos	userPO = null;	
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		try
		{
			
			flexLog("Send Initial Data");
			msg = (ECD0009DSMessage)mc.getMessageRecord("ECD0009DS");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("ECD0009");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0002");
		 	
			Messanger messanger = new Messanger(mc,msg);
			messanger.sendMessage(req,res);
			msg = (ECD0009DSMessage) messanger.getRecord();
			flexLog("Putting java beans into the session");
			ses.setAttribute("ecd0009", msg);
			ses.setAttribute("error", messanger.getMsgError());
			ses.setAttribute("userPO",userPO);
			req.setAttribute("ScreenMode","E");
			try {
			  flexLog("About to call Page: " + LangPath + "ECD0009_Card_Info.jsp");
			  callPage(	LangPath + "ECD0009_Card_Info.jsp",req, res);
			} catch (Exception e) {	flexLog("Exception calling page " + e);	}
			
		}
		catch(MessangerException ex){
			ex.printStackTrace();
			flexLog("Error: " + ex);
			res.sendRedirect(super.srctx +LangPath + "MessagePage.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
	   finally {
		
	   }

		
		
	}
	
	public void procActionUpdate (MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
		ECD0009DSMessage msg = null;
		UserPos	userPO = null;	
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		try
		{
			
			flexLog("Send Initial Data");
			msg = (ECD0009DSMessage)mc.getMessageRecord("ECD0009DS");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("ECD0009");
			msg.setH01TIMSYS(getTimeStamp());
		 	msg.setH01SCRCOD("01");
		 	msg.setH01OPECOD("0005");
		 	
            Messanger messanger = new Messanger(mc,msg);
            messanger.sendMessage(req,res);
			msg = (ECD0009DSMessage) messanger.getRecord();
			if (messanger.isNotError()){
		      flexLog("Putting java beans into the session");
			  ses.setAttribute("ecd0009", msg);
			  ses.setAttribute("error", messanger.getMsgError());
			  ses.setAttribute("userPO",userPO);
			  //req.setAttribute("ScreenMode","Q");
			  try {
			    flexLog("About to call Page: " + LangPath + "ECD0009_Card_List.jsp");
			    procActionList(mc,user,req,res,ses);
			  } catch (Exception e) {	flexLog("Exception calling page " + e);	}
			}
			else {
			   ELEERRMessage error = messanger.getMsgError();
			   ses.setAttribute("error",error);
			   ses.setAttribute("ecd0009", msg);
			   flexLog("About to call Page: " + LangPath + "ECD0009_Card_List.jsp");
			   callPage(LangPath + "ECD0009_Card_List.jsp",req, res); 
			}
		}
		catch(MessangerException ex){
			ex.printStackTrace();
			flexLog("Error: " + ex);
			res.sendRedirect(super.srctx +LangPath + "MessagePage.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
	   finally {
		
	   }

		
		
	}
	
	
	public void procActionSave (MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
		ECD0009DSMessage msg = null;
		UserPos	userPO = null;	
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		try
		{
			flexLog("Send Initial Data");
			msg = (ECD0009DSMessage)mc.getMessageRecord("ECD0009DS");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("ECD0009");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0001");
		 	
			Messanger messanger = new Messanger(mc,msg);
			messanger.sendMessage(req,res);
			msg = (ECD0009DSMessage) messanger.getRecord();
			if (messanger.isNotError()){
			  flexLog("Putting java beans into the session");
			  ses.setAttribute("ecd0009", msg);
			  ses.setAttribute("error", messanger.getMsgError());
			  ses.setAttribute("userPO",userPO);
			  //req.setAttribute("ScreenMode","Q");
			  try {
			    flexLog("About to call Page: " + LangPath + "ECD0009_Card_List.jsp");
			    //callPage(	LangPath + "ECD0009_Card_List.jsp",req, res);
			    procActionList(mc,user,req,res,ses);
			  }
			  catch (Exception e) {	flexLog("Exception calling page " + e);	}
			}  
			else {
			    ELEERRMessage error = messanger.getMsgError();
				ses.setAttribute("error",error);
				ses.setAttribute("ecd0009", msg);
				flexLog("About to call Page: " + LangPath +  "ECD0009_Card_Info.jsp");
				callPage(LangPath +  "ECD0009_Card_Info.jsp",req, res); 
			  }
		}
		catch(MessangerException ex){
			ex.printStackTrace();
			flexLog("Error: " + ex);
			res.sendRedirect(super.srctx +LangPath + "MessagePage.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
	   finally {
		
	   }

		
		
	}
	
	
	public void procActionDelete (MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
		ECD0009DSMessage msg = null;
		UserPos	userPO = null;	
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		try
		{
			
			flexLog("Send Initial Data");
			msg = (ECD0009DSMessage)mc.getMessageRecord("ECD0009DS");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("ECD0009");
			msg.setH01TIMSYS(getTimeStamp());
		 	msg.setH01SCRCOD("01");
		 	msg.setH01OPECOD("0004");
		 	
            Messanger messanger = new Messanger(mc,msg);
            messanger.sendMessage(req,res);
			msg = (ECD0009DSMessage) messanger.getRecord();
		    flexLog("Putting java beans into the session");
			ses.setAttribute("ecd0009", msg);
			ses.setAttribute("error", messanger.getMsgError());
			ses.setAttribute("userPO",userPO);
			//req.setAttribute("ScreenMode","Q");
			try {
			  flexLog("About to call Page: " + LangPath + "ECD0009_Card_List.jsp");
			  //callPage(	LangPath + "ECD0009_Card_List.jsp",req, res);
			  procActionList(mc,user,req,res,ses);
			} catch (Exception e) {	flexLog("Exception calling page " + e);	}
			
		}
		catch(MessangerException ex){
			ex.printStackTrace();
			flexLog("Error: " + ex);
			res.sendRedirect(super.srctx +LangPath + "MessagePage.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
	   finally {
		
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
