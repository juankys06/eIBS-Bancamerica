package datapro.eibs.products;

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
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSECD0003 extends datapro.eibs.master.SuperServlet {

	// credit card 
	static final int R_LIST 			= 1;
	static final int A_LIST 			= 2;
	static final int R_INQ				= 3;

	static final int R_ENTER			= 10;

	static final int R_INV_ENTER			= 100;
	static final int R_INV_GEN_LIST 		= 101;
	static final int R_INV_DET_LIST 		= 102;
	static final int A_INV_GEN_LIST 		= 103;
	static final int A_INV_HIS_LIST			= 104;
	static final int R_INV_HIS_LIST			= 105;
	
	static final int R_INV_STOCK_ENTER 		= 111;
	static final int R_INV_STOCK_LIST 		= 112;
	static final int A_INV_STOCK_LIST 		= 113;
			
	protected String LangPath = "S";

	/**
	 * JSECD0003 constructor comment.
	 */
	public JSECD0003() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSECD0003");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0003DSMessage msgList = null;
		JBObjList detList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0003DSMessage) mc.getMessageRecord("ECD0003DS");
			msgList.setH03USERID(user.getH01USR());
			msgList.setH03PROGRM("ECD0003");
			msgList.setH03TIMSYS(getTimeStamp());
			msgList.setH03SCRCOD("01");
			msgList.setH03OPECOD("0015");
			
			try {
				msgList.setE03CDRINI(req.getParameter("E03CDRINI"));			
			} catch (Exception e) {
				msgList.setE03CDRINI("");
			}
			try {
				msgList.setE03CDREND(req.getParameter("E03CDREND"));		
			} catch (Exception e) {
				msgList.setE03CDREND("");
			}			

			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
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
					flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inq_enter.jsp");
					callPage(LangPath + "ECD0003_plastic_inq_enter.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0003DS")) {
					detList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0003DSMessage) newmessage;
						marker = msgList.getH03FLGMAS();

						if (marker.equals("*")) {
								detList.setShowNext(false);
								break;
						} else {
							detList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								detList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("detList", detList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inq_list.jsp");
						callPage(LangPath + "ECD0003_plastic_inq_list.jsp", req, res);
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

	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ESS0030DSMessage msgUser = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try {
			int option = Integer.parseInt(req.getParameter("opt"));

			switch (option) {
				case 1 : // Inquiry
					procReqInquiry(mc, user, req, res, ses);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	
	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0003DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList detList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("INQUIRY");
		detList = (JBObjList) ses.getAttribute("detList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		detList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0003DSMessage) detList.getRecord();
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inq.jsp");
				callPage(LangPath + "ECD0003_plastic_inq.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procReqEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0003DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList detList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inq_enter.jsp");
				callPage(LangPath + "ECD0003_plastic_inq_enter.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procReqEnterInvStock(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0003DSMessage msgCD = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_stock_enter.jsp");
				callPage(LangPath + "ECD0003_plastic_inv_stock_enter.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procReqEnterInv(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0003DSMessage msgCD = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_inq_enter.jsp");
				callPage(LangPath + "ECD0003_plastic_inv_inq_enter.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}
	
	protected void procReqInvListGeneral(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0003DSMessage msgList = null;
		JBObjList genList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0003DSMessage) mc.getMessageRecord("ECD0003DS");
			msgList.setH03USERID(user.getH01USR());
			msgList.setH03PROGRM("ECD0003");
			msgList.setH03TIMSYS(getTimeStamp());
			msgList.setH03SCRCOD("01");
			msgList.setH03OPECOD("0013");
			
			try {
				msgList.setE03CDRTPL(req.getParameter("E03CDRTPL"));			
			} catch (Exception e) {
				msgList.setE03CDRTPL("");
			}
			try {
				msgList.setE03CDRBRD(req.getParameter("E03CDRBRD"));		
			} catch (Exception e) {
				msgList.setE03CDRBRD("");
			}
			try {
				msgList.setE03CDRSTS(req.getParameter("E03CDRSTS"));		
			} catch (Exception e) {
				msgList.setE03CDRSTS("");
			}

			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
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
					flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_gen_list.jsp");
					callPage(LangPath + "ECD0003_plastic_inv_gen_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0003DS")) {
					genList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0003DSMessage) newmessage;
						marker = msgList.getH03FLGMAS();

						if (marker.equals("*")) {
								genList.setShowNext(false);
								break;
						} else {
							genList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								genList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("genList", genList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_gen_list.jsp");
						callPage(LangPath + "ECD0003_plastic_inv_gen_list.jsp", req, res);
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
	
	protected void procReqInvListDetail(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0003DSMessage msgList = null;
		JBObjList detList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0003DSMessage) mc.getMessageRecord("ECD0003DS");
			msgList.setH03USERID(user.getH01USR());
			msgList.setH03PROGRM("ECD0003");
			msgList.setH03TIMSYS(getTimeStamp());
			msgList.setH03SCRCOD("01");
			msgList.setH03OPECOD("0012");
			
			try {
				msgList.setE03CDRTPL(req.getParameter("E03CDRTPL"));			
			} catch (Exception e) {
				msgList.setE03CDRTPL("");
			}
			try {
				msgList.setE03CDRBRD(req.getParameter("E03CDRBRD"));		
			} catch (Exception e) {
				msgList.setE03CDRBRD("");
			}
			try {
				msgList.setE03CDRSTS(req.getParameter("E03CDRSTS"));		
			} catch (Exception e) {
				msgList.setE03CDRSTS("");
			}		

			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
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
					flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_detail_list.jsp");
					callPage(LangPath + "ECD0003_plastic_inv_detail_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0003DS")) {
					detList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0003DSMessage) newmessage;
						marker = msgList.getH03FLGMAS();

						if (marker.equals("*")) {
								detList.setShowNext(false);
								break;
						} else {
							detList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								detList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("detList", detList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_detail_list.jsp");
						callPage(LangPath + "ECD0003_plastic_inv_detail_list.jsp", req, res);
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
	
	protected void procReqInvListStock(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ECD0003DSMessage msgList = null;
		JBObjList stockList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (ECD0003DSMessage) mc.getMessageRecord("ECD0003DS");
			msgList.setH03USERID(user.getH01USR());
			msgList.setH03PROGRM("ECD0003");
			msgList.setH03TIMSYS(getTimeStamp());
			msgList.setH03SCRCOD("01");
			msgList.setH03OPECOD("0020");
			
			try {
				msgList.setE03CDRTPL(req.getParameter("E03CDRTPL"));			
			} catch (Exception e) {
				msgList.setE03CDRTPL("");
			}
			
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
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
					flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_stock_list.jsp");
					callPage(LangPath + "ECD0003_plastic_inv_stock_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else if (newmessage.getFormatName().equals("ECD0003DS")) {
					stockList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true) {
						msgList = (ECD0003DSMessage) newmessage;
						marker = msgList.getH03FLGMAS();

						if (marker.equals("*")) {
								stockList.setShowNext(false);
								break;
						} else {
							stockList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								stockList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("stockList", stockList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

					try {
						flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_stock_list.jsp");
						callPage(LangPath + "ECD0003_plastic_inv_stock_list.jsp", req, res);
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

	protected void procReqInvInqListHistory(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0003DSMessage msgCD = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);
	
			try {
				flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_inq_enter_history.jsp");
				callPage(LangPath + "ECD0003_plastic_inv_inq_enter_history.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procReqInvListHistory(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0003DSMessage msgList = null;
		JBObjList hisList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try {
			msgList = (ECD0003DSMessage) mc.getMessageRecord("ECD0003DS");
			msgList.setH03USERID(user.getH01USR());
			msgList.setH03PROGRM("ECD0003");
			msgList.setH03TIMSYS(getTimeStamp());
			msgList.setH03SCRCOD("01");
			msgList.setH03OPECOD("0014");
			
			try {
				msgList.setE03CDRTPL(req.getParameter("E03CDRTPL"));			
			} catch (Exception e) {
				msgList.setE03CDRTPL("");
			}
			try {
				msgList.setE03CDRBRD(req.getParameter("E03CDRBRD"));		
			} catch (Exception e) {
				msgList.setE03CDRBRD("");
			}
			try {
				msgList.setE03CDRDAE(req.getParameter("E03CDRDAE"));			
				msgList.setE03CDRMOE(req.getParameter("E03CDRMOE"));			
				msgList.setE03CDRYEE(req.getParameter("E03CDRYEE"));			
			} catch (Exception e) {
				msgList.setE03CDRDAE("");
				msgList.setE03CDRMOE("");
				msgList.setE03CDRYEE("");
			}
			try {
				msgList.setE03CDRDAY(req.getParameter("E03CDRDAY"));			
				msgList.setE03CDRMON(req.getParameter("E03CDRMON"));			
				msgList.setE03CDRYEA(req.getParameter("E03CDRYEA"));			
			} catch (Exception e) {
				msgList.setE03CDRDAY("");
				msgList.setE03CDRMON("");
				msgList.setE03CDRYEA("");
			}
			try {
				msgList.setE03CDRSTS(req.getParameter("E03CDRSTS"));		
			} catch (Exception e) {
				msgList.setE03CDRSTS("");
			}
	
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		flexLog("Initializing java beans into the session");
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
					flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_history_list.jsp");
					callPage(LangPath + "ECD0003_plastic_inv_history_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	
			} else if (newmessage.getFormatName().equals("ECD0003DS")) {
					hisList = new JBObjList();
					boolean firstTime = true;
					String marker = "";
	
					while (true) {
						msgList = (ECD0003DSMessage) newmessage;
						marker = msgList.getH03FLGMAS();
	
						if (marker.equals("*")) {
								hisList.setShowNext(false);
								break;
						} else {
							hisList.addRow(msgList);
							if (firstTime) {
								firstTime = false;
							}
							if (marker.equals("+")) {
								hisList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}
	
					flexLog("Putting java beans into the session");
					ses.setAttribute("hisList", hisList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
	
					try {
						flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_history_list.jsp");
						callPage(LangPath + "ECD0003_plastic_inv_history_list.jsp", req, res);
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

	protected void procActionInvListGeneral(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0003DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList genList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		genList = (JBObjList) ses.getAttribute("genList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		genList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0003DSMessage) genList.getRecord();

			try {
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECD0003?SCREEN=102"
					+ "&E03CDRTPL=" + msgCD.getE03CDRTPL()
					+ "&E03CDRBRD=" + msgCD.getE03CDRBRD()
					+ "&E03CDRSTS=" + msgCD.getE03CDRSTS());
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionInvListHistory(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0003DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList hisList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		hisList = (JBObjList) ses.getAttribute("hisList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		hisList.setCurrentRow(row);
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0003DSMessage) hisList.getRecord();
	
			try {
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECD0003?SCREEN=102"
					+ "&E03CDRTPL=" + msgCD.getE03CDRTPL()
					+ "&E03CDRBRD=" + msgCD.getE03CDRBRD()
					+ "&E03CDRSTS=" + msgCD.getE03CDRSTS());
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	protected void procActionInvListStock(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ECD0003DSMessage msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList stockList = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		stockList = (JBObjList) ses.getAttribute("stockList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		stockList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ECD0003DSMessage) stockList.getRecord();
			ses.setAttribute("msgCD", msgCD);

			try {
				flexLog("About to call Page: " + LangPath + "ECD0003_plastic_inv_stock_warning_viewer.jsp");
				callPage(LangPath + "ECD0003_plastic_inv_stock_warning_viewer.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
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

			int screen = R_ENTER;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

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
					case R_ENTER:
						procReqEnter(mc, msgUser, req, res, session);	
					case R_LIST :
						procReqList(mc, msgUser, req, res, session);
						break;											
					case A_LIST :					    
						procActionList(mc, msgUser, req, res, session);
						break;	
					case R_INQ:
						procReqInquiry(mc, msgUser, req, res, session);
						break;
						
					case R_INV_ENTER:
						procReqEnterInv(mc, msgUser, req, res, session);
						break;
					case R_INV_STOCK_ENTER:
						procReqEnterInvStock(mc, msgUser, req, res, session);
						break;
					case R_INV_GEN_LIST:
						procReqInvListGeneral(mc, msgUser, req, res, session);
						break;
					case R_INV_DET_LIST:
						procReqInvListDetail(mc, msgUser, req, res, session);
						break;
					case R_INV_STOCK_LIST:
						procReqInvListStock(mc, msgUser, req, res, session);
						break;
					case A_INV_HIS_LIST:
						procReqInvListHistory(mc, msgUser, req, res, session);
						break;
					case R_INV_HIS_LIST:
						procReqInvInqListHistory(mc, msgUser, req, res, session);
						break;
						
					case A_INV_GEN_LIST:
						procActionInvListGeneral(mc, msgUser, req, res, session);
						break;
					case A_INV_STOCK_LIST:
						procActionInvListStock(mc, msgUser, req, res, session);
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