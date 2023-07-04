<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Cancelación/Renovación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<body bgcolor="#FFFFFF">

<H3 align="center">Cancelación/Renovación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cancel_renov_enter_basic, EWD0334"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEWD0334Ren">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>

  <table class="tbenter" width="100%" border="0">

    <tr> 
      <td nowrap width="50%">
      	<div align="right">N&uacute;mero de Cuenta :</div>
      </td>
      <td nowrap ALIGN="left" width="50%">  
        <INPUT type="text" name="SWDACC" size="12" maxlength="12" onkeypress="enterInteger()">
        <a href="javascript:GetAccount('SWDACC','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
      </td>
    </tr>
    <tr> 
      <td nowrap width="50%">
      	<div align="right">Cliente :</div>
      </td>
      <td nowrap ALIGN="left" width="50%">  
        <INPUT type="text" name="SWDCUN" size="9" maxlength="9" onkeypress="enterInteger()">
        <a href="javascript:GetCustomer('SWDCUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
      </td>
    </tr>
    <tr> 
      <td nowrap width="50%">
      	<div align="right">Moneda :</div>
      </td>    
      <td nowrap ALIGN="left" width="50%">  
        <INPUT type="text" name="SWDCCY" size="4" maxlength="3" >
        <a href="javascript:GetCurrency('SWDCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
      </td>
    </tr>
    <tr> 
      <td nowrap width="50%">
      	<div align="right">N&uacute;mero de Días para Vencimiento :</div>
      </td>    
      <td nowrap ALIGN="left" width="50%">  
        <INPUT type="text" name="RWDDAY" size="12" maxlength="12" onkeypress="enterInteger()">
        
      </td>
    </tr>
    
  </table>
   <p>
    <div align="center"> 
      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
    </div>
  </p>   
  
<script language="JavaScript">
  document.forms[0].SWDACC.focus();
  document.forms[0].SWDACC.select();
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
