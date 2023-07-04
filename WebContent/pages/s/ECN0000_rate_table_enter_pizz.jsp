<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<title>Tasas de Captadora</title>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "tbRate" class= "datapro.eibs.beans.ECN000003Message"  scope="session" />
<jsp:useBean id= "tbRate2" class= "datapro.eibs.beans.ECN000002Message"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</head>

<body nowrap>

<h3 align="center">Nueva Tasa de Pizarra<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rate_table_enter_pizz, ECN0000"></h3>
<hr size="4">
<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSECN0000">
  <input type=HIDDEN name="SCREEN" value="5">
  <INPUT TYPE=HIDDEN NAME="E03CDRSFL" VALUE="S">
  
  <table  class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=2>
	  <% if (userPO.getPurpose().equals("NEW")) {%>  
      <tr>
        <td width="50%"> 
          <div align="right">C&oacute;digo de Tabla : </div>
        </td>
        <td>
        	<input type="text" name="E03CDRRTB" size="2" maxlength="1" value="<%=tbRate.getE03CDRRTB()%>">
        </td>
      </tr>
	  <tr>
	  	<td width="50%"> 
          <div align="right">Fecha Proceso : </div>
        </td>
        <td>
        	<input type="text" name="E03CDRDT1" size="3" maxlength="2" value="<%=tbRate.getE03CDRDT1()%>" onKeypress="enterInteger()">
        	<input type="text" name="E03CDRDT2" size="3" maxlength="2" value="<%=tbRate.getE03CDRDT2()%>" onKeypress="enterInteger()">
        	<input type="text" name="E03CDRDT3" size="3" maxlength="2" value="<%=tbRate.getE03CDRDT3()%>" onKeypress="enterInteger()">
        </td>
      </tr>
	  <tr>
	    <td width="50%"> 
          <div align="right">C&oacute;digo de Moneda : </div>
        </td>
	  	<td>
	  	   <input type="text" name="E03CDRCCY" size="3" maxlength="3" value="<%=tbRate.getE03CDRCCY()%>">
	  	   <a href="javascript:GetCurrency('E03CDRCCY','')">
	  	   <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>         
        </td>
	  </tr>
	  <%} else if (userPO.getPurpose().equals("COPY")) {%>
      <tr>
        <td width="50%"> 
          <div align="right">C&oacute;digo de Tabla : </div>
        </td>
        <td>
        	<input type="text" name="E02CDRRTB" size="2" maxlength="1" value="<%=tbRate2.getE02CDRRTB()%>">
        </td>
      </tr>
	  <tr>
	  	<td width="50%"> 
          <div align="right">Fecha Proceso : </div>
        </td>
        <td>
        	<input type="text" name="E02CDRDT1" size="3" maxlength="2" value="<%=tbRate2.getE02CDRDT1()%>" onKeypress="enterInteger()">
        	<input type="text" name="E02CDRDT2" size="3" maxlength="2" value="<%=tbRate2.getE02CDRDT2()%>" onKeypress="enterInteger()">
        	<input type="text" name="E02CDRDT3" size="3" maxlength="2" value="<%=tbRate2.getE02CDRDT3()%>" onKeypress="enterInteger()">
        </td>
      </tr>
	  <tr>
	    <td width="50%"> 
          <div align="right">C&oacute;digo de Moneda : </div>
        </td>
	  	<td>
	  	   <input type="text" name="E02CDRCCY" size="3" maxlength="3" value="<%=tbRate2.getE02CDRCCY()%>">
	  	   <a href="javascript:GetCurrency('E02CDRCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>         
        </td>
	  </tr>	  
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
        	<input type="text" name="E02CFRRTB" size="2" maxlength="1" value="<%=tbRate2.getE02CFRRTB()%>">
        </td>
      </tr>
	  <tr>
	  	<td width="50%"> 
          <div align="right">Fecha : </div>
        </td>
        <td>
        	<input type="text" name="E02CFRDT1" size="3" maxlength="2" value="<%=tbRate2.getE02CFRDT1()%>" onKeypress="enterInteger()">
        	<input type="text" name="E02CFRDT2" size="3" maxlength="2" value="<%=tbRate2.getE02CFRDT2()%>" onKeypress="enterInteger()">
        	<input type="text" name="E02CFRDT3" size="3" maxlength="2" value="<%=tbRate2.getE02CFRDT3()%>" onKeypress="enterInteger()">
        </td>
      </tr>
	  <tr>
	    <td width="50%"> 
          <div align="right">Moneda : </div>
        </td>
	  	<td>
	  	   <input type="text" name="E02CFRCCY" size="3" maxlength="3" value="<%=tbRate2.getE02CFRCCY()%>">
	  	   <a href="javascript:GetCurrency('E02CFRCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>         
        </td>
	  </tr>
      <% } %>
  </table>
      
  <p align="center">         
      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
<% if (userPO.getPurpose().equals("NEW")) { %>
<script language="JavaScript">
  document.forms[0].E03CDRRTB.focus();
  document.forms[0].E03CDRRTB.select();
</script>
<% } else if (userPO.getPurpose().equals("COPY")) { %>
<script language="JavaScript">
  document.forms[0].E02CDRRTB.focus();
  document.forms[0].E02CDRRTB.select();
</script>
 
<% }
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



