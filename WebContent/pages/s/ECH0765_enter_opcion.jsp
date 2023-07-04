<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<U></U><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Cartolas Históricas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<SCRIPT Language="Javascript">
function enviar()
{

 	
	if (document.forms[0].E01CHMFEY.value == "")
		document.forms[0].E01CHMFEY.value = "0";
		
	if (document.forms[0].E01CHMFEM.value == "")
		document.forms[0].E01CHMFEM.value = "0";
		
	if (document.forms[0].E01CHMFED.value == "")
		document.forms[0].E01CHMFED.value = "0";

	if (document.forms[0].E01CHMACC.value == "")
		document.forms[0].E01CHMACC.value = "0";

	if (document.forms[0].E01CHMTCB.value == "")
		document.forms[0].E01CHMTCB.value = "0";

	
	if (document.forms[0].E01ESTADO.value != "1")
	  if (document.forms[0].E01CHMFEY.value == "0" && document.forms[0].E01CHMFEM.value == "0" && document.forms[0].E01CHMFED.value == "0")
	   if (document.forms[0].E01CHMACC.value == "0")
	   {
	  		alert("Debe ingresar fecha.");
	  		return false;
	   }

	   if (document.forms[0].E01CHMBRN.value != "")
	   {
		 	document.forms[0].submit();
	   }
	   else
			alert("Debe seleccionar la sucursal de entrega");

		
}

</SCRIPT>


<body >

<h3 align="center">Consulta de Chequera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="enter_opcion,ECH076501"></h3>
<hr size="4">

<%
if ( !userPO.getHeader21().equals("") ) 
{%>
<p>&nbsp;</p>
<p>&nbsp;</p>
<% 
if (userPO.getHeader21().equals("1")) 
	{%>
	<h3 align="center">Debe ingresar los criterios de busqueda.</h3>
	<%}
else
if (userPO.getHeader21().equals("2"))
	{%>
	<h3 align="center">No existen datos para la consulta.</h3>
	<%}
else
if (userPO.getHeader21().equals("3")) 
	{%>
	<h3 align="center">No existe detalle. Inconsistencia de datos.</h3>
	<%}
}
else
{
%>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0765">

    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="405">
    <INPUT TYPE=HIDDEN NAME="FechaNow" VALUE=<%=userPO.getHeader19()%>>

  <table class="tbenter" cellspacing=0 cellpadding=2 border="0">
    <tr> 
      <td nowrap>                 
        <div align="right">Oficina de entrega :</div>
      </td>
      <td nowrap> 
        <input size="5" type="text" name="E01CHMBRN" maxlength="4" onKeypress="enterInteger()" value = <%= Integer.parseInt(currUser.getE01UBR()) %>>
		<INPUT size="25" type="hidden" name="DESNJMOET" maxlength="20"  readonly>
		<a href="javascript:GetBranch('E01CHMBRN','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
		
      </td>
	</tr>
	<tr>
      <td nowrap >                 
        <div align="right">Estado : </div>
      </td>
      <td nowrap >
      <SELECT name="E01ESTADO">
                <OPTION value="1" selected>Imprenta</OPTION>
                <OPTION value="2">Recibidas</OPTION>
                <OPTION value="3">Vendidas</OPTION>
            </SELECT><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory field" align="absbottom" border="0"></td>
    </tr>
	<tr>	
      <td nowrap > 
        <div align="right">N&uacute;mero de Cuenta : </div>
      </td>
      <td nowrap> 
        <input type="text" name="E01CHMACC" size="12" maxlength="12" onKeypress="enterInteger()">
        <a href="javascript:GetAccount('E01CHMACC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
      </td>
    </tr>
    <tr>
      <td nowrap>                 
        <div align="right">Fecha  :</div>
      </td>
      <td nowrap> 
        <input type="text" name="E01CHMFED" size="2" maxlength="2" onkeypress="enterInteger()"  value="<%= Integer.parseInt(currUser.getE01RDD()) %>">
        <input type="text" name="E01CHMFEM" size="2" maxlength="2" onkeypress="enterInteger()"  value="<%= Integer.parseInt(currUser.getE01RDM()) %>">
        <input type="text" name="E01CHMFEY" size="2" maxlength="2" onkeypress="enterInteger()"  value="<%= Integer.parseInt(currUser.getE01RDY()) %>">
      </td>
    </tr>
    <tr>
      <td nowrap>                 
        <div align="right">Tipo Chequera :</div>
      </td>
      <td nowrap > 
        <input type="text" name="E01CHMTCB" size="2" maxlength="2" onkeypress="enterInteger()">
		<A href="javascript:GetTypCHKBook('E01CHMTCB','','','')">
		<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></A> 
        
      </td>
    </tr>


  </table>
  
 
  	<p align="center"> 
   	  <input id="EIBSBTN" type=Submit name="Submit" value="Enviar">
  </p>

  

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
<%}%>  
</body>
</html>
