
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Comisi&oacute;n por Extensi&oacute;n de Validez</TITLE>
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
<h4>Comisi&oacute;n por Extensi&oacute;n de Validez</h4>
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
    	Se Cobrar&aacute; el Cargo Regular por Enmienda adem&aacute;s del Cargo por Extensi&oacute;n de validez<br>
        &oacute; la Comisi&oacute;n por Incremento de Cr&eacute;dito</a>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><a href="javascript:a('N')">
		Solo se cobrar&aacute; Cargo por Extensi&oacute;n de Validez &oacute; la Comisi&oacute;n por Incremento de Cr&eacute;dito</a>
	</td>
  </tr> 
</table>
<P>
</P>
</BODY>
</HTML>
