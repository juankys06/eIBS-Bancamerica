<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Sistema Bancario: Consulta Convenios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="deal" class="datapro.eibs.beans.ESD079001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />
</head>

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
function retorna_pagina(){
location= prefix + language + "ESD0795_deal_enter.jsp";
} 
function goPrint(){print();}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<H3 align="center">Consulta de Convenios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="deal_basic.jsp, ESD0795"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0795" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">

   <TABLE class="tbenter" width="50%" ALIGN=CENTER>
    <TR>
	  <TD class="TDBKG"> <a href="javascript:retorna_pagina()">Registro<br>Anterior</a></td>
      <TD class="TDBKG"> <a href="javascript:goPrint()">Imprimir</a></TD>
      <TD class="TDBKG"> <a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a></TD>
    </TR>
   
  </TABLE>	
  <table  class="tableinfo" ALIGN=CENTER>
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="1"> 
              <div align="right"><b>Código de Convenio : </b></div>
            </td>
            <td colspan="3" nowrap><%= deal.getE01COTCDE().trim()%></td>
           </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Descripción : </b></div>
            </td>
            <td  nowrap colspan="1"><%= deal.getE01COTDES().trim()%></td>
            <td nowrap > 
              <div align="right"><b>R.U.T. Empresa : </b></div>
            </td>
            <td nowrap ><%= deal.getE01COTRUT().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Productos en Convenio : </b></div>
            </td>
            <td nowrap><% if ( deal.getE01COTCTY().trim().equals("9999")) {out.print("Todos");} else {out.print(deal.getE01COTCTY());} %></td>
            <td nowrap> 
              <div align="right"><b>Cta. Coriente Principal : </b></div>
            </td>
            <td nowrap><%= deal.getE01COTACC().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Fecha Inicio : </b></div>
            </td>
            <td nowrap><%= deal.getE01COTFID().trim()%> / <%= deal.getE01COTFIM().trim()%> / <%= deal.getE01COTFIA().trim()%></td>
            <td nowrap> 
              <div align="right"><b>Fecha Vencimiento : </b></div>
            </td>
            <td nowrap><%= deal.getE01COTFVD().trim()%> / <%= deal.getE01COTFVM().trim()%> / <%= deal.getE01COTFVA().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Estado : </b></div>
            </td>
            <td nowrap><% if ( deal.getE01COTSTA().trim().equals("A")) {out.print("Activo");} else {if ( deal.getE01COTSTA().trim().equals("C")) {out.print("Cancelado");} else {deal.getE01COTSTA();};} %> 
            </td>
            <td nowrap> 
              <div align="right"><b>Referencia de Cargo : </b></div>
            </td>
            <td nowrap><%= deal.getE01COTRCV().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Código Oficial : </b></div>
            </td>
            <td nowrap><%= deal.getE01COTOFC()%></td>
            <td nowrap> 
              <div align="right"><b>Código Oficina : </b></div>
            </td>
            <td nowrap><%= deal.getE01COTBRN()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4"> 
             
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>  
 
  </form>
 </body>
</html>
