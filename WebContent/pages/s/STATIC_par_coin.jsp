
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
<h4>Codigos de Monedas</h4>
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
      <div align="center"><b>ARS</b></div>
    </td>
    <td width="420"><a href="javascript:a('ARS')">PESOS ARGENTINOS</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>BS</b></div>
    </td>
    <td width="420"><a href="javascript:a('BS')">BOLIVIANOS</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>CLP</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('CLP')">PESOS CHILENOS</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>DEM</b></div>
    </td>
    <td width="420"><a href="javascript:a('DEM')">MARCOS ALEMANES</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>ECD</b></div>
    </td>
    <td width="420"><a href="javascript:a('ECD')">EASTERN CARIBBEAN DOLLAR</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>EUR</b></div>
    </td>
    <td width="420"><a href="javascript:a('EUR')">EUROS CURRENCY</a></td>
  </tr>
    <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>JPY</b></div>
    </td>
    <td width="420"><a href="javascript:a('JPY')">YEN JAPONES</a></td>
  </tr>
    <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>USD</b></div>
    </td>
    <td width="420"><a href="javascript:a('UDS')">DOLAR AMERICANO</a></td>
  </tr>
 
</table>
<P>
</P>
</BODY>
</HTML>
