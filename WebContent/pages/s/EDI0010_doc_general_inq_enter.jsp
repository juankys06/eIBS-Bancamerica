<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Forms</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function GetHelp(){
 var code = document.forms[0].Type.value;
 if( code == "C"){
  GetCustomer('Number');
 }
 else {
  GetAccount('Number','','','');
 }
}

</SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">


<h3 align="center">Consulta de Documentación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="doc_signature_card_enter,EDI0010"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010">
    
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <p>&nbsp;</p>
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0" >
    <tr> 
      <td nowrap> 
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr> 
            <td nowrap colspan="2"> 
              <div align="right">Tipo de documento :</div>
            </td>
            <td nowrap width="47%"> 
              <select name="DocType" >
                <option value="" selected>Todos</option>
                <option value="SC">Tarjeta de Firmas</option>
              </select>
            </td>
          </tr>
          <tr> 
            <td nowrap colspan="2"> 
              <div align="right">Documento para :</div>
            </td>
            <td nowrap width="47%"> 
              <select name="Type" >
                 <option value=""></option>
                <option value="C">Cliente</option>
                <option value="A">Cuenta</option>
              </select>
            </td>
          </tr>
          <tr> 
            <td nowrap colspan="2"> 
              <div align="right">Número de Cuenta o Cliente : </div>
            </td>
            <td nowrap width="47%"> 
              <input type="text" name="Number" size="14" maxlength="12">
              <a href="javascript:GetHelp()"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"  ></a> 
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
  document.forms[0].Number.focus();
  document.forms[0].Number.select();
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
