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

<H3 align="center">Moneda
	Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="assignment_inq_enter.jsp, ECC0090"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
  </p>

  <table class="tbenter" height="25%" width="100%" border="0">
    <tr>
		<td nowrap align="right">
			<input type="radio" name="search" value="I" onclick="document.forms[0].E01PRICUN.focus()" checked>
			Número de Cliente : 
        </td><td nowrap align="left">
			<input type="text" name="E01PRICUN" size="10" maxlength="9" onkeypress="enterInteger()" onclick="document.forms[0].search[0].click()">
			<a href="javascript:GetCustomerDescId('E01PRICUN','','')"><img src="../../images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>
		</td>
		
	</tr> 
    <tr> 
    	<td nowrap align="right">
			<input type="radio" name="search" value="C" onclick="document.forms[0].E01CCRNUM.focus()">
			Identificacion Cliente : 
		</td>
    	<td nowrap align="left"> 
        	<input type="text" name="E01CCRNUM" size="24" maxlength="20" onclick="document.forms[0].search[1].click()" onkeypress="enterInteger()">
        	<a href="javascript:GetPlastic2('E01CCRNUM', '' ,'')"></a>     
    	</td>
    </tr> 
  </table>


<table class="tbenter" width="100%" border="0">


	<tr>
		<td nowrap align="right" width="317" height="44"><input type="radio" name="search"
			value="C" onclick="document.forms[0].E01CCRNUM.focus()">Agencia :</td>
		<td nowrap align="left" width="357" height="44"><input type="text" name="E01CCRNUM0"
			size="24" maxlength="20"
			onclick="document.forms[0].search[1].click()"
			onkeypress="enterInteger()"> <a
			href="javascript:GetPlastic2('E01CCRNUM', '' ,'')"></a></td>
	</tr>
</table>
<table class="tbenter" width="100%" border="0">


	<tr>
		<td nowrap align="right" width="319" height="43"><input type="radio" name="search"
			value="C" onclick="document.forms[0].E01CCRNUM.focus()">
		Usuario o Trade :</td>
		<td nowrap align="left" width="355" height="43"><input type="text" name="E01CCRNUM1"
			size="24" maxlength="20"
			onclick="document.forms[0].search[1].click()"
			onkeypress="enterInteger()"> <a
			href="javascript:GetPlastic2('E01CCRNUM', '' ,'')"></a></td>
	</tr>
</table>
<table class="tbenter" width="100%" border="0">


	<tr>
		<td nowrap align="right" width="321" height="42"><input type="radio" name="search"
			value="C" onclick="document.forms[0].E01CCRNUM.focus()">
		Moneda   :</td>
		<td nowrap align="left" width="353" height="42"><input type="text" name="E01CCRNUM2"
			size="24" maxlength="20"
			onclick="document.forms[0].search[1].click()"
			onkeypress="enterInteger()"> <a
			href="javascript:GetPlastic2('E01CCRNUM', '' ,'')"></a></td>
	</tr>
</table>
<table class="tbenter" width="122%" border="0">


	<tr>
		<td nowrap align="right" width="326" height="40"><input type="radio"
			name="search" value="C" onclick="document.forms[0].E01CCRNUM.focus()">
		Fecha desde :</td>
		<td nowrap align="left" width="109" height="40"><input type="text" name="E01CCRNUM3"
			size="3" maxlength="20" onclick="document.forms[0].search[1].click()"
			onkeypress="enterInteger()"> <input type="text"
			name="E01CCRNUM31" size="3" maxlength="20"
			onclick="document.forms[0].search[1].click()"
			onkeypress="enterInteger()"><input type="text"
			name="E01CCRNUM32" size="3" maxlength="20"
			onclick="document.forms[0].search[1].click()"
			onkeypress="enterInteger()"><a
			href="javascript:GetPlastic2('E01CCRNUM', '' ,'')"></a></td>
		<td nowrap align="left" width="81" height="40">Fecha Hasta :</td>
		<td nowrap align="left" width="161" height="40"><input type="text"
			name="E01CCRNUM30" size="3" maxlength="20"
			onclick="document.forms[0].search[1].click()"
			onkeypress="enterInteger()"><input type="text"
			name="E01CCRNUM300" size="3" maxlength="20"
			onclick="document.forms[0].search[1].click()"
			onkeypress="enterInteger()"><input type="text"
			name="E01CCRNUM301" size="3" maxlength="20"
			onclick="document.forms[0].search[1].click()"
			onkeypress="enterInteger()"></td>
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

