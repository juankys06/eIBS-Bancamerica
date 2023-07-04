package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSESI0000 extends datapro.eibs.master.SuperServlet {

	// Options
	protected static final int SEARCH = 0;
	protected static final int SHOW_INFO = 1;
	protected static final int SHOW_DIRECT_CREDITS = 2;
	protected static final int SHOW_WARRANTIES = 3;
	protected static final int SHOW_INDIRECT_CREDITS = 4;
	protected static final int SHOW_CREDIT_CARDS = 5;
	protected static final int SHOW_MORTAGES = 6;

	// Pages
	protected static final String SEARCH_PAGE = "ESI0000_con_search.jsp";
	protected static final String DIRECT_CREDITS_PAGE =
		"ESI0002_Direct_Credits.jsp";
	protected static final String WARRANTIES_PAGE = "ESI0003_Warranties.jsp";
	protected static final String INDIRECT_CREDITS_PAGE =
		"ESI0004_Indirect_Credits.jsp";
	protected static final String CREDIT_CARDS_PAGE =
		"ESI0001_Credit_Cards.jsp";
	protected static final String MORTAGES_PAGE = "ESI0001_Mortages.jsp";

	protected String LangPath = "S";



	/**
	 * JSECLI001 constructor comment.
	 */
	public JSESI0000() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	private boolean isValidRif(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		MessageRecord newmessage = null;
		ESI000001Message msg = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = true;
		String id = "";
		String idType = null;
		String monthProcess = null;
		String yearProcess = null;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		if (userPO.getHeader6().equals("ESI0000.Valid"))
		  return true;
		

		// Send Initial data
		try {
			msg = (ESI000001Message) mc.getMessageRecord("ESI000001Message");
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("ESI0000");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0015");

			// all the fields here
			java.util.Enumeration enu = msg.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value =
						req.getParameter(field.getTag()).toUpperCase().trim();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}

			msg.send();
			msg.destroy();
			flexLog("ESI000001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//Receive data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				ses.setAttribute("error", msgError);
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			if (newmessage.getFormatName().equals("ESI000001")) {
				msg = (ESI000001Message) newmessage;
				try {
					flexLog("filling UserPO Objects Attributes");
					// fill UserPO object attributes..
					userPO.setHeader1(msg.getE01WTIPOI());
					userPO.setHeader2(msg.getE01WRIF());
					userPO.setHeader3(msg.getE01WMES());
					userPO.setHeader4(msg.getE01WANO());
					userPO.setHeader5(msg.getE01WNOMBR());
					userPO.setHeader6("ESI0000.Valid");

				} catch (Exception e) {
					flexLog("Exception loading RIF " + e);
					IsNotError = false;
				}

			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
				IsNotError = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}
		if (!IsNotError)
			try {
				callPage(LangPath + "ESI0000_con_search.jsp", req, res);
			} catch (Exception e) {
			}

		return !IsNotError;

	}

	/**
	 * Lista la informacion de los creditos directos del cliente.
	 */
	protected void listDirectCredits(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESI000002Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ESI000002Message) mc.getMessageRecord("ESI000002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ESI000002");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setH02SCRCOD("01");
			msgList.setE02WRIF(userPO.getHeader3()); //ID del cliente
			msgList.setE02WTIPOI(userPO.getHeader1()); //Tipo ID
			//msgList.setH02OPECOD("0015");
			msgList.send();
			msgList.destroy();
			flexLog("ESI000002 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				ses.setAttribute("error", msgError);
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName());

			if (newmessage.getFormatName().equals("ESI000002")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String chk = "";
				//var for ofac status
				//var for Warning status
				while (true) {

					msgList = (ESI000002Message) newmessage;
					marker = msgList.getE02INDOPE();

					//marker = msgList.getE02CCDOPE();

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
				ses.setAttribute("ESI000002Help", beanList);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			//send page back
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ESI0000_Direct_Credit_list.jsp");
				callPage(LangPath + "ESI0000_Direct_Credit_list.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	
	/**
	 *  Muestra la pagina de busqueda cuyo criterio es la 
	 * identificacion del cliente
	 *  
	 */
	protected void showSearchPage(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("DV");
			userPO.setPurpose("INQUIRY");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
		}

		try {
			flexLog("About to call Page: " + LangPath + SEARCH_PAGE);
			callPage(LangPath + SEARCH_PAGE, req, res);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
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
				flexLog("Error: " + e);
			}
		} else {

			int screen = SEARCH;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Get the path from the user profile
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
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(LangPath + super.sckNotOpenPage);
					return;
				}

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {

					//entering options
					case SEARCH : //  Show the search page
						showSearchPage(msgUser, req, res, session);
						break;
					case SHOW_INFO :
						if (isValidRif(mc, msgUser, req, res, session))
							listDirectCredits(mc, msgUser, req, res, session);
					case SHOW_DIRECT_CREDITS :
						listDirectCredits(mc, msgUser, req, res, session);
						break;
					default :
						res.sendRedirect(LangPath + super.devPage);
						break;
				}

				try {
					s.close();
				} catch (Exception e) {
					flexLog("Error: " + e);
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(LangPath + super.sckNotRespondPage);
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
