<html>
<head>
<title>Ajuste de Documentos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">

function CheckNum(){
if(isNaN(document.forms[0].E01DLDNLN.value)||(document.forms[0].E01DLDNLN.value.length < 1)){
alert("Debe ingresar un Numero de Prestamo valido");
document.forms[0].E01DLDNLN.value='';
document.forms[0].E01DLDNLN.focus();
}
else {
  document.forms[0].submit();
}
}

</SCRIPT>


</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<body nowrap bgcolor="#FFFFFF">

<h3 align="center">Ajuste de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_enter_adjustments,EDL0876"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0876" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="800">
  </p>
  <p> 
 <!-- <h4>Por favor Ingrese el n&uacute;mero 
    del Certificado de Dep&oacute;sito</h4>-->
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0" bordercolor="#000000">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing=0 class="tbenter" cellpadding=2 width="100%" border="0">
          <tr> 
            <td nowrap width="50%"> 
              <div align="right"> N&uacute;mero del Pr&eacute;stamo :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01DLDNLN" size="14" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E01DLDNLN','','10','')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Numero de Pr&eacute;stamo" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr> 
            <td nowrap width="50%"> 
              <div align="right"> N&uacute;mero del Documento
                :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01DLDNDR" size="12" maxlength="10" onKeypress="enterInteger()">
              <a href="javascript:GetDocument('E01DLDNDR', document.forms[0].E01DLDNLN.value, '' ,'A')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="..." align="absbottom" border="0" ></a> 
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
</p>
<script language="JavaScript">
  document.forms[0].E01DLDNLN.focus();
  document.forms[0].E01DLDNLN.select();
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
