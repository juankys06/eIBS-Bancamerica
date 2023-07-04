<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Tipo de Asiento</TITLE>
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
<h4>Tipo de Cuentas</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark"> 
    <td width=32 height="18"> 
      <div align="center"><b>C&oacute;digo</b></div>
    </td>
    <td width="410"> 
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>A</b></div>
    </td>
    <td width="410"><a href="javascript:a('A')">Cuenta de Cliente</a></td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>G</b></div>
    </td>
    <td width="410"><a href="javascript:a('G')">Cuenta Contable </a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
