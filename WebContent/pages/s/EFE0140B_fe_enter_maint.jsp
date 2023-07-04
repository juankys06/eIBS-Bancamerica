<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Maintenance of Foreign Exchange</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">

function CheckACC(){
if(document.forms[0].E01WKFACC.value.length < 1){
alert("A Certificate valid number must be entered");
document.forms[0].E01WKFACC.value='';
document.forms[0].E01WKFACC.focus();
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
<h3 align="center">Mantenimiento - Moneda Extranjera <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fe_enter_maint, EFE0140B"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  </p>
  <!--<h4>Por favor Ingrese el n&uacute;mero del Certificado de Dep&oacute;sito</h4>-->
  <table class="tbenter" height="75%" width="100%" border="0">
    <tr>
          
      <td nowrap align="center"> N&uacute;mero de Cuenta : 
        <INPUT type="text" name="E01WKFACC" size="12" maxlength="9" onkeypress="enterInteger()">
              <a href="javascript:GetFexAcc('E01WKFACC')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a> 
		<p align="center">&nbsp; </p>              
		<p align="center"> 
    				
          <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckACC()">
 					</p>  
            </td>
          </tr>
        </table>
      
  </form>
<script language="JavaScript">
document.forms[0].E01WKFACC.focus();
document.forms[0].E01WKFACC.select();
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
</body>
</html>
