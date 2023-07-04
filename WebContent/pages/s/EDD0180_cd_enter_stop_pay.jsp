<html>
<head>
<title>Emision de Ordenes de no Pago</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckNum(){
if(isNaN(document.forms[0].E01STPACC.value)||(document.forms[0].E01STPACC.value.length < 1)){
alert("Debe introducir un valor valido de cuenta");
document.forms[0].E01STPACC.value='';
document.forms[0].E01STPACC.focus();
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

<h3 align="center">Emisi&oacute;n de Ordenes de no Pago
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cd_enter_stop_pay.jsp ,EDD0180"></h3> 
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0180">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
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
              <div align="right">Ingrese el n&uacute;mero de certificado :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01STPACC" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E01STPACC','','CD','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0"  ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>
    <div align="center"> 
      <input id="EIBSBTN" type=button name="Submit" OnClick="CheckNum()" value="Enviar">
    </div>
  </p>
<script language="JavaScript">
  document.forms[0].E01STPACC.focus();
  document.forms[0].E01STPACC.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>;
 <%
 }
%>
</form>
</body>
</html>
