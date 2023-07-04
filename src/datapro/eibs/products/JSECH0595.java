package datapro.eibs.products;

/**================================================================================
 * Create checkbook supplier File (Developed by Banesco Panamá)
 * Creation date: 08/10/2007
 * @author: Henry Guamantica
 ==================================================================================*/
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class JSECH0595 extends datapro.eibs.master.SuperServlet {

	// Action 
	private ServletConfig config = null;

	protected static final int R_ENTER_FILE = 100;
	protected static final int A_ENTER_FILE = 200;

	protected String LangPath = "S";

	/**
	 * JSECH00595 constructor comment.
	 */
	public JSECH0595() {
		super();
	}

	/**
	 * This method was created by Henry G.
	 */
	public void destroy() {

		flexLog("free resources used by JSECH00595");

	}

	/**
	 * This method was created by Henry G.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}

	protected void procReqEnterFile(
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
					+ "ECH0595_checkbooks_supplier_file.jsp");
			callPage(LangPath + "ECH0595_checkbooks_supplier_file.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void procActionFile(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECH059501Message msgCK = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		//read file
		try {
			msgCK = new ECH059501Message();

			msgCK.setH01USERID(user.getH01USR());
			msgCK.setH01PROGRM("ECH0595");
			msgCK.setH01TIMSYS(getTimeStamp());
			msgCK.setH01OPECOD("0001");
			msgCK.setH01FLGWK1("");
		
			// Receiving Parameter
			try {
				msgCK.setE01REPLACE(req.getParameter("E01REPLACE"));			
			} catch (Exception e) {
				msgCK.setE01REPLACE("");
			}
			
			msgCK.setE01DIA(req.getParameter("E01DIA")!= null?req.getParameter("E01DIA"):"");
			msgCK.setE01MES(req.getParameter("E01MES")!= null?req.getParameter("E01MES"):"");
			msgCK.setE01ANO(req.getParameter("E01ANO")!= null?req.getParameter("E01ANO"):"");
			
			// Send Request to AS400								
			mc.sendMessage(msgCK);
			msgCK.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		
		try {
			//Receive Error
			newmessage = mc.receiveMessage();			
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			
			//Receive Data
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ECH059501")) {
				try {msgCK = (datapro.eibs.beans.ECH059501Message) Beans.instantiate(getClass().getClassLoader(),
								"datapro.eibs.beans.ECH059501Message");
					flexLog("ECH059501 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgCK = (ECH059501Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgCK", msgCK);

				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			
			if (IsNotError) { // There are no errors
				if (!msgCK.getE01EXISTE().equals("Y")) { // If File does not exist
					try {
						flexLog("About to call Page: " + LangPath + "ECH0595_checkbooks_supplier_confirm.jsp");
						callPage(LangPath + "ECH0595_checkbooks_supplier_confirm.jsp",	req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					flexLog("About to call Page: "	+ LangPath	+ "ECH0595_checkbooks_supplier_file.jsp");
					callPage(LangPath + "ECH0595_checkbooks_supplier_file.jsp", req, res);
				}				
			} else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				flexLog("About to call Page: "	+ LangPath	+ "ECH0595_checkbooks_supplier_file.jsp");
				callPage(LangPath + "ECH0595_checkbooks_supplier_file.jsp", req, res);
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
							procReqEnterFile(msgUser, req, res, session);
							break;
						case A_ENTER_FILE :
							procActionFile(mc, msgUser, req, res, session);
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
