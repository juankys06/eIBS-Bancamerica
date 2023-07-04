
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Factor</TITLE>
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
<h4>Factor a Aplicar</h4>
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
      <div align="center"><b>%</b></div>
    </td>
    <td width="420"><a href="javascript:a('%')">Aplica solamente el Porcentaje</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>F</b></div>
    </td>
    <td width="420"><a href="javascript:a('F')">Aplica Cargo Fijo</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>A</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('A')">Suma al Porcentaje Base</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>M</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('M')">Multiplica al Porcentaje Base</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>X</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('X')">Usa Tasa Maxima de Interes</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>Y</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('Y')">Tasa Maxima Efectiva Anual</a></td>
  </tr>
  
</table>
<P>
</P>
</BODY>
</HTML>
