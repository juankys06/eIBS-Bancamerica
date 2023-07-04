
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Calculo de Interes</TITLE>
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
<h4>Calculo de Interes</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30 height="18" >
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="420">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>1</b></div>
    </td>
    <td width="420"><a href="javascript:a('1')">Actual/actual (en per&iacute;odo</a>)</td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>2</b></div>
    </td>
    <td width="420"><a href="javascript:a('2')">Actual/365</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>3</b></div>
    </td>
    <td width="420"><A href="javascript:a('3')">Actual/365 (366 año bisiesto)</A><A href="javascript:a('3')"> </A></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>4</b></div>
    </td>
    <td width="420"><a href="javascript:a('4')"> </a><A href="javascript:a('2')">Actual/360</A></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>5</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('5')">30/360</a></td>
  </tr>
    <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>6</b></div>
    </td>
    <td width="420"><a href="javascript:a('6')">30E/360</a></td>
  </tr>
  
</table>
<P>
</P>
</BODY>
</HTML>
