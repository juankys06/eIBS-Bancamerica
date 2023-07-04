<html>
<head>
<title>Mantenimiento Denominaciones de Moneda</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<H3 align="center">Mantenimiento Denominaciones de Moneda<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="enter_currency, ETLR100"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSETLR100">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>
  
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr> 
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td nowrap width="50%"> 
        <div align="right">Moneda : </div>
      </td>
      <td nowrap width="50%"> 
        <input type="text" name="E01TLDCCY" size="4" maxlength="3" >
        <a href="javascript:GetCurrency('E01TLDCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
      </td>
    </tr>
  </table>
  <br>
          <p align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </p>
<script language="JavaScript">
  document.forms[0].E01TLDCCY.focus();
  document.forms[0].E01TLDCCY.select();
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
