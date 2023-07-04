<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Management Portfolio System</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invTrade" class="datapro.eibs.beans.EIE012001Message"  scope="session" />

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
 function Calculate(){
  document.forms[0].SCREEN.value = '16';
  document.forms[0].submit();

}

function recInterest(){
  
  if(document.forms[0].H01FLGWK3.value == 'Y'){
    document.forms[0].H01FLGWK3.value = '';
  }
  else {
    document.forms[0].H01FLGWK3.value = 'Y';
}
}
  
function recComm(){
  
  if(document.forms[0].H01FLGWK1.value == 'Y'){
    document.forms[0].H01FLGWK1.value = '';
  }
  else {
    document.forms[0].H01FLGWK1.value = 'Y';
}
}


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


%>
<div align="center"> 
  <h3>Ordenes de Compra - Venta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_trade_tickets.jsp,EIE0120"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0120" >
  <h4> 
    <input type="hidden" name="SCREEN"  value="2" >
    Informaci&oacute;n B&aacute;sica</h4>
  <table border="0">
    <tr>
      <td>
        <div align="right">N&uacute;mero de Orden :</div>
      </td>
      <td> 
        <div align="left">
          <input type="text" name="E01ORDNUM" size="9" maxlength="5" value="<%= invTrade.getE01ORDNUM()%>" readonly>
        </div>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo" width="715">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showCustomerInq(document.forms[0].E01ORDCUN.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Cliente </a>:</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01ORDCUN" size="9" maxlength="9" value="<%= invTrade.getE01ORDCUN().trim()%>">
              <input type="text" name="E01ORDCTN" size="35" maxlength="35" value="<%= invTrade.getE01ORDCTN().trim()%>" >
              <a href="javascript:GetCustomerDescIdInvest('E01ORDCUN','E01ORDCTN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
            <td nowrap colspan="2" ><a href="javascript:GetInvestmentsByCustomer(document.forms[0].E01ORDCUN.value,'E01ORDPRF','E01ORDIIC','E01ORDICN','E01ORDSCY','E01ORDCUC','E01ORDCCN','E01ORDPTY','E01ORDVNO','E01ORDQTY','E01ORDCSP','E01ORDSYM','E01ORDISI','E01ORDATY','E01ORDLP1','E01ORDLP2','E01ORDLP3')"> 
              Inversiones por Cliente<img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Portafolio :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDPRF" size="9" maxlength="9" value="<%= invTrade.getE01ORDPRF()%>" >
                <a href="javascript:GetPortfolioNumDesc('E01ORDPRF','E01ORDCUN','',document.forms[0].E01ORDCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showRetAccountInq(document.forms[0].E01ORDHAC.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Cuenta Transaccional: </a></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDHCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDHCY()%>"
                readonly >
              <input type="text" name="E01ORDHAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDHAC()%>"
                >
              <a href="javascript:GetCashAccount('E01ORDHCY','E01ORDHAC','E01MEMBAL',document.forms[0].E01ORDCUN.value,document.forms[0].E01ORDPRF.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"  ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Saldo Disponible :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01MEMBAL" size="17" maxlength="16" value="<%= invTrade.getE01MEMBAL()%>"
				onKeyPress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Tasa de Cambio : </div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDEXR" size="11" maxlength="11" value="<%= invTrade.getE01ORDEXR()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Saldo Moneda del Instrumento :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01CNVBAL" size="17" maxlength="16" value="<%= invTrade.getE01CNVBAL()%>"
				onKeyPress="enterDecimal()" readonly>
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
              <select name="E01ORDPTY">
                <option value="BND" <%if (invTrade.getE01ORDPTY().equals("BND")) { out.print("selected"); }%>>Bonos</option>
                <option value="EQT" <%if (invTrade.getE01ORDPTY().equals("EQT")) {  out.print("selected"); }%>>Acciones</option>
                <option value="MUT" <%if (invTrade.getE01ORDPTY().equals("MUT")) {  out.print("selected"); }%>>Fondos Mutuos</option>
                <option value="PFS" <%if (invTrade.getE01ORDPTY().equals("PFS")) {  out.print("selected"); }%>>Acciones Preferenciales</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
            <td nowrap colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].E01ORDIIC.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Instrumento </a> :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDIIC" size="9" maxlength="9" value="<%= invTrade.getE01ORDIIC()%>" >
              <input type="text" name="E01ORDICN" size="35" maxlength="30" value="<%= invTrade.getE01ORDICN()%>">
              <a href="javascript:GetInstrumentParams('E01ORDIIC','E01ORDICN','E01ORDSCY','E01ORDSYM','E01ORDISI','E01ORDCSP','E01ORDPTY')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Moneda del Instrumento :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" name="E01ORDSCY" size="4" maxlength="3" value="<%= invTrade.getE01ORDSCY()%>"readonly>
            </td>
            <td nowrap width="14%" > 
              <div align="right">ISIN : </div>
            </td>
            <td nowrap width="33%" >
              <input type="text" name="E01ORDISI" size="16" maxlength="12" value="<%= invTrade.getE01ORDISI()%>"readonly>
            </td>
          </tr>
		  
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha Ultimo Pago de Intereses :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" name="E01ORDLP1" size="3" maxlength="2" value="<%= invTrade.getE01ORDLP1()%>" readonly>
              <input type="text" name="E01ORDLP2" size="3" maxlength="2" value="<%= invTrade.getE01ORDLP2()%>" readonly>
              <input type="text" name="E01ORDLP3" size="3" maxlength="2" value="<%= invTrade.getE01ORDLP3()%>" readonly>
            </td>
            <td nowrap width="14%" > 
              <div align="right"><a href="javascript:GetSymbolInfo(document.forms[0].E01ORDSYM.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Símbolo :</a></div>
            </td>
            <td nowrap width="33%" >
              <input type="text"  name="E01ORDSYM" size="17" maxlength="15" value="<%= invTrade.getE01ORDSYM()%>"readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Acumulaci&oacute;n :</div>
            </td>
            <td nowrap width="36%" > 
              <select  name="E01ORDATY">
                <option value="1" <%if (invTrade.getE01ORDATY().equals("1")) { out.print("selected"); }%>>Actual/Actual</option>
                <option value="2" <%if (invTrade.getE01ORDATY().equals("2")) {  out.print("selected"); }%>>Actual/365</option>
                <option value="3" <%if (invTrade.getE01ORDATY().equals("3")) {  out.print("selected"); }%>>Actual/365(366 
                Año Bisiesto)</option>
                <option value="4" <%if (invTrade.getE01ORDATY().equals("4")) {  out.print("selected"); }%>>Actual/360</option>
                <option value="5" <%if (invTrade.getE01ORDATY().equals("5")) {  out.print("selected"); }%>>30/360</option>
                <option value="6" <%if (invTrade.getE01ORDATY().equals("6")) {  out.print("selected"); }%>>30E/360</option>
              </select>
            </td>
            <td nowrap width="14%" > 
              <div align="right">CUSIP :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" name="E01ORDCSP" size="16" maxlength="12" value="<%= invTrade.getE01ORDCSP()%>"readonly>
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
              <input type="text" name="E01ORDMIP" size="17" maxlength="15" value="<%= invTrade.getE01ORDMIP()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" > 
              <div align="right">Precio M&aacute;ximo de Compra :</div>
            </td>
            <td nowrap width="71%"> 
              <input type="text" name="E01ORDMXP" size="17" maxlength="15" value="<%= invTrade.getE01ORDMXP()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right"> Notas de la Orden (Trader):</div>
            </td>
            <td nowrap width="71%" > 
              <input type="text" name="E01ORDCO1" size="40" maxlength="35" value="<%= invTrade.getE01ORDCO1()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="71%"> 
              <div align="right"></div>
              <div align="left"> 
                <input type="text" name="E01ORDCO2" size="40" maxlength="35" value="<%= invTrade.getE01ORDCO2()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right"> </div>
            </td>
            <td nowrap width="71%"> 
              <input type="text" name="E01ORDCO3" size="40" maxlength="35" value="<%= invTrade.getE01ORDCO3()%>" readonly>
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
                <select name="E01ORDTRN">
                  <option value="1" <%if (invTrade.getE01ORDTRN().equals("1")) { out.print("selected"); }%>>Compra</option>
                  <option value="5" <%if (invTrade.getE01ORDTRN().equals("5")) {  out.print("selected"); }%>>Compra Múltiple</option>
                  <option value="2" <%if (invTrade.getE01ORDTRN().equals("2")) {  out.print("selected"); }%>>Venta</option>
                  <option value="6" <%if (invTrade.getE01ORDTRN().equals("6")) {  out.print("selected"); }%>>Venta Múltiple</option>
                  <option value="3" <%if (invTrade.getE01ORDTRN().equals("3")) {  out.print("selected"); }%>>Transferencia de Entrada</option>
                  <option value="4" <%if (invTrade.getE01ORDTRN().equals("4")) {  out.print("selected"); }%>>Transferencia de Salida</option>
                </select>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha de la Orden :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDIN1" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN1()%>">
                <input type="text" name="E01ORDIN2" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN2()%>">
                <input type="text" name="E01ORDIN3" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN3()%>">
                <a href="javascript:DatePicker(document.forms[0].E01ORDIN1,document.forms[0].E01ORDIN2,document.forms[0].E01ORDIN3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha Efectiva : </div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDST1" size="3" maxlength="2" value="<%= invTrade.getE01ORDST1()%>">
              <input type="text" name="E01ORDST2" size="3" maxlength="2" value="<%= invTrade.getE01ORDST2()%>">
              <input type="text" name="E01ORDST3" size="3" maxlength="2" value="<%= invTrade.getE01ORDST3()%>">
              <a href="javascript:DatePicker(document.forms[0].E01ORDST1,document.forms[0].E01ORDST2,document.forms[0].E01ORDST3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDVNO" size="17" maxlength="16" value="<%= invTrade.getE01ORDVNO()%>">
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Cantidad : </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDQTY" size="17" maxlength="16" value="<%= invTrade.getE01ORDQTY()%>">
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Precio del Banco:</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDPRB" size="17" maxlength="15" value="<%= invTrade.getE01ORDPRB()%>" onKeyPress="enterDecimal()">
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio del Cliente :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDPRC" size="17" maxlength="15" value="<%= invTrade.getE01ORDPRC()%>" onKeyPress="enterDecimal()">
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
              </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="17%" >
              <div align="right"><b>Monto de la Inversi&oacute;n :</b></div>
            </td>
            <td nowrap width="36%" >
              <input type="text" name="E01ORDIAM" size="17" maxlength="15" value="<%= invTrade.getE01ORDIAM()%>" readonly>
            </td>
            <td nowrap width="14%" >&nbsp;</td>
            <td nowrap width="33%" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Inter&eacute;s a Pagar :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" name="E01ORDIAL" size="17" maxlength="15" value="<%= invTrade.getE01ORDIAL()%>" onKeyPress="enterDecimal()">
                <input type="hidden" name="H01FLGWK3"  value="<%= invTrade.getH01FLGWK3()%>" >
                Recalcular : 
                <input type="radio" name="CH01FLGWK3 " value="Y" onClick="document.forms[0].H01FLGWK3.value='Y'"
			  <%if(invTrade.getH01FLGWK3().equals("Y")) out.print("checked");%>>
                Si 
                <input type="radio" name="CH01FLGWK3 " value="" onClick="document.forms[0].H01FLGWK3.value=''"
			  <%if(invTrade.getH01FLGWK3().equals("")) out.print("checked");%>>
                No </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right">D&iacute;as Acumulados :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
                <input type="text" name="E01ORDDYS" size="4" maxlength="3" value="<%= invTrade.getE01ORDDYS()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" ><b>Comisiones</b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Comisi&oacute;n de Operaci&oacute;n :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" name="E01ORDOCV" size="17" maxlength="15" value="<%= invTrade.getE01ORDOCV()%>" onKeyPress="enterDecimal()">
              <input type="hidden" name="H01FLGWK1"  value="<%= invTrade.getH01FLGWK1()%>" >
              Recalcular : 
              <input type="radio" name="CH01FLGWK1 " value="Y" onClick="document.forms[0].H01FLGWK1.value='Y'"
			  <%if(invTrade.getH01FLGWK1().equals("Y")) out.print("checked");%>>
              Si 
              <input type="radio" name="CH01FLGWK1 " value="" onClick="document.forms[0].H01FLGWK1.value=''"
			  <%if(invTrade.getH01FLGWK1().equals("")) out.print("checked");%>>
              No </td>
            <td nowrap width="14%" > 
              <div align="right">Tabla Comisi&oacute;n Operaci&oacute;n :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" name="E01ORDOCT" size="4" maxlength="2" value="<%= invTrade.getE01ORDOCT()%>">
              <a href="javascript:GetCommCustodyTable('E01ORDOCT',document.forms[0].E01ORDPTY.value,'','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Comisi&oacute;n de Rentabilidad :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDTGV" size="17" maxlength="15" value="<%= invTrade.getE01ORDTGV()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" > 
              <div align="left"><b>Totales</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Monto Transacci&oacute;n del Banco:</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDTBK" size="17" maxlength="15" value="<%= invTrade.getE01ORDTBK()%>" onKeyPress="enterDecimal()" readonly>
                <a href="javascript:Calculate()"> <img src="<%=request.getContextPath()%>/images/calculator.gif" alt="calculator" align="absmiddle" border="0" > 
                </a></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Monto Transacci&oacute;n del Cliente:</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDTVL" size="17" maxlength="15" value="<%= invTrade.getE01ORDTVL()%>" onKeyPress="enterDecimal()" readonly>
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
              <input type="text" name="E01FUTBAL" size="17" maxlength="15" value="<%= invTrade.getE01FUTBAL()%>" onKeyPress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Saldo en <%= invTrade.getE01ORDSCY()%> :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01CNVFUB" size="17" maxlength="15" value="<%= invTrade.getE01CNVFUB()%>" onKeyPress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Notas de la Orden :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDTC1" size="35" maxlength="30" value="<%= invTrade.getE01ORDTC1()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" >&nbsp;</td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDTC2" size="35" maxlength="30" value="<%= invTrade.getE01ORDTC2()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" >&nbsp;</td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDTC3" size="35" maxlength="30" value="<%= invTrade.getE01ORDTC3()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Broker / Informaci&oacute;n del Custodio</h4>
  <table  class="tableinfo" width="715">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right">Broker :</div>
            </td>
            <td nowrap width="80%" > 
              <div align="left"> 
                <input type="text" name="E01ORDBRK" size="5" maxlength="3" value="<%= invTrade.getE01ORDBRK()%>" >
                <input type="text" name="E01ORDBKN" size="35" maxlength="30" value="<%= invTrade.getE01ORDBKN()%>">
                <a href="javascript:GetBrokerI('E01ORDBRK','E01ORDBKN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
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
                <input type="text" name="E01ORDCUC" size="5" maxlength="3" value="<%= invTrade.getE01ORDCUC()%>" >
                <input type="text" name="E01ORDCCN" size="35" maxlength="30" value="<%= invTrade.getE01ORDCCN()%>">
                <a href="javascript:GetCustodian('E01ORDCUC','E01ORDCCN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF">
      <td width="38%">&nbsp;</td>
      <td width="21%"> 
        <div align="left">
          <input type="checkbox" name="E01ORDDLT" value="Y" <% if(invTrade.getE01ORDDLT().equals("Y")){ out.print("checked");} %>>
          Marcar para Borrar</div>
      </td>
      <td width="41%">&nbsp;</td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td width="38%"> 
        <div align="right"> </div>
        <div align="center"> </div>
      </td>
      <td width="21%"> 
        <div align="left">
          <input type="checkbox" name="H01FLGWK2" value="A" <% if(invTrade.getH01FLGWK2().equals("A")){ out.print("checked");} %>>
          Aceptar con Errores</div>
      </td>
      <td width="41%"> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td colspan="3"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td colspan="3"> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
 
  </form>
</body>
</html>
