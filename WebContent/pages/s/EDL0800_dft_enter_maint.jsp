<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Draft Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckNum(){
if(isNaN(document.forms[0].E01DEAACC.value)||(document.forms[0].E01DEAACC.value.length < 1)){
alert("Debe ingresar un número de Préstamo valido");
document.forms[0].E01DEAACC.value='';
document.forms[0].E01DEAACC.focus();
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

<h3 align="center">Mantenimiento Descuento de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="dft_enter_inq,EDL0800"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  </p>
  <!--<h4>Por favor Ingrese el n&uacute;mero del Certificado de Dep&oacute;sito</h4>-->
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0" bordercolor="#000000">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr><td>&nbsp;</td></tr>
		   <tr><td>&nbsp;</td></tr>
		    <tr><td>&nbsp;</td></tr>
			 <tr><td>&nbsp;</td></tr>
		  <tr> 
            <td nowrap width="50%"> 
              <div align="right">N&uacute;mero de Cuenta:</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01DEAACC" size="12" maxlength="12" onkeypress="enterInteger()">
              <a href="javascript:GetAccount('E01DEAACC','','10','G')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
<script language="JavaScript">
  document.forms[0].E01DEAACC.focus();
  document.forms[0].E01DEAACC.select();
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
