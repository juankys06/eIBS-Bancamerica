<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Nivel de Autorización de Usuarios</TITLE>
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

<h4>Nivel de Autorización de Usuarios</h4>
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
    <td ><A HREF="javascript:a('A')">Administrador</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>B</b></div>
    </td>
    <td ><a href="javascript:a('B')">Supervisor/Operador</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>C</b></div>
    </td>
    <td ><a href="javascript:a('C')">Supervisor/Operador que puede aprobar sus propias transacciones</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>O</b></div>
    </td>
    <td ><a href="javascript:a('O')">Operador</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>S</b></div>
    </td>
    <td ><a href="javascript:a('S')">Supervisor</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>I</b></div>
    </td>
    <td ><a href="javascript:a('I')">Consultas</a></td>
  </tr>
  
</table>
<P>
</P>
</BODY>
</HTML>
