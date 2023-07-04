package datapro.eibs.helps;


import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.products.JOActionRedirect;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

import java.text.SimpleDateFormat; 
import java.util.Date;

public class JSENOMBLQ extends datapro.eibs.master.SuperServlet {


		
	
	protected static final int R_List = 1;	
	protected static final int R_New = 2;	
	protected static final int R_Mante = 22;
	protected static final int R_Mod = 3;
	protected static final int R_Del = 4;

	protected String LangPath = "S";
	public JSENOMBLQ() {
		super();
	}

	public void destroy() {

		flexLog("free resources used by JSEFE0501");

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	
	protected void procReqNew(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException {

			ELEERRMessage msgError = null;
			UserPos userPO = null;
			MessageRecord newmessage = null;
			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
				userPO = new datapro.eibs.beans.UserPos();
				//userPO.setPurpose("MAINTENANCE");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("BlqMant", newmessage);
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			try {
				flexLog("About to call Page: " + LangPath + "ENOMBLQ_New.jsp");
				callPage(LangPath + "ENOMBLQ_New.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
	protected void procReqMod(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException {

			ELEERRMessage msgError = null;
			UserPos userPO = null;
			ENOMBLQ01Message msgList = null;
			MessageRecord newmessage = null;
			String acc=req.getParameter("ACC").trim();
			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
				userPO = new datapro.eibs.beans.UserPos();
				//userPO.setPurpose("MAINTENANCE");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			try
	   	    {
	   	    	msgList = (ENOMBLQ01Message)mc.getMessageRecord("ENOMBLQ01");
	   	    	msgList.setH01USERID(user.getH01USR());
	   	    	msgList.setH01PROGRM("ENOMBLQ");
	   	    	msgList.setH01TIMSYS(getTimeStamp());
	   	    	msgList.setH01SCRCOD("");
	   	    	msgList.setH01OPECOD("0004"); 
	   	    	msgList.setE01NOMCRD(acc);
	   	    	
	   	    	
	   	      flexLog("EFE0501DS Enter Account Header Sent");    	     
	   	      msgList.send();   	     
	   	      msgList.destroy();
	   	    } catch (Exception e) {
	   	      e.printStackTrace();
	   	      flexLog("ENOMBLQ01 Error: " + e);
	   	      throw new RuntimeException("Socket Communication Error");
	   	    }

			try {
				newmessage = mc.receiveMessage();
				
				if (newmessage.getFormatName().equals("ENOMBLQ01")) {
					msgList = (ENOMBLQ01Message) newmessage;   			
					msgList.setH01FLGWK3("M");
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);	
					ses.setAttribute("BlqMant", newmessage);

				flexLog("About to call Page: " + LangPath + "ENOMBLQ_New.jsp");
				callPage(LangPath + "ENOMBLQ_New.jsp", req, res);
			}
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
	protected void procReqDel(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException {

			ELEERRMessage msgError = null;
			UserPos userPO = null;
			ENOMBLQ01Message msgList = null;
			MessageRecord newmessage = null;
			String acc=req.getParameter("ACC").trim();
			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
				userPO = new datapro.eibs.beans.UserPos();
				//userPO.setPurpose("MAINTENANCE");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			try
	   	    {
	   	    	msgList = (ENOMBLQ01Message)mc.getMessageRecord("ENOMBLQ01");
	   	    	msgList.setH01USERID(user.getH01USR());
	   	    	msgList.setH01PROGRM("ENOMBLQ");
	   	    	msgList.setH01TIMSYS(getTimeStamp());
	   	    	msgList.setH01SCRCOD("");
	   	    	msgList.setH01OPECOD("0004"); 
	   	    	msgList.setE01NOMCRD(acc);
	   	    	
	   	    	
	   	      flexLog("EFE0501DS Enter Account Header Sent");    	     
	   	      msgList.send();   	     
	   	      msgList.destroy();
	   	    } catch (Exception e) {
	   	      e.printStackTrace();
	   	      flexLog("ENOMBLQ01 Error: " + e);
	   	      throw new RuntimeException("Socket Communication Error");
	   	    }

			try {
				newmessage = mc.receiveMessage();
				
				if (newmessage.getFormatName().equals("ENOMBLQ01")) {
					msgList = (ENOMBLQ01Message) newmessage;   			
					msgList.setH01FLGWK3("E");
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);	
					ses.setAttribute("BlqMant", newmessage);

				flexLog("About to call Page: " + LangPath + "ENOMBLQ_New.jsp");
				callPage(LangPath + "ENOMBLQ_New.jsp", req, res);
			}
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
	protected void procReqMant(MessageContext mc,ESS0030DSMessage user,	HttpServletRequest req,HttpServletResponse res,HttpSession ses)	throws ServletException, IOException {

		MessageRecord newmessage = null;
		ENOMBLQ01Message msgList = null;
		
        ELEERRMessage msgError = null;
        //JBList beanList = null;
        UserPos userPO = null;
        boolean IsNotError = false;         
		    newmessage = null;
   
   	    try
   	    {
   	      msgError = new ELEERRMessage();
   	    } catch (Exception ex) {
   	      flexLog("Error: " + ex);
   	    }

   	    userPO = (UserPos)ses.getAttribute("userPO");
   	    
   	    try
   	    {
   	    	msgList = (ENOMBLQ01Message)mc.getMessageRecord("ENOMBLQ01");
   	    	msgList.setH01USERID(user.getH01USR());
   	    	msgList.setH01PROGRM("ENOMBLQ");
   	    	msgList.setH01TIMSYS(getTimeStamp());
   	    	msgList.setH01SCRCOD("");
   	    	msgList.setH01OPECOD(req.getParameter("OPECOD").trim()); 
   	    	msgList.setE01NOMACC(req.getParameter("E01NOMACC").trim());
   	    	msgList.setE01NOMCRD(req.getParameter("E01NOMCRD").trim());
   	    	msgList.setE01NOMCCY(req.getParameter("E01NOMCCY").trim());
   	    	msgList.setE01NOMAMT(req.getParameter("E01NOMAMT").trim());
   	    	msgList.setE01NOMMDM(req.getParameter("E01NOMMDM").trim());
   	    	msgList.setE01NOMMDD(req.getParameter("E01NOMMDD").trim());   	    	
   	    	msgList.setE01NOMMDY(req.getParameter("E01NOMMDY").trim());
   	    	
   	    	
   	    	
   	      flexLog("EFE0501DS Enter Account Header Sent");    	     
   	      msgList.send();   	     
   	      msgList.destroy();
   	    } catch (Exception e) {
   	      e.printStackTrace();
   	      flexLog("ENOMBLQ01 Error: " + e);
   	      throw new RuntimeException("Socket Communication Error");
   	    }

   	 // Receive Error Message
   		try {
   			newmessage = mc.receiveMessage();

   			if (newmessage.getFormatName().equals("ELEERR")) {
   				msgError = (ELEERRMessage) newmessage;
   				IsNotError = msgError.getERRNUM().equals("0");
   				flexLog("IsNotError = " + IsNotError);
   				showERROR(msgError);
   				ses.setAttribute("error", msgError);
   				ses.setAttribute("userPO", userPO);
   				
   				flexLog("About to call Page: " + LangPath + "ENOMBLQ_New.jsp");
   				callPage(LangPath + "ENOMBLQ_New.jsp", req, res);
   			} else
   				flexLog("Message " + newmessage.getFormatName() + " received.");
   			
   			if (newmessage.getFormatName().equals("ENOMBLQ01")) {
				msgList = (ENOMBLQ01Message) newmessage;   			
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				

				 // There are no errors
				
				try {
					flexLog("About to call Page: "+ LangPath+ "ENOMBLQ_Message.jsp");
					callPage(LangPath + "ENOMBLQ_Message.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
   			   }		
   			} catch (Exception e) {
   			e.printStackTrace();
   			flexLog("Error: " + e);
   			throw new RuntimeException("Socket Communication Error");
   		}

		}
	
	protected void procReqList(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException {

			ELEERRMessage msgError = null;
			UserPos userPO = null;
			ENOMBLQ01Message msgSearch = null;
			MessageRecord newmessage = null;
			ENOMBLQ01Message msgList = null;
	        JBList beanList = null;
	        String acc="";
	        acc=req.getParameter("ACCAUX");
          
			
			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
				userPO = new datapro.eibs.beans.UserPos();
				//userPO.setPurpose("MAINTENANCE");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}
			try
	        {
	            flexLog("Send Initial Data");
	            msgSearch = (ENOMBLQ01Message)mc.getMessageRecord("ENOMBLQ01");
	            msgSearch.setH01USERID(user.getH01USR());
	            msgSearch.setH01PROGRM("ENOMBLQ");
	            msgSearch.setH01TIMSYS(getTimeStamp());
	         
	            msgSearch.setH01SCRCOD("01");
	            msgSearch.setH01OPECOD("0004");
	            flexLog("ENOMBLQ Header Sent");
	            try
	            {
	           
	          
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
	            try
                {
	            	msgSearch.setE01NOMCRD(acc);
                }
                catch(Exception ex)
                {
                	msgSearch.setE01NOMCRD("00000000000");
                }
	           
	            msgSearch.send();
	            msgSearch.destroy();
	            flexLog("ENOMBLQ Message Sent");
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            flexLog("Error: " + e);
	            throw new RuntimeException("Socket Communication Error");
	        }
			newmessage = mc.receiveMessage();
			 if(newmessage.getFormatName().equals("ELEERR") && msgError.getERRNUM().equals("0"))
	            {
	                newmessage = mc.receiveMessage();
	            }
	            if(newmessage.getFormatName().equals("ENOMBLQ01"))
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
	                do
	                {
	                    msgList = (ENOMBLQ01Message)newmessage;
	                    marker = msgList.getE01INDOPE();
	                    if(marker.equals("*"))
	                    {
	                        beanList.setShowNext(false);
	                        break;
	                    }
	                    if(firstTime)
	                    {
	                        firstTime = false;
	                        beanList.setFirstRec(Integer.parseInt(msgList.getE01NUMREC()));
	                        chk = "checked";
	                    } else
	                    {
	                        chk = "";
	                    }
	                    myRow = new StringBuffer("<TR>");
	                    myRow.append("<TD NOWRAP><input type=\"radio\" name=\"ACC\" value=\"" + msgList.getE01NOMACC() + "\" " + chk + " onclick=\"showAddInfo(" + indexRow + ")\"></TD>");
	                    myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE01NOMACC()) + "</TD>");
	                    myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE01NOMCRD()) + "</TD>");
	                    myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE01NOMCCY()) + "</TD>");
	                    myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgList.getE01NOMAMT()) + "</TD>");
	                    String fecha=msgList.getE01NOMMDD()+"/"+msgList.getE01NOMMDM()+"/"+msgList.getE01NOMMDY();
	                    myRow.append("<TD NOWRAP>" + Util.formatCell(fecha) + "</TD>");
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
	                ses.setAttribute("blqList", beanList);
	             

				try {
					flexLog("About to call Page: " + LangPath + "ENOMBLQ_Mant.jsp");
					callPage(LangPath + "ENOMBLQ_Mant.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
	        } else
	        {
	          flexLog("Message " + newmessage.getFormatName() + " received.");
	        }

		}
	
	

	

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;
		ELEERRMessage msgError=null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			int screen = R_List;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// Request
					
				
					case R_List :
						procReqList(mc, msgUser, req, res, session);
						break;	
					case R_New:
						procReqNew(mc, msgUser, req, res, session);
						break;
					case R_Mante:
						procReqMant(mc, msgUser, req, res, session);
						break;
					case R_Mod:
						procReqMod(mc, msgUser, req, res, session);
						break;
					case R_Del:
						procReqDel(mc, msgUser, req, res, session);
						break;
			
				}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	
	protected void showERROR(ELEERRMessage m) {
		if (logType != NONE) {

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