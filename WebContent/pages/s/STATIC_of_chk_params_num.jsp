
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Cheques de Gerencia</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function a(code) {
 top.opener.document.forms[0][top.opener.fieldName].value = code;
 top.opener.document.forms[0][top.opener.fieldName].focus();
 top.close();
 }
//-->
</script>
</HEAD>
<BODY>
<h4>Tipo de N&uacute;meraci&oacute;n de Cheques de Gerencia</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr> 
    <td width=30 height="18" > 
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="420"> 
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>1</b></div>
    </td>
    <td width="420"><a href="javascript:a('1')">Banco y Moneda</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>2</b></div>
    </td>
    <td width="420"><a href="javascript:a('2')">Banco, Moneda y Formato</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>3</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('3')"> Banco, Moneda,Formato y Agencia</a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
