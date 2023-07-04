package datapro.eibs.products;
/**
 * Customer-Portfolio Selection / List
 * Creation date: (07/13/07)
 * @author: Carlos Castillo
 */ 
import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.master.Util;
import java.io.*;
import java.net.Socket;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEFO0010 extends SuperServlet {

    protected String LangPath;

    public JSEFO0010() {
        LangPath = "S";
    }

    public void destroy() {
        flexLog("free resources used by JSEFO0010");
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    //
    // Option Menu
    //
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
                    
                    case 1: // Request Search parameters
                        procReqSearch(msgUser, req, res, session);
                        break;

                    case 2: // Process Search according with parameters
                        procActionSearch(mc, msgUser, req, res, session);
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

    // 
    // Request Parameters for Search
    //
    protected void procReqSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try {
            msgError = new ELEERRMessage();
            userPO = new UserPos();
            userPO.setOption("CIF");
            userPO.setPurpose("INQUIRY");
            String type = null;
            try {
                type = req.getParameter("TYPE"); 
                if(type == null)
                    type = "INQUIRY";
            }
            catch(Exception e) {
                type = "";
            }
            userPO.setHeader7(type);
            String opt = null;
            try {
                opt = req.getParameter("OPTION"); 
                if(opt == null)
                    opt = "";
            }
            catch(Exception e) {
                opt = "";
            }
            userPO.setHeader8(opt);
            ses.setAttribute("error", msgError);
            ses.setAttribute("userPO", userPO);
        }
        catch(Exception ex) {
            flexLog("Error: " + ex);
        }
        try {
            flexLog("About to call Page: " + LangPath + "EFO0010_cif_client_search.jsp");
            callPage(LangPath + "EFO0010_cif_client_search.jsp", req, res);
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Exception calling page " + e);
        }
    }

    
    // 
    // Send List according with Search Parameters
    //
    protected void procActionSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException {
        MessageRecord newmessage = null;
        ELEERRMessage msgError = null;
        EFO001001Message msgSearch = null;
        EFO001002Message msgList = null;
        JBList beanList = null;
        UserPos userPO = null;
        userPO = (UserPos)ses.getAttribute("userPO");
		String num = "";
        int type = 0;
        try {
            flexLog("Send Initial Data");
            msgSearch = (EFO001001Message)mc.getMessageRecord("EFO001001");  
            msgSearch.setH01USERID(user.getH01USR());
            msgSearch.setH01PROGRM("EFO0010");
            msgSearch.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgSearch.setH01SCRCOD("01");
            msgSearch.setH01OPECOD("0004");
            try {
                type = Integer.parseInt(req.getParameter("Type"));
				try {
					num = req.getParameter("NameSearch").toUpperCase();
					}
				catch(Exception ex) {
					num = " ";
				}
				msgSearch.setE01SELNME(num);
                msgSearch.setE01SELTYP(type + "");
                userPO.setID(msgSearch.getE01SELNME());
                userPO.setType(msgSearch.getE01SELTYP());
                switch(type) {
                case 1: // Customer Number
                    msgSearch.setE01SELCUN(num);
                    break;

                case 2: // Id Number
                case 3: // Short Name
                case 4: // Full Name 
                case 8: // All Customers allowed
                case 9: // Long Id Number
                default:
                    msgSearch.setE01SELCUN("0");
                    break;

                }
                try {
                    msgSearch.setE01NUMREC(req.getParameter("Pos"));
                }
                catch(Exception ex) {
                    msgSearch.setE01NUMREC("0");
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                flexLog("Input data error " + e);
            }
            msgSearch.send();
            msgSearch.destroy();
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
                    msgError = new ELEERRMessage();
                }
                catch(Exception ex) {
                    flexLog("Error: " + ex);
                }
                msgError = (ELEERRMessage)newmessage;
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                try {
                	flexLog("About to call Page: " + LangPath + "EFO0010_cif_client_search.jsp");
                	callPage(LangPath + "EFO0010_cif_client_search.jsp", req, res);
                }
                catch(Exception e) {
                    flexLog("Exception calling page " + e);
                }
            } else
			if(newmessage.getFormatName().equals("EFO001002")) {
				try {
					beanList = new JBList();
				}
				catch(Exception ex) {
					flexLog("Error: " + ex);
				}
				try {
					beanList.setSearchText(num);
					beanList.setSearchType(type + "");
				}
				catch(Exception e) {
					e.printStackTrace();
					beanList.setSearchText("A");
					beanList.setSearchType("3");
					flexLog("Input data error " + e);
				}
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				int indexRow = 0;
				while(true) {
					msgList = (EFO001002Message)newmessage;
					marker = msgList.getE02INDOPE();
					if(marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					}
					chk = "";
					if(firstTime) {
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgList.getE02NUMREC()));
						chk = "checked";
					}
					myRow = new StringBuffer("<TR>");
					myRow.append("<TD NOWRAP ALIGN=CENTER><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgList.getE02CUSCUN() + "\" " + chk + " onclick=\"getPortfClient(" + indexRow + ",'" + msgList.getE02CUSCUN() + "')\"></TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02CUSCUN()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE02CUSNA1()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02CUPOFC()) + "</TD>");
					myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getE02OFCBRN()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE02CUSSHN()));                    
                    
					myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA"+indexRow+"\" VALUE=\"" +  Util.formatCell(msgList.getE02CUSIDN()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE02CUSTID()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE02CUSPID()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE02CLISTS()) + "<br>");
					myRow.append(Util.formatCell(msgList.getE02CLILGT()) + "\"></TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					indexRow ++;				
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
					newmessage = mc.receiveMessage();
				}
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("cifList", beanList);
				try {
					flexLog("About to call Page: " + LangPath + "EFO0010_cif_list_front_office.jsp");
					callPage(LangPath + "EFO0010_cif_list_front_office.jsp", req, res);
                } catch(Exception e) {
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
