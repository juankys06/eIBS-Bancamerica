package datapro.eibs.client;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class JSECIF040 extends SuperServlet
{

    protected static final int R_AVERAGE = 1;
    protected static final int A_AVERAGE = 2;
    protected static final int R_ENTER = 100;
    protected static final int A_ENTER = 200;
    protected String LangPath;

    public JSECIF040()
    {
        LangPath = "S";
    }

    public void destroy()
    {
        flexLog("free resources used by JSESD0080");
    }

    public void init(ServletConfig config)
        throws ServletException
    {
        super.init(config);
    }

    protected void procActionEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
            throws ServletException, IOException
        {
            UserPos userPO = null;
            userPO = (UserPos)ses.getAttribute("userPO");
            String accNum = "";
            try
            {
                try
                {
                    accNum = req.getParameter("E01ACCNUM");
                }
                catch(Exception e)
                {
                    accNum = "0";
                }
                userPO.setIdentifier(accNum);
                flexLog("Putting java beans into the session");
                ses.setAttribute("userPO", userPO);
                if(req.getParameter("Mess").equals("")){
    				procReqAverage(mc, user, req, res, ses);
    			}else{
    			
    				procReqAverageDiario(mc, user, req, res, ses);
    			}
            }
            catch(Exception e)
            {
                e.printStackTrace();
                flexLog("Error: " + e);
                throw new RuntimeException("Socket Communication Error");
            }
        }
   
    
    protected void procReqAverageDiario(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
    		throws ServletException, IOException {

    MessageRecord newmessage = null;
    ECIF04003Message msgList2 = null;
    ECIF04001Message msgList = null;
    ECIF04003Message msgHeader = null;
    ELEERRMessage msgError = null;

    UserPos	userPO = null;	
    boolean IsNotError = false;

    userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

    int type = 0;
    	String num = "";

    // Send Initial data
    	try
    	{
    		flexLog("Send Initial Data");
    		msgList = (ECIF04001Message)mc.getMessageRecord("ECIF04001");
    		msgList.setH01USERID(user.getH01USR());
    	 	msgList.setH01PROGRM("ECIF040");
    	 	msgList.setH01TIMSYS(getTimeStamp());
    	 	msgList.setH01SCRCOD("03");
    	 	msgList.setH01OPECOD("0003");
    	 	
    	 	msgList.setE01ACCNUM(userPO.getIdentifier());
    	 	msgList.setE01SELYAR(req.getParameter("Ano").substring(2,4));
    	 	msgList.setE01SELMTH(req.getParameter("Mess"));
    	 	msgList.send();	
    	 	msgList.destroy();
    	 	flexLog("ECIF040 Message Sent");
    	}		
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		flexLog("Error: " + e);
    	  	throw new RuntimeException("Socket Communication Error");
    	}
    			
    	
    try
    {
    	flexLog("Send Initial Data");
    	msgList2 = (ECIF04003Message)mc.getMessageRecord("ECIF04003");
    	msgList2.setH03USERID(user.getH01USR());
     	msgList2.setH03PROGRM("ECIF040");
     	msgList2.setH03TIMSYS(getTimeStamp());
     	msgList2.setH03SCRCOD("03");
     	msgList2.setH03OPECOD("0003");
     	msgList2.setE03SELYAR(req.getParameter("Ano"));
     	msgList2.setE03SELMTH(req.getParameter("Mess"));
     	
     	msgList2.setE03ACCNUM(userPO.getIdentifier());

     	msgList2.send();	
     	msgList2.destroy();
     	flexLog("ECIF040 Message Sent");
    }		
    catch (Exception e)
    {
    	e.printStackTrace();
    	flexLog("Error: " + e);
      	throw new RuntimeException("Socket Communication Error");
    }
    	
    // Receive Data
    try
    {
        newmessage = mc.receiveMessage();
    	if (newmessage.getFormatName().equals("ELEERR")) {
    		try {
    			msgError = new datapro.eibs.beans.ELEERRMessage();
    		} 
    		catch (Exception ex) {
    			flexLog("Error: " + ex); 
    		}

    		msgError = (ELEERRMessage)newmessage;
    		showERROR(msgError);
    		flexLog("Putting java beans into the session");
    		ses.setAttribute("error", msgError);

    		newmessage = mc.receiveMessage();
    		try {
    			flexLog("About to call Page: " + LangPath + "ECIF040_averages_enter.jsp");
    			callPage(LangPath + "ECIF040_averages_enter.jsp", req, res);

    		}
    		catch (Exception e) {
    			flexLog("Exception calling page " + e);
    		}

    	}
    	else if (newmessage.getFormatName().equals("ECIF04003")) {
    		try {
    			
    			msgHeader = new datapro.eibs.beans.ECIF04003Message();
    	  	} 
    		catch (Exception ex) {
    			flexLog("Error: " + ex); 
    	  	}

    		msgHeader = (ECIF04003Message)newmessage;

    		msgList2 = (ECIF04003Message)newmessage;
    	

    		
    			
    	
    		}
    	
    		
    		
    		flexLog("Putting java beans into the session");
    		ses.setAttribute("aveBean", msgList2);
    		ses.setAttribute("header", msgHeader);
    		ses.setAttribute("userPO", userPO);

    		try {
    			flexLog("About to call Page: " + LangPath + "ECIF040_averages_rtDiario.jsp");
    			callPage(LangPath + "ECIF040_averages_rtDiario.jsp", req, res);

    		}
    		catch (Exception e) {
    			flexLog("Exception calling page " + e);
    		}
    		
    	

    }
    catch (Exception e)	{
    	e.printStackTrace();
    	flexLog("Error: " + e);
      	throw new RuntimeException("Socket Communication Error");
    }	

  }
    
    
    protected void procReqAverage(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        ECIF04001Message msgList = null;
        ECIF04002Message msgHeader = null;
        ELEERRMessage msgError = null;
        JBAverage beanAve = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        userPO = (UserPos)ses.getAttribute("userPO");
        int type = 0;
        String num = "";
        try
        {
            flexLog("Send Initial Data");
            msgList = (ECIF04001Message)mc.getMessageRecord("ECIF04001");
            msgList.setH01USERID(user.getH01USR());
            msgList.setH01PROGRM("ECIF040");
            msgList.setH01TIMSYS(getTimeStamp());
            msgList.setH01SCRCOD("01");
            msgList.setH01OPECOD("0004");
            msgList.setE01ACCNUM(userPO.getIdentifier());
            msgList.send();
            msgList.destroy();
            flexLog("ECIF040 Message Sent");
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
                try
                {
                    msgError = new ELEERRMessage();
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                msgError = (ELEERRMessage)newmessage;
                showERROR(msgError);
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                newmessage = mc.receiveMessage();
                try
                {
                    flexLog("About to call Page: " + LangPath + "ECIF040_averages_enter.jsp");
                    callPage(LangPath + "ECIF040_averages_enter.jsp", req, res);
                }
                catch(Exception e)
                {
                    flexLog("Exception calling page " + e);
                }
            } else
            if(newmessage.getFormatName().equals("ECIF04002"))
            {
                try
                {
                    beanAve = new JBAverage();
                    msgHeader = new ECIF04002Message();
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                msgHeader = (ECIF04002Message)newmessage;
                boolean firstTime = true;
                String marker = "";
                BigDecimal ave[] = new BigDecimal[24];
                int year[] = new int[24];
                int month[] = new int[24];
                for(int i = 0; i < 24; i++)
                {
                    ave[i] = new BigDecimal(0);
                    year[i] = -1;
                    month[i] = -1;
                }

                BigDecimal factor_3 = new BigDecimal(3);
                BigDecimal factor_12 = new BigDecimal(12);
                BigDecimal aveTrimestral = new BigDecimal(0);
                BigDecimal currAnnualAve = new BigDecimal(0);
                BigDecimal prevAnnualAve = new BigDecimal(0);
                int currYear = -1;
                int prevYear = -1;
                String graph = "";
                int col = 0;
                do
                {
                    newmessage = mc.receiveMessage();
                    msgList = (ECIF04001Message)newmessage;
                    marker = msgList.getE01INDOPE();
                    flexLog("marker = " + marker);
                    if(marker.equals("*"))
                    {
                        break;
                    }
                    col++;
                    graph = graph + "<param name=c" + col + "_label value=\"" + msgList.getE01MTHLET() + " " + Util.formatYear(msgList.getE01YEARNU()) + "\">";
                    graph = graph + "<param name=c" + col + " value=\"" + Util.parseCCYtoDouble(msgList.getE01AVERAG()) + "\">";
                    graph = graph + "<param name=c" + col + "_valab value=\"" + msgList.getE01AVERAG() + "\">";
                    graph = graph + "<param name=c" + col + "_color value=\"" + getColor(col) + "\">";
                    graph = graph + "<param name=c" + col + "_style value=\"solid\">";
                    month[col - 1] = Integer.parseInt(msgList.getE01MTHNUM());
                    year[col - 1] = Integer.parseInt(msgList.getE01YEARNU());
                    ave[col - 1] = msgList.getBigDecimalE01AVERAG();
                } while(true);
                if(col > 0)
                {
                    beanAve.setNoRecord(false);
                    currYear = year[col - 1];
                    if(currYear == 0)
                    {
                        prevYear = 99;
                    } else
                    {
                        prevYear = currYear - 1;
                    }
                    beanAve.setCurrYear(Util.formatYear(currYear));
                    beanAve.setPrevYear(Util.formatYear(prevYear));
                    try
                    {
                        for(int i = col - 1; i > -1; i--)
                        {
                            if(year[i] == currYear)
                            {
                                beanAve.setCurrYearMAve(ave[i].toString(), month[i] - 1);
                                currAnnualAve = currAnnualAve.add(ave[i]);
                                if(month[i] % 3 == 0)
                                {
                                    aveTrimestral = aveTrimestral.add(ave[i]);
                                    if(i == 0)
                                    {
                                        aveTrimestral = aveTrimestral.divide(factor_3, 0);
                                        beanAve.setCurrYear3MAve(aveTrimestral.toString(), (month[i] + 2) / 3 - 1);
                                    }
                                } else
                                if((month[i] + 1) % 3 == 0)
                                {
                                    aveTrimestral = aveTrimestral.add(ave[i]);
                                    if(i == 0)
                                    {
                                        aveTrimestral = aveTrimestral.divide(factor_3, 0);
                                        beanAve.setCurrYear3MAve(aveTrimestral.toString(), (month[i] + 2) / 3 - 1);
                                    }
                                } else
                                if((month[i] + 2) % 3 == 0)
                                {
                                    aveTrimestral = aveTrimestral.add(ave[i]);
                                    aveTrimestral = aveTrimestral.divide(factor_3, 0);
                                    beanAve.setCurrYear3MAve(aveTrimestral.toString(), (month[i] + 2) / 3 - 1);
                                    aveTrimestral = new BigDecimal(0);
                                }
                                continue;
                            }
                            if(year[i] != prevYear)
                            {
                                break;
                            }
                            beanAve.setPrevYearMAve(ave[i].toString(), month[i] - 1);
                            prevAnnualAve = prevAnnualAve.add(ave[i]);
                            if(month[i] % 3 == 0)
                            {
                                aveTrimestral = aveTrimestral.add(ave[i]);
                                if(i == 0)
                                {
                                    aveTrimestral = aveTrimestral.divide(factor_3, 0);
                                    beanAve.setPrevYear3MAve(aveTrimestral.toString(), (month[i] + 2) / 3 - 1);
                                }
                            } else
                            if((month[i] + 1) % 3 == 0)
                            {
                                aveTrimestral = aveTrimestral.add(ave[i]);
                                if(i == 0)
                                {
                                    aveTrimestral = aveTrimestral.divide(factor_3, 0);
                                    beanAve.setPrevYear3MAve(aveTrimestral.toString(), (month[i] + 2) / 3 - 1);
                                }
                            } else
                            if((month[i] + 2) % 3 == 0)
                            {
                                aveTrimestral = aveTrimestral.add(ave[i]);
                                aveTrimestral = aveTrimestral.divide(factor_3, 0);
                                beanAve.setPrevYear3MAve(aveTrimestral.toString(), (month[i] + 2) / 3 - 1);
                                aveTrimestral = new BigDecimal(0);
                            }
                        }

                    }
                    catch(Exception e)
                    {
                        flexLog("Read error " + e);
                    }
                }
                currAnnualAve = currAnnualAve.divide(factor_12, 0);
                prevAnnualAve = prevAnnualAve.divide(factor_12, 0);
                if(!graph.equals(""))
                {
                    String appHeader = "<applet archive=\"eibs_applets.zip\" code=\"datapro.eibs.applets.graph.Chart.cla" +
"ss\" width=100% height=350 align=\"absmiddle\"  codebase=\""
 + SuperServlet.webAppPath + LangPath + "\">";
                    graph = appHeader + "<param name=title value=\"\">" + "<param name=columns value=" + col + ">" + "<param name=orientation value=\"vertical\">" + "<param name=scale value=\"1\">" + graph + "</applet>";
                }
                beanAve.setCurrYearAve(currAnnualAve.toString());
                beanAve.setPrevYearAve(prevAnnualAve.toString());
                beanAve.setGraph(graph);
                flexLog("Putting java beans into the session");
                ses.setAttribute("aveBean", beanAve);
                ses.setAttribute("header", msgHeader);
                ses.setAttribute("userPO", userPO);
                try
                {
                    flexLog("About to call Page: " + LangPath + "ECIF040_averages_rt.jsp");
                    callPage(LangPath + "ECIF040_averages_rt.jsp", req, res);
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

    protected void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        ECIF04001Message msgAverage = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try
        {
            msgAverage = new ECIF04001Message();
            msgError = new ELEERRMessage();
            userPO = new UserPos();
            userPO.setOption("AVERAGE");
            userPO.setPurpose("");
            ses.setAttribute("average", msgAverage);
            ses.setAttribute("error", msgError);
            ses.setAttribute("userPO", userPO);
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        try
        {
            flexLog("About to call Page: " + LangPath + "ECIF040_averages_enter.jsp");
            callPage(LangPath + "ECIF040_averages_enter.jsp", req, res);
        }
        catch(Exception e)
        {
            flexLog("Exception calling page " + e);
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        Socket s;
        HttpSession session;
        int screen;
        s = null;
        MessageContext mc = null;
        ESS0030DSMessage msgUser = null;
        session = null;
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
            //break MISSING_BLOCK_LABEL_530;
        }
        screen = 100;
        Exception e;
         msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
        LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
        try
        {
            flexLog("Opennig Socket Connection");
            s = new Socket(SuperServlet.hostIP, getInitSocket(req) + 1);
            s.setSoTimeout(SuperServlet.sckTimeOut);
             mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
            try
            {
                screen = Integer.parseInt(req.getParameter("SCREEN"));
            }
            // Misplaced declaration of an exception variable
            catch(Exception e1)
            {
                flexLog("Screen set to default value");
            }
            switch(screen)
            {
            case 1: // '\001'
                procReqAverage(mc, msgUser, req, res, session);
                break;

            case 100: // 'd'
                procReqEnter(msgUser, req, res, session);
                break;

            case 200: 
                procActionEnter(mc, msgUser, req, res, session);
                break;

            default:
                res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.devPage);
                break;

            case 2: // '\002'
                break;
            }
        }
        // Misplaced declaration of an exception variable
        catch(Exception e1)
        {
            e1.printStackTrace();
            int sck = getInitSocket(req) + 1;
            flexLog("Socket not Open(Port " + sck + "). Error: " + e1);
            res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotOpenPage);
        }
        finally
        {
            s.close();
        }
        //break MISSING_BLOCK_LABEL_530;
        //e;
        //flexLog("Error: " + e);
        res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotRespondPage);
    }

    protected void showERROR(ELEERRMessage m)
    {
        if(logType != 0)
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

    private String getColor(int color)
    {
        String myColor = "red";
        switch(color)
        {
        case 1: // '\001'
            myColor = "red";
            break;

        case 2: // '\002'
            myColor = "green";
            break;

        case 3: // '\003'
            myColor = "blue";
            break;

        case 4: // '\004'
            myColor = "pink";
            break;

        case 5: // '\005'
            myColor = "orange";
            break;

        case 6: // '\006'
            myColor = "magenta";
            break;

        case 7: // '\007'
            myColor = "cyan";
            break;

        case 8: // '\b'
            myColor = "white";
            break;

        case 9: // '\t'
            myColor = "yellow";
            break;

        case 10: // '\n'
            myColor = "gray";
            break;

        case 11: // '\013'
            myColor = "darkGray";
            break;

        case 12: // '\f'
            myColor = "red";
            break;
        }
        return myColor;
    }
}
