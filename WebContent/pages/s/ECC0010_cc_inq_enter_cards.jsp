<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Tarjetas Adicionales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
function CheckACC(){
	if (document.forms[0].search[0].checked ){
		if ( document.forms[0].E01CCMACC.value.length < 1) {
			alert("Debe ser dado un número de cuenta válido");
			document.forms[0].E01CCMACC.value='';
			document.forms[0].E01CCMACC.focus();
		} else {
			document.forms[0].submit();
		}
	} else {
		if ( document.forms[0].E01CCMPCN.value.length < 1) {
			alert("Debe ser dado un número de tarjeta válido");
			document.forms[0].E01CCMPCN.value='';
			document.forms[0].E01CCMPCN.focus();
		} else {
			document.forms[0].submit();
		} 
	} 
}

</SCRIPT>


</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Consulta de Tarjetas de Crédito	
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cc_inq_enter_cards, ECC0010"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010I">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    <INPUT TYPE=HIDDEN NAME="typAcc" VALUE="94">
  </p>

  <table class="tbenter" HEIGHT="25%" width="100%" border="0">
    <tr> 
      <td nowrap ALIGN="right">
      	<input type="radio" name="search" value="A" onclick="document.forms[0].E01CCMACC.focus()" checked>
      		N&uacute;mero de Cuenta : </td>
      <td nowrap ALIGN="left"> 
        <INPUT type="text" name="E01CCMACC" size="12" maxlength="12" 
				onclick="document.forms[0].search[0].click()" onkeypress="enterInteger()">
        <a href="javascript:GetAccount('E01CCMACC','','94','')">
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" onclick="document.forms[0].search[0].click()"></a>  
      </td>
    </tr> 
    <tr> 
      <td nowrap ALIGN="right">
		<input type="radio" name="search" value="C" onclick="document.forms[0].E01CCMPCN.focus()">N&uacute;mero de Tarjeta : </td>
      <td nowrap ALIGN="left"> 
        <INPUT type="text" name="E01CCMPCN" size="24" maxlength="20" 
        		onclick="document.forms[0].search[1].click()" onkeypress="enterInteger()">
        <a href="javascript:GetPlastic2('E01CCMPCN', 'C', '' )">
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" onclick="document.forms[0].search[1].click()"></a>     
      </td>
    </tr>       
  </table>  
 <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit" value="Aceptar" onClick="CheckACC()">
  </p>  
<script language="JavaScript">
  document.forms[0].E01CCMACC.focus();
  document.forms[0].E01CCMACC.select();
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

