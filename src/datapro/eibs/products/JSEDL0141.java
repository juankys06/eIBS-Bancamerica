package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDL0141 extends datapro.eibs.master.SuperServlet {

	static final int R_AUTHORITATION = 5;
	static final int A_AUTHORITATION = 2;
	static final int R_AUTHORITATION_INQ = 3;
	static final int R_PASSWORD = 1;

	private String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSEDL0141() {
		super();
	}

	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSEDL0141(int logType) {
		super(logType);

	}
	/**
	 * This method was created in VisualAge.
	 */
	private synchronized void procActionAuthoritation(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDL014101Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		JBObjList authoList = null;

		authoList = (JBObjList) ses.getAttribute("authoList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = 0;
		}

		authoList.setCurrentRow(row);
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDL014101Message) authoList.getRecord();
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDL0141");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setE01ACTION(req.getParameter("action"));
			msgList.setE01DEARMK(req.getParameter("reason"));
			msgList.setE01DEANRTE(req.getParameter("E01DEANRTE" + row));
			msgList.setH01OPECOD("0005");
			mc.sendMessage(msgList);
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError = new datapro.eibs.beans.ELEERRMessage();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There is no error				
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.products.JSEDL0141?SCREEN=5");
				} else {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0141_authoritation_list.jsp?ROW="
								+ row);
						res.sendRedirect(super.srctx + 
							LangPath
								+ "EDL0141_authoritation_list.jsp?ROW="
								+ row);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
	/**
	 * This method was created in VisualAge.
	 */
	private synchronized void procReqAuthoritation(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDL014101Message msgList = null;
		JBObjList beanList = null;
		UserPos userPO = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDL014101Message) mc.getMessageRecord("EDL014101");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ESS0090");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD("0001");

			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("EDL014101")) {

				try {
					beanList = new datapro.eibs.beans.JBObjList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";

				while (true) {

					msgList = (EDL014101Message) newmessage;

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

				userPO = new datapro.eibs.beans.UserPos();
				userPO.setPurpose("AUTHORITATION");

				//
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("authoList", beanList);
				ses.setAttribute("error", msgError);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL0141_authoritation_list.jsp");
					callPage(
						LangPath + "EDL0141_authoritation_list.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	private synchronized void procReqAuthoritationInq(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		try {
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			int appCode = Integer.parseInt(req.getParameter("appCode"));
			String accNum = req.getParameter("ACCNUM");
			String typeCode = req.getParameter("typeCode");

			datapro.eibs.products.JOActionRedirect red =
				new datapro.eibs.products.JOActionRedirect(
					ACC_APPROVAL_INQ,
					appCode,
					accNum,
					LangPath,
					ses);
			res.sendRedirect(super.srctx + red.action());
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * service method comment.
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws javax.servlet.ServletException, java.io.IOException {
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

			int screen = R_AUTHORITATION;

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
						case R_PASSWORD :
							procReqPassword(req, res, session);
							break;
						case R_AUTHORITATION :
							procReqAuthoritation(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_AUTHORITATION :
							procActionAuthoritation(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_AUTHORITATION_INQ :
							procReqAuthoritationInq(
								mc,
								msgUser,
								req,
								res,
								session);
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

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	private synchronized void procReqPassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;

		try {
			//mod emat 01/24/2002
			//send draft parameter

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setRedirect(
				"/servlet/datapro.eibs.products.JSEDL0141?SCREEN="
					+ R_AUTHORITATION
					+ (req.getParameter("ACCNUM") == null
						? ""
						: "&ACCNUM=" + req.getParameter("ACCNUM")));
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}