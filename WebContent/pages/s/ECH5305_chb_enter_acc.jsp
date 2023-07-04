<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Solicitud de Mantenimiento</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="JavaScript">

function enviar(){
	
	var E01CHPACC= document.forms[0].E01CHPACC.value
	if (E01CHPACC.length > 0)
	{
	    return true;
	}
	else
	{
		alert("Es requerido que se entre un valor al número de Cuenta Corriente.");
		document.forms[0].E01CHPACC.focus();
		return false;
	}
}
</script>

<% 
 if ( !error.getERRNUM().equals("0")  ) 
 {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>

<body >

<H3 align="center">Solicitud Manual a la Dispensadora<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_enter_acc, ECH5305"></H3>


<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH5305" onsubmit="return(enviar());">
<INPUT type="hidden" name="SCREEN" value="2">
  <h4>&nbsp;</h4>
  <Center>
  <table class="tbenter" cellspacing=0 cellpadding=2 border="0">
    <tr> 
      <td nowrap width="50%"><div align="right">N&uacute;mero de Cuenta : </div></td>
      <td nowrap width="50%"><input type="text" name="E01CHPACC" size="12" maxlength="12" onKeypress="enterInteger()">
      <a href="javascript:GetAccount('E01CHPACC','','RA','')">
      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a></td>
    </tr>
  </table>
  
<p align="center"> 
   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
</p>


</form>
</body>
</html>
