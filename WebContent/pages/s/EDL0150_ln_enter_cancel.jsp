<html>
<head>
<title>Solicitud de Cancelacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckNum(){
if(isNaN(document.forms[0].E07DEAACC.value)||(document.forms[0].E07DEAACC.value.length < 1)){
alert("Debe ingresar un número de Certificado valido");
document.forms[0].E07DEAACC.value='';
document.forms[0].E07DEAACC.focus();
}
else {
  document.forms[0].submit();
}
}

</SCRIPT>


</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body  nowrap bgcolor="#FFFFFF">

<h3 align="center">Pre - Cancelaci&oacute;n de Pr&eacute;stamos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="lnenter_cancel, EDL0150"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  </p>
 <!-- <h4>Por favor Ingrese el n&uacute;mero del Certificado de Dep&oacute;sito</h4>-->
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
              <div align="right">Ingrese el N&uacute;mero del Pr&eacute;stamo 
                :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E07DEAACC" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E07DEAACC','','10','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p align="center">
    <input type=image  class=imgfilter name="Submit" src="<%=request.getContextPath()%>/images/s/bt_enviar.gif" onClick="CheckNum();" onMouseDown="this.className='' "onMouseUp="this.className='imgfilter' ">
  </p>
<script language="JavaScript">
  document.forms[0].E07DEAACC.focus();
  document.forms[0].E07DEAACC.select();
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
