<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Préstamos Preferenciales Nuevos</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
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

function PrintPreview() {

  <% 
  int iniPos = chgList.getFirstRec() - 1;
  out.println("var pos = " + iniPos + ";");
  %>
	var pg = '<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1143?SCREEN=3&Pos=' + pos;
	CenterWindow(pg,720,500,2);

}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<FORM>
<%
	if ( chgList.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>

  <h3 align="center">Préstamos Preferenciales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="chg_list.jsp,EDL1143"> 
  </h3>
  <hr size="4">

  <p>&nbsp;</p>
<table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
        </table>
      </td>
    </tr>
  </table>
  <h4 align="left">Registro de Operaciones</h4>
  <TABLE class="tableinfo">

<%-- *** Impuesto Unico Sobre Inmuebles *** --%>

<%	if (userPO.getHeader7().equals("1")) {%>
    <TR id=trdark> 
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
    </TR>
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

  </TABLE>
    
  <BR>
  
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( chgList.getShowPrev() ) {
      			int pos = chgList.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEDL1143?SCREEN=1&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
 %> 
   </TD>
 	<TD WIDTH="50%" ALIGN=RIGHT>
 <%      
        if ( chgList.getShowNext() ) {
      			int pos = chgList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEDL1143?SCREEN=1&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
%>
  	</TD>
 	</TR>
 	</TABLE>

  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="PrintPreview()" value="Imprimir">
  </div>
</p>

  <%
  }
%>

</FORM>

</BODY>
</HTML>
