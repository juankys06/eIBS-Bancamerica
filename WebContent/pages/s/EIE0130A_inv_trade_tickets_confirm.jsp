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
<SCRIPT Language = "javascript">
function doPrint(){
	if(!window.print){
       var msg ="This browser doesn't allow print";
	   alert(msg);
	   return;}
	
    window.focus();
	window.print();

	return;
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
  <h3>Orden de Compra - Venta</h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0309" >
  <table class="tableinfo">
    <tr> 
      <td> 
        <div align="center">La operaci&oacute;n a finalizado satisfactoriamente</div>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica
    <input type="hidden" name="SCREEN"  value="500" >
  </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">N&uacute;mero de Orden :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDNUM" size="9" maxlength="5" value="<%= invTrade.getE01ORDNUM()%>" >
            </td>
            <td nowrap width="14%" > 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDCUN" size="9" maxlength="9" value="<%= invTrade.getE01ORDCUN().trim()%>">
              <input type="text" readonly  name="E01ORDCTN" size="35" maxlength="35" value="<%= invTrade.getE01ORDCTN().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Portafolio :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDPRF" size="9" maxlength="9" value="<%= invTrade.getE01ORDPRF()%>" >
            </td>
            <td nowrap width="14%" > 
              <div align="right">Cuenta Transaccional :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDHCY2" size="3" maxlength="3" value="<%= invTrade.getE01ORDHCY()%>"
                 >
              <input type="text" readonly  name="E01ORDHAC3" size="12" maxlength="9" value="<%= invTrade.getE01ORDHAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01ORDHBK.value,'','','','RT')" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Saldo :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01MEMBAL" size="17" maxlength="16" value="<%= invTrade.getE01MEMBAL()%>"
				onKeyPress="enterDecimal()" >
            </td>
            <td nowrap width="14%" > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDEXR" size="11" maxlength="11" value="<%= invTrade.getE01ORDEXR()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Saldo en <%= invTrade.getE01ORDSCY()%> :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01CNVBAL" size="17" maxlength="16" value="<%= invTrade.getE01CNVBAL()%>"
				onKeyPress="enterDecimal()" >
            </td>
            <td nowrap width="14%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" >&nbsp;</td>
          </tr>
          <tr id="trdark">
            <td nowrap width="17%" >
              <div align="right">C&oacute;digo de Producto : </div>
            </td>
            <td nowrap width="36%" >
              <div align="left">
                <input type="text" readonly  name="E01ORDCDE" size="5" maxlength="3" value="<%= invTrade.getE01ORDCDE()%>" >
                <input type="text" readonly  name="E01ORDPNM" size="35" maxlength="30" value="<%= invTrade.getE01ORDPNM()%>">
              </div>
            </td>
            <td nowrap width="14%" >
              <div align="right">Cuenta del Producto :</div>
            </td>
            <td nowrap width="33%" >
              <div align="left">
                <input type="text" readonly  name="E01ORDPBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDPBK()%>">
                <input type="text" readonly  name="E01ORDPBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01ORDPBK.value,'','','','')" value="<%= invTrade.getE01ORDPBR()%>">
                <input type="text" readonly  name="E01ORDPCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDPCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01ORDPBK.value,'','','','')">
                <input type="text" readonly  name="E01ORDPGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDPGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01ORDPBK.value,document.forms[0].E01ORDPCY.value,'','','')" >
                <input type="text" readonly  name="E01ORDHAC2" size="12" maxlength="9" value="<%= invTrade.getE01ORDHAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01ORDPBK.value,'','','','RT')" >
              </div>
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
              <div align="right">Instrumento :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDIIC" size="9" maxlength="9" value="<%= invTrade.getE01ORDIIC()%>" >
              <input type="text" readonly  name="E01ORDICN" size="35" maxlength="30" value="<%= invTrade.getE01ORDICN()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Tipo de Producto :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="hidden" name="E01ORDPTY"  value="<%= invTrade.getE01ORDPTY()%>" >
              <% if(invTrade.getE01ORDPTY().equals("BND")) out.print("Bonds");
              				else if(invTrade.getE01ORDPTY().equals("EQT")) out.print("Equity");
							else if(invTrade.getE01ORDPTY().equals("MUT")) out.print("Mutual Funds");
							else if(invTrade.getE01ORDPTY().equals("PFS")) out.print("Preferred Stocks");
							else out.print("");%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Moneda del Instrumento :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDSCY" size="4" maxlength="3" value="<%= invTrade.getE01ORDSCY()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">S&iacute;mbolo :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDSYM" size="17" maxlength="15" value="<%= invTrade.getE01ORDSYM()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha Ultimo Pago de Intereses:</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDLP1" size="3" maxlength="2" value="<%= invTrade.getE01ORDLP1()%>">
              <input type="text" readonly  name="E01ORDLP2" size="3" maxlength="2" value="<%= invTrade.getE01ORDLP2()%>">
              <input type="text" readonly  name="E01ORDLP3" size="3" maxlength="2" value="<%= invTrade.getE01ORDLP3()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">ISIN :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDISI" size="14" maxlength="12" value="<%= invTrade.getE01ORDISI()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Acumulaci&oacute;n :</div>
            </td>
            <td nowrap width="36%" > 
              <select disabled  name="E01ORDATY">
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
              <input type="text" readonly  name="E01ORDCSP" size="14" maxlength="12" value="<%= invTrade.getE01ORDCSP()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">D&iacute;as Acumulados :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDDYS" size="4" maxlength="3" value="<%= invTrade.getE01ORDDYS()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" >&nbsp;</td>
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
              <div align="right">Fecha de la Orden :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDIN1" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN1()%>">
                <input type="text" readonly  name="E01ORDIN2" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN2()%>">
                <input type="text" readonly  name="E01ORDIN3" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN3()%>">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right">Fecha Efectiva :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDST1" size="3" maxlength="2" value="<%= invTrade.getE01ORDST1()%>">
                <input type="text" readonly  name="E01ORDST2" size="3" maxlength="2" value="<%= invTrade.getE01ORDST2()%>">
                <input type="text" readonly  name="E01ORDST3" size="3" maxlength="2" value="<%= invTrade.getE01ORDST3()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Transacci&oacute;n :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <select disabled  name="E01ORDTRN">
                  <option value="1" <%if (invTrade.getE01ORDTRN().equals("1")) { out.print("selected"); }%>>Compra</option>
                  <option value="2" <%if (invTrade.getE01ORDTRN().equals("2")) {  out.print("selected"); }%>>Venta</option>
                  <option value="3" <%if (invTrade.getE01ORDTRN().equals("3")) {  out.print("selected"); }%>>Transferencia de Entrada</option>
                  <option value="4" <%if (invTrade.getE01ORDTRN().equals("4")) {  out.print("selected"); }%>>Transferencia de Salida</option>
                  <option value="5" <%if (invTrade.getE01ORDTRN().equals("5")) {  out.print("selected"); }%>>Compra Múltiple</option>
                  <option value="6" <%if (invTrade.getE01ORDTRN().equals("6")) {  out.print("selected"); }%>>Venta Múltiple</option>
                  <option value="7" <%if (invTrade.getE01ORDTRN().equals("7")) {  out.print("selected"); }%>>Ajuste(Disminución)</option>
                </select>
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
              <div align="right"></div>
              <div align="left"> </div>
              <div align="right"></div>
              <div align="left"> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> Valor Nominal :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDVNO" size="17" maxlength="16" value="<%= invTrade.getE01ORDVNO()%>">
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
            <td nowrap width="17%" > 
              <div align="right">Cantidad :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left">
                <input type="text" readonly  name="E01ORDQTY" size="17" maxlength="16" value="<%= invTrade.getE01ORDQTY()%>">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Precio del Banco :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDPRB" size="17" maxlength="15" value="<%= invTrade.getE01ORDPRB()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio del Cliente :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDPRC" size="17" maxlength="15" value="<%= invTrade.getE01ORDPRC()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Comisi&oacute;n de Rentabilidad :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDTGV" size="17" maxlength="15" value="<%= invTrade.getE01ORDTGV()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right">Tabla de Comisi&oacute;n :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDTBG" size="4" maxlength="2" value="<%= invTrade.getE01ORDTBG()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Otras Comisiones :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDOCV" size="17" maxlength="15" value="<%= invTrade.getE01ORDOCV()%>" onKeyPress="enterDecimal()">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Tabla de Comisi&oacute;n :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDOCT" size="4" maxlength="2" value="<%= invTrade.getE01ORDOCT()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><b>Sub Total :</b></div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDIAM" size="17" maxlength="15" value="<%= invTrade.getE01ORDIAM()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Intereses por Pagar :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDIAL" size="17" maxlength="15" value="<%= invTrade.getE01ORDIAL()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><b>Total de la Orden :</b></div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDTVL" size="17" maxlength="15" value="<%= invTrade.getE01ORDTVL()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> </div>
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
                <input type="text" readonly  name="E01ORDBRK" size="5" maxlength="3" value="<%= invTrade.getE01ORDBRK()%>" >
                <input type="text" readonly  name="E01ORDBKN" size="35" maxlength="30" value="<%= invTrade.getE01ORDBKN()%>">
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
                <input type="text" readonly  name="E01ORDCUC" size="5" maxlength="3" value="<%= invTrade.getE01ORDCUC()%>" >
                <input type="text" readonly  name="E01ORDCCN" size="35" maxlength="30" value="<%= invTrade.getE01ORDCCN()%>">
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
              <div align="center">Tipo de<br>
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
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cuenta Contrapartida :</div>
            </td>
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDHBK()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01ORDHBK.value,'','','','')" value="<%= invTrade.getE01ORDHBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDHCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01ORDHBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDHGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01ORDHBK.value,document.forms[0].E01ORDHCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDHAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDHAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01ORDHBK.value,'','','','RT')" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="7" > 
              <div align="left"><b>Cuenta de Repago</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <select disabled  name="E01ORDIPT">
                  <option value="1" <%if (invTrade.getE01ORDIPT().equals("1")) { out.print("selected"); }%>>Cuenta Corriente</option>
                  <option value="2" <%if (invTrade.getE01ORDIPT().equals("2")) {  out.print("selected"); }%>>Cheques de Gerencia</option>
				  <option value="3" <%if (invTrade.getE01ORDIPT().equals("3")) { out.print("selected"); }%>>Cuenta Contable</option>
                  <option value="4" <%if (invTrade.getE01ORDIPT().equals("4")) {  out.print("selected"); }%>>Transferencia Fed</option>
				  <option value="5" <%if (invTrade.getE01ORDIPT().equals("5")) { out.print("selected"); }%>>Transferencia Telex</option>
                  <option value="6" <%if (invTrade.getE01ORDIPT().equals("6")) {  out.print("selected"); }%>>Swift Formato MT-100</option>
				  <option value="7" <%if (invTrade.getE01ORDIPT().equals("7")) { out.print("selected"); }%>>Swift Formato MT-200</option>
                  <option value="8" <%if (invTrade.getE01ORDIPT().equals("8")) {  out.print("selected"); }%>>Swift Formato MT-202</option>
                </select>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDIBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDIBK()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDIBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01ORDIBK.value,'','','','')" value="<%= invTrade.getE01ORDIBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDICY" size="3" maxlength="3" value="<%= invTrade.getE01ORDICY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01ORDIBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDIGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDIGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01ORDIBK.value,document.forms[0].E01ORDICY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDIAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDIAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01ORDIBK.value,'','','','RT')" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Capital :</div>
            </td>
            <td nowrap > 
              <div align="center">
				<select disabled  name="E01ORDCPT">
                  <option value="1" <%if (invTrade.getE01ORDCPT().equals("1")) { out.print("selected"); }%>>Cuenta Corriente</option>
                  <option value="2" <%if (invTrade.getE01ORDCPT().equals("2")) {  out.print("selected"); }%>>Cheques de Gerencia</option>
				  <option value="3" <%if (invTrade.getE01ORDCPT().equals("3")) { out.print("selected"); }%>>Cuenta Contable</option>
                  <option value="4" <%if (invTrade.getE01ORDCPT().equals("4")) {  out.print("selected"); }%>>Transferencia Fed</option>
				  <option value="5" <%if (invTrade.getE01ORDCPT().equals("5")) { out.print("selected"); }%>>Transferencia Telex</option>
                  <option value="6" <%if (invTrade.getE01ORDCPT().equals("6")) {  out.print("selected"); }%>>Swift Formato MT-100</option>
				  <option value="7" <%if (invTrade.getE01ORDCPT().equals("7")) { out.print("selected"); }%>>Swift Formato MT-200</option>
                  <option value="8" <%if (invTrade.getE01ORDCPT().equals("8")) {  out.print("selected"); }%>>Swift Formato MT-202</option>
                </select></div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDCBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01ORDCBK.value,'','','','')" value="<%= invTrade.getE01ORDCBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDCCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01ORDCBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDCGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01ORDCBK.value,document.forms[0].E01ORDCCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDCAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDCAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01ORDCBK.value,'','','','RT')" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Dividendos :</div>
            </td>
            <td nowrap > 
              <div align="center">
             <select disabled  name="E01ORDDPT">
                  <option value="1" <%if (invTrade.getE01ORDDPT().equals("1")) { out.print("selected"); }%>>Cuenta Corriente</option>
                  <option value="2" <%if (invTrade.getE01ORDDPT().equals("2")) {  out.print("selected"); }%>>Cheques de Gerencia</option>
				  <option value="3" <%if (invTrade.getE01ORDDPT().equals("3")) { out.print("selected"); }%>>Cuenta Contable</option>
                  <option value="4" <%if (invTrade.getE01ORDDPT().equals("4")) {  out.print("selected"); }%>>Transferencia Fed</option>
				  <option value="5" <%if (invTrade.getE01ORDDPT().equals("5")) { out.print("selected"); }%>>Transferencia Telex</option>
                  <option value="6" <%if (invTrade.getE01ORDDPT().equals("6")) {  out.print("selected"); }%>>Swift Formato MT-100</option>
				  <option value="7" <%if (invTrade.getE01ORDDPT().equals("7")) { out.print("selected"); }%>>Swift Formato MT-200</option>
                  <option value="8" <%if (invTrade.getE01ORDDPT().equals("8")) {  out.print("selected"); }%>>Swift Formato MT-202</option>
                </select>
			  </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDBK" size="2" maxlength="2" value="<%= invTrade.getE01ORDDBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDBR" size="3" maxlength="3" value="<%= invTrade.getE01ORDDBR()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01ORDDBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDDCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01ORDDBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDGL" size="15" maxlength="13" value="<%= invTrade.getE01ORDDGL()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01ORDDBK.value,document.forms[0].E01ORDDCY.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01ORDDAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDDAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01ORDDBK.value,'','','','RT')" >
              </div>
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
                <input type="text" readonly  name="E01ORDMXC" size="11" maxlength="11" value="<%= invTrade.getE01ORDMXC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Notas de la Orden :</div>
            </td>
            <td nowrap colspan="3" >
              <input type="text" readonly  name="E01ORDTC1" size="35" maxlength="30" value="<%= invTrade.getE01ORDTC1()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" >
              <input type="text" readonly  name="E01ORDTC2" size="35" maxlength="30" value="<%= invTrade.getE01ORDTC2()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" >
              <input type="text" readonly  name="E01ORDTC3" size="35" maxlength="30" value="<%= invTrade.getE01ORDTC3()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Notas de la Orden (Back Office) :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDBC1" size="35" maxlength="30" value="<%= invTrade.getE01ORDBC1()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDBC2" size="35" maxlength="30" value="<%= invTrade.getE01ORDBC2()%>">
            </td>
          </tr>
          <tr id="trclear"> 
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
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%">&nbsp;</td>
      <td width="33%">&nbsp;</td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=button name="Submit" value="Imprimir" onClick="doPrint()">
        </div>
      </td>
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit2" value="Continuar">
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form>
</body>
</html>
