<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<title>Tasas de Captadora</title>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</head>

<body nowrap>

<h3 align="center">Nueva Tasa de Interes por Plazo vs Monto<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rate_table_enter, ECN0000"></h3>
<hr size="4">
<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSECN0000">
  <input type=HIDDEN name="SCREEN" value="3">
  <INPUT TYPE=HIDDEN NAME="E02CDRSFL" VALUE="">
  
  <table  class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=2>
      <tr>
        <td width="50%"> 
          <div align="right">C&oacute;digo de Tabla : </div>
        </td>
        <td>
        	<input type="text" name="E02CDRRTB" size="2" maxlength="1">
        </td>
      </tr>
	  <tr>
	  	<td width="50%"> 
          <div align="right">Fecha Proceso : </div>
        </td>
        <td>
        	<input type="text" name="E02CDRDT1" size="3" maxlength="2" onKeypress="enterInteger()">
        	<input type="text" name="E02CDRDT2" size="3" maxlength="2" onKeypress="enterInteger()">
        	<input type="text" name="E02CDRDT3" size="3" maxlength="2" onKeypress="enterInteger()">
        </td>
      </tr>
	  <tr>
	    <td width="50%"> 
          <div align="right">C&oacute;digo de Moneda : </div>
        </td>
	  	<td>
	  	   <input type="text" name="E02CDRCCY" size="3" maxlength="3">
	  	   <a href="javascript:GetCurrency('E02CDRCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>         
        </td>
	  </tr>
	  <% if (userPO.getPurpose().equals("COPY")) {%>
	  <tr>
        <td width="50%" align=right> 
          <br>
          <h4 style="text-align: right"> Copiar Tasas de :</h4>
        </td>
        <td></td>
      </tr>
	  <tr>
        <td width="50%"> 
          <div align="right">Tabla : </div>
        </td>
        <td>
        	<input type="text" name="E02CFRRTB" size="2" maxlength="1">
        </td>
      </tr>
	  <tr>
	  	<td width="50%"> 
          <div align="right">Fecha : </div>
        </td>
        <td>
        	<input type="text" name="E02CFRDT1" size="3" maxlength="2" onKeypress="enterInteger()">
        	<input type="text" name="E02CFRDT2" size="3" maxlength="2" onKeypress="enterInteger()">
        	<input type="text" name="E02CFRDT3" size="3" maxlength="2" onKeypress="enterInteger()">
        </td>
      </tr>
	  <tr>
	    <td width="50%"> 
          <div align="right">Moneda : </div>
        </td>
	  	<td>
	  	   <input type="text" name="E02CFRCCY" size="3" maxlength="3">
	  	   <a href="javascript:GetCurrency('E02CFRCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>         
        </td>
	  </tr>
      <% } %>
  </table>
      
  <p align="center">         
      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
<script language="JavaScript">
  document.forms[0].E02CDRRTB.focus();
  document.forms[0].E02CDRRTB.select();
</script>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>

</html>



