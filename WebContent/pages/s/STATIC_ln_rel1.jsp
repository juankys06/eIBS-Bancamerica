
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Tipos de Relacion 1</TITLE>
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
<h4>Tipos de Relaci&oacute;n 1</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30 height="18" >
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="420">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>A</b></div>
    </td>
    <td width="420"><a href="javascript:a('A')">Arrendamiento Financiero Con Capitalizaci&oacute;n</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>B</b></div>
    </td>
    <td width="420"><a href="javascript:a('B')">Arrendamiento Financiero Sin Capitalizaci&oacute;n</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>F</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('F')"> Fondeo</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>G</b></div>
    </td>
    <td width="420"><a href="javascript:a('G')">Administraci&oacute;n de Fondos</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>P</b></div>
    </td>
    <td width="420"><a href="javascript:a('P')">Participaci&oacute;n</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>O</b></div>
    </td>
    <td width="420"><a href="javascript:a('O')">Proyectos de Constructor</a></td>
  </tr>
  <tr> 
    <td width=30 height="22" > 
      <div align="center"><b>S</b></div>
    </td>
    <td width="420" height="22"><a href="javascript:a('S')">Sindicaci&oacute;n</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>T</b></div>
    </td>
    <td width="420"><a href="javascript:a('T')">Indexado a Certificado</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>1</b></div>
    </td>
    <td width="420"><a href="javascript:a('1')">Tasa mas alta de Certificado</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('N')">Ninguno de los Anteriores</a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
