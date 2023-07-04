<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta de Asignaciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function Check(){
	if (document.forms[0].search[0].checked) {
		document.forms[0].SCREEN.value = "1100";
		document.forms[0].E01CCRNUM.value = "";
		document.forms[0].submit();
	}
	else if (document.forms[0].search[1].checked) {
		if (document.forms[0].E01CCRNUM.value.length < 1) {
			alert("Introduzca un número de tarjeta válido.");
			document.forms[0].E01CCRNUM.value = "";
			document.forms[0].E01CCRNUM.focus();
		}
		else {
			document.forms[0].SCREEN.value = "1200";
			document.forms[0].E01PRICUN.value = "";
			document.forms[0].submit();
		}
	}
}

</SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Consulta de Asignaciones
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="assignment_inq_enter.jsp, ECC0090"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
  </p>

  <table class="tbenter" HEIGHT="25%" width="100%" border="0">
    <TR>
		<TD nowrap align="right">
			<INPUT type="radio" name="search" value="I" onclick="document.forms[0].E01PRICUN.focus()" checked>
			Número de Cliente : 
        </TD>
		<TD nowrap align="left">
			<INPUT type="text" name="E01PRICUN" size="10" maxlength="9" onKeyPress="enterInteger()" onclick="document.forms[0].search[0].click()">
			<A href="javascript:GetCustomerDescId('E01PRICUN','','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A>
		</TD>
	</TR> 
    <tr> 
    	<td nowrap ALIGN="right">
			<input type="radio" name="search" value="C" onclick="document.forms[0].E01CCRNUM.focus()">
			Número de Tarjeta : 
		</td>
    	<td nowrap ALIGN="left"> 
        	<INPUT type="text" name="E01CCRNUM" size="24" maxlength="20" onclick="document.forms[0].search[1].click()" onkeypress="enterInteger()">
        	<a href="javascript:GetPlastic2('E01CCRNUM', '' ,'')"></a>     
    	</td>
    </tr> 
  </table>  
 <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit" value="Aceptar" onClick="Check()">
  </p>  
<script language="JavaScript">
  document.forms[0].E01PRICUN.focus();
  document.forms[0].E01PRICUN.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     
 }
%> 
</form>
</body>
</html>

