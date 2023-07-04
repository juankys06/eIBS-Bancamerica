package datapro.eibs.params;

/**
 * Insert the type's description here.
 * Creation date: (11/06/05 3:09:07 PM)
 * @author: R.Quimper
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDD4005 extends datapro.eibs.master.SuperServlet {

	protected static final int A_LIST = 100;
	protected static final int A_DELFILE = 200;
	protected static final int A_DELFLD = 201;
	protected static final int A_NEW = 300;
	protected static final int R_NEW_FIELD = 301;
	protected static final int A_MAINT = 400;
	protected static final int A_MAINT_FIELD = 401;
	protected static final int R_LIST = 500;

	protected static final int R_MAINT = 800;
	protected static final int R_MAINT_FIELD = 801;
	protected static final int DET_LIST = 600;

	protected String LangPath = "S";

	/**
	 * JSEEJ0030 constructor comment.
	 */
	public JSEDD4005() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECB0030");

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
	protected void procReqSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ECB003001Message msgSearch = new ECB003001Message();
		UserPos userPO = new UserPos();

		ses.setAttribute("msgMT", msgSearch);
		ses.setAttribute("userPO", userPO);

		try {

			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ECB0030_params_officer_enter_inq.jsp");
			callPage(
				LangPath + "ECB0030_params_officer_enter_inq.jsp",
				req,
				res);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD400501Message msgSearch = null;
		EDD400501Message msgList = null;

		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD400501Message) mc.getMessageRecord("EDD400501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD4005");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0015");

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD400501 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			} else {
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

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD4005_params_files_list.jsp");
						callPage(
							LangPath + "EDD4005_params_files_list.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				} else {
					flexLog(
						"Message " + newmessage.getFormatName() + " received.");
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
	protected void procReqDetList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD400501Message msgSearch = null;
		EDD400501Message msgList = null;

		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		int posi = 0;
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD400501Message) mc.getMessageRecord("EDD400501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD4005");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0016");

			//	read file id
			try {
				msgSearch.setE01DDFFIL(req.getParameter("E01DDFFIL"));
				userPO.setHeader1(req.getParameter("E01DDFFIL"));
			} catch (Exception e) {
			}

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD400501 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			} else
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
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD4005_params_fields_list.jsp");
						callPage(
							LangPath + "ECB0030_params_fields_list.jsp",
							req,
							res);

					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD4005_params_fields_list.jsp");
						callPage(
							LangPath + "EDD4005_params_fields_list.jsp",
							req,
							res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				}
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/*
	 *  Prepare a new Field Format record to insert
	 */
	protected void procReqNewField(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		try {
			String E01DDFFIL = req.getParameter("E01DDFFIL");
			EDD400501Message msgField =
				(EDD400501Message) mc.getMessageRecord("EDD400501");
			msgField.setE01DDFFIL(E01DDFFIL);
			ses.setAttribute("msgField", msgField);

			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDD4005_params_files_new.jsp");
			callPage(
				LangPath
					+ "EDD4005_params_fields_new.jsp?E01DDFFIL"
					+ E01DDFFIL,
				req,
				res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD400501Message msgSearch = null;
		EDD400501Message msgList = null;

		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD400501Message) mc.getMessageRecord("EDD400501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD4005");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0002");

			// read file id
			try {
				msgSearch.setE01DDFFIL(req.getParameter("E01DDFFIL"));
			} catch (Exception e) {
			}

			//msgSearch.setE01DDFFIL(user.getH01USR());

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD400501 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			if (IsNotError) {
				try {
					newmessage = (EDD400501Message) mc.receiveMessage();
					ses.setAttribute("msgEje", newmessage);
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4005_params_files_maint.jsp");
					callPage(
						LangPath + "EDD4005_params_files_maint.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4005_params_files_maint.jsp");
					callPage(
						LangPath + "EDD4005_params_files_maint.jsp",
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
	protected void procFieldInfo(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD400501Message msgSearch = null;
		EDD400501Message msgList = null;

		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD400501Message) mc.getMessageRecord("EDD400501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD4005");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0003");

			// read file id
			try {
				msgSearch.setE01DDFFIL(req.getParameter("E01DDFFIL"));
				msgSearch.setE01DDFDS2(req.getParameter("E01DDFDS2"));
			} catch (Exception e) {
			}

			//msgSearch.setE01DDFFIL(user.getH01USR());

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD400501 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			if (IsNotError) {
				try {
					newmessage = (EDD400501Message) mc.receiveMessage();
					ses.setAttribute("msgField", newmessage);
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4005_params_files_maint.jsp");
					callPage(
						LangPath + "EDD4005_params_fields_maint.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4005_params_files_maint.jsp");
					callPage(
						LangPath + "EDD4005_params_fields_list.jsp",
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
	protected void procDelFile(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD400501Message msgSearch = null;
		EDD400501Message msgList = null;

		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD400501Message) mc.getMessageRecord("EDD400501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD4005");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0009");

			// read file id
			try {
				msgSearch.setE01DDFFIL(req.getParameter("ROW"));// Row=E01DDFFIL value
				
			} catch (Exception e) {
			}

			//msgSearch.setE01DDFFIL(user.getH01USR());

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD400501 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			if (IsNotError) {
				try {
					newmessage = (EDD400501Message) mc.receiveMessage();
					ses.setAttribute("msgField", newmessage);
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.params.JSEDD4005?SCREEN=100");

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4005_params_files_list.jsp");
					callPage(
						LangPath + "EDD4005_params_files_list.jsp",
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
	protected void procDelField(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD400501Message msgSearch = null;
		EDD400501Message msgList = null;

		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgSearch = (EDD400501Message) mc.getMessageRecord("EDD400501");
			msgSearch.setH01USERID(user.getH01USR());
			msgSearch.setH01PROGRM("EDD4005");
			msgSearch.setH01TIMSYS(getTimeStamp());
			msgSearch.setH01SCRCOD("01");
			msgSearch.setH01OPECOD("0010");

			// read file id
			try {
				msgSearch.setE01DDFFIL(req.getParameter("E01DDFFIL"));
				msgSearch.setE01DDFDS2(req.getParameter("CURRENTROW"));
								//E01DDFDS2
			} catch (Exception e) {
			}

			//msgSearch.setE01DDFFIL(user.getH01USR());

			msgSearch.send();
			msgSearch.destroy();
			flexLog("EDD400501 Message Sent");

			// Receive Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {

				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			if (IsNotError) {
				try {
					newmessage = (EDD400501Message) mc.receiveMessage();
					ses.setAttribute("msgField", newmessage);
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(
						super.srctx
							+ "/servlet/datapro.eibs.params.JSEDD4005?SCREEN=600&E01DDFFIL="
							+ ((EDD400501Message) newmessage).getE01DDFFIL());

				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD4005_params_fields_list.jsp");
					callPage(
						LangPath + "EDD4005_params_fields_list.jsp",
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
	protected void procActionNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EDD400501Message msgEje = new EDD400501Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgEje = (EDD400501Message) mc.getMessageRecord("EDD400501");
			msgEje.setH01USERID(user.getH01USR());
			msgEje.setH01PROGRM("EDD4005");
			msgEje.setH01TIMSYS(getTimeStamp());
			msgEje.setH01OPECOD("0001");

			// all the fields here
			java.util.Enumeration enu = msgEje.fieldEnumeration();
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
			msgEje.send();
			msgEje.destroy();

			// Receive Error Message

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

			}
			flexLog("Message " + newmessage.getFormatName() + " received.");
			// Receive Data
			if (IsNotError) {

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("EDD400501")) {

					msgEje = (EDD400501Message) newmessage;

					if (IsNotError) {
						ses.setAttribute("userPO", userPO);
						res.setContentType("text/html");
						PrintWriter out = res.getWriter();
						String href =
							req.getContextPath()
								+ "/pages/s/MISC_search_wait.jsp?URL='"
								+ req.getContextPath()
								+ "/servlet/datapro.eibs.params.JSEDD4005?SCREEN=100'";
						out.println("<HTML>");
						out.println("<HEAD>");
						out.println("<TITLE>Close</TITLE>");
						out.println("</HEAD>");
						out.println("<BODY>");
						out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
						out.println(
							"		top.opener.location.href = \""
								+ href
								+ "\";");
						out.println("		top.close();");
						out.println("</SCRIPT>");
						out.println("<P>Close it!!!</P>");
						out.println("</BODY>");
						out.println("</HTML>");
						out.close();
					} else {
						flexLog("Putting java beans into the session");
						ses.setAttribute("error", msgError);
						ses.setAttribute("msgEje1", msgEje);

						try {
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EDD4005_params_files_new.jsp");
							callPage(
								LangPath + "EDD4005_params_files_new.jsp",
								req,
								res);

						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} else
					flexLog(
						"Message " + newmessage.getFormatName() + " received.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EDD400501Message msgMT = new EDD400501Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMT = (EDD400501Message) mc.getMessageRecord("EDD400501");
			msgMT.setH01USERID(user.getH01USR());
			msgMT.setH01PROGRM("EDD4005");
			msgMT.setH01TIMSYS(getTimeStamp());
			msgMT.setH01OPECOD("0005");

			// all the fields here
			java.util.Enumeration enu = msgMT.fieldEnumeration();
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

			msgMT.send();
			msgMT.destroy();

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

			if (newmessage.getFormatName().equals("EDD400501")) {

				msgMT = (EDD400501Message) newmessage;

				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href =
						req.getContextPath()
							+ "/pages/s/MISC_search_wait.jsp?URL='"
							+ req.getContextPath()
							+ "/servlet/datapro.eibs.params.JSEDD4005?SCREEN=100'";
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println(
						"		top.opener.location.href = \"" + href + "\";");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMT1", msgMT);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD4005_params_files_maint.jsp");
						callPage(
							LangPath + "EDD4005_params_files_maint.jsp",
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
			flexLog("Exception calling page " + e);
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procSaveField(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		EDD400501Message msgMT = new EDD400501Message();
		MessageRecord newmessage = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		ELEERRMessage msgError = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgMT = (EDD400501Message) mc.getMessageRecord("EDD400501");
			msgMT.setH01USERID(user.getH01USR());
			msgMT.setH01PROGRM("EDD4005");
			msgMT.setH01TIMSYS(getTimeStamp());
			msgMT.setH01OPECOD("0017");

			// all the fields here
			java.util.Enumeration enu = msgMT.fieldEnumeration();
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

			msgMT.send();
			msgMT.destroy();

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

			if (newmessage.getFormatName().equals("EDD400501")) {

				msgMT = (EDD400501Message) newmessage;

				if (IsNotError) {
					ses.setAttribute("userPO", userPO);
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					String href =
						req.getContextPath()
							+ "/pages/s/MISC_search_wait.jsp?URL='"
							+ req.getContextPath()
							+ "/servlet/datapro.eibs.params.JSEDD4005?SCREEN=100'";
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println(
						"		top.opener.location.href = \"" + href + "\";");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();
				} else {
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgMT1", msgMT);
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDD4005_params_files_maint.jsp");
						callPage(
							LangPath + "EDD4005_params_files_maint.jsp",
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

			int screen = R_LIST;

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

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						case A_DELFILE :
							procDelFile(mc, msgUser, req, res, session);
							break;
						case A_DELFLD :
							procDelField(mc, msgUser, req, res, session);
							break;
						case A_NEW :
							procActionNew(mc, msgUser, req, res, session);
							break;
						case A_MAINT :
							procActionMaintenance(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_MAINT_FIELD :
							procSaveField(mc, msgUser, req, res, session);
							break;
						case A_LIST :
							procReqList(mc, msgUser, req, res, session);
							break;
						case R_LIST :
							procReqSearch(msgUser, req, res, session);
							break;
						case R_MAINT :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case R_MAINT_FIELD :
							procFieldInfo(mc, msgUser, req, res, session);
							break;
						case DET_LIST :
							procReqDetList(mc, msgUser, req, res, session);
							break;
						case R_NEW_FIELD :
							procReqNewField(mc, msgUser, req, res, session);
							break;
						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
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