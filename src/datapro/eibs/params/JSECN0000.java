package datapro.eibs.params;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.*;
import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSECN0000 extends SuperServlet {

    protected static final int R_MAINTENANCE = 1;
    protected static final int A_MAINTENANCE = 2;
    protected static final int R_MAINTENANCE_PIZZ = 7;
    protected static final int A_MAINTENANCE_PIZZ = 8;
    protected static final int A_TB_ENTER = 3;
    protected static final int A_TB_ENTER_PIZZ = 5;
    protected static final int R_LIST = 100;
    protected static final int A_LIST = 200;
    protected static final int R_LIST_PIZZ = 300;
    protected static final int A_LIST_PIZZ = 400;
	protected static final int R_LIST_READ		= 500;
	protected static final int R_LIST_PIZZ_READ	= 700;
    protected String LangPath;

    public JSECN0000() {
        LangPath = "S";
    }

    public void destroy() {
        flexLog("free resources used by JSEDL0130");
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void procActionTableMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ECN000003Message msgRate = null;
        ECN000002Message msgRate2 = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        userPO = (UserPos)ses.getAttribute("userPO");
        try {
            String opt = "S";
            opt = req.getParameter("OPT").equals(null) ? "S" : req.getParameter("OPT");
            flexLog("Send Initial Data");
            msgRate = (ECN000003Message)mc.getMessageRecord("ECN000003");
            msgRate.setH03USERID(user.getH01USR());
            msgRate.setH03PROGRM("ECN0000");
            msgRate.setH03TIMSYS(SuperServlet.getTimeStamp());
            msgRate.setH03SCRCOD("01");
            Enumeration enu = msgRate.fieldEnumeration();
            MessageField field = null;
            String value = null;
            while(enu.hasMoreElements())  {
                field = (MessageField)enu.nextElement();
                try {
                    value = req.getParameter(field.getTag()).toUpperCase();
                    if(value != null)
                        field.setString(value);
                }
                catch(Exception exception) { }
            }

            if(opt.equals("S")) {
                msgRate.send();
                msgRate.destroy();
                flexLog("ECN000003 Message Sent");
                newmessage = mc.receiveMessage();
				if(newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else {
					flexLog("Message " + newmessage.getFormatName() + " received.");
				}
                newmessage = mc.receiveMessage();
                if(newmessage.getFormatName().equals("ECN000003")) {
                    msgRate = (ECN000003Message)newmessage;
                    flexLog("Putting java beans into the session");
                    userPO.setHeader1("");
                    ses.setAttribute("error", msgError);
                    ses.setAttribute("userPO", userPO);
                    try {
                        if(IsNotError) {
                            flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_confirm.jsp");
                            callPage(LangPath + "ECN0000_rate_table_confirm.jsp", req, res);
                        } else {
                            ses.setAttribute("tbRate", msgRate);
                            flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_maint.jsp");
                            callPage(LangPath + "ECN0000_rate_table_maint.jsp", req, res);
                        }
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                } else {
                    flexLog("Message " + newmessage.getFormatName() + " received.");
                }
            } else {
                try {
                    userPO.setHeader1("CC");
                    ses.setAttribute("userPO", userPO);
                    ses.setAttribute("tbRate", msgRate);
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_maint.jsp");
                    callPage(LangPath + "ECN0000_rate_table_maint.jsp", req, res);
                }
                catch(Exception e) {
                    flexLog("Exception calling page " + e);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void procActionTableMaintPizz(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ECN000003Message msgRate = null;
        ECN000002Message msgRate2 = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        userPO = (UserPos)ses.getAttribute("userPO");
        try {
            String opt = "S";
            opt = req.getParameter("OPT").equals(null) ? "S" : req.getParameter("OPT");
            flexLog("Send Initial Data");
            msgRate = (ECN000003Message)mc.getMessageRecord("ECN000003");
            msgRate.setH03USERID(user.getH01USR());
            msgRate.setH03PROGRM("ECN0000");
            msgRate.setH03TIMSYS(SuperServlet.getTimeStamp());
            msgRate.setH03SCRCOD("01");
            msgRate.setE03CDRSFL("S");
            Enumeration enu = msgRate.fieldEnumeration();
            MessageField field = null;
            String value = null;
            while(enu.hasMoreElements())  {
                field = (MessageField)enu.nextElement();
                try {
                    value = req.getParameter(field.getTag()).toUpperCase();
                    if(value != null)
                        field.setString(value);
                }
                catch(Exception exception) { }
            }

            if(opt.equals("S")) {
                msgRate.send();
                msgRate.destroy();
                flexLog("ECN000003 Message Sent");
                newmessage = mc.receiveMessage();
				if(newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage)newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else {
					flexLog("Message " + newmessage.getFormatName() + " received.");
				}
                newmessage = mc.receiveMessage();
                if(newmessage.getFormatName().equals("ECN000003")) {
                    msgRate = (ECN000003Message)newmessage;
                    flexLog("Putting java beans into the session");
                    userPO.setHeader1("");
                    ses.setAttribute("error", msgError);
                    ses.setAttribute("userPO", userPO);
                    try {
                        if(IsNotError) {
                            flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_confirm_pizz.jsp");
                            callPage(LangPath + "ECN0000_rate_table_confirm_pizz.jsp", req, res);
                        } else {
                            ses.setAttribute("tbRate", msgRate);
                            flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_maint_pizz.jsp");
                            callPage(LangPath + "ECN0000_rate_table_maint_pizz.jsp", req, res);
                        }
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                } else {
                    flexLog("Message " + newmessage.getFormatName() + " received.");
                }
            } else {
                try {
                    userPO.setHeader1("CC");
                    ses.setAttribute("userPO", userPO);
                    ses.setAttribute("tbRate", msgRate);
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_maint_pizz.jsp");
                    callPage(LangPath + "ECN0000_rate_table_maint_pizz.jsp", req, res);
                }
                catch(Exception e) {
                    flexLog("Exception calling page " + e);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void procActionTableEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ECN000003Message msgRate = null;
        ECN000002Message msgRate2 = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        userPO = (UserPos)ses.getAttribute("userPO");
        try {
            if(userPO.getPurpose().equals("COPY")) {
                flexLog("Send Initial Data");
                msgRate2 = (ECN000002Message)mc.getMessageRecord("ECN000002");
                msgRate2.setH02USERID(user.getH01USR());
                msgRate2.setH02PROGRM("ECN0000");
                msgRate2.setH02TIMSYS(SuperServlet.getTimeStamp());
                msgRate2.setH02SCRCOD("01");
                msgRate2.setH02OPECOD("0001");
                Enumeration enu = msgRate2.fieldEnumeration();
                MessageField field = null;
                String value = null;
                while(enu.hasMoreElements())  {
                    field = (MessageField)enu.nextElement();
                    try {
                        value = req.getParameter(field.getTag()).toUpperCase();
                        if(value != null)
                            field.setString(value);
                    }
                    catch(Exception exception1) { }
                }

                msgRate2.send();
                msgRate2.destroy();
                flexLog("ECN000002 Message Sent");
            } else {
                flexLog("Send Initial Data");
                msgRate = (ECN000003Message)mc.getMessageRecord("ECN000003");
                msgRate.setH03USERID(user.getH01USR());
                msgRate.setH03PROGRM("ECN0000");
                msgRate.setH03TIMSYS(SuperServlet.getTimeStamp());
                msgRate.setH03SCRCOD("01");
                msgRate.setH03OPECOD("0001");
                try {
                    msgRate.setE03CDRRTB(req.getParameter("E02CDRRTB"));
                    msgRate.setE03CDRDT1(req.getParameter("E02CDRDT1"));
                    msgRate.setE03CDRDT2(req.getParameter("E02CDRDT2"));
                    msgRate.setE03CDRDT3(req.getParameter("E02CDRDT3"));
                    msgRate.setE03CDRCCY(req.getParameter("E02CDRCCY"));
                    msgRate.setE03CDRSFL(req.getParameter("E02CDRSFL"));
                }
                catch(Exception exception) { }
                msgRate.send();
                msgRate.destroy();
                flexLog("ECN000003 Message Sent");
            }
            newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ECN000003")) {
                msgRate = (ECN000003Message)newmessage;
                flexLog("Putting java beans into the session");
                userPO.setHeader1("");
                ses.setAttribute("error", msgError);
                ses.setAttribute("userPO", userPO);
                try {
                    if(IsNotError) {
                        ses.setAttribute("tbRate", msgRate);
                        flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_maint.jsp");
                        callPage(LangPath + "ECN0000_rate_table_maint.jsp", req, res);
                    } else {
                        flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_enter.jsp");
                        callPage(LangPath + "ECN0000_rate_table_enter.jsp", req, res);
                    }
                }
                catch(Exception e) {
                    flexLog("Exception calling page " + e);
                }
            } else
            if(newmessage.getFormatName().equals("ECN000002")) {
                msgRate2 = (ECN000002Message)newmessage;
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                ses.setAttribute("userPO", userPO);
                try {
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_enter.jsp");
                    callPage(LangPath + "ECN0000_rate_table_enter.jsp", req, res);
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

    protected void procActionTableEnterPizz(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ECN000003Message msgRate = null;
        ECN000002Message msgRate2 = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        userPO = (UserPos)ses.getAttribute("userPO");
        try {
            if(userPO.getPurpose().equals("COPY")) {
                flexLog("Send Initial Data");
                msgRate2 = (ECN000002Message)mc.getMessageRecord("ECN000002");
                msgRate2.setH02USERID(user.getH01USR());
                msgRate2.setH02PROGRM("ECN0000");
                msgRate2.setH02TIMSYS(SuperServlet.getTimeStamp());
                msgRate2.setH02SCRCOD("01");
                msgRate2.setH02OPECOD("0001");
                msgRate2.setE02CDRSFL("S");
                
                Enumeration enu = msgRate2.fieldEnumeration();
                MessageField field = null;
                String value = null;
                while(enu.hasMoreElements())  {
                    field = (MessageField)enu.nextElement();
                    try {
                        value = req.getParameter(field.getTag()).toUpperCase();
                        if(value != null)
                            field.setString(value);
                    }
                    catch(Exception exception1) { }
                }

                msgRate2.send();
                msgRate2.destroy();
                flexLog("ECN000002 Message Sent");
            } else {
                flexLog("Send Initial Data");
                msgRate = (ECN000003Message)mc.getMessageRecord("ECN000003");
                msgRate.setH03USERID(user.getH01USR());
                msgRate.setH03PROGRM("ECN0000");
                msgRate.setH03TIMSYS(SuperServlet.getTimeStamp());
                msgRate.setH03SCRCOD("01");
                msgRate.setH03OPECOD("0001");
				
				Enumeration enu = msgRate.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while(enu.hasMoreElements())  {
					field = (MessageField)enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase();
						if(value != null)
							field.setString(value);
					}
					catch(Exception exception1) { }
				}
				
                msgRate.send();
                msgRate.destroy();
                flexLog("ECN000003 Message Sent");
            }
            newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
            newmessage = mc.receiveMessage();
            
            if(newmessage.getFormatName().equals("ECN000003")) {
                msgRate = (ECN000003Message)newmessage;
                flexLog("Putting java beans into the session");
                userPO.setHeader1("");
                ses.setAttribute("error", msgError);
                ses.setAttribute("userPO", userPO);
				ses.setAttribute("tbRate", msgRate);
                try {
                    if(IsNotError) {
                        flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_maint_pizz.jsp");
                        callPage(LangPath + "ECN0000_rate_table_maint_pizz.jsp", req, res);
                    } else {
                        flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_enter_pizz.jsp");
                        callPage(LangPath + "ECN0000_rate_table_enter_pizz.jsp", req, res);
                    }
                }
                catch(Exception e) {
                    flexLog("Exception calling page " + e);
                }
            } else if(newmessage.getFormatName().equals("ECN000002")) {
                msgRate2 = (ECN000002Message)newmessage;
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                ses.setAttribute("userPO", userPO);
				ses.setAttribute("tbRate2", msgRate2);                
                try {
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_enter_pizz.jsp");
                    callPage(LangPath + "ECN0000_rate_table_enter_pizz.jsp", req, res);
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

    protected void procReqTableList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ECN000001Message msgRate = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        userPO = new UserPos();
        
		String purpose = (String) ses.getAttribute("Purpose");
		if (purpose.equals("READONLY")) {
			userPO.setPurpose(purpose);
		} 
		
        String opCode = "0015";
        try {
            msgRate = (ECN000001Message)mc.getMessageRecord("ECN000001");
            msgRate.setH01USERID(user.getH01USR());
            msgRate.setH01PROGRM("ECN0000");
            msgRate.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgRate.setH01SCRCOD("01");
            msgRate.setH01OPECOD(opCode);
            msgRate.send();
            msgRate.destroy();
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ELEERR")) {
                msgError = (ELEERRMessage)newmessage;
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);
            } else {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ECN000001")) {
                String marker = "";
                JBObjList beanList = new JBObjList();
                do {
                    msgRate = (ECN000001Message)newmessage;
                    marker = msgRate.getE01CDROPE();
                    msgRate.setHandler(null);
                    if(marker.equals("*")) {
                        beanList.setShowNext(false);
                        break;
                    }
                    beanList.addRow(msgRate);
                    if(marker.equals("+")) {
                        beanList.setShowNext(true);
                        break;
                    }
                    newmessage = mc.receiveMessage();
                }
                while(true);
                flexLog("Putting java beans into the session");
                ses.setAttribute("tbRateList", beanList);
                ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
                try {
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_list.jsp");
                    callPage(LangPath + "ECN0000_rate_table_list.jsp", req, res);
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

    protected void procReqTableListPizz(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ECN000001Message msgRate = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        userPO = new UserPos();
        
		String purpose = (String) ses.getAttribute("Purpose");
		if (purpose.equals("READONLY")) {
			userPO.setPurpose(purpose);
		} 
        String opCode = "0015";
        try {
            msgRate = (ECN000001Message)mc.getMessageRecord("ECN000001");
            msgRate.setH01USERID(user.getH01USR());
            msgRate.setH01PROGRM("ECN0000");
            msgRate.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgRate.setH01SCRCOD("01");
            msgRate.setH01OPECOD(opCode);
            msgRate.setE01CDRSFL("S");
            msgRate.send();
            msgRate.destroy();
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ELEERR")) {
                msgError = (ELEERRMessage)newmessage;
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);
            } else {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ECN000001")) {
                String marker = "";
                JBObjList beanList = new JBObjList();
                do {
                    msgRate = (ECN000001Message)newmessage;
                    marker = msgRate.getE01CDROPE();
                    msgRate.setHandler(null);
                    if(marker.equals("*")) {
                        beanList.setShowNext(false);
                        break;
                    }
                    beanList.addRow(msgRate);
                    if(marker.equals("+")) {
                        beanList.setShowNext(true);
                        break;
                    }
                    newmessage = mc.receiveMessage();
                }
                while(true);
                flexLog("Putting java beans into the session");
                ses.setAttribute("tbRateList", beanList);
                ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
                try {
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_list_pizz.jsp");
                    callPage(LangPath + "ECN0000_rate_table_list_pizz.jsp", req, res);
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

    protected void procReqTable(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ELEERRMessage msgError = null;
        ECN000003Message msgRate = null;
        boolean IsNotError = false;
        UserPos userPO = null;
        userPO = (UserPos)ses.getAttribute("userPO");
        try {
            flexLog("Send Initial Data");
            msgRate = (ECN000003Message)ses.getAttribute("tbRate");
            msgRate.setH03USERID(user.getH01USR());
            msgRate.setH03PROGRM("ECN0000");
            msgRate.setH03TIMSYS(SuperServlet.getTimeStamp());
            msgRate.setH03SCRCOD("01");
            msgRate.setH03OPECOD("0002");
            mc.sendMessage(msgRate);
            msgRate.destroy();
            flexLog("ECN000003 Message Sent");
            newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ECN000003")) {
                msgRate = (ECN000003Message)newmessage;
                ses.setAttribute("userPO", userPO);
                ses.setAttribute("tbRate", msgRate);
                ses.setAttribute("error", msgError);
                try {
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_maint.jsp");
                    callPage(LangPath + "ECN0000_rate_table_maint.jsp", req, res);
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

    protected void procReqTablePizz(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ELEERRMessage msgError = null;
        ECN000003Message msgRate = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        userPO = (UserPos)ses.getAttribute("userPO");
        try {
            flexLog("Send Initial Data");
            msgRate = (ECN000003Message)ses.getAttribute("tbRate");
            msgRate.setH03USERID(user.getH01USR());
            msgRate.setH03PROGRM("ECN0000");
            msgRate.setH03TIMSYS(SuperServlet.getTimeStamp());
            msgRate.setH03SCRCOD("01");
            msgRate.setH03OPECOD("0002");
            msgRate.setE03CDRSFL("S");
            mc.sendMessage(msgRate);
            msgRate.destroy();
            flexLog("ECN000003 Message Sent");
            newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ECN000003")) {
                msgRate = (ECN000003Message)newmessage;
                ses.setAttribute("userPO", userPO);
                ses.setAttribute("tbRate", msgRate);
                ses.setAttribute("error", msgError);
                try {
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_maint_pizz.jsp");
                    callPage(LangPath + "ECN0000_rate_table_maint_pizz.jsp", req, res);
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

    protected void procActionTableList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        UserPos userPO = null;
        ELEERRMessage msgError = null;
        JBObjList tbList = null;
        try {
            msgError = new ELEERRMessage();
            userPO = (UserPos)ses.getAttribute("userPO");
            int row = 0;
			if (req.getParameter("opt").equals("M") || req.getParameter("opt").equals("D")) {	
                row = Integer.parseInt(req.getParameter("ROW"));
                tbList = (JBObjList)ses.getAttribute("tbRateList");
                tbList.setCurrentRow(row);
                ECN000001Message tbRate1 = (ECN000001Message)tbList.getRecord();
				if (req.getParameter("opt").equals("M")) {
					userPO.setPurpose("MAINTENANCE");
				} else {
					userPO.setPurpose("READONLY");
				}
                ECN000003Message tbRate = new ECN000003Message();
                tbRate.setE03CDRRTB(tbRate1.getE01CDRRTB());
                tbRate.setE03CDRDSC(tbRate1.getE01CDRDSC());
                tbRate.setE03CDRDT1(tbRate1.getE01CDRDT1());
                tbRate.setE03CDRDT2(tbRate1.getE01CDRDT2());
                tbRate.setE03CDRDT3(tbRate1.getE01CDRDT3());
                tbRate.setE03CDRCCY(tbRate1.getE01CDRCCY());
                tbRate.setE03CDRSFL(tbRate1.getE01CDRSFL());
                ses.setAttribute("userPO", userPO);
                ses.setAttribute("tbRate", tbRate);
                procReqTable(mc, user, req, res, ses);
            } else {
                if(req.getParameter("opt").equals("N"))
                    userPO.setPurpose("NEW");
                else
                    userPO.setPurpose("COPY");
                ses.setAttribute("error", msgError);
                ses.setAttribute("userPO", userPO);
                try {
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_enter.jsp");
                    callPage(LangPath + "ECN0000_rate_table_enter.jsp", req, res);
                }
                catch(Exception e) {
                    flexLog("Exception calling page " + e);
                }
            }
        }
        catch(Exception ex) {
            flexLog("Error: " + ex);
        }
    }

    protected void procActionTableListPizz(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        UserPos userPO = null;
        ELEERRMessage msgError = null;
        JBObjList tbList = null;
        try {
            msgError = new ELEERRMessage();
            userPO = (UserPos)ses.getAttribute("userPO");
            int row = 0;
			if (req.getParameter("opt").equals("M") || req.getParameter("opt").equals("D")) {	
                row = Integer.parseInt(req.getParameter("ROW"));
                tbList = (JBObjList)ses.getAttribute("tbRateList");
                tbList.setCurrentRow(row);
                ECN000001Message tbRate1 = (ECN000001Message)tbList.getRecord();
				if (req.getParameter("opt").equals("M")) {
					userPO.setPurpose("MAINTENANCE");
				} else {
					userPO.setPurpose("READONLY");
				}
                ECN000003Message tbRate = new ECN000003Message();
                tbRate.setE03CDRRTB(tbRate1.getE01CDRRTB());
                tbRate.setE03CDRDSC(tbRate1.getE01CDRDSC());
                tbRate.setE03CDRDT1(tbRate1.getE01CDRDT1());
                tbRate.setE03CDRDT2(tbRate1.getE01CDRDT2());
                tbRate.setE03CDRDT3(tbRate1.getE01CDRDT3());
                tbRate.setE03CDRCCY(tbRate1.getE01CDRCCY());
                tbRate.setE03CDRSFL("S");
                ses.setAttribute("userPO", userPO);
                ses.setAttribute("tbRate", tbRate);
                procReqTablePizz(mc, user, req, res, ses);
            } else {
                if(req.getParameter("opt").equals("N"))
                    userPO.setPurpose("NEW");
                else
                    userPO.setPurpose("COPY");
                ses.setAttribute("error", msgError);
                ses.setAttribute("userPO", userPO);
                try {
                    flexLog("About to call Page: " + LangPath + "ECN0000_rate_table_enter_pizz.jsp");
                    callPage(LangPath + "ECN0000_rate_table_enter_pizz.jsp", req, res);
                }
                catch(Exception e) {
                    flexLog("Exception calling page " + e);
                }
            }
        }
        catch(Exception ex) {
            flexLog("Error: " + ex);
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Socket s = null;
        MessageContext mc = null;
        ESS0030DSMessage msgUser = null;
        HttpSession session = null;
        session = req.getSession(false);
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
            int screen = R_LIST;
            try {
                msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
                LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
                try {
                    flexLog("Opennig Socket Connection");
                    s = new Socket(SuperServlet.hostIP, SuperServlet.iniSocket + 1);
                    s.setSoTimeout(SuperServlet.sckTimeOut);
                    mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
                    try {
                        screen = Integer.parseInt(req.getParameter("SCREEN"));
                    }
                    catch(Exception e) {
                        flexLog("Screen set to default value");
                    }
                    switch(screen) {
                    case R_LIST: // 'd'
						session.setAttribute("Purpose", "");
                        procReqTableList(mc, msgUser, req, res, session);
                        break;

                    case R_LIST_PIZZ:
						session.setAttribute("Purpose", "");
                        procReqTableListPizz(mc, msgUser, req, res, session);
                        break;
					case R_LIST_READ :
						session.setAttribute("Purpose", "READONLY");
						procReqTableList(mc, msgUser, req, res, session);
						break;
					case R_LIST_PIZZ_READ :
						session.setAttribute("Purpose", "READONLY");
						procReqTableListPizz(mc, msgUser, req, res, session);
						break;		
                    case A_LIST: 
                        procActionTableList(mc, msgUser, req, res, session);
                        break;

                    case A_LIST_PIZZ: 
                        procActionTableListPizz(mc, msgUser, req, res, session);
                        break;

                    case A_TB_ENTER: // '\003'
                        procActionTableEnter(mc, msgUser, req, res, session);
                        break;

                    case A_TB_ENTER_PIZZ: // '\005'
                        procActionTableEnterPizz(mc, msgUser, req, res, session);
                        break;

                    case A_MAINTENANCE: // '\002'
                        procActionTableMaint(mc, msgUser, req, res, session);
                        break;

                    case A_MAINTENANCE_PIZZ: // '\b'
                        procActionTableMaintPizz(mc, msgUser, req, res, session);
                        break;

                    default:
                        res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.devPage);
                        break;

                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    int sck = SuperServlet.iniSocket + 1;
                    flexLog("Socket not Open(Port " + sck + "). Error: " + e);
                    res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotOpenPage);
                }
                finally {
                    s.close();
                }
            }
            catch(Exception e) {
                flexLog("Error: " + e);
                res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotRespondPage);
            }
        }
    }

    protected void showERROR(ELEERRMessage m) {
        if(SuperServlet.logType != 0) {
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
