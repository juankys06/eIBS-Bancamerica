package datapro.eibs.master;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.MethodUtils;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public abstract class BaseServlet extends SuperServlet {

	protected int socketNumber = 1;

	protected String LangPath = "S";
	
	protected final int ERROR_OR_DATA = 1 ;  
	protected final int ERROR_AND_DATA = 2 ;  

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException {

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

			msgUser = (datapro.eibs.beans.ESS0030DSMessage) session
					.getAttribute("currUser");

			// Here we should get the path from the user profile
			flexLog("LangPath:" + rootPath + msgUser.getE01LAN() + "/") ;
			LangPath = rootPath + msgUser.getE01LAN() + "/";

			flexLog("Opennig Socket Connection:" + super.hostIP + ":" +
					getInitSocket(req) + socketNumber);
			try {

				s = new Socket(super.hostIP, getInitSocket(req) + socketNumber);
				s.setSoTimeout(super.sckTimeOut);
				mc = new MessageContext(new DataInputStream(
						new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(new BufferedOutputStream(s
								.getOutputStream())), "datapro.eibs.beans");

				
				int screen ;
				screen = Integer.parseInt(req.getParameter("SCREEN"));
				
				try {
					processRequest(mc, msgUser, req, res, session, screen );
				} catch (ServletException e1) {
					e1.printStackTrace();
					throw new RuntimeException(e1.getMessage());
					
				} catch (IOException e1) {
					e1.printStackTrace();
					try {
						res.sendRedirect(super.srctx + LangPath
								+ super.sckNotOpenPage);
					} catch (IOException e2) {
						e2.printStackTrace();
						throw new RuntimeException(e2.getMessage());
					}
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
				flexLog("Socket not Open(Port " + getInitSocket(req)
						+ socketNumber + "). Error: " + e);
				try {
					res.sendRedirect(super.srctx + LangPath
							+ super.sckNotOpenPage);
				} catch (IOException e1) {
					e1.printStackTrace();
					throw new RuntimeException(e1.getMessage());
				}

			} finally {
				try {
					s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					throw new RuntimeException(e1.getMessage());
				}
			}

		}

	}


	abstract protected void processRequest(MessageContext mc,
			ESS0030DSMessage msgUser, HttpServletRequest req,
			HttpServletResponse res, HttpSession session, int screen)
		throws ServletException, IOException;


	protected void logERROR(ELEERRMessage m)
	{
		if (logType != NONE) {
			
			flexLog("ERROR received.");
			
			flexLog("ERROR number:" + m.getERRNUM());
			flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01() + " code : " + m.getERDF01());
			flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02() + " code : " + m.getERDF02());
			flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03() + " code : " + m.getERDF03());
			flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04() + " code : " + m.getERDF04());
			flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05() + " code : " + m.getERDF05());
			flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06() + " code : " + m.getERDF06());
			flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07() + " code : " + m.getERDF07());
			flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08() + " code : " + m.getERDF08());
			flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09() + " code : " + m.getERDF09());
			flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10() + " code : " + m.getERDF10());
			
		}
	}

	
	protected void pageRedirect(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String pageName) throws ServletException,
			IOException {

		ELEERRMessage msgError = null;

		msgError = new datapro.eibs.beans.ELEERRMessage();
		session.setAttribute("error", msgError);

		flexLog("About to call Page: " + LangPath + pageName);
		callPage(LangPath + pageName, req, res);
	}
	
	/*
	 * SOLO PROCESA OPCIONES QUE RETORNAN UN SOLO MENSAJE
	 * PageOk: puede venir en blanco solo cuando se procesa un bloque de registros 
	 * y se envia con valor para el ultimo registro procesado.
	 * PageOk puede ser un JSP o un servlet. 
	 */
	protected void processOption(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses,
			MessageRecord messageFormat, String pageOK, String pageError, 
			int responseType)
			throws ServletException, IOException {
	
		
		MessageRecord newmessage = null;	
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		String forwardPage = null ;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		Enumeration enumeration = messageFormat.fieldEnumeration();
		MessageField field = null;	
		while (enumeration.hasMoreElements()) {

			field = (MessageField) enumeration.nextElement();		
			
			try {				
				if (field.getTag().toUpperCase().substring(3).equals("USERID")) MethodUtils.invokeMethod(messageFormat, "set"
						+ field.getTag().toUpperCase(), user.getH01USR());
				else if (field.getTag().toUpperCase().substring(3).equals("TIMSYS")) MethodUtils.invokeMethod(messageFormat, "set"
						+ field.getTag().toUpperCase(), getTimeStamp());
				else if (field.getTag().toUpperCase().substring(3).equals("SCRCOD")) MethodUtils.invokeMethod(messageFormat, "set"
						+ field.getTag().toUpperCase(), "01");
				else 	MethodUtils.invokeMethod(messageFormat, "set"
						+ field.getTag().toUpperCase(), req.getParameter(field.getTag().toUpperCase()));
		
				
			} catch (NoSuchMethodException e) {
				flexLog("EXCEPTION:" + e.getMessage());
			} catch (IllegalAccessException e) {
				flexLog("EXCEPTION:" + e.getMessage());
			} catch (InvocationTargetException e) {
				flexLog("EXCEPTION:" + e.getMessage());
			}catch (Exception e) {
				flexLog("EXCEPTION:" + e.getMessage());
			}

		}
		
		//SEND MESSAGE
		flexLog( "MSG TO SEND:" + messageFormat.getFormatName() ) ;
		flexLog( "MSG TO SEND:" + messageFormat.toString() ) ;
		mc.sendMessage( messageFormat );
		messageFormat.destroy();

		try {

			//RECEIVE RESPONSE MSGS
			switch ( responseType ) {
			case ERROR_OR_DATA :
				newmessage = mc.receiveMessage();
				flexLog("Msg Received:" + newmessage.getFormatName() ) ;
				
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					forwardPage = pageError ;
					logERROR( msgError );
				}else if (newmessage.getFormatName().equals( messageFormat.getFormatName() )) {
					messageFormat = newmessage ;
					forwardPage = pageOK ;
				}else{
					flexLog("Msg expected not Received:" + messageFormat.getFormatName() ) ;
					throw new Exception( "Msg expected not Received" ) ;
				}				
				break;
				
			case ERROR_AND_DATA :
				//RECEIVE ERROR
				newmessage = mc.receiveMessage();
				msgError = (ELEERRMessage) newmessage;
				flexLog("Msg Received:" + newmessage.getFormatName() ) ;
				if (newmessage.getFormatName().equals("ELEERR")) {
					forwardPage = pageError ;
					logERROR( msgError );
				}else{
					flexLog("Msg expected not Received:" + messageFormat.getFormatName() ) ;
					throw new Exception( "Msg expected not Received" ) ;
				}	
				
				//RECEIVE DATA
				newmessage = mc.receiveMessage();
				flexLog("Msg Received:" + newmessage.getFormatName() ) ;
				if (newmessage.getFormatName().equals( messageFormat.getFormatName() )) {
					messageFormat = newmessage ;
					forwardPage = pageOK ;
				}else{
					flexLog("Msg expected not Received:" + messageFormat.getFormatName() ) ;
					throw new Exception( "Msg expected not Received" ) ;
				}
				
				if ( !msgError.getERRNUM().equals("0") ) {
					forwardPage = pageError ;
				} 
				
				break;

			default:
				throw new Exception( "Response Type not recognized." ) ; 
			}
			
		} catch (Exception e) {
			flexLog("No More MSGs Received") ;
			if (forwardPage == null) {				
				msgError.setERRNUM("1") ;
				msgError.setERNU01("-999");
				msgError.setERDS01( e.getMessage() ) ;
				forwardPage = pageError ; 
			}
		}
		
		if (pageOK!="")
		{
		flexLog("Putting beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("msgData", messageFormat );
		
		flexLog("About to call Page: " + LangPath + forwardPage);
		callPage(LangPath + forwardPage, req, res);
		}	
			
	}
	
	/*
	 * PROCESA LAS OPCIONES DE UNA LISTA
	 */
	protected void processList(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses,
			MessageRecord messageFormat, String pageOK, String pageError, 
			int responseType)
			throws ServletException, IOException {
		
		
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		String forwardPage = null ;
		JBObjList msgList = null ;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		Enumeration enumeration = messageFormat.fieldEnumeration();
		MessageField field = null;	
		while (enumeration.hasMoreElements()) {
			field = (MessageField) enumeration.nextElement();		
			
			try {				
				if (field.getTag().toUpperCase().substring(3).equals("USERID")) MethodUtils.invokeMethod(messageFormat, "set"
						+ field.getTag().toUpperCase(), user.getH01USR());
				else if (field.getTag().toUpperCase().substring(3).equals("TIMSYS")) MethodUtils.invokeMethod(messageFormat, "set"
						+ field.getTag().toUpperCase(), getTimeStamp());
				else if (field.getTag().toUpperCase().substring(3).equals("SCRCOD")) MethodUtils.invokeMethod(messageFormat, "set"
						+ field.getTag().toUpperCase(), "01");
				else 	MethodUtils.invokeMethod(messageFormat, "set"
						+ field.getTag().toUpperCase(), req.getParameter(field.getTag().toUpperCase()));
		
				
			} catch (NoSuchMethodException e) {
				flexLog("EXCEPTION:" + e.getMessage());
			} catch (IllegalAccessException e) {
				flexLog("EXCEPTION:" + e.getMessage());
			} catch (InvocationTargetException e) {
				flexLog("EXCEPTION:" + e.getMessage());
			}catch (Exception e) {
				flexLog("EXCEPTION:" + e.getMessage());
			}

		}
		
		//SEND MESSAGE
		flexLog( "MSG TO SEND:" + messageFormat.getFormatName() ) ;
		flexLog( "MSG TO SEND:" + messageFormat.toString() ) ;
		mc.sendMessage( messageFormat );
		messageFormat.destroy();

		try {

			//RECEIVE RESPONSE MSGS

			switch ( responseType ) {
			case ERROR_OR_DATA :
				newmessage = mc.receiveMessage();
				flexLog("Msg Received:" + newmessage.getFormatName() ) ;
				
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					forwardPage = pageError ;
					logERROR( msgError );
				}else if (newmessage.getFormatName().equals( messageFormat.getFormatName() )) {

				// PROCESAR LISTA
									
				msgList = new JBObjList() ;
				String marker = "" ;
				boolean firstTime = true ;
									
				do { 
				
					marker = newmessage.getField("H01FLGMAS").getString() ;
					 
					if (marker.equals("*")) {
						msgList.setShowNext(false);
						break;
					}
					else { 
						msgList.addRow( newmessage );
						if (firstTime) {
							firstTime = false;
						}
										
						if (marker.equals("+")) {
							msgList.setShowNext(true);
							break;
						}
					}
				
					newmessage = mc.receiveMessage();
				} while (true);
					
					forwardPage = pageOK ;
				}else{
					flexLog("Msg expected not Received:" + messageFormat.getFormatName() ) ;
					throw new Exception( "Msg expected not Received" ) ;
				}				
				break;
				
			case ERROR_AND_DATA :
				//RECEIVE ERROR
				newmessage = mc.receiveMessage();
				flexLog("Msg Received:" + newmessage.getFormatName() ) ;
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					forwardPage = pageError ;
					logERROR( msgError );
				}else{
					flexLog("Msg expected ELEERR not Received:" + messageFormat.getFormatName() ) ;
					throw new Exception( "Msg expected ELEERR not Received" ) ;
				}	
				
				//RECEIVE DATA
				newmessage = mc.receiveMessage();
				flexLog("Msg Received:" + newmessage.getFormatName() ) ;
				flexLog( "MSG RECIBIDO:" + newmessage.toString() ) ;
				if (newmessage.getFormatName().equals( messageFormat.getFormatName() )) {
					
				msgList = new JBObjList() ;
				String marker = "" ;
				boolean firstTime = true ;
									
				while (true) {
					marker = newmessage.getField("H01FLGMAS").getString() ;//  messageFormat.getH01FLGMAS();
					if (marker.equals("*")) {
						msgList.setShowNext(false);
						break;
					}
					else {
						msgList.addRow( newmessage );
						if (firstTime) {
							firstTime = false;
						}
										
						if (marker.equals("+")) {
							msgList.setShowNext(true);
							break;
						}
					}
				
					newmessage = mc.receiveMessage();
					flexLog( "OTRO MSG RECIBIDO:" + newmessage.toString() );
				} 
				    ses.setAttribute("appList", msgList);
					forwardPage = pageOK ;
				}else{
					flexLog("Msg expected not Received:" + messageFormat.getFormatName() ) ;
					throw new Exception( "Msg expected not Received" ) ;
				}
				
				if ( !msgError.getERRNUM().equals("0") ) {
					forwardPage = pageError ;
				} 
				
				break;

			default:
				throw new Exception( "Response Type not recognized." ) ;

			}
			
		} catch (Exception e) {
			flexLog("No More MSGs Received") ;
			if (forwardPage == null) {
				msgError.setERRNUM("1") ;
				msgError.setERNU01("-999");
				msgError.setERDS01( e.getMessage() ) ;
				forwardPage = pageError ; 
			}
		}
		
		flexLog("Putting beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("msgList", msgList ) ;
		
		
		flexLog("About to call Page: " + LangPath + forwardPage);
		callPage(LangPath + forwardPage, req, res);
			
		
		
	}
	
	
	public void callPage(
			String s,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse)
			throws IOException, ServletException {

		
		if ( s.indexOf("/servlet/") == -1  ) {
			
			// This is for preventing cache from the browser
			httpservletresponse.setHeader("Pragma", "No-cache");
			httpservletresponse.setDateHeader("Expires", 0);
			httpservletresponse.setHeader("Cache-Control", "no-cache");
			// This is the HTTP Meta tag equivalent
			// <META HTTP-EQUIV="Pragma" CONTENT="No-cache">
			// this is for the proxi server caching
			// httpservletresponse.setHeader("Cache-Control", "private");

			// Third Variant
			getServletConfig().getServletContext().getRequestDispatcher(s).forward(
				httpservletrequest,
				httpservletresponse);
			
		}else{
			
			httpservletresponse.sendRedirect(super.srctx + s.substring( s.indexOf("/servlet/") )  ) ;
			
		}


	}
	
	
	
}
