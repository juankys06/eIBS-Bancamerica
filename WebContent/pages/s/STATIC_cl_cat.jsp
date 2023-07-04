<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Factor Cobro de Deduccion</TITLE>
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
<h4>Categoria de la Linea</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=32 height="18">
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="410">
      <div align="left"><b>Clasificaci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=32 height="18">&nbsp;</td>
    <td width="410">Nivel Cliente</td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>A</b></div>
    </td>
    <td width="410"><a href="javascript:a('A')">Linea Activa</a></td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>G</b></div>
    </td>
    <td width="410"><a href="javascript:a('G')">Sujeto a Garant&iacute;a</a></td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>I</b></div>
    </td>
    <td width="410"><A HREF="javascript:a('I')"> </a><a href="javascript:a('F')">Incompleta 
      Inactiva </a></td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>S</b></div>
    </td>
    <td width="410"><a href="javascript:a('S')">Supervisada</a> </td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="410"><a href="javascript:a('N')">Linea No- Revolvente</a> </td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>R</b></div>
    </td>
    <td width="410"><a href="javascript:a('R')">Linea Renovable</a></td>
  </tr>
  <tr> 
    <td width=32 >&nbsp;</td>
    <td width="410">Nivel Banco</td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>C</b></div>
    </td>
    <td width="410"><a href="javascript:a('C')">Linea de Control</a> </td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
