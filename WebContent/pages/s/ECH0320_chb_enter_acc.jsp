<html>
<head>
<title>Solicitud de Entrega</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<H3 align="center">Entrega de Chequeras a Clientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_enter_acc,ECH0320"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0320">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="7">
  </p>
  
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr> 
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td nowrap width="50%"> 
        <div align="right"><% if (currUser.getE01INT().equals("18")) out.print("RUT");%> Identificacion del Cliente : </div>
      </td>
      <td nowrap width="50%"> 
        <input type="hidden" name="CUSCUN">
        <input type="text" name="E02CUMBNI" size="15" maxlength="15" onKeypress="enterInteger()">
        <a href="javascript:GetCustomerDescId('CUSCUN', '', 'E02CUMBNI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a></td>      
    </tr>    
    <tr> 
      <td nowrap width="50%"> 
        <div align="right">N&uacute;mero de Cuenta : </div>
      </td>
      <td nowrap width="50%"> 
        <input type="text" name="E02SELACC" size="12" maxlength="12" onKeypress="enterInteger()"><IMG
			src="<%=request.getContextPath()%>/images/Check.gif"
			alt="campo obligatorio" border="0">
        <a href="javascript:GetAccByClient('E02SELACC','','RA','',document.forms[0].CUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a></td>      
    </tr>
    
  </table>
  <br>
          <p align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </p>
<script language="JavaScript">
  document.forms[0].E02SELACC.focus();
  document.forms[0].E02SELACC.select();
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
</form></body>
</html>
