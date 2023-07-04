
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Reembolso Autom&aacute;tico de Refinanciamiento/Pr&eacute;stamos</TITLE>
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
<h4>Reembolso Autom&aacute;tico de Refinanciamiento/Pr&eacute;stamos</h4>
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
    	Pr&eacute;stamos por Refinanciamiento de Carta de Cr&eacute;dito Pagada Autom&aacute;ticamente al Vencimiento.</a>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><a href="javascript:a('N')">
		Ser&aacute; Pagada por Usuario</a>
	</td>
  </tr> 
</table>
<P>
</P>
</BODY>
</HTML>
