
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Condición de Contrato</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function a(code) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++)
{
	var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName) 
	{
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
 top.close();
 }
//-->
</script>
</HEAD>
<BODY>
<h4>Tipos de Documento</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30 height="20">
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="420" height="20">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18"> 
      <div align="center"><b>1</b></div>
    </td>
    <td width="420"><a href="javascript:a('1')">Cheques de Gerencia</a></td>
  </tr>
  <tr> 
    <td width=30 height="18"> 
      <div align="center"><b>2</b></div>
    </td>
    <td width="420"><a href="javascript:a('2')">Cheques Certificados</a></td>
  </tr>
  <tr> 
    <td width=30 height="18"> 
      <div align="center"><b>3</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('3')"> Transferencias / Recibidas</a></td>
  </tr>
  <tr> 
    <td width=30 height="18"> 
      <div align="center"><b>4</b></div>
    </td>
    <td width="420"><a href="javascript:a('4')">Cupones Certificado Deposito</a></td>
  </tr>
  <tr> 
    <td width=30 height="18"> 
      <div align="center"><b>8</b></div>
    </td>
    <td width="420"><a href="javascript:a('8')">Cheques Corresponsales</a></td>
  </tr>
  <tr> 
    <td width=30 height="18"> 
      <div align="center"><b>9</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('9')">Otros Documentos</a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
