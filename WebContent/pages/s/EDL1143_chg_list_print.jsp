<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Reportes Préstamos Preferenciales</TITLE><META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "chgList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="chgInfo" class="datapro.eibs.beans.EDL114302Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<BODY>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function doPrint(){
	if(!window.print){
       var msg ="Debe actualizar su navegador para imprimir";
	   alert(msg);
	   return;}
	
    window.focus();
	window.print();

	return;
}

function checkSubmit(){
  
  document.forms[0].submit();
}

</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<FORM >
<%
	if ( chgList.getNoResult() ) {
   		out.print("<center><h4>No hay resultados que correspondan a su criterio de búsqueda</h4></center>");
	}
	else {
%>
  <h3 align="center">Reportes Préstamos Preferenciales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="chg_list_print.jsp,EDL1143"> 
  </h3>
  <hr size="4">
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
<%	if (userPO.getHeader7().equals("1")) {%>
          <tr id="trdark"> 
            <td nowrap width="100%" align="center"> 
              <b>Préstamos Preferenciales Nuevos</b>
            </td>
          </tr>
<%	} %>

<%	if (userPO.getHeader7().equals("2")) {%>
          <tr id="trdark"> 
            <td nowrap width="100%" align="center"> 
              <b>Préstamos Preferenciales Modificados</b>
            </td>
          </tr>
<%	} %>


<%	if (userPO.getHeader7().equals("3")) {%>
          <tr id="trdark"> 
            <td nowrap width="100%" align="center"> 
              <b>Crédito Fiscal</b>
            </td>
          </tr>
<%	} %>

        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">


<%-- *** Préstamos Preferenciales Nuevos *** --%>

<%	if (userPO.getHeader7().equals("1")) {%>

    <tr> 
      <TH ALIGN=CENTER>Número de Préstamo</TH>
	  <TH ALIGN=CENTER>Fecha de Inicio</TH>
      <TH ALIGN=CENTER>Fecha de Liquidación</TH>
      <TH ALIGN=CENTER>Período de Pago</TH>
      <TH ALIGN=CENTER>Cédula</TH>
      <TH ALIGN=CENTER>Nombre del Deudor</TH>
      <TH ALIGN=CENTER>Provincia</TH>
      <TH ALIGN=CENTER>Corregimiento</TH>
      <TH ALIGN=CENTER>Número de Finca</TH>
      <TH ALIGN=CENTER>Valor del Terreno</TH>      
      <TH ALIGN=CENTER>Valor de Mejoras</TH>            
      <TH ALIGN=CENTER>Valor Total Inmueble</TH>            
      <TH ALIGN=CENTER>Monto del Préstamo</TH>            
      <TH ALIGN=CENTER>Tasa de Referencia</TH>            
      <TH ALIGN=CENTER>Tasa Pactada</TH>                  
      <TH ALIGN=CENTER>Tramo Preferencial Vigente</TH>                        
      <TH ALIGN=CENTER>Registro Forestal</TH>                        
<%	} %>



<%-- *** Seguro Contra Riesgo *** --%>

<%	if (userPO.getHeader7().equals("2")) {%>


    <TR id=trdark> 
      <TH ALIGN=CENTER>RUC Acreedor Anterior</TH>
      <TH ALIGN=CENTER>DV</TH>
	  <TH ALIGN=CENTER>Fecha de Cambio</TH>
      <TH ALIGN=CENTER>Número de Préstamo Anterior</TH>
      <TH ALIGN=CENTER>Número de Préstamo Nuevo</TH>
      <TH ALIGN=CENTER>Cédula</TH>
      <TH ALIGN=CENTER>Nombre del Deudor</TH>
	  <TH ALIGN=CENTER>Fecha de Inicio</TH>
      <TH ALIGN=CENTER>Fecha de Liquidación</TH>
      <TH ALIGN=CENTER>Período de Pago</TH>
      <TH ALIGN=CENTER>Monto del Préstamo</TH>            
      <TH ALIGN=CENTER>Tasa de Referencia</TH>            
      <TH ALIGN=CENTER>Tasa Pactada</TH>                  
      <TH ALIGN=CENTER>Tramo Preferencial Vigente</TH>                        
      <TH ALIGN=CENTER>Registro Forestal</TH>                        
    </TR>
<%	} %>


<%-- *** Seguro Hipoteca *** --%>

<%	if (userPO.getHeader7().equals("3")) {%>


    <TR id=trdark> 
      <TH ALIGN=CENTER>Número de Préstamo</TH>
	  <TH ALIGN=CENTER>Fecha Inicio del Periodo</TH>
      <TH ALIGN=CENTER>Fecha Final del Periodo</TH>
      <TH ALIGN=CENTER>Saldo Inicial del Periodo</TH>      
      <TH ALIGN=CENTER>Saldo Final del Periodo</TH>            
      <TH ALIGN=CENTER>Saldo Medio del Prestamo</TH>      
      <TH ALIGN=CENTER>Tasa de Referencia Promedio</TH>      
      <TH ALIGN=CENTER>Ingresos con Tasa de Referencia</TH>      
      <TH ALIGN=CENTER>Ingresos Efectivos</TH>      
      <TH ALIGN=CENTER>Diferencia Efectiva</TH>            
      <TH ALIGN=CENTER>Limite Tope del Tramo Preferencial</TH>            
      <TH ALIGN=CENTER>Diferencia Tope del Tramo Preferencial</TH>                  
      <TH ALIGN=CENTER>Credito Fiscal</TH>                  
    </TR>
<%	} %>



    <%
                chgList.initRow();
                while (chgList.getNextRow()) {
                    if (chgList.getFlag().equals("")) {
                    		out.println(chgList.getRecord());
                    }
                }
              %> 
  </table>
  <p>&nbsp;</p>
  <table width="100%">
    <tr> 
      <td width="33%" nowrap> 
        <div align="left"> </div>
        </td>
      <td width="33%" nowrap> </td>
      <td width="34%"><b>Total :</b> <%= Util.fcolorCCY(userPO.getHeader21().trim())%></td>
    </tr>
  </table>
  
  <%
  }
%> 
</FORM>

</BODY>
</HTML>
