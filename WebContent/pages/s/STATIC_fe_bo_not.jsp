<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Notification Type</TITLE>
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
<h4>Tipo Notificaci�n</h4>
<table align=center class=tableinfo style="width:95%">
  <TR id=trdark> 
    <TH align=left  > 
      <div align="left">C�digo</div>
    </TH>
    <TH align=left  > 
      <div align="left">Descripci�n</div>
    </TH>
  </TR>
  <tr> 
    <td width=30 > 
      <div align="center"><b>N</b></div>
    </td>
    <td ><a href="javascript:a('N')">No Notificaci�n</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>S</b></div>
    </td>
    <td ><a href="javascript:a('S')">Enviar Notificaci�n Via Swift</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>F</b></div>
    </td>
    <td ><A HREF="javascript:a('F')">Enviar Notificaci�n Via Fax</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>T</b></div>
    </td>
    <td ><A HREF="javascript:a('T')">Enviar Notificaci�n Via Telex</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>C</b></div>
    </td>
    <td><A HREF="javascript:a('C')">Enviar Notificaci�n Via Swift CLS</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>P</b></div>
    </td>
    <td><A HREF="javascript:a('P')">Imprimir Notificaci�n</a></td>
  </tr>
  
</table>
</BODY>
</HTML>
