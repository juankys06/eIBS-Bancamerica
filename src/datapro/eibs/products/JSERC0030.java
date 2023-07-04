package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSERC0030 extends datapro.eibs.master.SuperServlet {

	protected static final int R_APPROVAL = 100;
	protected static final int A_APPROVAL = 200;
	protected static final int S_APPROVAL = 300;
	protected static final int S_DELETE = 400;

	protected String LangPath = "S";

	/**
	 * JSReportManager constructor comment.
	 */
	public JSERC0030() {
		super();
	}
	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSERC0030(int logType) {
		super(logType);
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
		ERC003002Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ERC003002Message) mc.getMessageRecord("ERC003002");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ERC0030");
			msgList.setH02TIMSYS(getTimeStamp());
			msgList.setE02ACTION(req.getParameter("action"));
			String index = req.getParameter("BTHNUM");
			msgList.setE02RCIBNK(req.getParameter("E02RCIBNK"+index));
			msgList.setE02RCICCY(req.getParameter("E02RCICCY"+index));
			msgList.setE02RCIGLN(req.getParameter("E02RCIGLN"+index));
			msgList.setE02RCIACC(req.getParameter("E02RCIACC"+index));
			msgList.setE02RCIBD1(req.getParameter("E02RCIBD1"+index));
			msgList.setE02RCIBD2(req.getParameter("E02RCIBD2"+index));
			msgList.setE02RCIBD3(req.getParameter("E02RCIBD3"+index));
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
//		Receive Message
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
					callPage(LangPath + "ERC0030_reconc_approval_ent.jsp?refresh=\"refresh\"",
							  req,
							  res);
				 } else {
					 try {
						flexLog(
						   "About to call Page: "
						   + LangPath
						   + "ERC0030_reconc_approval_ent.jsp");
					   callPage(
						   LangPath + "ERC0030_reconc_approval_ent.jsp",
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
		ERC003001Message msgList = null;
		JBList beanList = null;
		UserPos userPO = null;
		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ERC003001Message) mc.getMessageRecord("ERC003001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERC0030");
			msgList.setH01TIMSYS(getTimeStamp());
			//msgList.setH01SCRCOD(req.getParameter("appCode"));
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

			} else if (newmessage.getFormatName().equals("ERC003001")) {
				try {
					beanList = new datapro.eibs.beans.JBList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				boolean firstTime;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String bthNum = req.getParameter("BTHNUM");

				if (bthNum == null)
					firstTime = true;
				else
					firstTime = false;
				int indexRow = 0;
				while (true) {
					msgList = (ERC003001Message) newmessage;

					marker = msgList.getH01FLGMAS();
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							chk = "checked";
						}
						else{
							chk = "";
						} /*else {
							if (msgList.getE01BTHNUM().trim().equals(bthNum))
								chk = "checked";
							else
								chk = "";
						}*/

						myRow = new StringBuffer("<TR>");
						myRow.append(
							"<TD NOWRAP><input type=\"radio\" name=\"BTHNUM\" value=\""
								+ indexRow
								+ "\" "
								+ chk
								+ "></TD>");
						myRow.append("<input type=\"hidden\" name=\"E02RCIBNK"+indexRow+"\" value=\""+msgList.getE01RCIBNK()+"\">");
						myRow.append("<input type=\"hidden\" name=\"E02RCICCY"+indexRow+"\" value=\""+msgList.getE01RCICCY()+"\">");
						myRow.append("<input type=\"hidden\" name=\"E02RCIGLN"+indexRow+"\" value=\""+msgList.getE01RCIGLN()+"\">");
						myRow.append("<input type=\"hidden\" name=\"E02RCIACC"+indexRow+"\" value=\""+msgList.getE01RCIACC()+"\">");
						myRow.append("<input type=\"hidden\" name=\"E02RCIBD1"+indexRow+"\" value=\""+msgList.getE01RCISD1()+"\">");
						myRow.append("<input type=\"hidden\" name=\"E02RCIBD2"+indexRow+"\" value=\""+msgList.getE01RCISD2()+"\">");
						myRow.append("<input type=\"hidden\" name=\"E02RCIBD3"+indexRow+"\" value=\""+msgList.getE01RCISD3()+"\">");
						
								
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01RCISTI()
								+ "', '"
								+ msgList.getE01RCISTF()
								+ "', '"
								+ msgList.getE01RCIBNK()
								+ "', '"
								+ msgList.getE01RCICCY()
								+ "', '"
								+ msgList.getE01RCIGLN()
								+ "', '"
								+ msgList.getE01RCIACC()
								+ "', '"
								+ msgList.getE01RCISD1()
								+ "', '"
								+ msgList.getE01RCISD2()
								+ "', '"
								+ msgList.getE01RCISD3()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCIBNK())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01RCISTI()
								+ "', '"
								+ msgList.getE01RCISTF()
								+ "', '"
								+ msgList.getE01RCIBNK()
								+ "', '"
								+ msgList.getE01RCICCY()
								+ "', '"
								+ msgList.getE01RCIGLN()
								+ "', '"
								+ msgList.getE01RCIACC()
								+ "', '"
								+ msgList.getE01RCISD1()
								+ "', '"
								+ msgList.getE01RCISD2()
								+ "', '"
								+ msgList.getE01RCISD3()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCICCY())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01RCISTI()
								+ "', '"
								+ msgList.getE01RCISTF()
								+ "', '"
								+ msgList.getE01RCIBNK()
								+ "', '"
								+ msgList.getE01RCICCY()
								+ "', '"
								+ msgList.getE01RCIGLN()
								+ "', '"
								+ msgList.getE01RCIACC()
								+ "', '"
								+ msgList.getE01RCISD1()
								+ "', '"
								+ msgList.getE01RCISD2()
								+ "', '"
								+ msgList.getE01RCISD3()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCIGLN())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01RCISTI()
								+ "', '"
								+ msgList.getE01RCISTF()
								+ "', '"
								+ msgList.getE01RCIBNK()
								+ "', '"
								+ msgList.getE01RCICCY()
								+ "', '"
								+ msgList.getE01RCIGLN()
								+ "', '"
								+ msgList.getE01RCIACC()
								+ "', '"
								+ msgList.getE01RCISD1()
								+ "', '"
								+ msgList.getE01RCISD2()
								+ "', '"
								+ msgList.getE01RCISD3()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCIACC())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01RCISTI()
								+ "', '"
								+ msgList.getE01RCISTF()
								+ "', '"
								+ msgList.getE01RCIBNK()
								+ "', '"
								+ msgList.getE01RCICCY()
								+ "', '"
								+ msgList.getE01RCIGLN()
								+ "', '"
								+ msgList.getE01RCIACC()
								+ "', '"
								+ msgList.getE01RCISD1()
								+ "', '"
								+ msgList.getE01RCISD2()
								+ "', '"
								+ msgList.getE01RCISD3()
								+ "')\">"
								+ Util.formatDate(
									msgList.getE01RCISD1(),
									msgList.getE01RCISD2(),
									msgList.getE01RCISD3())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"CENTER\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01RCISTI()
								+ "', '"
								+ msgList.getE01RCISTF()
								+ "', '"
								+ msgList.getE01RCIBNK()
								+ "', '"
								+ msgList.getE01RCICCY()
								+ "', '"
								+ msgList.getE01RCIGLN()
								+ "', '"
								+ msgList.getE01RCIACC()
								+ "', '"
								+ msgList.getE01RCISD1()
								+ "', '"
								+ msgList.getE01RCISD2()
								+ "', '"
								+ msgList.getE01RCISD3()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCISTI())
								+ "</A></TD>");
						myRow.append(
							"<TD NOWRAP ALIGN=\"RIGHT\"><A HREF=\"javascript:showInqApprovalBatch('"
								+ msgList.getE01RCISTI()
								+ "', '"
								+ msgList.getE01RCISTF()
								+ "', '"
								+ msgList.getE01RCIBNK()
								+ "', '"
								+ msgList.getE01RCICCY()
								+ "', '"
								+ msgList.getE01RCIGLN()
								+ "', '"
								+ msgList.getE01RCIACC()
								+ "', '"
								+ msgList.getE01RCISD1()
								+ "', '"
								+ msgList.getE01RCISD2()
								+ "', '"
								+ msgList.getE01RCISD3()
								+ "')\">"
								+ Util.formatCell(msgList.getE01RCISTF())
								+ "</A></TD>");
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
						indexRow++;
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				userPO = new datapro.eibs.beans.UserPos();
				userPO.setPurpose("APPROVAL");
				//if (req.getParameter("appCode").equalsIgnoreCase("CD"))
				//	userPO.setOption("CD");
				//else
				//	userPO.setOption("LN");

				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("appList", beanList);
				ses.setAttribute("error", msgError);

				if (beanList.getNoResult()) {
					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "MISC_no_result.jsp");
						res.sendRedirect(super.srctx + LangPath + "MISC_no_result.jsp");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else {

					try {
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERC0030_reconc_approval_ent.jsp");
						callPage(
							LangPath + "ERC0030_reconc_approval_ent.jsp",
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
	
	protected void procReqMant(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			MessageRecord newmessage = null;
			ERC000001Message msgList = null;
			ERC000001Message msgListSend = new ERC000001Message();
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
				String row = req.getParameter("CURRCODE");
				msgList = (ERC000001Message) mc.getMessageRecord("ERC000001");
				msgList.setH01USERID(user.getH01USR());
				msgList.setH01PROGRM("ERC0000");
				msgList.setH01TIMSYS(getTimeStamp());
				msgList.setH01SCRCOD("01");
				msgList.setH01OPECOD("0002");
				msgList.setH01FLGWK2("M");
				userPO.setBank(req.getParameter("E01RCIBNK"));
				msgList.setE01RCIBNK(req.getParameter("E01RCIBNK"));
				msgList.setE01RCICCY(req.getParameter("E01RCICCY"));
				msgList.setE01RCIGLN(req.getParameter("E01RCIGLN"));
				msgList.setE01RCIACC(req.getParameter("E01RCIACC"));
				msgList.setE01RCISD1(req.getParameter("E01RCISD1"));
				msgList.setE01RCISD2(req.getParameter("E01RCISD2"));
				msgList.setE01RCISD3(req.getParameter("E01RCISD3"));
				msgList.setE01RCISTI(req.getParameter("E01RCISTI"));
				msgList.setE01RCISTF(req.getParameter("E01RCISTF"));
				
				msgListSend.setE01RCIBNK(req.getParameter("E01RCIBNK"));
				msgListSend.setE01RCICCY(req.getParameter("E01RCICCY"));
				msgListSend.setE01RCIGLN(req.getParameter("E01RCIGLN"));
				msgListSend.setE01RCIACC(req.getParameter("E01RCIACC"));
				msgListSend.setE01RCISD1(req.getParameter("E01RCISD1"));
				msgListSend.setE01RCISD2(req.getParameter("E01RCISD2"));
				msgListSend.setE01RCISD3(req.getParameter("E01RCISD3"));
				msgListSend.setE01RCISTI(req.getParameter("E01RCISTI"));
				msgListSend.setE01RCISTF(req.getParameter("E01RCISTF"));	
				msgList.send();
			
				//msgList.destroy();
				flexLog("ERC000001 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			// Receive Data
			try {
				newmessage = mc.receiveMessage();
			
				if (newmessage.getFormatName().equals("ERC000001")) {
					JBObjList beanList = new JBObjList();

					boolean firstTime = true;
					String marker = "";
					ArrayList variableParameters = new ArrayList();
					int total=0;
					String sFinalTotal="";
					while (true) {
					
						msgList = (ERC000001Message) newmessage;
						sFinalTotal=msgList.getE01RCISAF();
						marker = msgList.getH01FLGMAS();
					
						if (marker.equals("*")) {
						
							break;
						} else {
						
							variableParameters.add(msgList.getE01RCIBD1());
							variableParameters.add(msgList.getE01RCIBD2());
							variableParameters.add(msgList.getE01RCIBD3());
							variableParameters.add(msgList.getE01RCICKN());
							variableParameters.add(msgList.getE01RCIAMD());
							variableParameters.add(msgList.getE01RCIAMC());
							variableParameters.add(msgList.getE01RCISAT());
						}
						newmessage = mc.receiveMessage();
						total++;
					}
					msgListSend.setE01RCISAF(sFinalTotal);
				
					ses.setAttribute("msgList",msgListSend);
					ses.setAttribute("variableParameters", variableParameters);
				
					ses.setAttribute("userPO", userPO);
					flexLog("Putting java beans into the session");

					try {
						ses.setAttribute("total", total + "");
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERC0000_reconc_mant_ent.jsp");
						callPage(
							LangPath + "ERC0000_reconc_consult_ent.jsp",
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
	 * This method was created in VisualAge.
	 */
	protected void procReqDelete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

			MessageRecord newmessage = null;
			ELEERRMessage msgError = null;
			ERC003002Message msgList = null;
			UserPos userPO = null;
			boolean IsNotError = false;

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgList = (ERC003002Message) mc.getMessageRecord("ERC003002");
				msgList.setH02USERID(user.getH01USR());
				msgList.setH02PROGRM("ERC0030");
				msgList.setH02TIMSYS(getTimeStamp());
				msgList.setE02ACTION(req.getParameter("action"));
				String index = req.getParameter("BTHNUM");
				msgList.setE02RCIBNK(req.getParameter("E02RCIBNK"+index));
				msgList.setE02RCICCY(req.getParameter("E02RCICCY"+index));
				msgList.setE02RCIGLN(req.getParameter("E02RCIGLN"+index));
				msgList.setE02RCIACC(req.getParameter("E02RCIACC"+index));
				msgList.setE02RCIBD1(req.getParameter("E02RCIBD1"+index));
				msgList.setE02RCIBD2(req.getParameter("E02RCIBD2"+index));
				msgList.setE02RCIBD3(req.getParameter("E02RCIBD3"+index));
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
					callPage(LangPath + "ERC0030_reconc_approval_ent.jsp?refresh=\"refresh\"",
							  req,
							  res);
				 	} else {
					 	try {
							flexLog(
						   "About to call Page: "
						   + LangPath
						   + "ERC0030_reconc_approval_ent.jsp");
					  	 callPage(
						   LangPath + "ERC0030_reconc_approval_ent.jsp",
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

			int screen = R_APPROVAL;

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
						
						case S_APPROVAL :
							procReqMant(mc, msgUser, req, res, session);
							break;
						case R_APPROVAL :
							procReqApproval(mc, msgUser, req, res, session);
							break;
						case A_APPROVAL :
							procActionApproval(mc, msgUser, req, res, session);
							break;
						case S_DELETE :
							procReqDelete(mc, msgUser, req, res, session);
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

	protected static final int R_PASSWORD = 1;

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
			userPO.setRedirect(
				"/servlet/datapro.eibs.products.JSEGL0012?SCREEN="
					+ R_APPROVAL
					+ "&appCode="
					+ req.getParameter("appCode"));
			ses.setAttribute("userPO", userPO);
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.menu.JSESS0030?SCREEN=7");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
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