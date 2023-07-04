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
<% 
	String appCode = "";
	String type = request.getParameter("Type");
	if (type == null) type = "";
	if (type.equals("D")) {
		appCode = "RT";
	} else if (type.equals("C")) {
		appCode = "94";
	}
	
	String opt = request.getParameter("opt");
	if (opt == null) opt = "";
%>

function CheckCard(){
if ( document.forms[0].E01CCRNUM.value.length < 1) {
  alert("Debe entrar una tarjeta válida");
  document.forms[0].E01CCRNUM.value='';
  document.forms[0].E01CCRNUM.focus();
}
else {
  document.forms[0].submit();
  }
}

function CheckACC(){
	if (document.forms[0].search[0].checked ){
		if ( document.forms[0].E01CCRCRA.value.length < 1) {
			alert("Debe ser dado un número de cuenta válido");
			document.forms[0].E01CCRCRA.value='';
			document.forms[0].E01CCRCRA.focus();
		} else {
			document.forms[0].submit();
		}
	} else {
		if ( document.forms[0].E01CCRNUM.value.length < 1) {
			alert("Debe ser dado un número de tarjeta válido");
			document.forms[0].E01CCRNUM.value='';
			document.forms[0].E01CCRNUM.focus();
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

<H3 align="center">
<%
	if (opt.equals("4")) out.print("Cambio de Estado "); 

	if (type.equals("D")) out.print("Tarjetas de Débito");
	else if (type.equals("C")) out.print("Tarjetas de Crédito");
	else out.print("Tarjetas Adicionales");
%>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cc_enter_card, ECC0060"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
    <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= opt %>">
    <INPUT TYPE=HIDDEN NAME="typAcc" VALUE="<%= appCode %>">
    
  </p>

  <table class="tbenter" HEIGHT="25%" width="100%" border="0">

	<tr> 
      <td nowrap ALIGN="right">
      	<input type="radio" name="search" value="A" onclick="document.forms[0].E01CCRCRA.focus()" checked>
      		N&uacute;mero de Cuenta : </td>
      <td nowrap ALIGN="left"> 
        <INPUT type="text" name="E01CCRCRA" size="12" maxlength="12" 
				onclick="document.forms[0].search[0].click()" onkeypress="enterInteger()">
        <a href="javascript:GetAccount('E01CCRCRA','',document.forms[0].typAcc.value,'')">
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" onclick="document.forms[0].search[0].click()"></a>     
      </td>
    </tr>
    <tr> 
      <td nowrap ALIGN="right">
		<input type="radio" name="search" value="C" onclick="document.forms[0].E01CCRNUM.focus()">N&uacute;mero de Tarjeta : </td>
      <td nowrap ALIGN="left"> 
        <INPUT type="text" name="E01CCRNUM" size="24" maxlength="20" value="639489"
        		onclick="document.forms[0].search[1].click()" onkeypress="enterInteger()">
			<A href="javascript:actPinpad( 'E01CCRNUM' )">
			<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Leer Tarjetas" align="absbottom" border="0"></A> 

        <%-- 
        <a href="javascript:GetPlastic2('E01CCRNUM', '<%= type %>' ,'')">
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" onclick="document.forms[0].search[1].click()"></a>     
        --%>
      </td>
    </tr>

    <tr> 
      <td nowrap ALIGN=CENTER colspan="2"><div align="center">
      		<input id="EIBSBTN" type="submit" name="Submit" value="Aceptar">
      	</div>
      </td>
    </tr>
  </table>
  
<script language="JavaScript">
  document.forms[0].E01CCRCRA.focus();
  document.forms[0].E01CCRCRA.select();
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

