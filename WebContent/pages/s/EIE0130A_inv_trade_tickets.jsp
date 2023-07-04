<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Management Portfolio System</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invTrade" class="datapro.eibs.beans.EIE013001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>
<SCRIPT Language="Javascript">
       
      builtNewMenu(trade_a_ticket_opt);
 
   </SCRIPT>
</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 
<div align="center"> 
  <h3>Consulta de Orden de Compra - Venta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_trade_tickets.jsp,EIE0130A"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0130A" >
  <h4> 
    <input type="hidden" name="SCREEN"  value="2" >
    Informaci&oacute;n B&aacute;sica </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">N&uacute;mero de Orden :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01ORDNUM" size="9" maxlength="5" value="<%= invTrade.getE01ORDNUM()%>"
			  <% if (invTrade.getF01ORDNUM().equals("Y")) out.print("id=\"txtchanged\""); %> >
            </td>
            <td nowrap colspan="3" >
              <div align="right">Estado de la Orden :</div>
            </td>
            <td nowrap >
              <div align="left">
			  <input type="text" readonly  name="E01ORDSTS" size="35" maxlength="25" 
				<% if (invTrade.getF01ORDSTS().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDSTS().equals("P")) out.print("Pending");
							else if (invTrade.getE01ORDSTS().equals("V")) out.print("Voided");
							else if (invTrade.getE01ORDSTS().equals("C")) out.print("Confirmed");
							else out.print("");%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showCustomerInq(document.forms[0].E01ORDCUN.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Cliente </a>:</div>
            </td>
            <td nowrap colspan="5" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDCUN" size="9" maxlength="9" value="<%= invTrade.getE01ORDCUN().trim()%>"
				<% if (invTrade.getF01ORDCUN().equals("Y")) out.print("id=\"txtchanged\""); %>>
                <input type="text" readonly  name="E01ORDCTN" size="35" maxlength="35" value="<%= invTrade.getE01ORDCTN().trim()%>" 
				<% if (invTrade.getF01ORDCTN().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Portafolio :</div>
            </td>
            <td nowrap colspan="5" > 
              <input type="text" readonly  name="E01ORDPRF" size="9" maxlength="9" value="<%= invTrade.getE01ORDPRF()%>" 
			  <% if (invTrade.getF01ORDPRF().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showRetAccountInq(document.forms[0].E01ORDHAC.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Cuenta Transaccional</a> :</div>
            </td>
            <td nowrap colspan="5" > 
              <input type="text" readonly  name="E01ORDHCY2" size="3" maxlength="3" value="<%= invTrade.getE01ORDHCY()%>"
               <% if (invTrade.getF01ORDHCY().equals("Y")) out.print("id=\"txtchanged\""); %> >
              <input type="text" readonly  name="E01ORDHAC3" size="12" maxlength="9" value="<%= invTrade.getE01ORDHAC()%>"
                <% if (invTrade.getF01ORDHAC().equals("Y")) out.print("id=\"txtchanged\""); %>
				>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Saldo Disponible :</div>
            </td>
            <td nowrap width="108%" colspan="3" > 
              <input type="text" readonly  name="E01MEMBAL" size="17" maxlength="16" value="<%= invTrade.getE01MEMBAL()%>"
				<% if (invTrade.getF01MEMBAL().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
            <td nowrap width="14%" > 
              <div align="right">Saldo en <%= invTrade.getE01ORDSCY()%> :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01CNVBAL" size="17" maxlength="16" value="<%= invTrade.getE01CNVBAL()%>"
				<% if (invTrade.getF01CNVBAL().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">C&oacute;digo de Producto : </div>
            </td>
            <td nowrap colspan="5" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDCDE" size="5" maxlength="3" value="<%= invTrade.getE01ORDCDE()%>"
				<% if (invTrade.getF01ORDCDE().equals("Y")) out.print("id=\"txtchanged\""); %>>
                <input type="text" readonly  name="E01ORDPNM" size="35" maxlength="30" value="<%= invTrade.getE01ORDPNM()%>"
				<% if (invTrade.getF01ORDPNM().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" rowspan="2" > 
              <div align="right">Cuenta del Producto :</div>
            </td>
            <td nowrap width="16%"> 
              <div align="center">Banco</div>
            </td>
            <td nowrap width="17%"> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap width="17%"> 
              <div align="center">Moneda</div>
            </td>
            <td nowrap width="17%"> 
              <div align="center">Cuenta Contable</div>
            </td>
            <td nowrap width="18%"> 
              <div align="center"> Centro de Costo</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDPBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDPBK()%>"
				<% if (invTrade.getF01ORDPBK().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDPBR" size="3" maxlength="3"
                value="<%= invTrade.getE01ORDPBR()%>"
				<% if (invTrade.getF01ORDPBR().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDPCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDPCY()%>"
                <% if (invTrade.getF01ORDPCY().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDPGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDPGL()%>" 
				<% if (invTrade.getF01ORDPGL().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDPCC" size="12" maxlength="9" value="<%= invTrade.getE01ORDPCC()%>" 
				<% if (invTrade.getF01ORDPCC().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap colspan="5" > 
              <input type="text" readonly  name="E01ORDEXR" size="11" maxlength="11" value="<%= invTrade.getE01ORDEXR()%>"
				<% if (invTrade.getF01ORDEXR().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n del Instrumento </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Producto :</div>
            </td>
            <td nowrap width="36%" > 
			<input type="text" readonly  name="E01ORDPTY" size="35" maxlength="25" 
				<% if (invTrade.getF01ORDPTY().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDPTY().equals("BND")) out.print("Bonds");
							else if (invTrade.getE01ORDPTY().equals("EQT")) out.print("Equity");
							else if (invTrade.getE01ORDPTY().equals("MUT")) out.print("Mutual Funds");
							else if (invTrade.getE01ORDPTY().equals("PFS")) out.print("Preferred Stocks");
							else out.print("");%>" >
              
            </td>
            <td nowrap colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].E01ORDIIC.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Instrumento </a> :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDIIC" size="9" maxlength="9" value="<%= invTrade.getE01ORDIIC()%>" 
				<% if (invTrade.getF01ORDIIC().equals("Y")) out.print("id=\"txtchanged\""); %>>
              <input type="text" readonly  name="E01ORDICN" size="35" maxlength="30" value="<%= invTrade.getE01ORDICN()%>"
			  <% if (invTrade.getF01ORDICN().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Moneda del Instrumento :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDSCY" size="4" maxlength="3" value="<%= invTrade.getE01ORDSCY()%>"
			 <% if (invTrade.getF01ORDSCY().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
            <td nowrap width="14%" > 
              <div align="right"><a href="javascript:GetSymbolInfo(document.forms[0].E01ORDSYM.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Símbolo :</a> </div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDSYM" size="17" maxlength="15" value="<%= invTrade.getE01ORDSYM()%>"
			  <% if (invTrade.getF01ORDSYM().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha Ultimo Pago de Intereses :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDLP1" size="3" maxlength="2" value="<%= invTrade.getE01ORDLP1()%>"
			    <% if (invTrade.getF01ORDLP1().equals("Y")) out.print("id=\"txtchanged\""); %>>
              <input type="text" readonly  name="E01ORDLP2" size="3" maxlength="2" value="<%= invTrade.getE01ORDLP2()%>"
				 <% if (invTrade.getF01ORDLP2().equals("Y")) out.print("id=\"txtchanged\""); %>>
              <input type="text" readonly  name="E01ORDLP3" size="3" maxlength="2" value="<%= invTrade.getE01ORDLP3()%>"
				 <% if (invTrade.getF01ORDLP3().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
            <td nowrap width="14%" > 
              <div align="right">ISIN :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDISI" size="16" maxlength="12" value="<%= invTrade.getE01ORDISI()%>"
				 <% if (invTrade.getF01ORDISI().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Acumulaci&oacute;n :</div>
            </td>
            <td nowrap width="36%" >
			 <input type="text" readonly  name="E01ORDATY" size="35" maxlength="25" 
				<% if (invTrade.getF01ORDATY().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDATY().equals("1")) out.print("Actual/Actual");
							else if (invTrade.getE01ORDATY().equals("2")) out.print("Actual/365");
							else if (invTrade.getE01ORDATY().equals("3")) out.print("Actual/365(366 Leap Year)");
							else if (invTrade.getE01ORDATY().equals("4")) out.print("Actual/360");
							else if (invTrade.getE01ORDATY().equals("5")) out.print("30/360");
							else if (invTrade.getE01ORDATY().equals("6")) out.print("30E/360");
							else out.print("");%>" > 
              
            </td>
            <td nowrap width="14%" > 
              <div align="right">CUSIP :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDCSP" size="16" maxlength="12" value="<%= invTrade.getE01ORDCSP()%>"
				<% if (invTrade.getF01ORDCSP().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n de la Orden</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%" > 
              <div align="right">Precio M&iacute;nimo de Venta :</div>
            </td>
            <td nowrap width="71%" > 
              <input type="text" readonly  name="E01ORDMIP" size="17" maxlength="15" value="<%= invTrade.getE01ORDMIP()%>" 
			  <% if (invTrade.getF01ORDMIP().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" > 
              <div align="right">Precio M&aacute;ximo de Compra :</div>
            </td>
            <td nowrap width="71%"> 
              <input type="text" readonly  name="E01ORDMXP" size="17" maxlength="15" value="<%= invTrade.getE01ORDMXP()%>" 
			  <% if (invTrade.getF01ORDMXP().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right"> Notas de la Orden (Trader) :</div>
            </td>
            <td nowrap width="71%" > 
              <input type="text" readonly  name="E01ORDCO1" size="40" maxlength="35" value="<%= invTrade.getE01ORDCO1()%>" 
				>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="71%"> 
              <div align="right"></div>
              <div align="left"> 
                <input type="text" readonly  name="E01ORDCO2" size="40" maxlength="35" value="<%= invTrade.getE01ORDCO2()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right"> </div>
            </td>
            <td nowrap width="71%"> 
              <input type="text" readonly  name="E01ORDCO3" size="40" maxlength="35" value="<%= invTrade.getE01ORDCO3()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n de la Transacci&oacute;n </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Transacci&oacute;n :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
				<input type="text" readonly  name="E01ORDTRN" size="35" maxlength="25" 
				<% if (invTrade.getF01ORDTRN().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDTRN().equals("1")) out.print("Purchase");
							else if (invTrade.getE01ORDTRN().equals("2")) out.print("Sale");
							else if (invTrade.getE01ORDTRN().equals("3")) out.print("Transfer In");
							else if (invTrade.getE01ORDTRN().equals("4")) out.print("Transfer Out");
							else if (invTrade.getE01ORDTRN().equals("5")) out.print("Multiple Purchase");
							else if (invTrade.getE01ORDTRN().equals("6")) out.print("Multiple Sale");
							else if (invTrade.getE01ORDTRN().equals("7")) out.print("Adjustment (Decrement)");
							else out.print("");%>" >
                
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha de Orden :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDIN1" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN1()%>"
				<% if (invTrade.getF01ORDIN1().equals("Y")) out.print("id=\"txtchanged\""); %>>
                <input type="text" readonly  name="E01ORDIN2" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN2()%>"
				<% if (invTrade.getF01ORDIN2().equals("Y")) out.print("id=\"txtchanged\""); %>>
                <input type="text" readonly  name="E01ORDIN3" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN3()%>"
				<% if (invTrade.getF01ORDIN3().equals("Y")) out.print("id=\"txtchanged\""); %>>
                <a href="javascript:DatePicker(document.forms[0].E01ORDIN1,document.forms[0].E01ORDIN2,document.forms[0].E01ORDIN3)"> 
                </a> </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right">V&iacute;a Confirmaci&oacute;n Orden :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
				<input type="text" readonly  name="E01ORDCNF" size="20" maxlength="15" 
				<% if (invTrade.getF01ORDCNF().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDCNF().equals("N")) out.print("None");
							else if (invTrade.getE01ORDCNF().equals("E")) out.print("e-Mail");
							else if (invTrade.getE01ORDTRN().equals("F")) out.print("Fax");
							else if (invTrade.getE01ORDCNF().equals("P")) out.print("Printer");
							
							else out.print("");%>" >
                
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha Efectiva : </div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDST1" size="3" maxlength="2" value="<%= invTrade.getE01ORDST1()%>"
			  <% if (invTrade.getF01ORDST1().equals("Y")) out.print("id=\"txtchanged\""); %> >
              <input type="text" readonly  name="E01ORDST2" size="3" maxlength="2" value="<%= invTrade.getE01ORDST2()%>"
			  <% if (invTrade.getF01ORDST2().equals("Y")) out.print("id=\"txtchanged\""); %>>
              <input type="text" readonly  name="E01ORDST3" size="3" maxlength="2" value="<%= invTrade.getE01ORDST3()%>"
			  <% if (invTrade.getF01ORDST3().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDVNO" size="17" maxlength="16" value="<%= invTrade.getE01ORDVNO()%>"
				<% if (invTrade.getF01ORDVNO().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Cantidad : </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDQTY" size="17" maxlength="16" value="<%= invTrade.getE01ORDQTY()%>"
				<% if (invTrade.getF01ORDQTY().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Precio del Banco :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDPRB" size="17" maxlength="15" value="<%= invTrade.getE01ORDPRB()%>" onKeyPress="enterDecimal()"
				<% if (invTrade.getF01ORDPRB().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio del Cliente :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDPRC" size="17" maxlength="15" value="<%= invTrade.getE01ORDPRC()%>" onKeyPress="enterDecimal()"
				<% if (invTrade.getF01ORDPRC().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Monto de la Inversi&oacute;n :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" name="E01ORDIAM" size="17" maxlength="15" value="<%= invTrade.getE01ORDIAM()%>" readonly
				<% if (invTrade.getF01ORDIAM().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap width="14%" >&nbsp;</td>
            <td nowrap width="33%" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Intereses por Pagar :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDIAL" size="17" maxlength="15" value="<%= invTrade.getE01ORDIAL()%>" onKeyPress="enterDecimal()"
			  <% if (invTrade.getF01ORDIAL().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
            <td nowrap width="14%" > 
              <div align="right">D&iacute;as Acumulados :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDDYS" size="4" maxlength="3" value="<%= invTrade.getE01ORDDYS()%>"
				<% if (invTrade.getF01ORDDYS().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Comisi&oacute;n de la Operaci&oacute;n :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDOCV" size="17" maxlength="15" value="<%= invTrade.getE01ORDOCV()%>" onKeyPress="enterDecimal()"
				<% if (invTrade.getF01ORDOCV().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right">Tabla Comisi&oacute;n Operaci&oacute;n :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDOCT" size="4" maxlength="2" value="<%= invTrade.getE01ORDOCT()%>"
				<% if (invTrade.getF01ORDOCT().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" > 
              <div align="left"></div>
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Comisi&oacute;n de Rentabilidad :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDTGV" size="17" maxlength="15" value="<%= invTrade.getE01ORDTGV()%>" onKeyPress="enterDecimal()"
				<% if (invTrade.getF01ORDTGV().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" > 
              <div align="left"><b>Totales</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Monto Transacci&oacute;n del Banco :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDTBK" size="17" maxlength="15" value="<%= invTrade.getE01ORDTBK()%>" 
				<% if (invTrade.getF01ORDTBK().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Monto Transacci&oacute;n del Cliente :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDTVL" size="17" maxlength="15" value="<%= invTrade.getE01ORDTVL()%>" onKeyPress="enterDecimal()"
			  <% if (invTrade.getF01ORDTVL().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="left"><b>Saldo en Fecha Efectiva</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"> <a href="javascript:showInvHolds(document.forms[0].E01ORDCUN.value,document.forms[0].E01ORDHAC.value,document.forms[0].E01ORDST1.value
												,document.forms[0].E01ORDST2.value,document.forms[0].E01ORDST3.value)"> 
                <img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Saldo en <%= invTrade.getE01ORDHCY()%> : </a> </div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly name="E01FUTBAL" size="17" maxlength="15" value="<%= invTrade.getE01FUTBAL()%>" 
				<% if (invTrade.getF01FUTBAL().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Saldo en <%= invTrade.getE01ORDSCY()%> :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly name="E01CNVFUB" size="17" maxlength="15" value="<%= invTrade.getE01CNVFUB()%>" 
				<% if (invTrade.getF01CNVFUB().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Broker / Informaci&oacute;n del Custodio</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right">Broker :</div>
            </td>
            <td nowrap width="80%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDBRK" size="5" maxlength="3" value="<%= invTrade.getE01ORDBRK()%>" 
				<% if (invTrade.getF01ORDBRK().equals("Y")) out.print("id=\"txtchanged\""); %>>
                <input type="text" readonly  name="E01ORDBKN" size="35" maxlength="30" value="<%= invTrade.getE01ORDBKN()%>"
				<% if (invTrade.getF01ORDBKN().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right">Custodio :</div>
            </td>
            <td nowrap width="80%" > 
              <div align="left"></div>
              <div align="right"></div>
              <div align="left"> 
                <input type="text" readonly  name="E01ORDCUC" size="5" maxlength="3" value="<%= invTrade.getE01ORDCUC()%>" 
				<% if (invTrade.getF01ORDCUC().equals("Y")) out.print("id=\"txtchanged\""); %>>
                <input type="text" readonly  name="E01ORDCCN" size="35" maxlength="30" value="<%= invTrade.getE01ORDCCN()%>"
				<% if (invTrade.getF01ORDCCN().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n de la Cuenta</h4>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"><b></b></div>
            </td>
            <td nowrap > 
              <div align="center">Tipo de <br>
                Pago</div>
            </td>
            <td nowrap > 
              <div align="center">Banco</div>
            </td>
            <td nowrap> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap > 
              <div align="center">Moneda</div>
            </td>
            <td nowrap > 
              <div align="center">Cuenta Contable</div>
            </td>
            <td nowrap > 
              <div align="center">Referencia</div>
            </td>
            <td nowrap > 
              <div align="center">Centro de<br>
                Costo</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> Cuenta Contrapartida :</div>
            </td>
            <td nowrap > 
              <div align="center">Cuentas Corrientes</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDHBK()%>"
				<% if (invTrade.getF01ORDHBK().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHBR" size="3" maxlength="3"
                <% if (invTrade.getF01ORDHBR().equals("Y")) out.print("id=\"txtchanged\""); %>
				 value="<%= invTrade.getE01ORDHBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDHCY()%>"
                <% if (invTrade.getF01ORDHCY().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDHGL()%>" 
				<% if (invTrade.getF01ORDHGL().equals("Y")) out.print("id=\"txtchanged\""); %> >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHAC5" size="12" maxlength="9" value="<%= invTrade.getE01ORDHAC()%>"
                <% if (invTrade.getF01ORDHAC().equals("Y")) out.print("id=\"txtchanged\""); %> >
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E01ORDHCC" size="12" maxlength="9" value="<%= invTrade.getE01ORDHCC()%>"
				<% if (invTrade.getF01ORDHCC().equals("Y")) out.print("id=\"txtchanged\""); %>
				readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="8" > 
              <div align="left"><b>Cuentas de Repago</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> Inter&eacute;s :</div>
            </td>
            <td nowrap > 
			
              <div align="center"> 
				<input type="text" readonly  name="E01ORDIPT" size="35" maxlength="25" 
				<% if (invTrade.getF01ORDIPT().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDIPT().equals("1")) out.print("Retail Account");
							else if (invTrade.getE01ORDIPT().equals("2")) out.print("Official Check");
							else if (invTrade.getE01ORDIPT().equals("3")) out.print("G/L Account");
							else if (invTrade.getE01ORDIPT().equals("4")) out.print("FED Transfer");
							else if (invTrade.getE01ORDIPT().equals("5")) out.print("Telex Transfer");
							else if (invTrade.getE01ORDIPT().equals("6")) out.print("Swift Format MT-100");
							else if (invTrade.getE01ORDIPT().equals("7")) out.print("Swift Format MT-200");
							else if (invTrade.getE01ORDIPT().equals("8")) out.print("Swift Format MT-202");
							else out.print("None");%>" >
                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDIBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDIBK()%>"
				<% if (invTrade.getF01ORDIBK().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDIBR" size="3" maxlength="3"
                <% if (invTrade.getF01ORDIBR().equals("Y")) out.print("id=\"txtchanged\""); %>
				 value="<%= invTrade.getE01ORDIBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDICY" size="3" maxlength="3" value="<%= invTrade.getE01ORDICY()%>"
                <% if (invTrade.getF01ORDICY().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDIGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDIGL()%>" 
				<% if (invTrade.getF01ORDIGL().equals("Y")) out.print("id=\"txtchanged\""); %> >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDIAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDIAC()%>"
                <% if (invTrade.getF01ORDIAC().equals("Y")) out.print("id=\"txtchanged\""); %> >
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E01ORDHCC2" size="12" maxlength="9" value="<%= invTrade.getE01ORDICC()%>"
				<% if (invTrade.getF01ORDICC().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"> Capital :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
				<input type="text" readonly  name="E01ORDCPT" size="35" maxlength="25" 
				<% if (invTrade.getF01ORDCPT().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDCPT().equals("1")) out.print("Retail Account");
							else if (invTrade.getE01ORDCPT().equals("2")) out.print("Official Check");
							else if (invTrade.getE01ORDCPT().equals("3")) out.print("G/L Account");
							else if (invTrade.getE01ORDCPT().equals("4")) out.print("FED Transfer");
							else if (invTrade.getE01ORDCPT().equals("5")) out.print("Telex Transfer");
							else if (invTrade.getE01ORDCPT().equals("6")) out.print("Swift Format MT-100");
							else if (invTrade.getE01ORDCPT().equals("7")) out.print("Swift Format MT-200");
							else if (invTrade.getE01ORDCPT().equals("8")) out.print("Swift Format MT-202");
							else out.print("None");%>" >
               
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDCBK()%>"
				<% if (invTrade.getF01ORDCBK().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCBR" size="3" maxlength="3"
                 <% if (invTrade.getF01ORDCBR().equals("Y")) out.print("id=\"txtchanged\""); %>
				 value="<%= invTrade.getE01ORDCBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDCCY()%>"
                <% if (invTrade.getF01ORDCCY().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDCGL()%>" 
				<% if (invTrade.getF01ORDCGL().equals("Y")) out.print("id=\"txtchanged\""); %> >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDCAC()%>"
                <% if (invTrade.getF01ORDCAC().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E01ORDHCC3" size="12" maxlength="9" value="<%= invTrade.getE01ORDCCC()%>"
				readonly <% if (invTrade.getF01ORDCCC().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> Dividendos :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
				<input type="text" readonly  name="E01ORDDPT" size="35" maxlength="25" 
				<% if (invTrade.getF01ORDDPT().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDDPT().equals("1")) out.print("Retail Account");
							else if (invTrade.getE01ORDDPT().equals("2")) out.print("Official Check");
							else if (invTrade.getE01ORDDPT().equals("3")) out.print("G/L Account");
							else if (invTrade.getE01ORDDPT().equals("4")) out.print("FED Transfer");
							else if (invTrade.getE01ORDDPT().equals("5")) out.print("Telex Transfer");
							else if (invTrade.getE01ORDDPT().equals("6")) out.print("Swift Format MT-100");
							else if (invTrade.getE01ORDDPT().equals("7")) out.print("Swift Format MT-200");
							else if (invTrade.getE01ORDDPT().equals("8")) out.print("Swift Format MT-202");
							else out.print("None");%>" >
                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDDBK()%>"
				<% if (invTrade.getF01ORDDBK().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDBR" size="3" maxlength="3" value="<%= invTrade.getE01ORDDBR()%>"
                <% if (invTrade.getF01ORDDBR().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDDCY()%>"
                <% if (invTrade.getF01ORDDCY().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDDGL()%>"
				<% if (invTrade.getF01ORDDGL().equals("Y")) out.print("id=\"txtchanged\""); %>  >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDDAC()%>"
                <% if (invTrade.getF01ORDDAC().equals("Y")) out.print("id=\"txtchanged\""); %> >
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E01ORDHCC4" size="12" maxlength="9" value="<%= invTrade.getE01ORDDCC()%>"
				readonly <% if (invTrade.getF01ORDDCC().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="8" > 
              <div align="left"><b>Cuenta de Pago del Broker</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cuenta :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
				<input type="text" readonly  name="E01ORDDPT" size="35" maxlength="25" 
				<% if (invTrade.getF01ORDBPT().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDBPT().equals("1")) out.print("Retail Account");
							else if (invTrade.getE01ORDBPT().equals("2")) out.print("Official Check");
							else if (invTrade.getE01ORDBPT().equals("3")) out.print("G/L Account");
							else if (invTrade.getE01ORDBPT().equals("4")) out.print("FED Transfer");
							else if (invTrade.getE01ORDBPT().equals("5")) out.print("Telex Transfer");
							else if (invTrade.getE01ORDBPT().equals("6")) out.print("Swift Format MT-100");
							else if (invTrade.getE01ORDBPT().equals("7")) out.print("Swift Format MT-200");
							else if (invTrade.getE01ORDBPT().equals("8")) out.print("Swift Format MT-202");
							else out.print("None");%>" >
                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDBBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDBBK()%>"
				<% if (invTrade.getF01ORDBBK().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDBBR" size="3" maxlength="3" value="<%= invTrade.getE01ORDBBR()%>"
                <% if (invTrade.getF01ORDBBR().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDBCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDBCY()%>"
                <% if (invTrade.getF01ORDBCY().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDBGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDBGL()%>"
				<% if (invTrade.getF01ORDBGL().equals("Y")) out.print("id=\"txtchanged\""); %>  >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDBAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDBAC()%>"
                <% if (invTrade.getF01ORDBAC().equals("Y")) out.print("id=\"txtchanged\""); %> >
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E01ORDHCC5" size="12" maxlength="9" value="<%= invTrade.getE01ORDBCC()%>"
				readonly <% if (invTrade.getF01ORDBCC().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">V&iacute;a de Pago :</div>
            </td>
            <td nowrap > 

			<input type="text" readonly  name="E01ORDBVI" size="35" maxlength="25" 
				<% if (invTrade.getF01ORDBVI().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (invTrade.getE01ORDBVI().equals("N")) out.print("None");
							else if (invTrade.getE01ORDBVI().equals("A")) out.print("Retail Account");
							else if (invTrade.getE01ORDBVI().equals("C")) out.print("Official Check");
							else if (invTrade.getE01ORDBVI().equals("G")) out.print("G/L Account");
							else if (invTrade.getE01ORDBVI().equals("F")) out.print("FED Transfer");
							else if (invTrade.getE01ORDBVI().equals("T")) out.print("Telex Transfer");
							else if (invTrade.getE01ORDBVI().equals("1")) out.print("Swift Format MT-100");
							else if (invTrade.getE01ORDBVI().equals("2")) out.print("Swift Format MT-200");
							else if (invTrade.getE01ORDBVI().equals("3")) out.print("Swift Format MT-202");
							else out.print("None");%>" >

              
            </td>
            <td nowrap >&nbsp;</td>
            <td nowrap >&nbsp;</td>
            <td nowrap >&nbsp;</td>
            <td nowrap >&nbsp;</td>
            <td nowrap >&nbsp;</td>
            <td nowrap > 
              <div align="center"></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Porciento M&aacute;ximo de Garant&iacute;a :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"></div>
              <div align="right"></div>
              <div align="left"> 
                <input type="text" readonly  name="E01ORDMXC" size="11" maxlength="11" value="<%= invTrade.getE01ORDMXC()%>" 
				<% if (invTrade.getF01ORDMXC().equals("Y")) out.print("id=\"txtchanged\""); %>>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Notas de la Orden :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDTC1" size="35" maxlength="30" value="<%= invTrade.getE01ORDTC1()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDTC2" size="35" maxlength="30" value="<%= invTrade.getE01ORDTC2()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDTC3" size="35" maxlength="30" value="<%= invTrade.getE01ORDTC3()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Notas de la Orden (Back Office) :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDBC1" size="35" maxlength="30" value="<%= invTrade.getE01ORDBC1()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDBC2" size="35" maxlength="30" value="<%= invTrade.getE01ORDBC2()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDBC3" size="35" maxlength="30" value="<%= invTrade.getE01ORDBC3()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4><br>
    <%if(invTrade.getE01ORDTRN().equals("5")||invTrade.getE01ORDTRN().equals("6")){%> </h4>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
  <div align="center"> 
	      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
 <%}%>
  </form>
</body>
</html>
