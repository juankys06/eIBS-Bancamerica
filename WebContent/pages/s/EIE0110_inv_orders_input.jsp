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
<SCRIPT Language="javascript">
 function Calculate(){
  document.forms[0].SCREEN.value = '16';
  document.forms[0].submit();

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
  <h3>Entrada de Ordenes <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_orders_input.jsp,EIE110"> 
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
            <td nowrap width="29%" > 
              <div align="right">Número de Orden :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDNUM" size="9" maxlength="5" value="<%= invOrders.getE01ORDNUM()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" > 
              <div align="right"><a href="javascript:showCustomerInq(document.forms[0].E01ORDCUN.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Cliente </a>:</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="right"></div>
              <div align="left"> 
                <input type="text" name="E01ORDCUN" size="9" maxlength="9" value="<%= invOrders.getE01ORDCUN().trim()%>">
                <input type="text" name="E01ORDCTN" size="30" maxlength="30" value="<%= invOrders.getE01ORDCTN().trim()%>" readonly>
                <a href="javascript:GetCustomerDescIdInvest('E01ORDCUN','E01ORDCTN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" > 
              <div align="right">Portafolio :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDPRF" size="9" maxlength="9" value="<%= invOrders.getE01ORDPRF()%>" >
              <a href="javascript:GetPortfolioNumDesc('E01ORDPRF','E01ORDCUN','',document.forms[0].E01ORDCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" > 
              <div align="right"><a href="javascript:showRetAccountInq(document.forms[0].E01ORDHAC.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Cuenta Transaccional : </a></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDHCY" size="3" maxlength="3" value="<%= invOrders.getE01ORDHCY()%>"
                readonly >
              <input type="text" name="E01ORDHAC" size="12" maxlength="9" value="<%= invOrders.getE01ORDHAC()%>"
                 >
              <a href="javascript:GetCashAccount('E01ORDHCY','E01ORDHAC','E01MEMBAL',document.forms[0].E01ORDCUN.value,document.forms[0].E01ORDPRF.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"  ></a></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" > 
              <div align="right">Saldo Disponible :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01MEMBAL" size="17" maxlength="16" value="<%= invOrders.getE01MEMBAL()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap >&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01ORDEXR" size="11" maxlength="11" value="<%= invOrders.getE01ORDEXR()%>" >
            </td>
            <td nowrap >Saldo en Moneda del Instrumento :</td>
            <td nowrap >
              <input type="text" name="E01CNVBAL" size="17" maxlength="16" value="<%= invOrders.getE01CNVBAL()%>" readonly>
            </td>
          </tr>
        </table>
		 
      </td>
    </tr>
  </table>
  <h4>Información del Instrumento</h4>
  <table  class="tableinfo">
    <tr id="trdark"> 
      <td nowrap width="29%" > 
        <div align="right">Tipo de Producto :</div>
      </td>
      <td nowrap width="71%" > 
        <select name="E01ORDPTY">
          <option value="BND" <%if (invOrders.getE01ORDPTY().equals("BND")) { out.print("selected"); }%>>Bonos</option>
          <option value="EQT" <%if (invOrders.getE01ORDPTY().equals("EQT")) {  out.print("selected"); }%>>Acciones</option>
          <option value="MUT" <%if (invOrders.getE01ORDPTY().equals("MUT")) {  out.print("selected"); }%>>Fondos Mutuales</option>
          <option value="PFS" <%if (invOrders.getE01ORDPTY().equals("PFS")) {  out.print("selected"); }%>>Acciones Preferentes</option>
        </select>
        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="29%" > 
        <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].E01ORDIIC.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
          Instrumento :</a> </div>
      </td>
      <td nowrap width="71%" > 
        <input type="text" name="E01ORDIIC" size="9" maxlength="9" value="<%= invOrders.getE01ORDIIC()%>" >
        <input type="text" name="E01ORDICN" size="35" maxlength="30" value="<%= invOrders.getE01ORDICN()%>">
        <a href="javascript:GetInstrumentParams('E01ORDIIC','E01ORDICN','E01ORDSCY','E01ORDSYM','E01ORDISI','E01ORDCSP','E01ORDPTY')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"> 
        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></a></td>
    </tr>
    <tr id="trdark"> 
      <td nowrap width="29%" > 
        <div align="right">Moneda del Instrumento :</div>
      </td>
      <td nowrap width="71%" > 
        <div align="left"> 
          <input type="text" name="E01ORDSCY" size="4" maxlength="3" value="<%= invOrders.getE01ORDSCY()%>" readonly>
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="29%" > 
        <div align="right"><a href="javascript:GetSymbolInfo(document.forms[0].E01ORDSYM.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
          Símbolo :</a></div>
      </td>
      <td nowrap width="71%" > 
        <div align="left"> 
          <input type="text" name="E01ORDSYM" size="17" maxlength="15" value="<%= invOrders.getE01ORDSYM()%>"readonly>
        </div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td nowrap width="29%" > 
        <div align="right">ISIN :</div>
      </td>
      <td nowrap width="71%" > 
        <div align="left"> 
          <input type="text" name="E01ORDISI" size="16" maxlength="12" value="<%= invOrders.getE01ORDISI()%>"readonly>
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="29%" > 
        <div align="right">CUSIP :</div>
      </td>
      <td nowrap width="71%" > 
        <div align="left"> 
          <input type="text" name="E01ORDCSP" size="16" maxlength="12" value="<%= invOrders.getE01ORDCSP()%>"readonly>
        </div>
      </td>
    </tr>
  </table>
  <h4>Información Transaccional </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%" > 
              <div align="right">Tipo de Transacción :</div>
            </td>
            <td nowrap width="71%" > 
              <div align="left"> 
                <select name="E01ORDTRN">
                  <option value="1" <%if (invOrders.getE01ORDTRN().equals("1")) { out.print("selected"); }%>>Compra</option>
				  <option value="5" <%if (invOrders.getE01ORDTRN().equals("5")) {  out.print("selected"); }%>>Compra Multiple 
                  </option>
                  <option value="2" <%if (invOrders.getE01ORDTRN().equals("2")) {  out.print("selected"); }%>>Venta</option>
                  <option value="3" <%if (invOrders.getE01ORDTRN().equals("3")) {  out.print("selected"); }%>>Transferencia Interna</option>
                  <option value="4" <%if (invOrders.getE01ORDTRN().equals("4")) {  out.print("selected"); }%>>Transferencia Externa</option>
                  
                </select>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap width="71%"> 
              <input type="text" name="E01ORDVNO" size="17" maxlength="16" value="<%= invOrders.getE01ORDVNO()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Cantidad :</div>
            </td>
            <td nowrap width="71%"> 
              <input type="text" name="E01ORDQTY" size="17" maxlength="16" value="<%= invOrders.getE01ORDQTY()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" > 
              <div align="right">Precio Mínimo de Venta :</div>
            </td>
            <td nowrap width="71%" > 
              <input type="text" name="E01ORDMIP" size="17" maxlength="15" value="<%= invOrders.getE01ORDMIP()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" > 
              <div align="right">Precio Máximo de Compra :</div>
            </td>
            <td nowrap width="71%"> 
              <input type="text" name="E01ORDMXP" size="17" maxlength="15" value="<%= invOrders.getE01ORDMXP()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Precio al Cliente :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01ORDPRC" size="17" maxlength="16" value="<%= invOrders.getE01ORDPRC()%>" onKeyPress="enterDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Total de la Orden :</div>
            </td>
            <td nowrap width="71%"> 
              <input type="text" name="E01ORDTVL" size="17" maxlength="15" value="<%= invOrders.getE01ORDTVL()%>" onKeyPress="enterDecimal()">
			  <a href="javascript:Calculate()"> 
               <img src="<%=request.getContextPath()%>/images/calculator.gif" alt="calculator" align="absmiddle" border="0" >
			  </a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right"> Notas :</div>
            </td>
            <td nowrap width="71%" > 
              <input type="text" name="E01ORDCO1" size="40" maxlength="35" value="<%= invOrders.getE01ORDCO1()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="71%"> 
              <div align="right"></div>
              <div align="left"> 
                <input type="text" name="E01ORDCO2" size="40" maxlength="35" value="<%= invOrders.getE01ORDCO2()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right"> </div>
            </td>
            <td nowrap width="71%"> 
              <input type="text" name="E01ORDCO3" size="40" maxlength="35" value="<%= invOrders.getE01ORDCO3()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
 
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center">
          <input type="checkbox" name="H01FLGWK2" value="A" <% if(invOrders.getH01FLGWK2().equals("A")){ out.print("checked");} %>>
          Aceptar con Advertencias </div>
      </td>
    </tr>
  </table>
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
 
  </form>
</body>
</html>
