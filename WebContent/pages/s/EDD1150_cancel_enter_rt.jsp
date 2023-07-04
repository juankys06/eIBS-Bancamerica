<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Cancelacion de Cuentas de Detalle</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
if ( document.forms[0].E01ACMACC.value.length < 1) {
  alert("A valid account number must be entered");
  document.forms[0].E01ACMACC.value='';
  document.forms[0].E01ACMACC.focus();
}
else {
  document.forms[0].submit();
  }
}

</SCRIPT>
</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<body bgcolor="#FFFFFF">

<H3 align="center">Cancelación de Cuentas de Detalle<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cancel_enter_rt, EDD1150"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD1150">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>

  <table class="tbenter" HEIGHT="75%" width="100%" border="0">

    <tr> 
      <td nowrap ALIGN=CENTER> N&uacute;mero de Cuenta : 
        <INPUT type="text" name="E01ACMACC" size="12" maxlength="12" onkeypress="enterInteger()">
        <a href="javascript:GetAccount('E01ACMACC','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
        
  <p>
    <div align="center"> 
      <input id="EIBSBTN" type=button name="Submit" OnClick="CheckACC()" value="Enviar">
    </div>
  </p>   
 	 
      </td>
    </tr>
  </table>
  
<script language="JavaScript">
  document.forms[0].E01ACMACC.focus();
  document.forms[0].E01ACMACC.select();
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
