<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Solicitud de Mantenimiento</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="JavaScript">

function enviar(){

	var acc= document.forms[0].E01CKCACC.value
	if (acc.length > 0)
	{
	    return true;
	}
	else
	{
		alert("Es requerido que se entre un valor al número de Cuenta Corriente.");
		document.forms[0].E01CKCACC.focus();
		return false;
	}
}
</script>




</head>


<body>


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.teller.JSETI0540" onsubmit="return(enviar());">

	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
 	<INPUT TYPE=HIDDEN NAME="SEARCHC" VALUE="">
<h3 align="center">Mantenimiento Lista Proteccion de Cheques<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="enter_param.jsp, ETI0540"></h3>
<hr size="4">
  <br><br><br><br><br><br>
  <table class="tableinfo">
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Banco : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
          <input type="text" name="E01CKCBNK"  size=3 maxlength="2">
        </div>
      </td>
    </tr>
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Cuenta : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
      <input type="text" name="E01CKCACC" size="12" maxlength="12" onKeypress="enterInteger()">
      <a href="javascript:GetAccount('E01CKCACC','','RA','')">
      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" >
      </a>
        </div>
      </td>
    </tr>
  </table>
  <br>

<p align="center">
	<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
</p>

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