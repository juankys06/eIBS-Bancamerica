package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTPClient;

import datapro.eibs.beans.EDD400101Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEDD4001 extends datapro.eibs.master.SuperServlet {

	// Action 

	protected static final int A_POSITION = 800;

	protected static final int R_REFERENCE_LIST = 100;
	protected static final int R_CODES_LIST = 400;

	protected static final int R_ENTER = 1;
	protected static final int R_NEW = 300;
	protected static final int R_MAINTENANCE = 500;
	protected static final int A_MAINTENANCE = 600;
	protected static final int A_CONFIRM = 700;
	protected static final int LOAD_FILE = 900;

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSEDD4001() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEIE00000");

	}
	/**
	 * This method was created by David Mavilla.
	 * 
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procActionPos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int inptOPT = 0;

		inptOPT = Integer.parseInt(req.getParameter("opt"));

		switch (inptOPT) {
			case 1 : //New
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSEDD4001?SCREEN=300");
				break;
			case 2 : //Maintenance
				procReqMaintenance(mc, user, req, res, ses);
				break;

			case 3 : //Deletion
				procActionProcess(mc, user, req, res, ses);
				break;

			default :
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSEDD4001?SCREEN=500");
				break;
		}
	}

	protected void procReqReferenceList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD400101Message msgList = null;
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
			msgList = (EDD400101Message) mc.getMessageRecord("EDD400101");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD400101");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0010");
			msgList.send();
			msgList.destroy();
			flexLog("EDD400101 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD400101")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String TableTyp = "";
				String chkOfac = "";
				String chkWarn = "";
				int compar = 0;
				int indexRow = 0;
				while (true) {

					msgList = (EDD400101Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (firstTime) {
						firstTime = false;
						chk = "checked";

					} else {
						chk = "";
					}

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
				ses.setAttribute("EDD400101Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4001_comp_enter_list.jsp");
					callPage(
						LangPath + "EDD4001_comp_enter_list.jsp",
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
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected void procReqCodesList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD400101Message msgList = null;
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
			msgList = (EDD400101Message) mc.getMessageRecord("EDD400101");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD4001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");

			try {
				msgList.setE01PAYCIA(req.getParameter("TABLECODE"));
			} catch (Exception e) {
				msgList.setE01PAYCIA(userPO.getHeader6());
			}

			try {
				msgList.setE01PAYCDE(req.getParameter("PROCESSCODE"));
			} catch (Exception e) {
				msgList.setE01PAYCDE(userPO.getHeader7());
			}

			msgList.send();
			msgList.destroy();
			flexLog("EDD400101 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD400101")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String TableTyp = "";
				String chkOfac = "";
				String chkWarn = "";
				int compar = 0;
				int indexRow = 0;
				while (true) {

					msgList = (EDD400101Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (firstTime) {
						firstTime = false;
						chk = "checked";
						userPO.setHeader10(msgList.getE01PAYCIA());
						userPO.setHeader5("400");
						userPO.setHeader6(msgList.getE01PAYCIA());
						userPO.setHeader7(msgList.getE01PAYCDE());
						userPO.setHeader8(msgList.getD01PAYCIA());
						userPO.setHeader9(msgList.getD01PAYCDE());
					} else {
						chk = "";
					}

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
				ses.setAttribute("EDD400101Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4001_comp_accounts_list.jsp");
					callPage(
						LangPath + "EDD4001_comp_accounts_list.jsp",
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
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	protected void procActionConfirm(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD400101Message msgList = null;
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
			msgList = (EDD400101Message) mc.getMessageRecord("EDD400101");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDD4001");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0018");

			try {
				msgList.setE01PAYCIA(req.getParameter("TABLECODE"));
			} catch (Exception e) {
				msgList.setE01PAYCIA(userPO.getHeader6());
			}

			try {
				msgList.setE01PAYCDE(req.getParameter("PROCESSCODE"));
			} catch (Exception e) {
				msgList.setE01PAYCDE(userPO.getHeader7());
			}

			msgList.send();
			msgList.destroy();
			flexLog("EDD400101 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				// showERROR(msgError);
				//beanList.setNoResult(true);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}

		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD400101")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String TableTyp = "";
				String chkOfac = "";
				String chkWarn = "";
				int compar = 0;
				int indexRow = 0;
				while (true) {

					msgList = (EDD400101Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (firstTime) {
						firstTime = false;
						chk = "checked";
						userPO.setHeader10(msgList.getE01PAYCIA());
						userPO.setHeader5("400");
						userPO.setHeader6(msgList.getE01PAYCIA());
						userPO.setHeader7(msgList.getE01PAYCDE());
						userPO.setHeader8(msgList.getD01PAYCIA());
						userPO.setHeader9(msgList.getD01PAYCDE());
					} else {
						chk = "";
					}

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
				ses.setAttribute("EDD400101Help", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4001_confirm_proc.jsp");
					callPage(LangPath + "EDD4001_confirm_proc.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
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
		EDD400101Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT = (EDD400101Message) ses.getAttribute("refCodes");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("ECH011001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01OPECOD("0002");
			//			msgRT.setE02SETRCD(req.getParameter("E02CNORCD"));
			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD400101 Message Sent");
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
				showERROR(msgError);
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

			if (newmessage.getFormatName().equals("EDD400101")) {
				try {
					msgRT = new EDD400101Message();
					flexLog("EDD400101 Message Received");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgRT = (EDD400101Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("refCodes", msgRT);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.params.JSEDD4001?SCREEN=400");
				} else { // There are errors

					String tablecode = userPO.getHeader6();
					String processcode = userPO.getHeader7();

					callPage(getRedirectPage(tablecode, processcode), req, res);

				}
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionProcess(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD400101Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int acctype = 0;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			JBObjList bl = (JBObjList) ses.getAttribute("EDD400101Help");
			int idx = Integer.parseInt(req.getParameter("CURRCODE").trim());
			bl.setCurrentRow(idx);

			msgRT = (EDD400101Message) bl.getRecord();

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgRT.setH01USERID(user.getH01USR());
			msgRT.setH01PROGRM("EDD4001");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01OPECOD("0009");

			try {
				msgRT.setE01PAYCIA(req.getParameter("TABLECODE"));
			} catch (Exception e) {
				msgRT.setE01PAYCIA(userPO.getHeader6());
			}

			try {
				msgRT.setE01PAYCDE(req.getParameter("PROCESSCODE"));
			} catch (Exception e) {
				msgRT.setE01PAYCDE(userPO.getHeader7());
			}

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

			mc.sendMessage(msgRT);
			msgRT.destroy();
			flexLog("EDD400101 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// ojo		

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				try {
					msgError =
						(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
							getClass().getClassLoader(),
							"datapro.eibs.beans.ELEERRMessage");
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				msgRT = null;

				JBObjList bl = (JBObjList) ses.getAttribute("EDD400101Help");
				ses.setAttribute("EDD400101Help", bl);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4001_comp_accounts_list.jsp");
					callPage(
						LangPath + "EDD4001_comp_accounts_list.jsp",
						req,
						res);

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
				res.sendRedirect(
					super.srctx
						+ "/servlet/datapro.eibs.params.JSEDD4001?SCREEN=700");

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
	}

	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD400101Message msgRT = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		msgRT = new EDD400101Message();
		msgRT.setE01PAYCIA(userPO.getHeader10());
		msgRT.setE01PAYCDE(userPO.getHeader7());
		msgRT.setD01PAYCIA(userPO.getHeader8());
		msgRT.setD01PAYCDE(userPO.getHeader9());
		ses.setAttribute("refCodes", msgRT);

		String tablecode = userPO.getHeader6();
		String processcode = userPO.getHeader7();

		callPage(getRedirectPage(tablecode, processcode), req, res);

	}

	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EDD400101Message msgDoc = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Receive Data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("EDD400101Help");
			int idx = Integer.parseInt(req.getParameter("CURRCODE").trim());
			bl.setCurrentRow(idx);

			msgDoc = (EDD400101Message) bl.getRecord();

			userPO.setHeader20("");

			flexLog("Putting java beans into the session");

			ses.setAttribute("refCodes", msgDoc);
			ses.setAttribute("userPO", userPO);

			String tablecode = userPO.getHeader6();
			String processcode = userPO.getHeader7();

			callPage(getRedirectPage(tablecode, processcode), req, res);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	private String getRedirectPage(String tablecode, String processcode) {
		//Screen Code Selection
		String ret = "";

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD4001_comp_account_details.jsp");
			ret = LangPath + "EDD4001_comp_account_details.jsp";
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
		return ret;
	}

	// FUNCTIONS FOR LOADING FORMAT FILESS
    //***********************************************************************************
	protected void loadFormatFile(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
		List fileItemsList)
		throws ServletException, IOException {

		// Validate format parameteres.  If file name is ok, then get Path

		if (ServletFileUpload.isMultipartContent(req)) {
			try {

				// Parse the HTTP request...
				
				//DiskFileItemFactory diskFileItemFactory =				new DiskFileItemFactory();
				//diskFileItemFactory.setSizeThreshold(40960);
				
				/* the unit is bytes */
				//ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
				//List fileItemsList = servletFileUpload.parseRequest(req);

				// process file items
				EDD400101Message msg = (EDD400101Message) mc.getMessageRecord("EDD400101");
				Iterator it = fileItemsList.iterator();
				while (it.hasNext()) {
					FileItem fileItem = (FileItem) it.next();
					if (fileItem.isFormField()) {
						String name = fileItem.getFieldName();
						String value = fileItem.getString();
						try{
							msg.getField(name).setString(value);
						}catch (Exception e){
						}

					} else {
						/* The file item contains an uploaded file */

						// Validate file name
						String fileName = fileItem.getName();

						int index = fileName.lastIndexOf("\\");
						if (index < 0)
							index = fileName.lastIndexOf("/");

						if (index >= 0)
							fileName = fileName.substring(index +1);

						index = fileName.lastIndexOf('.');
						if (index >= 0)
							fileName = fileName.substring(0,index);

                        msg.setF01WKNFMT(fileName);
						String path =
							checkFileName(mc, user, req, res, ses, msg);

						// if name is valid  upload file and start process
						if (path != null) {
							upLoadFile(req, res, ses, fileItem, fileName, path);
							startProcess(mc,user,req,res,ses,msg);
						}

					}
				}

			} catch (Exception fuex) {
			}

		}
		
		callPage(LangPath + "EDD4001_comp_enter_list.jsp", req, res);

	}

	public String checkFileName(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
	     EDD400101Message msg)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		//EDD400101Message msg = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		String path = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String changePinOption = req.getParameter("OPT");

		// Send Initial data
		try {
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EDD4001");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0020");
			

			msg.send();
			msg.destroy();
			flexLog("EDD400101 Message Sent");

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

		//RECEIVE DATA MSG
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD400101")) {

				if (IsNotError) {

					// ASIGNAR NUEVO PIN
					try {
						path = newmessage.getField("F01WKNPTH").getString().trim();
						userPO.setHeader1(path);

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

					//}

				}
			}

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		return path;
	}

	private void upLoadFile(
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
		FileItem fileItem,
		String fileName,
		String path) {

		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {

			/* Get the name attribute value of the <input type="file"> element. */
			String fieldName = fileItem.getFieldName();

			/* Get the size of the uploaded file in bytes. */
			long fileSize = fileItem.getSize();

			/* Get the name of the uploaded file at the client-side. 
			 * Some browsers such as IE 6 include the whole path here (e.g. e:\files\myFile.txt), 
			 * so you may need to extract the file name from the path. 
			 * This information is provided by the client browser, which means you should
			 *  be cautious since it may be a wrong value provided by a malicious user. */
			//String fileName = fileItem.getName();

			/* Get the content type (MIME type) of the uploaded file. 
			 * This information is provided by the client browser, which means you should
			 *  be cautious since it may be a wrong value provided by a malicious user. */
			String contentType = fileItem.getContentType();

			InputStream fileStream = fileItem.getInputStream();

			// Send it by FTP
			// Connect and logon to FTP Server
			PropertyResourceBundle ftpProperties = null;
			String host = "";
			String user = "";
			String pasw = "";

			try {
				ftpProperties =
					(PropertyResourceBundle) PropertyResourceBundle.getBundle(
						"EDD4001");
				host = ftpProperties.getString("ftpHost");
				user = ftpProperties.getString("user");
				pasw = ftpProperties.getString("password");
				userPO.setHeader2(host);
			} catch (Exception e) {
			}

			FTPClient ftp = new FTPClient();

			ftp.connect(host);

			ftp.login(user, pasw);
			System.out.println("Connected to " + host + ".");
			System.out.print(ftp.getReplyString());

			// change path
			ftp.changeWorkingDirectory(path);

			// store the file

			ftp.storeFile(fileName + ".FILE", fileStream);

			//Logout from the FTP Server and disconnect
			ftp.logout();
			ftp.disconnect();
			
			req.setAttribute("Process", "Loaded");

			ses.setAttribute("userPO", userPO);

		} catch (IOException io) {
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void startProcess(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses,
	    EDD400101Message msg)
		throws ServletException, IOException {
			
		MessageRecord newmessage = null;
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
			
			msg.setH01USERID(user.getH01USR());
			msg.setH01PROGRM("EDD4001");
			msg.setH01TIMSYS(getTimeStamp());
			msg.setH01SCRCOD("01");
			msg.setH01OPECOD("0021");

			msg.setF01WKNPTH(userPO.getHeader1());
			msg.setF01WKNDIP(userPO.getHeader2());
			
			msg.send();
			msg.destroy();
			flexLog("EDD400101 Message Sent");

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

			if (newmessage.getFormatName().equals("EDD400101")) {

				if (IsNotError) {

					// ASIGNAR NUEVO PIN
					try {
						

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

					//}

				} else {

					flexLog("Putting java beans into the session. Error Received");
					ses.setAttribute("error", msgError);
					showERROR(msgError);

					
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
    //************************************************************************************
	// FIN DE FUNCIONES DE CARGA DEL FORMATO			

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

			int screen = A_POSITION;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Openning Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
							
                    List items=null;
					try {
						if (ServletFileUpload.isMultipartContent(req)) {
							FileItemFactory factory = new DiskFileItemFactory();
							ServletFileUpload upload =
								new ServletFileUpload(factory);
							//List items = null;
							try {
								items = upload.parseRequest(req);
							} catch (FileUploadException e) {
								e.printStackTrace();
							}
							Iterator itr = items.iterator();
							while (itr.hasNext()) {
								FileItem item = (FileItem) itr.next();
								String name = item.getFieldName();
								String value = item.getString();

								if (item.isFormField()) {
									if (name.equals("SCREEN")){
										screen =  Integer.parseInt(value);
										break;
									}
									  
								}

							}
						} else
							screen =
								Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {

						//Request

						case R_REFERENCE_LIST :
							procReqReferenceList(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_CODES_LIST :
							procReqCodesList(mc, msgUser, req, res, session);
							break;
						case R_NEW :
							procReqNew(mc, msgUser, req, res, session);
							break;
						case R_MAINTENANCE :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;

							// Action
						case A_POSITION :
							procActionPos(mc, msgUser, req, res, session);
							break;
						case A_MAINTENANCE :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_CONFIRM :
							procActionConfirm(mc, msgUser, req, res, session);
							break;

							// END Entering
						case LOAD_FILE :
							loadFormatFile(mc, msgUser, req, res, session,items);
							break;
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
