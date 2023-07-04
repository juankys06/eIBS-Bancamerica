// IBS Reports Help
// C. Castillo
// Source File Name:   JSEWD0901.java

package datapro.eibs.general;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import java.io.*;
import java.net.Socket;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEWD0901 extends SuperServlet {

    public JSEWD0901() {
    }

    public void init(ServletConfig config) throws ServletException {
    	    super.init(config);
    } 

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Socket s = null;
        MessageContext mc = null;
        MessageRecord newmessage = null;
        EWD0901DSMessage msgHelp = null;
        ESS0030DSMessage msgUser = null;
        JBList beanList = null;
        HttpSession session = req.getSession(false);
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
            msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
            String Language = msgUser.getE01LAN();
            String LangPath = SuperServlet.rootPath + Language + "/";
			try {
                flexLog("Opennig Socket Connection");
                s = new Socket(SuperServlet.hostIP, SuperServlet.iniSocket + 1);
                s.setSoTimeout(SuperServlet.sckTimeOut);
                mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
                try {
                    msgHelp = (EWD0901DSMessage)mc.getMessageRecord("EWD0901DS");
					
					msgHelp.setRWDUSR(msgUser.getH01USR());
					
					try {
						msgHelp.setRWDMOD(req.getParameter("MODULE"));
						}
					catch(Exception exception) { }
					try {
						msgHelp.setRWDPER(req.getParameter("PERIOD"));
						}
					catch(Exception exception) { }
					try {
						msgHelp.setRWDRPN(req.getParameter("REPORT"));
						}
					catch(Exception exception) { }
					try {
						msgHelp.setRWDFRC(req.getParameter("FromRecord"));
						}
					catch(Exception exception) { }					
                    msgHelp.send();
                    msgHelp.destroy();
                    flexLog("EWD0901DS Message Sent");
                }
                catch(Exception e) {
                    e.printStackTrace();
                    flexLog("Error: " + e);
                    res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotRespondPage);
                    return;
                }
                try {
                    newmessage = mc.receiveMessage();
                    if(newmessage.getFormatName().equals("EWD0901DS")) {
                        try {
                            beanList = new JBList();
                            flexLog("EWD0901DS Message Received");
                        }
                        catch(Exception ex) {
                            flexLog("Error: " + ex);
                        }
                        String marker = "";
						boolean firstTime = true;
                        String myFlag = "";
                        StringBuffer myRow = null;
                        String myDesc = "";
                        do {
                            msgHelp = (EWD0901DSMessage)newmessage;
                            marker = msgHelp.getEWDOPE();
                            if(marker.equals("*")) {
                                beanList.setShowNext(false);
                                break;
                            }
							if(firstTime) {
								firstTime = false;
								beanList.setFirstRec(Integer.parseInt(msgHelp.getEWDREC()));
							}
                            myRow = new StringBuffer("<TR>");
							myRow.append("<td nowrap align=\"left\"><a href=\"javascript:enter('" + msgHelp.getEWDRPN() + "')\">" + msgHelp.getEWDRPN() + "</a></td>");
                            myRow.append("<td nowrap align=\"left\"><a href=\"javascript:enter('" + msgHelp.getEWDRPN() + "')\">" + msgHelp.getEWDDSC() + "</a></td>");
                            myRow.append("</TR>");
                            beanList.addRow(myFlag, myRow.toString());
                            
                            if(marker.equals("+")) {
                                beanList.setShowNext(true);
                                break;
                            }
                            newmessage = mc.receiveMessage();
                        }
                        while(true);
                        flexLog("Putting java beans into the session");
                        session.setAttribute("EWD0901Help", beanList);
                        try {
                            flexLog("About to call Page: " + LangPath + "EWD0901_reports_helpmessage.jsp");
                            callPage(LangPath + "EWD0901_reports_helpmessage.jsp", req, res);
                        }
                        catch(Exception e) {
                            flexLog("Exception calling page " + e);
                        }
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    flexLog("Error: " + e);
                    res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotRespondPage);
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
    }
}
