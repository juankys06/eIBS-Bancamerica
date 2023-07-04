<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Inclearing/Outgoing Process</TITLE>
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
<H4>Proceso de Cámara Entrante/Saliente</H4>
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
    <td width=30 colspan="2"> 
      <div >Depósito</div>
    </td>
  </tr>
  <tr> 
    <td width=30> 
      <div align="center"><b>R</b></div>
    </td>
    <td ><A HREF="javascript:a('R')">Cheques Locales</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>P</b></div>
    </td>
    <td ><A HREF="javascript:a('P')">Cheques No Locales</a></td>
  </tr>
  
  <tr> 
    <td width=30 colspan="2"> 
      <div > Cámara</div>
    </td>
  </tr>  
  <tr> 
    <td width=30> 
      <div align="center"><b>Y</b></div>
    </td>
    <td ><A HREF="javascript:a('Y')">Cheque de Cámara</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>N</b></div>
    </td>
    <td ><A HREF="javascript:a('N')">Cheque Regular</a></td>
  </tr>
     
</table>
<br>
</BODY>
</HTML>
