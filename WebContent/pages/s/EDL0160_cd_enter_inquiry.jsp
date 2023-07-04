<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Solicitud de Consulta de Certificados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>



</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>
<%
String labeltxt = "Certificado";
String titletxt = "Certificados de Deposito";
if (userPO.getOption().equals("IN")) {
    labeltxt = "Inversión";
    titletxt = "Inversiones";
}
%>
<h3 align="center">Consulta de <%=titletxt %><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_enter_inquiry, EDL0160"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130I">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  </p>

  <table width="100%" border="0" bordercolor="#000000" cellspacing=0 cellpadding=0>
   
	<tr bordercolor="#FFFFFF"> 
      <td> 
        <table  class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=2>
          
		    <tr><td>&nbsp;</td></tr>
	  <tr><td>&nbsp;</td></tr>
	  <tr><td>&nbsp;</td></tr>
		  <tr><td>&nbsp;</td></tr>
		  <tr> 
            <td width="50%"> 
              <div align="right">Ingrese el N&uacute;mero de <%=labeltxt %> : </div>
            </td>
            <td width="50%"> 
              <input type="text" name="E01DEAACC" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E01DEAACC','','<%=userPO.getOption().trim() %>','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
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
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>
