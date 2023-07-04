
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
<h4>Status del Documento</h4>
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
      <div align="center"></div>
    </td>
    <td width="420"><a href="javascript:a(' ')">Emitido no Disponible</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>D</b></div>
    </td>
    <td width="420"><a href="javascript:a('D')">Disponible</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>P</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('P')"> Pagado</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>C</b></div>
    </td>
    <td width="420"><a href="javascript:a('C')">Cancelado</a></td>
  </tr>
  <tr> 
    <td width=30 height="22" > 
      <div align="center"><b>S</b></div>
    </td>
    <td width="420" height="22"><a href="javascript:a('S')">Suspendido</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>E</b></div>
    </td>
    <td width="420"><a href="javascript:a('E')">Entregado a Cliente</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>R</b></div>
    </td>
    <td width="420"><a href="javascript:a('R')">Reverso de Certificaci&oacute;n</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>T</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('T')">Todos los Status</a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
