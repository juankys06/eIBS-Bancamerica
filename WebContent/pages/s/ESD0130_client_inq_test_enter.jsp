<html>
<head>
<title>Consulta de Clientes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">

function CheckNum(){
	if(document.forms[0].E01CUN.value.length < 1){
		alert("Debe introducir un valor valido de cliente");
		document.forms[0].E01CUN.value='';
		document.forms[0].E01CUN.focus();
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

<h3 align="center">Consulta de Clientes
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_inq_test_enter.jsp , ESD0130"></h3> 
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0130">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
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
              <div align="right">Ingrese el n&uacute;mero de cliente :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01CUN" size="10" maxlength="9" onKeypress="enterInteger()">
              <a href="javascript:GetCustomer('E01CUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0"  ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=button name="Submit" value="Enviar" onclick="CheckNum()">
          </div>
<script language="JavaScript">
  document.forms[0].E01CUN.focus();
  document.forms[0].E01CUN.select();
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
