
// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Author: C. Castillo
// Source File Name:   JSEWD0701.java

package datapro.eibs.general;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import java.io.*;
import java.net.Socket;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEWD0701 extends SuperServlet {

    private String LangPath;

    public JSEWD0701() {
        LangPath = "S";
    }

    public void destroy() {
        flexLog("free resources used by JSEWD0701");
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Socket s = null;
        MessageContext mc = null;
        MessageRecord newmessage = null;
        EWD0701DSMessage msgList = null;
        ESS0030DSMessage msgUser = null;
        JBObjList beanList = null;
        UserPos userPO = null;
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
            userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
			try {
                flexLog("Opennig Socket Connection");
                s = new Socket(SuperServlet.hostIP, SuperServlet.iniSocket + 1);
                s.setSoTimeout(SuperServlet.sckTimeOut);
                mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
                try {
                    msgList = (EWD0701DSMessage)mc.getMessageRecord("EWD0701DS");
					
					msgList.setRWDUSR(msgUser.getH01USR());
					
					try {
						msgList.setRWDTYP(req.getParameter("TYPE"));
						userPO.setType(req.getParameter("TYPE"));
						}
					catch(Exception exception) { }
					try {
						msgList.setRWDACC(req.getParameter("CODE"));
						userPO.setAccNum(req.getParameter("CODE"));
						}
					catch(Exception exception) { }
					try {
						msgList.setRWDFRC(req.getParameter("Pos"));
						}
					catch(Exception exception) { }					
                    msgList.send();
                    msgList.destroy();
                    flexLog("EWD0701DS Message Sent");
                }
                catch(Exception e) {
                    e.printStackTrace();
                    flexLog("Error: " + e);
                    res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotRespondPage);
                    return;
                }
                try {
                	newmessage = mc.receiveMessage();
                    if(newmessage.getFormatName().equals("EWD0701DS")) {
                    	beanList = new JBObjList(); 
                        String marker = "";
                        boolean firstTime = true;
                        do {
                            msgList = (EWD0701DSMessage)newmessage;
                            marker = msgList.getEWDOPE();
                            if(marker.equals("*")) {
                                beanList.setShowNext(false);
                                break;
                            }
        					if(firstTime) {
        						firstTime = false;
        						beanList.setFirstRec(Integer.parseInt(msgList.getEWDREC()));
        						userPO.setCusNum(msgList.getEWDRCN().toString());
        						userPO.setCusName(msgList.getEWDNA1());
        						userPO.setOfficer(msgList.getEWDOFC());
        						userPO.setCurrency(msgList.getEWDCCY());
        						userPO.setProdCode(msgList.getEWDPRO());
        					}
                            beanList.addRow(msgList);
                            if(marker.equals("+")) {
                                beanList.setShowNext(true);
                                break;
                            }
                            newmessage = mc.receiveMessage();
                        }
                        while(true);
                        flexLog("Putting java beans into the session");
                        session.setAttribute("EWD0701Help", beanList);
                        session.setAttribute("userPO", userPO);
                        try {
                            flexLog("About to call Page: " + LangPath + "EWD0701_spins_history_list.jsp");
                            callPage(LangPath + "EWD0701_spins_history_list.jsp", req, res);
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
