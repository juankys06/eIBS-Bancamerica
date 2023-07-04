// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   JSESO0000.java

package datapro.eibs.products;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.*;
import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSESO0000 extends SuperServlet {

    protected static final int R_LIST = 100;
    protected static final int A_LIST = 200;
    protected static final int R_LIST_RATES = 300;
    protected static final int A_LIST_RATES = 400;
    protected static final int R_NEW = 1;
    protected static final int R_MAINTENANCE = 2; 
    protected static final int A_MAINTENANCE = 3;
    protected static final int A_DELETE = 4;
    protected static final int R_NEW_RATE = 5;
    protected static final int R_MAINT_RATE = 6;
    protected static final int A_MAINT_RATE = 7;
    protected static final int A_DELETE_RATE = 8;
    protected String LangPath;

    public JSESO0000() {
        LangPath = "S";
    }

    public void destroy() {
        flexLog("free resources used by JSESO0000");
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
                        procReqListRates(mc, msgUser, req, res, session);
                        break;

                    case 400: 
                        procActionListRates(mc, msgUser, req, res, session);
                        break;

                    case 1: // '\001'
                        procReqNew(mc, msgUser, req, res, session);
                        break;

                    case 2: // '\002'
                        procReqMaintenance(mc, msgUser, req, res, session);
                        break;

                    case 3: // '\003'
                        procActionMaintenance(mc, msgUser, req, res, session);
                        break;

                    case 4: // '\004'
                        procActionDelete(mc, msgUser, req, res, session);
                        break;

                    case 5: // '\005'
                        procReqNewRate(mc, msgUser, req, res, session);
                        break;

                    case 6: // '\006'
                        procReqRateMaintenance(mc, msgUser, req, res, session);
                        break;

                    case 7: // '\007'
                        procActionRateMaintenance(mc, msgUser, req, res, session);
                        break;

                    case 8: // '\b'
                        procActionDeleteRate(mc, msgUser, req, res, session);
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

    protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO000001Message msgCD = null;
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
            msgCD = (ESO000001Message)mc.getMessageRecord("ESO000001");
            msgCD.setH01USERID(user.getH01USR());
            msgCD.setH01PROGRM("ESO0000");
            msgCD.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH01SCRCOD("01");
            msgCD.setH01OPECOD("0015");
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
            if(newmessage.getFormatName().equals("ESO000001")) {
                cdList = new JBObjList();
                boolean firstTime = true;
                String marker = "";
                do {
                    msgCD = (ESO000001Message)newmessage;
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
                    flexLog("About to call Page: " + LangPath + "ESO0000_cd_precancel_list.jsp");
                    callPage(LangPath + "ESO0000_cd_precancel_list.jsp", req, res);
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
                procReqNew(mc, user, req, res, ses);
                break;

            case 2: // '\002'
                procReqMaintenance(mc, user, req, res, ses);
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

    protected void procReqListRates(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO000002Message msgCD = null;
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
            msgCD = (ESO000002Message)mc.getMessageRecord("ESO000002");
            msgCD.setH02USERID(user.getH01USR());
            msgCD.setH02PROGRM("ESO0000");
            msgCD.setH02TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH02SCRCOD("01");
            msgCD.setH02OPECOD("0015");
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
            if(newmessage.getFormatName().equals("ESO000002")) {
                cdList = new JBObjList();
                boolean firstTime = true;
                String marker = "";
                do {
                    msgCD = (ESO000002Message)newmessage;
                    marker = msgCD.getE02FINDAT();
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
                    flexLog("About to call Page: " + LangPath + "ESO0000_cd_rates_list.jsp");
                    callPage(LangPath + "ESO0000_cd_rates_list.jsp", req, res);
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

    protected void procActionListRates(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        ESS0030DSMessage msgUser = null;
        userPO = (UserPos)ses.getAttribute("userPO");
        try {
            int option = Integer.parseInt(req.getParameter("opt"));
            switch(option) {
            case 1: // '\001'
                procReqNewRate(mc, user, req, res, ses);
                break;

            case 2: // '\002'
                procReqRateMaintenance(mc, user, req, res, ses);
                break;

            case 4: // '\004'
                procActionDeleteRate(mc, user, req, res, ses);
                break;

            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void procReqNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try {
            flexLog("About to call Page: " + LangPath + "ESO0000_cd_precancel_new.jsp");
            callPage(LangPath + "ESO0000_cd_precancel_new.jsp", req, res);
        }
        catch(Exception e) {
            flexLog("Exception calling page " + e);
        }
    }

    protected void procReqMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO000001Message msgCD = null;
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
        catch(Exception exception) { }
        cdList.setCurrentRow(row);
        try {
            flexLog("Send Initial Data");
            msgCD = (ESO000001Message)cdList.getRecord();
            flexLog("Putting java beans into the session");
            ses.setAttribute("msgCD", msgCD);
            ses.setAttribute("userPO", userPO);
            try {
                flexLog("About to call Page: " + LangPath + "ESO0000_cd_precancel_maint.jsp");
                callPage(LangPath + "ESO0000_cd_precancel_maint.jsp", req, res);
            }
            catch(Exception e) {
                flexLog("Exception calling page " + e);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO000001Message msgCD = null;
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
            msgCD = (ESO000001Message)mc.getMessageRecord("ESO000001");
            msgCD.setH01USERID(user.getH01USR());
            msgCD.setH01PROGRM("ESO0000");
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
            flexLog("ESO000001 Message Sent");
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
            if(newmessage.getFormatName().equals("ESO000001")) {
                try {
                    msgCD = new ESO000001Message();
                    flexLog("ESO000001 Message Sent");
                }
                catch(Exception ex) {
                    flexLog("Error: " + ex);
                }
                msgCD = (ESO000001Message)newmessage;
                userPO.setIdentifier(msgCD.getE01SOLACC());
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                ses.setAttribute("cdMaint", msgCD);
                ses.setAttribute("userPO", userPO);
                if(IsNotError)
                    try {
                        res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.products.JSESO0000?SCREEN=100");
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                else
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0000_cd_precancel_maint.jsp");
                        callPage(LangPath + "ESO0000_cd_precancel_maint.jsp", req, res);
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

    protected void procActionDelete(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO000001Message msgCD = null;
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
            msgCD = (ESO000001Message)cdList.getRecord();
            msgCD.setH01USERID(user.getH01USR());
            msgCD.setH01PROGRM("ESO0000");
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
            if(newmessage.getFormatName().equals("ESO000001")) {
                msgCD = (ESO000001Message)newmessage;
                if(IsNotError) {
                    try {
                        res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.products.JSESO0000?SCREEN=100");
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                } else {
                    flexLog("Putting java beans into the session");
                    ses.setAttribute("error", msgError);
                    ses.setAttribute("userPO", userPO);
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0000_cd_precancel_list.jsp");
                        callPage(LangPath + "ESO0000_cd_precancel_list.jsp", req, res);
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

    protected void procReqNewRate(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try {
            flexLog("About to call Page: " + LangPath + "ESO0000_cd_rates_new.jsp");
            callPage(LangPath + "ESO0000_cd_rates_new.jsp", req, res);
        }
        catch(Exception e) {
            flexLog("Exception calling page " + e);
        }
    }

    protected void procReqRateMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO000002Message msgCD = null;
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
        catch(Exception exception) { }
        cdList.setCurrentRow(row);
        try {
            flexLog("Send Initial Data");
            msgCD = (ESO000002Message)cdList.getRecord();
            flexLog("Putting java beans into the session");
            ses.setAttribute("msgCD", msgCD);
            ses.setAttribute("userPO", userPO);
            try {
                flexLog("About to call Page: " + LangPath + "ESO0000_cd_rates_maint.jsp");
                callPage(LangPath + "ESO0000_cd_rates_maint.jsp", req, res);
            }
            catch(Exception e) {
                flexLog("Exception calling page " + e);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void procActionRateMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO000002Message msgCD = null;
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
            msgCD = (ESO000002Message)mc.getMessageRecord("ESO000002");
            msgCD.setH02USERID(user.getH01USR());
            msgCD.setH02PROGRM("ESO0000");
            msgCD.setH02TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH02SCRCOD("01");
            msgCD.setH02OPECOD("0005");
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
            flexLog("ESO000002 Message Sent");
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
            if(newmessage.getFormatName().equals("ESO000002")) {
                try {
                    msgCD = new ESO000002Message();
                    flexLog("ESO000001 Message Sent");
                }
                catch(Exception ex) {
                    flexLog("Error: " + ex);
                }
                msgCD = (ESO000002Message)newmessage;
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                ses.setAttribute("cdMaint", msgCD);
                ses.setAttribute("userPO", userPO);
                if(IsNotError)
                    try {
                        res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.products.JSESO0000?SCREEN=300");
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                else
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0000_cd_rates_maint.jsp");
                        callPage(LangPath + "ESO0000_cd_rates_maint.jsp", req, res);
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

    protected void procActionDeleteRate(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ESO000002Message msgCD = null;
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
            msgCD = (ESO000002Message)cdList.getRecord();
            msgCD.setH02USERID(user.getH01USR());
            msgCD.setH02PROGRM("ESO0000");
            msgCD.setH02TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH02SCRCOD("01");
            msgCD.setH02OPECOD("0004");
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
            if(newmessage.getFormatName().equals("ESO000002")) {
                msgCD = (ESO000002Message)newmessage;
                if(IsNotError) {
                    try {
                        res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.products.JSESO0000?SCREEN=300");
                    }
                    catch(Exception e) {
                        flexLog("Exception calling page " + e);
                    }
                } else {
                    flexLog("Putting java beans into the session");
                    ses.setAttribute("error", msgError);
                    ses.setAttribute("userPO", userPO);
                    try {
                        flexLog("About to call Page: " + LangPath + "ESO0000_cd_rates_list.jsp");
                        callPage(LangPath + "ESO0000_cd_rates_list.jsp", req, res);
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
