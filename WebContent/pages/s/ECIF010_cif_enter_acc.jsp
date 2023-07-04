<html>
<head>
<title>Consulta de Cuentas</title>
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

<body nowrap bgcolor="#FFFFFF">

<h3 align="center">Consulta de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cif_enter_acc, ECIF010"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010">
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
	 <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

  <TABLE class="tbenter">
    <TR>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(1)"><b>Consulta</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(2)"><b>Estado de Cuentas</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(3)"><b>Promedio</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </TD>
    </TR>
  </TABLE>
  <p>&nbsp;</p>
  <table width="100%" border="0" bordercolor="#000000" cellspacing=0 cellpadding=0>
   
	<tr bordercolor="#FFFFFF"> 
      <td> 
        <table  class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=2>
          
		    <tr><td>&nbsp;</td></tr>
	  <tr><td>&nbsp;</td></tr>
	  <tr><td>&nbsp;</td></tr>
		  <tr><td>&nbsp;</td></tr>
		  <tr> 
            <td width="50%"> 
              
            <div align="right">Ingrese el N&uacute;mero de Cuenta : </div>
            </td>
            <td width="50%"> 
              <input type="text" name="ACCNUM" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('ACCNUM','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Busqueda por Cliente" align="bottom" border="0" ></a> 
			  <a href="javascript:GetOldAccount('','','','','','','ACCNUM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Busqueda por Cuenta Vieja" align="bottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
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
