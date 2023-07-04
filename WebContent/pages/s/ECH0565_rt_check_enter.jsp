<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Solicitud de Mantenimiento</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function enviar(){

	var E01ACMACC= document.forms[0].E01ACMACC.value
	if (E01ACMACC.length > 0)
	{
	    return true;
	}
	else
	{
		alert("Es requerido que se entre un valor al número de Cuenta Corriente.");
		document.forms[0].E01ACMACC.focus();
		return false;
	}
}
</script>




</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Consulta de Chequera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_check_enter_.jsp,ECH0565"></H3>

<% 
 if ( !error.getERRNUM().equals("0")  ) 
 {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0565" onsubmit="return(enviar());">

<INPUT type="hidden" name="SCREEN" value="5">
  <h4>&nbsp;</h4>
  <table class="tbenter" cellspacing=0 cellpadding=2 border="0">
    <tr> 
      <td nowrap width="50%"><div align="right">N&uacute;mero de Cuenta : </div></td>
      <td nowrap width="50%"><input type="text" name="E01ACMACC" size="12" maxlength="12" onKeypress="enterInteger()"><a href="javascript:GetAccount('E01ACMACC','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a></td>
    </tr>
</TABLE>


<p align="center">
<input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
</p>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>;
 <%
 }
%>
</form>
</body>
</html>
