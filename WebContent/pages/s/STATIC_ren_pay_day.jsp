<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Relaci�n con el banco</TITLE>
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
<h4>Renovaci�n/Pago Pr�ximo D�a</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark"> 
    <td width=30 height="18" > 
      <div align="left"><b></b></div>
    </td>
    <td width="420"> 
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>S�</b></div>
    </td>
    <td width="420"><a href="javascript:a('Y')">Los c�digos de renovaci�n (B, C, D, E, H, I, S) se renuevan como la primera operaci�n del d�a del Vencimiento</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center">No<b></b></div>
    </td>
    <td width="420"><a href="javascript:a('N')">Renovaci�n en el cierre del d�a del Vencimiento</a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
