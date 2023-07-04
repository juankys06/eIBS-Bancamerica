<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function enviar(){

	var acc= document.forms[0].E01CHMACC.value
	if (acc.length > 0)
	{
	    return true;
	}
	else
	{
		alert("Account Number Required.");
		document.forms[0].E01CHMACC.focus();
		return false;
	}
}
</script>




</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<H3 align="center">Cambio de Estatus de Cheques y Chequeras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_enter_anul.jsp,ECH0580"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0580" onsubmit="return(enviar());">

<INPUT type="hidden" name="SCREEN" value="200">
  <h4>&nbsp;</h4>
  <Center>
  <table class="tbenter" cellspacing=0 cellpadding=2 border="0">
    <tr> 
      <td nowrap width="50%"><div align="right">Número de Cuenta : </div></td>
      <td nowrap width="50%"><input type="text" name="E01CHMACC" size="12" maxlength="12" onKeypress="enterInteger()"><a href="javascript:GetAccount('E01CHMACC','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a></td>
    </tr>
</TABLE>


<p align="center">
	<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
</p>

<script language="JavaScript">
  document.forms[0].E01CHMACC.focus();
  document.forms[0].E01CHMACC.select();
</script>
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
</body>
</html>
