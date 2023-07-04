package datapro.eibs.products;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ECC008001Message;
import datapro.eibs.beans.ECC010001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSECC010001 extends SuperServlet {

	protected String LangPath = "S";

	private final int REQ_SELECT_CARD = 10; //Pag de Seleccion de Tarjeta

	private final int REQ_CHANGE_PIN = 20; //Pag Cambio de PIN
	
	private final int ACT_CHANGE_PIN = 30; //Cambio de PIN 
	

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

			int screen = 0;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session
						.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc = new MessageContext(new DataInputStream(
							new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s
									.getOutputStream())), "datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
					// Request
					case REQ_SELECT_CARD:
						reqSelectCard(mc, msgUser, req, res, session);
						break;

					case REQ_CHANGE_PIN:
						reqChangePIN(mc, msgUser, req, res, session);
						break;

					case ACT_CHANGE_PIN:
						actChangePIN(mc, msgUser, req, res, session);
						break;

					default:
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath
							+ super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath
						+ super.sckNotRespondPage);
			}

		}

	}

	private void actChangePIN(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses) {

		MessageRecord newmessage = null;
		ECC010001Message msg = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String changePinOption = req.getParameter("OPT") ; 

		// Send Initial data
		try {
			msg = (ECC010001Message) mc.getMessageRecord("ECC010001");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("ECC0100");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			
			msg.setH01OPECOD("0030");
			
			try {
				msg.setE01CCRNUM(req.getParameter("E01CCRNUM")) ;
				msg.setE01CCRPIO(req.getParameter("E01CCRPIO")) ;
				msg.setE01CCRPIN(req.getParameter("E01CCRPIN")) ;
			} catch (Exception e) {
				flexLog("ERROR - INPUT WITHOUT CARD NUMBER OR PIN");
				throw new RuntimeException( e.getMessage() );
			}
			msg.send();
			flexLog("ECC010001 Message Sent");
			flexLog( msg.toString() );
			msg.destroy();

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		
		//RECEIVE ERROR MSG
		
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
		
		
		//userPO.setHeader19( changePinOption ) ;
		
		//flexLog("Change PIN OPTION:" + changePinOption );
		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("userPO", userPO);
		
		
		//RECEIVE DATA MSG
		try {
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ECC010001")) {

				if(IsNotError){

						// ASIGNAR NUEVO PIN
						try {
							flexLog("About to call Page: " + LangPath
									+ "ECC010001_change_pin_enter_card.jsp");
							callPage(LangPath + "ECC010001_change_pin_enter_card.jsp", req,
									res);
							
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
						
					//}
					
				}else{
					
					flexLog("Putting java beans into the session. Error Received");
					ses.setAttribute("error", msgError);
					showERROR( msgError ) ;
				
				
					flexLog("About to call Page: " + LangPath
							+ "ECC010001_change_pin.jsp");
					callPage(LangPath + "ECC010001_change_pin.jsp", req, res);

					
				}
				
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");		
		}
		

	}

	private void reqChangePIN(MessageContext mc, ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECC008001Message msgList = null;
		ELEERRMessage msgError = null;
/*		JBListRec appList = null;
*/		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECC008001Message) mc.getMessageRecord("ECC008001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECC0080");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			try {
				msgList.setE01CCANUM(req.getParameter("E01CCANUM"));
			} catch (Exception e) {
				msgList.setE01CCANUM("0");
			}
			msgList.send();
			msgList.destroy();
			flexLog("ECC008001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath
							+ "ECC010001_change_pin_enter_card.jsp");
					callPage(LangPath + "ECC010001_change_pin_enter_card.jsp", req, res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECC008001")) {

				
				msgList = (ECC008001Message) newmessage;
				userPO.setIdentifier(msgList.getE01CCANUM());
				userPO.setCusNum(msgList.getE01CARCUN());
				userPO.setCusName(msgList.getE01CARNA1());
				userPO.setHeader1(msgList.getE01CCANUM());
				userPO.setHeader2(msgList.getE01CCASTA());
				userPO.setHeader3(msgList.getE01CCADSC());
				userPO.setHeader4(msgList.getE01CCACID());
				userPO.setHeader19("1");
				userPO.setHeader20("");
				userPO.setHeader21("");
				

				flexLog("Putting java beans into the session");
//				ses.setAttribute("appList", appList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath
							+ "ECC010001_change_pin.jsp");
					callPage(LangPath + "ECC010001_change_pin.jsp", req,
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

	private void reqSelectCard(MessageContext mc, ESS0030DSMessage msgUser,
			HttpServletRequest req, HttpServletResponse res, HttpSession session)
			throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			session.setAttribute("error", msgError);
			//			ses.setAttribute("userPO", userPO);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath
					+ "ECC010001_change_pin_enter_card.jsp");
			callPage(LangPath + "ECC010001_change_pin_enter_card.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
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
 