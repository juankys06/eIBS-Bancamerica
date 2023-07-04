
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>C&oacute;mputo Comisi&oacute;n por Aceptaci&oacute;n</TITLE>
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
<h4>Cálculo Comisi&oacute;n por Aceptaci&oacute;n</h4>
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
      <div align="center"><b>Y</b></div>
    </td>
    <td width="420"><a href="javascript:a('Y')">
    	La comisi&oacute;n por aceptaci&oacute;n se calcula en base al t&eacute;rmino (No. de d&iacute;s) <br>
        dados en la negotiaci&oacute;n</a>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><a href="javascript:a('N')">
		La comisi&oacute;n por aceptaci&oacute;n se calcula en base al No. de d&iacute;s transcurridos  <br>
		desde la Fecha de hoy o Fecha de Valor dada (si hay) hasta la Fecha de vencimiento de la Aceptaci&oacute;n</a>
	</td>
  </tr> 
</table>
<P>
</P>
</BODY>
</HTML>
