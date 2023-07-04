package datapro.eibs.products;

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

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

import com.datapro.generic.beanutil.BeanList;



public class JSERA0130 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 

	//inquiry options
	protected static final int R_COLLATERALS	= 100;
	
	protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSERA0130() {
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

////////
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

		  int screen = R_COLLATERALS;
		
		  try {
		
			  msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			  // Here we should get the path from the user profile
			  LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			  try
			  {
				  flexLog("Opennig Socket Connection ");
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
				  // BEGIN CD
				  // Request
				  case R_COLLATERALS :
					  procGetCollaterals(mc, msgUser, req, res, session);
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
			  } finally {
					  s.close();
			  }

		  }
		  catch (Exception e) {
			  flexLog("Error: " + e);
			  res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		  }

	  }
	
  }

//////////

  protected void procGetCollaterals(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			  throws ServletException, IOException {

	  MessageRecord newmessage = null;
	  ERA013001Message msgLNSts = null;
	  UserPos	userPO = null;	
	  JBObjList lnStatus = null;
	
	  userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	  String opCode = "0004";

	  // Send Initial data
	  try
	  {
		  msgLNSts = (EDL013503Message)mc.getMessageRecord("EDL013503");
		  msgLNSts.setH03USERID(user.getH01USR());
		  msgLNSts.setH03PROGRM("EDL0135");
		  msgLNSts.setH03TIMSYS(getTimeStamp());
		  msgLNSts.setH03SCRCOD("01");
		  msgLNSts.setH03OPECOD(opCode);
		  msgLNSts.setE03DEAACC(userPO.getIdentifier());
		  msgLNSts.send();	
		  msgLNSts.destroy();

		  flexLog("EDL013503 Message Sent");
	  }		
	  catch (Exception e)
	  {
		  e.printStackTrace();
		  flexLog("Error: " + e);
		  throw new RuntimeException("Socket Communication Error");
	  }


	  //Receive Error Message
	  try
	  {
		  newmessage = mc.receiveMessage();
	      if (newmessage.getFormatName().equals("ERA013001")) {
			  try {
				  msgLNSts = new datapro.eibs.beans.ERA013001Message();
				  flexLog("ERA013001 Message Received");
			  } catch (Exception ex) {
				  flexLog("Error: " + ex); 
			  }
			
			  String marker = "";
			  lnStatus = new JBObjList();				
			  while (true) {
				  msgLNSts = (ERA013001Message)newmessage; 
				  marker = msgLNSts.getH03FLGMAS();
				  if (marker.equals("*")) {
						  break;
				  } else {										
						  lnStatus.addRow(msgLNSts);									
				  }											
				  newmessage = mc.receiveMessage();
			  }				

			  flexLog("Putting java beans into the session");
			  ses.setAttribute("lnStatus", lnStatus);
			  try {
				  flexLog("About to call Page: " + LangPath + "ERA0130_collaterlas_details.jsp");
				  callPage(LangPath + "ERA0130_collaterlas_details.jsp", req, res);	
			  }
			  catch (Exception e) {
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



}