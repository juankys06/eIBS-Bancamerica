<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Ordenes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invOrders" class="datapro.eibs.beans.EIE011001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

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
  <h3>Consulta de Ordenes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_orders_input.jsp,EIE110"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0110" >
  <h4>Información Básica
    <input type="hidden" name="SCREEN"  value="2" >
  </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="26%" > 
              <div align="right">Número de Orden :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly  name="E01ORDNUM" size="9" maxlength="5" value="<%= invOrders.getE01ORDNUM()%>" >
            </td>
            <td nowrap width="26%" > 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ORDCUN" size="9" maxlength="9" value="<%= invOrders.getE01ORDCUN().trim()%>">
              <input type="text" readonly  name="E01ORDCTN" size="30" maxlength="30" value="<%= invOrders.getE01ORDCTN().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right">Cuenta Transaccional  :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly  name="E01ORDHCY" size="3" maxlength="3" value="<%= invOrders.getE01ORDHCY()%>"
                >
              <input type="text" readonly  name="E01ORDHAC" size="12" maxlength="9" value="<%= invOrders.getE01ORDHAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01ORDHBK.value,'','','','RT')" >
            </td>
            <td nowrap width="26%" > 
              <div align="right">Portafolio :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDPRF" size="9" maxlength="9" value="<%= invOrders.getE01ORDPRF()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" > 
              <div align="right">Saldo :</div>
            </td>
            <td nowrap width="15%" > <a href="javascript:GetInstrument('E01ORDIIC','E01ORDICN')"> 
              </a> 
              <input type="text" readonly  name="E01MEMBAL" size="17" maxlength="16" value="<%= invOrders.getE01MEMBAL()%>" >
            </td>
            <td nowrap width="26%" > 
              <div align="right">Saldo en <%= invOrders.getE01ORDSCY()%> :</div>
            </td>
            <td nowrap width="33%" > <a href="javascript:GetInstrument('E01ORDIIC','E01ORDICN')"> 
              </a> 
              <input type="text" readonly  name="E01CNVBAL" size="17" maxlength="16" value="<%= invOrders.getE01CNVBAL()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap width="15%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDEXR" size="11" maxlength="11" value="<%= invOrders.getE01ORDEXR()%>" >
              </div>
            </td>
            <td nowrap width="26%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" > 
              <div align="right">Instrumento : </div>
            </td>
            <td nowrap width="15%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDIIC" size="9" maxlength="9" value="<%= invOrders.getE01ORDIIC()%>" >
                <input type="text" readonly  name="E01ORDICN" size="35" maxlength="30" value="<%= invOrders.getE01ORDICN()%>">
              </div>
            </td>
            <td nowrap width="26%" > 
              <div align="right">Tipo de Producto :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"><a href="javascript:GetInstrument('E01ORDIIC','E01ORDICN')"> 
                <select disabled name="E01ORDPTY">
                  <option value="BND" <%if (invOrders.getE01ORDPTY().equals("BND")) { out.print("selected"); }%>>Bonos</option>
                  <option value="EQT" <%if (invOrders.getE01ORDPTY().equals("EQT")) {  out.print("selected"); }%>>Acciones</option>
                  <option value="MUT" <%if (invOrders.getE01ORDPTY().equals("MUT")) {  out.print("selected"); }%>>Fondos Mutuales</option>
                  <option value="PFS" <%if (invOrders.getE01ORDPTY().equals("PFS")) {  out.print("selected"); }%>>Acciones Preferentes</option>
                </select>
                </a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right">Moneda del Instrumento :</div>
            </td>
            <td nowrap width="15%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ORDSCY" size="4" maxlength="3" value="<%= invOrders.getE01ORDSCY()%>" >
              </div>
            </td>
            <td nowrap width="26%" > 
              <div align="right">Tipo de Transacción :</div>
            </td>
            <td nowrap width="33%" > 
              <select disabled name="E01ORDTRN">
                <option value="1" <%if (invOrders.getE01ORDTRN().equals("1")) { out.print("selected"); }%>>Compra</option>
                <option value="2" <%if (invOrders.getE01ORDTRN().equals("2")) {  out.print("selected"); }%>>Venta</option>
                <option value="3" <%if (invOrders.getE01ORDTRN().equals("3")) {  out.print("selected"); }%>>Transferencia Interna</option>
                <option value="4" <%if (invOrders.getE01ORDTRN().equals("4")) {  out.print("selected"); }%>>Transferencia Externa</option>
                <option value="5" <%if (invOrders.getE01ORDTRN().equals("5")) {  out.print("selected"); }%>>Compra Multiple</option>
                <option value="6" <%if (invOrders.getE01ORDTRN().equals("6")) {  out.print("selected"); }%>>Ajuste(Incremento)</option>
                <option value="7" <%if (invOrders.getE01ORDTRN().equals("7")) {  out.print("selected"); }%>>Ajuste(Decremento)</option>
              </select>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información de la Transacción </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="26%" > 
              <div align="right">Cantidad :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly  name="E01ORDQTY" size="17" maxlength="16" value="<%= invOrders.getE01ORDQTY()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly  name="E01ORDVNO" size="17" maxlength="16" value="<%= invOrders.getE01ORDVNO()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" > 
              <div align="right">Precio M&iacute;nimo :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly  name="E01ORDMIP" size="17" maxlength="15" value="<%= invOrders.getE01ORDMIP()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right">Precio M&aacute;ximo :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly  name="E01ORDMXP" size="17" maxlength="15" value="<%= invOrders.getE01ORDMXP()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" > 
              <div align="right">Precio al Cliente :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly  name="E01ORDPRC" size="17" maxlength="16" value="<%= invOrders.getE01ORDPRC()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right">Monto de la Orden :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly  name="E01ORDIAM" size="17" maxlength="15" value="<%= invOrders.getE01ORDIAM()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01ORDCO1" size="40" maxlength="35" value="<%= invOrders.getE01ORDCO1()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <div align="right"></div>
              <div align="left"> 
                <input type="text" readonly  name="E01ORDCO2" size="40" maxlength="35" value="<%= invOrders.getE01ORDCO2()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" > 
              <div align="right"> </div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01ORDCO3" size="40" maxlength="35" value="<%= invOrders.getE01ORDCO3()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
