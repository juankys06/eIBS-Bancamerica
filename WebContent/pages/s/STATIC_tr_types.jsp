
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Tipos de transferencias</TITLE>
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
<h4>Tipos de transferencias</h4> 
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=32  height="18">
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="406">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=32  height="18"> 
      <div align="center"><b>F</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('F')"> Fed</a></td>
  </tr>
  <tr> 
    <td width=32  height="18"> 
      <div align="center"><b>S</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('S')"> Swift</a></td>
  </tr>
  <tr> 
    <td width=32  height="18"> 
      <div align="center"><b>R</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('R')"> Telex</a></td>
  </tr>
  <tr> 
    <td width=32  height="18"> 
      <div align="center"><b>I</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('I')"> Internal/External</a></td>
  </tr>
</table>
<P align="left"> </P>
</BODY>
</HTML>
