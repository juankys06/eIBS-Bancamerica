<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Transferencia de Carta De Credito</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>

<h3 align="center">Transferencia de Carta De Credito <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"></h3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510">
    <input TYPE=HIDDEN NAME="SCREEN" VALUE="105">
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr> 
      <td nowrap width="50%" align="right">Numero de Carta De Credito : </td>
      <td nowrap width="50%"> 
        <input type="text" name="E05LCMACC" size="13" maxlength="12" onKeypress="enterInteger()">
        <a href="javascript:GetAccount('E05LCMACC','','40','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a> 
      </td>
    </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>

</form>
<P><BR>
<script language="JavaScript">
  document.forms[0].E05LCMACC.focus();
  document.forms[0].E05LCMACC.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <script Language="Javascript">
            showErrors();
     </script>
 <%
 }
%>
</body>
</html>
