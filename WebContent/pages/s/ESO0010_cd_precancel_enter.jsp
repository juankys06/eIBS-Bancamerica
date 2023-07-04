<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Aplicación de Pre-Cancelación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function Check(){
	if (document.forms[0].search[0].checked) {
		document.forms[0].SCREEN.value = "100";
		document.forms[0].submit();
	}
	else if (document.forms[0].search[1].checked) {
		document.forms[0].SCREEN.value = "2";
		document.forms[0].submit();
	}
}

</SCRIPT>


</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Aplicación de Pre-Cancelación
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_precancel_enter.jsp, ESO0010"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESO0010">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
  </p>

  <table class="tbenter" HEIGHT="25%" width="100%" border="0">
    <TR>
		<TD nowrap align="right">
			<INPUT type="radio" name="search" value="U" onclick="document.forms[0].E01SELUSR.focus()" checked>
			Usuario : 
        </TD>
		<TD nowrap align="left">
			<INPUT type="text" name="E01SELUSR" size="11" maxlength="10" onKeyPress="" onclick="document.forms[0].search[0].click()">
		</TD>
	</TR> 
    <tr> 
    	<td nowrap ALIGN="right">
			<input type="radio" name="search" value="C" onclick="document.forms[0].E01SELACC.focus()">
			Número de Certificado : 
		</td>
    	<td nowrap ALIGN="left"> 
        	<INPUT type="text" name="E01SELACC" size="14" maxlength="13" onclick="document.forms[0].search[1].click()" onkeypress="enterInteger()">
            <a href="javascript:document.forms[0].search[1].click();GetAccount('E01SELACC','','CD','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
    	</td>
    </tr> 
  </table>  
 <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="Check()">
  </p>  
<script language="JavaScript">
  document.forms[0].E01SELUSR.focus();
  document.forms[0].E01SELUSR.select();
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

