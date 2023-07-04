<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Forms</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function GetHelp(){
 var code = document.forms[0].APP_CODE.value;
 if( code == "00"){
  GetCustomerDescId('ACCOUNT','','');
 }
 else if( code == "XX"){
  GetProduct('ACCOUNT', '', '');
 }
 else {
  GetAccount('ACCOUNT','','','');
 }
}

function validate() {
  if ((document.forms[0].APP_CODE.value == 'XX' && document.forms[0].OPE_CODE.value !== 'AA') || 
      (document.forms[0].APP_CODE.value !== 'XX' && document.forms[0].OPE_CODE.value == 'AA')) {
   alert("Cuando se seleccionan formulario para PRODUCTO el tipo tiene que ser APLICACION y vise versa");
  }
  else {  
   if (trim(document.forms[0].ACCOUNT.value) == '') {
     var msg = "";
     if (document.forms[0].APP_CODE.value == 'XX') msg = "El código del producto no puede estar en blanco";
     else if (document.forms[0].APP_CODE.value == '00') msg = "El número del cliente no puede estar en blanco";
     else if (document.forms[0].APP_CODE.value == '  ') msg = "El número de la cuenta no puede estar en blanco";
     alert(msg);
   }
   else {
     document.forms[0].submit();
   }
  }
}
</SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">


<h3 align="center">Formularios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="forms_enter,EFRM000"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000">
    
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
  </p>
  <p>&nbsp;</p>
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0" bordercolor="#000000">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr>
            <td nowrap colspan="2">
              <div align="right">Tipo : </div>
            </td>
            <td nowrap width="57%">
              <select name="OPE_CODE" >
                <option value="AA">Aplicación</option>
                <option value="01" selected>Apertura</option>
                <option value="02">Cambio de Tasa</option>
                <option value="03">Rollover</option>
                <option value="04">Pago</option>
                <option value="05">Cancelación</option>
                <option value="XX">Genérico</option>
              </select>
            </td>
          </tr>
          <tr> 
            <td nowrap colspan="2"> 
              <div align="right">Formularios para : </div>
            </td>
            <td nowrap width="57%"> 
              <select name="APP_CODE" >
                <option value="  " selected>Cuenta</option>
                <option value="00">Cliente</option>
                <option value="XX">Producto</option>
              </select>
              <input type="text" name="ACCOUNT" size="12" maxlength="9"">
              <a href="javascript:GetHelp()"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"  ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="validate()" value="Enviar">
  </div>
</p>
<script language="JavaScript">
  document.forms[0].ACCOUNT.focus();
  document.forms[0].ACCOUNT.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>
