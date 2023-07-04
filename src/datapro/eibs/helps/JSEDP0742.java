package datapro.eibs.helps; 


/**
 * Insert the type's description here.
 * Creation date: (04/25/05 6:08:55 PM)
 * @author: John Andrade
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.sockets.*;

public class JSEDP0742 extends datapro.eibs.master.SuperServlet {

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDP0742() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDP0720");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
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

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, super.iniSocket + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					MessageRecord newmessage = null;
					ELEERRMessage msgError = null;
					EDP074201Message msgList = null; //Sucursales
					JBObjList beanList = null;

					String myOption = "";
					String myFlag = "";

					UserPos userPO = null;
					userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");

					try {

						flexLog("Send Initial Data Branch");
						msgList = (EDP074201Message) mc.getMessageRecord("EDP074201");
						msgList.setH01USERID(msgUser.getH01USR());
						msgList.setH01PROGRM("EDP0742");
						msgList.setH01TIMSYS(getTimeStamp());
						msgList.setH01OPECOD("0015");
						msgList.send();
						msgList.destroy();
					} catch (Exception ex) {
						ex.printStackTrace();
						//flexLog("Error: " + ex);
						throw new RuntimeException("Socket Communication Error Branch");
					}

					try {
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("EDP074201")) {
							beanList = new JBObjList();
							String marker = "";
							while (true) {

								msgList = (EDP074201Message) newmessage;
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

						} else
							flexLog("Message " + newmessage.getFormatName() + " received.");

						flexLog("Putting java beans into the session Branch");
						session.setAttribute("EDP0742Help", beanList);
						session.setAttribute("error", msgError);
						session.setAttribute("userPO", userPO);

					} catch (Exception ex) {
						ex.printStackTrace();
						//flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error Branch");
					}

					/***********************************************/
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EWD_EDP0742_help_message.jsp");
						callPage(LangPath + "EWD_EDP0742_help_message.jsp", req, res);
					} catch (Exception ex) {
						flexLog("Exception calling page " + ex);
					}
					/***********************************************/

				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} 

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
			}
			finally {
					s.close();
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