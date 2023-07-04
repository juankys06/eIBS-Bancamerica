
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Clases de Prestamos</TITLE>
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
<h4>Apertura/Cierre de Cuentas</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30 height="18" >
      <div align="left"><b>Sel</b></div>
    </td>
    <td width="210">
      <div align="left"><b>Cierre</b></div>
    </td>
    <td width="210">
      <div align="left"><b>Apertura</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>1</b></div>
    </td>
    <td width="210"><a href="javascript:a('1')">SI</a></td>
    <td width="210"><a href="javascript:a('1')">SI</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>2</b></div>
    </td>
    <td width="210"><a href="javascript:a('2')">SI</a></td>
    <td width="210"><a href="javascript:a('2')">NO</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>3</b></div>
    </td>
    <td width="210"><a href="javascript:a('3')">NO</a></td>
    <td width="210"><a href="javascript:a('3')">SI</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>4</b></div>
    </td>
    <td width="210"><a href="javascript:a('4')">NO</a></td>
    <td width="210"><a href="javascript:a('4')">NO</a></td>
  </tr>    
</table>
<P>
</P>
</BODY>
</HTML>
