<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>>Customer Bills &amp; Account Payable Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
if ( document.forms[0].E01BTHKEY.value.length < 1) {
  alert("Introduzca un usuario Válido");
  document.forms[0].E01BTHKEY.value='';
  document.forms[0].E01BTHKEY.focus();
}
else {
  document.forms[0].submit();
  }
}

</SCRIPT>
</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Perfil de Acceso Usuario<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="user_access_enter, ESD0007"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESD0007">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>

  <table class="tbenter"  width="100%" border="0">
    <tr> 
      <td nowrap ALIGN=CENTER> 
        Identif. Usuario : 
        <INPUT type="text" name="E01BTHKEY" size="16" maxlength="15" >
        <INPUT type="hidden" name="E01DSC" >
        <a href="javascript:GetUser('E01BTHKEY','E01DSC',document.forms[0].E01BTHKEY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></a> 
      </td>
    </tr>       
  </table>
  <h4>Operation Type</h4>
  <table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"border="0" >
   <tr> 
      <td > 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">      
          <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <input type="radio" name="E01OPETYP" value="0002" checked > Mantenimiento   
            </td>
            <td width="60%" nowrap> 
            </td>
          </tr>  
          <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <input type="radio" name="E01OPETYP" value="0001" > Nuevo
            </td>
            <td width="60%" nowrap> 
            </td>
          </tr>                                     
        </table>      
      </td>
    </tr>  
  </table>  
 <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckACC()">
  </p>  
 
<script language="JavaScript">
  document.forms[0].E01BTHKEY.focus();
  document.forms[0].E01BTHKEY.select();
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
