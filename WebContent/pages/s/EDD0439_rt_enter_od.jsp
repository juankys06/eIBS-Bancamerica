<html>
<head>
<title>Proteccion de Sobregiros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Protecci&oacute;n de Sobregiros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="od_enter.jsp,EDD0439"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0439">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <h4>&nbsp;</h4>
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr> 
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td nowrap width="50%"> 
        <div align="right">N&uacute;mero de Cuenta : </div>
      </td>
      <td nowrap width="50%"> 
        <input type="text" name="E01CLSACC" size="12" maxlength="12" onKeypress="enterInteger()">
        <a href="javascript:GetAccount('E01CLSACC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
      </td>
    </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
<script language="JavaScript">
  document.forms[0].E01CLSACC.focus();
  document.forms[0].E01CLSACC.select();
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
