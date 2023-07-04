package datapro.eibs.products;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import java.beans.Beans;
import java.io.*;
import java.net.Socket;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class JSECH0580 extends SuperServlet {

    protected static final int A_RT_INF_CHEQUERA = 404;
    protected final int A_RT_ENTER_INF_CHEQUERA = 405;
    protected String LangPath;

    public JSECH0580() {
        LangPath = "s";
    }

    public void destroy() {
        flexLog("free resources used by JSECH0765");
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Socket s = null;
		MessageContext mc = null;    	
        HttpSession session = null;
        session = req.getSession(false);
		ESS0030DSMessage msgUser = null;
        if(session == null) {
            try {
                res.setContentType("text/html");
                printLogInAgain(res.getWriter());
            }
            catch(Exception e) {
                e.printStackTrace();
                flexLog("Exception ocurred. Exception = " + e);
            }
        } else {
            int screen = 100;
			flexLog("Opennig Socket Connection");
			s = new Socket(SuperServlet.hostIP, SuperServlet.iniSocket + 1);
			s.setSoTimeout(SuperServlet.sckTimeOut);
			mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
            try {
				msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
				LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
                try {
                    screen = Integer.parseInt(req.getParameter("SCREEN"));
                }
                catch(Exception e) {
                    flexLog("Screen set to default value");
                }
                switch(screen) {
                case 100: 
					flexLog("About to call Page: " + LangPath + "ECH0580_chb_enter_anul.jsp");
					callPage(LangPath + "ECH0580_chb_enter_anul.jsp", req, res);
                    break;
				case 200:
					ReqListarChequerasCta(mc, msgUser, req, res, session);
					break;
				case 300:
  			        ReqListarCheques(mc, msgUser, req, res, session);
				    break;
				case 400:    
					ActAnull(mc, msgUser, req, res, session);
					break;
                default:
                    res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.devPage);
                    break;
                }    

            }catch(Exception e) {
				e.printStackTrace();
				int sck = SuperServlet.iniSocket + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotOpenPage);
			}finally {
				s.close();
			}
        }
    }
    	    
	private void ReqListarChequerasCta(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
		MessageRecord newmessage = null;
		ECH056501Message msgList = null;
		ECH056503Message msgHeader = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = (ELEERRMessage)Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
		}
		catch(Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (UserPos)ses.getAttribute("userPO");

		try {
			flexLog("Send Initial Data");
			msgList = (ECH056501Message)mc.getMessageRecord("ECH056501");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECH0565");
			msgList.setH01TIMSYS(SuperServlet.getTimeStamp());
			msgList.setH01SCRCOD("01");
			try {
				msgList.setE01CHMACC(req.getParameter("E01CHMACC"));
			}
			catch(Exception e) {
				msgList.setE01CHMACC(userPO.getIdentifier());
			}
			msgList.send();
			msgList.destroy();
			flexLog("ECH056501 Message Sent");
		}
		catch(Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		try {
			newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				msgError.setHandler(null);
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
			}
			newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ECH056503")) {
				msgHeader = (ECH056503Message)newmessage;
				msgHeader.setHandler(null);
			}
			newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ECH056501")) {
				String chk = "";
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				beanList = new JBList();
				int indexRow = 0;
				do {
					msgList = (ECH056501Message)newmessage;
					marker = msgList.getH01FLGMAS();
					if(marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					chk = msgList.getE01CHMNCB().trim();
					myFlag = "";
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP>" + Util.formatDate(msgList.getE01CHMRQ1(), msgList.getE01CHMRQ2(), msgList.getE01CHMRQ3()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatDate(msgList.getE01CHMAC1(), msgList.getE01CHMAC2(), msgList.getE01CHMAC3()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(chk) + "</A></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01CHMSTS()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01CHMNTC()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01CHMICK()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getE01CHMFCK()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01STSSOL()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01STSRCV()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01STSENV()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01STSSUC()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE01STSENT()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><input type=\"radio\" name=\"CHKBCORR\" checked value=\"" + indexRow + " \" ></TD>");					
					myRow.append("<td nowrap><INPUT TYPE=HIDDEN NAME=ACC" + indexRow + " value=" + msgList.getE01CHMACC() + ">");
					myRow.append("<td nowrap><INPUT TYPE=HIDDEN NAME=NCB" + indexRow + " value=" + msgList.getE01CHMNCB() + ">");
					myRow.append("</TR>");
					indexRow++;
					beanList.addRow(myFlag, myRow.toString());
					newmessage = mc.receiveMessage();
				}
				while(true);
				myRow = new StringBuffer("<INPUT TYPE=HIDDEN NAME=NOCHKB VALUE=" + indexRow + ">");
				beanList.addRow("", myRow.toString());
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				if(IsNotError) {
					ses.setAttribute("rtBasic", msgHeader);
					ses.setAttribute("checkBooks", beanList);
					userPO.setIdentifier(msgHeader.getE03ACMACC());
					ses.setAttribute("userPO", userPO);
					flexLog("About to call Page: " + LangPath + "ECH0580_check_books_list.jsp");
					callPage(LangPath + "ECH0580_check_books_list.jsp", req, res);
				} else {
					flexLog("About to call Page: " + LangPath + "ECH0580_chb_enter_anul.jsp");
					callPage(LangPath + "ECH0580_chb_enter_anul.jsp", req, res);
				}
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
	}
	
	protected void ReqListarCheques(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
		MessageRecord newmessage = null;
		ECH056502Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		try {
			msgError = new ELEERRMessage();
		}
		catch(Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (UserPos)ses.getAttribute("userPO");
		String Action = req.getParameter("ACTION");		
		int type = 0;
		String num = "";
		String CheckBookN ="";
		try {
			flexLog("Send Initial Data");
			String corr = req.getParameter("CHKBCORR");
			String pcta =  "ACC" + corr.trim();
			String chkb = "NCB" + corr.trim();
			msgList = (ECH056502Message)mc.getMessageRecord("ECH056502");
			msgList.setH02USERID(user.getH01USR());
			msgList.setH02PROGRM("ECH0565");
			msgList.setH02TIMSYS(SuperServlet.getTimeStamp());
			msgList.setH02SCRCOD("01");
			msgList.setE02CHMACC(req.getParameter(pcta));
			try {
				CheckBookN = req.getParameter(chkb);
				msgList.setE02CHMNCB(CheckBookN);
			}
			catch(Exception e) {
				msgList.setE02CHMNCB("0");
			}
			msgList.send();
			msgList.destroy();
			flexLog("ECH056502 Message Sent");
		}
		catch(Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		try {
			newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				try {
					flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				}
				catch(Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else
			if(newmessage.getFormatName().equals("ECH056502")) {
				try {
					beanList = new JBList();
					flexLog("ECH056502 Message Received");
				}
				catch(Exception ex) {
					flexLog("Error: " + ex);
				}
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				boolean firstck = true;
				do {
					msgList = (ECH056502Message)newmessage;
					marker = msgList.getH02FLGMAS();
					if(marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					myFlag = "";
					myRow = new StringBuffer("<TR>");
					if(firstck){
						myRow.append("<INPUT type=hidden name=\"FCHK\" value=" + msgList.getE02CHMN01().trim() + ">");
						firstck=false;
					}
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN01()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS01()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN02()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS02()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN03()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS03()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN04()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS04()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=RIGHT >" + Util.formatCell(msgList.getE02CHMN05()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=LEFT  >" + Util.formatCell(msgList.getE02CHMS05()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					newmessage = mc.receiveMessage();
				}
				while(true);
				String lckf = "<INPUT type=hidden name=\"TCHK\" value=" + msgList.getE02CHMN05().trim() + ">";
				beanList.addRow("",lckf);
				String ChkBook = "<INPUT type=hidden name=\"CHKBNUM\" value=" + msgList.getE02CHMNCB().trim() + ">";
				beanList.addRow("",ChkBook);
				flexLog("Putting java beans into the session");
				ses.setAttribute("checks", beanList);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("ChkAction",Action);
				ses.setAttribute("CHECKBOOKN",CheckBookN);
				try {
					flexLog("About to call Page: " + LangPath + "ECH0580_checks_status.jsp");
					callPage(LangPath + "ECH0580_checks_status.jsp", req, res);
				}
				catch(Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void ActAnull(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
		MessageRecord newmessage = null;
		ECH058001Message msgList = null;
		ELEERRMessage msgError = null;
		JBList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		try {
			msgError = new ELEERRMessage();
		}
		catch(Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (UserPos)ses.getAttribute("userPO");
		String ActionType = req.getParameter("ACTION");
		String AccNum = req.getParameter("E03ACMACC");
		String CheckBookNum = req.getParameter("CHKBNUM");
		try {
			flexLog("Send Initial Data");
			msgList = (ECH058001Message)mc.getMessageRecord("ECH058001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ECH0580");
			msgList.setH01TIMSYS(SuperServlet.getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setE01CHMACC(AccNum);
			msgList.setE01CHMNCB(CheckBookNum);
			msgList.setH01OPECOD(ActionType);
			if(ActionType.equals("02")){
				msgList.setE01CHMICK(req.getParameter("E01CHMICK"));
				msgList.setE01CHMFCK(req.getParameter("E01CHMFCK"));
				msgList.setE01CHMSTS(req.getParameter("E01CHMSTS"));
			}
			msgList.send();
			msgList.destroy();
			flexLog("ECH058001 Message Sent");
		}catch(Exception es){
			es.printStackTrace();
			flexLog("Error: " + es);
			throw new RuntimeException("Socket Communication Error");			
		}
		
		try {
			newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				int nume = msgError.getBigDecimalERRNUM().intValue();
				if( nume > 0){				
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					try {
						flexLog("About to call Page: " + LangPath + "ECH0580_checks_status.jsp");
						callPage(LangPath + "ECH0580_checks_status.jsp", req, res);
					}catch(Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else{
					ReqListarChequerasCta(mc, user, req, res, ses);
				}
			}	
		}catch(Exception er1){
			er1.printStackTrace();
			flexLog("Error: " + er1);
			throw new RuntimeException("Socket Communication Error");			
			
		}
	}
}
