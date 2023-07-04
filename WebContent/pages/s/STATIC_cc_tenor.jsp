<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Ayuda de Tenor</TITLE>
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
<H4>Ayuda de Tenor</H4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <TR id="trdark"> 
    <TH><div align="left">Código</div></TH>
    <TH>
      <div align="left">Descripción</div>
    </TH>
  </TR>
  <tr> 
    <td width=30 height="18"> 
      <div align="center"><b>S</b></div>
    </td>
    <td><A HREF="javascript:a('S')"> Pago a la Vista</a></td>
  </tr>
  <tr> 
    <td width=30 height="18"> 
      <div align="center"><b>T</b></div>
    </td>
    <td><A HREF="javascript:a('T')"> Aceptación</a></td>
  </tr>
  <tr> 
    <td width=30 height="18"> 
      <div align="center"><b>M</b></div>
    </td>
    <td><A HREF="javascript:a('M')"> Pago Mixto</a></td>
  </tr>
  <tr> 
    <td width=30  height="18"> 
      <div align="center"><b>D</b></div>
    </td>
    <td><A HREF="javascript:a('D')"> Pago Diferido</a></td>
  </tr>
	<TR>
		<TD width="30" height="18">
		<div align="center"><B>N</B></div>
		</TD>
		<TD><A href="javascript:a('N')">Negociación</A></TD>
	</TR></table>
</BODY>
</HTML>
