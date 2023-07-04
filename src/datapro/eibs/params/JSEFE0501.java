package datapro.eibs.params;


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

public class JSEFE0501 extends datapro.eibs.master.SuperServlet {


		
	protected static final int R_Nueva_ParamRT = 1;
	protected static final int R_Mantenimiento_ParamRT = 2;	
	protected static final int R_reg_param = 11;
	protected static final int R_listMant_param = 21;
	protected static final int R_mant_param = 22;
	protected static final int R_reg_new = 12;
	
	protected String LangPath = "S";
	public JSEFE0501() {
		super();
	}

	public void destroy() {

		flexLog("free resources used by JSEFE0501");

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	

	protected void procReqNewParamRT(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)	
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			//userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EFE0501_Prev_Nuevo_ParamRT.jsp");
			callPage(LangPath + "EFE0501_Prev_Nuevo_ParamRT.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	protected void procReqMantenimientoParamRT(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException {

			ELEERRMessage msgError = null;
			UserPos userPO = null;

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
				userPO = new datapro.eibs.beans.UserPos();
				//userPO.setPurpose("MAINTENANCE");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			try {
				flexLog("About to call Page: " + LangPath + "EFE0501_Prev_Mant_ParamRT.jsp");
				callPage(LangPath + "EFE0501_Prev_Mant_ParamRT.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
	
	protected void procRegistrarParamRT(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException {

		MessageRecord newmessage = null;
		EFE0501DSMessage msgList = null;
        ELEERRMessage msgError = null;
        JBList beanList = null;
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
   	    	msgList = (EFE0501DSMessage)mc.getMessageRecord("EFE0501DS");
   	    	msgList.setH01USERID(user.getH01USR());
   	    	msgList.setH01PROGRM("EFE501");
   	    	msgList.setH01TIMSYS(getTimeStamp());
   	    	msgList.setH01SCRCOD("");
   	    	msgList.setE01FEIBNK("01");
   	    	msgList.setH01OPECOD("0001"); 
   	    	msgList.setE01FEICCY(req.getParameter("E01FEICCY").trim());
   	    	msgList.setE01FEICOV(req.getParameter("tiptrn").trim());
   	    	
   	      flexLog("EFE0501DS Enter Account Header Sent");    	     
   	      msgList.send();
   	      msgList.destroy();
   	    } catch (Exception e) {
   	      e.printStackTrace();
   	      flexLog("EFE0501DS Error: " + e);
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
   				flexLog("About to call Page: " + LangPath + "EFE0501_Prev_Nuevo_ParamRT.jsp");
   				callPage(LangPath + "EFE0501_Prev_Nuevo_ParamRT.jsp", req, res);
   			} else
   				flexLog("Message " + newmessage.getFormatName() + " received.");
   			
   			if (newmessage.getFormatName().equals("EFE0501DS")) {
				msgList = (EFE0501DSMessage) newmessage;   			
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("gaMant", msgList);

				 // There are no errors
				
				try {
					flexLog("About to call Page: "+ LangPath+ "AEFE0501_New_ParamRT.jsp");
					callPage(LangPath + "EFE0501_New_ParamRT.jsp", req, res);
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
	protected void procSendnewRT(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
    	    throws ServletException, IOException
    	  {
		
		  MessageRecord newmessage = null;
		  EFE0501DSMessage msgList = null;
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
              msgList = (EFE0501DSMessage)mc.getMessageRecord("EFE0501DS");
              msgList.setH01USERID(user.getH01USR());
              msgList.setH01PROGRM("EFE0501");
              msgList.setH01TIMSYS(getTimeStamp());
              msgList.setH01SCRCOD("");
              msgList.setH01OPECOD(req.getParameter("OPECOD").trim());
              msgList.setE01FEIBNK("01");
              if(!req.getParameter("OPECOD").trim().equals("0005")){
	             
	              java.util.Enumeration enu = msgList.fieldEnumeration();
		  		  MessageField field = null;
		  		  String value = null;
		  		  while (enu.hasMoreElements()) {
		  				field = (MessageField) enu.nextElement();
		  				try {
		  					value = req.getParameter(field.getTag()).toUpperCase();
		  					if (value != null) {
		  						field.setString(value);
		  					}
		  				} catch (Exception e) {
		  				}
		  			}
              }else{
            	  String ref=req.getParameter("REF").trim();
          	      String[] Datos=ref.split("_");  
            	  msgList.setE01FEICCY(Datos[0]); 
            	  msgList.setE01FEICOV(Datos[1]); 
            	  msgList.setE01FEIBR1(Datos[2]);  
            	  msgList.setE01FEIBR2(Datos[2]);
            	  
            	  
              }
              msgList.send();
          //    msgList.destroy();         
              flexLog("EFE0501DS Message Sent");
          }
          catch(Exception e)
          {
              e.printStackTrace();
              flexLog("Error: " + e);
              throw new RuntimeException("Socket Communication Error");
          }

    			// Receive Data
    	      try {
    				newmessage = mc.receiveMessage();

    				if (newmessage.getFormatName().equals("ELEERR")) {
    					msgError = (ELEERRMessage) newmessage;
    					IsNotError = msgError.getERRNUM().equals("0");
    					flexLog("IsNotError = " + IsNotError);
    					showERROR(msgError);
    					flexLog("Putting java beans into the session");    					
    					ses.setAttribute("error", msgError);
        				ses.setAttribute("userPO", userPO);
        				ses.setAttribute("gaMant", msgList);
        				
        					try {
        						flexLog("About to call Page: "+ LangPath+ "EFE0501_New_ParamRT.jsp");
        						callPage(LangPath + "EFE0501_New_ParamRT.jsp", req, res);
        					} catch (Exception e) {
        						flexLog("Exception calling page " + e);
        					}
        				
    				} else{
    					if(req.getParameter("OPECOD").trim().equals("0002")){
    						procReqNewParamRT(mc, user, req, res, ses);
    					}
    					if(req.getParameter("OPECOD").trim().equals("0004")||req.getParameter("OPECOD").trim().equals("0005")){
    						procReqListMantenimiento(mc, user, req, res, ses);
    					}
    				}
    					

    			} catch (Exception e) {
    				e.printStackTrace();
    				flexLog("Error: " + e);
    				throw new RuntimeException("Socket Communication Error");
    			}

 
      }
	protected void procReqListMantenimiento(MessageContext mc,ESS0030DSMessage user,HttpServletRequest req,HttpServletResponse res,HttpSession ses)	throws ServletException, IOException {
		  MessageRecord newmessage = null;
		  EFE0501DSMessage msgList = null;
          ELEERRMessage msgError = null;
          JBList beanList = null;
          UserPos userPO = null;
          boolean IsNotError = false;
         // String opecod=req.getParameter("OPECOD").trim();
          
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
              msgList = (EFE0501DSMessage)mc.getMessageRecord("EFE0501DS");
              msgList.setH01USERID(user.getH01USR());
              msgList.setH01PROGRM("EFE0501");
              msgList.setH01TIMSYS(getTimeStamp());
              msgList.setH01SCRCOD("");
              msgList.setH01OPECOD("0003");   
              if("".equals(userPO.getHeader23())){
            	  msgList.setE01FEICCY(req.getParameter("E01FEICCY").trim()); 
	              msgList.setE01FEICOV(req.getParameter("TIPTRN").trim()); 
	              msgList.setE01FEIBR1(req.getParameter("E01FEIBR1").trim()); 
	              msgList.setE01FEIBR2(req.getParameter("E01FEIBR2").trim()); 
	              
	              String filter="";
	              if("".equals(req.getParameter("E01FEICCY").trim())){
	            	  filter=filter+"*;";
	              }else{
	            	  filter=filter+req.getParameter("E01FEICCY").trim()+";";
	              }
	              if("".equals(req.getParameter("TIPTRN").trim())){
	            	  filter=filter+"*;";
	              }else{
	            	  filter=filter+req.getParameter("TIPTRN").trim()+";";
	              }
	              if("".equals(req.getParameter("E01FEIBR1").trim())){
	            	  filter=filter+"*;";
	              }else{
	            	  filter=filter+req.getParameter("E01FEIBR1").trim()+";";
	              }
	              if("".equals(req.getParameter("E01FEIBR2").trim())){
	            	  filter=filter+"*;";
	              }else{
	            	  filter=filter+req.getParameter("E01FEIBR2").trim()+";";
	              }
	           	  userPO.setHeader23(filter);
            	  
              }else{
            	  String filter=userPO.getHeader23();
            	  String[] Datos=filter.split(";"); 
            	  if("*".equals(Datos[0])){
            		  msgList.setE01FEICCY("");
	              }else{
	            	  msgList.setE01FEICCY(Datos[0]);
	              }
            	  if("*".equals(Datos[1])){
            		  msgList.setE01FEICOV("");
	              }else{
	            	  msgList.setE01FEICOV(Datos[1]);
	              }
            	  if("*".equals(Datos[2])){
            		  msgList.setE01FEIBR1("");
	              }else{
	            	  msgList.setE01FEIBR1(Datos[2]);
	              }
            	  if("*".equals(Datos[3])){
            		  msgList.setE01FEIBR2("");
	              }else{
	            	  msgList.setE01FEIBR2(Datos[3]);
	              }	                   	              	              	            
              }
              
              msgList.send();
              msgList.destroy();
              flexLog("EFE0501DS Message Sent");
          }
          catch(Exception e)
          {
              e.printStackTrace();
              flexLog("Error: " + e);
              throw new RuntimeException("Socket Communication Error");
          }
          try
          {
            //  newmessage = mc.receiveMessage();
             // if(newmessage.getFormatName().equals("ELEERR"))// mensaje de error
             // {
                 
              //}
              newmessage = mc.receiveMessage();
              if(newmessage.getFormatName().equals("EFE0501DS"))//mensaje de listado de transacciones rechazadas
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
                      msgList = (EFE0501DSMessage)newmessage;
                      marker = msgList.getE01FEIREV();
                      
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
                     /* if(msgList.getACH1MOD().equals("1")){
                    	  myFlag="1";
                      }
                      if(msgList.getACH1REC().equals("1")){
                    	  myFlag="2";
                      }*/
                    
                      String Ref=msgList.getE01FEICCY()+"_"+msgList.getE01FEICOV()+"_"+msgList.getE01FEIBR1();
                      myRow = new StringBuffer();
                      myRow.append("<TR>");
                      myRow.append("<TD NOWRAP WIDTH=2% ALIGN=CENTER><input type=\"radio\" name=\"REF\" value=\"" + Ref+ "\" " + chk + "></TD>");
                      myRow.append("<TD NOWRAP WIDTH=5% ALIGN=LEFT>" + Util.formatCell(msgList.getE01FEICCY()) + "</TD>");
                      myRow.append("<TD NOWRAP WIDTH=15% ALIGN=LEFT>" + Util.formatCell(msgList.getE01FEICCD()) + "</TD>");
                      myRow.append("<TD NOWRAP WIDTH=10% ALIGN=CENTER>" + Util.formatCell(msgList.getE01FEICOD()) + "</TD>");                     
                      myRow.append("<TD NOWRAP WIDTH=5% ALIGN=LEFT>" + Util.formatCell(msgList.getE01FEIBR1()) + "</TD>");  
                      myRow.append("<TD NOWRAP WIDTH=33% ALIGN=CENTER>" + Util.formatCell(msgList.getE01FEIBRD()) + "</TD>");  
                      myRow.append("<TD NOWRAP WIDTH=10% ALIGN=RIGHT>" + Util.formatCell(msgList.getE01FEIRAB()) + "</TD>"); 
                      myRow.append("<TD NOWRAP WIDTH=10% ALIGN=RIGHT>" + Util.formatCell(msgList.getE01FEIALT()) + "</TD>");  
                      myRow.append("<TD NOWRAP WIDTH=10% ALIGN=RIGHT>" + Util.formatCell(msgList.getE01FEIBAJ()) + "</TD>");  
                     
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
                	  
                	 
	                      flexLog("About to call Page: " + LangPath + "EFE0501_List_Mant.jsp");
	                      callPage(LangPath + "EFE0501_List_Mant.jsp", req, res);
                	
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
	protected void procSendMant(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
    	    throws ServletException, IOException
    	  {
		 MessageRecord newmessage = null;
		 EFE0501DSMessage msgList = null;
         ELEERRMessage msgError = null;
         JBList beanList = null;
         UserPos userPO = null;
         boolean IsNotError = false;
          String tiptrn,selftr,selsec,selref;          
  	      String ref=req.getParameter("REF").trim();
  	      String[] Datos=ref.split("_");  	    
		    newmessage = null;
		    EFE0501DSMessage msgAcc = null;
    	     msgError = null;
    	     userPO = null;
    	    try
    	    {
    	      msgError = new ELEERRMessage();
    	    } catch (Exception ex) {
    	      flexLog("Error: " + ex);
    	    }

    	    userPO = (UserPos)ses.getAttribute("userPO");
    	    
    	    try
    	    {
    	      msgAcc = (EFE0501DSMessage)mc.getMessageRecord("EFE0501DS");
    	      msgAcc.setH01USERID(user.getH01USR());
    	      msgAcc.setH01PROGRM("EFE0501");
    	      msgAcc.setH01TIMSYS(getTimeStamp());
    	      msgAcc.setH01SCRCOD("");
    	      msgAcc.setH01OPECOD("0003");   
    	      msgAcc.setE01FEICCY(Datos[0]); 
    	      msgAcc.setE01FEICOV(Datos[1]); 
    	      msgAcc.setE01FEIBR1(Datos[2]);  
    	      msgAcc.setE01FEIBR2(Datos[2]);  
    	      msgAcc.send();
    	      msgAcc.destroy();
    	    } catch (Exception e) {
    	      e.printStackTrace();
    	      flexLog("EFE0501DS Error: " + e);
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
    			} else
    				flexLog("Message " + newmessage.getFormatName() + " received.");

    		} catch (Exception e) {
    			e.printStackTrace();
    			flexLog("Error: " + e);
    			throw new RuntimeException("Socket Communication Error");
    		}

    		// Receive Data
    		try {
    			
    			if (newmessage.getFormatName().equals("EFE0501DS")) {
    				msgAcc = (EFE0501DSMessage) newmessage;    				

    				flexLog("Putting java beans into the session");
    				//ses.setAttribute("error", msgErr);
    				ses.setAttribute("userPO", userPO);
    				ses.setAttribute("gaMant", msgAcc);

    				 // There are no errors
    					try {
    						flexLog("About to call Page: "+ LangPath+ "EFE0501_Mant_ParamRT.jsp");
    						callPage(LangPath + "EFE0501_Mant_ParamRT.jsp", req, res);
    					} catch (Exception e) {
    						flexLog("Exception calling page " + e);
    					}
    					newmessage = mc.receiveMessage();
    				 
    			} else
    				flexLog("Message " + newmessage.getFormatName() + " received.");
    		} catch (Exception e) {
    			e.printStackTrace();
    			flexLog("Error: " + e);
    			throw new RuntimeException("Socket Communication Error");
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

			int screen = R_Nueva_ParamRT;

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
					
					case R_Nueva_ParamRT :
						procReqNewParamRT(mc, msgUser, req, res, session);
						break;	
					case R_Mantenimiento_ParamRT :
						procReqMantenimientoParamRT(mc, msgUser, req, res, session);
						break;	
					case R_reg_param :	
						procRegistrarParamRT(mc, msgUser, req, res, session);
						break;
					case R_reg_new :	
						procSendnewRT(mc, msgUser, req, res, session);
						break;
					case R_listMant_param :	
						procReqListMantenimiento(mc, msgUser, req, res, session);
						break;
					case R_mant_param :	
						procSendMant(mc, msgUser, req, res, session);
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