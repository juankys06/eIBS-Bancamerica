<html>
<head>
<title>Cancelacion de Cheques Certificados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

	function CheckNum(){
	if(isNaN(document.forms[0].E02OFMCKN.value)||(document.forms[0].E02OFMCKN.value.length < 1)){
		alert("Debe ingresar un número de Cheque valido");
		document.forms[0].E02OFMCKN.value='';
		document.forms[0].E02OFMCKN.focus();
	} else {
 		document.forms[0].submit();
	}
	}

</SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<h3 align="center">Cancelación de Cheques Certificados<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="of_chkcer_enter_cancel, EOF0120"></h3>
<hr size="4">
<p>&nbsp;</p>

	<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0120" >
    	<input TYPE=HIDDEN NAME="SCREEN" VALUE="203">
    	<input TYPE=HIDDEN NAME="E02OFMDTY" VALUE="2">
    
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0" bordercolor="#000000">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap width="50%">
              <div align="right">N&uacute;mero de Cuenta :</div>
            </td>
            <td nowrap width="50%">
              <input type="text" id="ACCOUNT" name="E02OFMACC" size="15" maxlength="12" onKeypress="enterInteger()">
       		  <a href="javascript:GetCheckAccount('E02OFMCKN', 'E02OFMACC', '2', 'D')">
       		  <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a> 
            </td>
          </tr>
          <tr> 
            <td nowrap width="50%"> 
              <div align="right">N&uacute;mero de Cheque :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E02OFMCKN" size="12" maxlength="9" onKeypress="enterInteger()">
              <a href="javascript:GetCheckByAccount('E02OFMCKN', document.forms[0].E02OFMACC.value, '2', 'D')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" border="0"></a> 
            </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="CheckNum()" value="Enviar">
  </div>
  
<script language="JavaScript">
  document.forms[0].E02OFMCKN.focus();
  document.forms[0].E02OFMCKN.select();
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
