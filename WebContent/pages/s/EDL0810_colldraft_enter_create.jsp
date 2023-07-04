<html>
<head>
<title>Pago de Préstamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckNum(){
	if(isNaN(document.forms[0].E01DLHNRO.value)||(document.forms[0].E01DLHNRO.value.length < 1)){
		alert("Debe ingresar un número de Planilla valido");
		document.forms[0].E01DLHNRO.value='';
		document.forms[0].E01DLHNRO.focus();
  		return false;
	}
	else {
  		return true;
	}
}

</SCRIPT>


</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>




<body  nowrap bgcolor="#FFFFFF">

<h3 align="center">Creación Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="collection_enter_maint, EDL0810"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810" onsubmit="return(CheckNum())">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1600">
  </p>
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0" bordercolor="#000000">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
            <tr><td>&nbsp;</td></tr>
			  <tr><td>&nbsp;</td></tr>
			    <tr><td>&nbsp;</td></tr>
				  <tr><td>&nbsp;</td></tr>
		  <tr> 
            <td nowrap width="50%"> 
              <div align="right">Ingrese el N&uacute;mero de Planilla :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01DLHNRO" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetCollDraft('E01DLHNRO')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
	</p>
<script language="JavaScript">
  document.forms[0].E01DLHNRO.focus();
  document.forms[0].E01DLHNRO.select();
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
