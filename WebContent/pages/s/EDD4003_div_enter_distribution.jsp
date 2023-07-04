<html>
<head>
<title>Distribución de Dividendos (FMH)</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<H3 align="center">Distribución de Dividendos (FMH)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="div_enter_parameters_prc.jsp, EDD4003"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD4003">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  </p>
  
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr> 
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td nowrap width="50%"> 
        <div align="right">Banco : </div>
      </td>
      <td nowrap width="50%"> 
        <input type="text" name="E01DIVBNK" size="3" maxlength="2" onKeypress="enterInteger()">
      </td>
    </tr>
  </table>
  <br>
          <p align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </p>
<script language="JavaScript">
  document.forms[0].E01DIVBNK.focus();
  document.forms[0].E01DIVBNK.select();
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
