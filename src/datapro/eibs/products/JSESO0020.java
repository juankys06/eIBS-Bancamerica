// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   JSESO0020.java

package datapro.eibs.products;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.*;
import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSESO0020 extends SuperServlet {

    protected static final int R_ENTER = 300;
    protected static final int R_LIST = 100;
    protected static final int A_LIST = 200;
    protected static final int R_APPROVAL = 1;
    protected static final int R_INQUIRY = 2;
    protected static final int A_APPROVAL = 3;
    protected static final int A_DELETE = 4;
    protected String LangPath;

    public JSESO0020() {
        LangPath = "S";
    }

    public void destroy() {
        flexLog("free resources used by JSESO0020");
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
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
            int screen = 1;
            try {
                msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
                LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
                try {
                    flexLog("Opening Socket Connection");
                    s = new Socket(SuperServlet.hostIP, SuperServlet.getInitSocket(req) + 1);
                    s.setSoTimeout(SuperServlet.sckTimeOut);
                    mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
                    try {
                        screen = Integer.parseInt(req.getParameter("SCREEN"));
                    }
                    catch(Exception e) {
                        flexLog("Screen set to default value");
                    }
                    switch(screen) {
                    case 100: // 'd'
                        procReqList(mc, msgUser, req, res, session);
                        break;

                    case 200: 
                        procActionList(mc, msgUser, req, res, session);
                        break;

                    case 300: 
                        procReqEnter(mc, msgUser, req, res, session);
                        break;

                    case 1: // '\001'
                        procReqApproval(mc, msgUser, req, res, session);
                        break;

                    case 2: // '\002'
                        procReqInquiry(mc, msgUser, req, res, session);
                        break;

                    case 3: // '\003'
                        procActionApproval(mc, msgUser, req, res, session);
                        break;

                    case 4: // '\004'
                        procActionDelete(mc, msgUser, req, res, session);
                        break;

                    default:
                        res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.devPage);
                        break;

                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    int sck = SuperServlet.getInitSocket(req) + 1;
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

    protected void procReqEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try {
            flexLog("About to call Page: " + LangPath + "ESO0020_cd_rates_enter.jsp");
            callPage(LangPath + "ESO0020_cd_rates_enter.jsp", req, res);
        }
        catch(Exception e) {
            flexLog("Exception calling page " + e);
        }
    }

    protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO002001Message msgCD = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        JBObjList cdList = null;
        boolean IsNotError = false;
        try {
            msgError = new ELEERRMessage();
        }
        catch(Exception ex) {
            flexLog("Error: " + ex);
        }
        userPO = (UserPos)ses.getAttribute("userPO");
        String opCode = null;
        try {
            msgCD = (ESO002001Message)mc.getMessageRecord("ESO002001");
            msgCD.setH01USERID(user.getH01USR());
            msgCD.setH01PROGRM("ESO0020");
            msgCD.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH01SCRCOD("01");
            msgCD.setH01OPECOD("0015");
            try {
                msgCD.setE01SELUSR(req.getParameter("E01SELUSR"));
            }
            catch(Exception e) {
                msgCD.setE01SELUSR("");
            }
            msgCD.send();
            msgCD.destroy();
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
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);
            } else {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ESO002001")) {
                cdList = new JBObjList();
                boolean firstTime = true;
                String marker = "";
                do {
                    msgCD = (ESO002001Message)newmessage;
                    marker = msgCD.getE01FINDAT();
                    if(marker.equals("*")) {
                        cdList.setShowNext(false);
                        break;
                    }
                    cdList.addRow(msgCD);
                    if(firstTime)
                        firstTime = false;
                    if(marker.equals("+")) {
                        cdList.setShowNext(true);
                        break;
                    }
                    newmessage = mc.receiveMessage();
                }
                while(true);
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                ses.setAttribute("cdList", cdList);
                ses.setAttribute("userPO", userPO);
                try {
                    flexLog("About to call Page: " + LangPath + "ESO0020_cd_rates_list.jsp");
                    callPage(LangPath + "ESO0020_cd_rates_list.jsp", req, res);
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

    protected void procActionList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        ESS0030DSMessage msgUser = null;
        userPO = (UserPos)ses.getAttribute("userPO");
        try {
            int option = Integer.parseInt(req.getParameter("opt"));
            switch(option) {
            case 1: // '\001'
                procReqApproval(mc, user, req, res, ses);
                break;

            case 2: // '\002'
                procReqInquiry(mc, user, req, res, ses);
                break;

            case 3: // '\003'
                procActionApproval(mc, user, req, res, ses);
                break;

            case 4: // '\004'
                procActionDelete(mc, user, req, res, ses);
                break;

            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void procReqApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO002001Message msgCD = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        JBObjList cdList = null;
        boolean IsNotError = false;
        userPO = (UserPos)ses.getAttribute("userPO");
        userPO.setPurpose("MAINTENANCE");
        cdList = (JBObjList)ses.getAttribute("cdList");
        int row = -1;
        try {
            row = Integer.parseInt(req.getParameter("ROW"));
        }
        catch(Exception e) {
            row = -1;
        }
        if(row != -1)
            cdList.setCurrentRow(row);
        try {
            flexLog("Send Initial Data");
            msgCD = (ESO002001Message)cdList.getRecord();
            msgCD.setH01USERID(user.getH01USR());
            msgCD.setH01PROGRM("ESO0020");
            msgCD.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH01SCRCOD("01");
            msgCD.setH01OPECOD("0002");
            mc.sendMessage(msgCD);
            msgCD.destroy();
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
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);
            } else {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ESO002001")) {
                msgCD = (ESO002001Message)newmessage;
                ses.setAttribute("error", msgError);
                ses.setAttribute("msgCD", msgCD);
                ses.setAttribute("userPO", userPO);
                if(IsNotError) {
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0020_cd_rates_approval.jsp");
                        callPage(LangPath + "ESO0020_cd_rates_approval.jsp", req, res);
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                } else {
                    flexLog("Putting java beans into the session");
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0020_cd_rates_list.jsp");
                        callPage(LangPath + "ESO0020_cd_rates_list.jsp", req, res);
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
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

    protected void procActionApproval(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO002001Message msgCD = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        String option = "";
        try {
            msgError = new ELEERRMessage();
        }
        catch(Exception ex) {
            flexLog("Error: " + ex);
        }
        userPO = (UserPos)ses.getAttribute("userPO");
        option = req.getParameter("opt");
        try {
            flexLog("Send Initial Data");
            msgCD = (ESO002001Message)mc.getMessageRecord("ESO002001");
            msgCD.setH01USERID(user.getH01USR());
            msgCD.setH01PROGRM("ESO0020");
            msgCD.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH01SCRCOD("01");
            msgCD.setH01OPECOD("0005");
            Enumeration enu = msgCD.fieldEnumeration();
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

            msgCD.send();
            msgCD.destroy();
            flexLog("ESO002001 Message Sent");
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
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);
            } else {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ESO002001")) {
                try {
                    msgCD = new ESO002001Message();
                    flexLog("ESO002001 Message Sent");
                }
                catch(Exception ex) {
                    flexLog("Error: " + ex);
                }
                msgCD = (ESO002001Message)newmessage;
                userPO.setIdentifier(msgCD.getE01SOLACC());
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                ses.setAttribute("cdMaint", msgCD);
                ses.setAttribute("userPO", userPO);
                if(IsNotError)
                    try {
                        res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.products.JSESO0020?SCREEN=100");
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                else
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0020_cd_rates_maint.jsp");
                        callPage(LangPath + "ESO0020_cd_rates_maint.jsp", req, res);
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

    protected void procReqInquiry(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO002001Message msgCD = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        JBObjList cdList = null;
        boolean IsNotError = false;
        userPO = (UserPos)ses.getAttribute("userPO");
        userPO.setPurpose("MAINTENANCE");
        cdList = (JBObjList)ses.getAttribute("cdList");
        int row = -1;
        try {
            row = Integer.parseInt(req.getParameter("ROW"));
        }
        catch(Exception e) {
            row = -1;
        }
        if(row != -1)
            cdList.setCurrentRow(row);
        try {
            flexLog("Send Initial Data");
            if(row != -1)
                msgCD = (ESO002001Message)cdList.getRecord();
            else
                msgCD = (ESO002001Message)mc.getMessageRecord("ESO002001");
            msgCD.setH01USERID(user.getH01USR());
            msgCD.setH01PROGRM("ESO0020");
            msgCD.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH01SCRCOD("01");
            msgCD.setH01OPECOD("0002");
            if(row == -1)
                msgCD.setE01SELCUN(req.getParameter("E01SELCUN"));
            mc.sendMessage(msgCD);
            msgCD.destroy();
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
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);
            } else {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ESO002001")) {
                msgCD = (ESO002001Message)newmessage;
                ses.setAttribute("error", msgError);
                ses.setAttribute("msgCD", msgCD);
                ses.setAttribute("userPO", userPO);
                if(IsNotError) {
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0020_cd_rates_inq.jsp");
                        callPage(LangPath + "ESO0020_cd_rates_inq.jsp", req, res);
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                } else {
                    flexLog("Putting java beans into the session");
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0020_cd_rates_enter.jsp");
                        callPage(LangPath + "ESO0020_cd_rates_enter.jsp", req, res);
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
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

    protected void procActionDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO002001Message msgCD = null;
        ELEERRMessage msgError = null;
        JBObjList cdList = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        userPO = (UserPos)ses.getAttribute("userPO");
        cdList = (JBObjList)ses.getAttribute("cdList");
        int row = -1;
        try {
            row = Integer.parseInt(req.getParameter("ROW"));
        }
        catch(Exception exception) { }
        cdList.setCurrentRow(row);
        try {
            flexLog("Send Initial Data");
            msgCD = (ESO002001Message)cdList.getRecord();
            msgCD.setH01USERID(user.getH01USR());
            msgCD.setH01PROGRM("ESO0020");
            msgCD.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH01SCRCOD("01");
            msgCD.setH01OPECOD("0004");
            mc.sendMessage(msgCD);
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
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);
            } else {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ESO002001")) {
                msgCD = (ESO002001Message)newmessage;
                if(IsNotError) {
                    try {
                        res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.products.JSESO0020?SCREEN=100");
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                } else {
                    flexLog("Putting java beans into the session");
                    ses.setAttribute("error", msgError);
                    ses.setAttribute("userPO", userPO);
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0020_cd_rates_list.jsp");
                        callPage(LangPath + "ESO0020_cd_rates_list.jsp", req, res);
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
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
}
