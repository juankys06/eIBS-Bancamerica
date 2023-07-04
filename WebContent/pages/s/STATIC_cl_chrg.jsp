<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Tipos de Cargos</TITLE>
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
<h4>Tipo de Cargos</h4>
<table align=center class="tableinfo" style="width:95%">
  <tr id="trdark">
    <td width=31 height="18" >
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="407">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=31 height="18" > 
      <div align="center"><b>00</b></div>
    </td>
    <td width="407"><a href="javascript:a('00')">No hay Cargos</a></td>
  </tr>
  <tr> 
    <td width=31 height="18" > 
      <div align="center"><b>01</b></div>
    </td>
    <td width="407"><a href="javascript:a('01')">Basados en Saldo No Utilizado</a></td>
  </tr>
  <tr> 
    <td width=31 height="18" > 
      <div align="center"><b>02</b></div>
    </td>
    <td width="407"><A HREF="javascript:a('02')">Basados en Saldo Utilizado</a></td>
  </tr>
  <tr> 
    <td width=31 height="18" > 
      <div align="center"><b>03</b></div>
    </td>
    <td width="407"><a href="javascript:a('03')">Promedio de Saldo No Utilizado</a></td>
  </tr>
  <tr> 
    <td width=31 height="18" > 
      <div align="center"><b>04</b></div>
    </td>
    <td width="407"><a href="javascript:a('04')">Promedio de Saldo Utilizado</a> 
    </td>
  </tr>
  <tr> 
    <td width=31 height="18" > 
      <div align="center"><b>05</b></div>
    </td>
    <td width="407"><a href="javascript:a('05')">Basados en Monto Linea de Cr&eacute;dito</a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
