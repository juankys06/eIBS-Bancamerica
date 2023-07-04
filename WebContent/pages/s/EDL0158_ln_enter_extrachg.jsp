<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Mantenimiento de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
if ( document.forms[0].E01DLSACC.value.length < 1) {
  alert("Debe entrar un cuenta valida");
  document.forms[0].E01DLSACC.value='';
  document.forms[0].E01DLSACC.focus();
  return false;
}
else {
  return true;
  }
}

</SCRIPT>

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "lnExtChg" class= "datapro.eibs.beans.EDL015801Message"  scope="session" />

<body bgcolor="#FFFFFF">

<h3 align="center">Gastos Extraordinarios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_enter_extrachg,EDL0158"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0158" onsubmit="return(CheckACC())">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" height="80%" border="0">
		  <tr> 
            <td nowrap align="center"> 
              Número de Cuenta :
              <INPUT type="text" name="E01DLSACC" size="15" maxlength="12" onkeypress="enterInteger()" value="<%= lnExtChg.getE01DLSACC() %>">
              <a href="javascript:GetAccount('E01DLSACC','','10','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              <p align="center"> 
			<% 
			if (error.getERWRNG().equals("Y")) { 
				error.setERWRNG(" ");
			%>
				<input type="checkbox" name="H01FLGWK2" value="A">
      				Reactivar Cuenta.
			<% 
			} 
			%>
		</p> 
        <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
 				</td>
          </tr>
        </table>
     
  
  <script language="JavaScript">
  document.forms[0].E01DLSACC.focus();
  document.forms[0].E01DLSACC.select();
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
