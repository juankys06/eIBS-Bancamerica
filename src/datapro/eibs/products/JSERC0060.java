package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.util.ArrayList;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSERC0060 extends datapro.eibs.master.SuperServlet {

	protected static final int INIT = 100;
	protected static final int RESULT_SEARCH = 200;
	protected static final int SHOW_DETAILS = 300;
	protected static final int EXEC_SEARCH = 400;
	protected static final int VALIDATE = 500;
	protected static final int APPROVAL = 600;
	protected static final int CONFIRM = 700;

	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSERC0060() {
		super();
	}
	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSERC0060(int logType) {
		super(logType);
	}
	
	protected void showSearchPage(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		callPage(LangPath + "ERC0060_adjust_search_criteria.jsp",req,res);
	}
	
	protected void showMessage(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			String deb = req.getParameter("deb");
			String cred = req.getParameter("cred");
			String ok = req.getParameter("ok");
		callPage(LangPath + "ERC0060_confirm_message.jsp?deb="+deb+"&cred="+cred+"&ok="+ok+"",req,res);
	}
	
	protected void showList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
		MessageRecord newmessage = null;
		ERC006001Message msgList = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			msgList = (ERC006001Message) mc.getMessageRecord("ERC006001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERC0060");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setE01RCTCCY(req.getParameter("E01RCTCCY"));
			msgList.setE01RCTGLN(req.getParameter("E01RCTGLN"));
			msgList.setE01RCTACC(req.getParameter("E01RCTACC"));
			msgList.send();
			msgList.destroy();
			flexLog("ERC006001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ERC006001")) {
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
					msgList = (ERC006001Message) newmessage;
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
					if (newmessage.getFormatName().equals("ELEERR")) {
						
						msgError = (ELEERRMessage) newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						if (!IsNotError) { //There is Error
							showERROR(msgError);
							flexLog("Putting java beans into the session");
							ses.setAttribute("error", msgError);
							callPage(LangPath + "ERC0060_adjust_search_criteria.jsp", req, res);
						}
					}
				}
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("glList", beanList);
	
				try {
					flexLog(
					"About to call Page: "
						+ LangPath
						+ "ERC0060_adjust_list_ent.jsp");
					
					callPage(
							LangPath + "ERC0060_adjust_list_ent.jsp",
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
	
	protected void validateList(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
		ERC006001Message msgList = null;
		ERC006001Message msgList1 = (ERC006001Message)req.getSession().getAttribute("msgList");
		req.setAttribute("E01RCTFR1",req.getParameter("E01RCTFR1"));
		req.setAttribute("E01RCTFR2",req.getParameter("E01RCTFR2"));
		req.setAttribute("E01RCTFR3",req.getParameter("E01RCTFR3"));
		req.setAttribute("E01RCTTO1",req.getParameter("E01RCTTO1"));
		req.setAttribute("E01RCTTO2",req.getParameter("E01RCTTO2"));
		req.setAttribute("E01RCTTO3",req.getParameter("E01RCTTO3"));
		req.setAttribute("E01RCTRF1",req.getParameter("E01RCTRF1"));
		req.setAttribute("E01RCTRF2",req.getParameter("E01RCTRF2"));
		req.setAttribute("E01RCTAM1",req.getParameter("E01RCTAM1"));
		req.setAttribute("E01RCTAM2",req.getParameter("E01RCTAM2"));
		System.out.println("validateList "+req.getParameter("E01RCTFR1"));
		System.out.println("validateList "+req.getParameter("E01RCTTO1"));
		System.out.println(req.getParameter("E01RCTFR1"));
		System.out.println(req.getParameter("E01RCTFR2"));
		try {
			msgList = (ERC006001Message) mc.getMessageRecord("ERC006001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERC0060");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0030");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String total = req.getParameter("total");
		ArrayList attributes = new ArrayList();
		for(int i=0;i<Integer.parseInt(total);i++){
			if(req.getParameter("H01FLGWK1"+i)!=null){
				ERC006001Message ms = new ERC006001Message();
				msgList.setE01RCTBNK(msgList1.getE01RCTBNK());
				msgList.setE01RCTCCY(msgList1.getE01RCTCCY());
				msgList.setE01RCTGLN(msgList1.getE01RCTGLN());
				msgList.setE01RCTACC(msgList1.getE01RCTACC());
				msgList.setE01RCTAMD(req.getParameter("E01RCTAMD"+i));
				msgList.setE01RCTAMC(req.getParameter("E01RCTAMC"+i));
				msgList1.setE01RCTBD1(req.getParameter("E01RCTBD1"+i));
				msgList1.setE01RCTBD2(req.getParameter("E01RCTBD2"+i));
				msgList1.setE01RCTBD3(req.getParameter("E01RCTBD3"+i));
				msgList1.setE01RCTCKN(req.getParameter("E01RCTCKN"+i));
				ms.setE01RCTBNK(msgList1.getE01RCTBNK());
				ms.setE01RCTCCY(msgList1.getE01RCTCCY());
				ms.setE01RCTGLN(msgList1.getE01RCTGLN());
				ms.setE01RCTACC(msgList1.getE01RCTACC());
				ms.setE01RCTBD1(req.getParameter("E01RCTBD1"+i));
				ms.setE01RCTBD2(req.getParameter("E01RCTBD2"+i));
				ms.setE01RCTBD3(req.getParameter("E01RCTBD3"+i));
				ms.setE01RCTCKN(req.getParameter("E01RCTCKN"+i));
				attributes.add(ms);
				msgList.send();
				//msgList.destroy();
			}
		}
		ses.setAttribute("attributes",attributes);
		msgList.setH01FLGMAS("*");
		msgList.send();
		//msgList.destroy();
		MessageRecord newmessage = null;
		newmessage = mc.receiveMessage();
		JBObjList beanList = new JBObjList();
		String deb = "";
		String cred = "";
		while (true) {
			msgList = (ERC006001Message) newmessage;
			String marker = msgList.getH01FLGMAS();
			if (marker.equals("*")) {
				deb = msgList.getE01TOTCRE();
				cred = msgList.getE01TOTDEB();
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
		if(deb.equals(cred)){
			callPage(LangPath + "ERC0060_search_list_ent.jsp?balance=\"balance\"&showList=\"show\"&cred="+cred+"&deb="+deb+"&ok=Siok",req,res);
		}
		else{
			flexLog(
					"About to call Page: "
					+ LangPath
					+ "ERC0060_search_list_ent.jsp");
			callPage(
					LangPath + "ERC0060_search_list_ent.jsp?balance=\"balance\"&showList=\"show\"&cred="+cred+"&deb="+deb+"&ok=Nook",req,res);
		}
	}
	
	protected void showDetails(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
		MessageRecord newmessage = null;
		ERC006001Message msgListSent = new ERC006001Message();
		ERC006001Message msgList = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			msgList = (ERC006001Message) mc.getMessageRecord("ERC006001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERC0060");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0020");
			String row = req.getParameter("CURRCODE");
			msgList.setE01RCTBNK(req.getParameter("E01RCTBNK"+row));
			msgList.setE01RCTCCY(req.getParameter("E01RCTCCY"+row));
			msgList.setE01RCTGLN(req.getParameter("E01RCTGLN"+row));
			msgList.setE01RCTACC(req.getParameter("E01RCTACC"+row));
			msgListSent.setE01RCTBNK(req.getParameter("E01RCTBNK"+row));
			msgListSent.setE01RCTCCY(req.getParameter("E01RCTCCY"+row));
			msgListSent.setE01RCTGLN(req.getParameter("E01RCTGLN"+row));
			msgListSent.setE01RCTACC(req.getParameter("E01RCTACC"+row));
			msgList.send();
			msgList.destroy();
			flexLog("ERC006001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ERC006001")) {
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
					msgList = (ERC006001Message) newmessage;
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
				ses.setAttribute("glList", beanList);

				try {
					flexLog(
					"About to call Page: "
						+ LangPath
						+ "ERC0060_search_list_ent.jsp");
					ses.setAttribute("msgList",msgListSent);
					callPage(
							LangPath + "ERC0060_search_list_ent.jsp",
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
	
	protected void confirm(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		MessageRecord newmessage = null;
		ERC006001Message msgList = null;
		
		req.setAttribute("E01RCTFR1",req.getParameter("E01RCTFR1"));
		req.setAttribute("E01RCTFR2",req.getParameter("E01RCTFR2"));
		req.setAttribute("E01RCTFR3",req.getParameter("E01RCTFR3"));
		req.setAttribute("E01RCTTO1",req.getParameter("E01RCTTO1"));
		req.setAttribute("E01RCTTO2",req.getParameter("E01RCTTO2"));
		req.setAttribute("E01RCTTO3",req.getParameter("E01RCTTO3"));
		req.setAttribute("E01RCTRF1",req.getParameter("E01RCTRF1"));
		req.setAttribute("E01RCTRF2",req.getParameter("E01RCTRF2"));
		req.setAttribute("E01RCTAM1",req.getParameter("E01RCTAM1"));
		req.setAttribute("E01RCTAM2",req.getParameter("E01RCTAM2"));
		System.out.println("confirm "+req.getParameter("E01RCTFR1"));
		System.out.println("confirm "+req.getParameter("E01RCTTO1"));
		if(req.getParameter("accept")!=null){
			// Send Initial data
			try {
				ERC006001Message msgList1 = (ERC006001Message)req.getSession().getAttribute("msgList");
				msgList = (ERC006001Message) mc.getMessageRecord("ERC006001");
				msgList.setE01RCTBNK(msgList1.getE01RCTBNK());
				msgList.setE01RCTCCY(msgList1.getE01RCTCCY());
				msgList.setE01RCTGLN(msgList1.getE01RCTGLN());
				msgList.setE01RCTACC(msgList1.getE01RCTACC());
				ArrayList atts = (ArrayList)ses.getAttribute("attributes");
				msgList.setH01USERID(user.getH01USR());
				msgList.setH01PROGRM("ERC0060");
				msgList.setH01TIMSYS(getTimeStamp());
				msgList.setH01SCRCOD("01");
				msgList.setH01OPECOD("0035");
				for(int i=0;i<atts.size();i++){
					ERC006001Message ms = new ERC006001Message();
					ms = (ERC006001Message)atts.get(i);
					msgList.setE01RCTBD1(ms.getE01RCTBD1());
					msgList.setE01RCTBD2(ms.getE01RCTBD2());
					msgList.setE01RCTBD3(ms.getE01RCTBD3());
					msgList.setE01RCTCKN(ms.getE01RCTCKN());
					msgList.setH01FLGMAS(" ");
					msgList.send();
					
				}

				msgList.setH01FLGMAS("*");
				msgList.send();
				//msgList.destroy();
				flexLog("ERC006001 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
			try {
							flexLog(
								"About to call Page: "
								+ LangPath
								+ "ERC0060_search_list_ent.jsp");
							callPage(
								LangPath + "ERC0060_search_list_ent.jsp?conf=ok",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

		}
		else{
			try {
				flexLog(
					"About to call Page: "
					+ LangPath
					+ "ERC0060_search_list_ent.jsp");
				callPage(
					LangPath + "ERC0060_search_list_ent.jsp?conf=ok",
					req,
					res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}

	}	
	protected void showResults(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
			MessageRecord newmessage = null;
			ERC006001Message msgList = null;
			ELEERRMessage msgError = null;
			boolean IsNotError = false;

			try {
				msgError =
					(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
						getClass().getClassLoader(),
						"datapro.eibs.beans.ELEERRMessage");
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			// Send Initial data
			try {
				ERC006001Message msgList1 = (ERC006001Message)req.getSession().getAttribute("msgList");
				msgList = (ERC006001Message) mc.getMessageRecord("ERC006001");
				msgList.setH01USERID(user.getH01USR());
				msgList.setH01PROGRM("ERC0060");
				msgList.setH01TIMSYS(getTimeStamp());
				msgList.setH01SCRCOD("01");
				msgList.setH01OPECOD("0025");
				msgList.setE01RCTBNK(msgList1.getE01RCTBNK());
				msgList.setE01RCTCCY(msgList1.getE01RCTCCY());
				msgList.setE01RCTGLN(msgList1.getE01RCTGLN());
				msgList.setE01RCTACC(msgList1.getE01RCTACC());
				if(req.getParameter("E01RCTFR1")!=null && req.getParameter("E01RCTFR2")!=null &&
					req.getParameter("E01RCTFR3")!=null && req.getParameter("E01RCTTO1")!=null &&
					req.getParameter("E01RCTTO2")!=null && req.getParameter("E01RCTTO3")!=null &&
					req.getParameter("E01RCTRF1")!=null && req.getParameter("E01RCTRF2")!=null &&
					req.getParameter("E01RCTAM1")!=null && req.getParameter("E01RCTAM2")!=null ){
						msgList.setE01RCTFR1(req.getParameter("E01RCTFR1"));
						msgList.setE01RCTFR2(req.getParameter("E01RCTFR2"));
						msgList.setE01RCTFR3(req.getParameter("E01RCTFR3"));
						msgList.setE01RCTTO1(req.getParameter("E01RCTTO1"));
						msgList.setE01RCTTO2(req.getParameter("E01RCTTO2"));
						msgList.setE01RCTTO3(req.getParameter("E01RCTTO3"));
						msgList.setE01RCTRF1(req.getParameter("E01RCTRF1"));
						msgList.setE01RCTRF2(req.getParameter("E01RCTRF2"));
						msgList.setE01RCTAM1(req.getParameter("E01RCTAM1"));
						msgList.setE01RCTAM2(req.getParameter("E01RCTAM2"));
				}
				req.setAttribute("E01RCTFR1",req.getParameter("E01RCTFR1"));
				req.setAttribute("E01RCTFR2",req.getParameter("E01RCTFR2"));
				req.setAttribute("E01RCTFR3",req.getParameter("E01RCTFR3"));
				req.setAttribute("E01RCTTO1",req.getParameter("E01RCTTO1"));
				req.setAttribute("E01RCTTO2",req.getParameter("E01RCTTO2"));
				req.setAttribute("E01RCTTO3",req.getParameter("E01RCTTO3"));
				req.setAttribute("E01RCTRF1",req.getParameter("E01RCTRF1"));
				req.setAttribute("E01RCTRF2",req.getParameter("E01RCTRF2"));
				req.setAttribute("E01RCTAM1",req.getParameter("E01RCTAM1"));
				req.setAttribute("E01RCTAM2",req.getParameter("E01RCTAM2"));
				System.out.println("showResults "+req.getParameter("E01RCTFR1"));
				System.out.println("showResults "+req.getParameter("E01RCTFR2"));
				msgList.send();
				//msgList.destroy();
				flexLog("ERC006001 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			// Receive Data
			try {
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ERC006001")) {
					JBObjList beanList = new JBObjList();
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String TableTyp = "";
					String chkOfac = "";
					String chkWarn = "";
					int compar = 0;
					int indexRow = 0;
					while (true) {
						msgList = (ERC006001Message) newmessage;
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
					ses.setAttribute("glList", beanList);

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERC0060_search_list_ent.jsp");
						callPage(
							LangPath + "ERC0060_search_list_ent.jsp?showList=\"show\"",
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

			int screen = INIT;

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
						
						case INIT :
							showSearchPage(mc, msgUser, req, res, session);
							break;
						case RESULT_SEARCH :
							showList(mc, msgUser, req, res, session);
							break;
						case SHOW_DETAILS :
							showDetails(mc, msgUser, req, res, session);
							break;
						case EXEC_SEARCH :
							showResults(mc, msgUser, req, res, session);
							break;
						case VALIDATE :
							validateList(mc, msgUser, req, res, session);
							break;
						case APPROVAL :
							showMessage(mc, msgUser, req, res, session);
							break;
						case CONFIRM :
							confirm(mc, msgUser, req, res, session);
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