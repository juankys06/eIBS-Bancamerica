<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Via de Pago</TITLE>
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

<h4> Via de Pago</h4>
<table align=center class=tableinfo style="width:95%">
  <TR id=trdark> 
    <TH align=left  > 
      <div align="left">Código</div>
    </TH>
    <TH align=left  > 
      <div align="left">Descripción</div>
    </TH>
  </TR>
  <tr> 
    <td width=30  > 
      <div align="center"><b>A</b></div>
    </td>
    <td ><a href="javascript:a('A')">Cuenta de Detalle</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>G</b></div>
    </td>
    <td ><a href="javascript:a('G')">Cuenta Contable</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>F</b></div>
    </td>
    <td ><A HREF="javascript:a('F')"> Transferencia FED</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>T</b></div>
    </td>
    <td ><a href="javascript:a('T')">Transferencia Telex</a></td>
  </tr>
  <tr> 
    <td width=30   > 
      <div align="center"><b>1</b></div>
    </td>
    <td ><a href="javascript:a('1')">Swift MT-100</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>2</b></div>
    </td>
    <td ><a href="javascript:a('2')">Swift MT-200</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>3</b></div>
    </td>
    <td ><a href="javascript:a('3')">Swift MT-202</a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
