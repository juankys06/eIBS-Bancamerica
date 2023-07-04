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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.ibm.jvm.io.FileOutputStream;
//import org.apache.xerces.parsers.DOMParser;
//import org.w3c.dom.Node;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD008101Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.JBQualiFile;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSESD0081 extends datapro.eibs.master.SuperServlet {

	protected static final int R_ENTER = 1;
	protected static final int A_ENTER = 2;
	protected static final int A_PROCESS = 4;
	protected static final int A_EXIT = 5;
	protected static final int R_RESPONSE = 3;
	protected static final int A_RESPONSE = 6;
	

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESD0081() {
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
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD008101Message msgClient = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		String CODE = "";

		// Send Initial data
		try {
			msgClient = (ESD008101Message) mc.getMessageRecord("ESD008101");
			msgClient.setH81USR(user.getH01USR());
			msgClient.setH81PGM("ESD008101");
			msgClient.setH81TIM(getTimeStamp());
			msgClient.setH81SCR("01");
			msgClient.setH81OPE("0001");

			try {
				msgClient.setE81CUN(req.getParameter("CUN"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81SSN(req.getParameter("IDN"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81LGT(req.getParameter("TYPE"));
			} catch (Exception e) {
			}

			msgClient.send();
			msgClient.destroy();
			
		

		// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		

		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD008101")) {
				
				msgClient = (ESD008101Message) newmessage;

				userPO.setPurpose("M");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("client", msgClient);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						if (msgClient.getE81LGT().equals("2")) {
							flexLog("About to call Page: "+ LangPath+ "ESD0081_qualifile_personal_basic.jsp");
							callPage(LangPath + "ESD0081_qualifile_personal_basic.jsp",req,res);
						} else {
							flexLog("About to call Page: "+ LangPath+ "ESD0081_corp_personal_basic.jsp");
							callPage(LangPath + "ESD0081_qualifile_corp_basic.jsp",req,res);
						
						}						
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0081_client_both_enter_new_qualifile.jsp");
						callPage(
							LangPath
								+ "ESD0081_client_both_enter_new_qualifile.jsp",
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

	/**
	* This method was created in VisualAge.
	*/
	protected void procActionResponse(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD008101Message msgClient = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		String CODE = "";

		// Send Initial data
		try {
			msgClient = (ESD008101Message) mc.getMessageRecord("ESD008101");
			msgClient.setH81USR(user.getH01USR());
			msgClient.setH81PGM("ESD008101");
			msgClient.setH81TIM(getTimeStamp());
			msgClient.setH81SCR("01");
			msgClient.setH81OPE("0005");

			try {
				msgClient.setE81ACT(req.getParameter("AccountAcceptanceText"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81CUN(req.getParameter("CUSTOMER"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81LGT(req.getParameter("LGT"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81QFS(req.getParameter("ScoreNbr"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81CST(req.getParameter("CreditBureauScoreNbr"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81DBR(req.getParameter("ConsumerDetailReferenceNbr"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81CBI(req.getParameter("CreditBureauId"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81BRF(req.getParameter("CreditBureauReferenceNbr"));
			} catch (Exception e) {
			}

			try {
				msgClient.setE81TTI(req.getParameter("TransactionTrackingId"));
			} catch (Exception e) {
			}

			msgClient.send();
			msgClient.destroy();
			flexLog("EIE011001 Message Sent");
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

			if (newmessage.getFormatName().equals("ESD008101")) {
				try {
					msgClient = new ESD008101Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgClient = (ESD008101Message) newmessage;

				userPO.setPurpose("M");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("client", msgClient);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					if (msgClient.getE81ACT().toUpperCase().equals("OPEN")) {
						res.sendRedirect(
							super.srctx
								+ "/servlet/datapro.eibs.client.JSESD0080?SCREEN=500&E01CUN="
								+ msgClient.getE81CUN());
					}
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0081_qualifile_rejection.jsp");
						callPage(
							LangPath + "ESD0081_qualifile_rejection.jsp",
							req,
							res);
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Exception calling page " + e);
					}

				} else { // There are errors
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESD0081_client_response.jsp");
						callPage(
							LangPath + "ESD0081_client_response.jsp",
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

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ESD008101Message msgPayInst = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgPayInst = new datapro.eibs.beans.ESD008101Message();
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("QUALIFIED");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("client", msgPayInst);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESD0081_client_both_enter_new_qualifile.jsp");
			callPage(
				LangPath + "ESD0081_client_both_enter_new_qualifile.jsp",
				req,
				res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD008101Message msgClient = new ESD008101Message();
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBQualiFile qualiFile = null; 
		boolean IsNotError = false;
		String opeCod = "0002";

		
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		opeCod = "0003";
		
		try{
			qualiFile = (JBQualiFile) ses.getAttribute("qualiFile");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}		
		//get parameters
		java.util.Enumeration enu = msgClient.fieldEnumeration();
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
		
		if(qualiFile != null ){
			
			if (qualiFile.getFinalAction().trim().equals("ACCEPT")) {			
				opeCod = "0002";
			}
			else {
				opeCod = "0003";
			}

			// Send Initial data
			try {
				flexLog("Send Initial Data");
				//msgClient = (ESD008101Message) mc.getMessageRecord("ESD008101");
				msgClient.setH81USR(user.getH01USR());
				msgClient.setH81PGM("ESD0081");
				msgClient.setH81TIM(getTimeStamp());
				msgClient.setH81SCR("01");
				
				msgClient.setH81OPE(opeCod);
				
				msgClient.setE81BRF(qualiFile.getCreditBureauReferenceNbr());
				msgClient.setE81QFS(qualiFile.getScoreNbr());
			
				//qualiFile.getCreditBureauId();
				//qualiFile.getCreditBureauReferenceNbr();
				//qualiFile.getCreditBureauScoreNbr();
				//qualiFile.getScoreNbr();
				//qualiFile.getTransactionTrackingId();
			
				// all the fields here
			

				mc.sendMessage(msgClient);
				msgClient.destroy();
			
		// Receive Error Message
		
				newmessage = mc.receiveMessage();
	
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

		

		// Receive Data
		
				newmessage = mc.receiveMessage();
	
				if (newmessage.getFormatName().equals("ESD008101")) {
					
					msgClient = (ESD008101Message) newmessage;				
	
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("client", msgClient);
	                
	                try {
						byte buf[] = qualiFile.getXmlSource().getBytes();
						String path = this.getServletContext().getRealPath("efunds/xml/");
						OutputStream osQFile = new java.io.FileOutputStream(path + msgClient.getE81CUN() +".xml");
						osQFile.write(buf);
						osQFile.close();
	                } catch (FileNotFoundException e) {
	                } catch (SecurityException s) {
	                }

					if(msgClient.getH81OPE().equals("0002")){
						res.sendRedirect(super.srctx + 
											"/servlet/datapro.eibs.client.JSESD0080?SCREEN=500" + "&E01CUN=" + msgClient.getE81CUN());	
					} else{
						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESD0081_qualifile_personal_basic.jsp");
							callPage(
								LangPath + "ESD0081_qualifile_personal_basic.jsp",
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
		} else {
			userPO.setRedirect("QUALIFILE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("client", msgClient);
			ses.setAttribute("userPO", userPO);
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ESD0081_qualifile_personal_basic.jsp");
				callPage(
					LangPath + "ESD0081_qualifile_personal_basic.jsp",
					req,
					res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
	}

	protected void procReqResponse(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESD008101Message msgClient = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgClient = (ESD008101Message) mc.getMessageRecord("ESD008101");
			msgClient.setH81USR(user.getH01USR());
			msgClient.setH81PGM("ESD0010");
			msgClient.setH81TIM(getTimeStamp());
			msgClient.setH81SCR("01");
			msgClient.setH81OPE("0005");
	
			// all the fields here
			java.util.Enumeration enu = msgClient.fieldEnumeration();
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
	
			msgClient.send();
			msgClient.destroy();
			flexLog("ESD008101 Message Sent");
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
	
			if (newmessage.getFormatName().equals("ESD008101")) {
				try {
					msgClient =
						(
							datapro
								.eibs
								.beans
								.ESD008101Message) Beans
								.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ESD008101Message");
					flexLog("ESD008101 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
	
				msgClient = (ESD008101Message) newmessage;
				// showESD000506(msgPayInst);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("client", msgClient);
	
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ESD0081_qualifile_response.jsp");
					callPage(
						LangPath + "ESD0081_qualifile_response.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqQualiFile(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		JBQualiFile quali = null;
		JBListRec closures = null;
		JBListRec accountActions = null;
		JBListRec products = null;
		ELEERRMessage msgError = null;

		boolean IsNotError = false;

		flexLog("Initializing java beans into the session");
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		//procReqDocInfoXML(ses);
		
		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ESD0081_client_response.jsp");
			callPage(LangPath + "ESD0081_client_response.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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

			int screen = R_ENTER;

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
						// Request

						// Action
						case A_PROCESS :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_EXIT :
							procReqEnterMaint( msgUser, req, res, session);
							break;							
						case A_RESPONSE :
							procActionResponse(mc, msgUser, req, res, session);
							break;
							// END Personal & Corporative 

							// BEGIN Entering
							// Request
						case R_ENTER :
							procReqEnterMaint(msgUser, req, res, session);
							break;
						case R_RESPONSE :
							procReqResponse(mc,msgUser, req, res, session);
							break;
							// Action 
						case A_ENTER :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
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
}