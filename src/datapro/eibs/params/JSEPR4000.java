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

public class JSEPR4000 extends SuperServlet {

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

 public JSEPR4000() {
     LangPath = "S";
 }

 public void destroy() {
     flexLog("free resources used by JSEPR4000");
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
                     
				case 500: 
					procReqDelet(mc, msgUser, req, res, session);
					break;
					
                 case 2: // 
                     procActionPos(mc, msgUser, req, res, session);
                     break;
                     
				 case 3: // 
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
         flexLog("About to call Page: " + LangPath + "EPR4000_enter_param.jsp");
         callPage(LangPath + "EPR4000_enter_param.jsp", req, res);
     }
     catch(Exception e) {
         flexLog("Exception calling page " + e);
     }
 }

 protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     EPR400001Message msgList = null;
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
         msgList = (EPR400001Message)mc.getMessageRecord("EPR400001");
         msgList.setH01USERID(user.getH01USR());
         msgList.setH01PROGRM("EPR4000");
         msgList.setH01TIMSYS(SuperServlet.getTimeStamp());
         msgList.setH01SCRCOD("01");
         msgList.setH01OPECOD("0015");
         try {
             msgList.setE01PRCBNK(req.getParameter("E01PRCBNK").toUpperCase());
         }
         catch(Exception exception) { }
         msgList.send();
         msgList.destroy();
         flexLog("EPR400001 Message Sent");
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
         if(newmessage.getFormatName().equals("EPR400001")) {
             try {
                 userPO.setBank(req.getParameter("E01PRCBNK"));
                 userPO.setHeader1(req.getParameter("E01PRCORG"));
				 userPO.setHeader2(req.getParameter("E01PRCTYP"));
				 userPO.setHeader3(req.getParameter("E01PRCCOP"));
				 userPO.setHeader4(req.getParameter("E01PRCCOM"));
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
                 msgList = (EPR400001Message)newmessage;
                 marker = msgList.getE01PRCOPE();
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
				 myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\"><input type=\"radio\" name=\"ACCNUM\" onclick=\"getParams('" + msgList.getE01PRCBNK() + "','" + msgList.getE01PRCORG() + "','" + msgList.getE01PRCTYP() + "','" + msgList.getE01PRCCOP() + "','"+ msgList.getE01PRCCOM() + "')\"></TD>");
                 myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"10%\">" + msgList.getE01PRCBNK() + "</td>");
                 myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\">" + msgList.getE01PRCORG() + "</td>");
                 myRow.append("<TD NOWRAP  ALIGN=CENTER width=\"20%\">" + msgList.getE01PRCTYP() + "</td>");          
				 myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"20%\">" + msgList.getE01PRCCOP() + "</td>");
				 myRow.append("<TD NOWRAP  ALIGN=LEFT width=\"20%\">" + msgList.getE01PRCCOM() + "</td>");
				
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
             ses.setAttribute("EPR4000Help", beanList);
             ses.setAttribute("userPO", userPO);
             if(IsNotError)
                 try {
                     flexLog("About to call Page: " + LangPath + "EPR4000_fee_list.jsp");
                     callPage(LangPath + "EPR4000_fee_list.jsp", req, res);
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             else
                 try {
                     flexLog("About to call Page: " + LangPath + "EPR4000_enter_param.jsp");
                     callPage(LangPath + "EPR4000_enter_param.jsp", req, res);
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
     String ORG = "";
     String TYP = "";
	 String COP = "";
     String COM = "";
     try {
        BNK = req.getParameter("BNK");
     }
     catch(Exception exception) { }
     try {
        ORG = req.getParameter("ORG");
     }
     catch(Exception exception1) { }
     try {
        TYP = req.getParameter("TYP");
     }
     catch(Exception exception2) { }
  	try {
	    COP = req.getParameter("COP");
	 }
	 catch(Exception exception3) { }
	 try {
		COM = req.getParameter("COM");
	 }
	 catch(Exception exception4) { }
    
     switch(inptOPT) {
     case 1: // '\001'
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEPR4000?SCREEN=100" + "&BNK=" + BNK + "&ORG=" + ORG + "&TYP=" + TYP + "&COP=" + COP + "&COM=" + COM);
         break;

     case 2: // '\002'
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEPR4000?SCREEN=300" + "&BNK=" + BNK + "&ORG=" + ORG + "&TYP=" + TYP + "&COP=" + COP + "&COM=" + COM);
         break;

     case 3: // '\003'
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEPR4000?SCREEN=500" + "&BNK=" + BNK + "&ORG=" + ORG + "&TYP=" + TYP + "&COP=" + COP + "&COM=" + COM);
         break;

     case 4: // '\004'
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEPR4000?SCREEN=700" + "&BNK=" + BNK + "&ORG=" + ORG + "&TYP=" + TYP + "&COP=" + COP + "&COM=" + COM);
         break;
    
     default:
         res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEPR4000?SCREEN=100" + "&BNK=" + BNK + "&ORG=" + ORG + "&TYP=" + TYP + "&COP=" + COP + "&COM=" + COM);
         break;

     }
 }

 protected void procReqNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
     MessageRecord newmessage = null;
     EPR400002Message msgDoc = null;
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
         msgDoc = (EPR400002Message)mc.getMessageRecord("EPR400002");
         msgDoc.setH02USERID(user.getH01USR());
         msgDoc.setH02PROGRM("EPR4000");
         msgDoc.setH02TIMSYS(SuperServlet.getTimeStamp());
         msgDoc.setH02SCRCOD("01");
         msgDoc.setH02OPECOD("0001");
         try {
             msgDoc.setE02PRCBNK(req.getParameter("BNK"));
         }
         catch(Exception exception) { }
         try {
             msgDoc.setE02PRCORG(req.getParameter("ORG"));
         }
         catch(Exception exception1) { }
         try {
            msgDoc.setE02PRCTYP(req.getParameter("TYP"));
         }
         catch(Exception exception2) { }
 	     try {
			msgDoc.setE02PRCCOP(req.getParameter("COP"));
		 }
		 catch(Exception exception3) { }
         try {
			msgDoc.setE02PRCCOM(req.getParameter("COM"));
		 }
		 catch(Exception exception4) { }

         msgDoc.send();
         msgDoc.destroy();
         flexLog("EPR400002 Message Sent");
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
         if(newmessage.getFormatName().equals("EPR400002")) {
             try {
                 msgDoc = new EPR400002Message();
                 flexLog("EPR400002 Message Received");
             }
             catch(Exception ex) {
                 flexLog("Error: " + ex);
             }
             msgDoc = (EPR400002Message)newmessage;
             flexLog("Putting java beans into the session");
             ses.setAttribute("error", msgError);
             ses.setAttribute("userPO", userPO);
             ses.setAttribute("lnParam", msgDoc);
             if(IsNotError)
                 try {
                     flexLog("About to call Page: " + LangPath + "EPR4000_fee_maint.jsp");
                     callPage(LangPath + "EPR4000_fee_maint.jsp", req, res);
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             else
                 try {
                     flexLog("About to call Page: " + LangPath + "EPR4000_fee_list.jsp");
                     callPage(LangPath + "EPR4000_fee_list.jsp", req, res);
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
     EPR400002Message msgDoc = null;
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
         msgDoc = (EPR400002Message)mc.getMessageRecord("EPR400002");
         msgDoc.setH02USERID(user.getH01USR());
         msgDoc.setH02PROGRM("EPR4000");
         msgDoc.setH02TIMSYS(SuperServlet.getTimeStamp());
         msgDoc.setH02SCRCOD("01");
         msgDoc.setH02OPECOD("0002");
         try {
             msgDoc.setE02PRCBNK(req.getParameter("BNK"));
         }
         catch(Exception exception) { }
         try {
             msgDoc.setE02PRCORG(req.getParameter("ORG"));
         }
         catch(Exception exception1) { }
         try {
            msgDoc.setE02PRCTYP(req.getParameter("TYP"));
          }
		 catch(Exception exception2) { }
		 try {
			msgDoc.setE02PRCCOP(req.getParameter("COP"));
		 }
		 catch(Exception exception3) { }
		 try {
			msgDoc.setE02PRCCOM(req.getParameter("COM"));
		  }
         catch(Exception exception4) { }
         msgDoc.send();
         msgDoc.destroy();
         flexLog("EPR400002 Message Sent");
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
         if(newmessage.getFormatName().equals("EPR400002")) {
             try {
                 msgDoc = new EPR400002Message();
                 flexLog("EPR400002 Message Received");
             }
             catch(Exception ex) {
                 flexLog("Error: " + ex);
             }
             msgDoc = (EPR400002Message)newmessage;
             flexLog("Putting java beans into the session");
             ses.setAttribute("error", msgError);
             ses.setAttribute("userPO", userPO);
             ses.setAttribute("lnParam", msgDoc);
             if(IsNotError)
                 try {
                     flexLog("About to call Page: " + LangPath + "EPR4000_fee_maint.jsp");
                     callPage(LangPath + "EPR4000_fee_maint.jsp", req, res);
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             else
                 try {
                     flexLog("About to call Page: " + LangPath + "EPR4000_fee_list.jsp");
                     callPage(LangPath + "EPR4000_fee_list.jsp", req, res);
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

 protected void procReqDelet(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
	 MessageRecord newmessage = null;
	 EPR400002Message msgDoc = null;
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
		 msgDoc = (EPR400002Message)mc.getMessageRecord("EPR400002");
		 msgDoc.setH02USERID(user.getH01USR());
		 msgDoc.setH02PROGRM("EPR4000");
		 msgDoc.setH02TIMSYS(SuperServlet.getTimeStamp());
		 msgDoc.setH02SCRCOD("01");
		 msgDoc.setH02OPECOD("0004");
		 try {
			 msgDoc.setE02PRCBNK(req.getParameter("BNK"));
		 }
		 catch(Exception exception) { }
		 try {
			 msgDoc.setE02PRCORG(req.getParameter("ORG"));
		 }
		 catch(Exception exception1) { }
		 try {
			msgDoc.setE02PRCTYP(req.getParameter("TYP"));
		  }
		 catch(Exception exception2) { }
		 try {
			msgDoc.setE02PRCCOP(req.getParameter("COP"));
		 }
		 catch(Exception exception3) { }
		 try {
			msgDoc.setE02PRCCOM(req.getParameter("COM"));
		  }
		 catch(Exception exception4) { }
		 msgDoc.send();
		 msgDoc.destroy();
		 flexLog("EPR400002 Message Sent");
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
		 if(newmessage.getFormatName().equals("EPR400002")) {
			 try {
				 msgDoc = new EPR400002Message();
				 flexLog("EPR400002 Message Received");
			 }
			 catch(Exception ex) {
				 flexLog("Error: " + ex);
			 }
			 msgDoc = (EPR400002Message)newmessage;   
			 flexLog("Putting java beans into the session");
			 ses.setAttribute("error", msgError);
			 ses.setAttribute("userPO", userPO);
			 ses.setAttribute("lnParam", msgDoc);
			 if(IsNotError)
				 try {
					 flexLog("About to call Page: " + LangPath + "EPR4000_enter_param.jsp");
					 callPage(LangPath + "EPR4000_enter_param.jsp", req, res);
				 }
				 catch(Exception e) {
					 flexLog("Exception calling page " + e);
				 }
			 else
				 try {
					 flexLog("About to call Page: " + LangPath + "EPR4000_fee_list.jsp");
					 callPage(LangPath + "EPR4000_fee_list.jsp", req, res);
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
     EPR400002Message msgRT = null;
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
         msgRT = (EPR400002Message)ses.getAttribute("lnParam");
         msgRT.setH02USERID(user.getH01USR());
         msgRT.setH02PROGRM("EPR4000");
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
         flexLog("EPR400002 Message Sent");
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
         if(newmessage.getFormatName().equals("EPR400002")) {
             try {
                 msgRT = new EPR400002Message();
                 flexLog("EPR400002 Message Received");
             }
             catch(Exception ex) {
                 flexLog("Error: " + ex);
             }
             msgRT = (EPR400002Message)newmessage;
             flexLog("Putting java beans into the session");
             ses.setAttribute("error", msgError);
             ses.setAttribute("lnParam", msgRT);
             ses.setAttribute("userPO", userPO);
             if(IsNotError)
                 try {
                     res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.params.JSEPR4000?SCREEN=1000");
                 }
                 catch(Exception e) {
                     flexLog("Exception calling page " + e);
                 }
             else
                 try {
                     flexLog("About to call Page: " + LangPath + "EPR4000_fee_maint.jsp");
                     callPage(LangPath + "EPR4000_fee_maint.jsp", req, res);
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