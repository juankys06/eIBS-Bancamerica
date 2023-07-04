package datapro.eibs.approval;


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
import javax.swing.text.FlowView;

import datapro.eibs.approval.ConexionDB;
import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.products.JOActionRedirect;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat; 
import java.util.Date;
import java.sql.*;

public class JSEDD2000 extends datapro.eibs.master.SuperServlet {


		
	protected static final int fec_camara_rec = 1;
	protected static final int E_camara_rec = 11;
	protected static final int E_Read_ibs = 2;
	protected static final int S_send_camara_entrante = 10;	
	protected static final int S_send_camara_rechazada = 20;	
	
	
	protected String LangPath = "S";
	public JSEDD2000() {
		super();
	}

	public void destroy() {

		flexLog("free resources used by JSEDD2000");

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqFec(
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
				flexLog("About to call Page: " + LangPath + "EDD200_prev_fec.jsp");
				callPage(LangPath + "EDD200_prev_fec.jsp", req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}

	protected void odbc(MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException  {
		
		
		
		UserPos userPO = null;
		userPO = (UserPos)ses.getAttribute("userPO");
		Connection conexion=null;
		JBList beanList = null; 
		String fecha="20"+req.getParameter("ano").trim()+req.getParameter("mes").trim()+req.getParameter("dia").trim();
		MessageRecord newmessage = null;
		EFTP00101Message msgList = null;
        ELEERRMessage msgError = null;
        String url1="";
		String bd="";
		String usr="",port="";
		String pass="";
		 DecimalFormat num = new DecimalFormat("####.00"); 
	////Conexion parametrizable desde el as400
		try
	    {
	    	msgList = (EFTP00101Message)mc.getMessageRecord("EFTP00101");
	    	msgList.setEFTPUSERID(user.getH01USR());
	    	msgList.setEFTPPROGRM("EFTP001");
	    	msgList.setEFTPTIMSYS(getTimeStamp());
	    	msgList.setEFTPSCRCOD("");
	    	msgList.setEFTPOPECOD("0001"); 
	    	msgList.setE01FTCDE("CC");
	    	
	      flexLog("EFTP00101 Enter Account Header Sent");    	     
	      msgList.send();
	      msgList.destroy();
	    } catch (Exception e) {
	      e.printStackTrace();
	      flexLog("EFTP00101 Error: " + e);
	      throw new RuntimeException("Socket Communication Error");
	    }

	 // Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				boolean IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				flexLog("About to call Page: " + LangPath + "EDD200_prev_fec.jsp");
				callPage(LangPath + "EDD200_prev_fec.jsp", req, res);
			} else{
				msgList = (EFTP00101Message) newmessage;    	
				url1=msgList.getE01FTADD();
				usr=msgList.getE01FTUSR();
				pass=msgList.getE01FTPWR();
				String[] result = msgList.getE01FTTPT().split(":");
				bd=result[0];
				port=result[1];
				
			
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}	
				
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		
		
		
		////////
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://"+url1+":"+port+";databaseName="+bd+";";
   
            conexion= DriverManager.getConnection(url,usr,pass);
            Statement statement = conexion.createStatement();
            StringBuffer myRow = null;
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
            int cont=0;
            float sum=0;
            double suma =0;
            String qry="Select * From TBL_RESUMEN_PROCESO_CAMARAS Where RPC_CODIGO_CAMARA = '001' And RPC_TIPO_CAMARA = '1' And RPC_ESTATUS_PROCESO = 'ES0' And Rpc_Tipo_Documento = 1  And Rpc_estatus_pago = 1 and RPC_FECHA_PROCESO='"+fecha+"'";
            ResultSet resultSet = statement.executeQuery(qry);
            while (resultSet.next()) {  
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
            	String Ref=resultSet.getString("RPC_ID");
            	//System.out.print(resultSet.getString("RPC_ID"));
            	 myRow = new StringBuffer();
            	myRow.append("<TR>");
             //   myRow.append("<TD NOWRAP><input type=\"radio\" name=\"REF\" value=\"" + Ref+ "\" " + chk + "></TD>");
                myRow.append("<TD NOWRAP ALIGN=left WIDTH=0,5%>" + Util.formatCell(resultSet.getString("RPC_ID")) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=center WIDTH=2,4 >" + Util.formatCell(resultSet.getString("RPC_CODIGO_CAMARA")) + "</TD>");  
                if(resultSet.getString("RPC_BATCH_ID")==null){
                	myRow.append("<TD NOWRAPALIGN=center WIDTH=3%>" + Util.formatCell("") + "</TD>");
                }else{
                	myRow.append("<TD NOWRAP ALIGN=center WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_BATCH_ID")) + "</TD>");
                }  
                myRow.append("<TD NOWRAP ALIGN=center WIDTH=2% >" + Util.formatCell(resultSet.getString("RPC_NUMERO_LOTE")) + "</TD>");
                 myRow.append("<TD NOWRAP ALIGN=center WIDTH=3% >" + Util.formatCell(resultSet.getString("RPC_NUMERO_CHEQUE")) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=center WIDTH=4%>" + Util.formatCell(resultSet.getString("RPC_NUMERO_CUENTA_ORIGEN")) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=center WIDTH=3,75%>" + Util.formatCell(resultSet.getString("RPC_NUMERO_CUENTA_DESTINO")) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=center WIDTH=2,75%>" + Util.formatCell(resultSet.getString("RPC_RUTA_ORIGEN")) + "</TD>");  
                if(resultSet.getString("RPC_RUTA_DESTINO")==null){
                	myRow.append("<TD NOWRAP ALIGN=center WIDTH=3% >" + Util.formatCell("") + "</TD>");
                }else{	
                	myRow.append("<TD NOWRAP ALIGN=center WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_RUTA_DESTINO")) + "</TD>");  
                }	
                double monto=Double.parseDouble(resultSet.getString("RPC_MONTO"));;
                myRow.append("<TD NOWRAP ALIGN=right WIDTH=3% >" + Util.formatCell(num.format(monto)) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=left WIDTH=2%>" + Util.formatCell(resultSet.getString("RPC_ESTATUS_PAGO")) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=left WIDTH=4%>" + Util.formatCell(resultSet.getString("RPC_ESTATUS_PROCESO")) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=leftWIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_TIPO_TRANSACCION")) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=center WIDTH=2%>" + Util.formatCell(resultSet.getString("RPC_CODIGO_BANCO")) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=center WIDTH=2%>" + Util.formatCell(resultSet.getString("RPC_CODIGO_OFICINA")) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=center WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_FECHA_PROCESO").substring(0,10)) + "</TD>"); 
                if(resultSet.getString("RPC_FECHA_CAMARA")==null){
                	myRow.append("<TD NOWRAP ALIGN=center WIDTH=4% >" + Util.formatCell("") + "</TD>");
                }else{
                	myRow.append("<TD NOWRAP ALIGN=center WIDTH=4%>" + Util.formatCell(resultSet.getString("RPC_FECHA_CAMARA")) + "</TD>");  
                }	
                if(resultSet.getString("RPC_FECHA_DEVOLUCION")==null){
                	myRow.append("<TD NOWRAP ALIGN=center WIDTH=4% >" + Util.formatCell("") + "</TD>");
                }else{	
                	myRow.append("<TD NOWRAP ALIGN=center WIDTH=4%>" + Util.formatCell(resultSet.getString("RPC_FECHA_DEVOLUCION").substring(0,10)) + "</TD>");
                }
                if(resultSet.getString("RPC_CODIGO_CONDICION_CHEQUE")==null){
                	myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=4%>" + Util.formatCell("") + "</TD>");
                }else{
                	myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=4%>" + Util.formatCell(resultSet.getString("RPC_CODIGO_CONDICION_CHEQUE")) + "</TD>");  
                }
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3% >" + Util.formatCell(resultSet.getString("RPC_TIPO_DOCUMENTO")) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3% >" + Util.formatCell(resultSet.getString("RPC_CANTIDAD_CHEQUES")) + "</TD>");
               // myRow.append("<TD NOWRAP ALIGN=LEFT >" + Util.formatCell(resultSet.getString("RPC_LINK_IMG")) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_TIPO_CAMARA")) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2% >" + Util.formatCell(resultSet.getString("RPC_SUBTRANSACCION")) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(resultSet.getString("RPC_GIRADOR")) + "</TD>");                      
                myRow.append("<TD NOWRAP ALIGN=center WIDTH=5%>" + Util.formatCell(resultSet.getString("RPC_ESTATUS_ESCANEO")) + "</TD>"); 
                if(resultSet.getString("RPC_OBSERVACION")==null){
                	myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3% >" + Util.formatCell("") + "</TD>");
                }else{
                	myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_OBSERVACION")) + "</TD>");
                }
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=5%>" + Util.formatCell(resultSet.getString("RPC_DIGITO_VERIFICADOR")) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_DOCUMENT_ID")) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_FECHA_CHEQUE")) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_BENEFICIARIO")) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_EST_TRUNCAMIENTO")) + "</TD>");  
                if(resultSet.getString("RPC_X9_BATCH_REFERENCE")==null){
                	myRow.append("<TD NOWRAP ALIGN=LEFT >" + Util.formatCell("") + "</TD>");
                }else{
                	myRow.append("<TD NOWRAP ALIGN=LEFT >" + Util.formatCell(resultSet.getString("RPC_X9_BATCH_REFERENCE")) + "</TD>");  
                }
                if(resultSet.getString("RPC_USERID")==null){
                	myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell("") + "</TD>");
                }else{
                	myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_USERID")) + "</TD>");
                }
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_TIPO_TRANSACCION_CHEQUE")) + "</TD>");
                if(resultSet.getString("RPC_DOCUMENT_ORIGEN" )==null){
                	myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3% >" + Util.formatCell("") + "</TD>");
                }else{
                	myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_DOCUMENT_ORIGEN")) + "</TD>");  
                }
                myRow.append("<TD NOWRAP ALIGN=LEFT  WIDTH=3%>" + Util.formatCell(resultSet.getString("RPC_FISA")) + "</TD>");
                myRow.append("</TR>");
                beanList.addRow("", myRow.toString()); 
                cont+=1;
               

            }
          
            ResultSet resultSet2 = statement.executeQuery("Select SUM(RPC_MONTO)AS suma From TBL_RESUMEN_PROCESO_CAMARAS Where RPC_CODIGO_CAMARA = '001' And RPC_TIPO_CAMARA = '1' And RPC_ESTATUS_PROCESO = 'ES0' And Rpc_Tipo_Documento = 1  And Rpc_estatus_pago = 1 and RPC_FECHA_PROCESO='"+fecha+"'");
            resultSet2.next();
            if(cont>0){
            	suma=Double.parseDouble(resultSet2.getString(1));
            }
            flexLog("Putting java beans into the session");
            userPO.setHeader23(Integer.toString(cont));
            userPO.setHeader22(num.format(suma));
            ses.setAttribute("cifPos", beanList);
            ses.setAttribute("userPO", userPO);
          //  System.out.print("numero de trans: "+cont+"  total de montos: "+sum);
            statement.close();
            try
            {         	  
          	  
               flexLog("About to call Page: " + LangPath + "EDD2000_pkm_to_ibs.jsp?fecha="+fecha);
               callPage(LangPath + "EDD2000_pkm_to_ibs.jsp?fecha="+fecha, req, res);
          	               		  
          	  
            }
            catch(Exception e)
            {
                flexLog("Exception calling page " + e);
            }
       
        }
        catch(ClassNotFoundException ex)
        {
            
           System.out.print("Error1 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(SQLException ex)
        {
     
            System.out.print( "Error2 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(Exception ex)
        {
        
            System.out.print( "Error3 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
	      
	      
	        if(conexion!=null)
	        {
	            System.out.print("Coexion Exitosa");
	        }

 
      }
	
	protected void Read_Ibs(MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException  {
		
		UserPos userPO = null;
		userPO = (UserPos)ses.getAttribute("userPO");
		Connection conexion=null;
		JBList beanList = null; 
		EDD200001Message msgList = null;
		MessageRecord newmessage = null;
   
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
            flexLog("Send Initial Data");
            msgList = (EDD200001Message)mc.getMessageRecord("EDD200001");
            msgList.setH01USERID(user.getH01USR());
            msgList.setH01PROGRM("EDD2000");
            msgList.setH01TIMSYS(getTimeStamp());
            msgList.setH01SCRCOD("");
            msgList.setH01OPECOD("0002");
            msgList.send();
            msgList.destroy();
            flexLog("EDD200001 Message Sent");
            newmessage = mc.receiveMessage();
            
           
            if(newmessage.getFormatName().equals("EDD200001"))//mensaje de listado de transacciones rechazadas
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
                int cont=0;
                DecimalFormat num = new DecimalFormat("####.00"); 
                double sum=0;
                
                do
                {
                	
                    msgList = (EDD200001Message)newmessage;
                    marker = msgList.getE01C10REV();
                    
                    if(marker.equals("*"))
                    {
                        beanList.setShowNext(false);
                        break;
                    }                      
                  
                  myRow = new StringBuffer();
            	myRow.append("<TR>");
             //   myRow.append("<TD NOWRAP><input type=\"radio\" name=\"REF\" value=\"" + Ref+ "\" " + chk + "></TD>");
            	myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=1%>" + Util.formatCell(msgList.getE01C10IDN()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10COD()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10KBT()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10CHK()) + "</TD>");                      
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10OAC()) + "</TD>");      
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10DAC()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10RUO()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10RUD()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=RIGHT WIDTH=3%>" + Util.formatCell(msgList.getE01C10MTO()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10SPA()) + "</TD>"); 
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10SPR()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10TTR()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10BNK()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10BRN()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10FPR()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10FCA()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10FDE()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10CCC()) + "</TD>"); 
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10TDO()) + "</TD>");  	
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10CCH()) + "</TD>");              
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10LNK()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10TIP()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10STR()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10GIR()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10SSC()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10OBS()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10DIG()) + "</TD>");                      
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10DID()) + "</TD>"); 
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10FCH()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10BEN()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10TRU()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10X9B()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10USR()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10TTC()) + "</TD>"); 
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10DOO()) + "</TD>");  
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=2%>" + Util.formatCell(msgList.getE01C10BID()) + "</TD>");
                myRow.append("<TD NOWRAP ALIGN=LEFT WIDTH=3%>" + Util.formatCell(msgList.getE01C10BID()) + "</TD>");
                
                
                myRow.append("</TR>");
                beanList.addRow(myFlag, myRow.toString());                
                newmessage = mc.receiveMessage();
                cont+=1;
              // String aux=msgList.getE01C10MTO().replace(".","");
               double auxi=Double.parseDouble(msgList.getE01C10MTO().replace(",", ""));
                sum+=auxi;
              
                
            } while(true);
                
            flexLog("Putting java beans into the session");
            userPO.setHeader23(Integer.toString(cont));
            userPO.setHeader22(num.format(sum));
            ses.setAttribute("cifPos", beanList);
            ses.setAttribute("userPO", userPO);
            
                   	  
          	  
               flexLog("About to call Page: " + LangPath + "EDD2000_ibsTopkm.jsp");
               callPage(LangPath + "EDD2000_ibsTopkm.jsp", req, res);
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
          	  
           
	
	protected void Send_To_IBS(MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException  {
		
		UserPos userPO = null;
		userPO = (UserPos)ses.getAttribute("userPO");
		Connection conexion=null;
		JBList beanList = null; 
		EDD200001Message msgList = null;
		MessageRecord newmessage = null;
		String fecha=req.getParameter("fecha").trim();
		String fechadeldia,fechadeldia2;
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();  
        fechadeldia=dateFormat.format(date); 
        fechadeldia2=dateFormat2.format(date); 
    	MessageRecord newmessage1 = null;
		EFTP00101Message msgList1 = null;
        ELEERRMessage msgError = null;
        String url1="";
		String bd="";
		String usr="",port="";
		String pass="";
	////Conexion parametrizable desde el as400
		try
	    {
	    	msgList1 = (EFTP00101Message)mc.getMessageRecord("EFTP00101");
	    	msgList1.setEFTPUSERID(user.getH01USR());
	    	msgList1.setEFTPPROGRM("EFTP001");
	    	msgList1.setEFTPTIMSYS(getTimeStamp());
	    	msgList1.setEFTPSCRCOD("");
	    	msgList1.setEFTPOPECOD("0001"); 
	    	msgList1.setE01FTCDE("CC");
	    	
	      flexLog("EFTP00101 Enter Account Header Sent");    	     
	      msgList1.send();
	      msgList1.destroy();
	    } catch (Exception e) {
	      e.printStackTrace();
	      flexLog("EFTP00101 Error: " + e);
	      throw new RuntimeException("Socket Communication Error");
	    }

	 // Receive Error Message
		try {
			newmessage1 = mc.receiveMessage();

			if (newmessage1.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage1;
				boolean IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				flexLog("About to call Page: " + LangPath + "EDD200_prev_fec.jsp");
				callPage(LangPath + "EDD200_prev_fec.jsp", req, res);
			} else{
				msgList1 = (EFTP00101Message) newmessage1;    	
				url1=msgList1.getE01FTADD();
				usr=msgList1.getE01FTUSR();
				pass=msgList1.getE01FTPWR();
				String[] result = msgList1.getE01FTTPT().split(":");
				bd=result[0];
				port=result[1];
				
				msgList1.destroy();
				flexLog("Message " + newmessage1.getFormatName() + " received.");
			}	
				
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		
		
		
		////////
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://"+url1+":"+port+";databaseName="+bd+";";
            
            conexion= DriverManager.getConnection(url,usr,pass);
            Statement statement = conexion.createStatement();
            StringBuffer myRow = null;
            try
            {
                beanList = new JBList();
            }
            catch(Exception ex)
            {
                flexLog("Error: " + ex);
            }
            
            
            ResultSet resultSet = statement.executeQuery("Select * From TBL_RESUMEN_PROCESO_CAMARAS Where RPC_CODIGO_CAMARA = '001' And RPC_TIPO_CAMARA = '1' And RPC_ESTATUS_PROCESO = 'ES0' And Rpc_Tipo_Documento = 1  And Rpc_estatus_pago = 1 and RPC_FECHA_PROCESO='"+fecha+"'");
            while (resultSet.next()) {  
            	 try
                 {
                     flexLog("Send Initial Data");
                     msgList = (EDD200001Message)mc.getMessageRecord("EDD200001");
                     msgList.setH01USERID(user.getH01USR());
                     msgList.setH01PROGRM("EDD2000");
                     msgList.setH01TIMSYS(getTimeStamp());
                     msgList.setH01SCRCOD("");
                     msgList.setH01OPECOD("0001");
                     msgList.setE01C10IDN(resultSet.getString("RPC_ID"));
                     msgList.setE01C10COD(resultSet.getString("RPC_CODIGO_CAMARA"));
                     msgList.setE01C10KBT(resultSet.getString("RPC_NUMERO_LOTE"));
                     msgList.setE01C10CHK(resultSet.getString("RPC_NUMERO_CHEQUE"));
                     msgList.setE01C10OAC(resultSet.getString("RPC_NUMERO_CUENTA_ORIGEN"));
                     msgList.setE01C10DAC(resultSet.getString("RPC_NUMERO_CUENTA_DESTINO"));
                     msgList.setE01C10RUO(resultSet.getString("RPC_RUTA_ORIGEN"));
                     if(resultSet.getString("RPC_RUTA_DESTINO")==null){
                    	 msgList.setE01C10RUD("");
                     }else{	
                    	 msgList.setE01C10RUD(resultSet.getString("RPC_RUTA_DESTINO")); 
                     }          
                     
                     msgList.setE01C10MTO(resultSet.getString("RPC_MONTO"));
                     msgList.setE01C10SPA(resultSet.getString("RPC_ESTATUS_PAGO"));
                     msgList.setE01C10SPR(resultSet.getString("RPC_ESTATUS_PROCESO"));
                     msgList.setE01C10TTR(resultSet.getString("RPC_TIPO_TRANSACCION"));
                     msgList.setE01C10BNK(resultSet.getString("RPC_CODIGO_BANCO"));
                     msgList.setE01C10BRN(resultSet.getString("RPC_CODIGO_OFICINA"));
                     String ano=resultSet.getString("RPC_FECHA_PROCESO").substring(0,4);
                     String dia=resultSet.getString("RPC_FECHA_PROCESO").substring(8,10);
                     String mes=resultSet.getString("RPC_FECHA_PROCESO").substring(5,7);
                     msgList.setE01C10FPR(dia+"/"+mes+"/"+ano);
                                         
                     msgList.setE01C10FCA(fechadeldia2);  
                     
                     if(resultSet.getString("RPC_FECHA_DEVOLUCION")==null){
                    	 msgList.setE01C10FDE("");
                     }else{	
                    	 msgList.setE01C10FDE(resultSet.getString("RPC_FECHA_DEVOLUCION"));
                     }
                     if(resultSet.getString("RPC_CODIGO_CONDICION_CHEQUE")==null){
                    	 msgList.setE01C10CCC("");;
                     }else{
                    	 msgList.setE01C10CCC(resultSet.getString("RPC_CODIGO_CONDICION_CHEQUE"));  
                     } 
                     msgList.setE01C10TDO(resultSet.getString("RPC_TIPO_DOCUMENTO"));
                     msgList.setE01C10CCH(resultSet.getString("RPC_CANTIDAD_CHEQUES"));
                     msgList.setE01C10LNK(resultSet.getString("RPC_LINK_IMG"));
                     msgList.setE01C10TIP(resultSet.getString("RPC_TIPO_CAMARA"));
                     msgList.setE01C10STR(resultSet.getString("RPC_SUBTRANSACCION"));
                     msgList.setE01C10GIR(resultSet.getString("RPC_GIRADOR"));
                     msgList.setE01C10SSC(resultSet.getString("RPC_ESTATUS_ESCANEO"));
                     if(resultSet.getString("RPC_OBSERVACION")==null){
                    	 msgList.setE01C10OBS("");
                     }else{
                    	 msgList.setE01C10OBS(resultSet.getString("RPC_OBSERVACION"));
                     }                     
                     msgList.setE01C10DIG(resultSet.getString("RPC_DIGITO_VERIFICADOR"));
                     msgList.setE01C10DID(resultSet.getString("RPC_DOCUMENT_ID"));
                     msgList.setE01C10FCH(resultSet.getString("RPC_FECHA_CHEQUE"));
                     msgList.setE01C10BEN(resultSet.getString("RPC_BENEFICIARIO"));
                     msgList.setE01C10TRU(resultSet.getString("RPC_EST_TRUNCAMIENTO"));
                     if(resultSet.getString("RPC_X9_BATCH_REFERENCE")==null){
                    	 msgList.setE01C10X9B("");
                     }else{
                    	 msgList.setE01C10X9B(resultSet.getString("RPC_X9_BATCH_REFERENCE"));  
                     }
                     if(resultSet.getString("RPC_USERID")==null){
                    	 msgList.setE01C10USR("");
                     }else{
                    	 msgList.setE01C10USR(resultSet.getString("RPC_USERID"));
                     }   
                     msgList.setE01C10TTC(resultSet.getString("RPC_TIPO_TRANSACCION_CHEQUE"));
                     if(resultSet.getString("RPC_DOCUMENT_ORIGEN" )==null){
                    	 msgList.setE01C10DOO("");
                     }else{
                    	 msgList.setE01C10DOO(resultSet.getString("RPC_DOCUMENT_ORIGEN"));
                     }
                     if(resultSet.getString("RPC_BATCH_ID")==null){
                    	 msgList.setE01C10BID("");
                     }else{
                    	 msgList.setE01C10BID(resultSet.getString("RPC_BATCH_ID"));
                     }                     
                     msgList.send();
                     msgList.destroy();
                     flexLog("EDD200001 Message Sent");
                     newmessage = mc.receiveMessage();
                 }
                 catch(Exception e)
                 {
                     e.printStackTrace();
                     flexLog("Error: " + e);
                     throw new RuntimeException("Socket Communication Error");
                 }
            	 Statement statementAct = conexion.createStatement();
            	 statementAct.execute("Update TBL_RESUMEN_PROCESO_CAMARAS Set RPC_ESTATUS_PROCESO = 'ES1' , RPC_FECHA_CAMARA='"+fechadeldia+"' Where RPC_ID = "+resultSet.getString("RPC_ID"));
            	 
            
            }
            statement.close();
            flexLog("Putting java beans into the session");
            ses.setAttribute("cifPos", beanList);
            ses.setAttribute("userPO", userPO);
            
            try
            {         	  
          	  
               flexLog("About to call Page: " + LangPath + "EDD200_prev_fec.jsp");
               callPage(LangPath + "EDD200_prev_fec.jsp", req, res);
          	               		  
          	  
            }
            catch(Exception e)
            {
                flexLog("Exception calling page " + e);
            }
       
        }
        catch(ClassNotFoundException ex)
        {
            
           System.out.print("Error1 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(SQLException ex)
        {
     
            System.out.print( "Error2 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(Exception ex)
        {
        
            System.out.print( "Error3 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
	      
	      
	        if(conexion!=null)
	        {
	            System.out.print("Coexion Exitosa");
	        }

 
      }

	protected void Send_To_BD(MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)	
			throws ServletException, IOException  {
		
		UserPos userPO = null;
		userPO = (UserPos)ses.getAttribute("userPO");
		Connection conexion=null;		 
		EDD200001Message msgList = null;
		MessageRecord newmessage = null;
		JBList beanList = null;
		MessageRecord newmessage1 = null;
		EFTP00101Message msgList1 = null;
        ELEERRMessage msgError = null;
        String url1="";
		String bd="";
		String usr="",port="";
		String pass="";
	////Conexion parametrizable desde el as400
		try
	    {
	    	msgList1 = (EFTP00101Message)mc.getMessageRecord("EFTP00101");
	    	msgList1.setEFTPUSERID(user.getH01USR());
	    	msgList1.setEFTPPROGRM("EFTP001");
	    	msgList1.setEFTPTIMSYS(getTimeStamp());
	    	msgList1.setEFTPSCRCOD("");
	    	msgList1.setEFTPOPECOD("0001"); 
	    	msgList1.setE01FTCDE("CC");
	    	
	      flexLog("EFTP00101 Enter Account Header Sent");    	     
	      msgList1.send();
	      msgList1.destroy();
	    } catch (Exception e) {
	      e.printStackTrace();
	      flexLog("EFTP00101 Error: " + e);
	      throw new RuntimeException("Socket Communication Error");
	    }

	 // Receive Error Message
		try {
			newmessage1 = mc.receiveMessage();

			if (newmessage1.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage1;
				boolean IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				flexLog("About to call Page: " + LangPath + "EDD200_prev_fec.jsp");
				callPage(LangPath + "EDD200_prev_fec.jsp", req, res);
			} else{
				msgList1 = (EFTP00101Message) newmessage1;    	
				url1=msgList1.getE01FTADD();
				usr=msgList1.getE01FTUSR();
				pass=msgList1.getE01FTPWR();
				String[] result = msgList1.getE01FTTPT().split(":");
				bd=result[0];
				port=result[1];
				
				msgList1.destroy();
				flexLog("Message " + newmessage1.getFormatName() + " received.");
			}	
				
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		
		
		
		////////
		
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://"+url1+":"+port+";databaseName="+bd+";";
            
            conexion= DriverManager.getConnection(url,usr,pass);
            Statement statement = conexion.createStatement();
            StringBuffer myRow = null;
       
                
                flexLog("Send Initial Data");
                msgList = (EDD200001Message)mc.getMessageRecord("EDD200001");
                msgList.setH01USERID(user.getH01USR());
                msgList.setH01PROGRM("EDD2000");
                msgList.setH01TIMSYS(getTimeStamp());
                msgList.setH01SCRCOD("");
                msgList.setH01OPECOD("0003");
                msgList.send();
                msgList.destroy();
                flexLog("EDD200001 Message Sent");
                newmessage = mc.receiveMessage();
                
               
                if(newmessage.getFormatName().equals("EDD200001"))//mensaje de listado de transacciones rechazadas
                {
                 
                   
                    String marker = "";
                   
                   
                    do
                    {
                        msgList = (EDD200001Message)newmessage;
                        marker = msgList.getE01C10REV();
                        
                        if(marker.equals("*"))
                        {
                            //beanList.setShowNext(false);
                            break;
                        }  
                        String monto=msgList.getE01C10MTO().replace(",","");
                      //  monto=monto.replace(",", "");
                        Statement statementAct = conexion.createStatement();
                        Date date = new Date();  
                        String fechadeldia;
                		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
                		fechadeldia=dateFormat.format(date);
                       	//ojo, donde dice bancamerica debe ir el nombre de la base de datos
                        String qry="Insert Into TBL_RESUMEN_PROCESO_CAMARAS (RPC_CODIGO_CAMARA,RPC_BATCH_ID,RPC_NUMERO_LOTE,RPC_NUMERO_CHEQUE,RPC_NUMERO_CUENTA_ORIGEN,"
                        		+ "RPC_NUMERO_CUENTA_DESTINO,RPC_RUTA_ORIGEN,RPC_RUTA_DESTINO,RPC_MONTO,RPC_ESTATUS_PAGO,RPC_ESTATUS_PROCESO,RPC_TIPO_TRANSACCION,RPC_CODIGO_BANCO,"
                        		+ "RPC_CODIGO_OFICINA,RPC_FECHA_PROCESO,RPC_FECHA_CAMARA,RPC_FECHA_DEVOLUCION, RPC_CODIGO_CONDICION_CHEQUE,RPC_TIPO_DOCUMENTO,RPC_CANTIDAD_CHEQUES,"
                        		+ "RPC_LINK_IMG,RPC_TIPO_CAMARA,RPC_SUBTRANSACCION,RPC_GIRADOR,RPC_ESTATUS_ESCANEO,RPC_OBSERVACION, RPC_DIGITO_VERIFICADOR,RPC_DOCUMENT_ID,RPC_FECHA_CHEQUE,"
                        		+ "RPC_BENEFICIARIO,RPC_EST_TRUNCAMIENTO,RPC_X9_BATCH_REFERENCE,RPC_USERID,RPC_TIPO_TRANSACCION_CHEQUE,RPC_DOCUMENT_ORIGEN,RPC_FISA) Values("+msgList.getE01C10COD()+","+msgList.getE01C10BID()+","
                        		+msgList.getE01C10KBT()+","+msgList.getE01C10CHK()+","+msgList.getE01C10OAC()+","+msgList.getE01C10DAC()+","+msgList.getE01C10RUO()+","+msgList.getE01C10RUD()+","+monto+","
                        		+fechadeldia+",'"+msgList.getE01C10SPR()+"',"+msgList.getE01C10TTR()+","+msgList.getE01C10BNK()+","+msgList.getE01C10BRN()+","+msgList.getE01C10FPR()+","+msgList.getE01C10FCA()+","
                        		+msgList.getE01C10FDE()+","+msgList.getE01C10CCC()+","+msgList.getE01C10TDO()+","+msgList.getE01C10CCH()+",'"+msgList.getE01C10LNK()+"',"+msgList.getE01C10TIP()+","+msgList.getE01C10STR()+",'"
                        		+msgList.getE01C10GIR()+"',"+msgList.getE01C10SSC()+","+msgList.getE01C10OBS()+","+msgList.getE01C10DIG()+","+msgList.getE01C10DID()+","+msgList.getE01C10FCH()+",'"+msgList.getE01C10BEN()+"',"
                        		+msgList.getE01C10TRU()+","+msgList.getE01C10X9B()+",'"+msgList.getE01C10USR()+"',"+msgList.getE01C10TTC()+","+msgList.getE01C10DOO()+","+msgList.getE01C10FIS()+")";           
                    		
                        qry=qry.replace(",,", ",NULL,");
                        qry=qry.replace(",,", ",NULL,");
                        statementAct.execute(qry);
                       newmessage = mc.receiveMessage();
                } while(true);
           
            	statement.close();
            	 
            
            
		            flexLog("Putting java beans into the session");
		            ses.setAttribute("cifPos", beanList);
		            ses.setAttribute("userPO", userPO);
		            
		            try
		            {         	  
		          	  
		               flexLog("About to call Page: " + LangPath + "EDD2000_ibsTopkm.jsp");
		               callPage(LangPath + "EDD2000_ibsTopkm.jsp", req, res);
		          	               		  
		          	  
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
        catch(ClassNotFoundException ex)
        {
            
           System.out.print("Error1 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(SQLException ex)
        {
     
            System.out.print( "Error2 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(Exception ex)
        {
        
            System.out.print( "Error3 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
	      
	      
	        if(conexion!=null)
	        {
	            System.out.print("Coexion Exitosa");
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

			int screen = E_camara_rec;

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
					
					case fec_camara_rec :
						procReqFec(mc, msgUser, req, res, session);
						break;
					case E_camara_rec :
						odbc(mc, msgUser, req, res, session);
						break;	
					case S_send_camara_entrante :
						Send_To_IBS(mc, msgUser, req, res, session);
						break;	
					case E_Read_ibs :
						Read_Ibs(mc, msgUser, req, res, session);
						break;	
					case S_send_camara_rechazada:
						Send_To_BD(mc, msgUser, req, res, session);
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