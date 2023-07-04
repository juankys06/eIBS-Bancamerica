//Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
//Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
//Copy registered to Evaluation Copy
//Source File Name:   JSEDD0660.java
      
package datapro.eibs.params;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.*;
import java.beans.Beans;
import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEDD0660 extends SuperServlet {

 static final int R_LIST = 1;
 static final int A_LIST = 2;
 static final int R_I_LIST = 3;
 static final int A_MAINT = 200;
 static final int R_ENTER_NEW = 1000;
 static final int R_NEW = 100;
 static final int R_MAINT = 300;
 static final int R_DELETE = 500;
 static final int R_INQUIRY = 700;
 private String LangPath;

 public JSEDD0660() {
     LangPath = "S";
 }

 public void destroy() {
     flexLog("free resources used by JSEDD0660");
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
                 flexLog("Opennig Socket Connection");
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
                 case 1: // 
                     procReqList(mc, msgUser, req, res, session);
                     break;

                 case 100: // 
                     procReqNew(mc, msgUser, req, res, session);
                     break;

                 case 1000: 
                     procReqEnterNew(mc, msgUser, req, res, session);
                     break;

                 case 300: 
                     procReqMaint(mc, msgUser, req, res, session);
                     break;

                 case 2: // 
                     procActionPos(mc, msgUser, req, res, session);
                     break;

                 case 200: 
                     procActionMaint(mc, msgUser, req, res, session);
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

 protected void procReqEnterNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     ELEERRMessage msgError = null;
     UserPos userPO = null;
     boolean IsNotError = false;
     try {
         msgError = new ELEERRMessage();
     }
     catch(Exception ex) {
         flexLog("Error: " + ex);
     }
     userPO = (UserPos)ses.getAttribute("userPO");
     try {
         flexLog("About to call Page: " + LangPath + "EDD0660_enter_param.jsp");
         callPage(LangPath + "EDD0660_enter_param.jsp", req, res);
     }
     catch(Exception e) {
         flexLog("Exception calling page " + e);
     }
 }

 protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     EDD066001Message msgList = null;
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
         msgList = (EDD066001Message)mc.getMessageRecord("EDD066001");
         msgList.setH01USERID(user.getH01USR());
         msgList.setH01PROGRM("EDD0660");
         msgList.setH01TIMSYS(SuperServlet.getTimeStamp());
         msgList.setH01SCRCOD("01");
         msgList.setH01OPECOD("0015");
         try {
             msgList.setE01PRFBNK(req.getParameter("E01PRFBNK").toUpperCase());
         }
         catch(Exception exception) { }
         msgList.send();
         msgList.destroy();
         flexLog("EDD066001 Message Sent");
     }
     catch(Exception e) {
         e.printStackTrace();
         flexLog("Error: " + e);
         throw new RuntimeException("Socket Communication Error");
     }
     try {
         newmessage = mc.receiveMessage();
         if(newmessage.getFormatName().equals("ELEERR")) {
             try {
                 msgError = (ELEERRMessage)Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
             }
             catch(Exception ex) {
                 flexLog("Error: " + ex);
             }
             msgError = (ELEERRMessage)newmessage;
             IsNotError = msgError.getERRNUM().equals("0");
             showERROR(msgError);
             flexLog("Putting java beans into the session");
             ses.setAttribute("error", msgError);
             ses.setAttribute("userPO", userPO);
         }
     }
     catch(Exception e) {
         e.printStackTrace();
         flexLog("Error: " + e + newmessage);
         throw new RuntimeException("Socket Communication Error Receiving");
     }
     try {
         newmessage = mc.receiveMessage();
         if(newmessage.getFormatName().equals("EDD066001")) {
             try {
                 userPO.setBank(req.getParameter("E01PRFBNK"));
                 userPO.setHeader1(req.getParameter("E01PRFTBL"));
                 userPO.setHeader2(req.getParameter("E01PRFCUN"));
             }
             catch(Exception exception1) { }
             beanList = new JBList();
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
             do {
                 msgList = (EDD066001Message)newmessage;
                 marker = msgList.getE01PRFOPE();
                 if(marker.equals("*")) {
                     beanList.setShowNext(false);
                     break;
                 }
                 if(firstTime) {
                     firstTime = false;
                     chk = "checked";
                 } else {
                     chk = "";
                 }
                 myRow = new StringBuffer("<TR>");
                 myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE01PRFTBL() + "\" " + chk + " onclick=\"getParams('" + msgList.getE01PRFBNK() + "','" + msgList.getE01PRFTBL() + "','"+ msgList.getE01PRFCUN() + "')\"></TD>");
                 myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01PRFBNK() + "</td>");
                 myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01PRFTBL() + "</td>");
                 myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01PRFFCY() + "</td>");
                 myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01PRFCUN() + "</td>");
                 myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"60%\">" + msgList.getE01PRFDSC() + "</td>");
                 myRow.append("</TR>");
                 beanList.addRow(myFlag, myRow.toString());
                 indexRow++;
                 if(marker.equals("+")) {
                     beanList.setShowNext(true);
                     break;
                 }
                 newmessage = mc.receiveMessage();
             }
             while(true);
             flexLog("Putting java beans into the session");
             ses.setAttribute("EDD0660Help", beanList);
             ses.setAttribute("userPO", userPO);
             if(IsNotError)
                 try {
                     flexLog("About to call Page: " + LangPath + "EDD0660_fee_list.jsp");
                     callPage(LangPath + "EDD0660_fee_list.jsp", req, res);
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             else
                 try {
                     flexLog("About to call Page: " + LangPath + "EDD0660_enter_param.jsp");
                     callPage(LangPath + "EDD0660_enter_param.jsp", req, res);
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
         throw new RuntimeException("Socket Communication Data Receiving");
     }
 }

 protected void procActionPos(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     ELEERRMessage msgError = null;
     UserPos userPO = null;
     boolean IsNotError = false;
     userPO = (UserPos)ses.getAttribute("userPO");
     int inptOPT = 0;
     inptOPT = Integer.parseInt(req.getParameter("opt"));
     String BNK = "";
     String TBL = "";
     String CUN = "";
     try {
         BNK = req.getParameter("BNK");
     }
     catch(Exception exception) { }
     try {
         TBL = req.getParameter("TBL");
     }
     catch(Exception exception1) { }
     try {
        CUN = req.getParameter("CUN");
    }
    catch(Exception exception2) { }
    
     switch(inptOPT) {
     case 1: // '\001'
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEDD0660?SCREEN=100" + "&BNK=" + BNK + "&TBL=" + TBL + "&CUN=" + CUN);
         break;

     case 2: // '\002'
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEDD0660?SCREEN=300" + "&BNK=" + BNK + "&TBL=" + TBL + "&CUN=" + CUN);
         break;

     case 3: // '\003'
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEDD0660?SCREEN=500" + "&BNK=" + BNK + "&TBL=" + TBL + "&CUN=" + CUN);
         break;

     case 4: // '\004'
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEDD0660?SCREEN=700" + "&BNK=" + BNK + "&TBL=" + TBL + "&CUN=" + CUN);
         break;

     default:
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEDD0660?SCREEN=100" + "&BNK=" + BNK + "&TBL=" + TBL + "&CUN=" + CUN);
         break;

     }
 }

 protected void procReqNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     EDD066002Message msgDoc = null;
     ELEERRMessage msgError = null;
     UserPos userPO = null;
     boolean IsNotError = false;
     int acctype = 0;
     try {
         msgError = new ELEERRMessage();
     }
     catch(Exception ex) {
         flexLog("Error: " + ex);
     }
     userPO = (UserPos)ses.getAttribute("userPO");
     try {
         msgDoc = (EDD066002Message)mc.getMessageRecord("EDD066002");
         msgDoc.setH02USERID(user.getH01USR());
         msgDoc.setH02PROGRM("EDD0660");
         msgDoc.setH02TIMSYS(SuperServlet.getTimeStamp());
         msgDoc.setH02SCRCOD("01");
         msgDoc.setH02OPECOD("0001");
         try {
             msgDoc.setE02PRFBNK(req.getParameter("BNK"));
         }
         catch(Exception exception) { }
         try {
             msgDoc.setE02PRFTBL(req.getParameter("TBL"));
         }
         catch(Exception exception1) { }
         try {
            msgDoc.setE02PRFCUN(req.getParameter("CUN"));
        }
        catch(Exception exception2) { }

         msgDoc.send();
         msgDoc.destroy();
         flexLog("EDD066002 Message Sent");
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
         if(newmessage.getFormatName().equals("EDD066002")) {
             try {
                 msgDoc = new EDD066002Message();
                 flexLog("EDD066002 Message Received");
             }
             catch(Exception ex) {
                 flexLog("Error: " + ex);
             }
             msgDoc = (EDD066002Message)newmessage;
             flexLog("Putting java beans into the session");
             ses.setAttribute("error", msgError);
             ses.setAttribute("userPO", userPO);
             ses.setAttribute("lnParam", msgDoc);
             if(IsNotError)
                 try {
                     flexLog("About to call Page: " + LangPath + "EDD0660_fee_maint.jsp");
                     callPage(LangPath + "EDD0660_fee_maint.jsp", req, res);
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             else
                 try {
                     flexLog("About to call Page: " + LangPath + "EDD0660_fee_list.jsp");
                     callPage(LangPath + "EDD0660_fee_list.jsp", req, res);
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

 protected void procReqMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     EDD066002Message msgDoc = null;
     ELEERRMessage msgError = null;
     UserPos userPO = null;
     boolean IsNotError = false;
     int acctype = 0;
     try {
         msgError = new ELEERRMessage();
     }
     catch(Exception ex) {
         flexLog("Error: " + ex);
     }
     userPO = (UserPos)ses.getAttribute("userPO");
     try {
         msgDoc = (EDD066002Message)mc.getMessageRecord("EDD066002");
         msgDoc.setH02USERID(user.getH01USR());
         msgDoc.setH02PROGRM("EDD0660");
         msgDoc.setH02TIMSYS(SuperServlet.getTimeStamp());
         msgDoc.setH02SCRCOD("01");
         msgDoc.setH02OPECOD("0002");
         try {
             msgDoc.setE02PRFBNK(req.getParameter("BNK"));
         }
         catch(Exception exception) { }
         try {
             msgDoc.setE02PRFTBL(req.getParameter("TBL"));
         }
         catch(Exception exception1) { }
         try {
            msgDoc.setE02PRFCUN(req.getParameter("CUN"));
        }
        catch(Exception exception2) { }
         msgDoc.send();
         msgDoc.destroy();
         flexLog("EDD066002 Message Sent");
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
         if(newmessage.getFormatName().equals("EDD066002")) {
             try {
                 msgDoc = new EDD066002Message();
                 flexLog("EDD066002 Message Received");
             }
             catch(Exception ex) {
                 flexLog("Error: " + ex);
             }
             msgDoc = (EDD066002Message)newmessage;
             flexLog("Putting java beans into the session");
             ses.setAttribute("error", msgError);
             ses.setAttribute("userPO", userPO);
             ses.setAttribute("lnParam", msgDoc);
             if(IsNotError)
                 try {
                     flexLog("About to call Page: " + LangPath + "EDD0660_fee_maint.jsp");
                     callPage(LangPath + "EDD0660_fee_maint.jsp", req, res);
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             else
                 try {
                     flexLog("About to call Page: " + LangPath + "EDD0660_fee_list.jsp");
                     callPage(LangPath + "EDD0660_fee_list.jsp", req, res);
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

 protected void procActionMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     EDD066002Message msgRT = null;
     ELEERRMessage msgError = null;
     UserPos userPO = null;
     boolean IsNotError = false;
     int acctype = 0;
     try {
         msgError = new ELEERRMessage();
     }
     catch(Exception ex) {
         flexLog("Error: " + ex);
     }
     userPO = (UserPos)ses.getAttribute("userPO");
     try {
         flexLog("Send Initial Data");
         msgRT = (EDD066002Message)ses.getAttribute("lnParam");
         msgRT.setH02USERID(user.getH01USR());
         msgRT.setH02PROGRM("EDD0660");
         msgRT.setH02TIMSYS(SuperServlet.getTimeStamp());
         msgRT.setH02SCRCOD("01");
         msgRT.setH02OPECOD("0005");
         Enumeration enu = msgRT.fieldEnumeration();
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

         mc.sendMessage(msgRT);
         msgRT.destroy();
         flexLog("EDD066002 Message Sent");
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
         if(newmessage.getFormatName().equals("EDD066002")) {
             try {
                 msgRT = new EDD066002Message();
                 flexLog("EDD066002 Message Received");
             }
             catch(Exception ex) {
                 flexLog("Error: " + ex);
             }
             msgRT = (EDD066002Message)newmessage;
             flexLog("Putting java beans into the session");
             ses.setAttribute("error", msgError);
             ses.setAttribute("lnParam", msgRT);
             ses.setAttribute("userPO", userPO);
             if(IsNotError)
                 try {
                     res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEDD0660?SCREEN=1000");
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             else
                 try {
                     flexLog("About to call Page: " + LangPath + "EDD0660_fee_maint.jsp");
                     callPage(LangPath + "EDD0660_fee_maint.jsp", req, res);
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
}