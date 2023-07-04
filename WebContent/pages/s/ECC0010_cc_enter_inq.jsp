<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta Tarjetas de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckCard(){
if ( document.forms[0].E01CCANUM.value.length < 1) {
  alert("Debe entrar una tarjeta válida");
  document.forms[0].E01CCANUM.value='';
  document.forms[0].E01CCANUM.focus();
  return false;
}
else {
  document.forms[0].submit();
  }
}

</SCRIPT>
</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Consulta Tarjetas de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cc_enter_maint, ECC0010"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010I" onsubmit="return(CheckCard())">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="800">
    <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%=request.getParameter("opt")%>">
  </p>

	<table  id="TBHELP" align="center" width="370" border="0" cellspacing="0" cellpadding="0">
	    <tr> 
	      <td nowrap>Numero de tarjeta : 
	        <INPUT type="text" name="E01CCANUM" size="21" maxlength="20" onkeypress="enterInteger()">
				<A href="javascript:GetPlastic('E01CCANUM')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Lista Tarjetas" align="absbottom" border="0"></A> 
		        <br>
				<div align="center"> 
					<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
				</div>     
	      </td>
	    </tr>
  </table>
  
<script language="JavaScript">
  document.forms[0].E01CCANUM.focus();
  document.forms[0].E01CCANUM.select();
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

