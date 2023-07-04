<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Solicitud de Consulta</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>

<H3 align="center">Consulta de Boletas de Garantia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bg_enter_inq,ELC5000"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000">
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="7">
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

	<!--<tr valign="middle">
 		<td nowrap width=40% align="right" height="17" >
 		 Ingrese el N&uacute;mero de Cliente :
		</td>
		<td nowrap width=40% align="left" height="17" >
			<input type=TEXT name="CUSNUM" value="" size=15 maxlength=9>
			<a href="javascript:GetCustomer('CUSNUM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a>
          	&nbsp;
		</td>
	</tr>-->
    <tr>
      <td nowrap width="50%">
        <div align="right">N&uacute;mero de Boleta : </div>
      </td>
      <td nowrap width="50%">
        <input type="text" name="E01LCMACC" size="12" maxlength="12" onKeypress="enterInteger()">
        <a href="javascript:GetAccount('E01LCMACC','','43','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      </td>
    </tr>
  </table>
  <br>
          <div align="center">
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
<script language="JavaScript">
  document.forms[0].E01LCMACC.focus();
  document.forms[0].E01LCMACC.select();
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
