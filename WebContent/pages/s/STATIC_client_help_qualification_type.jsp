
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Classification</TITLE>
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
<h4>Seleccione el código de calificación</h4>
<table align=center class="tableinfo" style="width:95%" >
  <tr id="trdark">
    <td  height="18" >
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="410">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td rowspan="2"  height="18" > 
      <div align="center"><b>1</b></div>
    </td>
    <td rowspan="2" width="410"><A HREF="javascript:a('1')"> Utiliza laCalificaci&oacute;n 
      mas alta entre la Objetiva y la Subjetiva para el C&aacute;lculo de Previsiones</a></td>
  </tr>
  <tr> </tr>
  <tr> 
    <td height="18"> 
      <div align="center"><b>2</b></div>
    </td>
    <td width="410" ><A HREF="javascript:a('2')"> No realiza ninguna Calificaci&oacute;n 
      del cliente</a></td>
  </tr>
  <tr> 
    <td rowspan="2" height="18" > 
      <div align="center"><b>3</b></div>
    </td>
    <td rowspan="2" width="410" ><A HREF="javascript:a('3')"> Utiliza la Calificaci&oacute;n 
      Subjetiva para el C&aacute;lculo de la Previsi&oacute;n</a></td>
  </tr>
  <tr> </tr>
</table>
</BODY>
</HTML>
