<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Tipo Comprobante</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">

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

<h4>Tipo de Comprobante</h4>
<table align=center class=tableinfo style="width:95%">
  <TR id=trdark> 
    <TH align=left  > 
      <div align="left"></div>
    </TH>
    <TH align=left  > 
      <div align="left">Descripci&oacute;n</div>
    </TH>
  </TR>
  <tr> 
    <td width=30  > 
      <div align="center"><b>01</b></div>
    </td>
    <td ><A HREF="javascript:a('01')">Facturas que generan cr&eacute;dito</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>02</b></div>
    </td>
    <td ><a href="javascript:a('02')">Facturas a consumidores finales</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>03</b></div>
    </td>
    <td ><a href="javascript:a('03')">Notas de d&eacute;bito</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>04</b></div>
    </td>
    <td ><a href="javascript:a('04')">Notas de cr&eacute;dito</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>11</b></div>
    </td>
    <td ><a href="javascript:a('11')">Registro proveedores informales</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>12</b></div>
    </td>
    <td ><a href="javascript:a('12')">Registro &uacute;nico de ingresos</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>13</b></div>
    </td>
    <td ><a href="javascript:a('13')">Registro de gastos menores</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>14</b></div>
    </td>
    <td ><a href="javascript:a('14')">Registro de operaciones para empresas<br>acogidas a reg&iacute;menes especiales de tributaci&oacute;n</a></td>
  </tr>
  
</table>
<P>
</P>
</BODY>
</HTML>
