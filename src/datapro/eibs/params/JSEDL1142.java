// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JSEDL1142.java
            
package datapro.eibs.params;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.*;
import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEDL1142 extends SuperServlet
{

	protected static final int R_ENTER = 1;
	protected static final int R_LIST = 100;
	protected static final int R_NEW = 200;
	protected static final int R_MAINTENANCE = 300;
	protected static final int A_MAINTENANCE = 400;
	protected String LangPath;

    public JSEDL1142()
    {
        LangPath = "S";
    }

    public void destroy()
    {
        flexLog("free resources used by JSEDL1142");
    }

    public void init(ServletConfig config)
        throws ServletException
    {
        super.init(config);
    }

    protected void procReqEnterParam(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try
        {
            msgError = new ELEERRMessage();
            userPO = new UserPos();
            ses.setAttribute("error", msgError);
            ses.setAttribute("userPO", userPO);
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        try
        {
            flexLog("About to call Page: " + LangPath + "EDL1142_limits_enter_parameter.jsp");
            callPage(LangPath + "EDL1142_limits_enter_parameter.jsp", req, res);
        }
        catch(Exception e)
        {
            flexLog("Exception calling page " + e);
        }
    }

	protected void procReqParamList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		EDL114201Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		try
		{
			msgError = new ELEERRMessage();
		}
		catch(Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			msgList = (EDL114201Message) mc.getMessageRecord("EDL114201");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EDL1142");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			
			msgList.setE01TASBNK(req.getParameter("E01TASBNK"));

			msgList.send();
			msgList.destroy();
			flexLog("EDL114201 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ELEERR")) {
	
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e + newmessage);
			throw new RuntimeException("Socket Communication Error Receiving");
		}
	
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("EDL114201")) {
	
				JBObjList beanList = new JBObjList();
	
				boolean firstTime = true;
				String marker = "";
				String chk = "";
				while (true) {
	
					msgList = (EDL114201Message) newmessage;
	
					marker = msgList.getH01FLGMAS();
	
					if (firstTime) {
						firstTime = false;
						chk = "checked";
						ses.setAttribute("prefere", msgList);
	
					} else {
						chk = "";
					}
	
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
	
						beanList.addRow(msgList);
	
						if (marker.equals("+")) {
							beanList.setShowNext(true);
	
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("list", beanList);
				ses.setAttribute("userPO", userPO);
	
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDL1142_limits_parameters_tables.jsp");
					callPage(
						LangPath + "EDL1142_limits_parameters_tables.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}
	
	}

    protected void procReqNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        EDL114201Message msgCD = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        try
        {
            msgError = new ELEERRMessage();
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        userPO = (UserPos)ses.getAttribute("userPO");
        try
        {
            msgCD = (EDL114201Message)mc.getMessageRecord("EDL114201");
            msgCD.setH01USERID(user.getH01USR());
            msgCD.setH01PROGRM("EDL114201");
            msgCD.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgCD.setH01SCRCOD("01");
            msgCD.setH01OPECOD("0002");
            try
            {
				msgCD.setE01TASBNK(req.getParameter("E01TASBNK"));
				msgCD.setE01TASTBL(req.getParameter("E01TASTBL"));
				msgCD.setE01TASDSC(req.getParameter("E01TASDSC"));
				msgCD.setE01TASDTE(req.getParameter("E01TASDTE"));
            }
            catch(Exception exception) { }
            msgCD.send();
            msgCD.destroy();
            flexLog("EDL114201 Message Sent");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try
        {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ELEERR"))
            {
                msgError = (ELEERRMessage)newmessage;
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);
            } else
            {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try
        {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("EDL114201"))
            {
                try
                {
                    msgCD = new EDL114201Message();
                    flexLog("EDL114201 Message Received");
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                msgCD = (EDL114201Message)newmessage;
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                ses.setAttribute("userPO", userPO);
                ses.setAttribute("prefere", msgCD);
                if(IsNotError)
                    try
                    {
                        flexLog("About to call Page: " + LangPath + "EDL1142_limits_parameters.jsp");
                        callPage(LangPath + "EDL1142_limits_parameters.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
                else
                    try
                    {
                        flexLog("About to call Page: " + LangPath + "EDL1142_limits_enter_parameter.jsp");
                        callPage(LangPath + "EDL1142_limits_enter_parameter.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
            } else
            {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		EDL114201Message msgDoc = null;
		UserPos userPO = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Receive Data
		try {
			JBObjList bl = (JBObjList) ses.getAttribute("list");
			int idx = Integer.parseInt(req.getParameter("ROW"));
			bl.setCurrentRow(idx);
	
			msgDoc = (EDL114201Message) bl.getRecord();
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("prefere", msgDoc);
	
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "EDL1142_limits_parameters.jsp");
				callPage(
					LangPath + "EDL1142_limits_parameters.jsp",
					req,
					res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

    protected void procActionMaintenance(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        EDL114201Message msgRT = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        int acctype = 0;
        try
        {
            msgError = new ELEERRMessage();
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        userPO = (UserPos)ses.getAttribute("userPO");
        try
        {
            flexLog("Send Initial Data");
            msgRT = (EDL114201Message)ses.getAttribute("prefere");
            msgRT.setH01USERID(user.getH01USR());
            msgRT.setH01PROGRM("EDL114201");
            msgRT.setH01TIMSYS(SuperServlet.getTimeStamp());
            msgRT.setH01SCRCOD("01");
            msgRT.setH01OPECOD("0005");
            Enumeration enu = msgRT.fieldEnumeration();
            MessageField field = null;
            String value = null;
            while(enu.hasMoreElements()) 
            {
                field = (MessageField)enu.nextElement();
                try
                {
                    value = req.getParameter(field.getTag()).toUpperCase();
                    if(value != null)
                        field.setString(value);
                }
                catch(Exception exception) { }
            }
            mc.sendMessage(msgRT);
            msgRT.destroy();
            flexLog("EDL114201 Message Sent");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try
        {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ELEERR"))
            {
                msgError = (ELEERRMessage)newmessage;
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);
            } else
            {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try
        {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("EDL114201"))
            {
                try
                {
                    msgRT = new EDL114201Message();
                    flexLog("EDL114201 Message Received");
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                msgRT = (EDL114201Message)newmessage;
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                ses.setAttribute("prefere", msgRT);
                ses.setAttribute("userPO", userPO);
                if(IsNotError)
                    try
                    {
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSEDL1142?SCREEN=100&E01TASBNK=" + msgRT.getE01TASBNK());
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
                else
                    try
                    {
                        flexLog("About to call Page: " + LangPath + "EDL1142_limits_parameters.jsp");
                        callPage(LangPath + "EDL1142_limits_parameters.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
            } else
            {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        Socket s = null;
        MessageContext mc = null;
        ESS0030DSMessage msgUser = null;
        HttpSession session = null;
        session = req.getSession(false);
        if(session == null)
        {
            try
            {
                res.setContentType("text/html");
                printLogInAgain(res.getWriter());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                flexLog("Exception ocurred. Exception = " + e);
            }
        } else
        {
            int screen = 1;
            try
            {
                msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
                LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
                try
                {
                    flexLog("Opennig Socket Connection");
                    s = new Socket(SuperServlet.hostIP, SuperServlet.getInitSocket(req) + 1);
                    s.setSoTimeout(SuperServlet.sckTimeOut);
                    mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
                    try
                    {
                        screen = Integer.parseInt(req.getParameter("SCREEN"));
                    }
                    catch(Exception e)
                    {
                        flexLog("Screen set to default value");
                    }
                    switch(screen)
                    {
                    case 1:
                        procReqEnterParam(msgUser, req, res, session);
                        break;

					case 100: 
						procReqParamList(mc, msgUser, req, res, session);
						break;

                    case 200: 
                        procReqNew(mc, msgUser, req, res, session);
                        break;

					case 300: 
						procReqMaintenance(mc, msgUser, req, res, session);
						break;

                    case 400: 
                        procActionMaintenance(mc, msgUser, req, res, session);
                        break;

                    default:
                        res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.devPage);
                        break;
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    int sck = SuperServlet.getInitSocket(req) + 1;
                    flexLog("Socket not Open(Port " + sck + "). Error: " + e);
                    res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotOpenPage);
                }
                finally
                {
                    s.close();
                }
            }
            catch(Exception e)
            {
                flexLog("Error: " + e);
                res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotRespondPage);
            }
        }
    }

    protected void showERROR(ELEERRMessage m)
    {
        if(SuperServlet.logType != 0)
        {
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
