<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Credit Cards Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
if ( document.forms[0].E01CCMACC.value.length < 1) {
  alert("A valid account number must be entered");
  document.forms[0].E01CCMACC.value='';
  document.forms[0].E01CCMACC.focus();
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

<H3 align="center">Mantenimiento Cupo de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cc_enter_maint, ECC0010"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  </p>

  <table class="tbenter" HEIGHT="75%" width="100%" border="0">

    <tr> 
      <td nowrap ALIGN=CENTER>Numero de Cuenta: 
        <INPUT type="text" name="E01CCMACC" size="14" maxlength="12" onkeypress="enterInteger()">
        <a href="javascript:GetAccount('E01CCMACC','','94','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
			<br>
 <div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckACC()">
  </div>     
      </td>
    </tr>
  </table>
  
<script language="JavaScript">
  document.forms[0].E01CCMACC.focus();
  document.forms[0].E01CCMACC.select();
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
