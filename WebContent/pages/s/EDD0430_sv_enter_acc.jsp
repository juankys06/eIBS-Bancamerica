<html>
<head>
<title>Relaciones entre Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  function goAction(op) {

     document.forms[0].opt.value = op;
     document.forms[0].submit();

  }
</SCRIPT>

</head>

<body nowrap bgcolor="#FFFFFF" onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/exit_over.gif','<%=request.getContextPath()%>/images/s/inquiry_over.gif','<%=request.getContextPath()%>/images/s/average_over.gif','<%=request.getContextPath()%>/images/s/statement_account_over.gif')">

<h3 align="center">Relaciones entre Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sv_enter_acc, EDD0430"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430">
  <input type=HIDDEN name="SCREEN" value="700">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  <p>&nbsp;</p>
  <table width="100%" border="0" bordercolor="#000000" cellspacing=0 cellpadding=0>
   
	<tr bordercolor="#FFFFFF"> 
      <td> 
        <table  class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=2>
          
		    <tr><td>&nbsp;</td></tr>
	  <tr>
            <td height="20">&nbsp;</td>
          </tr>
	  <tr><td>&nbsp;</td></tr>
		  <tr><td>&nbsp;</td></tr>
		  <tr> 
            <td width="50%"> 
              <div align="right">Ingrese el N&uacute;mero de Cuenta : </div>
            </td>
            <td width="50%"> 
              <input type="text" name="ACCNUM" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('ACCNUM','','04','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center">
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </p>
<script language="JavaScript">
  document.forms[0].ACCNUM.focus();
  document.forms[0].ACCNUM.select();
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
