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

import datapro.eibs.beans.ECH030601Message;
import datapro.eibs.beans.ECH030602Message;
import datapro.eibs.beans.ECH030603Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.sockets.MessageField;

public class JSECH0306 extends datapro.eibs.master.SuperServlet {

	protected static final int R_PASSWORD = 1;
	protected static final int A_CHKB = 2;
	protected static final int R_LIST_CHKB = 100;
	protected static final int A_LIST_CHKB = 200;	
    protected static final int R_APPROVAL = 300;
    protected static final int A_APPROVAL = 400;
    
	protected String LangPath = "S";

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH030603Message msgBean = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
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

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgBean = (ECH030603Message) mc.getMessageRecord("ECH030603");
			msgBean.setH03USERID(user.getH01USR());
			msgBean.setH03PROGRM("ECH0306");
			msgBean.setH03TIMSYS(getTimeStamp());
			msgBean.setE03ACTION(req.getParameter("action"));
			msgBean.setE03MSGTXT(req.getParameter("reason"));
			msgBean.setE03NUMSOL(req.getParameter("NUMSOL"));
			msgBean.send();
			msgBean.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
	try
	{
	  	newmessage = mc.receiveMessage();
	  
	  	if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
		  	}
			msgError = (ELEERRMessage)newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			if (IsNotError) { // There is no error
				ses.removeAttribute("chkbList");
				res.sendRedirect(super.srctx +"/servlet/datapro.eibs.products.JSECH0306?SCREEN=300");
			}
			else {
				try {
					 flexLog("About to call Page: " + LangPath + "ECH0306_approval_list.jsp");
					 callPage(LangPath + "ECH0306_approval_list.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}		
	  	}
	}
	catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	
	}

	protected void procReqListChkBook(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH030601Message msgList = null;
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
			msgList = (ECH030601Message) mc.getMessageRecord("ECH030601");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECH0306");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD("0010");
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

				msgError = (ELEERRMessage)newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
				 	flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 	callPage(LangPath + "error_viewer.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}


	  		}
	  		else if (newmessage.getFormatName().equals("ECH030601")) {

				try {
					beanList = new JBObjList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";

				while (true) {

					msgList = (ECH030601Message) newmessage;
					msgList.setHandler(null);
					marker = msgList.getH01FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						beanList.addRow(msgList);
					}
					newmessage = mc.receiveMessage();
				}
				//
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("chkbList", beanList);
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "ECH0306_chb_list.jsp");
					callPage(LangPath + "ECH0306_chb_list.jsp",req,res);
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
	protected void procActionListChkBook(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		UserPos userPO = null;
		ELEERRMessage msgError = new ELEERRMessage();
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		ECH030601Message msgCHKB = null;
		JBObjList beanList = null;
		
		int opt;
		int row;
		
		opt = Integer.parseInt(req.getParameter("opt"));
		
		switch (opt) {
			case 1 : //new
					msgCHKB = new ECH030601Message();
					userPO.setPurpose("NEW"); 
					break;
			case 2 : //maintenance
					userPO.setPurpose("MAINTENANCE");
					beanList = (JBObjList) ses.getAttribute("chkbList");
					row = Integer.parseInt(req.getParameter("actRow"));
					beanList.setCurrentRow(row);
					msgCHKB = (ECH030601Message) beanList.getRecord();
					break;
		}	

		flexLog("Putting java beans into the session");
		ses.setAttribute("userPO", userPO);
		ses.setAttribute("msgCHKB", msgCHKB);
		ses.setAttribute("error", msgError);
				
		try {
			flexLog("About to call Page: " + LangPath + "ECH0306_chb_req_basic.jsp");
			callPage(LangPath + "ECH0306_chb_req_basic.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
				

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionChkBook(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH030601Message msgCHKB = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		String opcode ="";
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		if (userPO.getPurpose().equals("NEW")) opcode = "0001"; else opcode = "0005";
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCHKB = (ECH030601Message) mc.getMessageRecord("ECH030601");
			msgCHKB.setH01USERID(user.getH01USR());
			msgCHKB.setH01PROGRM("ECH0306");
			msgCHKB.setH01TIMSYS(getTimeStamp());
			msgCHKB.setH01OPECOD(opcode);
			
			// all the fields here
			java.util.Enumeration enu = msgCHKB.fieldEnumeration();
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
			
			msgCHKB.send();
			msgCHKB.destroy();
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

			if (newmessage.getFormatName().equals("ECH030601")) {
				
				msgCHKB = (ECH030601Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("msgCHKB", msgCHKB);
				
				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ECH0306_chb_req_confirm.jsp");
					    callPage(LangPath + "ECH0306_chb_req_confirm.jsp",req,res);
					} catch (Exception e) {
						//flexLog("Exception calling page " + e);
					}
				} else {
				    ses.setAttribute("error", msgError);
					try {
						flexLog("About to call Page: " + LangPath + "ECH0306_chb_req_basic.jsp");
						callPage(LangPath + "ECH0306_chb_req_basic.jsp", req, res);
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
	protected void procReqApproval(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ECH030602Message msgList = null;
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
			msgList = (ECH030602Message) mc.getMessageRecord("ECH030602");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ECH0306");
			msgList.setH02TIMSYS(getTimeStamp());
			
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

				msgError = (ELEERRMessage)newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

				try {
				 	flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
				 	callPage(LangPath + "error_viewer.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}


	  		}
	  		else if (newmessage.getFormatName().equals("ECH030602")) {

				try {
					beanList = new JBObjList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";

				while (true) {

					msgList = (ECH030602Message) newmessage;

					marker = msgList.getH02FLGMAS();

					if (marker.equals("*")) {
						break;
					} else {
						beanList.addRow(msgList);
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

				if (beanList.getNoResult()) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "MISC_no_result.jsp");
						res.sendRedirect(super.srctx +LangPath + "MISC_no_result.jsp");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ECH0306_approval_list.jsp");
						callPage(
							LangPath + "ECH0306_approval_list.jsp",
							req,
							res);
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

			int screen = R_LIST_CHKB;

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
					case R_LIST_CHKB :
						procReqListChkBook(mc,msgUser, req, res, session);
						break;
					case A_LIST_CHKB :
						procActionListChkBook(mc, msgUser, req, res, session);
						break;
					case A_CHKB :
						procActionChkBook(mc, msgUser, req, res, session);
						break;
					case R_APPROVAL :
						procReqApproval(mc,msgUser, req, res, session);
						break;
					case A_APPROVAL :
						procActionApproval(mc, msgUser, req, res, session);
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
		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");			
			userPO.setRedirect("/servlet/datapro.eibs.products.JSECH0306?SCREEN=" + R_APPROVAL);			
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}