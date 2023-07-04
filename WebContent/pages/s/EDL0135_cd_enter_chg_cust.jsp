<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Mantenimiento de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "lnChgCust" class= "datapro.eibs.beans.EDL013502Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
 var pass = false;
	if ( document.forms[0].E02DEAACC.value.length < 1) {
  		alert("Teclee una cuenta valida");
  		document.forms[0].E02DEAACC.value='';
  		document.forms[0].E02DEAACC.focus();
	}
	else {
  		 document.forms[0].submit();
  	}
}

</SCRIPT>

</head>

<body>

<h3 align="center">Custodia de Dep&oacute;sitos a Plazo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_enter_chg_status,EDL0135"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0135">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
  </p>
  
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr><td>&nbsp;</td></tr>
		   <tr><td>&nbsp;</td></tr>
		    <tr><td>&nbsp;</td></tr>
			 <tr><td>&nbsp;</td></tr>
		  <tr > 
            <td nowrap colspan=2 align="center"> 
              Número de Cuenta :            
              <INPUT type="text" name="E02DEAACC" size="15" maxlength="12" onkeypress="enterInteger()" value="<%= lnChgCust.getE02DEAACC() %>">
              <a href="javascript:GetAccount('E02DEAACC','','CD','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr>
            <td nowrap colspan =2 align="center">  
             	
 			</td>
          </tr>
        </table>
        <br>
     <p align="center"> 				 
    	<input id="EIBSBTN" type=button name="Submit" OnClick="CheckACC()" value="Enviar">
  	 </p>
  
  <script language="JavaScript">
  document.forms[0].E02DEAACC.focus();
  document.forms[0].E02DEAACC.select();
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
