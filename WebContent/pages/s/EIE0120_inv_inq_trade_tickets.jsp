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
  <h3>Consulta Orden de Compra - Venta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_inq_trade_tickets.jsp,EIE0120"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0120" >
  <h4> Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo" width="715">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"> N&uacute;mero de Orden :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDNUM" size="9" maxlength="5" value="<%= invTrade.getE01ORDNUM()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showCustomerInq(document.forms[0].E01ORDCUN.value)"><img src="<%=request.getContextPath()%>/images/1aquire.gif" alt="help" align="absbottom" border="0" > 
                Cliente </a>:</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDCUN" size="9" maxlength="9" value="<%= invTrade.getE01ORDCUN().trim()%>">
                <input type="text" readonly  name="E01ORDCTN" size="35" maxlength="35" value="<%= invTrade.getE01ORDCTN().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Portafolio :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDPRF" size="9" maxlength="9" value="<%= invTrade.getE01ORDPRF()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showRetAccountInq(document.forms[0].E01ORDHAC.value)"><img src="<%=request.getContextPath()%>/images/1aquire.gif" alt="help" align="absbottom" border="0" > 
                Cuenta Transaccional :</a></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDHCY" size="3" maxlength="3" value="<%= invTrade.getE01ORDHCY()%>"
                >
              <input type="text" readonly  name="E01ORDHAC" size="12" maxlength="9" value="<%= invTrade.getE01ORDHAC()%>"
                >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Saldo Disponible :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01MEMBAL" size="17" maxlength="16" value="<%= invTrade.getE01MEMBAL()%>"
				onKeyPress="enterDecimal()" >
            </td>
            <td nowrap width="14%" > 
              <div align="right">Saldo en <%= invTrade.getE01ORDSCY()%> :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01CNVBAL" size="17" maxlength="16" value="<%= invTrade.getE01CNVBAL()%>"
				onKeyPress="enterDecimal()" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01ORDEXR" size="11" maxlength="11" value="<%= invTrade.getE01ORDEXR()%>">
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
              <select disabled   name="E01ORDPTY">
                <option value="BND" <%if (invTrade.getE01ORDPTY().equals("BND")) { out.print("selected"); }%>>Bonos</option>
                <option value="EQT" <%if (invTrade.getE01ORDPTY().equals("EQT")) {  out.print("selected"); }%>>Acciones</option>
                <option value="MUT" <%if (invTrade.getE01ORDPTY().equals("MUT")) {  out.print("selected"); }%>>Fondos Mutuos</option>
                <option value="PFS" <%if (invTrade.getE01ORDPTY().equals("PFS")) {  out.print("selected"); }%>>Acciones Preferenciales</option>
              </select>
            </td>
            <td nowrap colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].E01ORDIIC.value)"><img src="<%=request.getContextPath()%>/images/1aquire.gif" alt="help" align="absbottom" border="0" > 
                Instrumento </a> :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDIIC" size="9" maxlength="9" value="<%= invTrade.getE01ORDIIC()%>" >
              <input type="text" readonly  name="E01ORDICN" size="35" maxlength="30" value="<%= invTrade.getE01ORDICN()%>">
            </td>
            <td nowrap colspan="2" > 
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Moneda del Instrumento :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDSCY" size="4" maxlength="3" value="<%= invTrade.getE01ORDSCY()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right"><a href="javascript:GetSymbolInfo(document.forms[0].E01ORDSYM.value)"><img src="<%=request.getContextPath()%>/images/1aquire.gif" alt="help" align="absbottom" border="0" > 
                Símbolo :</a> </div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDSYM" size="17" maxlength="15" value="<%= invTrade.getE01ORDSYM()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha del Ultimo Pago de Intereses :</div>
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
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Acumulaci&oacute;n :</div>
            </td>
            <td nowrap width="36%" > 
              <select disabled   name="E01ORDATY">
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
              <div align="right"> Tipo de Transacci&oacute;n :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <select disabled   name="E01ORDTRN">
                  <option value="1" <%if (invTrade.getE01ORDTRN().equals("1")) { out.print("selected"); }%>>Compra</option>
                  <option value="2" <%if (invTrade.getE01ORDTRN().equals("2")) {  out.print("selected"); }%>>Venta</option>
                  <option value="3" <%if (invTrade.getE01ORDTRN().equals("3")) {  out.print("selected"); }%>>Transferencia de Entrada</option>
                  <option value="4" <%if (invTrade.getE01ORDTRN().equals("4")) {  out.print("selected"); }%>>Transferencia de Salida</option>
                  <option value="5" <%if (invTrade.getE01ORDTRN().equals("5")) {  out.print("selected"); }%>>Compras Múltiples</option>
                  <option value="6" <%if (invTrade.getE01ORDTRN().equals("6")) {  out.print("selected"); }%>>Ajuste(Incremento)</option>
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
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha de Orden :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDIN1" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN1()%>">
                <input type="text" readonly  name="E01ORDIN2" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN2()%>">
                <input type="text" readonly  name="E01ORDIN3" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN3()%>">
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
            <td nowrap > 
              <div align="right">Fecha Efectiva : </div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01ORDST1" size="3" maxlength="2" value="<%= invTrade.getE01ORDST1()%>">
              <input type="text" readonly  name="E01ORDST2" size="3" maxlength="2" value="<%= invTrade.getE01ORDST2()%>">
              <input type="text" readonly  name="E01ORDST3" size="3" maxlength="2" value="<%= invTrade.getE01ORDST3()%>">
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
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
          <tr id="trdark"> 
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
              <div align="right">Comisi&oacute;n de Rentabilidad:</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDTGV" size="17" maxlength="15" value="<%= invTrade.getE01ORDTGV()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right">Tabla de Comisi&oacute;n:</div>
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
              <div align="right">Tabla de Comisi&oacute;n:</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDOCT" size="4" maxlength="2" value="<%= invTrade.getE01ORDOCT()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Inter&eacute;s a Pagar :</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDIAL" size="17" maxlength="15" value="<%= invTrade.getE01ORDIAL()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="14%" > 
              <div align="right">D&iacute;as Acumulados :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDDYS" size="4" maxlength="3" value="<%= invTrade.getE01ORDDYS()%>">
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
              <div align="right">Monto Inversi&oacute;n del Banco:</div>
            </td>
            <td nowrap width="36%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDIAB" size="17" maxlength="15" value="<%= invTrade.getE01ORDIAB()%>" onKeyPress="enterDecimal()">
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
              <div align="right">Monto de la Transacci&oacute;n :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ORDTBK" size="17" maxlength="15" value="<%= invTrade.getE01ORDTBK()%>" onKeyPress="enterDecimal()">
            </td>
            <td nowrap width="14%" >&nbsp;</td>
            <td nowrap width="33%" >&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Broker / Informaci&oacute;n Custodio</h4>
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
</form>
</body>
</html>
