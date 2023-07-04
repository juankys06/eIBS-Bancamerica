package datapro.eibs.params;

/**================================================================================
 * External Transfer File Interface
 * Creation date: 10/26/2008
 * @author: R.Quimper 
 ==================================================================================*/
import java.io.*;
//import java.net.*;
//import java.beans.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import datapro.eibs.beans.*;
//import datapro.eibs.master.Util;
//import datapro.eibs.sockets.*;
import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

import datapro.eibs.beans.EDD400501Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.EDD400601Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
//import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;


public class JSEDD4006 extends datapro.eibs.master.SuperServlet {

	// Action 
	private ServletConfig config = null;

	protected static final int R_ENTER_FILE = 100;
	protected static final int A_ENTER_FILE = 200;
	protected static final int A_CONFIRM = 300;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDD4006() {
		super();
	}

	/**
	 * This method was created by Henry G.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDD4006");

	}

	/**
	 * This method was created by Henry G.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}

	protected void procReqEnterFileName(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD4006_externa_transfer_file.jsp");
			callPage(LangPath + "EDD4006_externa_transfer_file.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionFileName(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD400601Message msgFL = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		boolean FirstT = false;
		String FileType = "";
			
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
			com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(0);
	// Get Parameter from page using smart upload function
			FileType = mySmartUpload.getRequest().getParameter("OPT");
			
			StringReader sr = new StringReader(myFile.getContentString());
			LineNumberReader lnr = new LineNumberReader(sr);
			String line = "";			

			msgFL = new EDD400601Message();

			// Read text file									
			line = lnr.readLine();
			while (line != null) {
				msgFL.setH01USERID(user.getH01USR());
				msgFL.setH01PROGRM("EDD4006");
				msgFL.setH01TIMSYS(getTimeStamp());
				msgFL.setH01OPECOD("0001");
				msgFL.setH01FLGWK3("");
				msgFL.setH01FLGWK2(" ");
				if (!FirstT) {
					msgFL.setH01FLGWK2("C");	// Clear File First Time
					FirstT = true;
				}				
				msgFL.setE01DATA(line);			// Text Line
				mc.sendMessage(msgFL);			// Send Information to host
				msgFL.destroy();

				line = lnr.readLine();
			} // End Of Loop

			lnr.close();
			sr.close();

			//		res.sendRedirect(
			//			super.srctx
			//				+ "/servlet/datapro.eibs.params.JSEFE0224?SCREEN=300");

			//

			try {
				msgFL = new EDD400601Message();
				msgFL.setH01USERID(user.getH01USR());
				msgFL.setH01PROGRM("EDD4006");
				msgFL.setH01TIMSYS(getTimeStamp());
				msgFL.setH01OPECOD("0001");
				msgFL.setH01FLGWK3("*");
				msgFL.setH01FLGWK1(FileType);
				msgFL.setE01DATA("");
			// Send Second Information
				flexLog("Record 3 : Send Last Record Indicator = " + msgFL.getH01FLGWK1());
			//	msgFL.send();
				mc.sendMessage(msgFL);
				msgFL.destroy();
				
				// Receive Error Message
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						if (!IsNotError) { //There are errors
							try {
								flexLog("Putting java beans into the session");
								ses.setAttribute("error", msgError);

								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD4006_externa_transfer_file.jsp");
								callPage(
									LangPath
										+ "EDD4006_externa_transfer_file.jsp",
									req,
									res);

							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						} else {
							ses.setAttribute("msgFL", msgFL);
							try {
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD4006_externa_transfer_confirm.jsp");
								callPage(
									LangPath
										+ "EDD4006_externa_transfer_confirm.jsp",
									req,
									res);

							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}
					} else {
						ses.setAttribute("msgFL", msgFL);
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EFE0224_bloomberg_transfer_confirm.jsp");
							callPage(
								LangPath
									+ "EFE0224_bloomberg_transfer_confirm.jsp",
								req,
								res);

						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

					//			msgFL.destroy();

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					throw new RuntimeException("Socket Communication Error");
				}
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			//

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
	protected void procActionFileDescrip(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			MessageRecord newmessage = null;
			ELEERRMessage msgError = null;
			EDD400501Message msgFL = null;
			EDD400501Message msgList = null;
			
			JBObjList beanList = null;
			UserPos userPO = null;
			boolean IsNotError = false;

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			int posi = 0;
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgFL = (EDD400501Message) mc.getMessageRecord("EDD400501");
				msgFL.setH01USERID(user.getH01USR());
				msgFL.setH01PROGRM("EDD4005");
				msgFL.setH01TIMSYS(getTimeStamp());
				msgFL.setH01SCRCOD("01");
				msgFL.setH01OPECOD("0016");

			
				msgFL.send();
				msgFL.destroy();
				flexLog("EDD400501 Message Sent");
			
				// Receive Message
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {

					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					ses.setAttribute("error", msgError);
				}
				else
					flexLog("Message " + newmessage.getFormatName() + " received.");
			
									
			    if (newmessage.getFormatName().equals("EDD400501")) {
					
				
			
			    	beanList = new JBObjList();
					String marker = "";
					
					while (true) {

						msgList = (EDD400501Message) newmessage;
										
						marker = msgList.getH01FLGMAS();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							beanList.addRow(msgList);
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

									
					flexLog("Putting java beans into the session");
					ses.setAttribute("mtList", beanList);				
					ses.setAttribute("userPO", userPO);
					
					if (IsNotError) {
						try {
							flexLog("About to call Page: " + LangPath + "EDD4005_params_files_list.jsp");
							callPage(LangPath + "ECB0030_params_files_list.jsp", req, res);						
					
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
					else {
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);				
						try {
							flexLog("About to call Page: " + LangPath + "EDD4005_params_files_list.jsp");
							callPage(LangPath + "EDD4005_params_files_list.jsp", req, res);						
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				}
				else {
					flexLog("Message " + newmessage.getFormatName() + " received.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
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

			int screen = A_ENTER_FILE;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {

						//Request
						case R_ENTER_FILE :
							procReqEnterFileName(msgUser, req, res, session);
							break;
						case A_ENTER_FILE :
							procActionFileDescrip(mc, msgUser, req, res, session);
							procActionFileName(mc, msgUser, req, res, session);
							break;
							// END Entering

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
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
