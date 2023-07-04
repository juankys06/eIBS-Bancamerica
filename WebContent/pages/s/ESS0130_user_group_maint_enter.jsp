<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Mantenimiento Grupos de Usuario</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
  action = "";
  for (counter = 0; counter < document.forms[0].E01OPETYP.length; counter++)
  {
    if (document.forms[0].E01OPETYP[counter].checked)
     action = document.forms[0].E01OPETYP[counter].value; 
  }

if (action != "0001" && document.forms[0].E01USEUID.value.length < 1) {
  alert("Introduzca un grupo de usuario válido");
  document.forms[0].E01USEUID.value='';
  document.forms[0].E01USEUID.focus();
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

<H3 align="center">Mantenimiento Grupos de Usuario<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="user_group_maint_enter, ESS0130"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0130">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>

  <table class="tbenter"  width="100%" border="0">
    <tr> 
      <td nowrap ALIGN=CENTER> 
        Introduzca Grupo : 
        <INPUT type="text" name="E01USEUID" size="11" maxlength="10" >
        <a href="javascript:GetGroupId('E01USEUID')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></a> 
      </td>
    </tr>       
  </table>
  <h4>Tipo Operación</h4>
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
  document.forms[0].E01USEUID.focus();
  document.forms[0].E01USEUID.select();
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
