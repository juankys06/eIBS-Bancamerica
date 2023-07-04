<html>
<head>
<title>Estado de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />



<body bgcolor="#FFFFFF">


<H3 align="center">Estado de Cuentas de Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="coll_enter_stat.jsp,ESD0817"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSESD0817C">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <h4>&nbsp;</h4>
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr> 
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td height="22">&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td nowrap width="50%"> 
        <div align="right">Ingrese el N&uacute;mero de la Cobranza : </div>
      </td>
      <td nowrap width="50%"> 
        <input type="text" name="E01DCMACC" size="12" maxlength="12" onKeypress="enterInteger()">
        <a href="javascript:GetAccount('E01DCMACC','','CL','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
<script language="JavaScript">
  document.forms[0].E01DCMACC.focus();
  document.forms[0].E01DCMACC.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>;
 <%
 }
%>
</form>
</body>
</html>
