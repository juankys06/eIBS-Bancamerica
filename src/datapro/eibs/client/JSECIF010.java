package datapro.eibs.client;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.products.JOActionRedirect;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class JSECIF010 extends SuperServlet
{

    protected static final int R_SEARCH = 1;
    protected static final int A_SEARCH = 2;
    protected static final int R_LIST = 3;
    protected static final int A_LIST = 4;
    protected static final int R_TOTAL = 5;
    protected static final int A_TOTAL = 6;
    protected static final int R_PRODUCTS = 7;
    protected static final int A_PRODUCTS = 8;
    protected static final int R_ACCOUNT = 9;
    protected static final int A_ACCOUNT = 10;
    protected static final int R_POSITION = 11;
    protected static final int A_POSITION = 12;
    protected static final int A_LIST_TDC = 13;
    protected static final int R_ENTER_ACC = 100;
    protected static final int A_ENTER_ACC = 400;
    protected String LangPath;

    public JSECIF010()
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

    protected void procActionEnterAcc(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
    	    throws ServletException, IOException
    	  {
    	    MessageRecord newmessage = null;
    	    ECIF01007Message msgAcc = null;
    	    ELEERRMessage msgError = null;
    	    UserPos userPO = null;
    	    try
    	    {
    	      msgError = new ELEERRMessage();
    	    } catch (Exception ex) {
    	      flexLog("Error: " + ex);
    	    }

    	    userPO = (UserPos)ses.getAttribute("userPO");
    	    try
    	    {
    	      msgAcc = (ECIF01007Message)mc.getMessageRecord("ECIF01007");
    	      msgAcc.setH07USERID(user.getH01USR());
    	      msgAcc.setH07PROGRM("EDL0160");
    	      msgAcc.setH07TIMSYS(getTimeStamp());
    	      try {
    	        msgAcc.setH07FLGWK1(userPO.getHeader8());
    	      } catch (Exception localException1) {
    	      }
    	      msgAcc.setH07SCRCOD("01");
    	      msgAcc.setH07OPECOD("0004");
    	      flexLog("ECIF010 Enter Account Header Sent");
    	      try {
    	        msgAcc.setE07ACCNUM(req.getParameter("ACCNUM"));       
    	      } catch (Exception e) {
    	        msgAcc.setE07ACCNUM("0");
    	      }
    	      msgAcc.send();
    	      msgAcc.destroy();
    	    } catch (Exception e) {
    	      e.printStackTrace();
    	      flexLog("ECIF010 Error: " + e);
    	      throw new RuntimeException("Socket Communication Error");
    	    }

    	    try
    	    {
    	      newmessage = mc.receiveMessage();

    	      if (newmessage.getFormatName().equals("ELEERR")) {
    	        msgError = (ELEERRMessage)newmessage;

    	        ses.setAttribute("error", msgError);

    	        if (!msgError.getERRNUM().equals("0")) {
    	          try {
    	            flexLog("About to call Page: " + this.LangPath + 
    	              "ECIF010_cif_enter_acc.jsp");
    	            callPage(this.LangPath + "ECIF010_cif_enter_acc.jsp", req, 
    	              res);
    	          } catch (Exception e) {
    	            flexLog("Exception calling page " + e);
    	          }
    	        }

    	      }

    	      if (!newmessage.getFormatName().equals("ECIF01007")) {
    	        newmessage = mc.receiveMessage();
    	      }

    	      if (newmessage.getFormatName().equals("ECIF01007")) {
    	        try {
    	          msgAcc = new ECIF01007Message();
    	        } catch (Exception ex) {
    	          flexLog("Error: " + ex);
    	        }
    	        
    	        
    	       if(!req.getParameter("Mess").equals("")){
    			        msgAcc = (ECIF01007Message)newmessage;
    			        String mes=req.getParameter("Mess").toString();
    			        int ano=Integer.parseInt(req.getParameter("Ano"))+2000;
    			        int option = Integer.parseInt(req.getParameter("opt"));
    			        int appCode = Integer.parseInt(msgAcc.getE07APCCDE());
    			        String accNum = msgAcc.getE07ACCNUM();
    		
    			        JOActionRedirect red = new JOActionRedirect(
    			          option, appCode, accNum, this.LangPath, ses);
    			        res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.client.JSECIF040?SCREEN=200&E01ACCNUM="+accNum+"&Mess="+mes+"&Ano="+ano);
    		     }else{
    			        msgAcc = (ECIF01007Message)newmessage;
    			
    			        int option = Integer.parseInt(req.getParameter("opt"));
    			        int appCode = Integer.parseInt(msgAcc.getE07APCCDE());
    			        String accNum = msgAcc.getE07ACCNUM();
    			        String mes="";
    			        JOActionRedirect red = new JOActionRedirect(
    			          option, appCode, accNum, this.LangPath, ses);
    			        res.sendRedirect(SuperServlet.srctx + red.action()+"&Mess="+mes);
    		      }
    	      } else {
    	        flexLog("Message " + newmessage.getFormatName() + " received.");
    	      }
    	    } catch (Exception e) {
    	      e.printStackTrace();
    	      flexLog("Error: " + e);
    	      throw new RuntimeException("Socket Communication Error");
    	    }
    	  }

    protected void procActionList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
    {
        UserPos userPO = null;
        try
        {
            userPO = (UserPos)ses.getAttribute("userPO");
            int option = Integer.parseInt(req.getParameter("opt"));
            String clientNum = req.getParameter("CUSTOMER");
            userPO.setCusNum(clientNum);
            ses.setAttribute("userPO", userPO);
            switch(option)
            {
            case 1: // '\001'
                procReqTotal(mc, user, req, res, ses);
                break;

            case 2: // '\002'
                procReqPos(mc, user, req, res, ses);
                break;

            case 3: // '\003'
                procReqProd(mc, user, req, res, ses);
                break;

            case 4: // '\004'
                procReqAcc(mc, user, req, res, ses);
                break;

            case 5: // '\005'
                res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1&Pos=0");
                break;

            case 6: // '\006'
                res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.client.JSERA0000?SCREEN=7");
                break;

            case 9: // '\t'
                res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.products.JSECP001?SCREEN=2&CUSTOMER=" + clientNum);
                break;

            case 10: // '\n'
                res.sendRedirect(SuperServlet.srctx + "/servlet/datapro.eibs.invest.JSEIE0300?SCREEN=1&CUSTOMER=" + clientNum);
                break;

            case 7: // '\007'
            case 8: // '\b'
            default:
                res.sendRedirect(SuperServlet.srctx + LangPath + "Under_construction.jsp");
                break;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void procActionSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        UserPos userPO = null;
        try
        {
            userPO = (UserPos)ses.getAttribute("userPO");
            int type = Integer.parseInt(req.getParameter("Type"));
            String clientNum = req.getParameter("NameSearch").toUpperCase();
            userPO.setCusNum(clientNum);
            ses.setAttribute("userPO", userPO);
            switch(type)
            {
            case 1: // '\001'
                procReqTotal(mc, user, req, res, ses);
                break;

            default:
                procReqList(mc, user, req, res, ses);
                break;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void procActionSearch_Prev(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        ELEERRMessage msgError = null;
        ECIF01001Message msgSearch = null;
        ECIF01002Message msgList = null;
        ECIF01003Message msgTotal = null;
        JBList beanList = null;
        UserPos userPO = null;
        userPO = (UserPos)ses.getAttribute("userPO");
        int type = 0;
        String num = "";
        try
        {
            flexLog("Send Initial Data");
            msgSearch = (ECIF01001Message)mc.getMessageRecord("ECIF01001");
            msgSearch.setH01USERID(user.getH01USR());
            msgSearch.setH01PROGRM("ECIF010");
            msgSearch.setH01TIMSYS(getTimeStamp());
            msgSearch.setH01SCRCOD("01");
            msgSearch.setH01OPECOD("0004");
            try
            {
                type = Integer.parseInt(req.getParameter("Type"));
                num = req.getParameter("NameSearch").toUpperCase();
                msgSearch.setE01SELTYP((new StringBuffer(String.valueOf(type))).toString());
                switch(type)
                {
                case 1: // '\001'
                    msgSearch.setE01SELCUN(num);
                    break;

                case 5: // '\005'
                    msgSearch.setE01SELACC(num);
                    break;

                case 2: // '\002'
                case 3: // '\003'
                case 4: // '\004'
                default:
                    msgSearch.setE01SELNME(num);
                    break;
                }
                try
                {
                    msgSearch.setE01NUMREC(req.getParameter("Pos"));
                }
                catch(Exception ex)
                {
                    msgSearch.setE01NUMREC("0");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                flexLog("Input data error " + e);
            }
            msgSearch.send();
            msgSearch.destroy();
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
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                if(!msgError.getERRNUM().equals("0"))
                {
                    try
                    {
                        flexLog("About to call Page: " + LangPath + "ECIF010_cif_client_search.jsp");
                        callPage(LangPath + "ECIF010_cif_client_search.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
                }
            }
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ECIF01002"))
            {
                try
                {
                    beanList = new JBList();
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                try
                {
                    beanList.setSearchText(num);
                    beanList.setSearchType((new StringBuffer(String.valueOf(type))).toString());
                }
                catch(Exception e)
                {
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
                do
                {
                    msgList = (ECIF01002Message)newmessage;
                    marker = msgList.getE02INDOPE();
                    if(marker.equals("*"))
                    {
                        beanList.setShowNext(false);
                        break;
                    }
                    if(firstTime)
                    {
                        firstTime = false;
                        beanList.setFirstRec(Integer.parseInt(msgList.getE02NUMREC()));
                        chk = "checked";
                    } else
                    {
                        chk = "";
                    }
                    myRow = new StringBuffer("<TR>");
                    myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgList.getE02CUSCUN() + "\" " + chk + "></TD>");
                    myRow.append("<TD NOWRAP>" + msgList.getE02CUSCUN() + "</TD>");
                    myRow.append("<TD NOWRAP>" + msgList.getE02CUSNA1() + "</TD>");
                    myRow.append("<TD NOWRAP>" + msgList.getE02CUSSHN() + "</TD>");
                    myRow.append("<TD NOWRAP>" + msgList.getE02CUSIDN() + "</TD>");
                    myRow.append("<TD NOWRAP>" + msgList.getE02CUSTID() + "</TD>");
                    myRow.append("<TD NOWRAP>" + msgList.getE02CUSPID() + "</TD>");
                    myRow.append("<TD NOWRAP>" + msgList.getE02CLILGT() + "</TD>");
                    myRow.append("<TD NOWRAP>" + msgList.getE02CLITYP() + "</TD>");
                    myRow.append("<TD NOWRAP>" + msgList.getE02CLISTS() + "</TD>");
                    myRow.append("</TR>");
                    beanList.addRow(myFlag, myRow.toString());
                    if(marker.equals("+"))
                    {
                        beanList.setShowNext(true);
                        break;
                    }
                    newmessage = mc.receiveMessage();
                } while(true);
                flexLog("Putting java beans into the session");
                ses.setAttribute("cifList", beanList);
                try
                {
                    flexLog("About to call Page: " + LangPath + "ECIF010_cif_list.jsp");
                    callPage(LangPath + "ECIF010_cif_list.jsp", req, res);
                }
                catch(Exception e)
                {
                    flexLog("Exception calling page " + e);
                }
            } else
            if(newmessage.getFormatName().equals("ECIF01003"))
            {
                try
                {
                    msgTotal = new ECIF01003Message();
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                msgTotal = (ECIF01003Message)newmessage;
                flexLog("Putting java beans into the session");
                ses.setAttribute("cifTotal", msgTotal);
                try
                {
                    flexLog("About to call Page: " + LangPath + "ECIF010_cif_total.jsp");
                    callPage(LangPath + "ECIF010_cif_total.jsp", req, res);
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

    protected void procReqAcc(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        ECIF01006Message msgList = null;
        ELEERRMessage msgError = null;
        JBList beanList = null;
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
            flexLog("Send Initial Data");
            msgList = (ECIF01006Message)mc.getMessageRecord("ECIF01006");
            msgList.setH06USERID(user.getH01USR());
            msgList.setH06PROGRM("ECIF010");
            msgList.setH06TIMSYS(getTimeStamp());
            msgList.setH06SCRCOD("01");
            msgList.setH06OPECOD("0004");
            msgList.setE06SELCUN(userPO.getCusNum());
            flexLog("ECIF01006 Header Sent");
            try
            {
                String s = req.getParameter("appCode");
                msgList.setE06SELACD(s);
            }
            catch(Exception ex)
            {
                msgList.setE06SELACD("");
            }
            try
            {
                String s = req.getParameter("flag");
                msgList.setE06SELFLG(s);
            }
            catch(Exception ex)
            {
                msgList.setE06SELFLG("");
            }
            try
            {
                String s = req.getParameter("prodType");
                msgList.setE06SELTYP(s);
            }
            catch(Exception ex)
            {
                msgList.setE06SELTYP("");
            }
            try
            {
                String s = req.getParameter("prodCode");
                msgList.setE06SELPRO(s);
            }
            catch(Exception ex)
            {
                msgList.setE06SELPRO("");
            }
            try
            {
                String s = req.getParameter("ofc");
                msgList.setE06ACCTYP(s);
                msgList.setH06SCRCOD("OF");
            }
            catch(Exception ex)
            {
                msgList.setE06ACCTYP("");
            }
            msgList.send();
            msgList.destroy();
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
                if(!msgError.getERRNUM().equals("0"))
                {
                    flexLog("Putting java beans into the session");
                    ses.setAttribute("error", msgError);
                    try
                    {
                        res.sendRedirect(SuperServlet.srctx + LangPath + "Under_construction.jsp");
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
                }
            } else
            if(newmessage.getFormatName().equals("ECIF01006"))
            {
                try
                {
                    beanList = new JBList();
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                boolean firstTime = true;
                String marker = "";
                String myFlag = "";
                StringBuffer myRow = null;
                String chk = "";
                int indexRow = 0;
                BigDecimal sumTotal = new BigDecimal("0");
                do
                {
                    msgList = (ECIF01006Message)newmessage;
                    marker = msgList.getE06INDOPE();
                    if(marker.equals("*"))
                    {
                        beanList.setShowNext(false);
                        break;
                    }
                    if(firstTime)
                    {
                        firstTime = false;
                        chk = "checked";
                    } else
                    {
                        chk = "";
                    }
                    myFlag = "";
                    myRow = new StringBuffer("<TR>");
                    myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE06ACCNUM() + "\" " + chk + " onclick=\"showAddInfo(" + indexRow + ")\"></TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE06ACCNUM()) + "</TD>");
                    myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE06ACCSTS()) + "</TD>");
                    myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE06PROCDE()) + "</TD>");
                    myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE06CCYCDE()) + "</TD>");
                    myRow.append("<TD NOWRAP align=right>" + Util.fcolorCCY(msgList.getE06PRIAMN()) + "</TD>");
                    myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE06OFFICR()));
                    myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA" + indexRow + "\" VALUE=\"" + Util.formatCell(msgList.getE06BNKNUM()) + "<br>");
                    myRow.append(Util.formatCell(msgList.getE06BRNNUM()) + "<br>");
                    myRow.append(Util.formatDate(msgList.getE06OPEDT1(), msgList.getE06OPEDT2(), msgList.getE06OPEDT3()) + "<br>");
                    myRow.append(Util.fcolorCCY(msgList.getE06PRIAMN()) + "<br>");
                    myRow.append(Util.fcolorCCY(msgList.getE06INTAMN()) + "<br>");
                    myRow.append(Util.fcolorCCY(msgList.getE06OTHAMN()) + "<br>");
                    myRow.append(Util.fcolorCCY(msgList.getE06TOTAMN()) + "\">");
                    myRow.append("<INPUT TYPE=HIDDEN NAME=\"CODACD" + indexRow + "\" VALUE=\"" + Util.formatCell(msgList.getE06SELACD()) + "\"></TD>");
                    myRow.append("</TR>");
                    beanList.addRow(myFlag, myRow.toString());
                    indexRow++;
                    sumTotal = sumTotal.add(msgList.getBigDecimalE06PRIAMN());
                    newmessage = mc.receiveMessage();
                } while(true);
                flexLog("Putting java beans into the session");
                ses.setAttribute("cifAcc", beanList);
                if(msgList.getH06SCRCOD().equals("OF"))
                {
                    try
                    {
                        req.setAttribute("Total", sumTotal);
                        flexLog("About to call Page: " + LangPath + "ECIF170_cif_accounts.jsp");
                        callPage(LangPath + "ECIF170_cif_accounts.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
                } else
                {
                    try
                    {
                        req.setAttribute("Total", sumTotal);
                        flexLog("About to call Page: " + LangPath + "ECIF010_cif_accounts.jsp");
                        callPage(LangPath + "ECIF010_cif_accounts.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
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

    protected void procReqEnterAcc(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try
        {
            msgError = new ELEERRMessage();
            userPO = new UserPos();
            userPO.setOption("ACCOUNTS");
            userPO.setPurpose("INQUIRY");
            String opt = null;
            try
            {
                opt = req.getParameter("OPTION");
                if(opt == null)
                {
                    opt = "";
                }
            }
            catch(Exception e)
            {
                opt = "";
            }
            userPO.setHeader8(opt);
            ses.setAttribute("error", msgError);
            ses.setAttribute("userPO", userPO);
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        try
        {
            flexLog("About to call Page: " + LangPath + "ECIF010_cif_enter_acc.jsp");
            callPage(LangPath + "ECIF010_cif_enter_acc.jsp", req, res);
        }
        catch(Exception e)
        {
            flexLog("Exception calling page " + e);
        }
    }

    protected void procReqList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        ECIF01002Message msgList = null;
        ECIF01001Message msgSearch = null;
        ELEERRMessage msgError = null;
        JBList beanList = null;
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
        int type = 0;
        String num = "";
        try
        {
            flexLog("Send Initial Data");
            msgSearch = (ECIF01001Message)mc.getMessageRecord("ECIF01001");
            msgSearch.setH01USERID(user.getH01USR());
            msgSearch.setH01PROGRM("ECIF010");
            msgSearch.setH01TIMSYS(getTimeStamp());
            msgSearch.setH01FLGWK1(userPO.getHeader8());
            msgSearch.setH01SCRCOD("01");
            msgSearch.setH01OPECOD("0004");
            flexLog("ECIF01001 Header Sent");
            try
            {
                type = Integer.parseInt(req.getParameter("Type"));
                num = req.getParameter("NameSearch").toUpperCase();
                msgSearch.setE01SELTYP((new StringBuffer(String.valueOf(type))).toString());
                switch(type)
                {
                case 1: // '\001'
                    msgSearch.setE01SELCUN(num);
                    break;

                case 5: // '\005'
                    msgSearch.setE01SELACC(num);
                    break;

                case 2: // '\002'
                case 3: // '\003'
                case 4: // '\004'
                default:
                    msgSearch.setE01SELNME(num);
                    break;
                }
                try
                {
                    msgSearch.setE01NUMREC(req.getParameter("Pos"));
                }
                catch(Exception ex)
                {
                    msgSearch.setE01NUMREC("0");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                flexLog("Input data error " + e);
            }
            msgSearch.send();
            msgSearch.destroy();
            flexLog("ECIF01006 Message Sent");
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
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                if(!msgError.getERRNUM().equals("0"))
                {
                    try
                    {
                        flexLog("About to call Page: " + LangPath + "ECIF010_cif_client_search.jsp");
                        callPage(LangPath + "ECIF010_cif_client_search.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
                }
            }
            if(newmessage.getFormatName().equals("ELEERR") && msgError.getERRNUM().equals("0"))
            {
                newmessage = mc.receiveMessage();
            }
            if(newmessage.getFormatName().equals("ECIF01002"))
            {
                try
                {
                    beanList = new JBList();
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                try
                {
                    beanList.setSearchText(num);
                    beanList.setSearchType((new StringBuffer(String.valueOf(type))).toString());
                }
                catch(Exception e)
                {
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
                do
                {
                    msgList = (ECIF01002Message)newmessage;
                    marker = msgList.getE02INDOPE();
                    if(marker.equals("*"))
                    {
                        beanList.setShowNext(false);
                        break;
                    }
                    if(firstTime)
                    {
                        firstTime = false;
                        beanList.setFirstRec(Integer.parseInt(msgList.getE02NUMREC()));
                        chk = "checked";
                    } else
                    {
                        chk = "";
                    }
                    myRow = new StringBuffer("<TR>");
                    myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CUSTOMER\" value=\"" + msgList.getE02CUSCUN() + "\" " + chk + " onclick=\"showAddInfo(" + indexRow + ")\"></TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE02CUSCUN()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE02CUSNA1()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE02CUSSHN()));
                    myRow.append("<INPUT TYPE=HIDDEN NAME=\"TXTDATA" + indexRow + "\" VALUE=\"" + Util.formatCell(msgList.getE02CUSIDN()) + "<br>");
                    myRow.append(Util.formatCell(msgList.getE02CUSTID()) + "<br>");
                    myRow.append(Util.formatCell(msgList.getE02CUSPID()) + "<br>");
                    myRow.append(Util.formatCell(msgList.getE02CLISTS()) + "<br>");
                    myRow.append(Util.formatCell(msgList.getE02CLILGT()) + "\"></TD>");
                    myRow.append("</TR>");
                    beanList.addRow(myFlag, myRow.toString());
                    indexRow++;
                    if(marker.equals("+"))
                    {
                        beanList.setShowNext(true);
                        break;
                    }
                    newmessage = mc.receiveMessage();
                } while(true);
                flexLog("Putting java beans into the session");
                ses.setAttribute("cifList", beanList);
                try
                {
                    flexLog("About to call Page: " + LangPath + "ECIF010_cif_list.jsp");
                    callPage(LangPath + "ECIF010_cif_list.jsp", req, res);
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

    protected void procReqPos(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        ECIF01005Message msgList = null;
        ELEERRMessage msgError = null;
        JBList beanList = null;
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
            flexLog("Send Initial Data");
            msgList = (ECIF01005Message)mc.getMessageRecord("ECIF01005");
            msgList.setH05USERID(user.getH01USR());
            msgList.setH05PROGRM("ECIF010");
            msgList.setH05TIMSYS(getTimeStamp());
            msgList.setH05SCRCOD("01");
            msgList.setH05OPECOD("0004");
            msgList.setE05CUSCUN(userPO.getCusNum());
            msgList.send();
            msgList.destroy();
            flexLog("ECIF01005 Message Sent");
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
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                if(!msgError.getERRNUM().equals("0"))
                {
                    try
                    {
                        flexLog("About to call Page: " + LangPath + "ECIF010_cif_client_search.jsp");
                        callPage(LangPath + "ECIF010_cif_client_search.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
                }
            }
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ECIF01005"))
            {
                try
                {
                    beanList = new JBList();
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                boolean firstTime = true;
                String chk = "";
                String marker = "";
                String myFlag = "";
                StringBuffer myRow = null;
                BigDecimal totalAssets = new BigDecimal("0");
                BigDecimal totalLiabilities = new BigDecimal("0");
                BigDecimal netPosition = new BigDecimal("0");
                do
                {
                    msgList = (ECIF01005Message)newmessage;
                    marker = msgList.getE05INDOPE();
                    if(marker.equals("*"))
                    {
                        beanList.setShowNext(false);
                        break;
                    }
                    if(firstTime)
                    {
                        firstTime = false;
                        chk = "checked";
                        userPO.setHeader1(msgList.getE05CUSNA1().trim());
                        userPO.setHeader2(msgList.getE05CUSSTS().trim());
                    } else
                    {
                        chk = "";
                    }
                    myFlag = msgList.getE05CLSACC();
                    if(myFlag.trim().equals("1"))
                    {
                        totalAssets = totalAssets.add(msgList.getBigDecimalE05BSETOT());
                    } else
                    if(myFlag.trim().equals("2"))
                    {
                        totalLiabilities = totalLiabilities.add(msgList.getBigDecimalE05BSETOT());
                    }
                    myRow = new StringBuffer("<TR>");
                    myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE05ACCNUM() + "\" " + chk + "></TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05ACCNUM()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05ACCTYP()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05TYPDSC()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05ACCSTS()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE05CCYCDE()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatDate(msgList.getE05OPEDT1(), msgList.getE05OPEDT2(), msgList.getE05OPEDT3()) + "</TD>");
                    myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE05BSEPRI()) + "</TD>");
                    myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE05BSEINT()) + "</TD>");
                    myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE05BSEOTH()) + "</TD>");
                    myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE05BSETOT()) + "</TD>");
                    myRow.append("</TR>");
                    beanList.addRow(myFlag, myRow.toString());
                    newmessage = mc.receiveMessage();
                } while(true);
                netPosition = totalAssets.subtract(totalLiabilities);
                userPO.setHeader3(Util.fcolorCCY(totalAssets.toString()));
                userPO.setHeader4(Util.fcolorCCY(totalLiabilities.toString()));
                userPO.setHeader5(Util.fcolorCCY(netPosition.toString()));
                flexLog("Putting java beans into the session");
                ses.setAttribute("cifPos", beanList);
                ses.setAttribute("userPO", userPO);
                try
                {
                    flexLog("About to call Page: " + LangPath + "ECIF010_cif_position.jsp");
                    callPage(LangPath + "ECIF010_cif_position.jsp", req, res);
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
    protected void procReqTDC(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
            throws ServletException, IOException
        {
            MessageRecord newmessage = null;
            ECIF01009Message msgList = null;
            ELEERRMessage msgError = null;
            JBList beanList = null;
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
                flexLog("Send Initial Data");
                msgList = (ECIF01009Message)mc.getMessageRecord("ECIF01009");
                msgList.setH09USERID(user.getH01USR());
                msgList.setH09PROGRM("ECIF010");
                msgList.setH09TIMSYS(getTimeStamp());
                msgList.setH09SCRCOD("09");
                msgList.setH09OPECOD("0009");
                msgList.setE09CUSCUN(userPO.getCusNum());
                msgList.send();
                msgList.destroy();
                flexLog("ECIF01009 Message Sent");
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
                    flexLog("Putting java beans into the session");
                    ses.setAttribute("error", msgError);
                    if(!msgError.getERRNUM().equals("0"))
                    {
                        try
                        {
                            flexLog("About to call Page: " + LangPath + "ECIF010_cif_client_search.jsp");
                            callPage(LangPath + "ECIF010_cif_client_search.jsp", req, res);
                        }
                        catch(Exception e)
                        {
                            flexLog("Exception calling page " + e);
                        }
                    }
                }
                newmessage = mc.receiveMessage();
                if(newmessage.getFormatName().equals("ECIF01009"))
                {
                    try
                    {
                        beanList = new JBList();
                    }
                    catch(Exception ex)
                    {
                        flexLog("Error: " + ex);
                    }
                    boolean firstTime = true;
                    String chk = "";
                    String marker = "";
                    String myFlag = "";
                    StringBuffer myRow = null;
                    BigDecimal totalAssets = new BigDecimal("0");
                    BigDecimal totalLiabilities = new BigDecimal("0");
                    BigDecimal netPosition = new BigDecimal("0");
                    do
                    {
                        msgList = (ECIF01009Message)newmessage;
                        marker = msgList.getE09INDOPE();
                        if(marker.equals("*"))
                        {
                            beanList.setShowNext(false);
                            break;
                        }
                        if(firstTime)
                        {
                            firstTime = false;
                            chk = "checked";
                           // userPO.setHeader1(msgList.getE09CUSNA1().trim());
                            //userPO.setHeader2(msgList.getE09CUSSTS().trim());
                        } else
                        {
                            chk = "";
                        }
                      
                        myRow = new StringBuffer("<TR>");
                        myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACCNUM\" value=\"" + msgList.getE09NRTA() + "\" " + chk + "></TD>");
                        myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE09NPLA()) + "</TD>");
                        myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE09DPRO()) + "</TD>");
                        myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE09TIID()) + "</TD>");      
                        myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE09NIDE()) + "</TD>");                        
                        myRow.append("</TR>");
                        beanList.addRow(myFlag, myRow.toString());
                        newmessage = mc.receiveMessage();
                    } while(true);
                    //netPosition = totalAssets.subtract(totalLiabilities);
                    //userPO.setHeader3(Util.fcolorCCY(totalAssets.toString()));
                    //userPO.setHeader4(Util.fcolorCCY(totalLiabilities.toString()));
                    //userPO.setHeader5(Util.fcolorCCY(netPosition.toString()));
                    flexLog("Putting java beans into the session");
                    ses.setAttribute("cifPos", beanList);
                    ses.setAttribute("userPO", userPO);
                    try
                    {
                        flexLog("About to call Page: " + LangPath + "ECIF010_cif_consultaTDClist.jsp");
                        callPage(LangPath + "ECIF010_cif_consultaTDClist.jsp", req, res);
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

    protected void procReqProd(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        ECIF01004Message msgList = null;
        ELEERRMessage msgError = null;
        JBList beanList = null;
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
            flexLog("Send Initial Data");
            msgList = (ECIF01004Message)mc.getMessageRecord("ECIF01004");
            msgList.setH04USERID(user.getH01USR());
            msgList.setH04PROGRM("ECIF010");
            msgList.setH04TIMSYS(getTimeStamp());
            msgList.setH04SCRCOD("01");
            msgList.setH04OPECOD("0004");
            msgList.setE04CUSCUN(userPO.getCusNum());
            msgList.send();
            msgList.destroy();
            flexLog("ECIF01004 Message Sent");
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
            if(newmessage.getFormatName().equals("ECIF01004"))
            {
                try
                {
                    beanList = new JBList();
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                boolean firstTime = true;
                String marker = "";
                String myFlag = "";
                StringBuffer myRow = null;
                BigDecimal totalAssets = new BigDecimal("0");
                BigDecimal totalLiabilities = new BigDecimal("0");
                BigDecimal netPosition = new BigDecimal("0");
                String graphAssets = "";
                String graphLiabilities = "";
                int colAssets = 0;
                int colLiabilities = 0;
                do
                {
                    msgList = (ECIF01004Message)newmessage;
                    marker = msgList.getE04INDOPE();
                    flexLog("marker = " + marker);
                    if(marker.equals("*"))
                    {
                        beanList.setShowNext(false);
                        break;
                    }
                    if(firstTime)
                    {
                        firstTime = false;
                        userPO.setHeader1(msgList.getE04CUSNA1());
                        userPO.setHeader2(msgList.getE04CUSSTS());
                    }
                    myFlag = msgList.getE04CLSACC();
                    if(myFlag.trim().equals("1"))
                    {
                        totalAssets = totalAssets.add(msgList.getBigDecimalE04BSETOT());
                        colAssets++;
                        graphAssets = graphAssets + "<param name=c" + colAssets + "_label value=\"" + msgList.getE04PROCDE() + "\">";
                        graphAssets = graphAssets + "<param name=c" + colAssets + " value=\"" + msgList.getE04BSEPRI() + "\">";
                        graphAssets = graphAssets + "<param name=c1" + colAssets + "color value=\"red\">";
                        graphAssets = graphAssets + "<param name=c1" + colAssets + "style value=\"solid\">";
                        colAssets++;
                        graphAssets = graphAssets + "<param name=c" + colAssets + "_label value=\"\">";
                        graphAssets = graphAssets + "<param name=c" + colAssets + " value=\"" + msgList.getE04BSEINT() + "\">";
                        graphAssets = graphAssets + "<param name=c" + colAssets + "_color value=\"blue\">";
                        graphAssets = graphAssets + "<param name=c" + colAssets + "_style value=\"solid\">";
                    } else
                    if(myFlag.trim().equals("2"))
                    {
                        totalLiabilities = totalLiabilities.add(msgList.getBigDecimalE04BSETOT());
                        colLiabilities++;
                        graphLiabilities = graphLiabilities + "<param name=c" + colLiabilities + "_label value=\"" + msgList.getE04PROCDE() + "\">";
                        graphLiabilities = graphLiabilities + "<param name=c" + colLiabilities + " value=\"" + msgList.getE04BSEPRI() + "\">";
                        graphLiabilities = graphLiabilities + "<param name=c" + colLiabilities + "_color value=\"red\">";
                        graphLiabilities = graphLiabilities + "<param name=c" + colLiabilities + "_style value=\"solid\">";
                        colLiabilities++;
                        graphLiabilities = graphLiabilities + "<param name=c" + colLiabilities + "_label value=\"\">";
                        graphLiabilities = graphLiabilities + "<param name=c" + colLiabilities + " value=\"" + msgList.getE04BSEINT() + "\">";
                        graphLiabilities = graphLiabilities + "<param name=c" + colLiabilities + "_color value=\"blue\">";
                        graphLiabilities = graphLiabilities + "<param name=c" + colLiabilities + "_style value=\"solid\">";
                    }
                    myRow = new StringBuffer("<TR>");
                    myRow.append("<TD NOWRAP><A HREF=\"javascript:showProdAcc('" + msgList.getE04APLCDE() + "','" + msgList.getE04FLGOPE() + "','" + msgList.getE04ACCTYP() + "','" + msgList.getE04PROCDE() + "')\">" + Util.formatCell(msgList.getE04PRODSC().trim()) + "</A></TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04PROCDE()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04BNKNUM()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04CCYCDE()) + "</TD>");
                    myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getE04NUMOPE()) + "</TD>");
                    myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSEPRI()) + "</TD>");
                    myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSEINT()) + "</TD>");
                    myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSEOTH()) + "</TD>");
                    myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.fcolorCCY(msgList.getE04BSETOT()) + "</TD>");
                    myRow.append("</TR>");
                    beanList.addRow(myFlag, myRow.toString());
                    newmessage = mc.receiveMessage();
                } while(true);
                String appHeader = "<TR><TD colspan=\"9\"><applet code=\"datapro.eibs.applets.graph.Chart.class\" wi" +
"dth=100% height=150 align=\"absmiddle\"  codebase=\""
 + SuperServlet.webAppPath + "/applets/\">";
                if(!graphAssets.equals(""))
                {
                    graphAssets = appHeader + "<param name=title value=\"\"><param name=columns value=" + colAssets + "><param name=orientation value=\"vertical\"><param name=scale value=\"1\">" + graphAssets + "</applet></TD></TR>";
                }
                if(!graphLiabilities.equals(""))
                {
                    graphLiabilities = appHeader + "<param name=title value=\"\"><param name=columns value=" + colLiabilities + "><param name=orientation value=\"vertical\"><param name=scale value=\"1\">" + graphLiabilities + "</applet></TD></TR>";
                }
                netPosition = totalAssets.subtract(totalLiabilities);
                userPO.setHeader3(Util.fcolorCCY(totalAssets.toString()));
                userPO.setHeader4(Util.fcolorCCY(totalLiabilities.toString()));
                userPO.setHeader5(Util.fcolorCCY(netPosition.toString()));
                userPO.setHeader6("");
                userPO.setHeader7("");
                flexLog("Putting java beans into the session");
                ses.setAttribute("cifProd", beanList);
                ses.setAttribute("userPO", userPO);
                try
                {
                    flexLog("About to call Page: " + LangPath + "ECIF010_cif_products.jsp");
                    callPage(LangPath + "ECIF010_cif_products.jsp", req, res);
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

    protected void procReqSearch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try
        {
            msgError = new ELEERRMessage();
            userPO = new UserPos();
            userPO.setOption("CIF");
            userPO.setPurpose("INQUIRY");
            String opt = null;
            try
            {
                opt = req.getParameter("OPTION");
                if(opt == null)
                {
                    opt = "";
                }
            }
            catch(Exception e)
            {
                opt = "";
            }
            userPO.setHeader8(opt);
            ses.setAttribute("error", msgError);
            ses.setAttribute("userPO", userPO);
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        try
        {
            flexLog("About to call Page: " + LangPath + "ECIF010_cif_client_search.jsp");
            callPage(LangPath + "ECIF010_cif_client_search.jsp", req, res);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Exception calling page " + e);
        }
    }

    protected void procReqTotal(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        ECIF01003Message msgTotal = null;
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
        String opCode = "0004";
        try
        {
            msgTotal = (ECIF01003Message)mc.getMessageRecord("ECIF01003");
            msgTotal.setH03USERID(user.getH01USR());
            msgTotal.setH03PROGRM("ESD0080");
            msgTotal.setH03TIMSYS(getTimeStamp());
            msgTotal.setH03FLGWK1(userPO.getHeader8());
            msgTotal.setH03SCRCOD("01");
            msgTotal.setH03OPECOD(opCode);
            try
            {
                msgTotal.setE03CUSCUN(req.getParameter("E03CUSCUN"));
            }
            catch(Exception ex)
            {
                msgTotal.setE03CUSCUN(userPO.getCusNum());
            }
            msgTotal.send();
            msgTotal.destroy();
            flexLog("ECIF01003 Message Sent");
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
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                if(!msgError.getERRNUM().equals("0"))
                {
                    try
                    {
                        flexLog("About to call Page: " + LangPath + "ECIF010_cif_client_search.jsp");
                        callPage(LangPath + "ECIF010_cif_client_search.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
                }
            }
            if(!newmessage.getFormatName().equals("ECIF01003"))
            {
                newmessage = mc.receiveMessage();
            }
            if(newmessage.getFormatName().equals("ECIF01003"))
            {
                try
                {
                    msgTotal = new ECIF01003Message();
                }
                catch(Exception ex)
                {
                    flexLog("Error: " + ex);
                }
                msgTotal = (ECIF01003Message)newmessage;
                flexLog("Putting java beans into the session");
                ses.setAttribute("error", msgError);
                ses.setAttribute("cifTotal", msgTotal);
                try
                {
                    flexLog("About to call Page: " + LangPath + "ECIF010_cif_total.jsp");
                    callPage(LangPath + "ECIF010_cif_total.jsp", req, res);
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
        MessageContext mc;
        HttpSession session;
        int screen;
        mc = null;
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
            //break MISSING_BLOCK_LABEL_611;
        }
        screen = 1;
        Exception e;
         msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
        LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
        try
        {
            flexLog("Opennig Socket Connection");
            mc = new MessageContext(super.getMessageHandler("ECIF010", req));
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
            case 100: // 'd'
                procReqEnterAcc(msgUser, req, res, session);
                break;

            case 1: // '\001'
                procReqSearch(msgUser, req, res, session);
                break;

            case 3: // '\003'
                procReqList(mc, msgUser, req, res, session);
                break;

            case 5: // '\005'
                procReqTotal(mc, msgUser, req, res, session);
                break;

            case 7: // '\007'
                procReqProd(mc, msgUser, req, res, session);
                break;

            case 9: // '\t'
                procReqAcc(mc, msgUser, req, res, session);
                break;

            case 11: // '\013'
                procReqPos(mc, msgUser, req, res, session);
                break;
            case 13: // '\013'
            	procReqTDC(mc, msgUser, req, res, session);
                break;     
            case 10: // '\n'
            case 12: // '\f'
            case 400: 
                procActionEnterAcc(mc, msgUser, req, res, session);
                break;

            case 2: // '\002'
                procActionSearch(mc, msgUser, req, res, session);
                break;

            case 4: // '\004'
                procActionList(mc, msgUser, req, res, session);
                break;

            default:
                res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.devPage);
                break;

            case 6: // '\006'
            case 8: // '\b'
                break;
            }
        }
        // Misplaced declaration of an exception variable
        catch(Exception e1)
        {
            e1.printStackTrace();
            res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotOpenPage);
        }
        finally
        {
            if(mc != null)
            {
                mc.close();
            }
        }
        //break MISSING_BLOCK_LABEL_611;
       // e;
       // flexLog("Error: " + e);
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
}
