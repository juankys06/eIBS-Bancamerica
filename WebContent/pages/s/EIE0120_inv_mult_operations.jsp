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
  <h3>M&uacute;ltiple Orden de Compra - Venta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_mult_operations.jsp,EIE0120"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0120" >
  <h4> 
    <input type="hidden" name="SCREEN"  value="2" >
    Informaci&oacute;n de la Transacci&oacute;n</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="31%" > 
              <div align="right">N&uacute;mero de la Orden Maestra:</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ORDNUM" size="9" maxlength="5" value="<%= invTrade.getE01ORDNUM()%>" readonly>
            </td>
            <td nowrap width="24%" >&nbsp;</td>
            <td nowrap width="24%" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="31%" > 
              <div align="right">Tipo de Producto :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <select name="E01ORDPTY">
                  <option value="BND" <%if (invTrade.getE01ORDPTY().equals("BND")) { out.print("selected"); }%>>Bonos</option>
                  <option value="EQT" <%if (invTrade.getE01ORDPTY().equals("EQT")) {  out.print("selected"); }%>>Acciones</option>
                  <option value="MUT" <%if (invTrade.getE01ORDPTY().equals("MUT")) {  out.print("selected"); }%>>Fondos Mutuos</option>
                  <option value="PFS" <%if (invTrade.getE01ORDPTY().equals("PFS")) {  out.print("selected"); }%>>Acciones Preferenciales</option>
                </select>
              </div>
            </td>
            <td nowrap width="24%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="24%" >&nbsp; </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="31%" > 
              <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].E01ORDIIC.value)"><img src="<%=request.getContextPath()%>/images/1aquire.gif" alt="help" align="absbottom" border="0" > 
                Instrumento :</a> </div>
            </td>
            <td nowrap colspan="5" > 
              <div align="left"> </div>
              <div align="left"> 
                <input type="text" name="E01ORDIIC" size="9" maxlength="9" value="<%= invTrade.getE01ORDIIC()%>" >
                <input type="text" name="E01ORDICN" size="35" maxlength="30" value="<%= invTrade.getE01ORDICN()%>">
                <a href="javascript:GetInstrumentParams('E01ORDIIC','E01ORDICN','E01ORDSCY','','E01ORDISI','','E01ORDPTY')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="31%" > 
              <div align="right">Moneda del Instrumento :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDSCY" size="4" maxlength="3" value="<%= invTrade.getE01ORDSCY()%>"readonly>
              </div>
            </td>
            <td nowrap width="24%" > 
              <div align="right">ISIN :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="text" name="E01ORDISI" size="16" maxlength="12" value="<%= invTrade.getE01ORDISI()%>"readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="31%" > 
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
              </div>
            </td>
            <td nowrap width="24%" >&nbsp;</td>
            <td nowrap width="24%" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="31%" > 
              <div align="right">Fecha de la Orden :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDIN1" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN1()%>">
                <input type="text" name="E01ORDIN2" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN2()%>">
                <input type="text" name="E01ORDIN3" size="3" maxlength="2" value="<%= invTrade.getE01ORDIN3()%>">
                <a href="javascript:DatePicker(document.forms[0].E01ORDIN1,document.forms[0].E01ORDIN2,document.forms[0].E01ORDIN3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
              </div>
            </td>
            <td nowrap width="24%" > 
              <div align="right">Fecha Efectiva :</div>
            </td>
            <td nowrap width="24%" > 
              <div align="left"> 
                <input type="text" name="E01ORDST1" size="3" maxlength="2" value="<%= invTrade.getE01ORDST1()%>">
                <input type="text" name="E01ORDST2" size="3" maxlength="2" value="<%= invTrade.getE01ORDST2()%>">
                <input type="text" name="E01ORDST3" size="3" maxlength="2" value="<%= invTrade.getE01ORDST3()%>">
                <a href="javascript:DatePicker(document.forms[0].E01ORDST1,document.forms[0].E01ORDST2,document.forms[0].E01ORDST3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="31%" > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDVNO" size="17" maxlength="16" value="<%= invTrade.getE01ORDVNO()%>">
              </div>
            </td>
            <td nowrap width="24%" >&nbsp;</td>
            <td nowrap width="24%" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="31%" > 
              <div align="right">Cantidad : </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDQTY" size="17" maxlength="16" value="<%= invTrade.getE01ORDQTY()%>">
              </div>
            </td>
            <td nowrap width="24%" >&nbsp;</td>
            <td nowrap width="24%" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="31%" > 
              <div align="right">Precio del Banco :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDPRB" size="17" maxlength="15" value="<%= invTrade.getE01ORDPRB()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="24%" >&nbsp;</td>
            <td nowrap width="24%" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="31%" > 
              <div align="right">Precio del Cliente :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDPRC" size="17" maxlength="15" value="<%= invTrade.getE01ORDPRC()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="24%" >&nbsp;</td>
            <td nowrap width="24%" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="31%" > 
              <div align="right">Monto de la Transacci&oacute;n del Banco :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ORDTBK" size="17" maxlength="15" value="<%= invTrade.getE01ORDTBK()%>" onKeyPress="enterDecimal()" readonly>
                <a href="javascript:Calculate()"> </a></div>
            </td>
            <td nowrap width="24%" >&nbsp;</td>
            <td nowrap width="24%" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="31%" > 
              <div align="right">Broker :</div>
            </td>
            <td nowrap colspan="5" > 
              <div align="left"> 
                <input type="text" name="E01ORDBRK" size="5" maxlength="3" value="<%= invTrade.getE01ORDBRK()%>" >
                <input type="text" name="E01ORDBKN" size="35" maxlength="30" value="<%= invTrade.getE01ORDBKN()%>">
                <a href="javascript:GetBrokerI('E01ORDBRK','E01ORDBKN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="31%" > 
              <div align="right">Custodio :</div>
            </td>
            <td nowrap colspan="5" > 
              <div align="left"> 
                <input type="text" name="E01ORDCUC" size="5" maxlength="3" value="<%= invTrade.getE01ORDCUC()%>" >
                <input type="text" name="E01ORDCCN" size="35" maxlength="30" value="<%= invTrade.getE01ORDCCN()%>">
                <a href="javascript:GetCustodian('E01ORDCUC','E01ORDCCN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
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
