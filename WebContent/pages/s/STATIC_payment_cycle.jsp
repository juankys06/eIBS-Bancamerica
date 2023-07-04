
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Forma de pagos en Préstamos</TITLE>
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
<h4>Forma de Pagos en Préstamos</h4>
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
      <div align="center"><b>nnn</b></div>
    </td>
    <td width="420"><a href="javascript:a('')">Pagos Cícilicos cada nnn días (030, 090, 180)</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>MAT</b></div>
    </td>
    <td width="420"><a href="javascript:a('MAT')">Pagos al Vencimiento del Préstamo</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>SCH</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('SCH')">Plan de Pagos Ingresado Manualmente</a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
