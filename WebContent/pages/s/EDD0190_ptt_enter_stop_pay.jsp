<html>
<head>
<title>Emision de Ordenes de no Pagossss</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">
function enter(){
	if (document.forms[0].E01DHSACC.value <= 0){
		alert("Debe introducir un valor valido de cuenta");
		return false;
	}
	return true;
}

</SCRIPT>

</head>



<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<body>

<h3 align="center">Emisión de Protestos Manuales
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ptt_enter_stop_pay.jsp , EDD0190"></h3> 
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0190" onsubmit="return(enter())">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr> 
      <td nowrap> 
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr><td>&nbsp;</td></tr>
		   <tr><td>&nbsp;</td></tr>
		    <tr><td>&nbsp;</td></tr>
			 <tr><td>&nbsp;</td></tr>
		  <tr> 
            <td nowrap width="50%"> 
              <div align="right">Ingrese el n&uacute;mero de cuenta :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01DHSACC" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E01DHSACC','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0"  ></a> 
		  </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
<script language="JavaScript">
  document.forms[0].E01DHSACC.focus();
  document.forms[0].E01DHSACC.select();
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
