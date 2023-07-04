<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Rentabilidad por Clientes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="profit" class="datapro.eibs.beans.ECP001001Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
 function previousMonth(){
  document.forms[0].SELMTH.value = 'M';
  document.forms[0].submit();

}

 function previousYear(){
  document.forms[0].SELMTH.value = 'Y';
  document.forms[0].submit();

}
builtNewMenu(ecif10_i_opt);
</SCRIPT>

</head>

<body>

<%@ page import = "datapro.eibs.master.Util" %>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>

<h3 align="center">Rentabilidad por clientes al <% if (profit.getE01SELMTH().equals("Y")) out.print(" Año en Curso");%> 
  <% if (profit.getE01SELMTH().equals("M")) out.print(" Mes Anterior");%> <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cif_profit.jsp,ECP001"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECP001" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  <input type=HIDDEN name="SELMTH">
  <h4>Informaci&oacute;n del Cliente</h4>
 <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="22%" height="31"> 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap width="18%" height="31"> 
              <input type="text" readonly name="CUSTOMER"  size="11" maxlength="9" value="<%= profit.getE01CUSNUM().trim()%>">
            </td>
            <td nowrap width="21%" height="31"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="39%" height="31"> 
              <input type="text" readonly name="E01CUSNME"  size="35" maxlength="45" value="<%= profit.getE01CUSNME().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Rentabilidad</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap height="36"> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap height="31" bordercolor="#000000"> 
              <div align="center"><b>Concepto</b></div>
            </td>
            <td nowrap height="31" bordercolor="#000000"> 
              <div align="center"><b>Promedios</b></div>
            </td>
            <td nowrap height="31" bordercolor="#000000"> 
              <div align="center"><b>Interes</b></div>
            </td>
            <td nowrap height="31" bordercolor="#000000"> 
              <div align="center"><b>Costo / <br>
                Rendimiento</b></div>
            </td>
            <td nowrap height="31" bordercolor="#000000"> 
              <div align="center"><b>Utilidad <br>
                Neta</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000" height="31"> 
              <div align="right">Inversiones a Plazo :</div>
            </td>
            <td nowrap width="26%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG1" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG1())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT1" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT1())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST1" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST1())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET1" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET1())%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Pr&eacute;stamos Comerciales :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG2" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG2())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT2" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT2())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST2" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST2())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET2" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET2())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000" height="35"> 
              <div align="right">Descuento Documentos : </div>
            </td>
            <td nowrap width="26%" height="35" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG3" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG3())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="35" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT3" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT3())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="35" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST3" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST3())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="35" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET3" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET3())%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Sobregiros : </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG4" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG4())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT4" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT4())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST4" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST4())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET4" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET4())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Pr&eacute;stamos Hipotecarios :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG5" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG5())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT5" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT5())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST5" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST5())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET5" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET5())%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Pr&eacute;stamos de Arrendamiento :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG6" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG6())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT6" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT6())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST6" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST6())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET6" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET6())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Inversiones (24 Horas) : </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG7" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG7())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT7" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT7())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST7" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST7())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET7" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET7())%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><b>Total Activos : </b></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG8" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG8())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT8" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT8())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST8" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST8())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET8" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET8())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="5" bordercolor="#000000"> 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Dep&oacute;sitos Vista :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG9" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG9())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT9" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT9())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST9" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST9())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET9" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET9())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Dep&oacute;sitos Vista / Intereses :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG10" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG10())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT10" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT10())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST10" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST10())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET10" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET10())%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Inversiones a Plazos :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG11" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG11())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT11" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT11())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST11" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST11())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET11" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET11())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Certificados a T&eacute;rmino :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG12" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG12())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT12" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT12())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST12" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST12())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET12" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET12())%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Inversiones (24 Horas) :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG13" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG13())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT13" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT13())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST13" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST13())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET13" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET13())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><b>Total Pasivos :</b></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG14" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG14())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT14" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT14())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST14" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST14())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET14" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET14())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="5" bordercolor="#000000"> 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Utilidad por Uso Fondos :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01AVG15" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01AVG15())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01INT15" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01INT15())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01CST15" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01CST15())%>" >
              </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01NET15" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01NET15())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Utilidad por Servicios :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01FEEINC" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01FEEINC())%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Costo Operativo :</div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01COSOPE" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01COSOPE())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><b>Utilidad / P&eacute;rdida Neta :</b></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01PRFLSS" size="17" maxlength="15" value="<%= Util.formatCCY(profit.getE01PRFLSS())%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><b>Rendimiento :</b></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E01RENPOR" size="17" maxlength="15" value="<%= profit.getE01RENPOR()%>" >
              </div>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> <% if( profit.getE01SELMTH().trim().equals("M")) {%> 
          <input id="EIBSBTN_LARGE" type=button name="Button" value="Ver Año en Curso" onClick="javascript:previousYear()">
          <%}%> <% if( profit.getE01SELMTH().trim().equals("Y")) {%> 
          <input id="EIBSBTN_LARGE" type=button name="Button2" value="Ver Mes Anterior" onClick="javascript:previousMonth()">
          <%}%> </div>
      </td>
      <td width="33%">
        <div align="center">
          <input id="EIBSBTN" type=button name="Button22" value="Servicios" onClick="javascript:showServices('<%= profit.getE01CUSNUM()%>')">
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td colspan="2"> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
