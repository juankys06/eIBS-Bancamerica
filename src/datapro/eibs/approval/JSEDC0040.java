package datapro.eibs.approval;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;

import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;

import datapro.eibs.master.SuperServlet;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class JSEDC0040 extends SuperServlet {

	Socket s = null;
	MessageContext mc = null;
	MessageRecord message = null;
	ESS0030DSMessage user = null;
	ELEERRMessage msgError = null;
	JBObjList jbList = null;
	UserPos userPO = null;
	
	String LangPath = "e/";

	protected static final int R_APPROVAL				= 5;
	protected static final int A_DELETE					= 4;
	protected static final int A_APPROVAL				= 2;
	protected static final int R_APPROVAL_INQ			= 3;
	protected static final int R_PASSWORD				= 1;
	
	protected static final int R_COMMISSIONS_INQ		= 6;
	protected static final int R_CODES_INQ				= 7;
	protected static final int R_SPECIAL_INST_INQ		= 8;
	

	public JSEDC0040() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		try {
			if (s != null) s.close();
			flexLog("free resources used by JSEDC0040");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		if (session == null) {
			try {
				res.setContentType("text/html");
				super.printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {
			int screen = -1;

			user = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
			// Here we should get the path from the user profile
			LangPath = super.rootPath + user.getE01LAN() + "/";
			
			userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
			
			try {
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
				mc =
					new MessageContext(
						new DataInputStream(new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");
						
				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
					flexLog("Screen  Number: " + screen);
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}
				
				String PageToCall = "";

				switch (screen)	{
					case R_PASSWORD :
						procReqPassword(req, res, screen);
						break;
					case R_APPROVAL :
						procReqApproval(req, res, screen);
						break;
					case A_APPROVAL :
						procActionApproval(req, res, screen);
						break;
					case A_DELETE :
						procActionDelete(req, res, screen);
						break;
					case R_APPROVAL_INQ :
						procReqApprovalInq(req, res, screen);
						break;
					case R_COMMISSIONS_INQ :
						procReqCommissionsInq(req, res, screen);
						break;
					case R_CODES_INQ :
						procReqSpecialCodesInq(req, res, screen);
						break;
					case R_SPECIAL_INST_INQ :
						procReqSpecialInstInq(req, res, screen);
						break;

					default :
						PageToCall = "MISC_not_available.jsp";
						callPage(PageToCall, req, res);
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			} finally {
				if (s != null) s.close();
				flexLog("Socket used by JSEDC0040 closed.");
			}
		}	
	}	

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqSpecialInstInq(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
		
			String PageToCall = "";
			String apcode = userPO.getType();

			ESD000005Message msg = (ESD000005Message) msgHandle.initMessage("ESD000005", user.getH01USR(), "0002");
			msg.setH05USR(user.getH01USR());
			msg.setH05PGM("EDC0000");
			msg.setH05TIM(getTimeStamp());
			msg.setH05SCR("01");
			msg.setH05OPE("0002");
			msg.setE05ACC(userPO.getAccNum());
			msg.setE05TYP("3");
			initTransaction(String.valueOf(screen), "INQUIRY", "");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ESD000005Message) msgHandle.receiveMessage();
			
			if (apcode.equals("51")) {
				PageToCall = "EDC0040_doc_special_inst.jsp";
			} else {
				PageToCall = "EDC0040_sing_special_inst.jsp";
			}		
		
			putDataInSession(session, "dcInst", msg);
			callPage(PageToCall, req, res);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqSpecialCodesInq(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
		
			String PageToCall = "";
			String apcode = userPO.getType();

			ESD000002Message msg = (ESD000002Message) msgHandle.initMessage("ESD000002", user.getH01USR(), "0002");
			msg.setH02USR(user.getH01USR());
			msg.setH02PGM("EDC0000");
			msg.setH02TIM(getTimeStamp());
			msg.setH02SCR("01");
			msg.setH02OPE("0002");
			msg.setE02ACC(userPO.getAccNum());
			initTransaction(String.valueOf(screen), "INQUIRY", "");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			boolean isNotError = msgError.getERRNUM().equals("0");
			msg = (ESD000002Message) msgHandle.receiveMessage();
			
			if (apcode.equals("51")) {
				PageToCall = "EDC0040_doc_codes.jsp";
			} else {
				PageToCall = "EDC0040_sing_codes.jsp";
			}		
		
			putDataInSession(session, "dcCodes", msg);
			callPage(PageToCall, req, res);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqCommissionsInq(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
		
			String PageToCall = "";
			String apcode = userPO.getType();

			if (apcode.equals("51")) {
				EDC000002Message msg = (EDC000002Message) msgHandle.initMessage("EDC000002", user.getH01USR(), "0002");
				msg.setH02SCRCOD("01");
				try {
					msg.setE02DCMACC(userPO.getAccNum());
					msg.setE02DCMPRO(userPO.getProdCode());
				} catch (Exception e) {
				}
				initTransaction(String.valueOf(screen), "INQUIRY", "");
				msgHandle.sendMessage(msg);
				msgError = msgHandle.receiveErrorMessage();
				boolean isNotError = msgError.getERRNUM().equals("0");
				msg = (EDC000002Message) msgHandle.receiveMessage();
				PageToCall = "EDC0040_doc_commissions.jsp";
				putDataInSession(session, "dcNew2", msg);
			} else {
				EDC001002Message msg = (EDC001002Message) msgHandle.initMessage("EDC001002", user.getH01USR(), "0002");
				msg.setH02SCRCOD("01");
				try {
					msg.setE02DCMACC(userPO.getAccNum());
					msg.setE02DCMPRO(userPO.getProdCode());
				} catch (Exception e) {
				}
				initTransaction(String.valueOf(screen), "INQUIRY", "");
				msgHandle.sendMessage(msg);
				msgError = msgHandle.receiveErrorMessage();
				boolean isNotError = msgError.getERRNUM().equals("0");
				msg = (EDC001002Message) msgHandle.receiveMessage();
				PageToCall = "EDC0040_sing_commissions.jsp";
				putDataInSession(session, "dcNew2", msg);
			}
			callPage(PageToCall, req, res);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqApprovalInq(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			
			String PageToCall = "EDC0040_approval_list.jsp";
			String apcode = null;
			if (req.getParameter("E01DCMACD") != null) {
				apcode = req.getParameter("E01DCMACD");
			} else {
				apcode = userPO.getType();
			}
			if (apcode.equals("51")) {
				EDC000001Message msg = (EDC000001Message) msgHandle.initMessage("EDC000001", user.getH01USR(), "0002");
				msg.setH01SCRCOD("01");
				if (req.getParameter("E01DCMACC") != null) {
					msg.setE01DCMACC(req.getParameter("E01DCMACC"));
				} else {
					msg.setE01DCMACC(userPO.getAccNum());
				}
				initTransaction(String.valueOf(screen), "INQUIRY", "");
				msgHandle.sendMessage(msg);
				msgError = msgHandle.receiveErrorMessage();
				boolean isNotError = msgError.getERRNUM().equals("0");
				msg = (EDC000001Message) msgHandle.receiveMessage();
				if (isNotError) {
					userPO.setAccNum(msg.getE01DCMACC());
					userPO.setBank(msg.getE01DCMBNK());
					userPO.setProdCode(msg.getE01DCMPRO());
					userPO.setType("51");
					PageToCall = "EDC0040_doc_approval_inq.jsp";
				} 
				putDataInSession(session, "dcNew", msg);
			} else {
				EDC001001Message msg = (EDC001001Message) msgHandle.initMessage("EDC001001", user.getH01USR(), "0002");
				msg.setH01SCRCOD("01");
				if (req.getParameter("E01DCMACC") != null) {
					msg.setE01DCMACC(req.getParameter("E01DCMACC"));
				} else {
					msg.setE01DCMACC(userPO.getAccNum());
				}
				initTransaction(String.valueOf(screen), "INQUIRY", "");
				msgHandle.sendMessage(msg);
				msgError = msgHandle.receiveErrorMessage();
				boolean isNotError = msgError.getERRNUM().equals("0");
				msg = (EDC001001Message) msgHandle.receiveMessage();
				if (isNotError) {
					userPO.setAccNum(msg.getE01DCMACC());
					userPO.setBank(msg.getE01DCMBNK());
					userPO.setProdCode(msg.getE01DCMPRO());
					userPO.setType("50");
					PageToCall = "EDC0040_sing_approval_inq.jsp";
				}
				putDataInSession(session, "dcNew", msg);
			}
			callPage(PageToCall, req, res);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionDelete(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC004002Message msg = (EDC004002Message) msgHandle.initMessage("EDC004002", user.getH01USR(), "D");
			msg.setE02DCMACC(req.getParameter("E01DCMACC"));
			msg.setE02ACTION("D");
			msg.setE02MSGTXT(req.getParameter("reason"));
			initTransaction(String.valueOf(screen), "DELETE", "");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			putDataInSession(session, "", null);
			procReqApproval(req, res, screen);
			return;
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procActionApproval(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			String PageToCall = "error_viewer.jsp";
			EDC004002Message msg = (EDC004002Message) msgHandle.initMessage("EDC004002", user.getH01USR(), "A");
			msg.setE02DCMACC(req.getParameter("E01DCMACC"));
			msg.setE02ACTION("A");
			if (req.getParameter("reason") != null)
				msg.setE02MSGTXT(req.getParameter("reason"));
			initTransaction(String.valueOf(screen), "APPROVAL", "");
			msgHandle.sendMessage(msg);
			msgError = msgHandle.receiveErrorMessage();
			putDataInSession(session, "", null);
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				procReqApproval(req, res, screen);
			} else {
				PageToCall = "EDC0040_approval_list.jsp";
				callPage(PageToCall, req, res);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqApproval(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		try {
			MessageContextHandler msgHandle = new MessageContextHandler(mc);
			EDC004001Message msg = (EDC004001Message) msgHandle.initMessage("EDC004001", user.getH01USR(), "");
			initTransaction(String.valueOf(screen), "APPROVAL", "");
			msgHandle.sendMessage(msg);
			String PageToCall = "error_viewer.jsp";
			msgError = new ELEERRMessage();
			jbList = msgHandle.receiveMessageList("EDC004001", "H01FLGMAS", msgError);
			boolean isNotError = msgError.getERRNUM().equals("0");
			if (isNotError) {
				if (jbList.getNoResult()) {
					PageToCall = "MISC_no_result.jsp";
				} else {
					PageToCall = "EDC0040_approval_list.jsp";
				}
			}
			putDataInSession(session, "", null);
			callPage(PageToCall, req, res);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param screen
	 */
	private void procReqPassword(HttpServletRequest req, HttpServletResponse res, int screen) throws IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		initTransaction(String.valueOf(screen), "APPROVAL", "");
		userPO.setRedirect("/servlet/datapro.eibs.approval.JSEDC0040?SCREEN=" + R_APPROVAL + (req.getParameter("ACCNUM") == null ? "" : "&ACCNUM=" + req.getParameter("ACCNUM")));
		putDataInSession(session, "", null);
		res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");
		return;
	}

	private void initTransaction(String optMenu, String purpose, String number) {
		try {
			userPO.setOption(optMenu);
			userPO.setPurpose(purpose);
			if (!number.equals(""))	userPO.setIdentifier(number);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		try {
		} catch (Exception ex) {
			flexLog("Error getting userPO from session: " + ex);
		}
	}
	
	public void callPage(String page, HttpServletRequest req, HttpServletResponse res) {
		try {
			super.callPage(LangPath + page, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e.toString() + e.getMessage());
		}
		return; 
	}
	
	private void putDataInSession(HttpSession session, String msg_name, MessageRecord msg) {
		try {
			flexLog("Putting java beans into the session");

			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			if (jbList != null) session.setAttribute("appList", jbList);
			if (msg != null) session.setAttribute(msg_name, msg);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
	
}