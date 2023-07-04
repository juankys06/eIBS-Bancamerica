/*
 * Created on Sep 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package datapro.eibs.params;

/**
 * @author ccastillo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.*;
import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEGL0815 extends SuperServlet {

 protected static final int R_ENTER = 1;
 protected static final int A_ENTER = 2;
 protected static final int A_MAINT = 3;
 protected String LangPath;

 public JSEGL0815() {
     LangPath = "E";
 }

 public void destroy() {
     flexLog("free resources used by JSEgl0185");
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
                 case 1: // '\001'
                     procReqEnterMaint(mc, msgUser, req, res, session);
                     break;

                 case 2: // '\002'
                     procActionEnterMaint(mc, msgUser, req, res, session);
                     break;

                 case 3: // '\003'
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

 protected void procReqEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     EGL081501Message msgAA = null;
     ELEERRMessage msgError = null;
     try {
         msgError = new ELEERRMessage();
         msgAA = new EGL081501Message();
     }
     catch(Exception ex) {
         flexLog("Error: " + ex);
     }
     try {
         flexLog("Putting java beans into the session");
         ses.setAttribute("error", msgError);
         ses.setAttribute("accrual", msgAA);
         try {
             flexLog("About to call Page: " + LangPath + "EGL0815_amort_accrual_enter.jsp");
             callPage(LangPath + "EGL0815_amort_accrual_enter.jsp", req, res);
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

 protected void procActionEnterMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     EGL081501Message msgAA = null;
     EGL081502Message msgAA02 = null;
     ELEERRMessage msgError = null;
     boolean IsNotError = false;
     try {
         msgError = new ELEERRMessage();
     }
     catch(Exception ex) {
         flexLog("Error: " + ex);
     }
     try {
         flexLog("Send Initial Data");
         msgAA = (EGL081501Message)mc.getMessageRecord("EGL081501");
         msgAA.setH01USERID(user.getH01USR());
         msgAA.setH01PROGRM("EGL0815");
         msgAA.setH01TIMSYS(SuperServlet.getTimeStamp());
         msgAA.setH01SCRCOD("01");
         msgAA.setH01OPECOD("0002");
         msgAA.setE01AMOBNK(req.getParameter("E01AMOBNK"));
         msgAA.setE01AMOBRN(req.getParameter("E01AMOBRN"));
         msgAA.setE01AMOCCY(req.getParameter("E01AMOCCY"));
         msgAA.setE01AMOGLN(req.getParameter("E01AMOGLN"));
         msgAA.setE01AMOACC(req.getParameter("E01AMOACC"));
         msgAA.send();
         msgAA.destroy();
         flexLog("EGL081501 Message Sent");
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
         if(newmessage.getFormatName().equals("EGL081502")) {
             try {
                 msgAA02 = new EGL081502Message();
                 flexLog("EGL081502 Message Received");
             }
             catch(Exception ex) {
                 flexLog("Error: " + ex);
             }
             msgAA02 = (EGL081502Message)newmessage;
             flexLog("Putting java beans into the session");
             ses.setAttribute("error", msgError);
             if(IsNotError) {
                 ses.setAttribute("acc", msgAA02);
                 try {
                     flexLog("About to call Page: " + LangPath + "EGL0815_amort_accrual_basic.jsp");
                     callPage(LangPath + "EGL0815_amort_accrual_basic.jsp", req, res);
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             } else {
             	 msgAA = new EGL081501Message();
                 msgAA.setE01AMOBNK(msgAA02.getE02AMOBNK());
                 msgAA.setE01AMOBRN(msgAA02.getE02AMOBRN());
                 msgAA.setE01AMOCCY(msgAA02.getE02AMOCCY());
                 msgAA.setE01AMOGLN(msgAA02.getE02AMOGLN());
                 msgAA.setE01AMOACC(msgAA02.getE02AMOACC());
                 ses.setAttribute("accrual", msgAA);
                 try {
                     flexLog("About to call Page: " + LangPath + "EGL0815_amort_accrual_enter.jsp");
                     callPage(LangPath + "EGL0815_amort_accrual_enter.jsp", req, res);
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

 protected void procActionMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     EGL081502Message msgAA = null;
     ELEERRMessage msgError = null;
     boolean IsNotError = false;
     String option = "U";
     try {
         msgError = new ELEERRMessage();
     }
     catch(Exception ex) {
         flexLog("Error: " + ex);
     }
     try {
         flexLog("Send Initial Data");
         msgAA = (EGL081502Message)mc.getMessageRecord("EGL081502");
         msgAA.setH02USERID(user.getH01USR());
         msgAA.setH02PROGRM("EGL0815");
         msgAA.setH02TIMSYS(SuperServlet.getTimeStamp());
         msgAA.setH02SCRCOD("01");
         msgAA.setH02OPECOD("0005");
         try {
            option = req.getParameter("OPTION");
         }
         catch(Exception e) {
            flexLog("Option set to default value");
         }
         if (option.equals("D")) {
        	msgAA.setH02OPECOD("0004");
         } else if (option.equals("S")) {
        	msgAA.setH02OPECOD("0006");
         }

         Enumeration enu = msgAA.fieldEnumeration();
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

         msgAA.send();
         msgAA.destroy();
         flexLog("EGL081502 Message Sent");
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
         if(newmessage.getFormatName().equals("EGL081502")) {
             try {
                 msgAA = new EGL081502Message();
                 flexLog("EGL081502 Message Received");
             }
             catch(Exception ex) {
                 flexLog("Error: " + ex);
             }
             msgAA = (EGL081502Message)newmessage;
             flexLog("Putting java beans into the session");
             ses.setAttribute("error", msgError);
             ses.setAttribute("acc", msgAA);
             if(IsNotError)
                 try {
                     flexLog("About to call Page: " + LangPath + "EGL0815_amort_accrual_enter.jsp");
                     callPage(LangPath + "EGL0815_amort_accrual_enter.jsp", req, res);
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             else
                 try {
                     flexLog("About to call Page: " + LangPath + "EGL0815_amort_accrual_basic.jsp");
                     callPage(LangPath + "EGL0815_amort_accrual_basic.jsp", req, res);
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