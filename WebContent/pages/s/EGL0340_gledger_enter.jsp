<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id= "gLedger" class= "datapro.eibs.beans.EGL034001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">
 
<H3 align="center">Maestro de Contabilidad General<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="gledger_enter,EGL0340"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL0340">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <h4>&nbsp;</h4>
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr> 
      <td width="50%"> 
        <div align="right">Código de Banco : </div>
      </td>
      <td width="50%"> 
        <div align="left"> 
          <input type="text" name="E01GLMBNK" onKeypress="enterInteger()" size="3" maxlength="2" value="<%= gLedger.getE01GLMBNK().trim()%>" >
        </div>
      </td>
    </tr>
    <tr> 
      <td width="50%"> 
        <div align="right">Código de Moneda : </div>
      </td>
      <td width="50%"> 
        <div align="left"> 
          <input type="text" name="E01GLMCCY" size="3" maxlength="3" value="<%= gLedger.getE01GLMCCY().trim()%>">
          <a href="javascript:GetCurrency('E01GLMCCY', document.forms[0].E01GLMBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
        </div>
      </td>
    </tr>
    <tr> 
      <td width="50%"> 
        <div align="right">Número de Cuenta Contable : </div>
      </td>
      <td width="50%"> 
        <div align="left"> 
          <input type="text" name="E01GLMGLN" onKeypress="enterInteger()" size="17" maxlength="16" value="<%= gLedger.getE01GLMGLN().trim()%>">
          <a href="javascript:GetLedger('E01GLMGLN',document.forms[0].E01GLMBNK.value,document.forms[0].E01GLMCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
        </div>
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
<script language="JavaScript">
  document.forms[0].E01GLMBNK.focus();
  document.forms[0].E01GLMBNK.select();
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
