
package datapro.eibs.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.datapro.generics.BeanList;

import datapro.eibs.beans.EFA000001Message;
import datapro.eibs.beans.EFA000002Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSEFA0000 extends SuperServlet
{
    protected static final int R_ENTER_CUSTOMER         = 100;
    protected static final int R_GET_CUSTOMER_LIST      = 200;
	protected static final int R_PRINT_CUSTOMER_INVOICE = 300;
	protected static final int A_PRINT_PDF_INVOICE      = 400;
	protected static final int R_RETURN_TO_LIST         = 500;
	
	protected String LangPath = "S";
    
    public JSEFA0000(){
		super();
        LangPath = "S";
    }

    public void destroy()
    {
        flexLog("free resources used by JSEFA0000");
    }

    public void init(ServletConfig config)
        throws ServletException
    {
        super.init(config);
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        Socket s = null;
        MessageContext mc = null;
        ESS0030DSMessage msgUser = null;
        HttpSession session = null;
        session = req.getSession(false);
        if(session == null) {
            try  {
                res.setContentType("text/html");
                printLogInAgain(res.getWriter());
            }
            catch(Exception e) {
                e.printStackTrace();
                flexLog("Exception ocurred. Exception = " + e);
            }
        } else {
            int screen = 0;
            try {
                msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
                LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
                try {
                    flexLog("Opennig Socket Connection");
                    s = new Socket(SuperServlet.hostIP, SuperServlet.iniSocket + 1);
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
					flexLog("JSEFA0000 SCREEN : " + screen);
                    switch(screen)
                    {
                    case R_ENTER_CUSTOMER: 
                        procEnterCustomer(req, res);
                        break;

                    case R_GET_CUSTOMER_LIST: 
                        procListCustomerInvoice(mc, msgUser, req, res, session);
                        break;

					case R_PRINT_CUSTOMER_INVOICE: 
						procSendCustomerInvoice(mc, msgUser, req, res, session);
						break;
						
					case A_PRINT_PDF_INVOICE: 
						procPrintCustomerInvoice(mc, msgUser, req, res, session);
						break;	

					case R_RETURN_TO_LIST:
						procReturnToList( req, res);
						return;
                    default:
                        res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.devPage);
                        break;
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    int sck = SuperServlet.iniSocket + 2;
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
    
    protected void procEnterCustomer(HttpServletRequest req, HttpServletResponse res)   throws ServletException, IOException  {
        try   {
            flexLog("About to call Page: " + LangPath + "EFA0000_enter_customer.jsp.jsp");
            callPage(LangPath + "EFA0000_enter_customer.jsp", req, res);
        }
        catch(Exception e)    {
            flexLog("Exception calling page " + e);
        }
    }


    protected void procListCustomerInvoice(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException   {
        MessageRecord newmessage = null;
        EFA000001Message msgList = null;
		EFA000002Message msgCNFInfo = null;
		BeanList list = new BeanList();
        UserPos userPO = null;
        ELEERRMessage msgError = null;
        boolean IsNotError = false;
        String PageToCall = "EFA0000_enter_customer.jsp";
        String SELTYP = req.getParameter("E01OPESEL");
        try  {
			msgList = (EFA000001Message)mc.getMessageRecord("EFA000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EFA0000");
			msgList.setH01OPECOD("0099");
			msgList.setH01TIMSYS(SuperServlet.getTimeStamp());
			Enumeration enu = msgList.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while(enu.hasMoreElements()) {
			    field = (MessageField)enu.nextElement();
				try	{
					value = req.getParameter(field.getTag()).toUpperCase();
					if(value != null) field.setString(value);
				}
				catch(Exception exception) { }
			}
			msgList.send();
			msgList.destroy();
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try {
            newmessage = mc.receiveMessage();
            // Recieve Error or Structure
            if(newmessage.getFormatName().equals("ELEERR")) {
                msgError = (ELEERRMessage)newmessage;
                IsNotError = msgError.getERRNUM().equals("0");
                flexLog("IsNotError = " + IsNotError);
                showERROR(msgError);               
            } else {
            	// If not error 
				PageToCall = "EFA0000_customer_list.jsp";
				msgCNFInfo = (EFA000002Message) newmessage;
				msgCNFInfo.setH02FLGWK3(SELTYP);
				// Receive Details
				newmessage = mc.receiveMessage();
				msgList = (EFA000001Message)newmessage;
				while(!msgList.getH01FLGMAS().equals("*")){
					list.addRow(msgList);
					newmessage = mc.receiveMessage();
					msgList = (EFA000001Message)newmessage;
				}
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
		ses.setAttribute("error", msgError);
		ses.setAttribute("facusdata", msgCNFInfo);
		ses.setAttribute("facuslist", list);
		ses.setAttribute("userPO", userPO);
		try   {
			 flexLog("About to call Page: " + LangPath + PageToCall);
			 callPage(LangPath + PageToCall, req, res);
		} catch(Exception e)    {
			 flexLog("Exception calling page " + e);
		}
    }

	protected void procReturnToList(HttpServletRequest req, HttpServletResponse res)   throws ServletException, IOException  {
		try   {
			callPage(LangPath + "EFA0000_customer_list.jsp", req, res);
		}
		catch(Exception e)    {
			flexLog("Exception calling page " + e);
		}
	}


	protected void procSendCustomerInvoice(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws IOException{
		String PageToCall = "EFA0000_customer_frame.jsp";
		try   {
		 	flexLog("About to call Page: " + LangPath + PageToCall);
		 	callPage(LangPath + PageToCall, req, res);
		} catch(Exception e)    {
	 		flexLog("Exception calling page " + e);
		}
	}

	protected void procPrintCustomerInvoice(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws IOException{
		EFA000001Message msgList = null;
		EFA000002Message msgCNFInfo = null;
		BeanList Invoicelist = new BeanList();
		boolean IsError = false;
		
		String PageToCall ="EFA0000_customer_list.jsp";
		String E01TRFACR = req.getParameter("E01TRFACR");
		String E01TRFURN = req.getParameter("E01TRFURN");
		String E02CUSCUN = req.getParameter("E02CUSCUN");
		String E01TRFDT1 = req.getParameter("E01TRFDT1");
		String E01TRFDT2 = req.getParameter("E01TRFDT2");
		String E01TRFDT3 = req.getParameter("E01TRFDT3");
	
		String E01OPESEL = req.getParameter("E01OPESEL");
		
	
		msgCNFInfo = (EFA000002Message) ses.getAttribute("facusdata");
		Invoicelist = (BeanList) ses.getAttribute("facuslist");
		
	
		BigDecimal TotFac = new BigDecimal("0.00");
		BigDecimal TotExen = new BigDecimal("0.00");
		BigDecimal TotGravado = new BigDecimal("0.00");
		BigDecimal TotITBMS = new BigDecimal("0.00");

		BeanList LstData = new BeanList();
		if(E01OPESEL.trim().equals("R")){
			Invoicelist.initRow();
			while(Invoicelist.getNextRow()){
				msgList = (EFA000001Message)Invoicelist.getRecord();
				if(msgList.getE01TRFURN().equals(E01TRFURN)){
					LstData.addRow(msgList);
				}
			}
		}else{
			//Get new NCF for ACR in date
			LstData = this.procUpdateNCF(mc, user, req, ses, E02CUSCUN, E01TRFACR, E01TRFDT1, E01TRFDT2, E01TRFDT3);
		}
		

		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");		
		String ToDay = formatter.format(today);
		List BeanData = new ArrayList();;	
		BeanData.clear();
		LstData.initRow();
		while(LstData.getNextRow()){
			msgList = (EFA000001Message)LstData.getRecord();
			JSEFA0000Pdf ccrt = new JSEFA0000Pdf();		
			ccrt.setHeader1(msgCNFInfo.getE02CNTNME());
			ccrt.setHeader2(msgCNFInfo.getE02CNTADR());
			ccrt.setHeader3(msgCNFInfo.getE02CUSCTY());
			ccrt.setHeader4(msgCNFInfo.getE02CNTBID());
			ccrt.setHeader6(getServletConfig().getServletContext().getRealPath("/images/statement_logo.gif"));
			ccrt.setNofactura(msgList.getE01TRFNUM());	
			ccrt.setNcf(E01TRFURN);				
			ccrt.setNombre(msgCNFInfo.getE02CUSNA1());
			ccrt.setRnccustomer(msgCNFInfo.getE02CUSIDN());
			String FDate =  (msgList.getE01TRFDT1().trim().length()==1?"0"+msgList.getE01TRFDT1():msgList.getE01TRFDT1()) + "/" +
							(msgList.getE01TRFDT2().trim().length()==1?"0"+msgList.getE01TRFDT2():msgList.getE01TRFDT2()) + "/" +
							(msgList.getE01TRFDT3().trim().length()==1?"0"+msgList.getE01TRFDT3():msgList.getE01TRFDT3());
			String RDate =  (user.getE01RDD().trim().length()==1?"0"+user.getE01RDD():user.getE01RDD()) + "/" +
							(user.getE01RDM().trim().length()==1?"0"+user.getE01RDM():user.getE01RDM()) + "/" +
							(user.getE01RDY().trim().length()==1?"0"+user.getE01RDY():user.getE01RDY());
										
			ccrt.setDtlref1(FDate);
			if(E01OPESEL.trim().equals("R")){
				ccrt.setHeader1("Fecha de Reimpresion :");
			}else{
				ccrt.setHeader1("Fecha de Impresion :");
			}
			ccrt.setHeader2(RDate);
			ccrt.setDtlref2(msgList.getE01TRFAMT());
			ccrt.setDtlref3(msgList.getE01TRFDSC());
			ccrt.setFecha_emi(FDate);
			ccrt.setFooter1(msgList.getE01TRFCCY());
			// Obtiene el NCF para impresion nueva
		    ccrt.setNcf(msgList.getE01TRFURN());
		    ccrt.setTipofac(E01OPESEL.equals("I")?"ORIGINAL":"COPIA");
			BigDecimal importe = new BigDecimal("0.00");
			BigDecimal itbms = new BigDecimal("0.00");
			BigDecimal totrec = new BigDecimal("0.00");
			if(msgList.getBigDecimalE01TRFAMT().compareTo(new BigDecimal("0.00")) == 1){
				importe = importe.add(msgList.getBigDecimalE01TRFAMT());
				TotGravado = 	TotGravado.add(importe);
			}else{
				importe = importe.add(msgList.getBigDecimalE01TRFMEX());
				TotExen = TotExen.add(importe);
			}
			itbms = itbms.add(msgList.getBigDecimalE01TRFIVA());
			totrec = importe.add(itbms);
			TotITBMS = TotITBMS.add(itbms);
			TotFac = TotFac.add(totrec);
			ccrt.setCodigo(msgList.getE01TRFACR());
			ccrt.setDescripcion(msgList.getE01TRFDSC());
			ccrt.setImporte(importe);
			ccrt.setItbms(itbms);
			ccrt.setTotrec(totrec);	
			ccrt.setTotfac(TotFac);
			ccrt.setTotexento(TotExen);
			ccrt.setTotgravado(TotGravado);
			ccrt.setTotitbms(TotITBMS);
			BeanData.add(ccrt);											
		}
		if(IsError || BeanData.isEmpty()){
			try   {
				 flexLog("About to call Page: " + LangPath + PageToCall);
				 callPage(LangPath + PageToCall, req, res);
			} catch(Exception e)    {
				 flexLog("Exception calling page " + e);
			}
			return;			
		}
		 
		// --------- Call method to Generate PDF file via JASPER Reports ---------- //
		ServletOutputStream servletOutputStream = res.getOutputStream();
		ServletContext sc = getServletConfig().getServletContext() ;
		InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream("/reports/FacturaFiscal.jasper");
		
		try{
			JRBeanCollectionDataSource dataSource;					
			dataSource = new JRBeanCollectionDataSource( BeanData );					
			res.setContentType("application/pdf");
			JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, new HashMap(), dataSource );												
			servletOutputStream.flush();
			servletOutputStream.close();						
		} catch (JRException e) {
			// display stack trace in the browser
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			res.setContentType("text/plain");
			res.getOutputStream().print(stringWriter.toString());
		} catch(Exception eg){
			System.out.println("JSEFA0000, procPrintCustomerInvoice Error : " + eg);
			eg.printStackTrace();
		}
		finally{
			res.setContentType("text/html");
		}
			
	}


	protected BeanList procUpdateNCF(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req,HttpSession ses, String E02CUSCUN, String E01TRFACR, String E01TRFDT1, String E01TRFDT2, String E01TRFDT3) throws IOException{
		flexLog("Generando NCF Para Facturas: ");
		EFA000001Message msgList = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		BeanList lstNCF = new BeanList();
		
		try  {
			msgList = (EFA000001Message)mc.getMessageRecord("EFA000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("EFA0000");
			msgList.setH01OPECOD("0004");
			msgList.setH01TIMSYS(SuperServlet.getTimeStamp());
			msgList.setE01TRFCUN(E02CUSCUN);
			msgList.setE01TRFACR(E01TRFACR);
			msgList.setE01TRFDT1(E01TRFDT1);
			msgList.setE01TRFDT2(E01TRFDT2);
			msgList.setE01TRFDT3(E01TRFDT3);
			msgList.send();
			msgList.destroy();
		}
		catch(Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		try {
			newmessage = mc.receiveMessage();
			if(newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				ses.setAttribute("error", msgError);
				showERROR(msgError);               
			} else{
				msgList = (EFA000001Message) newmessage;
				while(!msgList.getH01FLGMAS().equals("*")){
					lstNCF.addRow(msgList);
					newmessage = mc.receiveMessage();
					if(newmessage.getFormatName().equals("ELEERR")) {
						msgError = (ELEERRMessage)newmessage;
						IsNotError = msgError.getERRNUM().equals("0");
						flexLog("IsNotError = " + IsNotError);
						ses.setAttribute("error", msgError);
						showERROR(msgError);
						break;               
					} 					
					msgList = (EFA000001Message) newmessage;
				}									
			}
			flexLog("NCF Generado Para Facturas: ");							
		}
		catch(Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}		

		return lstNCF;
	}


	protected void showERROR(ELEERRMessage m){
		if(SuperServlet.logType != 0){
			flexLog("ERROR received.");
			flexLog("ERROR number:" + m.getERRNUM());
			flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01() + " code : " + m.getERDF01());
			flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02() + " code : " + m.getERDF02());
			flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03() + " code : " + m.getERDF03());
			flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04() + " code : " + m.getERDF04());
			flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05() + " code : " + m.getERDF05());
			flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06() + " code : " + m.getERDF06());
			flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07() + " code : " + m.getERDF07());
			flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08() + " code : " + m.getERDF08());
			flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09() + " code : " + m.getERDF09());
			flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10() + " code : " + m.getERDF10());
		}
	}
}
