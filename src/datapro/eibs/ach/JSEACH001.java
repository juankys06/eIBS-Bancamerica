package datapro.eibs.ach;


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

public class JSEACH001 extends datapro.eibs.master.SuperServlet {


		
	protected static final int R_ENTRANTES = 200;
	protected static final int R_SALIENTES = 300;
	protected static final int R_ENTER_Rechazos = 100;
	protected static final int R_LIST_Rechazos = 110;	
	protected static final int R_ENTER_REF = 10;
	protected static final int R_MODIF_RECH = 2;
	protected static final int R_APROB_RECH = 5;
	protected static final int R_Sal = 310;
	protected static final int R_Sal_SEND = 320;
	protected static final int R_Sal_SENDw = 400;


	

	protected String LangPath = "S";
	public JSEACH001() {
		super();
	}

	public void destroy() {

		flexLog("free resources used by JSEACH001");

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	

	protected void procReqEntrantes(
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
			flexLog("About to call Page: " + LangPath + "ACH0001_previewAprobacion_ACH.jsp");
			callPage(LangPath + "ACH0001_previewAprobacion_ACH.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	protected void procReqEnterRechazo(
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
				flexLog("About to call Page: " + LangPath + "ACH0001_previewRechazo_ACH.jsp");
				callPage(LangPath + "ACH0001_previewRechazo_ACH.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
	
	protected void procReqListRechazo(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException {
		  MessageRecord newmessage = null;
		  EACH00101Message msgList = null;
          ELEERRMessage msgError = null;
          JBList beanList = null;
          UserPos userPO = null;
          boolean IsNotError = false;
          String opecod=req.getParameter("OPECOD").trim();
          
          try
          {
              msgError = new ELEERRMessage();
          }
          catch(Exception ex)
          {
              flexLog("Error: " + ex);
          }
          userPO = (UserPos)ses.getAttribute("userPO");
          if("".equals(userPO.getHeader23())){
        	  userPO.setHeader23(req.getParameter("TIPTRN").trim());
          }
          try
          {
              flexLog("Send Initial Data");
              msgList = (EACH00101Message)mc.getMessageRecord("EACH00101");
              msgList.setACH1USERID(user.getH01USR());
              msgList.setACH1PROGRM("ACH0001");
              msgList.setACH1TIMSYS(getTimeStamp());
              msgList.setACH1SCRCOD("");
              msgList.setACH1OPECOD(opecod);
              msgList.setACH1TIPTRN(userPO.getHeader23());
              msgList.send();
              msgList.destroy();
              flexLog("ACH000101D Message Sent");
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
              if(newmessage.getFormatName().equals("EACH00101"))//mensaje de listado de transacciones rechazadas
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
                      msgList = (EACH00101Message)newmessage;
                      marker = msgList.getACH1INDOPE();
                      
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
                      if(msgList.getACH1MOD().equals("1")){
                    	  myFlag="1";
                      }
                      if(msgList.getACH1REC().equals("1")){
                    	  myFlag="2";
                      }
                    
                      String Ref=msgList.getACH1REF()+"_"+msgList.getACH1RDP()+"_"+msgList.getACH1FTR()+"_"+msgList.getACH1SEC();
                      myRow = new StringBuffer();
                      myRow.append("<TR>");
                      myRow.append("<TD NOWRAP><input type=\"radio\" name=\"REF\" value=\"" + Ref+ "\" " + chk + "></TD>");
                      myRow.append("<TD NOWRAP>" + Util.formatCell(msgList.getACH1REF()) + "</TD>");
                      myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getACH1FTR()) + "</TD>");                      
                      myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getACH1RDP()) + "</TD>");      
                      myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getACH1NUC()) + "</TD>");
                      myRow.append("<TD NOWRAP ALIGN=LEFT>" + Util.formatCell(msgList.getACH1CUN()) + "</TD>");  
                      myRow.append("<TD NOWRAP ALIGN=CENTER>" + Util.formatCell(msgList.getACH1SHN()) + "</TD>");  
                      myRow.append("<TD NOWRAP ALIGN=RIGHT>" + Util.formatCell(msgList.getACH1MNT()) + "</TD>");
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
                	  
                	  if(opecod.equals("0001")){
	                      flexLog("About to call Page: " + LangPath + "ACH0001_listado_Rechazados.jsp");
	                      callPage(LangPath + "ACH0001_listado_Rechazados.jsp", req, res);
                	  }else{
                		  flexLog("About to call Page: " + LangPath + "ACH0001_listado_Aprov.jsp");
	                      callPage(LangPath + "ACH0001_listado_Aprov.jsp", req, res);                		  
                	  }
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
	protected void procReqSalDat(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
    	    throws ServletException, IOException
    	  {
		 MessageRecord newmessage = null;
		 EACH00301Message msgList = null;
         ELEERRMessage msgError = null;
         JBList beanList = null;
         UserPos userPO = null;
         boolean IsNotError = false;         
		    newmessage = null;
    	   String rec=req.getParameter("TIPTRN").trim(),accfrm=req.getParameter("ACCFRM").trim();
    	   String tptrn[]=rec.split("-");
    	    try
    	    {
    	      msgError = new ELEERRMessage();
    	    } catch (Exception ex) {
    	      flexLog("Error: " + ex);
    	    }

    	    userPO = (UserPos)ses.getAttribute("userPO");
    	    
    	    try
    	    {
    	    	msgList = (EACH00301Message)mc.getMessageRecord("EACH00301");
    	    	msgList.setACH3USERID(user.getH01USR());
    	    	msgList.setACH3PROGRM("ACH0001");
    	    	msgList.setACH3TIMSYS(getTimeStamp());
    	    	msgList.setACH3SCRCOD("");
    	    	msgList.setACH3OPECOD("0001"); 
    	    	msgList.setACH3ACCFRM(accfrm);
    	    	msgList.setACH3ATYTO(tptrn[0]);
    	    	msgList.setACH3TRNTYP(tptrn[1]);
    	    	msgList.setACH3RED(tptrn[2]);
    	      flexLog("EACH00301 Enter Account Header Sent");    	     
    	      msgList.send();
    	      msgList.destroy();
    	    } catch (Exception e) {
    	      e.printStackTrace();
    	      flexLog("EACH00301 Error: " + e);
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
    				flexLog("About to call Page: " + LangPath + "ACH0001_salientes_ACH.jsp");
    				callPage(LangPath + "ACH0001_salientes_ACH.jsp", req, res);
    			} else
    				flexLog("Message " + newmessage.getFormatName() + " received.");

    		} catch (Exception e) {
    			e.printStackTrace();
    			flexLog("Error: " + e);
    			throw new RuntimeException("Socket Communication Error");
    		}

    		// Receive Data
    		try {
    			
    			if (newmessage.getFormatName().equals("EACH00301")) {
    				msgList = (EACH00301Message) newmessage;    				
    				Date fecha=new Date(); 
    	            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); 
    	            SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmss");
    	            SimpleDateFormat sdf2=new SimpleDateFormat("HHmmss");
    	            msgList.setACH3TRNSEC(sdf2.format(fecha));    	            
    	            msgList.setACH3TRNDAT(sdf.format(fecha));
    	            msgList.setACH3FECTRN(sdf.format(fecha));
    	            msgList.setACH3TRNREF(sdf1.format(fecha));
    	            msgList.setACH3ATYTO(tptrn[0]);
    	            msgList.setACH3TRNTYP(tptrn[1]);
    	            msgList.setACH3RED(tptrn[2]);
    	            msgList.setACH3ACCFRM(accfrm);
    				flexLog("Putting java beans into the session");
    				ses.setAttribute("error", msgError);
    				ses.setAttribute("userPO", userPO);
    				ses.setAttribute("gaMant", msgList);

    				 // There are no errors
    				if(req.getParameter("destino").trim().equals("1")){
    					try {
    						flexLog("About to call Page: "+ LangPath+ "ACH0001_TrNaciona.jsp");
    						callPage(LangPath + "ACH0001_TrNaciona.jsp", req, res);
    					} catch (Exception e) {
    						flexLog("Exception calling page " + e);
    					}
    				}else{
    					try {
    						flexLog("About to call Page: "+ LangPath+ "ACH0001_TrInternacional.jsp");
    						callPage(LangPath + "ACH0001_TrInternacional.jsp", req, res);
    					} catch (Exception e) {
    						flexLog("Exception calling page " + e);
    					}
    					
    				}
    				 
    			} else
    				flexLog("Message " + newmessage.getFormatName() + " received.");
    		} catch (Exception e) {
    			e.printStackTrace();
    			flexLog("Error: " + e);
    			throw new RuntimeException("Socket Communication Error");
    		}
    	  }
	protected void procActionModifRech(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
    	    throws ServletException, IOException
    	  {
		
		  MessageRecord newmessage = null;
		  EACH00101Message msgList = null;
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
              msgList = (EACH00101Message)mc.getMessageRecord("EACH00101");
              msgList.setACH1USERID(user.getH01USR());
              msgList.setACH1PROGRM("ACH0001");//falta valores
              msgList.setACH1TIMSYS(getTimeStamp());
              msgList.setACH1SCRCOD("");//falta valores
              msgList.setACH1OPECOD("0003");//falta valores
              msgList.setACH1TIPTRN(req.getParameter("TIPTRN").trim()); //Codigo de TRansaccion
              msgList.send();
              msgList.destroy();
         //     newmessage = mc.receiveMessage();
              flexLog("ACH000101D Message Sent");
          }
          catch(Exception e)
          {
              e.printStackTrace();
              flexLog("Error: " + e);
              throw new RuntimeException("Socket Communication Error");
          }
          
      
          String tiptrn,selftr,selsec,selref;          
  	     
  	      selref=req.getParameter("ACH2REF").trim();
  	      selftr=req.getParameter("ACH2FTR").trim();
  	      selsec=req.getParameter("SELSEC").trim();
  	      tiptrn=req.getParameter("TIPTRN").trim();
		    newmessage = null;
    	    EACH00102Message msgAcc = null;
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
    	      msgAcc = (EACH00102Message)mc.getMessageRecord("EACH00102");
    	      msgAcc.setACH2USERID(user.getH01USR());
    	      msgAcc.setACH2PROGRM("ACH0001");
    	      msgAcc.setACH2TIMSYS(getTimeStamp());
    	      msgAcc.setACH2SCRCOD("");
    	      msgAcc.setACH2OPECOD("0003");
    	      msgAcc.setACH2TIPTRN(tiptrn);
    	      msgAcc.setACH2SELFTR(selftr);
    	      msgAcc.setACH2SELSEC(selsec);
    	      msgAcc.setACH2SELREF(selref);
    	      msgAcc.setACH2NUC(req.getParameter("ACH2NUC").trim());
    	      msgAcc.setACH2IDC(req.getParameter("ACH2IDC").trim());
    	      msgAcc.setACH2STATUS(req.getParameter("ACH2STATUS").trim());
    	      flexLog("EACH00102 Enter Account Header Sent");    	     
    	      msgAcc.send();
    	     
    	    } catch (Exception e) {
      	      e.printStackTrace();
      	      flexLog("EACH00102 Error: " + e);
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
    					msgAcc.setACH2OPECOD("0002");
        				ses.setAttribute("error", msgError);
        				procActionEnterRef(mc, user, req, res, ses,msgError);
    				} else{
    					
    					procReqListRechazo(mc, user, req, res, ses);
    				}
    					

    			} catch (Exception e) {
    				e.printStackTrace();
    				flexLog("Error: " + e);
    				throw new RuntimeException("Socket Communication Error");
    			}

 
      }
	protected void procActionAprobRech(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
    	    throws ServletException, IOException
    	  {
		
		  MessageRecord newmessage = null;
		  EACH00101Message msgList = null;
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
              msgList = (EACH00101Message)mc.getMessageRecord("EACH00101");
              msgList.setACH1USERID(user.getH01USR());
              msgList.setACH1PROGRM("ACH0001");//falta valores
              msgList.setACH1TIMSYS(getTimeStamp());
              msgList.setACH1SCRCOD("");//falta valores
              msgList.setACH1OPECOD("0005");//falta valores
              msgList.setACH1TIPTRN(req.getParameter("TIPTRN").trim()); //Codigo de TRansaccion
              msgList.send();
              msgList.destroy();
         //     newmessage = mc.receiveMessage();
              flexLog("ACH000101D Message Sent");
          }
          catch(Exception e)
          {
              e.printStackTrace();
              flexLog("Error: " + e);
              throw new RuntimeException("Socket Communication Error");
          }
          
      
          String tiptrn,selftr,selsec,selref;          
  	     
  	      selref=req.getParameter("ACH2REF").trim();
  	      selftr=req.getParameter("ACH2FTR").trim();
  	      selsec=req.getParameter("SELSEC").trim();
  	      tiptrn=req.getParameter("TIPTRN").trim();
		    newmessage = null;
    	    EACH00102Message msgAcc = null;
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
    	      msgAcc = (EACH00102Message)mc.getMessageRecord("EACH00102");
    	      msgAcc.setACH2USERID(user.getH01USR());
    	      msgAcc.setACH2PROGRM("ACH0001");
    	      msgAcc.setACH2TIMSYS(getTimeStamp());
    	      msgAcc.setACH2SCRCOD("");
    	      msgAcc.setACH2OPECOD("0005");
    	      msgAcc.setACH2TIPTRN(tiptrn);
    	      msgAcc.setACH2SELFTR(selftr);
    	      msgAcc.setACH2SELSEC(selsec);
    	      msgAcc.setACH2SELREF(selref);
    	      msgAcc.setACH2NUC(req.getParameter("ACH2NUC").trim());
    	      msgAcc.setACH2IDC(req.getParameter("ACH2IDC").trim());
    	      msgAcc.setACH2STATUS(req.getParameter("ACH2STATUS").trim());
    	      flexLog("EACH00102 Enter Account Header Sent");    	     
    	      msgAcc.send();
    	      msgAcc.destroy();
    	      newmessage = mc.receiveMessage();
    	    } catch (Exception e) {
    	      e.printStackTrace();
    	      flexLog("EACH00102 Error: " + e);
    	      throw new RuntimeException("Socket Communication Error");
    	    }
    	      	
    	    procReqListRechazo(mc, user, req, res, ses);
        
		
      }
	
	
	protected void procReqSalientes(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException {

			ELEERRMessage msgError = null;
			UserPos userPO = null;
			MessageRecord newmessage = null;
			EWD0720DSMessage msgHelp = null;
			JBList beanList = null;

			try {
				msgHelp = (EWD0720DSMessage) mc.getMessageRecord("EWD0720DS");
				msgHelp.setEWDOP1("CA");
				msgHelp.send();
				msgHelp.destroy();
				flexLog("EWD0720DS Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
			newmessage = mc.receiveMessage();
					try {
						beanList = new JBList();
						flexLog("EWD0720DS Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}

					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;

					while (true) {

						msgHelp = (EWD0720DSMessage) newmessage;

						marker = msgHelp.getEWDOPE();

						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							myRow = new StringBuffer();
							myRow.append("<option value="+msgHelp.getEWDRED()+"-"+msgHelp.getEWDTYPTRN()+"-"+msgHelp.getEWDCODTRN()+">"+msgHelp.getEWDDSC()+"</option>	");							
							beanList.addRow(myFlag, myRow.toString());
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

				
					
			try {				msgError = new datapro.eibs.beans.ELEERRMessage();
				userPO = new datapro.eibs.beans.UserPos();
				//userPO.setPurpose("MAINTENANCE");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);			
				ses.setAttribute("ewd0012Help", beanList);
			
				flexLog("About to call Page: " + LangPath + "ACH0001_salientes_ACH.jsp");
				callPage(LangPath + "ACH0001_salientes_ACH.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}


	protected void procActionEnterRef(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses,ELEERRMessage msgErr)
    	    throws ServletException, IOException
    	  {
		 MessageRecord newmessage = null;
		  EACH00101Message msgList = null;
         ELEERRMessage msgError = null;
         JBList beanList = null;
         UserPos userPO = null;
         boolean IsNotError = false;
          String tiptrn,selftr,selsec,selref;          
  	      String ref=req.getParameter("REF").trim();
  	      String[] Datos=ref.split("_");
  	      selref=Datos[0];
  	      selftr=Datos[2];
  	      selsec=Datos[3];
  	      tiptrn=Datos[1];
		    newmessage = null;
    	    EACH00102Message msgAcc = null;
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
    	      msgAcc = (EACH00102Message)mc.getMessageRecord("EACH00102");
    	      msgAcc.setACH2USERID(user.getH01USR());
    	      msgAcc.setACH2PROGRM("ACH0001");
    	      msgAcc.setACH2TIMSYS(getTimeStamp());
    	      msgAcc.setACH2SCRCOD("");
    	      msgAcc.setACH2OPECOD("0002");
    	      msgAcc.setACH2TIPTRN(tiptrn);
    	      msgAcc.setACH2SELFTR(selftr);
    	      msgAcc.setACH2SELSEC(selsec);
    	      msgAcc.setACH2SELREF(selref);
    	      
    	      flexLog("EACH00102 Enter Account Header Sent");    	     
    	      msgAcc.send();
    	      msgAcc.destroy();
    	    } catch (Exception e) {
    	      e.printStackTrace();
    	      flexLog("EACH00102 Error: " + e);
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
    			
    			if (newmessage.getFormatName().equals("EACH00102")) {
    				msgAcc = (EACH00102Message) newmessage;    				

    				flexLog("Putting java beans into the session");
    				ses.setAttribute("error", msgErr);
    				ses.setAttribute("userPO", userPO);
    				ses.setAttribute("gaMant", msgAcc);

    				 // There are no errors
    					try {
    						flexLog("About to call Page: "+ LangPath+ "ACH0001_Modificar.jsp");
    						callPage(LangPath + "ACH0001_Modificar.jsp", req, res);
    					} catch (Exception e) {
    						flexLog("Exception calling page " + e);
    					}
    				 
    			} else
    				flexLog("Message " + newmessage.getFormatName() + " received.");
    		} catch (Exception e) {
    			e.printStackTrace();
    			flexLog("Error: " + e);
    			throw new RuntimeException("Socket Communication Error");
    		}
    	  }
	protected void procSendSalientes(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
    	    throws ServletException, IOException
    	  {
		
		  MessageRecord newmessage = null;
		  EACH00301Message msgList = null;
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
              msgList = (EACH00301Message)mc.getMessageRecord("EACH00301");
              msgList.setACH3USERID(user.getH01USR());
              msgList.setACH3PROGRM("ACH0001");
              msgList.setACH3TIMSYS(getTimeStamp());
              msgList.setACH3SCRCOD("");
              msgList.setACH3OPECOD(req.getParameter("OPECOD").trim());
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
              msgList.send();
          //    msgList.destroy();         
              flexLog("ACH000301D Message Sent");
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
        				if(req.getParameter("destino").trim().equals("1")){
        					try {
        						flexLog("About to call Page: "+ LangPath+ "ACH0001_TrNaciona.jsp");
        						callPage(LangPath + "ACH0001_TrNaciona.jsp", req, res);
        					} catch (Exception e) {
        						flexLog("Exception calling page " + e);
        					}
        				}else{
        					try {
        						flexLog("About to call Page: "+ LangPath+ "ACH0001_TrInternacional.jsp");
        						callPage(LangPath + "ACH0001_TrInternacional.jsp", req, res);
        					} catch (Exception e) {
        						flexLog("Exception calling page " + e);
        					}
        				}
    				} else{
    					
    					procReqSalientes(mc, user, req, res, ses);
    				}
    					

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

			int screen = R_ENTRANTES;

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
					
					case R_ENTRANTES :
						procReqEntrantes(mc, msgUser, req, res, session);
						break;	
					case R_SALIENTES :
						procReqSalientes(mc, msgUser, req, res, session);
						break;		
					case R_ENTER_Rechazos :
						procReqEnterRechazo(mc, msgUser, req, res, session);
						break;	
					case R_LIST_Rechazos :
						procReqListRechazo(mc, msgUser, req, res, session);
						break;							
					case R_ENTER_REF :
						procActionEnterRef(mc, msgUser, req, res, session,msgError);
						break;	
					case R_MODIF_RECH :
						procActionModifRech(mc, msgUser, req, res, session);
						break;
					case R_APROB_RECH :
						procActionAprobRech(mc, msgUser, req, res, session);
						break;					
					case R_Sal :
						procReqSalDat(mc, msgUser, req, res, session);
						break;	
					case R_Sal_SEND :
						procSendSalientes(mc, msgUser, req, res, session);
						break;						
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
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