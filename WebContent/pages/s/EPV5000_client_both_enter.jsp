<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Manejo de Clientes</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
if ( document.forms[0].E01PVMIDN.value.length < 1) {
  alert("Ingrese un Número de Identificación");
  document.forms[0].E01PVMIDN.value='';
  document.forms[0].E01PVMIDN.focus();
}
else {
  document.forms[0].submit();
  }
}

</SCRIPT>


<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>

<body>

 
<h3 align="center">Manejo de Clientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_both_enter, EPV5000"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.credprop.JSEPV5000" >

    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
     <INPUT TYPE=HIDDEN name="CLIENCODE">
     <INPUT TYPE=HIDDEN name="CLIENTNAME">
    </p>
    <table  class="tbenter" width="100%" height="75%" border="0" cellspacing=0 cellpadding=2>
	<tr>
      <td align="center"> Ingrese el N&uacute;mero de Identificaci&oacute;n : 
        <input type="text" name="E01PVMIDN" size="17" maxlength="15" onKeyPress="enterInteger()">
        <a href="javascript:GetCustomerDescId('CLIENCODE','CLIENTNAME','E01PVMIDN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="middle" border="0" ></a> 
        <p align="center"> 
						
          <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckACC()"> 
 				 </p> 
     </td>        
    </tr>
  </table>

<script language="JavaScript">
  document.forms[0].E01PVMIDN.focus();
  document.forms[0].E01PVMIDN.select();
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
 </FORM>
</BODY>
</HTML>
 