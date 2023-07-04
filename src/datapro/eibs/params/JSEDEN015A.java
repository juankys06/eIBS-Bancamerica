package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Ramses Amaro
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.Socket;
import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.datapro.generic.beanutil.BeanList;

import datapro.eibs.beans.EDEN015DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import com.jspsmart.upload.SmartUpload;

public class JSEDEN015A extends datapro.eibs.master.SuperServlet {

	private ServletConfig config = null;
	
	// entering options

	protected static final int A_ENTER	 		= 200;
	
	protected String LangPath = "S";
	
	
/**
 * JSECLI001 constructor comment.
 */
public JSEDEN015A() {
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
	this.config = config;
}
/**
 * This method was created in VisualAge.
 */


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

	MessageRecord newmessage = null;
	EDEN015DSMessage msgEDE = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;
	boolean IsNotError = false;

	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
	} catch (Exception ex) {
		flexLog("Error: " + ex);
	}

	userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	String st = "";
	StringBuffer buf = new StringBuffer();

	//read file
	try {
		SmartUpload mySmartUpload = new SmartUpload();
		mySmartUpload.initialize(config, req, res);
		mySmartUpload.upload();
		com.jspsmart.upload.File myFile =  mySmartUpload.getFiles().getFile(0);
				
		StringReader sr = new StringReader(myFile.getContentString());
		LineNumberReader lnr = new LineNumberReader(sr);
		String line = "";
		boolean firstTime = true;
		
		
		while (true) {
			line = lnr.readLine();
			// detection of eof
			if (line == null) {
				break;
			}			
			msgEDE = new EDEN015DSMessage();
			msgEDE.setH01USERID(user.getH01USR());
			msgEDE.setH01PROGRM("EDEN015");
			msgEDE.setH01TIMSYS(getTimeStamp());
			msgEDE.setH01OPECOD("0001");
			if (firstTime == true) {
				firstTime = false;
				msgEDE.setH01FLGWK2("1");
			}

			try {
				msgEDE.setE01TYPE(mySmartUpload.getRequest().getParameter("E01TYPE"));
			} catch (Exception e) {
				msgEDE.setE01TYPE("");
			}
			
			msgEDE.setH01FLGWK1("");
			msgEDE.setE01DATA(line);
			//msgEDE.send();
			mc.sendMessage(msgEDE);
			
			//	Receive Error Message
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
			 catch (Exception e)
			 {
				 e.printStackTrace();
				 flexLog("Error: " + e);
				 throw new RuntimeException("Socket Communication Error");
			 }	
			if (!IsNotError) {
			   flexLog("Putting java beans into the session");
			   ses.setAttribute("error", msgError);	
			   try {
				   flexLog("About to call Page: " + LangPath + "EDEN015_enter_file.jsp");
				   callPage(LangPath + "EDEN015_enter_file.jsp", req, res);	
			   }
			   catch (Exception e) {
				   flexLog("Exception calling page " + e);
			   }				
			}			
			msgEDE = null;
		}

		msgEDE = new EDEN015DSMessage();
		msgEDE.setH01USERID(user.getH01USR());
		msgEDE.setH01PROGRM("EDEN015");
		msgEDE.setH01TIMSYS(getTimeStamp());
		msgEDE.setH01OPECOD("0001");
		try {
			msgEDE.setE01TYPE(mySmartUpload.getRequest().getParameter("E01TYPE"));
		} catch (Exception e) {
			msgEDE.setE01TYPE("");
		}	
		msgEDE.setH01FLGWK1("9");
		msgEDE.setE01DATA("");
		//msgEDE.send();
		mc.sendMessage(msgEDE);
		msgEDE.destroy();

		//	Receive Error Message
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
		 catch (Exception e)
		 {
			 e.printStackTrace();
			 flexLog("Error: " + e);
			 throw new RuntimeException("Socket Communication Error");
		 }	
		if (!IsNotError) {
		   flexLog("Putting java beans into the session");
		   ses.setAttribute("error", msgError);	
		   try {
			   flexLog("About to call Page: " + LangPath + "EDEN015_enter_file.jsp");
			   callPage(LangPath + "EDEN015_enter_file.jsp", req, res);	
		   }
		   catch (Exception e) {
			   flexLog("Exception calling page " + e);
		   }				
		} else {
			try {
				flexLog("About to call Page: "	+ LangPath	+ "EDEN015_enter_file_confirm.jsp");
				callPage(LangPath + "EDEN015_enter_file_confirm.jsp", req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}					
		}
		msgEDE = null;
				
		lnr.close();
		sr.close();		

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

		int screen = A_ENTER;
		
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
				// BEGIN Entering
				// Request
				// Action 
				case A_ENTER : 
					procActionEnter(mc, msgUser, req, res, session);
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