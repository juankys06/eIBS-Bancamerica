package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ECH032501Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSECH0325 extends datapro.eibs.master.SuperServlet {

	protected static final int R_PASSWORD = 1;
	protected static final int R_ENTER_BRN = 2;
	protected static final int A_ENTER_BRN = 3;
	protected static final int R_RECEP_LIST = 4;
	protected static final int A_RECEP_LIST = 5;
	

	protected String LangPath = "S";

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionReceptList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH032501Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		JBObjList chkbList = null;

		chkbList = (JBObjList) ses.getAttribute("chkbList");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = 0;
		}

		chkbList.setCurrentRow(row);
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ECH032501Message) chkbList.getRecord();
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECH0325");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD("0005");
			msgList.setE01CHMACT("S");
			mc.sendMessage(msgList);
			//msgList.destroy();
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

			if (newmessage.getFormatName().equals("ECH032501")) {

				msgList = (ECH032501Message) newmessage;
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There is no error				
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.products.JSECH0325?SCREEN=3&E01SELBNK="
							+ msgList.getE01SELBNK()
							+ "&E01SELBRN="
							+ msgList.getE01SELBRN());
				} else {
					try {
						flexLog(
							"About to call Page: " + LangPath + "ECH0325_chb_recept_list.jsp?ROW=" + row);
						res.sendRedirect(super.srctx + LangPath + "ECH0325_chb_recept_list.jsp?ROW=" + row);
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

	protected void procReqEnterBranch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		//ECH032001Message msgCHKB = null;
		UserPos userPO = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;


		try {
			userPO = new UserPos();
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

		userPO.setPurpose("MAINTENANCE");
		userPO.setOption(req.getParameter("OPT"));
		//ses.setAttribute("msgCHKB", msgCHKB);
		ses.setAttribute("userPO", userPO);

		try {
			flexLog("About to call Page: " + LangPath + "ECH0325_chb_enter_brn.jsp");
			callPage(LangPath + "ECH0325_chb_enter_brn.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterBranch(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH032501Message msgList = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ECH032501Message) mc.getMessageRecord("ECH032501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECH0325");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD("0001");
			msgList.setH01FLGWK3(userPO.getOption());
			
			try {
				msgList.setE01SELBNK(req.getParameter("E01SELBNK"));
			} catch (Exception e) {
			}
			try {
				msgList.setE01SELBRN(req.getParameter("E01SELBRN"));
			} catch (Exception e) {
			}
			try {
				msgList.setE01SELFD1(req.getParameter("E01SELFD1"));
			} catch (Exception e) {
			}
			try {
				msgList.setE01SELFD2(req.getParameter("E01SELFD2"));
			} catch (Exception e) {
			}
			try {
				msgList.setE01SELFD3(req.getParameter("E01SELFD3"));
			} catch (Exception e) {
			}
			try {
				msgList.setE01SELID1(req.getParameter("E01SELID1"));
			} catch (Exception e) {
			}
			try {
				msgList.setE01SELID2(req.getParameter("E01SELID2"));
			} catch (Exception e) {
			}
			try {
				msgList.setE01SELID3(req.getParameter("E01SELID3"));
			} catch (Exception e) {
			}
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

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

			if (newmessage.getFormatName().equals("ECH032501")) {

				try {
					beanList = new JBObjList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";

				while (true) {

					msgList = (ECH032501Message) newmessage;
					msgList.setHandler(null);
					marker = msgList.getE01OPECDE();

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

				//userPO = new UserPos();
				//userPO.setPurpose("RECEP_CHKB");

				//
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("chkbList", beanList);
				ses.setAttribute("error", msgError);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ECH0325_chb_recept_list.jsp");
						callPage(LangPath + "ECH0325_chb_recept_list.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					try {
						flexLog("About to call Page: " + LangPath + "ECH0325_chb_enter_brn.jsp");
						callPage(LangPath + "ECH0325_chb_enter_brn.jsp", req, res);
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

			int screen = R_RECEP_LIST;

			try {
				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
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
					case R_ENTER_BRN :
						procReqEnterBranch(msgUser, req, res, session);
						break;
					case A_ENTER_BRN :
						procActionEnterBranch(mc, msgUser, req, res, session);
						break;
					case A_RECEP_LIST :
						procActionReceptList(mc, msgUser, req, res, session);
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
				}
				finally {
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
	protected void procReqPassword(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		String opt;
		String acc;
		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			opt = req.getParameter("OPTION");
			acc = req.getParameter("ACCNUM") == null ? "" : "&ACCNUM=" + req.getParameter("ACCNUM");
			
			if (opt.equals("1")) {
				opt = "E";
			} else {
				opt = "R";
			}	
			userPO.setRedirect(
					"/servlet/datapro.eibs.products.JSECH0325?SCREEN="
						+ R_ENTER_BRN
						+ acc
						+ "&OPT=" + opt);
			
				
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}