<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Management Portfolio System</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invMult" class="datapro.eibs.beans.EIE013002Message"  scope="session" />

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
  <h3>Aprobaci&oacute;n - M&uacute;tiples Ordenes de Compra y Venta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_trade_tickets_mp.jsp,EIE0130A"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0130A" >
  <h4> 
    <h4> 
      <input type="hidden" name="SCREEN"  value="10" >
      Informaci&oacute;n Orden Maestra </h4>
    <table  class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="17%" > 
                <div align="right">N&uacute;mero de Orden :</div>
              </td>
              <td nowrap width="36%" > 
                <input type="text" readonly      name="E02ORDMNU" size="9" maxlength="5" value="<%= invMult.getE02ORDMNU()%>" >
              </td>
              <td nowrap width="14%" > 
                <div align="right"><a href="javascript:showCustomerInq(document.forms[0].E02ORDCUN.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                  Cliente </a>:</div>
              </td>
              <td nowrap width="33%" > 
                <input type="text" readonly      name="E02ORDMCU" size="9" maxlength="9" value="<%= invMult.getE02ORDMCU().trim()%>">
                <input type="text" readonly      name="E02ORDMCN" size="35" maxlength="35" value="<%= invMult.getE02ORDMCN().trim()%>" >
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="17%" > 
                <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].E02ORDMIC.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                  Instrumento </a> </div>
              </td>
              <td nowrap width="36%" > 
                <input type="text" readonly      name="E02ORDMIC" size="9" maxlength="9" value="<%= invMult.getE02ORDMIC()%>" >
                <input type="text" readonly      name="E02ORDICN" size="35" maxlength="30" value="<%= invMult.getE02ORDICN()%>">
              </td>
              <td nowrap width="14%" > 
                <div align="right">Moneda del Instrumento :</div>
              </td>
              <td nowrap width="33%" > 
                <input type="text" readonly      name="E02ORDMCY" size="4" maxlength="3" value="<%= invMult.getE02ORDMCY()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="17%" > 
                <div align="right">Tipo de Producto :</div>
              </td>
              <td nowrap width="36%" > 
                <input type="hidden" name="E02ORDPTY"  value="<%= invMult.getE02ORDPTY()%>" >
                <% if(invMult.getE02ORDPTY().equals("BND")) out.print("Bonds");
              				else if(invMult.getE02ORDPTY().equals("EQT")) out.print("Equity");
							else if(invMult.getE02ORDPTY().equals("MUT")) out.print("Mutual Funds");
							else if(invMult.getE02ORDPTY().equals("PFS")) out.print("Preferred Stocks");
							else out.print("");%>
              </td>
              <td nowrap width="14%" > 
                <div align="right"></div>
              </td>
              <td nowrap width="33%" >&nbsp; </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="17%" > 
                <div align="right">Fecha de la Orden :</div>
              </td>
              <td nowrap width="36%" > 
                <input type="text" readonly      name="E02ORDIN1" size="3" maxlength="2" value="<%= invMult.getE02ORDIN1()%>">
                <input type="text" readonly      name="E02ORDIN2" size="3" maxlength="2" value="<%= invMult.getE02ORDIN2()%>">
                <input type="text" readonly      name="E02ORDIN3" size="3" maxlength="2" value="<%= invMult.getE02ORDIN3()%>">
              </td>
              <td nowrap width="14%" > 
                <div align="right">Fecha Efectiva :</div>
              </td>
              <td nowrap width="33%" > 
                <input type="text" readonly      name="E02ORDST1" size="3" maxlength="2" value="<%= invMult.getE02ORDST1()%>">
                <input type="text" readonly      name="E02ORDST2" size="3" maxlength="2" value="<%= invMult.getE02ORDST2()%>">
                <input type="text" readonly      name="E02ORDST3" size="3" maxlength="2" value="<%= invMult.getE02ORDST3()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="17%" > 
                <div align="right"><b>Saldo Disponible :</b></div>
              </td>
              <td nowrap width="36%" > 
                <input type="text" readonly      name="E02ORDMTO" size="17" maxlength="15" value="<%= invMult.getE02ORDMTO()%>" onKeyPress="enterDecimal()">
              </td>
              <td nowrap width="14%" > 
                <div align="right"><b>Total de la Orden :</b></div>
              </td>
              <td nowrap width="33%" > 
                <input type="text" readonly      name="E02ORDMBA" size="17" maxlength="16" value="<%= invMult.getE02ORDMBA()%>"
				onKeyPress="enterDecimal()" >
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Informaci&oacute;n B&aacute;sica </h4>
    <table  class="tableinfo" width="715">
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="17%" > 
                <div align="right">N&uacute;mero de Orden Dependiente :</div>
              </td>
              <td nowrap width="36%" > 
                <div align="left"> 
                  <input type="text" readonly      name="E02ORDNUM" size="9" maxlength="9" value="<%= invMult.getE02ORDNUM()%>" >
                </div>
              </td>
              <td nowrap width="14%" > 
                <div align="right">Cliente :</div>
              </td>
              <td nowrap width="33%" > 
                <div align="left"> 
                  <input type="text" readonly      name="E02ORDCUN" size="11" maxlength="9" value="<%= invMult.getE02ORDCUN()%>" >
                  <input type="text" readonly      name="E02ORDCTN" size="35" maxlength="30" value="<%= invMult.getE02ORDCTN()%>">
                </div>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="17%" > 
                <div align="right"><a href="javascript:showRetAccountInq(document.forms[0].E02ORDHAC.value)">Portafolio 
                  : </a></div>
              </td>
              <td nowrap width="36%" > 
                <div align="left"> 
                  <input type="text" readonly      name="E02ORDPRF" size="12" maxlength="9" value="<%= invMult.getE02ORDPRF()%>">
                </div>
              </td>
              <td nowrap width="14%" > 
                <div align="right"><a href="javascript:showRetAccountInq(document.forms[0].E02ORDHAC.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                  Cuenta Transaccional :</a></div>
              </td>
              <td nowrap width="33%" > 
                <div align="left"> 
                  <input type="text" readonly      name="E02ORDHCY" size="3" maxlength="3" value="<%= invMult.getE02ORDHCY()%>"
                >
                  <input type="text" readonly      name="E02ORDHAC" size="12" maxlength="9" value="<%= invMult.getE02ORDHAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,'','','','','RT')" >
                </div>
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="17%" > 
                <div align="right">Saldo :</div>
              </td>
              <td nowrap width="36%" > 
                <div align="left"> 
                  <input type="text" readonly      name="E02MEMBAL" size="17" maxlength="16" value="<%= invMult.getE02MEMBAL()%>" onKeyPress="enterDecimal()">
                </div>
              </td>
              <td nowrap width="14%" > 
                <div align="right">Tasa de Cambio </div>
              </td>
              <td nowrap width="33%" > 
                <div align="left"> 
                  <input type="text" readonly      name="E02ORDEXR" size="12" maxlength="9" value="<%= invMult.getE02ORDEXR()%>">
                </div>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="17%" > 
                <div align="right">Saldo en :</div>
              </td>
              <td nowrap width="36%" > 
                <div align="left"> 
                  <input type="text" readonly      name="E02CNVBAL" size="17" maxlength="16" value="<%= invMult.getE02CNVBAL()%>" onKeyPress="enterDecimal()">
                </div>
              </td>
              <td nowrap width="14%" > 
                <div align="right"></div>
              </td>
              <td nowrap width="33%" > 
                <div align="left"></div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Informaci&oacute;n de la Transacci&oacute;n </h4>
  </h4>
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
                <select   name="E02ORDTRN" disabled>
                  <option value="1" <%if (invMult.getE02ORDTRN().equals("1")) { out.print("selected"); }%>>Compra</option>
                  <option value="5" <%if (invMult.getE02ORDTRN().equals("5")) {  out.print("selected"); }%>>Compra Múltiple</option>
                  <option value="2" <%if (invMult.getE02ORDTRN().equals("2")) {  out.print("selected"); }%>>Venta</option>
                  <option value="6" <%if (invMult.getE02ORDTRN().equals("6")) {  out.print("selected"); }%>>Venta Múltiple</option>
                  <option value="3" <%if (invMult.getE02ORDTRN().equals("3")) {  out.print("selected"); }%>>Transferencia de Entrada</option>
                  <option value="4" <%if (invMult.getE02ORDTRN().equals("4")) {  out.print("selected"); }%>>Transferencia de Salida</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E02ORDVNO" size="17" maxlength="16" value="<%= invMult.getE02ORDVNO()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right">V&iacute;a Confirmaci&oacute;n de Orden :</div>
            </td>
            <td nowrap > 
              <select disabled  name="E02ORDCNF">
                <option value="N" <%if (invMult.getE02ORDCNF().equals("N")) { out.print("selected"); }%>>Ninguna</option>
                <option value="E" <%if (invMult.getE02ORDCNF().equals("E")) { out.print("selected"); }%>>e-Mail</option>
                <option value="F" <%if (invMult.getE02ORDCNF().equals("F")) { out.print("selected"); }%>>Fax</option>
                <option value="P" <%if (invMult.getE02ORDCNF().equals("P")) { out.print("selected"); }%>>Impresora</option>
              </select>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Cantidad : </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E02ORDQTY" size="17" maxlength="16" value="<%= invMult.getE02ORDQTY()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Precio del Banco :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E02ORDPRB" size="17" maxlength="15" value="<%= invMult.getE02ORDPRB()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio del Cliente :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E02ORDPRC" size="17" maxlength="15" value="<%= invMult.getE02ORDPRC()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><b>Monto de la Inversi&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E02ORDIAM" size="17" maxlength="15" value="<%= invMult.getE02ORDIAM()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Intereses por Pagar :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E02ORDIAL" size="17" maxlength="15" value="<%= invMult.getE02ORDIAL()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right">D&iacute;as Acumulados :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E02ORDDYS" size="4" maxlength="3" value="<%= invMult.getE02ORDDYS()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="right"></div>
              <div align="left"> </div>
              <div align="right"></div>
              <div align="left"><b>Comisiones </b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Comisi&oacute;n de Operaci&oacute;n : </div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E02ORDOCV" size="17" maxlength="15" value="<%= invMult.getE02ORDOCV()%>" onKeyPress="enterDecimal()">
            </td>
            <td nowrap > 
              <div align="right"> Tabla de Comisi&oacute;n :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E02ORDOCT" size="4" maxlength="2" value="<%= invMult.getE02ORDOCT()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Comisi&oacute;n de Rentabilidad :</div>
            </td>
            <td nowrap width="72%" > 
              <div align="left"> 
                <input type="text" readonly  name="E02ORDTGV" size="17" maxlength="15" value="<%= invMult.getE02ORDTGV()%>" >
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
                <input type="text" readonly  name="E02ORDTBK" size="17" maxlength="15" value="<%= invMult.getE02ORDTBK()%>" onKeyPress="enterDecimal()" >
                <a href="javascript:Calculate()"> </a> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Monto Transacci&oacute;n del Cliente :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E02ORDTVL" size="17" maxlength="15" value="<%= invMult.getE02ORDTVL()%>" onKeyPress="enterDecimal()" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="left"><b>Saldo en Fecha Efectiva</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"> <a href="javascript:showInvHolds(document.forms[0].E02ORDCUN.value,document.forms[0].E02ORDHAC.value,document.forms[0].E02ORDST1.value
												,document.forms[0].E02ORDST2.value,document.forms[0].E02ORDST3.value)"> 
                <img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Saldo en <%= invMult.getE02ORDHCY()%> : </a> </div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E02FUTBAL" size="17" maxlength="15" value="<%= invMult.getE02FUTBAL()%>" onKeyPress="enterDecimal()" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Saldo en <%= invMult.getE02ORDSCY()%> :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E02CNVFUB" size="17" maxlength="15" value="<%= invMult.getE02CNVFUB()%>" onKeyPress="enterDecimal()" >
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
            <td nowrap > 
              <div align="center">Centro de<br>
                Costo</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cuenta Contrapartida :</div>
            </td>
            <td nowrap > 
              <div align="center">Cuentas Corrientes</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDHBK" size="2" maxlength="2" value="<%= invMult.getE02ORDHBK()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDHBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02ORDHBK.value,'','','','')" value="<%= invMult.getE02ORDHBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDHCY4" size="3" maxlength="3" value="<%= invMult.getE02ORDHCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02ORDHBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDHGL" size="15" maxlength="13" value="<%= invMult.getE02ORDHGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02ORDHBK.value,document.forms[0].E02ORDHCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDHAC4" size="12" maxlength="9" value="<%= invMult.getE02ORDHAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E02ORDHBK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDHCC" size="12" maxlength="9" value="<%= invMult.getE02ORDHCC()%>"
				oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E02ORDHBK.value,'','','','')">
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
              <div align="right">Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <select disabled  name="E02ORDIPT">
                  <option value="1" <%if (invMult.getE02ORDIPT().equals("1")) { out.print("selected"); }%>>Cuenta Corriente</option>
                  <option value="2" <%if (invMult.getE02ORDIPT().equals("2")) {  out.print("selected"); }%>>Cheques de Gerencia</option>
                  <option value="3" <%if (invMult.getE02ORDIPT().equals("3")) { out.print("selected"); }%>>Cuenta Contable</option>
                  <option value="4" <%if (invMult.getE02ORDIPT().equals("4")) {  out.print("selected"); }%>>Transferencia Fed</option>
                  <option value="5" <%if (invMult.getE02ORDIPT().equals("5")) { out.print("selected"); }%>>Transferencia Telex</option>
                  <option value="6" <%if (invMult.getE02ORDIPT().equals("6")) {  out.print("selected"); }%>>Swift Formato MT-100</option>
                  <option value="7" <%if (invMult.getE02ORDIPT().equals("7")) { out.print("selected"); }%>>Swift Formato MT-200</option>
                  <option value="8" <%if (invMult.getE02ORDIPT().equals("8")) {  out.print("selected"); }%>>Swift Formato MT-202</option>
                </select>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDIBK" size="2" maxlength="2" value="<%= invMult.getE02ORDIBK()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDIBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02ORDIBK.value,'','','','')" value="<%= invMult.getE02ORDIBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDICY" size="3" maxlength="3" value="<%= invMult.getE02ORDICY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02ORDIBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDIGL" size="15" maxlength="13" value="<%= invMult.getE02ORDIGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02ORDIBK.value,document.forms[0].E02ORDICY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDIAC" size="12" maxlength="9" value="<%= invMult.getE02ORDIAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E02ORDIBK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDICC" size="12" maxlength="9" value="<%= invMult.getE02ORDICC()%>"
				oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E02ORDIBK.value,'','','','')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Capital :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <select disabled  name="E02ORDCPT">
                  <option value="1" <%if (invMult.getE02ORDCPT().equals("1")) { out.print("selected"); }%>>Cuenta Corriente</option>
                  <option value="2" <%if (invMult.getE02ORDCPT().equals("2")) {  out.print("selected"); }%>>Cheques de Gerencia</option>
                  <option value="3" <%if (invMult.getE02ORDCPT().equals("3")) { out.print("selected"); }%>>Cuenta Contable</option>
                  <option value="4" <%if (invMult.getE02ORDCPT().equals("4")) {  out.print("selected"); }%>>Transferencia Fed</option>
                  <option value="5" <%if (invMult.getE02ORDCPT().equals("5")) { out.print("selected"); }%>>Transferencia Telex</option>
                  <option value="6" <%if (invMult.getE02ORDCPT().equals("6")) {  out.print("selected"); }%>>Swift Formato MT-100</option>
                  <option value="7" <%if (invMult.getE02ORDCPT().equals("7")) { out.print("selected"); }%>>Swift Formato MT-200</option>
                  <option value="8" <%if (invMult.getE02ORDCPT().equals("8")) {  out.print("selected"); }%>>Swift Formato MT-202</option>
                </select>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDCBK" size="2" maxlength="2" value="<%= invMult.getE02ORDCBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDCBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02ORDCBK.value,'','','','')" value="<%= invMult.getE02ORDCBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDCCY" size="3" maxlength="3" value="<%= invMult.getE02ORDCCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02ORDCBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDCGL" size="15" maxlength="13" value="<%= invMult.getE02ORDCGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02ORDCBK.value,document.forms[0].E02ORDCCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDCAC" size="12" maxlength="9" value="<%= invMult.getE02ORDCAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E02ORDCBK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDCCC" size="12" maxlength="9" value="<%= invMult.getE02ORDCCC()%>"
				oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E02ORDCBK.value,'','','','')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Dividendos :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <select disabled  name="E02ORDDPT">
                  <option value="1" <%if (invMult.getE02ORDDPT().equals("1")) { out.print("selected"); }%>>Cuenta Corriente</option>
                  <option value="2" <%if (invMult.getE02ORDDPT().equals("2")) {  out.print("selected"); }%>>Cheques de Gerencia</option>
                  <option value="3" <%if (invMult.getE02ORDDPT().equals("3")) { out.print("selected"); }%>>Cuenta Contable</option>
                  <option value="4" <%if (invMult.getE02ORDDPT().equals("4")) {  out.print("selected"); }%>>Transferencia Fed</option>
                  <option value="5" <%if (invMult.getE02ORDDPT().equals("5")) { out.print("selected"); }%>>Transferencia Telex</option>
                  <option value="6" <%if (invMult.getE02ORDDPT().equals("6")) {  out.print("selected"); }%>>Swift Formato MT-100</option>
                  <option value="7" <%if (invMult.getE02ORDDPT().equals("7")) { out.print("selected"); }%>>Swift Formato MT-200</option>
                  <option value="8" <%if (invMult.getE02ORDDPT().equals("8")) {  out.print("selected"); }%>>Swift Formato MT-202</option>
                </select>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDDBK" size="2" maxlength="2" value="<%= invMult.getE02ORDDBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDDBR" size="3" maxlength="3" value="<%= invMult.getE02ORDDBR()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02ORDDBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDDCY" size="3" maxlength="3" value="<%= invMult.getE02ORDDCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02ORDDBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDDGL" size="15" maxlength="13" value="<%= invMult.getE02ORDDGL()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02ORDDBK.value,document.forms[0].E02ORDDCY.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDDAC" size="12" maxlength="9" value="<%= invMult.getE02ORDDAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E02ORDDBK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E02ORDDCC" size="12" maxlength="9" value="<%= invMult.getE02ORDDCC()%>"
				oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E02ORDDBK.value,'','','','')">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Aditional </h4>
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
                <input type="text" readonly    name="E02ORDMXC" size="11" maxlength="11" value="<%= invMult.getE02ORDMXC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Notas de la Orden :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly    name="E02ORDTC1" size="35" maxlength="30" value="<%= invMult.getE02ORDTC1()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly    name="E02ORDTC2" size="35" maxlength="30" value="<%= invMult.getE02ORDTC2()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly    name="E02ORDTC3" size="35" maxlength="30" value="<%= invMult.getE02ORDTC3()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Notas de la Orden (Back Office) :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly    name="E02ORDBC1" size="35" maxlength="30" value="<%= invMult.getE02ORDBC1()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly    name="E02ORDBC2" size="35" maxlength="30" value="<%= invMult.getE02ORDBC2()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly    name="E02ORDBC3" size="35" maxlength="30" value="<%= invMult.getE02ORDBC3()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
   
  <h4><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
      <tr bgcolor="#FFFFFF"> 
        <td width="33%"> 
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
        </td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td> 
          <div align="center"> </div>
        </td>
      </tr>
    </table>
  </h4>
  </form>
</body>
</html>
