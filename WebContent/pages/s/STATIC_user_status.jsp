<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Estado de Usuario</TITLE>
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

<h4> Estado de Usuario</h4>
<table align=center class=tableinfo style="width:95%">
  <TR id=trdark> 
    <TH align=left  > 
      <div align="left">C�dido</div>
    </TH>
    <TH align=left  > 
      <div align="left">Descripci�n</div>
    </TH>
  </TR>
  <tr> 
    <td width=30  > 
      <div align="center"><b>1</b></div>
    </td>
    <td ><A HREF="javascript:a('1')">Activo</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>2</b></div>
    </td>
    <td ><a href="javascript:a('2')">Inactivo</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>3</b></div>
    </td>
    <td ><a href="javascript:a('3')">Desactivado (Usuario Denegado)</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>4</b></div>
    </td>
    <td ><a href="javascript:a('4')">Inactivo (Tiempo expirado) para Accesar al eIBS)</a></td>
  </tr>
  
</table>
<P>
</P>
</BODY>
</HTML>
