<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Frecuencia de Cargos</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function a(code) {
var formLength= top.top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++)
{
	var elementName= top.top.opener.document.forms[0].elements[n].name;
	if(elementName == top.top.opener.fieldName) 
	{
  		top.top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
 top.close();
 }
//-->
</script>
</HEAD>
<BODY>
<h4>Frecuencia de Cargos </h4>
<table align=center class="tableinfo" style="width:95%">
  <tr id="trdark">
    <td width=32 height="18" >
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="406">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr>
  <td width=32 height="18" > 
    <div align="center"><b>M</b></div>
  </td>
  <td width="406"><a href="javascript:a('M')">Mensual</a></td>
  </tr>
  <tr> 
    <td width=32 height="18" > 
      <div align="center"><b>Q</b></div>
    </td>
    <td width="406"><a href="javascript:a('Q')">Trimestral</a></td>
  </tr>
  <tr> 
    <td width=32 height="18" > 
      <div align="center"><b>S</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('S')"> </a><a href="javascript:a('S')">Semestral</a></td>
  </tr>
  <tr> 
    <td width=32 height="22" > 
      <div align="center"><b>R</b></div>
    </td>
    <td width="406" height="22"><a href="javascript:a('R')">Renovaci&oacute;n</a> 
    </td>
  </tr>
  <tr> 
    <td width=32 height="18" > 
      <div align="center"><b>X</b></div>
    </td>
    <td width="406"><a href="javascript:a('X')">Fecha Revisi&oacute;n</a></td>
  </tr>
  <tr> 
    <td width=32 height="18" > 
      <div align="center"><b>A</b></div>
    </td>
    <td width="406"><a href="javascript:a('A')">% en la Apertura</a> </td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
